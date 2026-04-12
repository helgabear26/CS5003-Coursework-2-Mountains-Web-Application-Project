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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `adminID` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  PRIMARY KEY (`adminID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mountain_activities`
--

LOCK TABLES `mountain_activities` WRITE;
/*!40000 ALTER TABLE `mountain_activities` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mountains`
--

LOCK TABLES `mountains` WRITE;
/*!40000 ALTER TABLE `mountains` DISABLE KEYS */;
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

-- Dump completed on 2026-04-12  9:03:14
