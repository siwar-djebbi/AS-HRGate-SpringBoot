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
(5, NULL, b'0', 'johndoe@examplle.com', b'1', 'sw', 'djebbi', '$2a$10$TEQm5q.n9cZM72Of1Li.b.Hzug0RqJi6HIf2ogHzd.pJbMcYCD/Ym', NULL, NULL),
(17, NULL, b'0', 'samare@gmail.com', b'0', NULL, NULL, '$2a$10$54sut8YkWJcYsTj3llnfe.7brCg4Qnwfg/ESgEe5uV8OIivZykwqC', NULL, NULL),
(18, NULL, b'0', 'john.dooiiiie@example.com', b'1', 'Johnn', 'Doe', '$2a$10$iUhzj82fOF5aRpUHG//6MunuYJuAXwEhbDqM/PgUgzhjAu0ybAyye', NULL, NULL),
(21, NULL, b'0', 'joh@example.com', b'1', 'Johnnn', 'Dore', '$2a$10$YaHRQKDLk2coMAuSwxqlGu1EQr/rOmT2cHRz1WJekIlCRXHJYppeW', NULL, NULL),
(22, NULL, b'0', 'op@gmail.com', b'1', 'siwwwww', 'Do', '$2a$10$83c7CMJgSy59ePOUUSc9v.yI.AgtkfjNgGfYQcSLRxu5Rdm3vx5gK', NULL, NULL),
(23, NULL, b'0', 'oaap@gmail.com', b'1', 'ayouta', 'Do', '$2a$10$NdtCd3UsbVBYDTpSnlqqJuR788kpQq98L7BkXfoge4eDG5u8xGBMS', NULL, NULL),
(25, NULL, b'0', 'srt@gmail.com', b'1', 'si', 'war', '$2a$10$0G.tIdAR5kEYinwsevVi4ewP8AjmxHy/TnY5hC4YCY7Aw8koAXI0q', NULL, NULL),
(26, NULL, b'0', 'srtw@gmail.com', b'1', 'siw', 'wawr', '$2a$10$RFS2EFmmvoJBU8TxBqPdLOy.T6dkHXXsOY541nktf2HMRSMjzqAre', NULL, NULL),
(27, NULL, b'0', 'srtwr@gmail.com', b'1', 'siwa', 'wawrr', '$2a$10$jlyAEqyznKEFAMhuVFPrLuL4EshLz0hhiAf/A5kDntoWdCpnDXR/u', NULL, NULL),
(28, NULL, b'0', 'srrtwr@gmail.com', b'1', 'sitwwwa', 'wawrr', '$2a$10$Nsi5YE.1d7Mqm4NsTPyA.eC/9HEt7IFIIsZ/xGA41si8lyGXqJ1by', NULL, NULL),
(29, NULL, b'0', 'srrtwfffr@gmail.com', b'1', 'sitzewwwa', 'wawdddrr', '$2a$10$Vtog5HjUsKomug7tX7J2X.OkubUj.4ixBWDFg0anBmG1Ci.ZNtHue', NULL, NULL),
(30, NULL, b'0', 'srrtwqqfffr@gmail.com', b'1', 'sitzewqqwwa', 'wawqqdddrr', '$2a$10$9X2y7ZG.bwUfzno7PDrzJOYqB.DPewMMizlgFXZJfraizCAZQgwka', NULL, NULL),
(33, NULL, b'0', 'ess@gmail.com', b'1', 'Essai', 'ess', '$2a$10$Ex06hwjHIjEUJSAl0Um3TeiCN4cnJlPaqDKhtmaU/F8hX8t1mn94q', NULL, NULL),
(34, NULL, b'0', 'michem@gmail.com', b'1', 'Mich', 'Em', '$2a$10$h1pmb0hy4WMrR5ZtWK9QOe7HT6pt7w9lq70/DUQNFsYwWl6zGbLLO', NULL, NULL),
(35, NULL, b'0', 'rackel@gmail.com', b'1', 'ra', 'ckel', '$2a$10$AGzAshQ0zbOUsC1FZl1rWO6.hRIJxLE3k69qroz3mYJMxhS3/VyjO', NULL, NULL);

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
  MODIFY `mat_pers` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
