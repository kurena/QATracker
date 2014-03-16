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
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue` (
  `idissue` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `issuecol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idissue`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issuetask`
--

DROP TABLE IF EXISTS `issuetask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issuetask` (
  `idissuetask` int(11) NOT NULL AUTO_INCREMENT,
  `idissue` int(11) NOT NULL,
  `idtask` int(11) NOT NULL,
  PRIMARY KEY (`idissuetask`),
  KEY `fk_issuetask_1_idx` (`idtask`),
  KEY `fk_issuetask_2_idx` (`idissue`),
  CONSTRAINT `fk_issuetask_1` FOREIGN KEY (`idtask`) REFERENCES `task` (`idtask`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_issuetask_2` FOREIGN KEY (`idissue`) REFERENCES `issue` (`idissue`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issuetask`
--

LOCK TABLES `issuetask` WRITE;
/*!40000 ALTER TABLE `issuetask` DISABLE KEYS */;
/*!40000 ALTER TABLE `issuetask` ENABLE KEYS */;
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
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idproyect`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyect`
--

LOCK TABLES `proyect` WRITE;
/*!40000 ALTER TABLE `proyect` DISABLE KEYS */;
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
  `description` varchar(45) DEFAULT NULL,
  `image` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taskproyect`
--

DROP TABLE IF EXISTS `taskproyect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taskproyect` (
  `idtaskproyect` int(11) NOT NULL AUTO_INCREMENT,
  `idproyect` int(11) NOT NULL,
  `idtask` int(11) NOT NULL,
  PRIMARY KEY (`idtaskproyect`),
  KEY `fk_taskproyect_1_idx` (`idproyect`),
  KEY `fk_taskproyect_2_idx` (`idtask`),
  CONSTRAINT `fk_taskproyect_1` FOREIGN KEY (`idproyect`) REFERENCES `proyect` (`idproyect`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_taskproyect_2` FOREIGN KEY (`idtask`) REFERENCES `task` (`idtask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taskproyect`
--

LOCK TABLES `taskproyect` WRITE;
/*!40000 ALTER TABLE `taskproyect` DISABLE KEYS */;
/*!40000 ALTER TABLE `taskproyect` ENABLE KEYS */;
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
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'kurena','Kevin','admin123','19',NULL),(2,'sdf','sdf','123','12',NULL),(3,'asd','asd','123','12',NULL),(4,'kaut94','kaut94','123','12',NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userproyect`
--

LOCK TABLES `userproyect` WRITE;
/*!40000 ALTER TABLE `userproyect` DISABLE KEYS */;
/*!40000 ALTER TABLE `userproyect` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-16 16:02:57
