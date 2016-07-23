import javax.inject.{Inject, Singleton}

import org.slf4j.LoggerFactory
import play.api.Logger
import play.api.mvc._

import scala.concurrent.Future


/**
 * User: Taoz
 * Date: 11/30/2015
 * Time: 9:14 PM
 */
package object controllers {



  import play.api.libs.concurrent.Execution.Implicits.defaultContext

  case class Logging[A](action: Action[A]) extends Action[A] {

    def apply(request: Request[A]): Future[Result] = {
      action(request)
    }

    lazy val parser = action.parser
  }

  //#actions-class-wrapping


  @Singleton
  class LoggingAction @Inject() extends ActionBuilder[Request] {
    private val log = LoggerFactory.getLogger(this.getClass)


    def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
      log.info(s"access log: ${request.uri}")
      block(request)
    }
  }

  object SessionKey {
    val userId = "uid"
    val mobile = "mobile"
    val email = "email"
    val username="username"
    val uType = "utype"
    val timestamp = "timestamp"
    val nickName = "nickname"
    val loginType = "logintype"
    val headImg = "headimg"
  }

  object UserType{
    val admin = 1
    val store = 2
    val user = 3
  }

  def getConfMap(request: Request[AnyContent]): Map[String, String] = {
    lazy val baseInfo = Map(
      "id" -> request.session.get(SessionKey.userId).getOrElse(""),
      "userType" -> request.session.get(SessionKey.uType).getOrElse(""),
      "nickName" -> request.session.get(SessionKey.nickName).getOrElse("")
    )
    baseInfo
  }


  case class rUser(uid: Long, userType: Int, nickName: String, headImg: String)
//  case class rStore(uid: Long, userType: Int, nickName: String, headImg: String)

  class UserRequest[A](val user: rUser, request: Request[A]) extends WrappedRequest[A](request)


  @Singleton
  class UserAction @Inject()() extends ActionRefiner[Request, UserRequest] {

    val SessionTimeOut = 24 * 60 * 60 * 1000 //ms
    val logger = LoggerFactory.getLogger(getClass)

    protected def authUser(request: RequestHeader): Future[Option[rUser]] = {

      val session = request.session

      try {
        val ts = session.get(SessionKey.timestamp).get.toLong
        val uid = session.get(SessionKey.userId).get.toLong
        val userType = session.get(SessionKey.uType).get.toInt
        val nickName = session.get(SessionKey.nickName).get
        val headImg = session.get(SessionKey.headImg).get


        if (System.currentTimeMillis() - ts > SessionTimeOut) {
          Future.successful(None)
        } else {
          Future.successful(Some(rUser(uid, userType, nickName, headImg)))
        }
      } catch {
        case ex: Throwable =>
          logger.info("Not Login Yet.")
          Future.successful(None)
      }
    }


    protected def onUnauthorized(request: RequestHeader) =
//      Results.Redirect(routes.Auth.loginPage()).withNewSession
      Results.Redirect("").withNewSession


    override protected def refine[A](request: Request[A]): Future[Either[Result, UserRequest[A]]] = {
      authUser(request).map {
        case Some(user) =>
          Right(new UserRequest(user, request))
        case _ =>
          Left(onUnauthorized(request))
      }
    }
  }


  @Singleton
  class AdminAction() extends ActionRefiner[UserRequest, UserRequest] {
    /**
     * 商户管理员认证
     */
    private val logger = LoggerFactory.getLogger(this.getClass)

    override protected def refine[A](request: UserRequest[A]): Future[Either[Result, UserRequest[A]]] = {
      Future.successful {
        if (request.user.userType < UserType.user) {
          Right(request)
        } else {
          Left(Results.Forbidden("Only Admin can do."))
        }
      }
    }
  }

  @Singleton
  class SystemAdminAction() extends ActionRefiner[UserRequest, UserRequest] {
    /**
     * 系统管理员认证
     */
    private val logger = LoggerFactory.getLogger(this.getClass)

    override protected def refine[A](request: UserRequest[A]): Future[Either[Result, UserRequest[A]]] = {
      Future.successful {
        if (request.user.userType == UserType.admin) {
          Right(request)
        } else {
          Left(Results.Forbidden("Only Admin can do."))
        }
      }
    }
  }

  @Singleton
  case class ActionUtils @Inject()(
                                    LoggingAction: LoggingAction,
                                    UserAction: UserAction,
                                    AdminAction: AdminAction,
                                    SystemAdminAction: SystemAdminAction
                                    ) {



  }




}