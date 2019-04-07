-- MySQL dump 10.17  Distrib 10.3.13-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: epd
-- ------------------------------------------------------
-- Server version	5.7.23-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `api`
--

DROP TABLE IF EXISTS `api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `uri` varchar(200) NOT NULL,
  `show_uri` varchar(200) DEFAULT NULL COMMENT '拼接给前台的完整uri',
  `content_type` varchar(200) NOT NULL,
  `method` varchar(200) NOT NULL,
  `request_param` blob,
  `response_param` blob,
  `project_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `create_by` int(11) DEFAULT NULL,
  `create_by_name` varchar(200) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_by_name` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `api_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='接口详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api`
--

LOCK TABLES `api` WRITE;
/*!40000 ALTER TABLE `api` DISABLE KEYS */;
INSERT INTO `api` VALUES (1,'222','22','2222',NULL,'application/x-www-form-urlencoded','POST','[{\"name\":\"asdas\",\"cate\":\"String\",\"default\":\"ww\",\"describe\":\"qqq\",\"id\":\"1\",\"require\":\"on\",\"property\":[]}]','[{\"name\":\"asd\",\"cate\":\"String\",\"default\":\"asd\",\"describe\":\"sad\",\"id\":\"1\",\"require\":\"on\",\"property\":[]}]',1,1,2,'admin',NULL,NULL,'2019-04-07 22:13:10',NULL),(2,'login','登录使用','login',NULL,'application/x-www-form-urlencoded','POST','[{\"name\":\"username\",\"cate\":\"String\",\"default\":\"admin\",\"describe\":\"用户名\",\"id\":\"1\",\"require\":\"on\",\"property\":[]},{\"name\":\"password\",\"cate\":\"String\",\"default\":\"admin\",\"describe\":\"密码\",\"id\":\"2\",\"require\":\"on\",\"property\":[]}]','[{\"name\":\"code\",\"cate\":\"Number\",\"default\":\"0\",\"describe\":\"状态码\",\"id\":\"1\",\"require\":\"on\",\"property\":[]},{\"name\":\"data\",\"cate\":\"Object\",\"default\":\"\",\"describe\":\"数据\",\"id\":\"2\",\"require\":\"on\",\"property\":[]},{\"name\":\"message\",\"cate\":\"String\",\"default\":\"成功\",\"describe\":\"响应文本\",\"id\":\"3\",\"require\":\"on\",\"property\":[]}]',1,1,2,'admin',NULL,NULL,'2019-04-07 22:57:21',NULL),(3,'测试接口','测试使用','test',NULL,'application/json','PUT','[{\"name\":\"\",\"cate\":\"\",\"default\":\"\",\"describe\":\"\",\"id\":\"1\",\"require\":\"\",\"property\":[]}]','[{\"name\":\"code\",\"cate\":\"Number\",\"default\":\"\",\"describe\":\"测试\",\"id\":\"1\",\"require\":\"\",\"property\":[]}]',1,1,2,'admin',NULL,NULL,'2019-04-07 22:59:56',NULL),(4,'新测试','1','2',NULL,'multipart/form-data','GET','[{\"name\":\"\",\"cate\":\"\",\"default\":\"\",\"describe\":\"\",\"id\":\"1\",\"require\":\"\",\"property\":[]}]','[{\"name\":\"code\",\"cate\":\"String\",\"default\":\"1\",\"describe\":\"1\",\"id\":\"1\",\"require\":\"on\",\"property\":[]}]',1,1,2,'admin',NULL,NULL,'2019-04-07 23:06:21',NULL),(5,'test','description','uri','/test//uri','a','get','aa','bb',1,1,2,'admin',NULL,NULL,'2019-04-07 23:46:40',NULL),(6,'test','description','uri','/test//uri','a','get','aa','bb',1,1,2,'admin',NULL,NULL,'2019-04-07 23:48:03',NULL),(7,'test','description','uri','/test/uri','a','get','aa','bb',1,1,2,'admin',NULL,NULL,'2019-04-07 23:49:40',NULL);
/*!40000 ALTER TABLE `api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '模块名称',
  `uri` varchar(255) DEFAULT NULL COMMENT '模块uri',
  `description` varchar(255) DEFAULT NULL COMMENT '模块描述',
  `project_id` int(11) NOT NULL,
  `create_by` int(11) NOT NULL,
  `create_by_name` varchar(200) NOT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_by_name` varchar(200) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `module_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='项目模块';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,'登录模块','',NULL,1,2,'admin',2,'admin','2019-04-07 21:48:10','2019-04-07 22:55:19');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '项目名称',
  `uri` varchar(200) NOT NULL COMMENT 'project uuid',
  `description` varchar(255) DEFAULT NULL COMMENT '项目说明',
  `base_url` varchar(200) DEFAULT NULL,
  `open` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否公开 default 0 私有，1 公开',
  `owner` int(11) NOT NULL DEFAULT '0' COMMENT '项目所有者，userid',
  `owner_name` varchar(200) NOT NULL,
  `return_format` varchar(200) DEFAULT NULL COMMENT '统一返回格式',
  `create_time` datetime NOT NULL COMMENT '项目创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '项目信息更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `project_id_uindex` (`id`) USING BTREE,
  UNIQUE KEY `project_uri_uindex` (`uri`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='项目实体类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'测试添加接口','52c75f0389554fe681eff8c5462af67d','测试使用','test',1,2,'admin',NULL,'2019-04-07 21:44:38',NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_map`
--

DROP TABLE IF EXISTS `project_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `uri` varchar(200) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  `api_id` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL COMMENT '父级ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `project_map_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='项目map';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_map`
--

LOCK TABLES `project_map` WRITE;
/*!40000 ALTER TABLE `project_map` DISABLE KEYS */;
INSERT INTO `project_map` VALUES (1,'测试添加接口','52c75f0389554fe681eff8c5462af67d',1,NULL,NULL,0),(2,'登录模块','',NULL,1,NULL,1),(3,'222','2222',NULL,NULL,1,2),(4,'login','login',NULL,NULL,2,2),(5,'测试接口','test',NULL,NULL,3,2),(6,'新测试','2',NULL,NULL,4,2),(7,'test','uri',NULL,NULL,5,2),(8,'test','uri',NULL,NULL,7,2);
/*!40000 ALTER TABLE `project_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_permission_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_role_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_role_permission_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色权限对应表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_user_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (2,'admin','$2a$10$uvfq7ZJbt8UoiIUCk3OkMe9Kv41P4IxyCo9VYM.qQk7kjgQcACiIa');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_ext`
--

DROP TABLE IF EXISTS `sys_user_ext`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_ext` (
  `uid` int(11) NOT NULL COMMENT '对应的sys_user表中用户id',
  `login_name` varchar(200) NOT NULL,
  `username` varchar(200) NOT NULL,
  `group_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属群组id，default 0',
  `group_admin` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否该组管理员，default 0 不是，1 是',
  `create_by` int(11) NOT NULL COMMENT '信息创建人',
  `create_time` datetime NOT NULL COMMENT '信息创建时间',
  `update_by` int(11) NOT NULL COMMENT '信息修改人',
  `update_time` datetime NOT NULL COMMENT '信息修改时间',
  `login_time` datetime DEFAULT NULL COMMENT '最近登录时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户信息扩展表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_ext`
--

LOCK TABLES `sys_user_ext` WRITE;
/*!40000 ALTER TABLE `sys_user_ext` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_ext` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_user_role_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色对应表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-08  0:08:04
