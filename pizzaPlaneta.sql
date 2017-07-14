CREATE DATABASE  IF NOT EXISTS `pizzeria` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pizzeria`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: pizzeria
-- ------------------------------------------------------
-- Server version	5.6.36

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id_cliente` varchar(36) NOT NULL,
  `nombre_cliente` varchar(45) DEFAULT NULL,
  `rut` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `id_interno_clte` varchar(48) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('00f5c678-1e6b-4492-a8e2-592909c3ce07','manuel','333333333','98989898','qdwqdqwdqwd@dqwdwdqwdwqd.cl',NULL,NULL),('31a8d00a-ec75-4f7c-8cee-76734e4acaea','jose escalona','15424182-5','12345678','jose@gmail.com','1',NULL),('a71d61d5-8983-4ab8-b452-94cf19f5272c','manuel meriño','16340395-1','67391357','manuel@gmail.com','2',NULL),('d3ee5c60-b7ba-49e8-876b-4bbc6d4383ea','manuel','11111111-1','23232323','qqq@qqq.qq',NULL,NULL),('e11ddb8b-4ee1-462c-9374-0ef5a10879d1','kakaka','22222222-2','23232323','dqwdqw@dwqdw.cl','21312',NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_venta`
--

DROP TABLE IF EXISTS `detalle_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_venta` (
  `id_detalle_venta` varchar(36) NOT NULL,
  `id_venta` varchar(36) DEFAULT NULL,
  `codigo_producto` varchar(45) DEFAULT NULL,
  `cantidad` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_venta`
--

LOCK TABLES `detalle_venta` WRITE;
/*!40000 ALTER TABLE `detalle_venta` DISABLE KEYS */;
INSERT INTO `detalle_venta` VALUES ('08a5c912-b78c-4a5d-8947-7ded3d40bee4','1','2','5'),('1eb5db34-08ac-41cb-9677-1718e7d75c58','1','3','2'),('dc66ff5b-2631-4814-b0e1-24868502e139','1','1','10');
/*!40000 ALTER TABLE `detalle_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `id_producto` varchar(45) NOT NULL,
  `producto` varchar(45) DEFAULT NULL,
  `codigo_producto` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `fecha_registro` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES ('15dd1a4b-27f7-4c6f-a324-d43b9245e934','pizza italiana','3','pizza ita',5000,'2017-07-03 22:36:21.351'),('4e04ce40-aec6-41d4-b004-ce14bfa54012','pizza napolitana','1','pizza',3000,'2017-07-03 22:34:07.183'),('64c9f2ee-4a4c-4c7f-a052-75174cf1e178','producto prueba','9','dqwdqdqwd',9999,'2017-07-08 11:50:40'),('a0c2152e-a9b5-4392-a0ba-acfa12e31bcb','pizza americana','2','pizza am',4000,'2017-07-03 22:35:46.912'),('bbeccd1c-9b54-4fdc-af9c-4ff3ef488dff','producto prueba','9','dqwdqdqwd',9999,'2017-07-08 11:55:57'),('eedf1bb0-602e-49cb-a0cd-f708d8667e39','producto prueba','9','dqwdqdqwd',9999,'2017-07-08 12:01:10'),('f6e58d2e-96e4-450b-8fa1-bcaee12e1c28','producto prueba 3','99','aaaa 1',19990,'2017-07-09 16:14:30.611'),('fc1ecfba-9a83-4fd6-b914-189e8219c3c4','producto prueba 1','9','dqwdqdqwd 1',9999,'2017-07-08 12:10:27');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventas` (
  `id` varchar(48) NOT NULL,
  `id_interno_clte` varchar(48) DEFAULT NULL,
  `fecha_venta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id_venta` varchar(48) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES ('70c63b0a-7b47-43b9-b638-058beff82926','1','2017-07-04 02:56:14','1'),('a3e9e71a-049a-490f-80a3-c359badc5b68','2','2017-07-04 02:56:40','2');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-14 12:22:39
