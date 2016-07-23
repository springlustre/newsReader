package models.dao

import com.google.inject.{Inject, Singleton}
import models.tables.SlickTables
import org.slf4j.LoggerFactory
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import slick.driver.JdbcProfile

/**
 * Created by springlustre on 2016/5/9.
 */


@Singleton
class CommentDAO @Inject()(
                            protected val dbConfigProvider:DatabaseConfigProvider
                            )extends HasDatabaseConfigProvider[JdbcProfile]{
  import slick.driver.MySQLDriver.api._
  private val log=LoggerFactory.getLogger(this.getClass)
  private val comments=SlickTables.tComment

  //创建评价
  def createComment(comment:SlickTables.rComment)={
    db.run((comments returning comments.map(_.id))+=comment).mapTo[Long]
  }

  //news评论数量
  def getSumOfNews(newsId:Long,cateId:Int)={
    db.run(comments.filter(c=>(c.newsId===newsId)&&(c.cateId===cateId)).size.result)
  }

  //user的评论数量
  def getSumOfUser(userId:Long)={
    db.run(comments.filter(_.userid===userId).size.result)
  }

  //获取新闻评论 分页
  def getCommentByNewsId(newsId:Long,cateId:Int,pageSize:Int,curPage:Int)={
    db.run(comments.filter(c=>(c.newsId===newsId)&&(c.cateId===cateId)).sortBy(_.createTime.desc).drop((curPage-1)*pageSize)
      .take(pageSize).result)
  }

  //获取个人评论
  def getCommentByUser(userId:Long,curPage:Int,pageSize:Int)={
    db.run(comments.filter(_.userid===userId).drop((curPage-1)*pageSize)
      .take(pageSize).result)
  }



}
