-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: market
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `produtos`
--

/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` (`id`, `categoria`, `nome`, `preco`, `imagem_url`) VALUES (1,'Bebidas','Café especial',29.90,'/imagens/produtos/cafe.jpg'),(2,'Bebidas','Chá de camomila',12.90,'/imagens/produtos/cha-camomila.jpg'),(3,'Mercearia','Granola artesanal',18.50,'/imagens/produtos/granola.jpg'),(4,'Mercearia','Mel de flores',24.90,'/imagens/produtos/mel.jpg'),(5,'Mercearia','Biscoito integral',8.90,'/imagens/produtos/biscoito-integral.jpg'),(6,'Bebidas','Suco de uva',16.90,'/imagens/produtos/suco-uva.jpg'),(7,'Mercearia','Arroz branco 1 kg',7.90,'/imagens/produtos/arroz.jpg'),(8,'Mercearia','Feijão carioca 1 kg',9.90,'/imagens/produtos/feijao.jpg'),(9,'Mercearia','Azeite de oliva 500 ml',32.90,'/imagens/produtos/azeite.jpg'),(10,'Mercearia','Macarrão espaguete 500 g',5.90,'/imagens/produtos/macarrao.jpg'),(11,'Mercearia','Açúcar cristal 1 kg',4.90,'/imagens/produtos/acucar.jpg'),(12,'Laticínios','Leite integral 1 L',5.50,'/imagens/produtos/leite.jpg'),(13,'Mercearia','Farinha de trigo 1 kg',6.90,'/imagens/produtos/farinha-trigo.jpg'),(14,'Laticínios','Manteiga 200 g',12.90,'/imagens/produtos/manteiga.jpg'),(15,'Laticínios','Queijo muçarela 300 g',18.90,'/imagens/produtos/queijo.jpg');
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-14  2:08:16
