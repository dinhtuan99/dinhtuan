-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2018 at 06:39 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fastfood_test`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(10) NOT NULL,
  `name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(10) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `name`, `quantity`, `price`) VALUES
(1, 'Gà rán', 95, 35000),
(3, 'Cánh gà giòn ', 87, 20000),
(4, 'Đùi gà truyền thống', 98, 35000),
(5, 'Cơm phi-lê gà giòn', 98, 41000),
(6, 'Cơm gà viên sốt Hàn', 99, 41000),
(7, 'Burger gà giòn', 100, 45000),
(8, 'Burger tôm', 100, 41000),
(9, 'Cơm gà truyền thống', 100, 41000),
(10, 'Cơm gà rán giòn cay', 100, 41000),
(11, 'Khoai tây chiên', 90, 14000),
(15, 'Kem ốc quế', 100, 5000),
(16, 'Nước lọc 500ml', 100, 15000);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `times` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `total` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `times`, `total`, `employee_id`) VALUES
(1, '2018-11-29 00:34:57', 90000, 1),
(2, '2018-11-29 00:35:47', 55000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders_detail`
--

CREATE TABLE `orders_detail` (
  `item_id` int(10) NOT NULL,
  `order_id` int(10) NOT NULL,
  `quantity` int(10) NOT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `orders_detail`
--

INSERT INTO `orders_detail` (`item_id`, `order_id`, `quantity`, `price`) VALUES
(0, 100, 12, 0),
(3, 100, 12, 20000),
(1, 100, 101, 35000),
(1, 100, 1, 35000),
(3, 100, 1, 20000),
(1, 100, 1, 35000),
(7, 100, 2, 45000),
(11, 100, 10, 14000),
(1, 1, 1, 35000),
(1, 1, 1, 35000),
(3, 1, 1, 20000),
(0, 1, 1, 0),
(0, 1, 1, 0),
(1, 2, 1, 35000),
(3, 2, 1, 20000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `username` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `passwords` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `passwords`, `name`, `email`, `address`, `role`) VALUES
(1, 'salesman1', 'salesman1', 'Steven Jobs', 'richkid@gmail.com', 'VN', 'Nhân Viên Bán Hàng'),
(2, 'salesman2', 'salesman2', 'Jack Ma', 'chinano1@gmail.com', 'VN', 'Nhân Viên Bán Hàng'),
(3, 'salesman3', 'salesman3', 'Ronaldo', 'ronaldodeptrai@gmail.com', 'VN', 'Nhân Viên Bán Hàng'),
(0, 'admin', 'admin', 'trideptrai', 'minhtripro1999@gmail.com', 'pop', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
