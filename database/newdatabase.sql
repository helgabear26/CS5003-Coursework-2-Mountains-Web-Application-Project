-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: mountainswebappdb
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accommodation`
--

LOCK TABLES `accommodation` WRITE;
/*!40000 ALTER TABLE `accommodation` DISABLE KEYS */;
INSERT INTO `accommodation` VALUES (1,'Everest Base Camp Lodge','Basic high-altitude lodge for trekkers heading to Everest Base Camp.','28.0026, 86.8528',1),(2,'K2 Base Camp Shelter','Remote and rugged shelter used by climbers preparing for K2 expeditions.','35.8808, 76.5133',2),(3,'Annapurna Sanctuary Lodge','Scenic lodge surrounded by Annapurna peaks, popular with trekkers.','28.5300, 83.8800',3),(4,'Gasherbrum I Camp Lodge','Minimalist accommodation for experienced climbers in extreme conditions.','35.7255, 76.6968',4),(5,'Gasherbrum II Rest Camp','Temporary rest stop with essential facilities for climbers.','35.7590, 76.6540',5),(6,'Annapurna II Base Lodge','Comfortable base lodge with panoramic Himalayan views.','28.5355, 84.1230',6),(7,'Annapurna III Trekker Hut','Simple hut catering to trekking groups and guides.','28.5860, 83.9915',7),(8,'Kilimanjaro Summit Camp','High-altitude camp near Uhuru Peak for summit attempts.','3.0678, 37.3560',8),(9,'Elbrus Mountain Lodge','Well-equipped lodge with heating and supplies for climbers.','43.3505, 42.4458',9),(10,'Mont Blanc Alpine Hut','Classic alpine hut offering stunning glacier views.','45.8330, 6.8658',10),(11,'Matterhorn Base Lodge','Charming Swiss lodge with direct access to climbing routes.','45.9770, 7.6590',11),(12,'Sunshine Peak Cabin','Small wooden cabin in the San Juan Mountains region.','37.9232, 107.4260',12),(13,'Jungfrau Ice Lodge','Unique lodge located near glacial areas in the Bernese Alps.','46.5470, 7.9850',3),(14,'Grossglockner Alpine Retreat','Modern alpine retreat with scenic Austrian mountain views.','47.0748, 12.6955',14),(15,'Dhaulagiri Base Camp Lodge','Remote lodge for trekkers exploring the Dhaulagiri circuit.','28.6960, 83.4870',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_price`
--

LOCK TABLES `activity_price` WRITE;
/*!40000 ALTER TABLE `activity_price` DISABLE KEYS */;
INSERT INTO `activity_price` VALUES (1,'GBP',120.00,'per_person',1),(2,'GBP',950.00,'package',1),(3,'GBP',80.00,'per_person',2),(4,'GBP',650.00,'package',2),(5,'GBP',110.00,'per_day',3),(6,'GBP',700.00,'package',3),(7,'GBP',95.00,'per_day',4),(8,'GBP',800.00,'package',4),(9,'GBP',60.00,'per_person',5),(10,'GBP',400.00,'package',5),(11,'GBP',105.00,'per_person',6),(12,'GBP',780.00,'package',6),(13,'GBP',60.00,'per_person',7),(14,'GBP',400.00,'package',7),(15,'GBP',200.00,'per_day',8),(16,'GBP',1200.00,'package',8),(17,'GBP',160.00,'per_person',9),(18,'GBP',1100.00,'package',9),(19,'GBP',90.00,'per_person',10),(20,'GBP',700.00,'package',10);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,1,1,'2026-06-20','dwecdce@gmail.com','09380302');
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
INSERT INTO `mountain_activities` VALUES (10,'Summit Climb','Climbing','Extreme high altitude summit expedition','2026-04-01','2026-05-31',1),(11,'Ice Climbing','Climbing','Technical ice climbing on steep faces','2026-11-01','2027-02-28',2),(12,'Trekking','Intermediate Hiking','Multi-day trek through mountain trails','2026-09-01','2026-11-30',3),(13,'Glacier Traverse','Climbing','Crossing glaciers with crevasse navigation','2026-06-01','2026-07-31',4),(14,'High Altitude Expedition','Climbing','Challenging expedition including acclimatization','2026-06-01','2026-08-31',5),(15,'Base Camp Trek','Advanced Hiking','Popular trek to the base camp of a mountain','2026-10-01','2026-11-30',6),(16,'Scenic Hike','Leisure','Scenic hike focused on photography','2026-09-01','2026-10-31',7),(17,'Hiking','Beginner Hiking','Non-technical climb suitable for beginners','2026-06-01','2026-10-31',8),(18,'Ski Mountaineering','Skiing','Climb and ski descent','2026-01-01','2026-04-05',9),(19,'Alpine Climb','Climbing','technical rock climbing routes','2026-07-01','2026-09-30',10),(20,'Rock Climbing','Climbing','Range of beginner to advanced rock climbing routes','2026-06-01','2026-09-30',11),(21,'Day Hiking','Hiking','Short scenic hikes','2026-05-01','2026-09-30',12),(22,'Snow Hiking','Hiking','Guided snow Hikes in alpine terrain','2026-12-01','2027-03-31',13),(23,'Summit Tour','Climbing','Guided summit ascent','2026-06-01','2026-09-30',14);
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
INSERT INTO `mountains` VALUES (1,'27.9882, 86.9254','Nepal/China',5,8849,'Himalayas','Mount Everest'),(2,'35.8800, 76.5151','Pakistan/China',5,8611,'Karakoram','K2'),(3,'28.6136, 83.8736','Nepal',5,8091,'Himalayas','Annapurna'),(4,'35.7244, 76.6964','Pakistan/China',5,8080,'Karakoram','Gasherbrum I'),(5,'35.7583, 76.6533','Pakistan/China',5,8035,'Karakoram','Gasherbrum II'),(6,'28.5350, 84.1225','Nepal',4,7937,'Himalayas','Annapurna II'),(7,'28.5850, 83.9908','Nepal',4,7555,'Himalayas','Annapurna III'),(8,'3.0674, 37.3556','Tanzania',3,5895,'Eastern Rift Mountains','Mount Kilimanjaro'),(9,'43.3499, 42.4453','Russia',4,5642,'Caucus Mountains','Mount Elbrus'),(10,'45.8326, 6.8652','France/Italy',3,4810,'Alps','Mount Blanc'),(11,'45.9766, 7.6585','Switzerland/Italy',3,4478,'Alps','Matterhorn'),(12,'37.9228, 107.4256','USA',2,4268,'San Juan Mountains Colorado','Sunshine Peak'),(13,'7.9626, 46.5368','Switzerland',2,4158,'Bernese Alps','Jungfrau'),(14,'47.0742, 12.6947','Austria',2,3798,'Alps','Grossglockner');
/*!40000 ALTER TABLE `mountains` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'ben','fishman','Chm@gmail.com','$2a$10$iLn.fOR6ZvxgbixsnnLSRu5YRWIG/pn7zdgNmxJvKN/S3QFFwEc.m','benfish');
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

-- Dump completed on 2026-04-20 18:19:40
