-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: gabs_usc
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `offices`
--

DROP TABLE IF EXISTS `offices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offices` (
  `office_ID` int NOT NULL AUTO_INCREMENT,
  `office_name` varchar(255) DEFAULT NULL,
  `building_code` char(2) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `hours` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`office_ID`)
<<<<<<< HEAD
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
=======
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
>>>>>>> bc4f5841a0e22f0ffc25bc0a0a3d457567ad6cc0
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offices`
--

LOCK TABLES `offices` WRITE;
/*!40000 ALTER TABLE `offices` DISABLE KEYS */;
INSERT INTO `offices` VALUES (1,'Cashier','LB','GF, Lobby','03:58 am - 09:58 pm'),(2,'Registrar\'s Office','LB','Beside car entrance','09:00 am - 05:00 pm'),(3,'DCISM Office','LB','4F by the 460s hallway','08:11 pm - 08:11 pm'),(4,'IE Department','LB','4F between 460s and 440s hall','08:11 pm - 08:11 pm');
/*!40000 ALTER TABLE `offices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routes` (
  `route_id` int NOT NULL AUTO_INCREMENT,
  `route_name` varchar(255) DEFAULT NULL,
  `traditionalJeep_Fare` double DEFAULT NULL,
  `modernJeep_Fare` double DEFAULT NULL,
  `jeepsToTake` varchar(255) DEFAULT NULL,
  `route_map` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`route_id`)
<<<<<<< HEAD
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
=======
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
>>>>>>> bc4f5841a0e22f0ffc25bc0a0a3d457567ad6cc0
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (1,'USC TC to Ayala',11,15,'13C Ayala direct','src/resources/routes/tcToAyala.png'),(8,'dumdum',11,15,'ur mom','src/resources/routes/ehe');
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
<<<<<<< HEAD
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
=======
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
>>>>>>> bc4f5841a0e22f0ffc25bc0a0a3d457567ad6cc0
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'baulita','andybaulita@gmail.com','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),(2,'suico','lorenz@gmail.com','dc066ce6a8774838c6be69243ec7c1adfee89c0b40b97cd93ce2a968106dbdf6'),(3,'aliser','francis@gmail.com','4544b3726ead44ac2ca3ca71b530a871f9d50626411fd99b86b14ad6a186bc59'),(4,'gobui','ralph@gmail.com','2147c6e281863632c45b5871bed8a9158cb430e41a0b9cfc14c62aac011f6095'),(5,'dummy','test@gmail.com','dc8ffdbf2736dbdf39508017ac594e0d069f3eee9b0f29ece256aa7d831f9ef6'),(6,'dummy2','test2@gmail.com','7b50943dab3f7f92f4120ca9a031d777b5a13bb71749e7e6a2cdd5f2e0ddaf5a'),(7,'dummy3','test3@gmail.com','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08'),(8,'dummy4','test4@gmail.com','e6eb6b4699a92f63128c8ababde3879ef16279989aad2f125a7204e45272043a');
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

<<<<<<< HEAD
-- Dump completed on 2023-05-08 22:04:52
=======
-- Dump completed on 2023-05-08 22:04:52
>>>>>>> bc4f5841a0e22f0ffc25bc0a0a3d457567ad6cc0
