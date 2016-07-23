package utils

/**
 * Created by wangchunze on 2016/5/17.
 */
object ToolUtil {

  def calculateRealtion(tags:List[String],news:String)={
    tags.map{tag=>
      news.split(tag).length-1
    }.sum
  }

}
