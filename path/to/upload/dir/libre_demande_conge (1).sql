-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 20, 2024 at 12:50 AM
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
-- Table structure for table `libre_demande_conge`
--

CREATE TABLE `libre_demande_conge` (
  `id_libre_dem_conge` bigint(20) NOT NULL,
  `reponse_chef` varchar(255) DEFAULT NULL,
  `reponsedrh` varchar(255) DEFAULT NULL,
  `codem` int(11) DEFAULT NULL,
  `code_soc` bigint(20) DEFAULT NULL,
  `conge_daysleft` int(11) DEFAULT NULL,
  `conge_enddate` date DEFAULT NULL,
  `conge_requestdate` date DEFAULT NULL,
  `conge_startdate` date DEFAULT NULL,
  `conge_temps_debut` int(11) DEFAULT NULL,
  `conge_temps_fin` int(11) DEFAULT NULL,
  `conge_type` varchar(255) DEFAULT NULL,
  `txt_chef` varchar(255) DEFAULT NULL,
  `txt_dem` varchar(255) DEFAULT NULL,
  `txt_reponse` varchar(255) DEFAULT NULL,
  `valide` varchar(255) NOT NULL DEFAULT 'I',
  `mat_pers` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `libre_demande_conge`
--

INSERT INTO `libre_demande_conge` (`id_libre_dem_conge`, `reponse_chef`, `reponsedrh`, `codem`, `code_soc`, `conge_daysleft`, `conge_enddate`, `conge_requestdate`, `conge_startdate`, `conge_temps_debut`, `conge_temps_fin`, `conge_type`, `txt_chef`, `txt_dem`, `txt_reponse`, `valide`, `mat_pers`) VALUES
(1, 'en attente', 'oui', 12, 125478, 12, '2024-07-31', '2024-07-24', '2024-07-27', 0, NULL, 'maladie', 'ok', 'ok', 'ok', 'V', 29),
(2, 'no', 'no', 144, 44444, 6, '2024-08-07', '2024-07-23', '2024-07-31', NULL, NULL, 'vacances', 'svp', 'svp', 'non', 'I', NULL),
(3, NULL, NULL, NULL, NULL, NULL, '2024-08-28', NULL, '2024-08-21', NULL, NULL, 'FMLA', NULL, NULL, NULL, 'I', 5),
(4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-08-20', NULL, NULL, NULL, NULL, NULL, NULL, 'I', NULL),
(5, NULL, NULL, NULL, 22222, NULL, '2024-08-20', NULL, '2024-08-15', NULL, NULL, NULL, '', 'a', '', 'I', NULL),
(6, NULL, NULL, NULL, 123, 16, '2024-08-23', NULL, '2024-08-14', NULL, NULL, NULL, '', 'abc', '', 'V', NULL),
(7, NULL, NULL, NULL, 22222, NULL, '2024-08-08', NULL, '2024-08-10', NULL, NULL, NULL, '', 'b', '', 'I', NULL),
(9, NULL, NULL, NULL, 1235, NULL, '2024-08-08', NULL, '2024-08-09', NULL, NULL, NULL, '', 'bbb', '', 'I', NULL),
(13, NULL, NULL, NULL, 123, NULL, '2024-08-14', '2024-08-06', '2024-08-07', NULL, NULL, NULL, '', 'abcc', '', 'I', NULL),
(14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'I', 5),
(15, NULL, NULL, NULL, NULL, 15, '2024-08-22', '2024-08-06', '2024-08-20', NULL, NULL, 'vaaaaaaac', '', 's', '', 'N', 34);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `libre_demande_conge`
--
ALTER TABLE `libre_demande_conge`
  ADD PRIMARY KEY (`id_libre_dem_conge`),
  ADD KEY `FKed47t9v8dxjb4showw5j8umlx` (`mat_pers`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `libre_demande_conge`
--
ALTER TABLE `libre_demande_conge`
  MODIFY `id_libre_dem_conge` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `libre_demande_conge`
--
ALTER TABLE `libre_demande_conge`
  ADD CONSTRAINT `FKed47t9v8dxjb4showw5j8umlx` FOREIGN KEY (`mat_pers`) REFERENCES `user` (`mat_pers`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
