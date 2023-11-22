CREATE DATABASE  IF NOT EXISTS `game` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `game`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: game
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `attribute`
--

DROP TABLE IF EXISTS `attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribute` (
  `playerId` int NOT NULL,
  `current_hp` int NOT NULL,
  `current_power` int NOT NULL,
  PRIMARY KEY (`playerId`),
  CONSTRAINT `attribute_ibfk_1` FOREIGN KEY (`playerId`) REFERENCES `player` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute`
--

LOCK TABLES `attribute` WRITE;
/*!40000 ALTER TABLE `attribute` DISABLE KEYS */;
INSERT INTO `attribute` VALUES (1,55,184);
/*!40000 ALTER TABLE `attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `high_score`
--

DROP TABLE IF EXISTS `high_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `high_score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `playerId` int DEFAULT NULL,
  `lvl` int DEFAULT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `playerId` (`playerId`),
  CONSTRAINT `high_score_ibfk_1` FOREIGN KEY (`playerId`) REFERENCES `player` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `high_score`
--

LOCK TABLES `high_score` WRITE;
/*!40000 ALTER TABLE `high_score` DISABLE KEYS */;
INSERT INTO `high_score` VALUES (1,1,0,-1411251704),(2,1,0,0),(3,1,0,776),(4,1,0,930),(5,1,0,873),(6,1,0,864),(7,1,0,874),(8,1,0,968),(9,1,0,389),(10,1,0,969),(11,1,0,973),(12,1,0,974);
/*!40000 ALTER TABLE `high_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `playerId` int NOT NULL,
  `current_x` int NOT NULL,
  `current_y` int NOT NULL,
  PRIMARY KEY (`playerId`),
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`playerId`) REFERENCES `player` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,384,432);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `pass` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,'oni','123','oni',888);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_state`
--

DROP TABLE IF EXISTS `player_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_state` (
  `playerId` int NOT NULL,
  `current_lvl` int NOT NULL,
  `game_state` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`playerId`),
  CONSTRAINT `player_state_ibfk_1` FOREIGN KEY (`playerId`) REFERENCES `player` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_state`
--

LOCK TABLES `player_state` WRITE;
/*!40000 ALTER TABLE `player_state` DISABLE KEYS */;
INSERT INTO `player_state` VALUES (1,0,0);
/*!40000 ALTER TABLE `player_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'game'
--
/*!50003 DROP PROCEDURE IF EXISTS `GetPlayerInfo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetPlayerInfo`(IN playerIdParam INT)
BEGIN
    SELECT p.id, p.username, p.name, p.score, l.current_x, l.current_y, ps.current_lvl,a.current_hp as hp , a.current_power as power
    FROM player p
    left JOIN location l ON p.id = l.playerId
    left JOIN player_state ps ON p.id = ps.playerId
    left join attribute a on a.playerId = p.id
    WHERE p.id = playerIdParam;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `saveCurrentProgress` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `saveCurrentProgress`(
    IN player INT,
    IN hp INT,
    IN power INT,
    IN x INT,
    IN y INT,
    IN lvl INT,
    IN score INT
)
BEGIN
    DECLARE playerExists INT;

    -- Check if the player exists in the location table
    SELECT COUNT(*) INTO playerExists FROM location WHERE playerId = player;

    IF playerExists > 0 THEN
        -- Update the player's location
        UPDATE location
        SET current_x = x,
            current_y = y
        WHERE playerId = player;
        
        -- Update the player's attributes in different tables
        UPDATE attribute
        SET current_hp = hp,
            current_power = power
        WHERE playerId = player;
        
        UPDATE player_state
        SET current_lvl = lvl
        WHERE playerId = player;
        
        UPDATE player
        SET player.score = score
        WHERE id = player;
    ELSE
        -- Insert the player's data into respective tables if they don't exist
        INSERT INTO location (playerId, current_x, current_y)
        VALUES (player, x, y);
        
        INSERT INTO attribute (playerId, current_hp, current_power)
        VALUES (player, hp, power);
        
        INSERT INTO player_state (playerId, current_lvl)
        VALUES (player, lvl);
        
        UPDATE player
        SET player.score = score
        WHERE id = player;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-22 10:12:59
