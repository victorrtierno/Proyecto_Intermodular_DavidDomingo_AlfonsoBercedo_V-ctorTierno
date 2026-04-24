CREATE DATABASE IF NOT EXISTS GestionEventos;
USE GestionEventos;

CREATE TABLE Evento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    lugar VARCHAR(150) NOT NULL,
    estado VARCHAR(20) DEFAULT 'Proximo'
);

CREATE TABLE Cantante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    prApellido VARCHAR(100) NOT NULL,
    sgApellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(15),
    numTarjeta VARCHAR(20),
    fechaRegistro DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(8) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    prApellido VARCHAR(100) NOT NULL,
    sgApellido VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL
);


CREATE TABLE Evento_Cantante (
    idEvento INT,
    idCantante INT,
    FechaEvento date not null,
    PRIMARY KEY (idEvento, idCantante),
    FOREIGN KEY (idEvento) REFERENCES Evento(id) ON DELETE CASCADE,
    FOREIGN KEY (idCantante) REFERENCES Cantante(id) ON DELETE CASCADE
);

CREATE TABLE Zona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idEvento INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    precioBase DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (idEvento) REFERENCES Evento(id) ON DELETE CASCADE
);

CREATE TABLE Asiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idZona INT NOT NULL,
    fila INT NOT NULL,
    numero INT NOT NULL,
    estado VARCHAR(20) DEFAULT 'Libre',
    FOREIGN KEY (idZona) REFERENCES Zona(id) ON DELETE CASCADE
);

CREATE TABLE Venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    precioTotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(id)
);

CREATE TABLE Entrada (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idVenta INT NOT NULL,
    idAsiento INT UNIQUE NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (idVenta) REFERENCES Venta(id) ON DELETE CASCADE,
    FOREIGN KEY (idAsiento) REFERENCES Asiento(id)
);






USE GestionEventos;

INSERT INTO Empleado (dni, nombre, usuario, contrasena, rol) VALUES 
('12345678', 'Administrador Principal', 'admin', 'admin123', 'ADMIN'),
('87654321', 'Taquillero Centro', 'taquilla1', 'taq123', 'VENTAS');

INSERT INTO Cliente (nombre, email, telefono, numtarjeta) VALUES 
('María López', 'maria.lopez@email.com', '600111222', '1234567890123456'),
('Carlos García', 'carlos.garcia@email.com', '600333444', '9876543210987654');

INSERT INTO Evento (nombre, fecha, lugar, estado) VALUES 
('Concierto Fin de Gira', '2026-08-15', 'WiZink Center', 'Proximo'),
('Festival de Verano 2026', '2026-07-10', 'Recinto Ferial', 'Proximo');

INSERT INTO Cantante (nombre, descripcion) VALUES 
('Rosalía', 'Artista internacional de pop y flamenco'),
('Quevedo', 'Cantante de reguetón y trap latino'),
('C. Tangana', 'Cantante, compositor y rapero');


INSERT INTO Evento_Cantante (idevento, idcantante, fechaevento) VALUES 
(1, 1, current_timestamp),
(2, 2, current_timestamp),
(2, 3, current_timestamp);


INSERT INTO Zona (idevento, nombre, preciobase) VALUES 
(1, 'Pista General', 50.00),
(1, 'Grada VIP', 120.00);
INSERT INTO Zona (idevento, nombre, preciobase) VALUES 
(2, 'Abono 3 Días', 150.00);


INSERT INTO Asiento (idzona, fila, numero, estado) VALUES 
(1, 1, 1, true),
(1, 1, 2, true),
(1, 1, 3, false); 
INSERT INTO Asiento (idzona, fila, numero, estado) VALUES 
(2, 1, 1, true),
(2, 1, 2, true);
INSERT INTO Asiento (idzona, fila, numero, estado) VALUES 
(3, 1, 1, true),
(3, 1, 2, true);

INSERT INTO Venta (idcliente, fecha, preciototal) VALUES 
(1, NOW(), 50.00);

INSERT INTO Entrada (idventa, idasiento, precio) VALUES 
(1, 3, 50.00);
