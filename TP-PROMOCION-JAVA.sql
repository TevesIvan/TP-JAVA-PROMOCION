CREATE DATABASE  IF NOT EXISTS `tp` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tp`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: tp
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Encargado'),(2,'Administrador'),(3,'Usuario');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elemento`
--

DROP TABLE IF EXISTS `elemento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `elemento` (
  `idElemento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `idTipoElemento` int(11) DEFAULT NULL,
  PRIMARY KEY (`idElemento`),
  KEY `fk_idTipoElemento_idx` (`idTipoElemento`),
  CONSTRAINT `fk_idTipoElemento` FOREIGN KEY (`idTipoElemento`) REFERENCES `tipo_elemento` (`idTipoElemento`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elemento`
--

LOCK TABLES `elemento` WRITE;
/*!40000 ALTER TABLE `elemento` DISABLE KEYS */;
INSERT INTO `elemento` VALUES (1,'Proyector 1',1),(2,'Proyector 23',1),(3,'Cochera Grande',2),(4,'Proyector 26',1),(5,'Notebook 42',3);
/*!40000 ALTER TABLE `elemento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `dni` varchar(45) NOT NULL,
  `habilitado` bit(1) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `contraseña` varchar(45) DEFAULT NULL,
  `idCategoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`dni`),
  KEY `fk_idCategoria_idx` (`idCategoria`),
  CONSTRAINT `fk_idCategoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Ivan','Teves','39502883','','ivtev','123qwe',2),(3,'Juan','Rodriguez','12345678','','juan','123456',1),(7,'Martin','Martinez','87654321','','ele','asdf',3),(8,'Mr.','Satan','666','','AST','qweasd',1);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPersona` int(11) NOT NULL,
  `idElemento` int(11) NOT NULL,
  `detalle` varchar(45) DEFAULT NULL,
  `estado` varchar(45) NOT NULL,
  `fechaHoraReserva` datetime NOT NULL,
  `fechaHoraDesde` datetime NOT NULL,
  `fechaHoraHasta` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idPersona_idx` (`idPersona`),
  KEY `fk_idElemento_idx` (`idElemento`),
  CONSTRAINT `fk_idElemento` FOREIGN KEY (`idElemento`) REFERENCES `elemento` (`idElemento`) ON UPDATE CASCADE,
  CONSTRAINT `fk_idPersona` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (1,1,2,'Importante','Reservado','2017-01-01 00:00:00','2017-09-07 00:00:00','2017-09-10 00:00:00'),(2,3,1,'Urgente','Reservado','2017-02-02 00:00:00','2017-06-01 00:00:00','2017-06-04 00:00:00'),(7,1,2,'Que ande bien','Reservado','2017-09-07 00:00:00','2017-06-02 00:00:00','2017-06-05 00:00:00'),(13,1,1,'Que tenga HDMI','Cancelado','2017-11-24 00:00:00','2040-01-01 00:00:00','2040-01-04 00:00:00'),(14,1,1,' ','Cancelado','2017-11-24 15:25:12','2040-01-01 14:00:00','2040-01-04 15:00:00'),(15,3,3,'Que entre una Camioneta','Reservado','2018-02-01 16:47:19','2018-02-27 14:00:00','2018-03-01 15:00:00'),(16,3,2,'Que sea HD','Cancelado','2018-02-04 18:24:39','2090-01-01 14:00:00','2091-02-02 15:00:00'),(17,1,2,'','Cancelado','2018-02-09 17:58:59','2018-02-10 14:00:00','2018-02-11 14:00:00');
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_elemento`
--

DROP TABLE IF EXISTS `tipo_elemento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_elemento` (
  `idTipoElemento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `cantMax` int(11) DEFAULT NULL,
  `encargado` bit(1) DEFAULT NULL,
  `maxTiempo` int(11) DEFAULT NULL,
  `diasAntMax` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTipoElemento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_elemento`
--

LOCK TABLES `tipo_elemento` WRITE;
/*!40000 ALTER TABLE `tipo_elemento` DISABLE KEYS */;
INSERT INTO `tipo_elemento` VALUES (1,'Proyector',10,'\0',24,2),(2,'Cochera',15,'\0',150,30),(3,'Notebook',20,'',200,10);
/*!40000 ALTER TABLE `tipo_elemento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-11 19:04:13
