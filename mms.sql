CREATE DATABASE  IF NOT EXISTS `mfrp` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mfrp`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: mfrp
-- ------------------------------------------------------
-- Server version	5.5.25a

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
-- Table structure for table `status_master`
--

DROP TABLE IF EXISTS `status_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_master` (
  `id` int(1) NOT NULL AUTO_INCREMENT,
  `status` varchar(20) NOT NULL,
  `min_age` int(3) NOT NULL,
  `max_age` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_master`
--

LOCK TABLES `status_master` WRITE;
/*!40000 ALTER TABLE `status_master` DISABLE KEYS */;
INSERT INTO `status_master` VALUES (1,'Minor',0,17),(2,'Normal',18,60),(4,'Senior',61,150);
/*!40000 ALTER TABLE `status_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin','admin123');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country_master`
--

DROP TABLE IF EXISTS `country_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country_master` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_master`
--

LOCK TABLES `country_master` WRITE;
/*!40000 ALTER TABLE `country_master` DISABLE KEYS */;
INSERT INTO `country_master` VALUES (1,'India');
/*!40000 ALTER TABLE `country_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch_admin_info`
--

DROP TABLE IF EXISTS `branch_admin_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch_admin_info` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `address` varchar(500) NOT NULL,
  `state_id` int(4) NOT NULL,
  `email` varchar(254) NOT NULL,
  `password` varchar(200) NOT NULL,
  `gender` varchar(15) NOT NULL,
  `marital_status` varchar(20) NOT NULL,
  `contact_no` varchar(10) NOT NULL,
  `status_id` int(1) NOT NULL,
  `date_of_birth` date NOT NULL,
  `identification_id` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `state_id` (`state_id`),
  KEY `status_id` (`status_id`),
  KEY `identification_id` (`identification_id`),
  CONSTRAINT `branch_admin_info_ibfk_1` FOREIGN KEY (`state_id`) REFERENCES `state_master` (`id`) ON DELETE CASCADE,
  CONSTRAINT `branch_admin_info_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`id`) ON DELETE CASCADE,
  CONSTRAINT `branch_admin_info_ibfk_3` FOREIGN KEY (`identification_id`) REFERENCES `identification_master` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1012 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_admin_info`
--

LOCK TABLES `branch_admin_info` WRITE;
/*!40000 ALTER TABLE `branch_admin_info` DISABLE KEYS */;
INSERT INTO `branch_admin_info` VALUES (1011,'Pankaj','Joshi','Nainital',2,'pankaj@gmail.com','YJChw2IcLJgJdKFZJpRf5A==','male','married','9412348640',1,'2014-09-09',4);
/*!40000 ALTER TABLE `branch_admin_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch_stock_detail`
--

DROP TABLE IF EXISTS `branch_stock_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch_stock_detail` (
  `id` varchar(6) NOT NULL,
  `medicine_id` varchar(5) NOT NULL,
  `branch_admin_id` int(4) NOT NULL,
  `branch_id` int(3) NOT NULL,
  `number_of_strips` int(5) NOT NULL,
  `registration_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `medicine_id` (`medicine_id`),
  KEY `branch_admin_id` (`branch_admin_id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `branch_stock_detail_ibfk_1` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`id`) ON DELETE CASCADE,
  CONSTRAINT `branch_stock_detail_ibfk_2` FOREIGN KEY (`branch_admin_id`) REFERENCES `branch_admin_info` (`id`) ON DELETE CASCADE,
  CONSTRAINT `branch_stock_detail_ibfk_3` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_stock_detail`
--

LOCK TABLES `branch_stock_detail` WRITE;
/*!40000 ALTER TABLE `branch_stock_detail` DISABLE KEYS */;
INSERT INTO `branch_stock_detail` VALUES ('ST0001','M0001',1011,1,50,'2015-07-02 09:26:52','20'),('ST0002','M0001',1011,1,50,'2015-07-02 09:51:56','descriptio'),('ST0003','M0001',1011,1,41,'2015-07-06 06:00:04','sfidnkl');
/*!40000 ALTER TABLE `branch_stock_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state_master`
--

DROP TABLE IF EXISTS `state_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state_master` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(30) NOT NULL,
  `country_id` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `country_id` (`country_id`),
  CONSTRAINT `state_master_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `country_master` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state_master`
--

LOCK TABLES `state_master` WRITE;
/*!40000 ALTER TABLE `state_master` DISABLE KEYS */;
INSERT INTO `state_master` VALUES (1,'ANDHRA PRADESH',1),(2,'ASSAM',1),(3,'ARUNACHAL PRADESH',1),(4,'BIHAR',1),(5,'GUJRAT',1),(6,'HARYANA',1),(7,'HIMACHAL PRADESH',1),(8,'JAMMU & KASHMIR',1),(9,'KARNATAKA',1),(10,'KERALA',1),(11,'MADHYA PRADESH',1),(12,'MAHARASHTRA',1),(13,'MANIPUR',1),(14,'MEGHALAYA',1),(15,'MIZORAM',1),(16,'NAGALAND',1),(17,'ORISSA',1),(18,'PUNJAB',1),(19,'RAJASTHAN',1),(20,'SIKKIM',1),(21,'TAMIL NADU',1),(22,'TRIPURA',1),(23,'UTTAR PRADESH',1),(24,'WEST BENGAL',1),(25,'DELHI',1),(26,'GOA',1),(27,'PONDICHERY',1),(28,'LAKSHDWEEP',1),(29,'DAMAN & DIU',1),(30,'DADRA & NAGAR',1),(31,'CHANDIGARH',1),(32,'ANDAMAN & NICOBAR',1),(33,'UTTARANCHAL',1),(34,'JHARKHAND',1),(35,'CHATTISGARH',1);
/*!40000 ALTER TABLE `state_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine_request`
--

DROP TABLE IF EXISTS `medicine_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine_request` (
  `id` varchar(6) NOT NULL,
  `medicine_id` varchar(5) DEFAULT NULL,
  `no_of_Strips` int(10) NOT NULL,
  `branch_id` int(3) NOT NULL,
  `request_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `branch_admin_id` int(4) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `medicine_id` (`medicine_id`),
  KEY `branch_admin_id` (`branch_admin_id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `medicine_request_ibfk_1` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`id`) ON DELETE CASCADE,
  CONSTRAINT `medicine_request_ibfk_2` FOREIGN KEY (`branch_admin_id`) REFERENCES `branch_admin_info` (`id`) ON DELETE CASCADE,
  CONSTRAINT `medicine_request_ibfk_3` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine_request`
--

LOCK TABLES `medicine_request` WRITE;
/*!40000 ALTER TABLE `medicine_request` DISABLE KEYS */;
INSERT INTO `medicine_request` VALUES ('REQ002','M0002',10,1,'2015-07-02 09:36:05',1011,'Cancelled'),('REQ003','M0001',50,1,'2015-07-06 05:09:01',1011,'Approved'),('REQ004','M0001',50,1,'2015-07-06 07:39:43',1011,'Cancelled'),('REQ005','M0015',5,1,'2015-07-06 07:44:54',1011,'Rejected'),('REQ006','M0015',5,1,'2015-07-06 07:45:08',1011,'Approved');
/*!40000 ALTER TABLE `medicine_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `branch_admin_id` int(4) DEFAULT NULL,
  `branch_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `branch_admin_id` (`branch_admin_id`),
  CONSTRAINT `branch_ibfk_1` FOREIGN KEY (`branch_admin_id`) REFERENCES `branch_admin_info` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,1011,'branch1'),(2,NULL,'branch2'),(3,NULL,'branch3'),(4,NULL,'branch4'),(5,NULL,'branch5'),(6,NULL,'branch6'),(7,NULL,'branch7'),(8,NULL,'branch8'),(9,NULL,'branch9'),(10,NULL,'branch10');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `id` varchar(5) NOT NULL,
  `medicine_name` varchar(200) NOT NULL,
  `dosage_value` int(5) NOT NULL,
  `dosage_unit` varchar(10) NOT NULL,
  `price_of_strip` varchar(15) NOT NULL,
  `description` varchar(500) NOT NULL,
  `requested_strips` int(10) NOT NULL,
  `medicines_in_strip` int(10) NOT NULL,
  `registration_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `manufacture_date` date NOT NULL,
  `expiry_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES ('M0001','Aminophylline ',225,'mg','5.09','Aminophylline : 225 mg',70,10,'2015-06-24 04:45:40','2013-06-04','2018-02-06'),('M0002','Oxcarbazepine',600,'mg','45.12','',15,10,'2015-06-24 04:55:41','2012-05-10','2016-01-14'),('M0003','Nalidixic Acid',300,'mg','26.86','Nalidixic Acid : 300 mg',12,300,'2015-06-24 04:57:56','2010-09-17','2015-08-21'),('M0004','Paracetamol',450,'mg','22.10','Paracetamol 450',14,10,'2015-06-24 05:01:28','2012-11-22','2016-01-07'),('M0005','Vit C ',50,'mg','11.74','Vit C : 50 mg',15,100,'2015-06-24 05:03:37','2011-11-17','2015-11-19'),('M0006','Metronidazole ',200,'mg','12.90','Metronidazole : 200 mg',10,15,'2015-06-24 05:04:12','2014-01-28','2017-01-09'),('M0007','Metronidazole',500,'mg','13.40','Metronidazole : 500 mg',20,15,'2015-06-24 05:08:10','2013-06-24','2016-06-15'),('M0008','Prednisolone',5,'mg','4.59','Prednisolone : 5 mg',20,10,'2015-06-24 05:09:50','2014-02-10','2016-09-17'),('M0009','Metronidazole',400,' mg','18.23','Metronidazole : 400 mg',20,15,'2015-06-24 05:12:06','2013-01-21','2016-08-28'),('M0010','Riboflavin ',10,'mg','1.50','Riboflavin : 10 mg',25,10,'2015-06-24 05:27:35','2014-06-16','2016-08-18'),('M0011','Theophylline',800,'mg','11.42','Theophylline : 800 mg',20,10,'2015-06-24 05:28:40','2013-03-18','2016-05-21'),('M0012','Penicillin V ',65,'mg','17.80','Penicillin V ',8,48,'2015-06-24 05:30:27','2014-02-19','2016-06-16'),('M0013','Glibenclamide : 2.5 mg',3,'mg','0.48','Glibenclamide : 2.5 mg',50,1,'2015-06-24 05:31:57','2014-04-16','2017-07-16'),('M0014','Methotrexate',3,'mg','4.85','Methotrexate : 2.5 mg',50,1,'2015-06-24 05:33:00','2014-04-22','2016-09-11'),('M0015','Metronidazole',400,'mg','10.37','Metronidazole : 400 mg',17,10,'2015-06-24 05:33:13','2014-06-20','2015-09-23'),('M0016','Naproxen',500,'mg','19.6','Naproxen : 500 mg',15,10,'2015-06-24 05:34:21','2012-04-16','2015-12-14'),('M0017','Norfloxacin : 100 mg',100,'mg','3.09','Norfloxacin : 100 mg',25,10,'2015-06-24 05:35:21','2014-02-12','2016-08-21'),('M0018','Metronidazole',300,'mg','8.40','Metronidazole : 300 mg',7,10,'2015-06-24 05:35:57','2014-06-25','2016-01-19'),('M0019','Frusemide (Furosemide) ',100,'mg','4.46','Frusemide (Furosemide) : 100 mg',5,10,'2015-06-24 05:36:27','2015-06-09','2017-08-20'),('M0020','Phenylbutazone',100,'mg','1.98','Phenylbutazone : 100 mg',10,10,'2015-06-24 05:37:19','2013-04-21','2016-08-07'),('M0021','Pheniramine Maleate',50,'mg','3.45','Pheniramine Maleate : 50 mg',10,10,'2015-06-24 05:40:33','2014-04-13','2017-10-08'),('M0022','Metronidazole ',200,'mg','5.97','Metronidazole : 200 mg',15,15,'2015-06-24 05:42:25','2014-09-27','2016-02-17'),('M0023','Pheniramine Maleate ',10,'mg','3.45','Pheniramine Maleate : 50 mg',10,15,'2015-06-24 05:42:39','2013-04-16','2016-08-06'),('M0024','Iodochlorohydroxyquinoline ',250,'mg','4.79','Iodochlorohydroxyquinoline : 250 mg',10,10,'2015-06-24 05:43:32','2014-05-27','2016-07-10'),('M0025','Metronidazole',400,'mg','19.99','Metronidazole : 400 mg',13,15,'2015-06-24 05:44:38','2015-06-03','2016-03-23'),('M0026','Metronidazole',300,'mg','4.72','Metronidazole : 300 mg',18,10,'2015-06-24 05:46:59','2014-12-18','2016-04-13'),('M0027','Metronidazole',400,'mg','6.82','Metronidazole : 400 mg',15,10,'2015-06-24 06:03:51','2015-01-15','2016-03-05'),('M0028','Levodopa',200,'mg','41.70','Levodopa : 200 mg',7,10,'2015-06-24 06:05:21','2014-10-11','2017-02-03'),('M0029','Levodopa ',100,'mg','20.68','Levodopa : 100 mg',9,10,'2015-06-24 06:06:47','2014-04-01','2016-03-07'),('M0030','Levodopa ',100,' mg','16.07','Levodopa : 100 mg',14,10,'2015-06-24 06:13:10','2014-10-16','2016-10-20'),('M0031','Ascorbic Acid ',200,' mg','11.70','Ascorbic Acid : 200 mg',20,10,'2015-06-24 06:15:08','2014-05-31','2016-02-18'),('M0032','Ascorbic Acid ',150,'mg','9.71','Ascorbic Acid : 150 mg',10,10,'2015-06-24 06:16:57','2015-01-15','2017-04-19'),('M0033','hdfhfkhf',15,'mg','6','',20,6,'2015-06-30 09:18:05','2015-06-16','2015-06-25'),('M0034','hdfhfkhf',15,'mg','6','',20,6,'2015-07-06 10:09:33','2015-07-01','2015-08-27'),('M0035','vishnu',15,'mgsd','9','bhdthet',20,6,'2015-07-06 10:59:39','2015-01-08','2015-07-16'),('M0036','vishnu',15,'mgsd','9','bhdthet',20,6,'2015-07-06 11:04:45','2015-01-08','2015-07-16'),('M0037','new medio sdjgkl',4841,'mg','896.2552','fdilshvgilsdnvlk',44,25,'2015-07-06 11:08:02','2015-07-01','2015-07-28');
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identification_master`
--

DROP TABLE IF EXISTS `identification_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identification_master` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `id_document_type` varchar(30) NOT NULL,
  `prefix_format` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identification_master`
--

LOCK TABLES `identification_master` WRITE;
/*!40000 ALTER TABLE `identification_master` DISABLE KEYS */;
INSERT INTO `identification_master` VALUES (1,'Voter Id ','GMV'),(2,'Pass port','PASS'),(4,'Driving License ','DL'),(5,'Adhar Card','AC'),(6,'PAN card','PAN'),(7,'Ration Card','RD');
/*!40000 ALTER TABLE `identification_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'mfrp'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-06 17:29:58
