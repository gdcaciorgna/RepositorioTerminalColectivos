-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-06-2019 a las 17:43:01
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_terminal_colectivos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `cod_postal` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`cod_postal`, `nombre`) VALUES
(2000, 'Rosario'),
(2600, 'Venado Tuerto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colectivo`
--

CREATE TABLE `colectivo` (
  `patente` varchar(45) NOT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compania_tarjeta`
--

CREATE TABLE `compania_tarjeta` (
  `cod_compania` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escala`
--

CREATE TABLE `escala` (
  `cod_ruta` int(11) NOT NULL,
  `orden` int(11) NOT NULL,
  `cod_terminal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajero`
--

CREATE TABLE `pasajero` (
  `dni` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajero_reserva`
--

CREATE TABLE `pasajero_reserva` (
  `dni` int(11) NOT NULL,
  `cod_ruta` int(11) NOT NULL,
  `fecha_res` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `usuario` varchar(45) NOT NULL,
  `asiento` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan`
--

CREATE TABLE `plan` (
  `fecha_hora_plan` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `patente` int(11) NOT NULL,
  `cuil` int(11) NOT NULL,
  `cod_ruta` int(11) NOT NULL,
  `precio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `fecha_res` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cod_ruta` int(11) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `cant_pas` int(11) DEFAULT NULL,
  `fecha_viaje` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fecha_venc` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fecha_canc` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `cod_compania` int(11) DEFAULT NULL,
  `nro_tarjeta` int(11) DEFAULT NULL,
  `fecha_pago` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

CREATE TABLE `ruta` (
  `cod_ruta` int(11) NOT NULL,
  `dias_sem` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `terminal`
--

CREATE TABLE `terminal` (
  `cod_terminal` int(11) NOT NULL,
  `cod_postal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `usuario` varchar(45) NOT NULL,
  `rol` varchar(45) NOT NULL DEFAULT 'Cliente',
  `email` varchar(45) NOT NULL,
  `contrasenia` varchar(45) NOT NULL,
  `cuil` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT 'activo'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`usuario`, `rol`, `email`, `contrasenia`, `cuil`, `estado`) VALUES
('admin', 'admin', 'admin@hotmail.com', 'admin', 123456, 'activo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`cod_postal`);

--
-- Indices de la tabla `colectivo`
--
ALTER TABLE `colectivo`
  ADD PRIMARY KEY (`patente`);

--
-- Indices de la tabla `compania_tarjeta`
--
ALTER TABLE `compania_tarjeta`
  ADD PRIMARY KEY (`cod_compania`);

--
-- Indices de la tabla `escala`
--
ALTER TABLE `escala`
  ADD PRIMARY KEY (`cod_ruta`,`orden`),
  ADD KEY `escala_terminal_idx` (`cod_terminal`);

--
-- Indices de la tabla `pasajero`
--
ALTER TABLE `pasajero`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `pasajero_reserva`
--
ALTER TABLE `pasajero_reserva`
  ADD PRIMARY KEY (`dni`,`cod_ruta`,`fecha_res`,`usuario`),
  ADD KEY `pasajero_reserva_usuario_idx` (`usuario`),
  ADD KEY `pasajero_reserva_reserva_idx` (`fecha_res`),
  ADD KEY `pasajero_reserva_ruta_idx` (`cod_ruta`);

--
-- Indices de la tabla `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`fecha_hora_plan`,`patente`,`cuil`,`cod_ruta`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`fecha_res`,`cod_ruta`,`usuario`),
  ADD KEY `ruta_reserva_idx` (`cod_ruta`),
  ADD KEY `usuario_reserva_idx` (`usuario`),
  ADD KEY `compania_reserva_idx` (`cod_compania`);

--
-- Indices de la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD PRIMARY KEY (`cod_ruta`);

--
-- Indices de la tabla `terminal`
--
ALTER TABLE `terminal`
  ADD PRIMARY KEY (`cod_terminal`),
  ADD KEY `terminal_ciudad_idx` (`cod_postal`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `compania_tarjeta`
--
ALTER TABLE `compania_tarjeta`
  MODIFY `cod_compania` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ruta`
--
ALTER TABLE `ruta`
  MODIFY `cod_ruta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `terminal`
--
ALTER TABLE `terminal`
  MODIFY `cod_terminal` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `escala`
--
ALTER TABLE `escala`
  ADD CONSTRAINT `escala_ruta` FOREIGN KEY (`cod_ruta`) REFERENCES `ruta` (`cod_ruta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `escala_terminal` FOREIGN KEY (`cod_terminal`) REFERENCES `terminal` (`cod_terminal`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pasajero_reserva`
--
ALTER TABLE `pasajero_reserva`
  ADD CONSTRAINT `pasajero_reserva_pasajero` FOREIGN KEY (`dni`) REFERENCES `pasajero` (`dni`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `pasajero_reserva_reserva` FOREIGN KEY (`fecha_res`) REFERENCES `reserva` (`fecha_res`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `pasajero_reserva_ruta` FOREIGN KEY (`cod_ruta`) REFERENCES `ruta` (`cod_ruta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `pasajero_reserva_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `compania_reserva` FOREIGN KEY (`cod_compania`) REFERENCES `compania_tarjeta` (`cod_compania`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ruta_reserva` FOREIGN KEY (`cod_ruta`) REFERENCES `ruta` (`cod_ruta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `usuario_reserva` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `terminal`
--
ALTER TABLE `terminal`
  ADD CONSTRAINT `terminal_ciudad` FOREIGN KEY (`cod_postal`) REFERENCES `ciudad` (`cod_postal`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
