-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2021 at 04:28 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `orsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `documents`
--

CREATE TABLE `documents` (
  `Id` int(250) NOT NULL,
  `Department_To` varchar(100) NOT NULL,
  `Department_From` varchar(100) NOT NULL,
  `Document_Type` varchar(100) NOT NULL,
  `Document_Purpose` varchar(100) NOT NULL,
  `Date_Forwarded` varchar(100) NOT NULL,
  `Date_Received` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `documents`
--

INSERT INTO `documents` (`Id`, `Department_To`, `Department_From`, `Document_Type`, `Document_Purpose`, `Date_Forwarded`, `Date_Received`) VALUES
(1, 'Motorpool', 'Administrator', 'Memorandum', 'Checking for Validation', '05/06/2021', '05/06/2021'),
(2, 'Quality Control', 'Administrator', 'Certificate', 'Signing of Document', '05/06/2021', NULL),
(3, 'Quality Control', 'Administrator', 'Memorandum', 'Checking for Validation', '05/06/2021', NULL),
(4, 'Administrator', 'Motorpool', 'Certificate', 'Signing of Document', '05/06/2021', NULL),
(5, 'Administrator', 'Motorpool', 'Memorandum', 'Checking for Validation', '05/06/2021', NULL),
(6, 'Quality Control', 'Motorpool', 'Certificate', 'Signing of Document', '05/06/2021', NULL),
(7, 'Quality Control', 'Motorpool', 'Memorandum', 'Checking for Validation', '05/06/2021', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `document_type`
--

CREATE TABLE `document_type` (
  `Id` int(50) NOT NULL,
  `Document_Type` varchar(250) NOT NULL,
  `Document_Purpose` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `document_type`
--

INSERT INTO `document_type` (`Id`, `Document_Type`, `Document_Purpose`) VALUES
(1, 'Certificate', 'Signing of Document'),
(2, 'Memorandum', 'Checking for Validation');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Id` int(50) NOT NULL,
  `Department` varchar(250) NOT NULL,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Id`, `Department`, `Username`, `Password`, `Name`) VALUES
(1, 'Administrator', 'admin', 'admin', 'ADMINISTRATOR'),
(2, 'Quality Control', 'AlumarQC', '12345', 'Alumar Bangahon'),
(3, 'Motorpool', 'JerichoM', '12345', 'Jericho Saramosing');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `documents`
--
ALTER TABLE `documents`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `document_type`
--
ALTER TABLE `document_type`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `documents`
--
ALTER TABLE `documents`
  MODIFY `Id` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `document_type`
--
ALTER TABLE `document_type`
  MODIFY `Id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `Id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
