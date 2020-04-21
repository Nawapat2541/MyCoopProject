-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 17, 2017 at 05:16 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `borrow`
--

CREATE TABLE `borrow` (
  `borrow_id` varchar(8) NOT NULL,
  `equipment_id` varchar(8) NOT NULL,
  `borrow_amout` int(11) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `user_borrowid` varchar(8) NOT NULL,
  `date_start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_end` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `location` varchar(1024) NOT NULL,
  `contact_addr` varchar(1024) NOT NULL,
  `status` varchar(1) NOT NULL,
  `sum` int(11) NOT NULL,
  `time_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user_create` varchar(32) NOT NULL,
  `time_update` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user_update` varchar(32) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `borrow`
--

INSERT INTO `borrow` (`borrow_id`, `equipment_id`, `borrow_amout`, `reason`, `user_borrowid`, `date_start`, `date_end`, `location`, `contact_addr`, `status`, `sum`, `time_create`, `user_create`, `time_update`, `user_update`) VALUES
('333', '123', 2, '', '', '2017-03-17 04:16:35', '0000-00-00 00:00:00', '', '', '', 0, '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `borrow`
--
ALTER TABLE `borrow`
  ADD PRIMARY KEY (`equipment_id`,`borrow_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
