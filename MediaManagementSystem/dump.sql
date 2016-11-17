-- MySQL dump 10.13  Distrib 5.7.16, for osx10.11 (x86_64)
--
-- Host: localhost    Database: MediaManagementDB
-- ------------------------------------------------------
-- Server version	5.7.16

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
-- Table structure for table `MEDIA`
--

DROP TABLE IF EXISTS `MEDIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MEDIA` (
  `mediaID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `type` varchar(128) NOT NULL,
  `image` varchar(512) DEFAULT NULL,
  `genre` varchar(128) NOT NULL,
  `price` int(10) unsigned NOT NULL,
  `sellPrice` int(10) DEFAULT NULL,
  `inventoryCount` int(10) unsigned NOT NULL,
  `isRentable` tinyint(1) NOT NULL,
  PRIMARY KEY (`mediaID`),
  UNIQUE KEY `MEDIA_id_uindex` (`mediaID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEDIA`
--

LOCK TABLES `MEDIA` WRITE;
/*!40000 ALTER TABLE `MEDIA` DISABLE KEYS */;
INSERT INTO `MEDIA` VALUES (1,'The Shawshank Redemption',NULL,'Movie','src/main/resources/movie/theshawshankredemption.jpg','Drama',5,3,2,0),(2,'The Godfather',NULL,'Movie','src/main/resources/movie/thegodfather.jpg','Crime',5,3,3,1),(3,'The Dark Knight',NULL,'Movie','src/main/resources/movie/thedarkknight.jpg','Action',7,3,1,0),(4,'Pulp Fiction',NULL,'Movie','src/main/resources/movie/pulpfiction.jpg','Drama',4,3,4,1),(5,'Fight Club',NULL,'Movie','src/main/resources/movie/fightclub.jpg','Thriller',5,3,2,1),(6,'Forrest Gump',NULL,'Movie','src/main/resources/movie/forrestgump.jpg','Comedy',3,3,3,0),(7,'Goodfellas',NULL,'Movie','src/main/resources/movie/goodfellas.jpg','Crime',6,3,4,1),(8,'Alexander Hamilton',NULL,'Book','src/main/resources/book/alexanderhamilton.jpg','Biography',14,3,6,0),(9,'Elon Musk',NULL,'Book','src/main/resources/book/elonmusk.jpg','Biography',10,3,3,0),(10,'Trump The Art of the Deal',NULL,'Book','src/main/resources/book/trumptheartofthedeal.jpg','Business',5,3,12,0),(11,'Harry Potter and the Cursed Child',NULL,'Book','src/main/resources/book/harrypotterandthecursedchild.jpg','Fiction',7,3,10,0),(12,'Blood Meridian',NULL,'Book ','src/main/resources/book/bloodmeridian.jpg','Fiction',8,3,9,0);
/*!40000 ALTER TABLE `MEDIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REVIEW`
--

DROP TABLE IF EXISTS `REVIEW`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REVIEW` (
  `reviewID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ratingValue` tinyint(1) NOT NULL,
  `reviewText` varchar(1024) NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `mediaID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`reviewID`),
  UNIQUE KEY `REVIEW_id_uindex` (`reviewID`),
  KEY `REVIEW_userID_fk` (`userID`),
  KEY `REVIEW_mediaID_fk` (`mediaID`),
  CONSTRAINT `REVIEW_mediaID_fk` FOREIGN KEY (`mediaID`) REFERENCES `MEDIA` (`mediaID`),
  CONSTRAINT `REVIEW_userID_fk` FOREIGN KEY (`userID`) REFERENCES `USER` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVIEW`
--

LOCK TABLES `REVIEW` WRITE;
/*!40000 ALTER TABLE `REVIEW` DISABLE KEYS */;
/*!40000 ALTER TABLE `REVIEW` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `userID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(70) NOT NULL,
  `password` varchar(512) NOT NULL,
  `email` varchar(70) NOT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `isAdmin` tinyint(1) NOT NULL,
  `accountBalance` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `USER_username_uindex` (`username`),
  UNIQUE KEY `USER_email_uindex` (`email`),
  UNIQUE KEY `USER_id_uindex` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (1,'admin','admin','tysc7237@colorado.edu','Admin',NULL,1,100),(2,'user','user','user@user.com','User','One',0,50);
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_MEDIA`
--

DROP TABLE IF EXISTS `USER_MEDIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_MEDIA` (
  `userMediaID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userID` int(10) unsigned NOT NULL,
  `mediaID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`userMediaID`),
  UNIQUE KEY `USER_MEDIA_userMediaID_uindex` (`userMediaID`),
  KEY `USER_MEDIA_user_fk` (`userID`),
  KEY `USER_MEDIA_media_fk` (`mediaID`),
  CONSTRAINT `USER_MEDIA_media_fk` FOREIGN KEY (`mediaID`) REFERENCES `MEDIA` (`mediaID`),
  CONSTRAINT `USER_MEDIA_user_fk` FOREIGN KEY (`userID`) REFERENCES `USER` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_MEDIA`
--

LOCK TABLES `USER_MEDIA` WRITE;
/*!40000 ALTER TABLE `USER_MEDIA` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER_MEDIA` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-17 10:28:26
