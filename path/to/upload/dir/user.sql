-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2024 at 02:46 PM
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
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `mat_pers` bigint(20) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salary` float DEFAULT NULL,
  `telephone` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`mat_pers`, `birth_date`, `deleted`, `email`, `enabled`, `first_name`, `last_name`, `password`, `salary`, `telephone`) VALUES
(1, NULL, b'0', 'siwar@gmail.com', b'0', 'siwar', 'djebbi', 'siwar', NULL, NULL),
(2, NULL, b'0', 'eya@gmail.com', b'0', 'eya', 'djebbi', 'eyaa', NULL, NULL),
(3, NULL, b'0', 'john.doe@example.com', b'1', 'John', 'Doe', 'your_password_here', NULL, NULL),
(4, NULL, b'0', 'john.de@example.com', b'1', 'John', 'De', '$2a$10$7DYgohTSo0EMAUkgRbD0y.l3YFNaHa78PWBU9MViNOjWhNTRmIpTK', NULL, NULL),
(5, NULL, b'0', 'johndoe@examplle.com', b'1', 'siwaa', 'djebbi', '$2a$10$fdKZ6H9p2Vdyf/FD.cmNs.GA3I28izFytoZls4jiri29QxPtx.41W', NULL, NULL),
(6, NULL, b'0', 'johndoee@example.com', b'1', 'siwaaaa', 'Doee', '$2a$10$pUDgi8nzIfgG2RZ1.Le/NeDNS0fdynFOwwQ1na.My1dEFSlih5g6e', NULL, NULL),
(7, NULL, b'0', 'johndoee@example.com', b'1', 'sr', 'Doee', '$2a$10$SF./ZHJKhE14MfbqrXfnjePSf4Er3LCN.WQeSITpuFUCZ44hPv.oy', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`mat_pers`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `mat_pers` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
