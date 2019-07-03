CREATE DATABASE  IF NOT EXISTS `db_terminal_colectivos` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_terminal_colectivos`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: db_terminal_colectivos
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `ciudades`
--

DROP TABLE IF EXISTS `ciudades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudades` (
  `cod_postal` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_postal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudades`
--

LOCK TABLES `ciudades` WRITE;
/*!40000 ALTER TABLE `ciudades` DISABLE KEYS */;
INSERT INTO `ciudades` VALUES (2000,'Rosario'),(2600,'Venado Tuerto');
/*!40000 ALTER TABLE `ciudades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colectivos`
--

DROP TABLE IF EXISTS `colectivos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `colectivos` (
  `patente` varchar(45) NOT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`patente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colectivos`
--

LOCK TABLES `colectivos` WRITE;
/*!40000 ALTER TABLE `colectivos` DISABLE KEYS */;
/*!40000 ALTER TABLE `colectivos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companias_tarjetas`
--

DROP TABLE IF EXISTS `companias_tarjetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companias_tarjetas` (
  `cod_compania` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_compania`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companias_tarjetas`
--

LOCK TABLES `companias_tarjetas` WRITE;
/*!40000 ALTER TABLE `companias_tarjetas` DISABLE KEYS */;
/*!40000 ALTER TABLE `companias_tarjetas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `escalas`
--

DROP TABLE IF EXISTS `escalas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `escalas` (
  `cod_ruta` int(11) NOT NULL,
  `orden` int(11) NOT NULL,
  `cod_terminal` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_ruta`,`orden`),
  KEY `escala_terminal_idx` (`cod_terminal`),
  CONSTRAINT `escala_ruta` FOREIGN KEY (`cod_ruta`) REFERENCES `rutas` (`cod_ruta`),
  CONSTRAINT `escala_terminal` FOREIGN KEY (`cod_terminal`) REFERENCES `terminales` (`cod_terminal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `escalas`
--

LOCK TABLES `escalas` WRITE;
/*!40000 ALTER TABLE `escalas` DISABLE KEYS */;
/*!40000 ALTER TABLE `escalas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasajeros`
--

DROP TABLE IF EXISTS `pasajeros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pasajeros` (
  `dni` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasajeros`
--

LOCK TABLES `pasajeros` WRITE;
/*!40000 ALTER TABLE `pasajeros` DISABLE KEYS */;
/*!40000 ALTER TABLE `pasajeros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasajeros_reservas`
--

DROP TABLE IF EXISTS `pasajeros_reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pasajeros_reservas` (
  `dni` int(11) NOT NULL,
  `cod_ruta` int(11) NOT NULL,
  `fecha_res` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `usuario` varchar(45) NOT NULL,
  `asiento` int(11) DEFAULT NULL,
  PRIMARY KEY (`dni`,`cod_ruta`,`fecha_res`,`usuario`),
  KEY `pasajero_reserva_usuario_idx` (`usuario`),
  KEY `pasajero_reserva_reserva_idx` (`fecha_res`),
  KEY `pasajero_reserva_ruta_idx` (`cod_ruta`),
  CONSTRAINT `pasajero_reserva_pasajero` FOREIGN KEY (`dni`) REFERENCES `pasajeros` (`dni`),
  CONSTRAINT `pasajero_reserva_reserva` FOREIGN KEY (`fecha_res`) REFERENCES `reservas` (`fecha_res`),
  CONSTRAINT `pasajero_reserva_ruta` FOREIGN KEY (`cod_ruta`) REFERENCES `rutas` (`cod_ruta`),
  CONSTRAINT `pasajero_reserva_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasajeros_reservas`
--

LOCK TABLES `pasajeros_reservas` WRITE;
/*!40000 ALTER TABLE `pasajeros_reservas` DISABLE KEYS */;
/*!40000 ALTER TABLE `pasajeros_reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planes`
--

DROP TABLE IF EXISTS `planes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planes` (
  `fecha_hora_plan` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `patente` int(11) NOT NULL,
  `cuil` int(11) NOT NULL,
  `cod_ruta` int(11) NOT NULL,
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`fecha_hora_plan`,`patente`,`cuil`,`cod_ruta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planes`
--

LOCK TABLES `planes` WRITE;
/*!40000 ALTER TABLE `planes` DISABLE KEYS */;
/*!40000 ALTER TABLE `planes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservas` (
  `fecha_res` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cod_ruta` int(11) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `cant_pas` int(11) DEFAULT NULL,
  `fecha_viaje` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fecha_venc` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fecha_canc` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `cod_compania` int(11) DEFAULT NULL,
  `nro_tarjeta` int(11) DEFAULT NULL,
  `fecha_pago` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`fecha_res`,`cod_ruta`,`usuario`),
  KEY `ruta_reserva_idx` (`cod_ruta`),
  KEY `usuario_reserva_idx` (`usuario`),
  KEY `compania_reserva_idx` (`cod_compania`),
  CONSTRAINT `compania_reserva` FOREIGN KEY (`cod_compania`) REFERENCES `companias_tarjetas` (`cod_compania`),
  CONSTRAINT `ruta_reserva` FOREIGN KEY (`cod_ruta`) REFERENCES `rutas` (`cod_ruta`),
  CONSTRAINT `usuario_reserva` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rutas`
--

DROP TABLE IF EXISTS `rutas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rutas` (
  `cod_ruta` int(11) NOT NULL AUTO_INCREMENT,
  `dias_sem` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_ruta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rutas`
--

LOCK TABLES `rutas` WRITE;
/*!40000 ALTER TABLE `rutas` DISABLE KEYS */;
/*!40000 ALTER TABLE `rutas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `terminales`
--

DROP TABLE IF EXISTS `terminales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `terminales` (
  `cod_terminal` int(11) NOT NULL AUTO_INCREMENT,
  `cod_postal` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_terminal`),
  KEY `terminal_ciudad_idx` (`cod_postal`),
  CONSTRAINT `terminal_ciudad` FOREIGN KEY (`cod_postal`) REFERENCES `ciudades` (`cod_postal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terminales`
--

LOCK TABLES `terminales` WRITE;
/*!40000 ALTER TABLE `terminales` DISABLE KEYS */;
/*!40000 ALTER TABLE `terminales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `usuario` varchar(45) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `rol` varchar(45) NOT NULL DEFAULT 'Cliente',
  `email` varchar(45) NOT NULL,
  `contrasenia` varchar(45) NOT NULL,
  `cuil` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT 'activo',
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('admin',NULL,NULL,'admin','admin@hotmail.com','admin',NULL,'activo'),('federico',NULL,NULL,'chofer','f_caciorgna@hotmail.com','123456',123,'eliminado'),('gdcaciorgna',NULL,NULL,'admin','gdcaciorgna@hotmail.com','123456',NULL,'activo'),('joaquinvilchez',NULL,NULL,'cliente','joaquinvilchez@hotmail.com','123456',NULL,'activo');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-03  0:51:17
