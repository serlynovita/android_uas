-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 10 Des 2018 pada 03.38
-- Versi Server: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `android_uas`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE IF NOT EXISTS `barang` (
`id_barang` int(11) NOT NULL,
  `nama_barang` varchar(255) NOT NULL,
  `warna_barang` varchar(255) NOT NULL,
  `kategori_barang` varchar(255) NOT NULL,
  `berat_barang` int(255) NOT NULL,
  `foto` varchar(10000) NOT NULL,
  `deskripsi` text NOT NULL,
  `harga` int(50) NOT NULL,
  `stok` int(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `warna_barang`, `kategori_barang`, `berat_barang`, `foto`, `deskripsi`, `harga`, `stok`) VALUES
(17, 'a', 'a', 'a', 4, '1544373234943.jpg', 'a', 4, 4),
(18, 'a', 'a', 'a', 1, '1544393038393.jpg', 'a', 4, 1),
(19, 'a', 'a', 'a', 1, '15443930383931.jpg', 'a', 4, 1),
(20, 'a', 'a', 'a', 1, '15443930383932.jpg', 'a', 4, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `ongkos_kirim`
--

CREATE TABLE IF NOT EXISTS `ongkos_kirim` (
`id_ongkir` int(11) NOT NULL,
  `kota` varchar(255) NOT NULL,
  `harga` int(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ongkos_kirim`
--

INSERT INTO `ongkos_kirim` (`id_ongkir`, `kota`, `harga`) VALUES
(1, 'malang', 25000),
(2, 'surabaya', 40000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembeli`
--

CREATE TABLE IF NOT EXISTS `pembeli` (
`id_pembeli` int(11) NOT NULL,
  `nama_pembeli` varchar(255) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `telpn` varchar(15) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pembeli`
--

INSERT INTO `pembeli` (`id_pembeli`, `nama_pembeli`, `alamat`, `telpn`, `email`, `password`) VALUES
(12, 'Rindu aji', 'malang', '0812345679', 'rinduaji@gmail.com', '12345');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
`id_transaksi` int(11) NOT NULL,
  `id_pembeli` int(11) NOT NULL,
  `id_ongkir` int(11) NOT NULL,
  `total_harga` int(50) NOT NULL,
  `tgl_beli` date NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_pembeli`, `id_ongkir`, `total_harga`, `tgl_beli`, `status`) VALUES
(1, 12, 1, 6500, '2018-12-05', 'Terkirim'),
(2, 12, 1, 1000, '1999-02-02', 'lol'),
(4, 12, 1, 4, '2018-12-10', 'menunggu'),
(5, 12, 1, 2, '2018-12-07', 'Terkirim');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
 ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `ongkos_kirim`
--
ALTER TABLE `ongkos_kirim`
 ADD PRIMARY KEY (`id_ongkir`);

--
-- Indexes for table `pembeli`
--
ALTER TABLE `pembeli`
 ADD PRIMARY KEY (`id_pembeli`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
 ADD PRIMARY KEY (`id_transaksi`), ADD KEY `id_pembeli` (`id_pembeli`), ADD KEY `id_ongkir` (`id_ongkir`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `ongkos_kirim`
--
ALTER TABLE `ongkos_kirim`
MODIFY `id_ongkir` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `pembeli`
--
ALTER TABLE `pembeli`
MODIFY `id_pembeli` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_pembeli`) REFERENCES `pembeli` (`id_pembeli`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_ongkir`) REFERENCES `ongkos_kirim` (`id_ongkir`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
