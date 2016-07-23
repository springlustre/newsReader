package models.dao

import javax.security.auth.Subject

import com.google.inject.{Inject, Singleton}
import controllers.newsObj
import models.tables.SlickTables
import org.slf4j.LoggerFactory
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by 王春泽 on 2016/4/5.
 */


@Singleton
class NewsDAO @Inject()(
  protected val dbConfigProvider:DatabaseConfigProvider
  )extends HasDatabaseConfigProvider[JdbcProfile]{
  import slick.driver.MySQLDriver.api._
  private val log=LoggerFactory.getLogger(this.getClass)

  val wangyi=SlickTables.tWangyi
  val leifeng=SlickTables.tLeifeng

  def getTable(cateId:Int)={
    cateId match{
      case 1=>wangyi
      case 2=>leifeng
    }
  }


  //网易社会
  def listWangyi(curPage:Int,pageSize:Int)={
    db.run(wangyi.groupBy(x=>x).map(_._1).sortBy(_.createTime.desc).drop((curPage-1)*pageSize).take(pageSize).result)
  }

  def getWangyiById(id:Long)={
    db.run(wangyi.filter(_.id===id).result.headOption)
  }

  def getAllWangyi={
    db.run(wangyi.result)
  }



  def getRecommentWangyiNews(tagList:List[String],source:String)={
    Future.sequence(tagList.map{tag=>
      db.run(wangyi.filter(w=>(w.description like("%"+tag+"%"))||(w.source===source)).result)
    })
  }

  def updateWangyiRelation(id:Long,relation:String)={
    db.run(wangyi.filter(_.id===id).map(_.relationNews).update(relation))
  }


  //雷锋
  def listLeifeng(curPage:Int,pageSize:Int)={
    db.run(leifeng.groupBy(x=>x).map(_._1).sortBy(_.createTime.desc).drop((curPage-1)*pageSize).take(pageSize).result)
  }

  def getLeifengById(id:Long)={
    db.run(leifeng.filter(_.id===id).result.headOption)
  }

  def getAllLeifeng={
    db.run(leifeng.result)
  }

  def getRecommentLeifengNews(tagList:List[String],source:String)={
    Future.sequence(tagList.map{tag=>
      db.run(leifeng.filter(t=>(t.description like("%"+tag+"%"))||(t.source===source)).result)
    })
  }

  def updateLeifengRelation(id:Long,relation:String)={
    db.run(leifeng.filter(_.id===id).map(_.relationNews).update(relation))
  }


  /**
   * 搜索新闻
   * @param searchKey
   * @return
   */
  def searchNews(searchKey:String)={
    val t1=db.run(
      wangyi.filter(t=>t.title like("%"+searchKey+"%")).result.map(t=>t
      .map{n=>
        val news=new newsObj()
          news.applyWangyi(n)
        news
      })
    )
    val t2=db.run(
      leifeng.filter(t=>t.title like("%"+searchKey+"%")).result.map(t=>t
        .map{n=>
          val news=new newsObj()
          news.applyLeifeng(n)
          news
        })
    )
    for {
      r1 <- t1
      r2 <- t2
    }yield{
      r1++r2
    }
  }

  //获取新闻数量
  def getNewsNum(cateId:Int)={
    db.run(getTable(cateId).length.result)
  }

  //删除新闻
  def deleteNews(cateId:Int,newsId:Long)={
    cateId match{
      case 1=>
        db.run(wangyi.filter(_.id===newsId).delete)
      case 2=>
        db.run(leifeng.filter(_.id===newsId).delete)
      case _=> Future(-1)
    }
  }

  /**
   * 获取近期关键词
   */
  def getKeyword()={
    db.run(wangyi.sortBy(_.createTime desc).take(10).map(_.tags).result)
  }





}
