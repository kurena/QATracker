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
  CONSTRAINT `fk_comment_1` FOREIGN KEY (`idIssue`) REFERENCES `issue` (`idissue`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_2` FOREIGN KEY (`idUser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'iniciemos el proceso',7,15,'2014-04-05'),(2,'tome tome\n',7,15,'2014-04-05'),(3,'empezemos arreglando esto por favor commitear sus cambios\n',7,15,'2014-04-05'),(4,'404',7,12,'2014-04-06');
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
  PRIMARY KEY (`idissue`),
  KEY `iduser_idx` (`idUserCreador`,`idUserAsignar`),
  KEY `fk_userids_idx` (`idUserAsignar`),
  CONSTRAINT `fk_userid` FOREIGN KEY (`idUserCreador`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_userids` FOREIGN KEY (`idUserAsignar`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,'Agregar la BD','Arreglar ese error',1,2,NULL,NULL,NULL),(3,'revisar','revisar',1,3,NULL,NULL,NULL),(4,'Testing','testing',1,1,NULL,NULL,NULL),(5,'tesing123','tesignfa',1,1,NULL,NULL,NULL),(6,'testing','testg',1,3,'',NULL,NULL),(11,'testing','tetgsda',1,5,'C:\\xampp\\htdocs\\proyectoProgra2\\QATracker\\QATracker\\src\\com\\uia\\is12\\Images\\images\\Captura.JPG',NULL,NULL),(12,'Problema en la Base de Datos','Mi base de datos esta teniendo un severo ',1,6,'C:\\xampp\\htdocs\\proyectoProgra2\\QATracker\\QATracker\\src\\com\\uia\\is12\\Images\\images\\Captura.JPG',NULL,NULL),(13,'ArregleloRAIAMsitodsadadds','darasdsa',1,6,'C:\\xampp\\htdocs\\proyectoProgra2\\QATracker\\QATracker\\src\\com\\uia\\is12\\Images\\images\\Captura.JPG',NULL,NULL),(14,'testing','tresafdasdas',1,1,'',NULL,NULL),(15,'Estas mamando mopri','Porfavor arreglar todo',7,2,'',NULL,NULL);
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
  CONSTRAINT `fk_proyect_1` FOREIGN KEY (`idCreatorUser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyect`
--

LOCK TABLES `proyect` WRITE;
/*!40000 ALTER TABLE `proyect` DISABLE KEYS */;
INSERT INTO `proyect` VALUES (1,'Wemo App','Im just testing',7),(2,'Fridom','Nice Site888',7);
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
  CONSTRAINT `fk_task_1` FOREIGN KEY (`idProyect`) REFERENCES `proyect` (`idproyect`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_2` FOREIGN KEY (`idCreatorUser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_3` FOREIGN KEY (`idUserAssign`) REFERENCES `proyect` (`idproyect`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'Agregar Imagenes','Por favor agregar las imagenes correspondientes lo mas pronto posible.','/home/kevin/NetBeansProjects/QATracker/QATracker\\src\\com\\uia\\is12\\Images\\images\\Screenshot from 2014-03-09 19:05:15.png',NULL,2,NULL,NULL),(5,'Agregar estilos','POr favor agregar los estilos correspondientes.','/home/kevin/NetBeansProjects/QATracker/QATracker\\src\\com\\uia\\is12\\Images\\images\\Screenshot from 2014-03-09 19:05:15.png',NULL,2,NULL,NULL),(6,'tome mop','jajajaa','/home/kevin/NetBeansProjects/QATracker/QATracker\\src\\com\\uia\\is12\\Images\\images\\Screenshot from 2014-03-09 19:05:15.png',NULL,2,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'kurena','Kevin','admin123','19',NULL,NULL),(2,'sdf','sdf','123','12',NULL,NULL),(3,'asd','asd','123','12',NULL,NULL),(4,'kaut94','kaut94','123','12',NULL,NULL),(5,'rquesada','Raiam','admin123','19','desarrollador','Quesada'),(6,'test','Raiam','test','12','desarrollador','Quesada'),(7,'kevinu94','Kevin','admin123','19','desarrollador','Test2');
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
  `iduser` int(11) NOT NULL,
  `idproyect` int(11) NOT NULL,
  PRIMARY KEY (`iduserproyect`),
  KEY `fk_proyectStructure_1_idx` (`iduser`),
  KEY `fk_proyectStructure_2_idx` (`idproyect`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userproyect`
--

LOCK TABLES `userproyect` WRITE;
/*!40000 ALTER TABLE `userproyect` DISABLE KEYS */;
INSERT INTO `userproyect` VALUES (1,1,1),(2,3,1),(3,5,1),(4,7,1),(41,5,2);
/*!40000 ALTER TABLE `userproyect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watch`
--

DROP TABLE IF EXISTS `watch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `watch` (
  `idwatch` int(11) NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  `idIssue` int(11) DEFAULT NULL,
  PRIMARY KEY (`idwatch`),
  KEY `fk_watch_1_idx` (`idUser`),
  KEY `fk_watch_2_idx` (`idIssue`),
  CONSTRAINT `fk_watch_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_watch_2` FOREIGN KEY (`idIssue`) REFERENCES `issue` (`idissue`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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

-- Dump completed on 2014-04-06 17:20:40
