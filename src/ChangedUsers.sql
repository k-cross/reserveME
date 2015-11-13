-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 10, 2015 at 07:22 AM
-- Server version: 10.0.17-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `reserveme`
--

-- --------------------------------------------------------

--
-- Table structure for table `foods`
--

CREATE TABLE `foods` (
  `foodID` int(11) NOT NULL,
  `dishname` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `ordered` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `foods`
--

INSERT INTO `foods` (`foodID`, `dishname`, `category`, `price`, `ordered`) VALUES
(101, 'Sushi', 'Rice', 10, 20),
(102, 'Pad Thai', 'Noodles', 6.5, 20),
(103, 'Yakitori', 'Grilled', 8, 10),
(104, 'Ramen', 'Noodles', 5, 30),
(105, 'Curry', 'Curry', 5, 40),
(106, 'Soon Dubu', 'Soup', 8.5, 20),
(107, 'Sam Gae Tang', 'Soup', 14, 5),
(108, 'Spicy Chicken', 'Meat', 8, 5),
(109, 'Chow Mein', 'Noodles', 5.5, 30),
(110, 'Ice Cream', 'Dessert', 4, 20);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderID` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `foodID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderID`, `userID`, `foodID`) VALUES
(1, 101, 101),
(2, 102, 102),
(3, 103, 103),
(4, 104, 104),
(5, 105, 105),
(6, 106, 106),
(7, 107, 107),
(8, 108, 108),
(9, 109, 109),
(10, 110, 110);

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservationID` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `tableID` int(11) DEFAULT NULL,
  `orderID` int(11) DEFAULT NULL,
  `people` int(11) DEFAULT NULL,
  `resDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservationID`, `userID`, `tableID`, `orderID`, `people`, `resDate`) VALUES
(1, 103, 3, 10, 4, '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `tables`
--

CREATE TABLE `tables` (
  `tableID` int(11) NOT NULL,
  `people` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tables`
--

INSERT INTO `tables` (`tableID`, `people`) VALUES
(1, 0),
(2, 1),
(3, 2),
(4, 3),
(5, 4),
(6, 5),
(7, 6),
(8, 7),
(9, 8),
(10, 9),
(11, 10),
(12, 11),
(13, 12),
(14, 13),
(15, 14),
(16, 15);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `uname` varchar(50) DEFAULT NULL,
  `pw` varchar(50) DEFAULT NULL,
  `usertype` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `name`, `uname`, `pw`, `usertype`) VALUES
(101, 'Chiu', 'chiu', 'qweasdzxc', 'manager'),
(102, 'Bernie Sanders', 'burn', 'qweasdzxc', 'customer'),
(103, 'George Bush', 'JB', 'qweasdzxc', 'staff'),
(104, 'Barry Obama', 'OG', 'qweasdzxc', 'staff'),
(105, 'Ronald Regan', 'R&R', 'qweasdzxc', 'staff'),
(106, 'Teddy Roosevelt', 'TeddyBear', 'qweasdzxc', 'customer'),
(107, 'George Washington', 'jackLondon', 'qweasdzxc', 'customer'),
(108, 'Hillary Clinton', 'hillaryDuff', 'qweasdzxc', 'customer'),
(109, 'Some One', 'SomeTwo', 'qweasdzxc', 'customer'),
(110, 'Jeb Bush', 'notGonnaWin', 'qweasdzxc', 'customer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `foods`
--
ALTER TABLE `foods`
  ADD PRIMARY KEY (`foodID`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `foodID` (`foodID`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservationID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `tableID` (`tableID`),
  ADD KEY `orderID` (`orderID`);

--
-- Indexes for table `tables`
--
ALTER TABLE `tables`
  ADD PRIMARY KEY (`tableID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `uname` (`uname`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `foods`
--
ALTER TABLE `foods`
  MODIFY `foodID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservationID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tables`
--
ALTER TABLE `tables`
  MODIFY `tableID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`foodID`) REFERENCES `foods` (`foodID`);

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`tableID`) REFERENCES `tables` (`tableID`),
  ADD CONSTRAINT `reservations_ibfk_3` FOREIGN KEY (`orderID`) REFERENCES `orders` (`orderID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
