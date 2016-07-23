name := """newsreader"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= {

  val akkaV = "2.4.2"
  val playSlickV = "1.1.1"
  val nscalaTimeV = "2.6.0"
  val mysqlConnectorV = "5.1.31"
  val slickV = "3.1.0"
  val codecV = "1.9"


  Seq(
    "com.typesafe.play" %% "play-slick" % playSlickV,
    "com.typesafe.slick" %% "slick" % slickV withSources(),
    "com.typesafe.slick" %% "slick-codegen" % slickV,
    "com.typesafe.akka" %% "akka-actor" % akkaV withSources(),
    "com.typesafe.akka" %% "akka-remote" % akkaV withSources(),
    "com.typesafe.akka" %% "akka-slf4j" % akkaV,
    "mysql" % "mysql-connector-java" % mysqlConnectorV,
    "com.github.nscala-time" %% "nscala-time" % nscalaTimeV,
    "commons-codec" % "commons-codec" % codecV
  )

}