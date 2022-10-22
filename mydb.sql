-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model` (
  `id` int NOT NULL AUTO_INCREMENT,
  `creator` varchar(16) DEFAULT NULL,
  `des` varchar(100) DEFAULT NULL,
  `algorithm` int NOT NULL,
  `param1` int DEFAULT NULL,
  `param2` int DEFAULT NULL,
  `param3` int DEFAULT NULL,
  `param4` int DEFAULT NULL,
  `state` int DEFAULT '0',
  `data` varchar(150) DEFAULT NULL,
  `acc` double DEFAULT NULL,
  `rec` double DEFAULT NULL,
  `prec` double DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `f1` double DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `creator_idx` (`creator`),
  CONSTRAINT `train` FOREIGN KEY (`creator`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (1,'z','aa',1,0,0,9,1,1,'\\download\\train\\1data.csv',0.9444444444444444,0.9444444444444444,1,'2022-10-19 17:53:04',0.9714285714285714,'2022-10-19 17:53:04'),(3,'aki','aa',1,0,0,9,1,1,'\\download\\train\\3JDT.csv',0.8282828282828283,0.8587570621468926,0.9440993788819876,'2022-10-19 18:01:19',0.8994082840236686,'2022-10-19 18:01:19'),(7,'admin','aa',1,0,0,9,1,1,'\\download\\train\\7Lucene.csv',0.9202898550724637,0.9259259259259259,0.9920634920634921,'2022-10-19 18:07:47',0.9578544061302682,'2022-10-19 18:07:49'),(8,'admin','aa',1,0,0,9,1,1,'\\download\\train\\8Lucene.csv',0.9202898550724637,0.9197080291970803,1,'2022-10-19 18:08:47',0.9581749049429658,'2022-10-19 18:08:47'),(13,'admin','aa',1,0,0,9,1,1,'\\download\\train\\13Lucene.csv',0.9202898550724637,0.9197080291970803,1,'2022-10-19 18:15:47',0.9581749049429658,'2022-10-19 18:15:47'),(16,'admin','aa',0,9,1,0,0,1,'\\download\\train\\16data.csv',0.9444444444444444,0.9444444444444444,1,'2022-10-19 18:23:16',0.9714285714285714,'2022-10-19 18:23:17'),(17,'z','aa',0,9,1,0,0,1,'\\download\\train\\17Lucene.csv',0.9057971014492754,0.9312977099236641,0.9682539682539683,'2022-10-19 18:24:29',0.9494163424124513,'2022-10-19 18:24:32'),(18,'aki','aa',1,0,0,9,1,1,'\\download\\train\\18PDE.csv',0.9026845637583892,0.906896551724138,0.9924528301886792,'2022-10-19 21:43:36',0.9477477477477477,'2022-10-19 21:43:36'),(19,'aki','aa',1,0,0,9,1,1,'\\download\\train\\19data.csv',0.9444444444444444,0.9444444444444444,1,'2022-10-19 22:34:28',0.9714285714285714,'2022-10-19 22:34:28'),(20,'aki','aa',1,0,0,9,1,1,'\\download\\train\\20data.csv',0.9444444444444444,0.9444444444444444,1,'2022-10-19 22:37:20',0.9714285714285714,'2022-10-19 22:37:20'),(21,'admin','aa',1,0,0,9,1,1,'\\download\\train\\21data.csv',0.9444444444444444,0.9444444444444444,1,'2022-10-19 22:37:58',0.9714285714285714,'2022-10-19 22:37:58'),(22,'z','aa',1,0,0,9,1,1,'\\download\\train\\22data.csv',0.9444444444444444,0.9444444444444444,1,'2022-10-19 22:40:10',0.9714285714285714,'2022-10-19 22:40:11'),(23,'aki','aa',1,0,0,9,1,1,'\\download\\train\\23data.csv',0.9444444444444444,0.9444444444444444,1,'2022-10-19 22:41:58',0.9714285714285714,'2022-10-19 22:41:59'),(24,'aki','aa',1,0,0,9,1,1,'\\download\\train\\24data.csv',0.9444444444444444,0.9444444444444444,1,'2022-10-19 22:43:59',0.9714285714285714,'2022-10-19 22:43:59'),(25,'aki','aa',1,0,0,9,1,1,'\\download\\train\\25data.csv',0.9444444444444444,0.9444444444444444,1,'2022-10-19 23:43:41',0.9714285714285714,'2022-10-19 23:43:41'),(26,'aki','test',0,9,0,0,0,1,'\\download\\train\\26data.csv',0.8888888888888888,0.9411764705882353,0.9411764705882353,'2022-10-21 14:38:34',0.9411764705882353,'2022-10-21 14:38:34'),(29,'gray','gray\'s model',0,9,0,0,0,1,'\\download\\train\\29JDT.csv',0.6363636363636364,0.888,0.8740157480314961,'2022-10-22 14:47:24',0.8809523809523809,'2022-10-22 15:01:24'),(30,'gray','my model2',0,9,0,0,0,1,'\\download\\train\\30data.csv',0.9444444444444444,0.9444444444444444,1,'2022-10-22 15:25:11',0.9714285714285714,'2022-10-22 15:25:11'),(31,'gray','tkkkkkk',1,0,0,9,0,1,'\\download\\train\\31JDT.csv',0.8333333333333334,0.8595505617977528,0.9503105590062112,'2022-10-22 15:35:00',0.9026548672566372,'2022-10-22 15:35:16'),(32,'gray','hello',1,0,0,9,0,1,'\\download\\train\\32data.csv',0.9444444444444444,0.9444444444444444,1,'2022-10-22 15:35:32',0.9714285714285714,'2022-10-22 15:35:32');
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predict`
--

DROP TABLE IF EXISTS `predict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `predict` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `data` varchar(150) DEFAULT NULL,
  `result` varchar(150) DEFAULT '',
  `time` datetime NOT NULL,
  `model` int NOT NULL,
  `state` int DEFAULT '0',
  `finish` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `has_idx` (`username`),
  CONSTRAINT `has` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predict`
--

LOCK TABLES `predict` WRITE;
/*!40000 ALTER TABLE `predict` DISABLE KEYS */;
INSERT INTO `predict` VALUES (1,'aki','\\download\\predict\\1pre.csv','/download/result/predict_result1.csv','2022-10-19 22:21:42',1,1,'2022-10-19 22:21:42'),(3,'admin','\\download\\predict\\3predict.csv','/download/result/predict_result3.csv','2022-10-19 22:24:43',3,1,'2022-10-19 22:24:43'),(4,'admin','\\download\\predict\\4predict.csv','/download/result/predict_result4.csv','2022-10-19 22:25:27',7,1,'2022-10-19 22:25:29'),(6,'admin','\\download\\predict\\6predict.csv','/download/result/predict_result6.csv','2022-10-19 22:28:14',13,1,'2022-10-19 22:28:14'),(7,'z','\\download\\predict\\7predict.csv','/download/result/predict_result7.csv','2022-10-19 22:29:05',18,1,'2022-10-19 22:29:06'),(8,'aki','\\download\\predict\\8pre.csv','/download/result/predict_result8.csv','2022-10-19 22:29:32',18,1,'2022-10-19 22:29:33'),(10,'aki','\\download\\predict\\10pre.csv','/download/result/predict_result10.csv','2022-10-19 22:44:31',24,1,'2022-10-19 22:44:32'),(11,'aki','\\download\\predict\\11pre.csv','/download/result/predict_result11.csv','2022-10-21 14:44:39',26,1,'2022-10-21 14:44:40'),(12,'aki','\\download\\predict\\12pre.csv','/download/result/predict_result12.csv','2022-10-21 14:44:58',25,1,'2022-10-21 14:44:58'),(13,'gray','\\download\\predict\\13pre.csv','/download/result/predict_result13.csv','2022-10-22 14:58:45',25,1,'2022-10-22 14:58:45'),(14,'gray','\\download\\predict\\14predict.csv','/download/result/predict_result14.csv','2022-10-22 15:25:39',30,1,'2022-10-22 15:25:40');
/*!40000 ALTER TABLE `predict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'aki','123','20301089'),(2,'admin','123',NULL),(3,'z','123',NULL),(8,'l','123','20301089'),(9,'gray','gray','1224145417@qq.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-22 15:37:40
