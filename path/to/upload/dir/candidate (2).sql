-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2024 at 12:56 PM
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
-- Database: `tttttttttt`
--

-- --------------------------------------------------------

--
-- Table structure for table `candidate`
--

CREATE TABLE `candidate` (
  `id_candidate` bigint(20) NOT NULL,
  `cv` longblob DEFAULT NULL,
  `cv_file_name` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `email_cand` varchar(255) DEFAULT NULL,
  `experience_cand` varchar(255) DEFAULT NULL,
  `first_name_cand` varchar(255) DEFAULT NULL,
  `last_name_cand` varchar(255) DEFAULT NULL,
  `skills_cand` varchar(255) DEFAULT NULL,
  `post_titlec` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `candidate`
--

INSERT INTO `candidate` (`id_candidate`, `cv`, `cv_file_name`, `education`, `email_cand`, `experience_cand`, `first_name_cand`, `last_name_cand`, `skills_cand`, `post_titlec`) VALUES
(1, NULL, NULL, 'Esprit', 'siwardjebbi6@gmail.com', '3', 'Siwar', 'Djebbi', '', NULL),
(2, NULL, NULL, 'Esprit', 'siwardjebbi6@gmail.com', '3', 'Siwar', 'Djebbi', '', NULL),
(3, NULL, NULL, 'ihec', 'hrAdmin@email.com', '2', 'eya', 'dj', '', NULL),
(4, NULL, NULL, 'Esprit', 'siwardjebbi6@gmail.com', '3', 'Siwar', 'Djebbi', '', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `candidate`
--
ALTER TABLE `candidate`
  ADD PRIMARY KEY (`id_candidate`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `candidate`
--
ALTER TABLE `candidate`
  MODIFY `id_candidate` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
