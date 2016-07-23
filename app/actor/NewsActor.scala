package actor

import actor.NewsActor.UpdateRelationNews
import akka.actor.Actor
import akka.event.Logging
import com.google.inject.{Inject, Singleton}
import models.dao.NewsDAO
import utils.ToolUtil
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by wangchunze on 2016/5/17.
 */

object NewsActor{
  case object UpdateRelationNews
}

@Singleton
class NewsActor @Inject()(
                         newsDAO:NewsDAO
                           ) extends Actor {
  val logger = Logging(context.system, this)
  import concurrent.duration._

  override def preStart():Unit={
    logger.info(s"${self.path.name} actor starting...")
    println(s"${self.path.name} actor starting...")
  }

  @throws[Exception](classOf[Exception])
  override def postStop():Unit={
    logger.info(s"${self.path.name} actor stopping...")
  }

  private def dataDelay = {
    import com.github.nscala_time.time.Imports._
    val cur = System.currentTimeMillis()
    val triggerTime = DateTime.now.hour(23).minute(59).second(59)
    logger.info(s"The first data add will at $triggerTime")
    triggerTime.getMillis - cur + 1 * 1000
  }

  private val updateRelationNews = context.system.scheduler.schedule(dataDelay millis,  1 days, self, UpdateRelationNews)


  override def receive:Receive={
    case UpdateRelationNews=>
      newsDAO.getAllWangyi.map{seq=>
        seq.map{news=>
          val tag=news.tags.split(",").toList
          newsDAO.getRecommentWangyiNews(tag,news.source).map{res=>
            val data=res.flatten.distinct.filterNot(_.id==news.id)
            val weight=data.map{n=>
              ToolUtil.calculateRealtion(tag,n.content.getOrElse(""))
            }
            val relation=data.zip(weight).sortBy(_._2).reverse.take(5).map(_._1.id).foldLeft("")((a,b)=>a+","+b).drop(1)
            newsDAO.updateWangyiRelation(news.id,relation)
          }
        }
      }


    case unknow@_=>logger.info(s"unknow message:$unknow in ${self.path.name} from ${sender().path.name}")
  }

}
