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
                     
drop trigger if exists updateDisponibilidad;

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
    tipo varchar(30),
    descripcion VARCHAR(50),
    tarifa DECIMAL(6, 2) DEFAULT 0.00
);

-- Tabla para los vehículos
CREATE TABLE Vehiculos (
    vehiculo_id INT PRIMARY KEY AUTO_INCREMENT,
    matricula CHAR(7) UNIQUE NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    color VARCHAR(30) NOT NULL,
    tipo_id INT NOT NULL,
    estado enum ('Disponible', 'Alquilado', 'Mantenimiento', 'Baja') DEFAULT 'Disponible',
    fecha_adquisicion DATE NOT NULL default (current_date()),
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
    permiso_valido BOOLEAN NOT NULL DEFAULT TRUE,
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla para las reservas
CREATE TABLE Reservas (
    reserva_id INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id INT NOT NULL,
    vehiculo_id INT NOT NULL,
    parking_id INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE not null,
    costo_total DECIMAL(10, 2) NOT NULL,
    -- metodo_pago VARCHAR(50),
    -- notas TEXT,
    -- fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(cliente_id),
    FOREIGN KEY (vehiculo_id) REFERENCES Vehiculos(vehiculo_id),
    FOREIGN KEY (parking_id) REFERENCES Parkings(parking_id)
);



create view cuentaparkings as
select count(parking_id) as numparkings from Parkings;

create view vistaidtipo as
select count(tipo_id) as ids from TiposVehiculos;

create view vistatipos as
select tipo from TiposVehiculos;

create view cuentavehiculos as
select count(vehiculo_id) as numvehiculos from Vehiculos;

create view cuentaclientes as
select count(cliente_id) as clientes from Clientes;

create view cuentareservas as
select count(reserva_id) as reservas from Reservas;

delimiter //
create trigger updateDisponibilidad
after insert on Vehiculos
for each row
begin
		update Parkings
        set plazas_disponibles = plazas_disponibles - 1
        where Parkings.parking_id = new.parking_id;

end //

drop trigger if exists updateDisponibilidadCambio;

delimiter //
create trigger updateDisponibilidadCambio
after update on Vehiculos
for each row
begin
	if new.parking_id <> old.parking_id then
		update Parkings set plazas_disponibles = plazas_disponibles -1 where Parkings.parking_id = new.parking_id;
        update Parkings set plazas_disponibles = plazas_disponibles +1 where Parkings.parking_id = old.parking_id;
	end if;

end //



INSERT INTO Parkings (nombre, direccion, ciudad, codigo_postal, capacidad_total, plazas_disponibles, hora_apertura, hora_cierre)
VALUES
('Las Farolas', 'Calle Inventada', 'Santander', '39000', 100, 90, '08:00', '23:00'),
('Centro Norte', 'Av. de la Paz, 12', 'Bilbao', '48001', 80, 70, '07:00', '22:00'),
('Parking Sur', 'Calle Real, 45', 'Madrid', '28001', 120, 110, '06:00', '23:59'),
('Estación', 'Plaza Mayor, 1', 'Valladolid', '47001', 60, 50, '08:00', '21:00');


INSERT INTO TiposVehiculos (tipo, descripcion, tarifa) VALUES
('Coche', 'Turismo estándar', 30.00),
('Moto', 'Motocicleta', 15.00),
('Furgoneta', 'Furgoneta de carga', 45.00),
('SUV', 'Vehículo deportivo utilitario', 40.00);


INSERT INTO Vehiculos (matricula, marca, modelo, color, tipo_id, estado, fecha_adquisicion, parking_id) VALUES
('1234ABC', 'Toyota', 'Yaris', 'Rojo', 1, 'Disponible', '2023-01-15', 1),
('5678DEF', 'Renault', 'Clio', 'Azul', 1, 'Alquilado', '2023-03-10', 1),
('9101GHI', 'Peugeot', '208', 'Negro', 1, 'Disponible', '2023-05-20', 2),
('1121JKL', 'Seat', 'Ibiza', 'Blanco', 1, 'Mantenimiento', '2022-12-01', 2),
('1314MNO', 'Honda', 'Civic', 'Gris', 1, 'Disponible', '2023-01-25', 3),
('1516PQR', 'BMW', 'X1', 'Negro', 4, 'Disponible', '2023-02-10', 3),
('1718STU', 'Kia', 'Sportage', 'Blanco', 4, 'Alquilado', '2023-04-18', 4),
('1920VWX', 'Volkswagen', 'Golf', 'Azul', 1, 'Disponible', '2023-06-05', 4),
('2021YZA', 'Opel', 'Corsa', 'Rojo', 1, 'Disponible', '2023-07-11', 1),
('2122BCD', 'Citroen', 'C3', 'Verde', 1, 'Disponible', '2023-08-15', 2),
('2223CDE', 'Piaggio', 'Liberty', 'Negro', 2, 'Disponible', '2023-01-10', 1),
('2324DEF', 'Yamaha', 'NMAX', 'Blanco', 2, 'Alquilado', '2023-02-20', 2),
('2425EFG', 'Honda', 'PCX', 'Gris', 2, 'Disponible', '2023-03-30', 3),
('2526FGH', 'Kawasaki', 'J125', 'Verde', 2, 'Disponible', '2023-04-15', 4),
('2627GHI', 'Ford', 'Transit', 'Blanco', 3, 'Disponible', '2023-05-10', 1),
('2728HIJ', 'Mercedes', 'Vito', 'Negro', 3, 'Disponible', '2023-06-12', 2),
('2829IJK', 'Renault', 'Kangoo', 'Azul', 3, 'Alquilado', '2023-07-20', 3),
('2930JKL', 'Fiat', 'Ducato', 'Gris', 3, 'Disponible', '2023-08-05', 4),
('3031KLM', 'Audi', 'Q5', 'Negro', 4, 'Disponible', '2023-01-19', 1),
('3132LMN', 'Hyundai', 'Tucson', 'Rojo', 4, 'Mantenimiento', '2023-02-23', 2),
('3233MNO', 'Nissan', 'Qashqai', 'Blanco', 4, 'Disponible', '2023-03-14', 3),
('3334NOP', 'Mazda', 'CX-5', 'Azul', 4, 'Disponible', '2023-04-22', 4),
('3435OPQ', 'Peugeot', 'Partner', 'Blanco', 3, 'Disponible', '2023-05-30', 1),
('3536PQR', 'Citroen', 'Berlingo', 'Gris', 3, 'Disponible', '2023-06-18', 2),
('3637QRS', 'Toyota', 'Proace', 'Negro', 3, 'Disponible', '2023-07-25', 3),
('3738RST', 'Volkswagen', 'Transporter', 'Azul', 3, 'Alquilado', '2023-08-12', 4),
('3839STU', 'Honda', 'CB500', 'Rojo', 2, 'Disponible', '2023-01-07', 1),
('3940TUV', 'Suzuki', 'Burgman', 'Blanco', 2, 'Disponible', '2023-02-15', 2),
('4041UVW', 'BMW', 'C400X', 'Negro', 2, 'Disponible', '2023-03-22', 3),
('4142VWX', 'Kymco', 'Agility', 'Gris', 2, 'Disponible', '2023-04-10', 4),
('4243WXY', 'Opel', 'Astra', 'Verde', 1, 'Disponible', '2023-05-14', 1),
('4344XYZ', 'Seat', 'Leon', 'Azul', 1, 'Disponible', '2023-06-20', 2),
('4445YZA', 'Ford', 'Focus', 'Negro', 1, 'Disponible', '2023-07-28', 3),
('4546ZAB', 'Renault', 'Megane', 'Blanco', 1, 'Disponible', '2023-08-10', 4),
('4647ABC', 'Nissan', 'Micra', 'Rojo', 1, 'Disponible', '2023-01-17', 1),
('4748BCD', 'Citroen', 'C4', 'Gris', 1, 'Disponible', '2023-02-25', 2),
('4849CDE', 'Peugeot', '2008', 'Negro', 1, 'Disponible', '2023-03-29', 3),
('4950DEF', 'Volkswagen', 'Polo', 'Blanco', 1, 'Disponible', '2023-04-17', 4),
('5051EFG', 'Toyota', 'Corolla', 'Azul', 1, 'Disponible', '2023-05-05', 1),
('5152FGH', 'Honda', 'Jazz', 'Verde', 1, 'Disponible', '2023-06-15', 2),
('5253GHI', 'Seat', 'Arona', 'Rojo', 1, 'Disponible', '2023-07-09', 3),
('5354HIJ', 'Opel', 'Crossland', 'Negro', 1, 'Disponible', '2023-08-18', 4);


INSERT INTO Clientes (nombre, apellidos, dni, f_nacimiento, email, telefono, direccion, ciudad, codigo_postal, permiso_valido, activo) VALUES
('Juan', 'Pérez Gómez', '12345678A', '1980-05-15', 'juan.perez@gmail.com', '600123456', 'Calle Luna 1', 'Santander', '39001', 1, 1),
('María', 'López Díaz', '23456789B', '1992-11-25', 'maria.lopez@gmail.com', '600234567', 'Calle Sol 2', 'Bilbao', '48002', 1, 1),
('Carlos', 'Sánchez Ruiz', '34567890C', '1985-03-10', 'carlos.sanchez@gmail.com', '600345678', 'Calle Mar 3', 'Madrid', '28002', 1, 1),
('Lucía', 'Martínez Pérez', '45678901D', '1990-07-18', 'lucia.martinez@gmail.com', '600456789', 'Calle Río 4', 'Valladolid', '47002', 1, 1),
('Pedro', 'García López', '56789012E', '1978-09-30', 'pedro.garcia@gmail.com', '600567890', 'Calle Viento 5', 'Santander', '39002', 1, 1),
('Ana', 'Fernández Gómez', '67890123F', '1987-12-22', 'ana.fernandez@gmail.com', '600678901', 'Calle Estrella 6', 'Bilbao', '48003', 1, 1),
('David', 'Jiménez Torres', '78901234G', '1995-02-14', 'david.jimenez@gmail.com', '600789012', 'Calle Nube 7', 'Madrid', '28003', 1, 1),
('Laura', 'Moreno Sáez', '89012345H', '1982-06-09', 'laura.moreno@gmail.com', '600890123', 'Calle Lluvia 8', 'Valladolid', '47003', 1, 1),
('Javier', 'Romero Gil', '90123456I', '1989-08-21', 'javier.romero@gmail.com', '600901234', 'Calle Sol 9', 'Santander', '39003', 1, 1),
('Elena', 'Navarro Ruiz', '01234567J', '1993-01-05', 'elena.navarro@gmail.com', '601012345', 'Calle Luna 10', 'Bilbao', '48004', 1, 1),
('Miguel', 'Ortega Castro', '11223344K', '1984-04-12', 'miguel.ortega@gmail.com', '601122334', 'Calle Mar 11', 'Madrid', '28004', 1, 1),
('Sara', 'Rubio Molina', '22334455L', '1991-10-28', 'sara.rubio@gmail.com', '601233445', 'Calle Río 12', 'Valladolid', '47004', 1, 1),
('Alberto', 'Serrano Vargas', '33445566M', '1979-03-07', 'alberto.serrano@gmail.com', '601344556', 'Calle Viento 13', 'Santander', '39004', 1, 1),
('Marta', 'Castro Ramos', '44556677N', '1986-07-19', 'marta.castro@gmail.com', '601455667', 'Calle Estrella 14', 'Bilbao', '48005', 1, 1),
('Pablo', 'Suárez León', '55667788O', '1994-11-23', 'pablo.suarez@gmail.com', '601566778', 'Calle Nube 15', 'Madrid', '28005', 1, 1),
('Patricia', 'Vega Sanz', '66778899P', '1981-02-16', 'patricia.vega@gmail.com', '601677889', 'Calle Lluvia 16', 'Valladolid', '47005', 1, 1),
('Raúl', 'Ibáñez Peña', '77889900Q', '1988-05-27', 'raul.ibanez@gmail.com', '601788990', 'Calle Sol 17', 'Santander', '39005', 1, 1),
('Cristina', 'Mora Cano', '88990011R', '1996-09-14', 'cristina.mora@gmail.com', '601899001', 'Calle Luna 18', 'Bilbao', '48006', 1, 1),
('Alfonso', 'Herrera Nieto', '99001122S', '1983-12-03', 'alfonso.herrera@gmail.com', '601900112', 'Calle Mar 19', 'Madrid', '28006', 1, 1),
('Julia', 'Luna Ríos', '10111213T', '1985-08-11', 'julia.luna@gmail.com', '602011213', 'Calle Río 20', 'Valladolid', '47006', 1, 1),
('Guillermo', 'Santos Prieto', '11121314U', '1992-04-09', 'guillermo.santos@gmail.com', '602112131', 'Calle Viento 21', 'Santander', '39006', 1, 1),
('Rosa', 'Campos Lara', '12131415V', '1987-11-20', 'rosa.campos@gmail.com', '602213141', 'Calle Estrella 22', 'Bilbao', '48007', 1, 1),
('Sergio', 'Peña Bravo', '13141516W', '1989-03-15', 'sergio.pena@gmail.com', '602314151', 'Calle Nube 23', 'Madrid', '28007', 1, 1),
('Isabel', 'Rey Méndez', '14151617X', '1991-07-24', 'isabel.rey@gmail.com', '602415161', 'Calle Lluvia 24', 'Valladolid', '47007', 1, 1),
('Fernando', 'Díaz Rubio', '15161718Y', '1984-10-30', 'fernando.diaz@gmail.com', '602516171', 'Calle Sol 25', 'Santander', '39007', 1, 1);


INSERT INTO Reservas (cliente_id, vehiculo_id, parking_id, fecha_inicio, fecha_fin, costo_total) VALUES
(1, 2, 1, '2024-05-01 10:00:00', '2024-05-03', 90.00),
(2, 4, 2, '2024-05-02 12:00:00', '2024-05-04', 60.00),
(3, 6, 3, '2024-05-03 09:00:00', '2024-05-06', 120.00),
(4, 8, 4, '2024-05-04 14:00:00', '2024-05-08', 160.00),
(5, 10, 1, '2024-05-05 08:00:00', '2024-05-07', 60.00),
(6, 12, 2, '2024-05-06 09:30:00', '2024-05-08', 45.00),
(7, 14, 3, '2024-05-07 10:15:00', '2024-05-10', 135.00),
(8, 16, 4, '2024-05-08 11:00:00', '2024-05-12', 180.00),
(9, 18, 1, '2024-05-09 12:45:00', '2024-05-11', 60.00),
(10, 20, 2, '2024-05-10 13:30:00', '2024-05-13', 90.00),
(11, 22, 3, '2024-05-11 14:00:00', '2024-05-14', 45.00),
(12, 24, 4, '2024-05-12 15:30:00', '2024-05-16', 180.00),
(13, 26, 1, '2024-05-13 16:45:00', '2024-05-15', 135.00),
(14, 28, 2, '2024-05-14 17:00:00', '2024-05-17', 60.00),
(15, 30, 3, '2024-05-15 18:15:00', '2024-05-18', 90.00),
(16, 32, 4, '2024-05-16 19:00:00', '2024-05-20', 120.00),
(17, 34, 1, '2024-05-17 20:30:00', '2024-05-19', 45.00),
(18, 36, 2, '2024-05-18 21:00:00', '2024-05-21', 180.00),
(19, 38, 3, '2024-05-19 22:15:00', '2024-05-22', 160.00),
(20, 40, 4, '2024-05-20 23:00:00', '2024-05-24', 120.00),
(21, 1, 1, '2024-05-21 08:00:00', '2024-05-23', 90.00),
(22, 3, 2, '2024-05-22 09:30:00', '2024-05-25', 60.00),
(23, 5, 3, '2024-05-23 10:45:00', '2024-05-26', 120.00),
(24, 7, 4, '2024-05-24 11:00:00', '2024-05-28', 160.00),
(25, 9, 1, '2024-05-25 12:15:00', '2024-05-27', 60.00),
(1, 11, 2, '2024-05-26 13:30:00', '2024-05-29', 45.00),
(2, 13, 3, '2024-05-27 14:45:00', '2024-05-30', 135.00),
(3, 15, 4, '2024-05-28 15:00:00', '2024-06-01', 180.00),
(4, 17, 1, '2024-05-29 16:15:00', '2024-06-02', 60.00),
(5, 19, 2, '2024-05-30 17:30:00', '2024-06-03', 90.00),
(6, 21, 3, '2024-05-31 18:45:00', '2024-06-04', 45.00),
(7, 23, 4, '2024-06-01 19:00:00', '2024-06-05', 180.00),
(8, 25, 1, '2024-06-02 20:15:00', '2024-06-06', 135.00),
(9, 27, 2, '2024-06-03 21:30:00', '2024-06-07', 60.00),
(10, 29, 3, '2024-06-04 22:45:00', '2024-06-08', 90.00),
(11, 31, 4, '2024-06-05 23:00:00', '2024-06-09', 120.00),
(12, 33, 1, '2024-06-06 08:15:00', '2024-06-10', 45.00),
(13, 35, 2, '2024-06-07 09:30:00', '2024-06-11', 180.00),
(14, 37, 3, '2024-06-08 10:45:00', '2024-06-12', 160.00),
(15, 39, 4, '2024-06-09 11:00:00', '2024-06-13', 120.00),
(16, 1, 1, '2024-06-10 12:15:00', '2024-06-14', 90.00),
(17, 3, 2, '2024-06-11 13:30:00', '2024-06-15', 60.00),
(18, 5, 3, '2024-06-12 14:45:00', '2024-06-16', 120.00),
(19, 7, 4, '2024-06-13 15:00:00', '2024-06-17', 160.00),
(20, 9, 1, '2024-06-14 16:15:00', '2024-06-18', 60.00);


select * from Reservas;