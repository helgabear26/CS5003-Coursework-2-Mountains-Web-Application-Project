-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: MountainsWebAppDB
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
-- Table structure for table `activity_price`
--

DROP TABLE IF EXISTS `activity_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_price` (
  `priceID` int NOT NULL AUTO_INCREMENT,
  `priceAmount` decimal(10,2) NOT NULL,
  `activityID` int NOT NULL,
  `priceType` varchar(255) NOT NULL,
  `currency` varchar(10) NOT NULL,
  PRIMARY KEY (`priceID`),
  KEY `activityID` (`activityID`),
  CONSTRAINT `activity_price_ibfk_1` FOREIGN KEY (`activityID`) REFERENCES `mountain_activities` (`activityID`)
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
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`adminID`),
  UNIQUE KEY `email` (`email`)
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
-- Table structure for table `booking_information`
--

DROP TABLE IF EXISTS `booking_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_information` (
  `bookingInformationID` int NOT NULL AUTO_INCREMENT,
  `accommodationName` varchar(255) NOT NULL,
  `checkInDate` date NOT NULL,
  `checkOutDate` date NOT NULL,
  `totalPrice` decimal(10,2) NOT NULL,
  `bookingStatus` varchar(255) NOT NULL,
  `noOfGuests` int NOT NULL,
  `bookingID` int NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`bookingInformationID`),
  KEY `bookingID` (`bookingID`),
  KEY `userID` (`userID`),
  CONSTRAINT `booking_information_ibfk_1` FOREIGN KEY (`bookingID`) REFERENCES `booking_system` (`bookingID`),
  CONSTRAINT `booking_information_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_information`
--

LOCK TABLES `booking_information` WRITE;
/*!40000 ALTER TABLE `booking_information` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_system`
--

DROP TABLE IF EXISTS `booking_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_system` (
  `bookingID` int NOT NULL AUTO_INCREMENT,
  `accommodationName` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `coordinates` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phoneNumber` varchar(20) NOT NULL,
  `availability` varchar(255) NOT NULL,
  `dates_booked` varchar(255) NOT NULL,
  `mountainID` int NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`bookingID`),
  KEY `mountainID` (`mountainID`),
  KEY `userID` (`userID`),
  CONSTRAINT `booking_system_ibfk_1` FOREIGN KEY (`mountainID`) REFERENCES `mountains` (`mountainID`),
  CONSTRAINT `booking_system_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_system`
--

LOCK TABLES `booking_system` WRITE;
/*!40000 ALTER TABLE `booking_system` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking_system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mountain_activities`
--

DROP TABLE IF EXISTS `mountain_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mountain_activities` (
  `activityID` int NOT NULL AUTO_INCREMENT,
  `mountainID` int NOT NULL,
  `activityName` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `seasonStart` date NOT NULL,
  `seasonEnd` date NOT NULL,
  PRIMARY KEY (`activityID`),
  KEY `mountainID` (`mountainID`),
  CONSTRAINT `mountain_activities_ibfk_1` FOREIGN KEY (`mountainID`) REFERENCES `mountains` (`mountainID`)
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
  `name` varchar(255) NOT NULL,
  `mountainRange` varchar(255) NOT NULL,
  `countryRegion` varchar(255) NOT NULL,
  `coordinates` varchar(255) NOT NULL,
  `difficultyRating` int NOT NULL,
  `elevation` int NOT NULL,
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
-- Table structure for table `review_system`
--

DROP TABLE IF EXISTS `review_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_system` (
  `reviewID` int NOT NULL AUTO_INCREMENT,
  `comments_description` varchar(255) NOT NULL,
  `bookingID` int NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`reviewID`),
  KEY `bookingID` (`bookingID`),
  KEY `userID` (`userID`),
  CONSTRAINT `review_system_ibfk_1` FOREIGN KEY (`bookingID`) REFERENCES `booking_system` (`bookingID`),
  CONSTRAINT `review_system_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_system`
--

LOCK TABLES `review_system` WRITE;
/*!40000 ALTER TABLE `review_system` DISABLE KEYS */;
/*!40000 ALTER TABLE `review_system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saved_preferences`
--

DROP TABLE IF EXISTS `saved_preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saved_preferences` (
  `savedItemID` int NOT NULL AUTO_INCREMENT,
  `itemType` varchar(255) NOT NULL,
  `itemID` int NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`savedItemID`),
  KEY `userID` (`userID`),
  CONSTRAINT `saved_preferences_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
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
  PRIMARY KEY (`userID`),
  UNIQUE KEY `email` (`email`)
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

-- Dump completed on 2026-03-26 13:39:32
