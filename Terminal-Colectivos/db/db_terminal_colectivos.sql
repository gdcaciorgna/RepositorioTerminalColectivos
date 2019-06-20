CREATE DATABASE  IF NOT EXISTS `db_terminal_colectivos` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_terminal_colectivos`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: db_terminal_colectivos
-- ------------------------------------------------------
-- Server version	5.0.24-community-nt

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
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudad` (
  `cod_postal` int(11) NOT NULL,
  `nombre` varchar(45) default NULL,
  PRIMARY KEY  (`cod_postal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (2000,'Rosario'),(2600,'Venado Tuerto');
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colectivo`
--

DROP TABLE IF EXISTS `colectivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `colectivo` (
  `patente` varchar(45) NOT NULL,
  `capacidad` int(11) default NULL,
  `estado` varchar(45) default NULL,
  PRIMARY KEY  (`patente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colectivo`
--

LOCK TABLES `colectivo` WRITE;
/*!40000 ALTER TABLE `colectivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `colectivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compania_tarjeta`
--

DROP TABLE IF EXISTS `compania_tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compania_tarjeta` (
  `cod_compania` int(11) NOT NULL auto_increment,
  `nombre` varchar(45) default NULL,
  PRIMARY KEY  (`cod_compania`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compania_tarjeta`
--

LOCK TABLES `compania_tarjeta` WRITE;
/*!40000 ALTER TABLE `compania_tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `compania_tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `escala`
--

DROP TABLE IF EXISTS `escala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `escala` (
  `cod_ruta` int(11) NOT NULL,
  `orden` int(11) NOT NULL,
  `cod_terminal` int(11) default NULL,
  PRIMARY KEY  (`cod_ruta`,`orden`),
  KEY `escala_terminal_idx` (`cod_terminal`),
  CONSTRAINT `escala_ruta` FOREIGN KEY (`cod_ruta`) REFERENCES `ruta` (`cod_ruta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `escala_terminal` FOREIGN KEY (`cod_terminal`) REFERENCES `terminal` (`cod_terminal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `escala`
--

LOCK TABLES `escala` WRITE;
/*!40000 ALTER TABLE `escala` DISABLE KEYS */;
/*!40000 ALTER TABLE `escala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasajero`
--

DROP TABLE IF EXISTS `pasajero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pasajero` (
  `dni` int(11) NOT NULL,
  `nombre` varchar(45) default NULL,
  `apellido` varchar(45) default NULL,
  PRIMARY KEY  (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasajero`
--

LOCK TABLES `pasajero` WRITE;
/*!40000 ALTER TABLE `pasajero` DISABLE KEYS */;
/*!40000 ALTER TABLE `pasajero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasajero_reserva`
--

DROP TABLE IF EXISTS `pasajero_reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pasajero_reserva` (
  `dni` int(11) NOT NULL,
  `cod_ruta` int(11) NOT NULL,
  `fecha_res` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `usuario` varchar(45) NOT NULL,
  `asiento` int(11) default NULL,
  PRIMARY KEY  (`dni`,`cod_ruta`,`fecha_res`,`usuario`),
  KEY `pasajero_reserva_usuario_idx` (`usuario`),
  KEY `pasajero_reserva_reserva_idx` (`fecha_res`),
  KEY `pasajero_reserva_ruta_idx` (`cod_ruta`),
  CONSTRAINT `pasajero_reserva_pasajero` FOREIGN KEY (`dni`) REFERENCES `pasajero` (`dni`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pasajero_reserva_reserva` FOREIGN KEY (`fecha_res`) REFERENCES `reserva` (`fecha_res`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pasajero_reserva_ruta` FOREIGN KEY (`cod_ruta`) REFERENCES `ruta` (`cod_ruta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pasajero_reserva_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasajero_reserva`
--

LOCK TABLES `pasajero_reserva` WRITE;
/*!40000 ALTER TABLE `pasajero_reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `pasajero_reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan` (
  `fecha_hora_plan` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `patente` int(11) NOT NULL,
  `cuil` int(11) NOT NULL,
  `cod_ruta` int(11) NOT NULL,
  `precio` double default NULL,
  PRIMARY KEY  (`fecha_hora_plan`,`patente`,`cuil`,`cod_ruta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `fecha_res` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `cod_ruta` int(11) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `cant_pas` int(11) default NULL,
  `fecha_viaje` timestamp NOT NULL default '0000-00-00 00:00:00',
  `fecha_venc` timestamp NOT NULL default '0000-00-00 00:00:00',
  `fecha_canc` timestamp NOT NULL default '0000-00-00 00:00:00',
  `cod_compania` int(11) default NULL,
  `nro_tarjeta` int(11) default NULL,
  `fecha_pago` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`fecha_res`,`cod_ruta`,`usuario`),
  KEY `ruta_reserva_idx` (`cod_ruta`),
  KEY `usuario_reserva_idx` (`usuario`),
  KEY `compania_reserva_idx` (`cod_compania`),
  CONSTRAINT `compania_reserva` FOREIGN KEY (`cod_compania`) REFERENCES `compania_tarjeta` (`cod_compania`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ruta_reserva` FOREIGN KEY (`cod_ruta`) REFERENCES `ruta` (`cod_ruta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usuario_reserva` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruta`
--

DROP TABLE IF EXISTS `ruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruta` (
  `cod_ruta` int(11) NOT NULL auto_increment,
  `dias_sem` varchar(45) default NULL,
  PRIMARY KEY  (`cod_ruta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruta`
--

LOCK TABLES `ruta` WRITE;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `terminal`
--

DROP TABLE IF EXISTS `terminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `terminal` (
  `cod_terminal` int(11) NOT NULL auto_increment,
  `cod_postal` int(11) default NULL,
  PRIMARY KEY  (`cod_terminal`),
  KEY `terminal_ciudad_idx` (`cod_postal`),
  CONSTRAINT `terminal_ciudad` FOREIGN KEY (`cod_postal`) REFERENCES `ciudad` (`cod_postal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terminal`
--

LOCK TABLES `terminal` WRITE;
/*!40000 ALTER TABLE `terminal` DISABLE KEYS */;
/*!40000 ALTER TABLE `terminal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `usuario` varchar(45) NOT NULL,
  `rol` varchar(45) NOT NULL default 'Cliente',
  `email` varchar(45) NOT NULL,
  `contrasenia` varchar(45) NOT NULL,
  `cuil` int(11) default NULL,
  PRIMARY KEY  (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('admin','admin','admin@hotmail.com','admin',123456);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-20 18:43:45
