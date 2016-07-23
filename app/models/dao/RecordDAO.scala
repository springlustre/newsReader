package models.dao

import com.google.inject.{Inject, Singleton}
import controllers.newsObj
import models.tables.SlickTables
import org.slf4j.LoggerFactory
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by 王春泽 on 2016/4/5.
 */


@Singleton
class RecordDAO @Inject()(
  protected val dbConfigProvider:DatabaseConfigProvider
  )extends HasDatabaseConfigProvider[JdbcProfile]{
  import slick.driver.MySQLDriver.api._
  private val log=LoggerFactory.getLogger(this.getClass)

  val record=SlickTables.tReadingRecord

  def createRecord(cateId:Long,newsId:Long,userId:Long,createTime:Long,tags:String)={
    val r = new SlickTables.rReadingRecord(
      id = -1,
      cateId = cateId,
      newsId = newsId,
      userId = userId,
      createTime = System.currentTimeMillis(),
      tags = tags
    )
    db.run(record returning record.map(_.id)+=r ).mapTo[Long]
  }



}
