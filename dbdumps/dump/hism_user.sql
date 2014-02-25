CREATE DATABASE  IF NOT EXISTS `hism` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hism`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: hism
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `middlename` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `creationdate` varchar(45) DEFAULT NULL,
  `administrator` int(1) DEFAULT NULL,
  `reserve` int(1) DEFAULT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,'admin','Rollercoaster2','Admin','','Admin','26092013',1,0),(8,'jesjen86@live.dk','jesp152369jen','Jesper','Bundgaard','Jensen','09102013',1,0),(9,'bestyrer@hoenen.dk','230680','Martin','Kim','Pedersen','12102013',1,0),(10,'lennim@hotmail.com','hackerz123','Lenni','askelund','mørch','12102013',1,0),(11,'mallica_t@hotmail.com','Camilla27','Mallica','','Back','16102013',1,0),(12,'jimmy_johansen@hotmail.com','Jimmy1234','Jimmy','','Johansen','16102013',1,0),(13,'mayzethrane@gmail.com','Billkaulitz23','Majbrit','Diana','Thrane','17102013',1,0),(14,'Mayzethrane20@gmail.com','Billkaulitz23','Majbrit','Diana ','Thrane','17102013',1,0),(15,'pkkann@gmail.com','Rollercoaster2','Patrick','','Kann','18102013',1,0),(16,'aske65@gmail.com','Aslak1983','Aslak','Carl','Poulsen','19102013',1,0),(17,'jacdam1@hotmail.com','googletagerminekager','Jacob','Dam','Svendsen','23102013',1,0),(18,'stephanie.kristensen@hotmail.com','sk10121990','Stephanie','','Kristensen','24102013',1,0),(19,'killwer@gmail.com','dnaand123','Anders','Steen','Jensen','24102013',0,0),(20,'summoned.91@hotmail.com','ikkegyldig','Nicolai','Birk','Gisselmann','25102013',1,0),(21,'frederikjansrud@gmail.com','8o88yolsen','Frederik','','jansrud','26102013',1,0),(22,'andetrs111@hotmail.com','frederik','østergaard','---','---','30102013',1,0),(23,'4140kristian@gmail.com','gg4s9xddw1(','Kristian','Enøe','Ramsnæs','30102013',1,0),(24,'nvjacobsen@gmail.com','741741','Nicklas','Voldbirk','Jacobsen','01112013',1,0),(25,'nadjajensen92@gmail.com','christopher','Nadja','','Jensen','06112013',1,0),(26,'helenelyng@hotmail.com','speedo','Helene','Elkjer','Lyng','07112013',1,0),(27,'lessen_6@hotmail.com','lea123','Lea','Line','Kunckel','09112013',1,0),(28,'sabrina191087@hotmail.com','kroell87','Sabrina','Brock','Sørensen','13112013',1,0),(29,'BalderRS2@hotmail.com','marts1994','Balder','','Rasmussen','16112013',0,0),(30,'jannick.bah@gmail.com','fuller','Jannick','','Bahle','21112013',1,0),(31,'anita_snuff@live.dk','jegersej2602','Anita','Tar','Olsen','23112013',1,0),(32,'Sif.arnkov@gmail.com','Dougie91','Sif','Anna Quaade','Arnkov','06122013',1,0),(33,'miekjaep@hotmail.com','miekjaep22','Mie','Kjæp','Jørgensen','12122013',1,0),(34,'olsen3100@gmail.com','kodeord3','Kris','Strandby','Olsen','10012014',1,0),(35,'tyksakken@hotmail.com','lorten1122','chris','gynter','odsing','24012014',0,0),(36,'thechamp2163@gmail.com','ingrid2950','Daniel','','Jensen','06022014',1,0),(37,'ingridi.paridon@gmail.com','gratis','Ingrid','Paridon','Holk','16022014',1,0);
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

-- Dump completed on 2014-02-25 19:20:48
