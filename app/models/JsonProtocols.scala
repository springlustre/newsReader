package models

import java.io.Writer

import controllers.newsObj
import play.api.libs.json._
import models.tables.SlickTables._
/**
 * User: Taoz
 * Date: 8/18/2015
 * Time: 12:10 PM
 */
trait JsonProtocols {

  object ErrorCode {
    val signatureError = 1000101
    val jsonFormatError = 1000102
    val requestAsJsonEmpty = jsonResult(1000103, "receive empty request body")

    val userNotExist = jsonResult(1000201,"user does not exist!")
    val passwordErr  = jsonResult(1000202,"the password error!")
    val registerErr  = jsonResult(1000203,"user register failed!")
    val userNotLogin = jsonResult(1000204,"user does not login!")
    val userEditErr  = jsonResult(1000205,"edit the info failed!")
    val hasNotRight  = jsonResult(1000206,"you doesn't have the right to do that!")

    val newsNotExist = jsonResult(1000301,"the news does not exist!")
    val deleteFailed = jsonResult(1000302,"failed to delete the news!")

    val commentCreateFailed=jsonResult(1000401,"create comments failed!")
  }



  def jsonResult(errorCode: Int, errorMsg: String) = {
    Json.obj("errCode" -> errorCode, "msg" -> errorMsg)
  }

  def jsonResult(errorCode: Int, errorMsg: String, data: JsObject) = {
    Json.obj("errCode" -> errorCode, "msg" -> errorMsg) ++ data
  }

  def successResult(data: JsObject) = success ++ data

  val success = jsonResult(0, "ok")

  implicit val rUser:Writes[rUser] = new Writes[rUser]{
    override def writes(obj:rUser):JsValue={
      Json.obj(
      "id"->obj.id,
      "nickname"->obj.nickname,
      "mobile"->obj.mobile,
      "email"->obj.email,
      "username"->obj.username,
      "sex"->obj.sex,
      "birthday"->obj.birthday,
      "pic"->obj.pic,
      "readNum"->obj.readNum,
      "commentNum"->obj.commentNum,
      "leval"->obj.leval,
      "preference"->obj.preference
      )
    }
  }

  implicit val rComment:Writes[rComment] = new Writes[rComment]{
    override def writes(obj:rComment):JsValue={
      Json.obj(
        "id"->obj.id,
        "userId"->obj.userid,
        "userName"->obj.userName,
        "userPic"->obj.userPic,
        "newsId"->obj.newsId,
        "content"->obj.content,
        "reId"->obj.reId,
        "createTime"->obj.createTime
      )
    }
  }

  implicit val rWangyi:Writes[rWangyi] = new Writes[rWangyi]{
    override def writes(obj:rWangyi):JsValue={
      Json.obj(
        "id"->obj.id,
        "title"->obj.title,
        "author"->obj.author,
        "source"->obj.source,
        "thumbnail"->obj.thumbnail,
        "description"->obj.description,
        "createTime"->obj.createTime,
        "content"->obj.content,
        "picUrls"->obj.picUrls,
        "cateId"->obj.cateId,
        "category"->obj.category,
        "url"->obj.url,
        "tags"->obj.tags
      )
    }
  }


  implicit val rLeifeng:Writes[rLeifeng] = new Writes[rLeifeng]{
    override def writes(obj:rLeifeng):JsValue={
      Json.obj(
        "id"->obj.id,
        "title"->obj.title,
        "author"->obj.author,
        "source"->obj.source,
        "thumbnail"->obj.thumbnail.split("\\?").headOption,
        "description"->obj.description,
        "createTime"->obj.createTime,
        "content"->obj.content,
        "picUrls"->obj.picUrls,
        "cateId"->obj.cateId,
        "category"->obj.category,
        "url"->obj.url,
        "tags"->obj.tags
      )
    }
  }


  implicit val newsObj:Writes[newsObj] = new Writes[newsObj]{
    override def writes(obj:newsObj):JsValue={
      Json.obj(
        "id"->obj.id,
        "title"->obj.title,
        "author"->obj.author,
        "source"->obj.source,
        "thumbnail"->obj.thumbnail,
        "description"->obj.description,
        "createTime"->obj.createTime,
        "content"->obj.content,
        "picUrls"->obj.picUrls,
        "cateId"->obj.cateId,
        "category"->obj.category,
        "url"->obj.url,
        "tags"->obj.tags
      )
    }
  }

}