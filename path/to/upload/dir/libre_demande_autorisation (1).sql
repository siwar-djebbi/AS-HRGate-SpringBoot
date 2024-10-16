-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 20, 2024 at 12:51 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hrsiwar`
--

-- --------------------------------------------------------

--
-- Table structure for table `libre_demande_autorisation`
--

CREATE TABLE `libre_demande_autorisation` (
  `id_libre_dem_auto` bigint(20) NOT NULL,
  `auto_duration` varchar(255) DEFAULT NULL,
  `auto_enddate` date DEFAULT NULL,
  `auto_endhour` int(11) DEFAULT NULL,
  `auto_endmin` int(11) DEFAULT NULL,
  `auto_requestdate` date DEFAULT NULL,
  `auto_startdate` date DEFAULT NULL,
  `auto_starthour` int(11) DEFAULT NULL,
  `auto_startmin` int(11) DEFAULT NULL,
  `code_soc` bigint(20) DEFAULT NULL,
  `text_demande` varchar(255) DEFAULT NULL,
  `mat_pers` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `libre_demande_autorisation`
--

INSERT INTO `libre_demande_autorisation` (`id_libre_dem_auto`, `auto_duration`, `auto_enddate`, `auto_endhour`, `auto_endmin`, `auto_requestdate`, `auto_startdate`, `auto_starthour`, `auto_startmin`, `code_soc`, `text_demande`, `mat_pers`) VALUES
(1, '4h 45m', NULL, 17, 0, '2024-08-06', '2024-08-08', 12, 15, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `libre_demande_autorisation`
--
ALTER TABLE `libre_demande_autorisation`
  ADD PRIMARY KEY (`id_libre_dem_auto`),
  ADD KEY `FKdmaryd8eilyolpodirqy8s8i7` (`mat_pers`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `libre_demande_autorisation`
--
ALTER TABLE `libre_demande_autorisation`
  MODIFY `id_libre_dem_auto` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `libre_demande_autorisation`
--
ALTER TABLE `libre_demande_autorisation`
  ADD CONSTRAINT `FKdmaryd8eilyolpodirqy8s8i7` FOREIGN KEY (`mat_pers`) REFERENCES `user` (`mat_pers`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
