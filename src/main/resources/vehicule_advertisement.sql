-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 12, 2023 at 06:15 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vehicule_advertisement`
--

-- --------------------------------------------------------

--
-- Table structure for table `dealer_table`
--

DROP TABLE IF EXISTS `dealer_table`;
CREATE TABLE IF NOT EXISTS `dealer_table` (
  `tier_limit` int(11) NOT NULL,
  `id` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dealer_table`
--

INSERT INTO `dealer_table` (`tier_limit`, `id`, `name`) VALUES
(10, 0x127146fd29c441db8593b4fab2db041d, 'TestDealer2'),
(2, 0x5312652546b74b3f8254655ca7dc29bc, 'TestDealerUpdated'),
(2, 0x5a14a4d59f7c49ec8288fa55f47add3d, 'TestDealer8'),
(1, 0x9da3f07fd392453d902f07d54a2b0c3a, 'TestDealer6'),
(4, 0xaab3235e13e34192b88efbc78aa24282, 'TestDealerUpdated22');

-- --------------------------------------------------------

--
-- Table structure for table `listing_table`
--

DROP TABLE IF EXISTS `listing_table`;
CREATE TABLE IF NOT EXISTS `listing_table` (
  `price` double NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `dealer_id` binary(16) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `state` enum('DRAFT','PUBLISHED') DEFAULT NULL,
  `vehicle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `listing_table`
--

INSERT INTO `listing_table` (`price`, `created_at`, `dealer_id`, `id`, `state`, `vehicle`) VALUES
(4500000, '2023-06-11 19:16:57.224614', 0x9da3f07fd392453d902f07d54a2b0c3a, 0x041bb6fc5c81413f9f8d3c4a1d6e27f8, 'DRAFT', 'Vehicle15'),
(4000000, '2023-06-11 17:01:49.701221', 0x5312652546b74b3f8254655ca7dc29bc, 0x2b70bf5484f14d198adfa0adfbbf08ce, 'PUBLISHED', 'Vehicle7'),
(4500000, '2023-06-11 19:16:01.935601', 0x9da3f07fd392453d902f07d54a2b0c3a, 0x4e402d1ccd14469294df5967d1ff3b06, 'PUBLISHED', 'Vehicle14'),
(45000, '2023-06-11 16:57:56.836817', 0x5312652546b74b3f8254655ca7dc29bc, 0x65e8a508483a4416985d55453f10e100, 'PUBLISHED', 'Vehicle1'),
(4000025, '2023-06-12 18:44:10.862288', 0x5a14a4d59f7c49ec8288fa55f47add3d, 0xbf2951b6bc104c96b3fe47fb02ae7529, 'DRAFT', 'Vehicle17'),
(4500000, '2023-06-11 19:16:15.233595', 0x9da3f07fd392453d902f07d54a2b0c3a, 0xc9d084cff9f94ca890afbb5fa3783985, 'DRAFT', 'Vehicle15'),
(25000, '2023-06-11 17:00:07.709220', 0x5312652546b74b3f8254655ca7dc29bc, 0xea48318c03dd472c82f337a54f2eefce, 'DRAFT', 'Vehicle1'),
(4500000, '2023-06-11 17:00:47.083609', 0x5312652546b74b3f8254655ca7dc29bc, 0xfad4d0a63fb04e52b9354deba531705a, 'DRAFT', 'Vehicle5');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
