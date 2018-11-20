use master;

create database empresa;

create table if not exists tienda(
    cod_sucursal int primary key,
    distrito varchar(100)

);

create table if not exists caja(
    cod_sucursal int references tienda(cod_sucursal) not null,
    num_de_caja  int not null,
    primary key(cod_sucursal,num_de_caja)

);
create table if not exists cliente(
    dni char(8) primary key,
    monto_a_pagar float(10,2),

    cod_sucursal int references caja(cod_sucursal) not null,
    num_de_caja int references caja(num_de_caja) not null 
);






create database if not exists empresa;
use empresa;

create table if not exists tienda(
    cod_sucursal int not null,
    distrito varchar(100),
    primary key(cod_sucursal)
);

create table if not exists caja(
    cod_sucursal int not null,
    num_de_caja  int not null,
    FOREIGN key (cod_sucursal) references tienda(cod_sucursal), 
    primary key(cod_sucursal, num_de_caja)

);

create table if not exists cliente(
    dni char(8) not null,
    monto_a_pagar float(10,2) not null,

    ref_cod_sucursal int not null,
    ref_num_de_caja int  not null,
    CONSTRAINT fk_caja_cliente FOREIGN key (ref_cod_sucursal, ref_num_de_caja) references caja(cod_sucursal, num_de_caja),
    primary key(dni)
);





























create database empresa;
use empresa;

-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-11-2018 a las 08:22:25
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11




/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `empresa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cajadepago`
--

CREATE TABLE IF NOT EXISTS `cajadepago` (
  `cod_sucursal` int(11) NOT NULL,
  `num_de_caja` int(11) NOT NULL,
  PRIMARY KEY (`num_de_caja`,`cod_sucursal`),
  KEY `FK_ Caja Tienda` (`cod_sucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `dni` char(8) NOT NULL,
  `monto_a_pagar` float(10,2) NOT NULL,
  `num_de_caja` int(11) NOT NULL,
  `cod_sucursal` int(11) NOT NULL,
  PRIMARY KEY (`dni`),
  KEY `FK_Caja_Cliente` (`num_de_caja`,`cod_sucursal`),
  KEY `CajaTiendaId` (`cod_sucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tienda`
--

CREATE TABLE IF NOT EXISTS `tienda` (
  `cod_sucursal` int(11) NOT NULL,
  `distrito` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`cod_sucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cajadepago`
--
ALTER TABLE `cajadepago`
  ADD CONSTRAINT `FK_ Caja Tienda` FOREIGN KEY (`cod_sucursal`) REFERENCES `tienda` (`cod_sucursal`) ON DELETE CASCADE;

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_2` FOREIGN KEY (`cod_sucursal`) REFERENCES `cajadepago` (`TiendaId`),
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`num_de_caja`) REFERENCES `cajadepago` (`NroCaja`),
  ADD CONSTRAINT `FK_Caja_Cliente` FOREIGN KEY (`cod_sucursal`, `num_de_caja`) REFERENCES `cajadepago` (`num_de_caja`, `cod_sucursal`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
