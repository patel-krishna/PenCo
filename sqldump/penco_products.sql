CREATE DATABASE  IF NOT EXISTS `penco` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `penco`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: penco.mysql.database.azure.com    Database: penco
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `SKU` varchar(50) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `vendor` varchar(100) DEFAULT NULL,
  `url_slug` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `imgSrc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`SKU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('1873','glitter oen set',NULL,NULL,NULL,0.00,NULL),('25615326','Glitter bookmark',NULL,NULL,NULL,0.00,NULL),('26273','bookmark','qqq','qqq','qq',3.99,'qqq'),('6727273','Glitter pen set',NULL,NULL,NULL,0.00,NULL),('SKU123456','Ballpoint Pen','A classic ballpoint pen for smooth writing. Ideal for everyday use.','Stationery World','ballpoint-pen',1.99,'https://muji.ca/cdn/shop/products/tripen_700x.jpg?v=1610035922'),('SKU123890','Eraser Pack','A pack of 3 erasers for erasing pencil marks cleanly and effectively.','Erase It All','eraser-pack-3pcs',1.79,'https://muji.ca/cdn/shop/products/eraser_black_s_700x.jpg?v=1608488412'),('SKU234567','Paper Clips','A pack of 100 stainless steel paper clips for holding documents together.','Office Essentials','paper-clips-100pcs',1.49,'https://img.muji.net/img/item/4550182207842_1260.jpg'),('SKU345678','Sticky Notes','Colorful sticky notes for marking important points and reminders.','Stationery Plus','sticky-notes-red',2.49,'https://muji.ca/cdn/shop/products/labeltab_700x.jpg?v=1618409443'),('SKU456789','File Folders','A set of 5 durable file folders for organizing and storing documents.','FileMaster','file-folders-set5',3.99,'https://muji.ca/cdn/shop/products/4550182110210_org_0496e7c6-d13b-4729-9d1c-c886c1c5a395_1260x1260.jpg?v=1653936312'),('SKU567123','Highlighter Set','A set of 4 colorful highlighters for emphasizing key points in your notes.','Highlighter Haven','highlighter-set-4pcs',3.49,'https://muji.ca/cdn/shop/products/minimarkers1_700x.jpg?v=1614962470'),('SKU567890','Desk Organizer','An elegant desk organizer to keep your workspace tidy and organized.','Organize It All','desk-organizer-black',14.99,'https://muji.ca/cdn/shop/files/4945247380910_org_700x.jpg?v=1686854560'),('SKU789012','Notebook','A high-quality notebook with lined pages, perfect for note-taking and journaling.','Office Supplies Inc.','notebook-2023',5.99,'https://muji.ca/cdn/shop/products/4548076316145_1260_700x.jpg?v=1603397677'),('SKU890123','Correction Tape','White correction tape for neatly fixing mistakes in documents and notes.','Stationery Solutions','correction-tape-white',2.29,'https://muji.ca/cdn/shop/products/correctiontape_700x.jpg?v=1611777244'),('SKU901234','Pencil Set','A set of 12 high-quality pencils for drawing and sketching.','Artistic Creations','pencil-set-12pcs',7.99,'https://muji.ca/cdn/shop/products/12pkcolourpencils_700x.jpg?v=1644510839'),('skuin','hhhh','hhhh','hhh','hhh',10.00,'jhhhh'),('testing`','test','test','test',NULL,1.00,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-08 22:33:07
