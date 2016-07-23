package controllers

import com.google.inject.{Inject, Singleton}
import models.JsonProtocols
import models.dao.CommentDAO
import models.tables.SlickTables
import org.slf4j.LoggerFactory
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by wangchunze on 2016/5/9.
 */

@Singleton
class CommentController@Inject()(
                                val commentDAO:CommentDAO
                                  )extends Controller with JsonProtocols {
  private val logger = LoggerFactory.getLogger(this.getClass)

  /**
   * 创建评论
   * @return
   */
  def createComment=Action.async{implicit request=>
    request.body.asJson match{
      case Some(json)=>
        val userId=1l
        val userName="王春泽"
        val userPic=""
        val newsId=(json \ "newsId").as[Long]
        val cateId=(json \ "cateId").as[Int]
        val content=(json \ "content").as[String]
        val reId=(json \ "reId").as[Long]
        val createTime=System.currentTimeMillis()
        val c=SlickTables.rComment(
        -1l,
        userid = userId,
        userName=userName,
        userPic=userPic,
        newsId = newsId,
        content = content,
        reId=reId,
        createTime=createTime,
        cateId = cateId
        )
        commentDAO.createComment(c).map{res=>
          if(res>0l){
            Ok(success)
          }else{
            Ok(ErrorCode.commentCreateFailed)
          }
        }

      case None=>
        Future.successful(Ok(ErrorCode.requestAsJsonEmpty))
    }
  }


  /**
   * 获取新闻评论
   * @param newsId
   * @param cateId
   * @param page
   * @param size
   * @return
   */
  def getCommentByNews(newsId:Long,cateId:Int,page:Option[Int],size:Option[Int])=Action.async{implicit request=>
    commentDAO.getSumOfNews(newsId,cateId).flatMap{cnt=>
      val curPage = page.getOrElse(1)
      val pageSize = size.getOrElse(20)
      val pageCnt = if(cnt%pageSize==0) cnt/pageSize else cnt/pageSize + 1
      commentDAO.getCommentByNewsId(newsId,cateId,pageSize,curPage).map{comment=>
        Ok(successResult(Json.obj("data"->comment,"curPage"->curPage,"pageCnt"->pageCnt)))
      }
    }
  }

  /**
   * 获取用户评论
   * @param page
   * @param size
   * @return
   */
  def getCommentByUser(page:Option[Int],size:Option[Int])=Action.async{implicit request=>
    val userId=1l
    commentDAO.getSumOfUser(userId).flatMap{cnt=>
      val curPage = page.getOrElse(1)
      val pageSize = size.getOrElse(20)
      val pageCnt = if(cnt%pageSize==0) cnt/pageSize else cnt/pageSize + 1
      commentDAO.getCommentByUser(userId,pageSize,curPage).map{comment=>
        Ok(Json.obj("data"->comment,"curPage"->curPage,"pageCnt"->pageCnt))
      }
    }
  }






}
