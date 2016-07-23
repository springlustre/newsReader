package modules

import actor.NewsActor
import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

/**
 * Created by 王春泽 on 2016/3/22.
 */
class ActorModule extends AbstractModule with AkkaGuiceSupport{

  override def configure() : Unit = {
    bindActor[NewsActor]("newsActor")
  }

}
