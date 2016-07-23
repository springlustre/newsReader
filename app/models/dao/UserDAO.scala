package models.dao

import com.google.inject.{Inject, Singleton}
import models.tables.SlickTables
import org.slf4j.LoggerFactory
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import slick.driver.JdbcProfile

/**
 * Created by 王春泽 on 2016/4/5.
 */
@Singleton
class UserDAO @Inject()(
                       protected val dbConfigProvider:DatabaseConfigProvider
                       )extends HasDatabaseConfigProvider[JdbcProfile] {
  import slick.driver.MySQLDriver.api._
  private val log=LoggerFactory.getLogger(this.getClass)
  private val User=SlickTables.tUser


  /**
   * 根据登录输入查找用户
   * @param login
   * @return
   */
  def findUser(login:String)={
    db.run(User.filter(t=>(t.username===login)||(t.mobile===login)||(t.email===login)).result.headOption)
  }

  def findAdmin(login:String)={
    db.run(User.filter(t=>t.leval===3&&((t.username===login)||(t.mobile===login)||(t.email===login))).result.headOption)
  }

  /**
   * 验证用户登录
   * @param login
   * @param password
   * @return
   */
  def checkLogin(login:String,password:String)={
    db.run(User.filter(t=>((t.username===login)||(t.mobile===login)||(t.email===login))
                          &&(t.password===password)).result.headOption)
  }
  /**
   * 根据id获取用户信息
   * @param userId
   * @return
   */
  def getUserById(userId:Long)={
    db.run(User.filter(_.id===userId).result.headOption)
  }

  /**
   * 修改用户信息
   * @param user
   * @return
   */
  def modifyUserInfo(user:SlickTables.rUser)={
    db.run(User.insertOrUpdate(user).asTry)
  }


  /**
   * 手机注册
   * @return
   */
  def registerByMobile(mobile:String,password:String)={
    db.run(User.map(t=>(t.mobile,t.password)).returning(User.map(_.id))+=(mobile,password)).mapTo[Long]
  }

  /**
   * 邮箱注册
   * @param email
   * @param password
   * @return
   */
  def registerByEmail(email:String,password:String)={
    db.run(User.map(t=>(t.email,t.password)).returning(User.map(_.id))+=(email,password)).mapTo[Long]
  }

  /**
   * 用户名注册
   * @param username
   * @param password
   * @return
   */
  def registerByUsername(username:String,password:String)={
    db.run(User.map(t=>(t.username,t.password)).returning(User.map(_.id))+=(username,password)).mapTo[Long]
  }


  def register(username:String,password:String,mobile:String)={
    db.run(User.map(t=>(t.username,t.password,t.mobile)).returning(User.map(_.id))+=(username,password,mobile)
    ).mapTo[Long]
  }

}
