
--para crear la base de datos
create database t_plaza;
use t_plaza;





-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/

-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-11-2018 a las 21:53:19
-- Versión del servidor: 10.1.33-MariaDB
-- Versión de PHP: 7.2.6

-- SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
-- SET AUTOCOMMIT = 0;
-- START TRANSACTION;
-- SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `t-plaza`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cajadepago`
--

CREATE TABLE `cajadepago` (
  `cod_sucursal` int(11) NOT NULL,
  `num_de_caja` int(11) NOT NULL,
  `monto_recaudado` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `dni` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  `monto_a_pagar` float NOT NULL,
  `cod_sucursal` int(11) NOT NULL,
  `num_de_caja` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tienda`
--

CREATE TABLE `tienda` (
  `cod_sucursal` int(11) NOT NULL,
  `distrito` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cajadepago`
--
ALTER TABLE `cajadepago`
  ADD PRIMARY KEY (`num_de_caja`,`cod_sucursal`),
  ADD KEY `FK_Caja_Tienda` (`cod_sucursal`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`dni`),
  ADD KEY `FK_Caja_Cliente` (`cod_sucursal`,`num_de_caja`);

--
-- Indices de la tabla `tienda`
--
ALTER TABLE `tienda`
  ADD PRIMARY KEY (`cod_sucursal`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cajadepago`
--
ALTER TABLE `cajadepago`
  ADD CONSTRAINT `FK_Caja_Tienda` FOREIGN KEY (`cod_sucursal`) REFERENCES `tienda` (`cod_sucursal`) ON DELETE CASCADE;

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FK_Caja_Cliente` FOREIGN KEY (`cod_sucursal`,`num_de_caja`) REFERENCES `cajadepago` (`cod_sucursal`, `num_de_caja`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

    insert into Tienda values(200, 'Barranco'), (210, 'Carabayllo'), (220, 'Chaclacayo'), (230,'Cieneguilla'),
                            (240, 'Ate'),(250, 'La MOlina'), (260, 'La victoria'), (270, 'Lima'), (280, 'Los Olivos'),
                            (290, 'San Borja'), (300, 'San Isidro');
    insert into cajadepago values(200, 6, 150.0),(200, 1, 500.0),(200, 8, 80.0),(200, 7, 180.0),(200, 4, 50.0),
                                (210, 7, 150.0),(210, 1, 500.0),(210, 8, 801.0),(210, 2, 980.0),(210, 0, 70.0),
                                (220, 1, 150.0),(220, 2, 500.0),(220, 7, 801.0),(220, 3, 980.0),(220, 4, 170.0),
                                (230, 7, 850.0),(230, 1, 100.0),(230, 8, 801.0),(230, 2, 980.0),(230, 0, 70.0),
                                (240, 6, 150.0),(240, 1, 500.0),(240, 7, 80.0),(240, 9, 180.0),(240, 4, 50.0),
                                (250, 6, 350.0),(250, 1, 820.0),(250, 7, 80.0),(250, 2, 180.0),(250, 4, 50.0),
                                (260, 1, 150.0),(260, 2, 500.0),(260, 7, 801.0),(260, 3, 980.0),(260, 4, 170.0),
                                (270, 1, 10.0),(270, 2, 60.0),
                                (280, 9, 150.0),(280, 2, 650.0),
                                (290, 7, 70.0),(290, 2, 60.0),
                                (300, 2, 60.0);

    insert into cliente values  ('78454985',50.0,200, 6),('16529843',78.0,200, 1),('86142594',510.0,200, 8),('31524625',70.0,200, 1),('54565456',40.0,200, 4),
                                ('71154985',789.0,200, 6),('16888843',12.0,200, 1),('86142444',840.0,200, 7),('44724625',12.0,200, 1),('54565256',89.0,200, 4),
                                ('84785984',45.0,210, 7),('78454974',90.0,210, 1),('95123485',40.0,210, 8),('76254985',510.0,210, 2),('95624187',55.5,210, 0),
                                ('95124987',50.0,220, 1),('95548177',50.5,220, 2),('25467987',74.0,220, 7),('95222871',95.0,220, 3),('55556421',70.0,220, 4),
                                ('65459877',15.6,230, 7),('11124987',50.0,230, 1),('33324987',554.0,230, 8),('88124987',50.0,230, 2),('96664987',50.0,230, 0),
                                ('95999987',50.0,240, 6),('95124333',50.0,240, 1),('15999998',82.0,240, 7),('95122136',50.0,240, 1),('95155547',57.0,240, 4),
                                ('78999987',47.0,240, 9),('78784333',52.0,240, 9),('15599998',96.0,240, 9),('95144456',522.0,240, 9),('95155587',575.0,240, 9),
                                ('95128885',80.0,250, 6),('95256487',10.0,250, 1),('97799887',36.0,250, 7),('25254987',50.0,250, 1),('63656892',80.0,250, 4),
                                ('40128885',82.0,250, 2),
                                ('95122322',50.0,260, 1),('88824987',50.0,260, 2),('95133381',50.0,260, 7),('12344987',82.0,260, 3),('95122555',15.0,260, 4),
                                ('44124987',504.0,270, 1),('14124557',510.0,270, 2),
                                ('66524987',50.0,280, 9),('95125369',51.2,280, 2),
                                ('45666987',50.0,290, 7),('12332498',152.0,290, 2),
                                ('95131188',50.0,300, 2);



