-- Creación de la base de datos
DROP DATABASE if exists ParkingAlquiler;
CREATE DATABASE ParkingAlquiler;
USE ParkingAlquiler;

drop table if exists Parkings,
					 TiposVehiculos,
                     Vehiculos,
                     Clientes,
                     Reservas,
                     Empleados;

-- creo el usuario mediante el cual vamos a realizar las operaciones de sql desde java

drop user developer@localhost;

create user developer@localhost identified by '1234';

grant all privileges on ParkingAlquiler.* to developer@localhost;

-- Tabla para los parkings
-- Por ahora la primary es un int, pero quizá luego sea mejor cambiar a un String

CREATE TABLE Parkings (
    parking_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    ciudad VARCHAR(50) NOT NULL,
    codigo_postal CHAR(5) NOT NULL,
    capacidad_total INT NOT NULL,
    plazas_disponibles INT NOT NULL,
    hora_apertura TIME NOT NULL,
    hora_cierre TIME NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

insert into Parkings (nombre, direccion, ciudad, codigo_postal, capacidad_total, plazas_disponibles, hora_apertura, hora_cierre) VALUES ('Las Farolas','Calle Inventada','Santander','39000',100,100,'08:00','23:00');

-- Tabla para los tipos de vehículos
CREATE TABLE TiposVehiculos (
    tipo_id INT PRIMARY KEY AUTO_INCREMENT,
    tipo enum('Compacto','Berlina','SUV','Todoterreno','Camioneta'),
    descripcion VARCHAR(50),
    tarifa DECIMAL(6, 2) DEFAULT 0.00
);

-- Tabla para los vehículos
CREATE TABLE Vehiculos (
    vehiculo_id INT PRIMARY KEY AUTO_INCREMENT,
    matricula CHAR(8) UNIQUE NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    color VARCHAR(30) NOT NULL,
    tipo_id INT NOT NULL,
    estado enum ('Disponible', 'Alquilado', 'Mantenimiento', 'Baja') DEFAULT 'Disponible',
    fecha_adquisicion DATE NOT NULL,
    parking_id INT NOT NULL,
    -- fecha_ultima_revision DATE,
    -- kilometraje INT NOT NULL,
    FOREIGN KEY (tipo_id) REFERENCES TiposVehiculos(tipo_id),
    FOREIGN KEY (parking_id) REFERENCES Parkings(parking_id)
);

-- Tabla para los clientes
CREATE TABLE Clientes (
    cliente_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni CHAR(9) UNIQUE NOT NULL,
    f_nacimiento DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono CHAR(9) NOT NULL,
    direccion VARCHAR(100),
    ciudad VARCHAR(50),
    codigo_postal CHAR(5),
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    permiso_valido BOOLEAN NOT NULL DEFAULT TRUE,
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla para las reservas
CREATE TABLE Reservas (
    reserva_id INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id INT NOT NULL,
    vehiculo_id INT NOT NULL,
    parking_id INT NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME DEFAULT NULL,
    estado enum ('Pendiente', 'Confirmada', 'En curso', 'Finalizada', 'Cancelada'),
    costo_total DECIMAL(10, 2) NOT NULL,
    -- metodo_pago VARCHAR(50),
    -- notas TEXT,
    -- fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(cliente_id),
    FOREIGN KEY (vehiculo_id) REFERENCES Vehiculos(vehiculo_id),
    FOREIGN KEY (parking_id) REFERENCES Parkings(parking_id)
);




-- Tabla para los empleados
CREATE TABLE Empleados (
    empleado_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    dni CHAR(9) UNIQUE NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefono CHAR(9) NOT NULL,
    puesto VARCHAR(50) NOT NULL,
    parking_id INT,
    fecha_contratacion DATE NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (parking_id) REFERENCES Parkings(parking_id)
);

insert into Parkings (nombre, direccion, ciudad, codigo_postal, capacidad_total, plazas_disponibles, hora_apertura, hora_cierre) values('Inventado','Calle falsa','Santander','39000',100,100,'09:00','23:00');

select * from Parkings;