package common

import com.google.inject.{Inject, Singleton}
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import play.api.{Environment, Configuration}
import slick.driver.JdbcProfile

/**
 * Created by 王春泽 on 2016/3/22.
 */
@Singleton
class AppSettings@Inject()(
                            protected val dbConfigProvider: DatabaseConfigProvider,
                            environment: Environment,
                            configuration: Configuration
                            ) extends HasDatabaseConfigProvider[JdbcProfile] {

  dbConfig.db//init DatabaseConfigProvider

  private[this] val config = configuration.underlying

//  val appConfig = config.getConfig("app")

}
