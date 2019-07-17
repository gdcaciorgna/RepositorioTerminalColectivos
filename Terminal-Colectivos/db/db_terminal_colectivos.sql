-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-07-2019 a las 17:41:31
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
-- Estructura de tabla para la tabla `ciudades`
--

CREATE TABLE `ciudades` (
  `cod_postal` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ciudades`
--

INSERT INTO `ciudades` (`cod_postal`, `nombre`) VALUES
(2000, 'Rosario'),
(2600, 'Venado Tuerto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colectivos`
--

CREATE TABLE `colectivos` (
  `patente` varchar(45) NOT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `companias_tarjetas`
--

CREATE TABLE `companias_tarjetas` (
  `cod_compania` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escalas`
--

CREATE TABLE `escalas` (
  `cod_ruta` int(11) NOT NULL,
  `orden` int(11) NOT NULL,
  `cod_terminal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajeros`
--

CREATE TABLE `pasajeros` (
  `dni` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajeros_reservas`
--

CREATE TABLE `pasajeros_reservas` (
  `dni` int(11) NOT NULL,
  `cod_ruta` int(11) NOT NULL,
  `fecha_res` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `usuario` varchar(45) NOT NULL,
  `asiento` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planes`
--

CREATE TABLE `planes` (
  `fecha_hora_plan` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `patente` int(11) NOT NULL,
  `cuil` int(11) NOT NULL,
  `cod_ruta` int(11) NOT NULL,
  `precio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
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
-- Estructura de tabla para la tabla `rutas`
--

CREATE TABLE `rutas` (
  `cod_ruta` int(11) NOT NULL,
  `dias_sem` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `terminales`
--

CREATE TABLE `terminales` (
  `cod_terminal` int(11) NOT NULL,
  `cod_postal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `usuario` varchar(45) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `rol` varchar(45) NOT NULL DEFAULT 'Cliente',
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `cuil` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT 'activo'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usuario`, `nombre`, `apellido`, `rol`, `email`, `password`, `cuil`, `estado`) VALUES
('admin', 'NombreAdmin', 'ApellidoAdmin', 'admin', 'admin@hotmail.com', 'admin', '20384605321', 'activo'),
('federico', 'Federico', 'Caciorgna', 'chofer', 'f_caciorgna@hotmail.com', '123456', '123', 'activo'),
('gdcaciorgna', 'Gerardo', 'Caciorgna', 'admin', 'gdcaciorgna@hotmail.com', '123456', NULL, 'activo'),
('joaquinvilchez', 'Joaquin', 'Vilchez', 'cliente', 'joaquinvilchez@hotmail.com', '123456', '20384460411', 'activo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciudades`
--
ALTER TABLE `ciudades`
  ADD PRIMARY KEY (`cod_postal`);

--
-- Indices de la tabla `colectivos`
--
ALTER TABLE `colectivos`
  ADD PRIMARY KEY (`patente`);

--
-- Indices de la tabla `companias_tarjetas`
--
ALTER TABLE `companias_tarjetas`
  ADD PRIMARY KEY (`cod_compania`);

--
-- Indices de la tabla `escalas`
--
ALTER TABLE `escalas`
  ADD PRIMARY KEY (`cod_ruta`,`orden`),
  ADD KEY `escala_terminal_idx` (`cod_terminal`);

--
-- Indices de la tabla `pasajeros`
--
ALTER TABLE `pasajeros`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `pasajeros_reservas`
--
ALTER TABLE `pasajeros_reservas`
  ADD PRIMARY KEY (`dni`,`cod_ruta`,`fecha_res`,`usuario`),
  ADD KEY `pasajero_reserva_usuario_idx` (`usuario`),
  ADD KEY `pasajero_reserva_reserva_idx` (`fecha_res`),
  ADD KEY `pasajero_reserva_ruta_idx` (`cod_ruta`);

--
-- Indices de la tabla `planes`
--
ALTER TABLE `planes`
  ADD PRIMARY KEY (`fecha_hora_plan`,`patente`,`cuil`,`cod_ruta`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`fecha_res`,`cod_ruta`,`usuario`),
  ADD KEY `ruta_reserva_idx` (`cod_ruta`),
  ADD KEY `usuario_reserva_idx` (`usuario`),
  ADD KEY `compania_reserva_idx` (`cod_compania`);

--
-- Indices de la tabla `rutas`
--
ALTER TABLE `rutas`
  ADD PRIMARY KEY (`cod_ruta`);

--
-- Indices de la tabla `terminales`
--
ALTER TABLE `terminales`
  ADD PRIMARY KEY (`cod_terminal`),
  ADD KEY `terminal_ciudad_idx` (`cod_postal`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `companias_tarjetas`
--
ALTER TABLE `companias_tarjetas`
  MODIFY `cod_compania` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `rutas`
--
ALTER TABLE `rutas`
  MODIFY `cod_ruta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `terminales`
--
ALTER TABLE `terminales`
  MODIFY `cod_terminal` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `escalas`
--
ALTER TABLE `escalas`
  ADD CONSTRAINT `escala_ruta` FOREIGN KEY (`cod_ruta`) REFERENCES `rutas` (`cod_ruta`),
  ADD CONSTRAINT `escala_terminal` FOREIGN KEY (`cod_terminal`) REFERENCES `terminales` (`cod_terminal`);

--
-- Filtros para la tabla `pasajeros_reservas`
--
ALTER TABLE `pasajeros_reservas`
  ADD CONSTRAINT `pasajero_reserva_pasajero` FOREIGN KEY (`dni`) REFERENCES `pasajeros` (`dni`),
  ADD CONSTRAINT `pasajero_reserva_reserva` FOREIGN KEY (`fecha_res`) REFERENCES `reservas` (`fecha_res`),
  ADD CONSTRAINT `pasajero_reserva_ruta` FOREIGN KEY (`cod_ruta`) REFERENCES `rutas` (`cod_ruta`),
  ADD CONSTRAINT `pasajero_reserva_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`usuario`);

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `compania_reserva` FOREIGN KEY (`cod_compania`) REFERENCES `companias_tarjetas` (`cod_compania`),
  ADD CONSTRAINT `ruta_reserva` FOREIGN KEY (`cod_ruta`) REFERENCES `rutas` (`cod_ruta`),
  ADD CONSTRAINT `usuario_reserva` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`usuario`);

--
-- Filtros para la tabla `terminales`
--
ALTER TABLE `terminales`
  ADD CONSTRAINT `terminal_ciudad` FOREIGN KEY (`cod_postal`) REFERENCES `ciudades` (`cod_postal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
