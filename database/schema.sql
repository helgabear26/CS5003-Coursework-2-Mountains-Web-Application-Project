-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mountainswebappdb
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accommodation`
--

DROP TABLE IF EXISTS `accommodation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accommodation` (
  `accommodationID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `coordinates` varchar(255) NOT NULL,
  `mountainID` int NOT NULL,
  PRIMARY KEY (`accommodationID`),
  KEY `FK_accommodation_mountain` (`mountainID`),
  CONSTRAINT `FK_accommodation_mountain` FOREIGN KEY (`mountainID`) REFERENCES `mountains` (`mountainID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accommodation`
--

LOCK TABLES `accommodation` WRITE;
/*!40000 ALTER TABLE `accommodation` DISABLE KEYS */;
/*!40000 ALTER TABLE `accommodation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_price`
--

DROP TABLE IF EXISTS `activity_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_price` (
  `priceID` int NOT NULL AUTO_INCREMENT,
  `currency` varchar(10) NOT NULL,
  `priceAmount` decimal(10,2) NOT NULL,
  `priceType` varchar(255) NOT NULL,
  `activityID` int NOT NULL,
  PRIMARY KEY (`priceID`),
  KEY `FK_activity_price_activityID` (`activityID`),
  CONSTRAINT `FK_activity_price_activityID` FOREIGN KEY (`activityID`) REFERENCES `mountain_activities` (`activityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_price`
--

LOCK TABLES `activity_price` WRITE;
/*!40000 ALTER TABLE `activity_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `bookingID` int NOT NULL AUTO_INCREMENT,
  `accommodationID` int NOT NULL,
  `userID` int NOT NULL,
  `datesBooked` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `phoneNo` varchar(20) NOT NULL,
  PRIMARY KEY (`bookingID`),
  KEY `FK_booking_accommodation` (`accommodationID`),
  KEY `FK_booking_user` (`userID`),
  CONSTRAINT `FK_booking_accommodation` FOREIGN KEY (`accommodationID`) REFERENCES `accommodation` (`accommodationID`),
  CONSTRAINT `FK_booking_user` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mountain_activities`
--

DROP TABLE IF EXISTS `mountain_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mountain_activities` (
  `activityID` int NOT NULL AUTO_INCREMENT,
  `activityName` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `seasonEnd` date NOT NULL,
  `seasonStart` date NOT NULL,
  `mountainID` int NOT NULL,
  PRIMARY KEY (`activityID`),
  KEY `FK_mountain_activities_mountainID` (`mountainID`),
  CONSTRAINT `FK_mountain_activities_mountainID` FOREIGN KEY (`mountainID`) REFERENCES `mountains` (`mountainID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mountain_activities`
--

LOCK TABLES `mountain_activities` WRITE;
/*!40000 ALTER TABLE `mountain_activities` DISABLE KEYS */;
INSERT INTO `mountain_activities` (`activityID`, `activityName`, `category`, `description`, `seasonEnd`, `seasonStart`, `mountainID`) VALUES (10,'Summit Climb','Climbing','Extreme high altitude summit expedition','2026-04-01','2026-05-31',1),(11,'Ice Climbing','Climbing','Technical ice climbing on steep faces','2026-11-01','2027-02-28',2),(12,'Trekking','Intermmediate Hiking','Multy-day trek through mountain trails','2026-09-01','2026-11-30',3),(13,'Glacier Traverse','Climbing','Crosssing glaciers with crevasse navigation','2026-06-01','2026-07-31',4),(14,'High Altitude Expedition','Climbing','Challenging expedition including acclimatization','2026-06-01','2026-08-31',5),(15,'Base Camp Trek','Advanced Hiking','Popular trek to the base camp of a mountain','2026-10-01','2026-11-30',6),(16,'Scenic Hike','Leisure','Scenic hike focused on photography','2026-09-01','2026-10-31',7),(17,'Hiking','Beginner Hiking','Non-technical climb suitable for beginners','2026-06-01','2026-10-31',8),(18,'Ski Mountaineering','Skiing','Climb and ski descent','2026-01-01','2026-04-05',9),(19,'Alpine Climb','Climbing','technical rock climbing routes','2026-07-01','2026-09-30',10),(20,'Rock Climbing','Climbing','Range of beginner to advanced rock climbing routes','2026-06-01','2026-09-30',11),(21,'Day Hiking','Hiking','Short scenic hikes','2026-05-01','2026-09-30',12),(22,'Snow Hiking','Hiking','Guided snow Hikes in alpine terrain','2026-12-01','2027-03-31',13),(23,'Summit Tour','Climbing','Gided summit ascent','2026-06-01','2026-09-30',14);
/*!40000 ALTER TABLE `mountain_activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mountains`
--

DROP TABLE IF EXISTS `mountains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mountains` (
  `mountainID` int NOT NULL AUTO_INCREMENT,
  `coordinates` varchar(255) NOT NULL,
  `countryRegion` varchar(255) NOT NULL,
  `difficultyRating` int NOT NULL,
  `elevation` int NOT NULL,
  `mountainRange` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`mountainID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mountains`
--

LOCK TABLES `mountains` WRITE;
/*!40000 ALTER TABLE `mountains` DISABLE KEYS */;
INSERT INTO `mountains` (`mountainID`, `coordinates`, `countryRegion`, `difficultyRating`, `elevation`, `mountainRange`, `name`) VALUES (1,'27.9882, 86.9254','Nepal/China',5,8849,'Himalayas','Mount Everest'),(2,'35.8800, 76.5151','Pakistan/China',5,8611,'Karakoram','K2'),(3,'28.6136, 83.8736','Nepal',5,8091,'Himalayas','Annapurna'),(4,'35.7244, 76.6964','Pakistan/China',5,8080,'Karakoram','Gasherbrum I'),(5,'35.7583, 76.6533','Pakistan/China',5,8035,'Karakoram','Gasherbrum II'),(6,'28.5350, 84.1225','Nepal',4,7937,'Himalayas','Annapurna II'),(7,'28.5850, 83.9908','Nepal',4,7555,'Himalayas','Annapurna III'),(8,'3.0674, 37.3556','Tanzania',3,5895,'Eastern Rift Mountains','Mount Kilimanjaro'),(9,'43.3499, 42.4453','Russia',4,5642,'Caucus Mountains','Mount Elbrus'),(10,'45.8326, 6.8652','France/Italy',3,4810,'Alps','Mount Blanc'),(11,'45.9766, 7.6585','Switzerland/Italy',3,4478,'Alps','Matterhorn'),(12,'37.9228, 107.4256','USA',2,4268,'San Juan Mountains Colorado','Sunshine Peak'),(13,'7.9626, 46.5368','Switzerland',2,4158,'Bernese Alps','Jungfrau'),(14,'47.0742, 12.6947','Austria',2,3798,'Alps','Grossglockner');
/*!40000 ALTER TABLE `mountains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saved_preferences`
--

DROP TABLE IF EXISTS `saved_preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saved_preferences` (
  `savedItemID` int NOT NULL AUTO_INCREMENT,
  `itemID` int NOT NULL,
  `itemType` varchar(255) NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`savedItemID`),
  KEY `FK_saved_preferences_userID` (`userID`),
  CONSTRAINT `FK_saved_preferences_userID` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saved_preferences`
--

LOCK TABLES `saved_preferences` WRITE;
/*!40000 ALTER TABLE `saved_preferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `saved_preferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-16 19:38:39
