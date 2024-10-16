-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 30 avr. 2024 à 13:00
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `testingdbs15`
--

-- --------------------------------------------------------

--
-- Structure de la table `consultant`
--

CREATE TABLE `consultant` (
  `consultant_id` bigint(20) NOT NULL,
  `clientnumber` bigint(20) DEFAULT NULL,
  `consultant_address` varchar(255) DEFAULT NULL,
  `consultant_email` varchar(255) DEFAULT NULL,
  `consultant_firstname` varchar(255) DEFAULT NULL,
  `consultant_lastname` varchar(255) DEFAULT NULL,
  `consultant_phonenumber` bigint(20) DEFAULT NULL,
  `date_birth` date DEFAULT NULL,
  `date_last_meeet` varchar(255) DEFAULT NULL,
  `date_last_meet` date DEFAULT NULL,
  `duree_totale_reunions` bigint(20) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `hire_date` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `nbr_affectation` bigint(20) DEFAULT NULL,
  `nbr_canceled_meet` bigint(20) DEFAULT NULL,
  `nbr_first_meet` bigint(20) DEFAULT NULL,
  `nbr_meet` bigint(20) DEFAULT NULL,
  `nbr_passed_meet` bigint(20) DEFAULT NULL,
  `skill` varchar(255) DEFAULT NULL,
  `portfolio_potfolio_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `consultant`
--

INSERT INTO `consultant` (`consultant_id`, `clientnumber`, `consultant_address`, `consultant_email`, `consultant_firstname`, `consultant_lastname`, `consultant_phonenumber`, `date_birth`, `date_last_meeet`, `date_last_meet`, `duree_totale_reunions`, `gender`, `hire_date`, `image`, `nbr_affectation`, `nbr_canceled_meet`, `nbr_first_meet`, `nbr_meet`, `nbr_passed_meet`, `skill`, `portfolio_potfolio_id`) VALUES
(1, 20706900, 'ariana', 'chadha@gmail.com', 'chadha', 'jaafra', 15555555555, '2017-04-05', 'last week', '2024-04-09', 0, 'female', '2021-04-14', NULL, 4, 4, 6, 15, 12, 'ONE_STAR', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `consultant`
--
ALTER TABLE `consultant`
  ADD PRIMARY KEY (`consultant_id`),
  ADD KEY `FKh2axug3q56u2o1foo0l0eyu5l` (`portfolio_potfolio_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `consultant`
--
ALTER TABLE `consultant`
  MODIFY `consultant_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `consultant`
--
ALTER TABLE `consultant`
  ADD CONSTRAINT `FKh2axug3q56u2o1foo0l0eyu5l` FOREIGN KEY (`portfolio_potfolio_id`) REFERENCES `portfolio` (`potfolio_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
