package controllers

import com.google.inject.{Inject, Singleton}
import models.JsonProtocols
import models.dao.UserDAO
import models.tables.SlickTables
import org.slf4j.LoggerFactory
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
 * Created by 王春泽 on 2016/4/5.
 */

@Singleton
class UserController @Inject()(
                              userDAO:UserDAO
                              )extends Controller with JsonProtocols{
  private val logger = LoggerFactory.getLogger(this.getClass)

  /**
   * 用户登录
   * @return
   */
  def userLogin=Action.async{implicit request=>
    request.body.asJson match{
      case Some(jsonData)=>
        val login=(jsonData \ "login").as[String]
        val password=(jsonData \ "password").as[String]
        userDAO.findUser(login).flatMap{
          case Some(exist)=>
            userDAO.checkLogin(login,password).map{
              case Some(user)=>
                val timestamp = System.currentTimeMillis()
                Ok(success).withSession(
                  SessionKey.userId -> user.id.toString,
                  SessionKey.mobile -> user.mobile,
                  SessionKey.email -> user.email,
                  SessionKey.username -> user.username,
                  SessionKey.nickName -> user.nickname,
                  SessionKey.timestamp -> timestamp.toString,
                  SessionKey.headImg -> user.pic,
                  SessionKey.uType -> user.userType.toString
                )
              case None=>
                Ok(ErrorCode.passwordErr)
            }
          case None=>
            Future.successful(Ok(ErrorCode.userNotExist))
        }

      case None=>
        Future.successful(Ok(ErrorCode.requestAsJsonEmpty))
    }
  }

  /**
   * 用户注册
   * @return
   */
  def userRegister=Action.async{implicit request=>
      request.body.asJson match{
        case Some(jsonData)=>
          val username=(jsonData \ "username").as[String]
          val password=(jsonData \ "password").as[String]
          val mobile=(jsonData \ "mobile").as[String]
          userDAO.register(username,password,mobile).map{res=>
            if(res>0)
              Ok(success).withSession(
                SessionKey.userId -> res.toString,
                SessionKey.username -> username
              )
            else
              Ok(ErrorCode.registerErr)
          }

        case None=>
          Future.successful(Ok(ErrorCode.requestAsJsonEmpty))
      }
    }


  /**
   * 获取用户信息
   * @return
   */
  def getUserInfo=Action.async{implicit request=>
    request.session.get(SessionKey.userId) match {
      case Some(userId)=>
        userDAO.getUserById(userId.toLong).map{
          case Some(user)=>
            Ok(successResult(Json.obj("data"->user)))
          case None=>
            Ok(ErrorCode.userNotExist)
        }
      case None=>
        Future.successful(Ok(ErrorCode.userNotLogin))
    }
  }


  /**
   * 编辑用户信息
   * @return
   */
  def editInfo=Action.async{implicit request=>
    request.session.get(SessionKey.userId) match {
      case Some(userId) =>
        request.body.asJson match {
          case Some(json) =>
            val nickname=(json \ "nickname").as[String]
            val mobile = (json \ "mobile").as[String]
            val email = (json \ "email").as[String]
            val sex = (json \ "sex").as[String]
            val birthday = (json \ "birthday").as[Long]
            val pic = (json \ "pic").as[String]
            userDAO.getUserById(userId.toLong).flatMap{
              case Some(user)=>
                val u=SlickTables.rUser(
                  userId.toLong,
                  nickname,
                  mobile,
                  email,
                user.username,
                user.password,
                sex,
                birthday,
                pic,
                user.readNum,
                user.commentNum,
                user.leval,
                user.createTime,
                user.preference
                )
                userDAO.modifyUserInfo(u).map{
                  case Success(_)=>
                    Ok(success)
                  case Failure(e) =>
                    logger.error(e.getMessage)
                    Ok(ErrorCode.userEditErr)
                }
              case None=>
                Future.successful(Ok(ErrorCode.userNotExist))
            }
          case None =>
            Future.successful(Ok(ErrorCode.requestAsJsonEmpty))
        }
      case None =>
        Future.successful(Ok(ErrorCode.userNotLogin))
    }
  }















}
