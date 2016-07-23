package controllers

import models.tables.SlickTables

/**
 * Created by wangchunze on 2016/5/17.
 */
class newsObj(
               var id: Long=0l,
               var title: String="" ,
               var author: String="" ,
               var source: String="" ,
               var thumbnail: String="" ,
               var description: String="" ,
               var createTime: Long=0l,
               var content: String="",
               var picUrls: String ="",
               var cateId: Int=0 ,
               var category: String="" ,
               var url: String ="",
               var tags: String ="",
               var relationNews: String=""
               ) {
//  def this() = {
//    this(0l,"", "", "", "", "", 0l, "","", 0, "", "", "")
//  }

  def applyWangyi(t:SlickTables.rWangyi)={
    id=t.id
    title=t.title
    author=t.author
    source=t.source
    thumbnail=t.thumbnail
    description=t.description
    createTime=t.createTime
    content=t.content.getOrElse("")
    picUrls=t.picUrls
    cateId=t.cateId
    category=t.category
    url=t.url
    tags=t.tags
    relationNews=t.relationNews
  }

  def applyLeifeng(t:SlickTables.rLeifeng)={
    id=t.id
    title=t.title
    author=t.author
    source=t.source
    thumbnail=t.thumbnail
    description=t.description
    createTime=t.createTime
    content=t.content
    picUrls=t.picUrls
    cateId=t.cateId
    category=t.category
    url=t.url
    tags=t.tags
    relationNews=t.relationNews
  }


//  val sc = new SparkContext(args(0), "KMeans-test")
//  val lines = sc.textFile(args(1))
//  val K = arg(2)
//  val convergeDist = arg(3)
//  val MaxIter = arg(4)
//  val data = lines.map(parseVector _).cache()
//  var kPoints = data.takeSample(false, K, 42).toArray
//  var tempDist = 1.0
//  var tempIter = 0
//  while (tempDist > convergeDist && tempIter < MaxIter) {
//    var closest = data.map(p => (closestPoint(p, kPoints), (p, 1)))
//    var pointStats = closest.reduceByKey { case ((x1, y1), (x2, y2)) => (x1 + x2, y1 + y2) }
//    var newPoints = pointStats.map { pair => (pair._1, pair._2._1 / pair._2._2) }.collectAsMap()
//    tempDist = 0.0
//    for (i <- 0 until K) {
//      tempDist += kPoints(i).squaredDist(newPoints(i))
//    }
//    for (newP <- newPoints) {
//      kPoints(newP._1) = newP._2
//    }
//    tempIter = tempIter + 1
//  }




}
