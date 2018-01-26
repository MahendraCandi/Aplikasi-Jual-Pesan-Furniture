-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 26, 2018 at 04:23 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dbfurniture`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE IF NOT EXISTS `barang` (
  `kd_barang` varchar(10) NOT NULL,
  `jenis_barang` varchar(15) DEFAULT NULL,
  `kategori` varchar(30) DEFAULT NULL,
  `nama_barang` varchar(30) DEFAULT NULL,
  `ukuran` varchar(15) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `warna` varchar(15) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `diskon` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_pesanan`
--

CREATE TABLE IF NOT EXISTS `detail_pesanan` (
  `id_detail` int(11) NOT NULL,
  `no_pemesanan` varchar(10) DEFAULT NULL,
  `kd_barang` varchar(20) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `kuantitas` int(11) DEFAULT NULL,
  `diskon` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE IF NOT EXISTS `pelanggan` (
  `kd_pelanggan` varchar(10) NOT NULL,
  `nm_pelanggan` varchar(30) DEFAULT NULL,
  `telepon` varchar(15) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `keterangan` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE IF NOT EXISTS `pembayaran` (
  `no_pembayaran` varchar(10) NOT NULL,
  `no_pemesanan` varchar(10) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `uang_bayar` double DEFAULT NULL,
  `uang_kembali` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pemesanan`
--

CREATE TABLE IF NOT EXISTS `pemesanan` (
  `no_pemesanan` varchar(10) NOT NULL,
  `kd_pelanggan` varchar(10) DEFAULT NULL,
  `jenis_pemesanan` varchar(15) DEFAULT NULL,
  `waktu` date DEFAULT NULL,
  `total_harga` double DEFAULT NULL,
  `uang_muka` double DEFAULT NULL,
  `nama_pemesanan` varchar(30) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `kode_user` varchar(10) DEFAULT NULL,
  `keterangan` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pengiriman`
--

CREATE TABLE IF NOT EXISTS `pengiriman` (
  `kd_pengiriman` varchar(10) NOT NULL,
  `no_pembayaran` varchar(10) DEFAULT NULL,
  `tgl_kirim` date DEFAULT NULL,
  `nm_penerima` varchar(30) DEFAULT NULL,
  `telp_penerima` varchar(15) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `kode_user` varchar(10) NOT NULL,
  `nama_user` varchar(30) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `hak_akses` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kd_barang`);

--
-- Indexes for table `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  ADD PRIMARY KEY (`id_detail`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`kd_pelanggan`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`no_pembayaran`);

--
-- Indexes for table `pemesanan`
--
ALTER TABLE `pemesanan`
  ADD PRIMARY KEY (`no_pemesanan`);

--
-- Indexes for table `pengiriman`
--
ALTER TABLE `pengiriman`
  ADD PRIMARY KEY (`kd_pengiriman`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`kode_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
