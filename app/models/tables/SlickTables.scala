package models.tables

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object SlickTables extends {
  val profile = slick.driver.MySQLDriver
} with SlickTables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait SlickTables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = tComment.schema ++ tLeifeng.schema ++ tReadingRecord.schema ++ tUser.schema ++ tWangyi.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table tComment
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param userid Database column userid SqlType(BIGINT)
   *  @param userName Database column user_name SqlType(VARCHAR), Length(50,true), Default()
   *  @param userPic Database column user_pic SqlType(VARCHAR), Length(300,true), Default()
   *  @param newsId Database column news_id SqlType(BIGINT)
   *  @param content Database column content SqlType(VARCHAR), Length(3000,true), Default()
   *  @param reId Database column re_id SqlType(BIGINT), Default(0)
   *  @param createTime Database column create_time SqlType(BIGINT), Default(0)
   *  @param cateId Database column cate_id SqlType(INT) */
  case class rComment(id: Long, userid: Long, userName: String = "", userPic: String = "", newsId: Long, content: String = "", reId: Long = 0L, createTime: Long = 0L, cateId: Int)
  /** GetResult implicit for fetching rComment objects using plain SQL queries */
  implicit def GetResultrComment(implicit e0: GR[Long], e1: GR[String], e2: GR[Int]): GR[rComment] = GR{
    prs => import prs._
    rComment.tupled((<<[Long], <<[Long], <<[String], <<[String], <<[Long], <<[String], <<[Long], <<[Long], <<[Int]))
  }
  /** Table description of table comment. Objects of this class serve as prototypes for rows in queries. */
  class tComment(_tableTag: Tag) extends Table[rComment](_tableTag, "comment") {
    def * = (id, userid, userName, userPic, newsId, content, reId, createTime, cateId) <> (rComment.tupled, rComment.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userid), Rep.Some(userName), Rep.Some(userPic), Rep.Some(newsId), Rep.Some(content), Rep.Some(reId), Rep.Some(createTime), Rep.Some(cateId)).shaped.<>({r=>import r._; _1.map(_=> rComment.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column userid SqlType(BIGINT) */
    val userid: Rep[Long] = column[Long]("userid")
    /** Database column user_name SqlType(VARCHAR), Length(50,true), Default() */
    val userName: Rep[String] = column[String]("user_name", O.Length(50,varying=true), O.Default(""))
    /** Database column user_pic SqlType(VARCHAR), Length(300,true), Default() */
    val userPic: Rep[String] = column[String]("user_pic", O.Length(300,varying=true), O.Default(""))
    /** Database column news_id SqlType(BIGINT) */
    val newsId: Rep[Long] = column[Long]("news_id")
    /** Database column content SqlType(VARCHAR), Length(3000,true), Default() */
    val content: Rep[String] = column[String]("content", O.Length(3000,varying=true), O.Default(""))
    /** Database column re_id SqlType(BIGINT), Default(0) */
    val reId: Rep[Long] = column[Long]("re_id", O.Default(0L))
    /** Database column create_time SqlType(BIGINT), Default(0) */
    val createTime: Rep[Long] = column[Long]("create_time", O.Default(0L))
    /** Database column cate_id SqlType(INT) */
    val cateId: Rep[Int] = column[Int]("cate_id")
  }
  /** Collection-like TableQuery object for table tComment */
  lazy val tComment = new TableQuery(tag => new tComment(tag))

  /** Entity class storing rows of table tLeifeng
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param title Database column title SqlType(VARCHAR), Length(150,true), Default()
   *  @param author Database column author SqlType(VARCHAR), Length(30,true), Default()
   *  @param source Database column source SqlType(VARCHAR), Length(255,true), Default()
   *  @param thumbnail Database column thumbnail SqlType(VARCHAR), Length(300,true), Default()
   *  @param description Database column description SqlType(VARCHAR), Length(300,true), Default()
   *  @param createTime Database column create_time SqlType(BIGINT)
   *  @param content Database column content SqlType(VARCHAR), Length(15000,true), Default()
   *  @param picUrls Database column pic_urls SqlType(VARCHAR), Length(3000,true), Default()
   *  @param cateId Database column cate_id SqlType(INT), Default(0)
   *  @param category Database column category SqlType(VARCHAR), Length(30,true), Default()
   *  @param url Database column url SqlType(VARCHAR), Length(300,true), Default()
   *  @param tags Database column tags SqlType(VARCHAR), Length(300,true), Default()
   *  @param relationNews Database column relation_news SqlType(VARCHAR), Length(30,true), Default() */
  case class rLeifeng(id: Long, title: String = "", author: String = "", source: String = "", thumbnail: String = "", description: String = "", createTime: Long, content: String = "", picUrls: String = "", cateId: Int = 0, category: String = "", url: String = "", tags: String = "", relationNews: String = "")
  /** GetResult implicit for fetching rLeifeng objects using plain SQL queries */
  implicit def GetResultrLeifeng(implicit e0: GR[Long], e1: GR[String], e2: GR[Int]): GR[rLeifeng] = GR{
    prs => import prs._
    rLeifeng.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[String], <<[Long], <<[String], <<[String], <<[Int], <<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table leifeng. Objects of this class serve as prototypes for rows in queries. */
  class tLeifeng(_tableTag: Tag) extends Table[rLeifeng](_tableTag, "leifeng") {
    def * = (id, title, author, source, thumbnail, description, createTime, content, picUrls, cateId, category, url, tags, relationNews) <> (rLeifeng.tupled, rLeifeng.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(title), Rep.Some(author), Rep.Some(source), Rep.Some(thumbnail), Rep.Some(description), Rep.Some(createTime), Rep.Some(content), Rep.Some(picUrls), Rep.Some(cateId), Rep.Some(category), Rep.Some(url), Rep.Some(tags), Rep.Some(relationNews)).shaped.<>({r=>import r._; _1.map(_=> rLeifeng.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13.get, _14.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column title SqlType(VARCHAR), Length(150,true), Default() */
    val title: Rep[String] = column[String]("title", O.Length(150,varying=true), O.Default(""))
    /** Database column author SqlType(VARCHAR), Length(30,true), Default() */
    val author: Rep[String] = column[String]("author", O.Length(30,varying=true), O.Default(""))
    /** Database column source SqlType(VARCHAR), Length(255,true), Default() */
    val source: Rep[String] = column[String]("source", O.Length(255,varying=true), O.Default(""))
    /** Database column thumbnail SqlType(VARCHAR), Length(300,true), Default() */
    val thumbnail: Rep[String] = column[String]("thumbnail", O.Length(300,varying=true), O.Default(""))
    /** Database column description SqlType(VARCHAR), Length(300,true), Default() */
    val description: Rep[String] = column[String]("description", O.Length(300,varying=true), O.Default(""))
    /** Database column create_time SqlType(BIGINT) */
    val createTime: Rep[Long] = column[Long]("create_time")
    /** Database column content SqlType(VARCHAR), Length(15000,true), Default() */
    val content: Rep[String] = column[String]("content", O.Length(15000,varying=true), O.Default(""))
    /** Database column pic_urls SqlType(VARCHAR), Length(3000,true), Default() */
    val picUrls: Rep[String] = column[String]("pic_urls", O.Length(3000,varying=true), O.Default(""))
    /** Database column cate_id SqlType(INT), Default(0) */
    val cateId: Rep[Int] = column[Int]("cate_id", O.Default(0))
    /** Database column category SqlType(VARCHAR), Length(30,true), Default() */
    val category: Rep[String] = column[String]("category", O.Length(30,varying=true), O.Default(""))
    /** Database column url SqlType(VARCHAR), Length(300,true), Default() */
    val url: Rep[String] = column[String]("url", O.Length(300,varying=true), O.Default(""))
    /** Database column tags SqlType(VARCHAR), Length(300,true), Default() */
    val tags: Rep[String] = column[String]("tags", O.Length(300,varying=true), O.Default(""))
    /** Database column relation_news SqlType(VARCHAR), Length(30,true), Default() */
    val relationNews: Rep[String] = column[String]("relation_news", O.Length(30,varying=true), O.Default(""))
  }
  /** Collection-like TableQuery object for table tLeifeng */
  lazy val tLeifeng = new TableQuery(tag => new tLeifeng(tag))

  /** Entity class storing rows of table tReadingRecord
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param cateId Database column cate_id SqlType(BIGINT), Default(0)
   *  @param newsId Database column news_id SqlType(BIGINT), Default(0)
   *  @param userId Database column user_id SqlType(BIGINT), Default(0)
   *  @param createTime Database column create_time SqlType(BIGINT), Default(0)
   *  @param tags Database column tags SqlType(VARCHAR), Length(200,true) */
  case class rReadingRecord(id: Long, cateId: Long = 0L, newsId: Long = 0L, userId: Long = 0L, createTime: Long = 0L, tags: String)
  /** GetResult implicit for fetching rReadingRecord objects using plain SQL queries */
  implicit def GetResultrReadingRecord(implicit e0: GR[Long], e1: GR[String]): GR[rReadingRecord] = GR{
    prs => import prs._
    rReadingRecord.tupled((<<[Long], <<[Long], <<[Long], <<[Long], <<[Long], <<[String]))
  }
  /** Table description of table reading_record. Objects of this class serve as prototypes for rows in queries. */
  class tReadingRecord(_tableTag: Tag) extends Table[rReadingRecord](_tableTag, "reading_record") {
    def * = (id, cateId, newsId, userId, createTime, tags) <> (rReadingRecord.tupled, rReadingRecord.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(cateId), Rep.Some(newsId), Rep.Some(userId), Rep.Some(createTime), Rep.Some(tags)).shaped.<>({r=>import r._; _1.map(_=> rReadingRecord.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column cate_id SqlType(BIGINT), Default(0) */
    val cateId: Rep[Long] = column[Long]("cate_id", O.Default(0L))
    /** Database column news_id SqlType(BIGINT), Default(0) */
    val newsId: Rep[Long] = column[Long]("news_id", O.Default(0L))
    /** Database column user_id SqlType(BIGINT), Default(0) */
    val userId: Rep[Long] = column[Long]("user_id", O.Default(0L))
    /** Database column create_time SqlType(BIGINT), Default(0) */
    val createTime: Rep[Long] = column[Long]("create_time", O.Default(0L))
    /** Database column tags SqlType(VARCHAR), Length(200,true) */
    val tags: Rep[String] = column[String]("tags", O.Length(200,varying=true))
  }
  /** Collection-like TableQuery object for table tReadingRecord */
  lazy val tReadingRecord = new TableQuery(tag => new tReadingRecord(tag))

  /** Entity class storing rows of table tUser
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param nickname Database column nickname SqlType(VARCHAR), Length(30,true), Default()
   *  @param mobile Database column mobile SqlType(VARCHAR), Length(20,true), Default()
   *  @param email Database column email SqlType(VARCHAR), Length(30,true), Default()
   *  @param username Database column username SqlType(VARCHAR), Length(30,true), Default()
   *  @param password Database column password SqlType(VARCHAR), Length(30,true), Default()
   *  @param sex Database column sex SqlType(VARCHAR), Length(6,true), Default()
   *  @param birthday Database column birthday SqlType(BIGINT), Default(0)
   *  @param pic Database column pic SqlType(VARCHAR), Length(100,true), Default()
   *  @param readNum Database column read_num SqlType(INT), Default(0)
   *  @param commentNum Database column comment_num SqlType(INT), Default(0)
   *  @param leval Database column leval SqlType(INT), Default(1)
   *  @param createTime Database column create_time SqlType(BIGINT), Default(0)
   *  @param preference Database column preference SqlType(VARCHAR), Length(300,true), Default()
   *  @param userType Database column user_type SqlType(INT), Default(1) */
  case class rUser(id: Long, nickname: String = "", mobile: String = "", email: String = "", username: String = "", password: String = "", sex: String = "", birthday: Long = 0L, pic: String = "", readNum: Int = 0, commentNum: Int = 0, leval: Int = 1, createTime: Long = 0L, preference: String = "", userType: Int = 1)
  /** GetResult implicit for fetching rUser objects using plain SQL queries */
  implicit def GetResultrUser(implicit e0: GR[Long], e1: GR[String], e2: GR[Int]): GR[rUser] = GR{
    prs => import prs._
    rUser.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[Long], <<[String], <<[Int], <<[Int], <<[Int], <<[Long], <<[String], <<[Int]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class tUser(_tableTag: Tag) extends Table[rUser](_tableTag, "user") {
    def * = (id, nickname, mobile, email, username, password, sex, birthday, pic, readNum, commentNum, leval, createTime, preference, userType) <> (rUser.tupled, rUser.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(nickname), Rep.Some(mobile), Rep.Some(email), Rep.Some(username), Rep.Some(password), Rep.Some(sex), Rep.Some(birthday), Rep.Some(pic), Rep.Some(readNum), Rep.Some(commentNum), Rep.Some(leval), Rep.Some(createTime), Rep.Some(preference), Rep.Some(userType)).shaped.<>({r=>import r._; _1.map(_=> rUser.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13.get, _14.get, _15.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column nickname SqlType(VARCHAR), Length(30,true), Default() */
    val nickname: Rep[String] = column[String]("nickname", O.Length(30,varying=true), O.Default(""))
    /** Database column mobile SqlType(VARCHAR), Length(20,true), Default() */
    val mobile: Rep[String] = column[String]("mobile", O.Length(20,varying=true), O.Default(""))
    /** Database column email SqlType(VARCHAR), Length(30,true), Default() */
    val email: Rep[String] = column[String]("email", O.Length(30,varying=true), O.Default(""))
    /** Database column username SqlType(VARCHAR), Length(30,true), Default() */
    val username: Rep[String] = column[String]("username", O.Length(30,varying=true), O.Default(""))
    /** Database column password SqlType(VARCHAR), Length(30,true), Default() */
    val password: Rep[String] = column[String]("password", O.Length(30,varying=true), O.Default(""))
    /** Database column sex SqlType(VARCHAR), Length(6,true), Default() */
    val sex: Rep[String] = column[String]("sex", O.Length(6,varying=true), O.Default(""))
    /** Database column birthday SqlType(BIGINT), Default(0) */
    val birthday: Rep[Long] = column[Long]("birthday", O.Default(0L))
    /** Database column pic SqlType(VARCHAR), Length(100,true), Default() */
    val pic: Rep[String] = column[String]("pic", O.Length(100,varying=true), O.Default(""))
    /** Database column read_num SqlType(INT), Default(0) */
    val readNum: Rep[Int] = column[Int]("read_num", O.Default(0))
    /** Database column comment_num SqlType(INT), Default(0) */
    val commentNum: Rep[Int] = column[Int]("comment_num", O.Default(0))
    /** Database column leval SqlType(INT), Default(1) */
    val leval: Rep[Int] = column[Int]("leval", O.Default(1))
    /** Database column create_time SqlType(BIGINT), Default(0) */
    val createTime: Rep[Long] = column[Long]("create_time", O.Default(0L))
    /** Database column preference SqlType(VARCHAR), Length(300,true), Default() */
    val preference: Rep[String] = column[String]("preference", O.Length(300,varying=true), O.Default(""))
    /** Database column user_type SqlType(INT), Default(1) */
    val userType: Rep[Int] = column[Int]("user_type", O.Default(1))
  }
  /** Collection-like TableQuery object for table tUser */
  lazy val tUser = new TableQuery(tag => new tUser(tag))

  /** Entity class storing rows of table tWangyi
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param title Database column title SqlType(VARCHAR), Length(50,true), Default()
   *  @param author Database column author SqlType(VARCHAR), Length(30,true), Default()
   *  @param source Database column source SqlType(VARCHAR), Length(255,true), Default()
   *  @param thumbnail Database column thumbnail SqlType(VARCHAR), Length(300,true), Default()
   *  @param description Database column description SqlType(VARCHAR), Length(300,true), Default()
   *  @param createTime Database column create_time SqlType(BIGINT)
   *  @param content Database column content SqlType(VARCHAR), Length(19000,true), Default(None)
   *  @param picUrls Database column pic_urls SqlType(VARCHAR), Length(1000,true), Default()
   *  @param cateId Database column cate_id SqlType(INT), Default(0)
   *  @param category Database column category SqlType(VARCHAR), Length(30,true), Default()
   *  @param url Database column url SqlType(VARCHAR), Length(300,true), Default()
   *  @param tags Database column tags SqlType(VARCHAR), Length(300,true), Default()
   *  @param relationNews Database column relation_news SqlType(VARCHAR), Length(30,true), Default() */
  case class rWangyi(id: Long, title: String = "", author: String = "", source: String = "", thumbnail: String = "", description: String = "", createTime: Long, content: Option[String] = None, picUrls: String = "", cateId: Int = 0, category: String = "", url: String = "", tags: String = "", relationNews: String = "")
  /** GetResult implicit for fetching rWangyi objects using plain SQL queries */
  implicit def GetResultrWangyi(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Int]): GR[rWangyi] = GR{
    prs => import prs._
    rWangyi.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[String], <<[Long], <<?[String], <<[String], <<[Int], <<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table wangyi. Objects of this class serve as prototypes for rows in queries. */
  class tWangyi(_tableTag: Tag) extends Table[rWangyi](_tableTag, "wangyi") {
    def * = (id, title, author, source, thumbnail, description, createTime, content, picUrls, cateId, category, url, tags, relationNews) <> (rWangyi.tupled, rWangyi.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(title), Rep.Some(author), Rep.Some(source), Rep.Some(thumbnail), Rep.Some(description), Rep.Some(createTime), content, Rep.Some(picUrls), Rep.Some(cateId), Rep.Some(category), Rep.Some(url), Rep.Some(tags), Rep.Some(relationNews)).shaped.<>({r=>import r._; _1.map(_=> rWangyi.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8, _9.get, _10.get, _11.get, _12.get, _13.get, _14.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column title SqlType(VARCHAR), Length(50,true), Default() */
    val title: Rep[String] = column[String]("title", O.Length(50,varying=true), O.Default(""))
    /** Database column author SqlType(VARCHAR), Length(30,true), Default() */
    val author: Rep[String] = column[String]("author", O.Length(30,varying=true), O.Default(""))
    /** Database column source SqlType(VARCHAR), Length(255,true), Default() */
    val source: Rep[String] = column[String]("source", O.Length(255,varying=true), O.Default(""))
    /** Database column thumbnail SqlType(VARCHAR), Length(300,true), Default() */
    val thumbnail: Rep[String] = column[String]("thumbnail", O.Length(300,varying=true), O.Default(""))
    /** Database column description SqlType(VARCHAR), Length(300,true), Default() */
    val description: Rep[String] = column[String]("description", O.Length(300,varying=true), O.Default(""))
    /** Database column create_time SqlType(BIGINT) */
    val createTime: Rep[Long] = column[Long]("create_time")
    /** Database column content SqlType(VARCHAR), Length(19000,true), Default(None) */
    val content: Rep[Option[String]] = column[Option[String]]("content", O.Length(19000,varying=true), O.Default(None))
    /** Database column pic_urls SqlType(VARCHAR), Length(1000,true), Default() */
    val picUrls: Rep[String] = column[String]("pic_urls", O.Length(1000,varying=true), O.Default(""))
    /** Database column cate_id SqlType(INT), Default(0) */
    val cateId: Rep[Int] = column[Int]("cate_id", O.Default(0))
    /** Database column category SqlType(VARCHAR), Length(30,true), Default() */
    val category: Rep[String] = column[String]("category", O.Length(30,varying=true), O.Default(""))
    /** Database column url SqlType(VARCHAR), Length(300,true), Default() */
    val url: Rep[String] = column[String]("url", O.Length(300,varying=true), O.Default(""))
    /** Database column tags SqlType(VARCHAR), Length(300,true), Default() */
    val tags: Rep[String] = column[String]("tags", O.Length(300,varying=true), O.Default(""))
    /** Database column relation_news SqlType(VARCHAR), Length(30,true), Default() */
    val relationNews: Rep[String] = column[String]("relation_news", O.Length(30,varying=true), O.Default(""))
  }
  /** Collection-like TableQuery object for table tWangyi */
  lazy val tWangyi = new TableQuery(tag => new tWangyi(tag))
}
