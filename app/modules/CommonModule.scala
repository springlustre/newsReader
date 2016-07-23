package modules

import common.AppSettings
import com.google.inject.AbstractModule
import play.api.{Configuration, Environment}

/**
 * Created by 王春泽 on 2016/3/22.
 */
class CommonModule(
                    environment: Environment,
                    configuration: Configuration) extends AbstractModule {




  override def configure(): Unit = {

    bind(classOf[AppSettings]).asEagerSingleton()




  }


}
