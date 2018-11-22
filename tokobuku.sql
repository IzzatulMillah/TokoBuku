-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 22, 2018 at 05:06 PM
-- Server version: 5.7.23-0ubuntu0.16.04.1-log
-- PHP Version: 7.0.32-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tokobuku`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id` bigint(20) NOT NULL,
  `judul` varchar(100) NOT NULL,
  `tipe_buku` varchar(100) NOT NULL,
  `saldo` int(10) NOT NULL,
  `harga` double NOT NULL,
  `flag_aktif` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id`, `judul`, `tipe_buku`, `saldo`, `harga`, `flag_aktif`) VALUES
(1542620361262, 'The Talking Cat Kitty', '', 23, 123456, 'Y'),
(1542621857470, 'The Harry Potter and The Goblet of Fire', '', 120, 4500000, 'Ya'),
(1542872878888, 'Master Java', 'Edukasi', 200, 150000, 'Ya');

-- --------------------------------------------------------

--
-- Table structure for table `detail_nota`
--

CREATE TABLE `detail_nota` (
  `id_nota` int(10) NOT NULL,
  `judul` varchar(200) NOT NULL,
  `jumlah_buku` int(10) NOT NULL,
  `harga_satuan` double NOT NULL,
  `harga_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_nota`
--

INSERT INTO `detail_nota` (`id_nota`, `judul`, `jumlah_buku`, `harga_satuan`, `harga_total`) VALUES
(1, 'The Harry Potter and The Goblet of Fire', 4, 4500000, 18000000),
(2, 'The Harry Potter and The Goblet of Fire', 2, 4500000, 9000000),
(5, 'The Harry Potter and The Goblet of Fire', 2, 4500000, 9000000);

-- --------------------------------------------------------

--
-- Table structure for table `header_nota`
--

CREATE TABLE `header_nota` (
  `id` int(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `kota` varchar(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `header_nota`
--

INSERT INTO `header_nota` (`id`, `nama`, `alamat`, `kota`, `tanggal`) VALUES
(1, 'Xanon', 'Washington DC', '', '2018-03-24'),
(2, 'Molly', 'Embong Malang', '', '2018-11-23'),
(3, 'Reyhan', 'Bulak Banteng', '', '2018-02-23'),
(8, 'Risa', 'Kemerdekaan', 'Jakarta', '2018-12-12'),
(9, 'Jack', 'Sesame street', 'New York', '2018-11-21'),
(10, 'Jihan', 'Keputran', 'Surabaya', '2018-12-03'),
(11, 'Erin', 'Kupang', 'Kupang', '2018-11-21'),
(12, 'Dori', 'Jepang Gang 5 No. 12', 'Padang', '2018-12-01');

-- --------------------------------------------------------

--
-- Table structure for table `pembeli`
--

CREATE TABLE `pembeli` (
  `id` int(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `kota` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembeli`
--

INSERT INTO `pembeli` (`id`, `nama`, `alamat`, `kota`) VALUES
(3, 'Izzatul Millah', 'kejawan', 'null'),
(4, 'Rizaldi', 'Panjang Jiwo Permai', 'null'),
(5, 'Jenny', 'Menanggal Utara', 'null'),
(6, 'Tasya', 'Kemang', 'null');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detail_nota`
--
ALTER TABLE `detail_nota`
  ADD PRIMARY KEY (`id_nota`);

--
-- Indexes for table `header_nota`
--
ALTER TABLE `header_nota`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pembeli`
--
ALTER TABLE `pembeli`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `header_nota`
--
ALTER TABLE `header_nota`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `pembeli`
--
ALTER TABLE `pembeli`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
