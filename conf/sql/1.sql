CREATE TABLE newsreader.user
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nickname VARCHAR(30) DEFAULT "" NOT NULL,
  mobile VARCHAR(20) DEFAULT "" NOT NULL,
  email VARCHAR(30) DEFAULT "" NOT NULL,
  username VARCHAR(30) DEFAULT "" NOT NULL,
  password VARCHAR(30) DEFAULT "" NOT NULL,
  sex VARCHAR(6) DEFAULT "" NOT NULL,
  birthday BIGINT DEFAULT 0 NOT NULL,
  pic VARCHAR(100) DEFAULT "" NOT NULL,
  read_num INT DEFAULT 0 NOT NULL,
  comment_num INT DEFAULT 0 NOT NULL,
  leval INT DEFAULT 1 NOT NULL,
  create_time BIGINT DEFAULT 0 NOT NULL,
  preference VARCHAR(300) DEFAULT "" NOT NULL
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `wangyi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL DEFAULT '',
  `author` varchar(30) NOT NULL DEFAULT '',
  `source` varchar(255) NOT NULL DEFAULT '',
  `thumbnail` varchar(300) NOT NULL DEFAULT '',
  `description` varchar(300) NOT NULL DEFAULT '',
  `create_time` bigint(20) NOT NULL,
  `content` varchar(10000) NOT NULL DEFAULT '',
  `pic_urls` varchar(1000) NOT NULL DEFAULT '',
  `cate_id` int(11) NOT NULL DEFAULT '0',
  `category` varchar(30) NOT NULL DEFAULT '',
  `url` varchar(300) NOT NULL DEFAULT '',
  `tags` varchar(300) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;

CREATE TABLE `leifeng` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL DEFAULT '',
  `author` varchar(30) NOT NULL DEFAULT '',
  `source` varchar(255) NOT NULL DEFAULT '',
  `thumbnail` varchar(300) NOT NULL DEFAULT '',
  `description` varchar(300) NOT NULL DEFAULT '',
  `create_time` bigint(20) NOT NULL,
  `content` varchar(15000) NOT NULL DEFAULT '',
  `pic_urls` varchar(3000) NOT NULL DEFAULT '',
  `cate_id` int(11) NOT NULL DEFAULT '0',
  `category` varchar(30) NOT NULL DEFAULT '',
  `url` varchar(300) NOT NULL DEFAULT '',
  `tags` varchar(300) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;




CREATE TABLE newsreader.comment
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  userid BIGINT NOT NULL,
  user_name VARCHAR(50) NOT NULL DEFAULT "",
  user_pic VARCHAR(300) NOT NULL DEFAULT "",
  news_id BIGINT NOT NULL,
  content VARCHAR(3000) DEFAULT "" NOT NULL,
  re_id BIGINT DEFAULT 0 NOT NULL,
  create_time BIGINT DEFAULT 0 NOT NULL
);


CREATE TABLE newsreader.reading_record
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  cate_id BIGINT DEFAULT 0 NOT NULL,
  news_id BIGINT DEFAULT 0 NOT NULL,
  user_id BIGINT DEFAULT 0 NOT NULL,
  create_time BIGINT DEFAULT 0 NOT NULL,
  tags VARCHAR(200) NOT NULL
);