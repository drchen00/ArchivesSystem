-- MySQL dump 10.13  Distrib 5.7.12, for Linux (x86_64)
--
-- Host: localhost    Database: archivesSystem
-- ------------------------------------------------------
-- Server version	5.7.12-0ubuntu1.1

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
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account` (
  `ID` int(11) NOT NULL,
  `Account` char(45) NOT NULL,
  `Password` char(45) NOT NULL,
  `Name` char(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Account_Account_uindex` (`Account`),
  UNIQUE KEY `Account_ID_uindex` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ActionDict`
--

DROP TABLE IF EXISTS `ActionDict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ActionDict` (
  `ID` int(11) NOT NULL,
  `Name` char(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Status_ID_uindex` (`ID`),
  UNIQUE KEY `Status_Status_uindex` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ActionDict`
--

LOCK TABLES `ActionDict` WRITE;
/*!40000 ALTER TABLE `ActionDict` DISABLE KEYS */;
INSERT INTO `ActionDict` VALUES (1,'借出'),(0,'入库'),(2,'归还'),(3,'销毁');
/*!40000 ALTER TABLE `ActionDict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ArchiveTrace`
--

DROP TABLE IF EXISTS `ArchiveTrace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ArchiveTrace` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ArchiveNum` int(11) NOT NULL,
  `Action` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `UserID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ArchiveTrace_ID_uindex` (`ID`),
  KEY `ArchiveTrace_ActionDict_ID_fk` (`Action`),
  KEY `ArchiveTrace_Archives_ArchiveNum_fk` (`ArchiveNum`),
  KEY `ArchiveTrace_Account_ID_fk` (`UserID`),
  CONSTRAINT `ArchiveTrace_Account_ID_fk` FOREIGN KEY (`UserID`) REFERENCES `Account` (`ID`),
  CONSTRAINT `ArchiveTrace_ActionDict_ID_fk` FOREIGN KEY (`Action`) REFERENCES `ActionDict` (`ID`),
  CONSTRAINT `ArchiveTrace_Archives_ArchiveNum_fk` FOREIGN KEY (`ArchiveNum`) REFERENCES `Archives` (`ArchiveNum`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ArchiveTrace`
--

LOCK TABLES `ArchiveTrace` WRITE;
/*!40000 ALTER TABLE `ArchiveTrace` DISABLE KEYS */;
/*!40000 ALTER TABLE `ArchiveTrace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ArchiveTraceHistory`
--

DROP TABLE IF EXISTS `ArchiveTraceHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ArchiveTraceHistory` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ArchiveNum` int(11) NOT NULL,
  `Action` int(11) NOT NULL,
  `Time` datetime NOT NULL,
  `UserID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ArchiveTraceHistory_ID_uindex` (`ID`),
  KEY `ArchiveTraceHistory_ArchivesHistory_ArchiveNum_fk` (`ArchiveNum`),
  KEY `ArchiveTraceHistory_ActionDict_ID_fk` (`Action`),
  KEY `ArchiveTraceHistory_Account_ID_fk` (`UserID`),
  CONSTRAINT `ArchiveTraceHistory_Account_ID_fk` FOREIGN KEY (`UserID`) REFERENCES `Account` (`ID`),
  CONSTRAINT `ArchiveTraceHistory_ActionDict_ID_fk` FOREIGN KEY (`Action`) REFERENCES `ActionDict` (`ID`),
  CONSTRAINT `ArchiveTraceHistory_ArchivesHistory_ArchiveNum_fk` FOREIGN KEY (`ArchiveNum`) REFERENCES `ArchivesHistory` (`ArchiveNum`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ArchiveTraceHistory`
--

LOCK TABLES `ArchiveTraceHistory` WRITE;
/*!40000 ALTER TABLE `ArchiveTraceHistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `ArchiveTraceHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Archives`
--

DROP TABLE IF EXISTS `Archives`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Archives` (
  `ArchiveNum` int(11) NOT NULL,
  `Name` char(45) NOT NULL,
  `TagNum` char(45) NOT NULL,
  `Status` int(11) NOT NULL,
  `CreatedTime` datetime NOT NULL,
  PRIMARY KEY (`ArchiveNum`),
  UNIQUE KEY `Archives_Num_uindex` (`ArchiveNum`),
  UNIQUE KEY `Archives_Name_uindex` (`Name`),
  UNIQUE KEY `Archives_TagNum_uindex` (`TagNum`),
  KEY `Archives_ArchivesStatusDict_ID_fk` (`Status`),
  CONSTRAINT `Archives_ArchivesStatusDict_ID_fk` FOREIGN KEY (`Status`) REFERENCES `ArchivesStatusDict` (`ID`),
  CONSTRAINT `Archives_Tags_TagNum_fk` FOREIGN KEY (`TagNum`) REFERENCES `Tags` (`TagNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Archives`
--

LOCK TABLES `Archives` WRITE;
/*!40000 ALTER TABLE `Archives` DISABLE KEYS */;
/*!40000 ALTER TABLE `Archives` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ArchivesHistory`
--

DROP TABLE IF EXISTS `ArchivesHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ArchivesHistory` (
  `ArchiveNum` int(11) NOT NULL,
  `Name` char(45) NOT NULL,
  PRIMARY KEY (`ArchiveNum`),
  UNIQUE KEY `ArchivesHistory_ArchiveNum_uindex` (`ArchiveNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ArchivesHistory`
--

LOCK TABLES `ArchivesHistory` WRITE;
/*!40000 ALTER TABLE `ArchivesHistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `ArchivesHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ArchivesStatusDict`
--

DROP TABLE IF EXISTS `ArchivesStatusDict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ArchivesStatusDict` (
  `ID` int(11) NOT NULL,
  `Name` char(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ArchivesStatusDict_ID_uindex` (`ID`),
  UNIQUE KEY `ArchivesStatusDict_Name_uindex` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ArchivesStatusDict`
--

LOCK TABLES `ArchivesStatusDict` WRITE;
/*!40000 ALTER TABLE `ArchivesStatusDict` DISABLE KEYS */;
INSERT INTO `ArchivesStatusDict` VALUES (1,'借出'),(0,'在库');
/*!40000 ALTER TABLE `ArchivesStatusDict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CheckConditionsDict`
--

DROP TABLE IF EXISTS `CheckConditionsDict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CheckConditionsDict` (
  `ID` int(11) NOT NULL,
  `Type` char(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CheckConditions_ID_uindex` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CheckConditionsDict`
--

LOCK TABLES `CheckConditionsDict` WRITE;
/*!40000 ALTER TABLE `CheckConditionsDict` DISABLE KEYS */;
INSERT INTO `CheckConditionsDict` VALUES (0,'按创建时间');
/*!40000 ALTER TABLE `CheckConditionsDict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CheckRecords`
--

DROP TABLE IF EXISTS `CheckRecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CheckRecords` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CheckCondition` int(11) NOT NULL,
  `CheckContent` char(128) NOT NULL,
  `UserID` int(11) NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CheckRecord_ID_uindex` (`ID`),
  KEY `CheckRecord_Account_ID_fk` (`UserID`),
  KEY `CheckRecord_CheckConditionsDict_ID_fk` (`CheckCondition`),
  CONSTRAINT `CheckRecord_Account_ID_fk` FOREIGN KEY (`UserID`) REFERENCES `Account` (`ID`),
  CONSTRAINT `CheckRecord_CheckConditionsDict_ID_fk` FOREIGN KEY (`CheckCondition`) REFERENCES `CheckConditionsDict` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CheckRecords`
--

LOCK TABLES `CheckRecords` WRITE;
/*!40000 ALTER TABLE `CheckRecords` DISABLE KEYS */;
/*!40000 ALTER TABLE `CheckRecords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CheckResults`
--

DROP TABLE IF EXISTS `CheckResults`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CheckResults` (
  `CheckID` int(11) NOT NULL,
  `TagNum` char(45) NOT NULL,
  `ExceptionInfo` int(11) NOT NULL,
  PRIMARY KEY (`CheckID`,`TagNum`),
  KEY `CheckResults_ExceptionInfoDict_ID_fk` (`ExceptionInfo`),
  KEY `CheckResults_Tags_TagNum_fk` (`TagNum`),
  CONSTRAINT `CheckResults_CheckRecord_ID_fk` FOREIGN KEY (`CheckID`) REFERENCES `CheckRecords` (`ID`),
  CONSTRAINT `CheckResults_ExceptionInfoDict_ID_fk` FOREIGN KEY (`ExceptionInfo`) REFERENCES `ExceptionInfoDict` (`ID`),
  CONSTRAINT `CheckResults_Tags_TagNum_fk` FOREIGN KEY (`TagNum`) REFERENCES `Tags` (`TagNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CheckResults`
--

LOCK TABLES `CheckResults` WRITE;
/*!40000 ALTER TABLE `CheckResults` DISABLE KEYS */;
/*!40000 ALTER TABLE `CheckResults` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ExceptionInfoDict`
--

DROP TABLE IF EXISTS `ExceptionInfoDict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ExceptionInfoDict` (
  `ID` int(11) NOT NULL,
  `Type` char(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ExceptionInfoDict_ID_uindex` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExceptionInfoDict`
--

LOCK TABLES `ExceptionInfoDict` WRITE;
/*!40000 ALTER TABLE `ExceptionInfoDict` DISABLE KEYS */;
INSERT INTO `ExceptionInfoDict` VALUES (0,'正常'),(1,'未盘点到权证');
/*!40000 ALTER TABLE `ExceptionInfoDict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tags`
--

DROP TABLE IF EXISTS `Tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tags` (
  `TagNum` char(45) NOT NULL,
  PRIMARY KEY (`TagNum`),
  UNIQUE KEY `Tag_Num_uindex` (`TagNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tags`
--

LOCK TABLES `Tags` WRITE;
/*!40000 ALTER TABLE `Tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `Tags` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-04 18:38:10
