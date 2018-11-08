CREATE DATABASE  IF NOT EXISTS `libreriamanoscritti` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `libreriamanoscritti`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: libreriamanoscritti
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `assegnazione`
--

DROP TABLE IF EXISTS `assegnazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assegnazione` (
  `IDUtente` int(11) NOT NULL,
  `IDPage` int(11) NOT NULL,
  KEY `UtenteAssegnazione` (`IDUtente`),
  KEY `PageAssegnazione` (`IDPage`),
  CONSTRAINT `PageAssegnazione` FOREIGN KEY (`IDPage`) REFERENCES `page` (`id`),
  CONSTRAINT `UtenteAssegnazione` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assegnazione`
--

LOCK TABLES `assegnazione` WRITE;
/*!40000 ALTER TABLE `assegnazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `assegnazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consente`
--

DROP TABLE IF EXISTS `consente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consente` (
  `IDRuolo` int(11) NOT NULL,
  `IDPermessi` int(11) NOT NULL,
  KEY `RuoloConsente` (`IDRuolo`),
  KEY `PermessiConsente` (`IDPermessi`),
  CONSTRAINT `PermessiConsente` FOREIGN KEY (`IDPermessi`) REFERENCES `permessi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `RuoloConsente` FOREIGN KEY (`IDRuolo`) REFERENCES `ruolo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consente`
--

LOCK TABLES `consente` WRITE;
/*!40000 ALTER TABLE `consente` DISABLE KEYS */;
INSERT INTO `consente` VALUES (1,4),(2,4),(3,4),(4,4),(5,4),(6,4),(7,4),(7,1),(7,2),(7,3),(2,5),(4,6),(3,5),(3,3),(5,2),(5,6);
/*!40000 ALTER TABLE `consente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manoscritto`
--

DROP TABLE IF EXISTS `manoscritto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manoscritto` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Pubblicato` tinyint(1) NOT NULL DEFAULT '0',
  `Anno` date DEFAULT NULL,
  `Secolo` int(11) NOT NULL,
  `Titolo` varchar(50) DEFAULT NULL,
  `Genere` varchar(50) DEFAULT NULL,
  `Autore` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manoscritto`
--

LOCK TABLES `manoscritto` WRITE;
/*!40000 ALTER TABLE `manoscritto` DISABLE KEYS */;
/*!40000 ALTER TABLE `manoscritto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `page` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` smallint(6) NOT NULL,
  `Manoscritto` int(11) NOT NULL,
  `Accettato` tinyint(1) NOT NULL DEFAULT '0',
  `Scanpath` varchar(100) NOT NULL,
  `Trascrizione` text,
  PRIMARY KEY (`ID`),
  KEY `PageManoscritto` (`Manoscritto`),
  CONSTRAINT `PageManoscritto` FOREIGN KEY (`Manoscritto`) REFERENCES `manoscritto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page`
--

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
/*!40000 ALTER TABLE `page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permessi`
--

DROP TABLE IF EXISTS `permessi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permessi` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permessi`
--

LOCK TABLES `permessi` WRITE;
/*!40000 ALTER TABLE `permessi` DISABLE KEYS */;
INSERT INTO `permessi` VALUES (1,'Modifica Back-End'),(2,'Accetta Trascrizioni'),(3,'Accetta Upload'),(4,'Visualizza profilo'),(5,'Carica Upload'),(6,'Carica Trascrizioni');
/*!40000 ALTER TABLE `permessi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruolo`
--

DROP TABLE IF EXISTS `ruolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruolo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruolo`
--

LOCK TABLES `ruolo` WRITE;
/*!40000 ALTER TABLE `ruolo` DISABLE KEYS */;
INSERT INTO `ruolo` VALUES (1,'Utente'),(2,'Uploader'),(3,'RevisoreUpload'),(4,'Trascrittore'),(5,'RevisoreTrascrizioni'),(6,'CapoTrascrittore'),(7,'Amministratore');
/*!40000 ALTER TABLE `ruolo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utente` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(50) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `TitoloDiStudio` varchar(50) DEFAULT NULL,
  `Professione` varchar(50) DEFAULT NULL,
  `Ruolo` int(11) NOT NULL,
  `LivelloTrascrittore` int(11) NOT NULL DEFAULT '0',
  `CanDownload` tinyint(1) NOT NULL DEFAULT '0',
  `RichiestaTrascrittore` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Email` (`Email`),
  KEY `UtenteRuolo` (`Ruolo`),
  CONSTRAINT `UtenteRuolo` FOREIGN KEY (`Ruolo`) REFERENCES `ruolo` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'dangeloandrea14@gmail.com','UtenteNormale','passwordnormale','liceo',NULL,1,0,0,1),(2,'utentone@utente.it','Utentechepuòscaricare','pass','Università',NULL,1,0,1,0),(3,'admin@admin.com','Admin','passadmin','nessuno',NULL,7,0,0,0);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'libreriamanoscritti'
--

--
-- Dumping routines for database 'libreriamanoscritti'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-07  9:48:23
