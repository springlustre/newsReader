package utils

import slick.codegen.SourceCodeGenerator
import slick.driver.{JdbcProfile, MySQLDriver}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * User: Taoz
 * Date: 7/15/2015
 * Time: 9:33 AM
 */
object MySlickCodeGenerator {


  import concurrent.ExecutionContext.Implicits.global

  val slickDriver = "slick.driver.MySQLDriver"
  val jdbcDriver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://139.129.25.229:3306/newsreader?characterEncoding=utf-8"
  val user = "root"
  val password = "wang897618476A"
  val outputFolder = "target/gencode/genTables"
  val pkg = "models.tables"
  def genCustomTables() = {

    // fetch data model
    val driver: JdbcProfile =
      Class.forName(slickDriver + "$").getField("MODULE$").get(null).asInstanceOf[JdbcProfile]
    val dbFactory = driver.api.Database
    val db = dbFactory.forURL(url, driver = jdbcDriver,
      user = user, password = password, keepAliveConnection = true)

    // fetch data model
    val modelAction = MySQLDriver.createModel(Some(MySQLDriver.defaultTables)) // you can filter specific tables here
    val modelFuture = db.run(modelAction)
    // customize code generator
    val codeGenFuture = modelFuture.map(model => new SourceCodeGenerator(model) {

      // override mapped table and class name
      override def entityName =
        dbTableName => "r" + dbTableName.toCamelCase
      override def tableName =
        dbTableName => "t" + dbTableName.toCamelCase
    })

    val codeGenerator = Await.result(codeGenFuture, Duration.Inf)
    codeGenerator.writeToFile(
      slickDriver, outputFolder, pkg, "SlickTables", "SlickTables.scala"
    )


  }


  def genDefaultTables() = {

    slick.codegen.SourceCodeGenerator.main(
      Array(slickDriver, jdbcDriver, url, outputFolder, pkg, user, password)
    )


  }


  def main(args: Array[String]) {
    //genDefaultTables()
    genCustomTables()

    println(s"Tables.scala generated in $outputFolder")
    println("DONE.")

  }


}


