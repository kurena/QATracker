CREATE DATABASE  IF NOT EXISTS `qa_tracker` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `qa_tracker`;
-- MySQL dump 10.13  Distrib 5.5.35, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: qa_tracker
-- ------------------------------------------------------
-- Server version	5.5.35-0ubuntu0.13.10.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `idcomment` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(500) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  `idIssue` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`idcomment`),
  KEY `fk_comment_1_idx` (`idIssue`),
  KEY `fk_comment_2_idx` (`idUser`),
  CONSTRAINT `fk_comment_1` FOREIGN KEY (`idIssue`) REFERENCES `issue` (`idissue`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_comment_2` FOREIGN KEY (`idUser`) REFERENCES `user` (`iduser`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue` (
  `idissue` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `idUserCreador` int(11) DEFAULT NULL,
  `idUserAsignar` int(11) DEFAULT NULL,
  `path` varchar(250) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `priority` varchar(45) DEFAULT NULL,
  `idTask` int(11) DEFAULT NULL,
  PRIMARY KEY (`idissue`),
  KEY `iduser_idx` (`idUserCreador`,`idUserAsignar`),
  KEY `fk_userids_idx` (`idUserAsignar`),
  KEY `fk_issue_1_idx` (`idTask`),
  CONSTRAINT `fk_issue_1` FOREIGN KEY (`idTask`) REFERENCES `task` (`idtask`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_userid` FOREIGN KEY (`idUserCreador`) REFERENCES `user` (`iduser`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_userids` FOREIGN KEY (`idUserAsignar`) REFERENCES `user` (`iduser`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (19,'asdfdsfsdfasdfasdfasdf','ghjghj',8,8,'','Abierto','Alta',11);
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyect`
--

DROP TABLE IF EXISTS `proyect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proyect` (
  `idproyect` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `idCreatorUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`idproyect`),
  KEY `fk_proyect_1_idx` (`idCreatorUser`),
  CONSTRAINT `fk_proyect_1` FOREIGN KEY (`idCreatorUser`) REFERENCES `user` (`iduser`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyect`
--

LOCK TABLES `proyect` WRITE;
/*!40000 ALTER TABLE `proyect` DISABLE KEYS */;
INSERT INTO `proyect` VALUES (3,'Testing1','Just a test',8);
/*!40000 ALTER TABLE `proyect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `idtask` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `idProyect` int(11) DEFAULT NULL,
  `idCreatorUser` int(11) DEFAULT NULL,
  `idUserAssign` int(11) DEFAULT NULL,
  PRIMARY KEY (`idtask`),
  KEY `fk_task_1_idx` (`idProyect`),
  KEY `fk_task_2_idx` (`idCreatorUser`),
  KEY `fk_task_3_idx` (`idUserAssign`),
  CONSTRAINT `fk_task_1` FOREIGN KEY (`idProyect`) REFERENCES `proyect` (`idproyect`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_task_2` FOREIGN KEY (`idCreatorUser`) REFERENCES `user` (`iduser`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_task_3` FOREIGN KEY (`idUserAssign`) REFERENCES `user` (`iduser`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (11,'rtyrtyr','tyrtyrtyrty','/home/kevin/NetBeansProjects/QATracker/QATracker\\src\\com\\uia\\is12\\Images\\images\\Screenshot from 2014-03-09 19:05:15.png','Abierto',3,8,8);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'kevinu94','Kevin','admin123','19','desarrollador',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userproyect`
--

DROP TABLE IF EXISTS `userproyect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userproyect` (
  `iduserproyect` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) DEFAULT NULL,
  `idproyect` int(11) DEFAULT NULL,
  PRIMARY KEY (`iduserproyect`),
  KEY `fk_proyectStructure_1_idx` (`iduser`),
  KEY `fk_proyectStructure_2_idx` (`idproyect`),
  CONSTRAINT `fk_userproyect_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_userproyect_2` FOREIGN KEY (`idproyect`) REFERENCES `proyect` (`idproyect`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userproyect`
--

LOCK TABLES `userproyect` WRITE;
/*!40000 ALTER TABLE `userproyect` DISABLE KEYS */;
INSERT INTO `userproyect` VALUES (42,8,3);
/*!40000 ALTER TABLE `userproyect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watch`
--

DROP TABLE IF EXISTS `watch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `watch` (
  `idwatch` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) DEFAULT NULL,
  `idIssue` int(11) DEFAULT NULL,
  PRIMARY KEY (`idwatch`),
  KEY `fk_watch_1_idx` (`idIssue`),
  KEY `fk_watch_2_idx` (`idUser`),
  CONSTRAINT `fk_watch_1` FOREIGN KEY (`idIssue`) REFERENCES `issue` (`idissue`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_watch_2` FOREIGN KEY (`idUser`) REFERENCES `user` (`iduser`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `watch`
--

LOCK TABLES `watch` WRITE;
/*!40000 ALTER TABLE `watch` DISABLE KEYS */;
/*!40000 ALTER TABLE `watch` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-13 22:57:57
