-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 06, 2023 at 07:37 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shareplate`
--

-- --------------------------------------------------------

--
-- Table structure for table `Bookings`
--

CREATE TABLE `Bookings` (
  `bid` int(11) NOT NULL,
  `donation_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `bstatus` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Bookings`
--

INSERT INTO `Bookings` (`bid`, `donation_id`, `receiver_id`, `qty`, `bstatus`) VALUES
(25346, 6, 9, 50, 'Booked'),
(25347, 8, 9, 100, 'Booked'),
(25348, 8, 15, 300, 'Booked'),
(25349, 7, 15, 500, 'Booked'),
(25350, 6, 27, 50, 'Booked'),
(25351, 8, 24, 100, 'Booked'),
(25353, 9, 9, 10, 'Booked'),
(25354, 9, 9, 20, 'Booked'),
(25355, 12, 9, 50, 'Booked'),
(25356, 11, 15, 100, 'Booked'),
(25357, 11, 9, 10, 'Booked'),
(25358, 13, 9, 50, 'Booked'),
(25359, 15, 9, 2, 'Booked'),
(25360, 32, 9, 50, 'Cancelled'),
(25364, 32, 9, 50, 'Cancelled'),
(25365, 30, 9, 5, 'Cancelled'),
(25366, 18, 9, 5, 'Cancelled'),
(25367, 30, 9, 2, 'Cancelled'),
(25368, 16, 9, 5, 'Cancelled'),
(25369, 32, 9, 10, 'Cancelled'),
(25370, 32, 9, 50, 'Cancelled'),
(25371, 18, 9, 10, 'Cancelled'),
(25372, 16, 9, 5, 'Cancelled'),
(25376, 32, 9, 10, 'Cancelled'),
(25377, 18, 9, 5, 'Booked'),
(25378, 16, 9, 2, 'Cancelled'),
(25379, 34, 36, 2, 'Booked'),
(25380, 34, 36, 2, 'Booked'),
(25381, 36, 42, 3, 'Booked'),
(25382, 35, 42, 1, 'Booked'),
(25383, 33, 9, 5, 'Booked'),
(25384, 37, 36, 23, 'Booked'),
(25385, 37, 36, 23, 'Booked'),
(25386, 37, 36, 21, 'Booked'),
(25387, 32, 36, 25, 'Booked'),
(25388, 33, 36, 95, 'Booked'),
(25389, 37, 36, 2, 'Booked'),
(25390, 36, 36, 1, 'Booked'),
(25391, 37, 36, 2, 'Booked'),
(25392, 37, 36, 2, 'Booked'),
(25393, 34, 36, 1, 'Booked'),
(25394, 36, 36, 1, 'Booked'),
(25395, 36, 36, 1, 'Booked'),
(25396, 32, 36, 1, 'Booked'),
(25397, 32, 36, 45, 'Booked'),
(25398, 41, 9, 3, 'Cancelled'),
(25399, 41, 9, 1, 'Cancelled'),
(25400, 41, 9, 1, 'Cancelled'),
(25401, 41, 9, 3, 'Cancelled'),
(25402, 41, 9, 2, 'Picked'),
(25403, 41, 9, 1, 'Picked'),
(25404, 41, 9, 2, 'Booked');

-- --------------------------------------------------------

--
-- Table structure for table `donate`
--

CREATE TABLE `donate` (
  `id` int(11) NOT NULL,
  `food_type` varchar(100) NOT NULL,
  `dietary` varchar(100) NOT NULL,
  `item_name` varchar(50) NOT NULL,
  `qty` int(11) NOT NULL,
  `location` varchar(300) NOT NULL,
  `contact` varchar(10) NOT NULL,
  `donor_id` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `date` varchar(50) NOT NULL,
  `time` varchar(50) NOT NULL,
  `remains` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `donate`
--

INSERT INTO `donate` (`id`, `food_type`, `dietary`, `item_name`, `qty`, `location`, `contact`, `donor_id`, `status`, `date`, `time`, `remains`) VALUES
(5, 'Meals', 'Non Vegetarian', 'Chicken Biryani ', 50, 'Mala', '9876907856', 10, 'submitted', '01-06-2023', '23:49:52', 50),
(6, 'Meals', 'Vegetarian', 'Sadhya', 100, 'Manjaly, Paravoor', '9694416469', 10, 'Finished', '11-06-2023', '19:44', 0),
(7, 'Fruits', 'Vegan', 'Apple', 500, 'Mala, Thrissur', '9945324577', 10, 'Finished', '11-06-2023', '19:47', 0),
(8, 'Pastries', 'Non Vegetarian', 'Cake', 500, 'Madathumpady, Mala', '9847675460', 10, 'Finished', '11-06-2023', '00:45:18', 0),
(9, 'Bread', 'Vegetarian', 'Sandwich', 200, 'Manjaly, Paravoor', '9747266718', 10, 'Some Remains', '11-06-2023', '09:35:36', 170),
(10, 'Meals', 'Non Vegetarian', 'Rice + Chicken Curry', 100, 'Kodakara, Thrissur', '9847654320', 10, 'submitted', '20-06-2023', '20:50:29', 100),
(11, 'Meals', 'Vegetarian', 'Biriyani ', 200, 'Kodungallur, Thrissur', '8567453409', 10, 'Some Remains', '20-06-2023', '20:51:34', 90),
(12, 'Snacks', 'Non Vegetarian', 'Chicken Roll', 300, 'Irinjalakuda, Thrissur', '9747566324', 10, 'Some Remains', '20-06-2023', '20:52:56', 250),
(13, 'Meals', 'Non Vegetarian', 'Chicken Rice', 100, 'Kodungallur, Thrissur', '8974534245', 10, 'Some Remains', '20-06-2023', '06:43:04', 50),
(40, 'Meals', 'Vegetarian', 'vegitable biriyani ', 12, 'Madathumpady, Mala', '1234567890', 10, 'submitted', '05-08-2023', '11:19:30', 12),
(41, 'Snacks', 'Non Vegetarian', 'burger ', 6, 'Mala, Thrissur', '7909137548', 10, 'Some Remains', '06-08-2023', '10:23:09', 1);

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `rate` varchar(10) NOT NULL,
  `feedback` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`id`, `uid`, `rate`, `feedback`) VALUES
(1, 10, '3', 'User Friendly. '),
(2, 10, '2', '');

-- --------------------------------------------------------

--
-- Table structure for table `sponsor`
--

CREATE TABLE `sponsor` (
  `id` int(11) NOT NULL,
  `sponsor_id` int(3) NOT NULL,
  `receiver_id` int(3) NOT NULL,
  `amount` float NOT NULL,
  `description` varchar(100) NOT NULL,
  `date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sponsor`
--

INSERT INTO `sponsor` (`id`, `sponsor_id`, `receiver_id`, `amount`, `description`, `date`) VALUES
(1, 10, 15, 500, 'Snacks', '09-06-2023'),
(2, 10, 27, 10000, 'Lunch', '09-06-2023'),
(3, 10, 22, 25000, 'Education to Tribal Child', '10-06-2023'),
(4, 10, 24, 5000, 'Food', '10-06-2023'),
(5, 10, 22, 500, 'Snacks', '20-06-2023'),
(6, 10, 22, 2000, 'Breakfast', '26-06-2023');

-- --------------------------------------------------------

--
-- Table structure for table `Test`
--

CREATE TABLE `Test` (
  `id` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `phone` int(11) NOT NULL,
  `eduction` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Test`
--

INSERT INTO `Test` (`id`, `name`, `phone`, `eduction`, `email`) VALUES
(1, 'Dhipin M Kumar', 1234567890, 'Graduation', 'dhipinmkumar2000@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `address` varchar(500) NOT NULL,
  `pincode` varchar(6) NOT NULL,
  `email` varchar(80) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `utype` varchar(20) NOT NULL,
  `r_type` varchar(30) NOT NULL,
  `upi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `phone`, `address`, `pincode`, `email`, `pwd`, `utype`, `r_type`, `upi`) VALUES
(8, 'Ivaan', '9747256832', 'Kizhakkevalappil House\nPanthalloor\nNellayi POST\nThrissur District ', '680305', 'ivaan2022@gmail.com', '123', 'donor', 'd', 'NIL'),
(9, 'Abhishek ', '8569085424', 'Kalarikkal House\nMadathumpady POST\nPoyya Via\nThrissur District ', '680733', 'testr@gmail.com', '123', 'receiver', 'Normal User', 'NIL'),
(10, 'Dhipin M K', '8547095454', 'Mangadath House\nMadathumpady PO\nMala, Poyya Via\nThrissur Dist', '680732', 'testd@gmail.com', '123', 'donor', 'd', 'NIL'),
(11, 'Anu', '9845263870', 'sthbnjjusf\nggjjivsetuknb\ndgbjjiojvss\nfghjj', '670987', 'anu@gmail.com', '111', 'donor', 'd', 'NIL'),
(12, 'Dhipin M K ', '7909145862', 'Mangadath House\nMadathumpady POST\nPoyya, Thrissur ', '680733', 'dhipinmkumar2000@gmail.com', '123', 'donor', 'd', 'NIL'),
(14, 'Joby Thomas', '9652148636', 'Palatty House\nPoyya\nMala, Thrissur ', '680302', 'test1d@gmail.com', '111', 'donor', 'd', 'NIL'),
(15, 'Snehatheeram Children Care', '8547095454', 'Haritha Nagar\nMuzris Nature Park Road \nKodungallur\nThrissur District ', '680736', 'snehatheeram2020@gmail.com', '222', 'receiver', 'Orphanage', 'devikrishna908@oksbi'),
(22, 'Yuva Social Movement', '9446163045', 'Thottamoola, Nenmenikunnu Post\nSulthan Bathery, Wayanad District \nKerala, India', '673592', 'yuvasocial2000@gmail.com', '111', 'receiver', 'Charity Organization', 'yuva1996@okicici '),
(23, 'ATMA Foundation', '8547095454', 'Surya Gardens, Paliyam Rd, Near Vadakke Stand, Thrissur, Kerala', '680001', 'mail@atmafoundation.org', 'test', 'receiver', 'Charity Organization', 'atma4@oksbi'),
(24, 'Rehoboth Girls Orphanage', '7034845430', 'Veembu Road, Nellikunnu,\r\nThrissur District\r\nKerala, India', '680005', 'rehobothgirls@gmail.com', '123', 'receiver', 'Orphanage', 'rehoboth@okicici'),
(25, 'Sanjeevani Balika Sadanam', '8956743421', 'Urakam, Thrissur District\r\nKerala, India', '680562', 'sanjeevanibalikasadanam@gmail.com', '233', 'receiver', 'Orphanage', 'sanjeevani98@okhdfc'),
(26, 'Ananta Living', '9656432920', 'Builtech Foundation, \r\nChittur Road, Palakkad District, Kerala State, India', '678013', 'anantaliving2000@gmail.com', '222', 'receiver', 'Old Age Home', 'ananta@okicici'),
(27, 'Santhwanam Old Age Home', '9878654676', 'SH51, Ashtamichira,\r\nThrissur District, Kerala, India\r\n', '680731', 'santwanamseniorliving@gmail.com', 'test', 'receiver', 'Old Age Home', 'santwanam98@oksbi'),
(32, 'Abhi', '5555555555', 'hhh', '123456', 'abhi@gmail.com', 'hello', 'donor', 'd', 'NIL'),
(33, 'Abhi', '7909137548', 'Mangadath maath', '680733', 'althafmu22@gmail.com', '1234', 'donor', 'd', 'NIL'),
(34, 'althaf', '7909137548', 'mangafsbsjs', '680733', 'althafmu2@gmail.com', '1234', 'receiver', 'Normal User', 'NIL'),
(35, 'Adhithya', '9497423112', 'ggg', '123456', 'adhi@gmail.com', 'hello', 'donor', 'd', 'NIL'),
(36, 'Reshmi', '9497423113', 'aaa', '963852', 'reshmign@gmail.com', 'hello', 'receiver', 'Charity Organizations', ''),
(37, 'Dhipin M Kumar', '7909137548', 'gshsbsbsbs', '680977', 'testqw@gmail.com', '123', 'receiver', 'Normal User', 'NIL'),
(38, 'Dhipin M Kumar', '9496416964', 'gahshsjs', '680733', 'test12357@gmail.com', '123', 'donor', 'd', 'NIL'),
(39, 'okhdkh', '8686868622', 'jfjgdohfohc', '758552', 'kgxjgs.@gmail.com', 'qwe', 'donor', 'd', 'NIL'),
(40, 'gshsh', '9496416964', 'bshsys', '680733', 'tesgs@gmail.com', '123', 'donor', 'd', 'NIL'),
(41, 'xy', '9495125821', 'asdf', '683520', 'x@gmail.com', 'x123', 'donor', 'd', 'NIL'),
(42, 'Y', '9495125821', 'ddf', '685512', 'y@fg.co', 'y123', 'receiver', 'Orphanages', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Bookings`
--
ALTER TABLE `Bookings`
  ADD PRIMARY KEY (`bid`);

--
-- Indexes for table `donate`
--
ALTER TABLE `donate`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sponsor`
--
ALTER TABLE `sponsor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Test`
--
ALTER TABLE `Test`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Bookings`
--
ALTER TABLE `Bookings`
  MODIFY `bid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25405;

--
-- AUTO_INCREMENT for table `donate`
--
ALTER TABLE `donate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `rating`
--
ALTER TABLE `rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sponsor`
--
ALTER TABLE `sponsor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `Test`
--
ALTER TABLE `Test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
