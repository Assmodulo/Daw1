#Con esto vamos creando la base de datos y nos aseguramos de que la borremos en caso
#de que ya exista
DROP DATABASE IF exists viajeros;
CREATE DATABASE viajeros;

Use viajeros;

CREATE TABLE provincia(
    CP char(2) NOT NULL UNIQUE,
    nombre varchar(50) NOT NULL, 
    CONSTRAINT CP primary key (CP)
);

 insert into provincia values 
    ('39','Cantabria'),
    ('08','Barcelona'),
    ('28','Madrid'),
    ('01','Alava'),
    ('11','Cadiz'),
    ('23','Jaen'),
    ('37','Salamanca');

-- Tabla localidad
CREATE table localidad(
    cp_completo char(5) NOT NULL,
    localidad varchar(50), 
    cp char(2) NOT NULL,
    CONSTRAINT cp_completo primary key (cp_completo),
    CONSTRAINT cp foreign key (cp) references provincia (CP)
);

 insert into localidad values
    ('01001','Alberca Vieja','01'),
    ('01007','Alto de Prado','01'),
    ('39200','Reinosa','39'),
    ('39400','Los Corrales de Buelna','39'),
    ('39460','Cartes','39'),
    ('11220','ValdePrado','11'),
    ('23000','Jaen','23'),
    ('23440','Baeza','23'),
    ('28000','Madrid','28'),
    ('28801','Alcalá de Henares','28'),
    ('08000','Barcelona','08'),
    ('08860','Casteldefels','08'),
    ('37000','Salamanca','37');

CREATE TABLE departamento(
	cod_dep SMALLINT AUTO_INCREMENT UNIQUE NOT NULL,
    departamento VARCHAR(15) NOT NULL,
    CONSTRAINT cod_dep primary key (cod_dep)
);

  insert into departamento values
    (1,'Administracion'),
    (2,'Mantenimiento'),
    (3,'Marketing'),
    (4,'Monitores');

-- Tabla empresa_vehiculos
CREATE TABLE empresa_vehiculos(
    cif CHAR(9) UNIQUE NOT NULL,
    nombre_empresa VARCHAR(50) NOT NULL, 
    direccion VARCHAR(100) NOT NULL, 
    localidad VARCHAR(50) NOT NULL, 
    telefono CHAR(9) NOT NULL,
    CONSTRAINT cif primary key (cif)
);

  insert into empresa_vehiculos values
    ('11111111A','CARRENT','Calle Inventada','Madrid','645789123'),
    ('22222222B','COGEMYCOCHEYCORRE','Poligono Los Mecánicos','Santander','666123456'),
    ('33333333V','TRIPPROVIDER','Avda. Los Árboles','Barcelona','777789789');

-- Tabla paquetes
CREATE TABLE paquetes(
    cod_paquete CHAR(4) UNIQUE NOT NULL,
    nombre_paquete VARCHAR(50) NOT NULL, 
    descripcion VARCHAR(100) NOT NULL, 
    direccion VARCHAR(100) NOT NULL, 
    precio DECIMAL(6,2) NOT NULL,
    num_max_personas SMALLINT NOT NULL,
    CONSTRAINT cod primary key (cod_paquete)
);

 insert into paquetes values
    ('01AB','Barranquismo1','Barranquismo inicial','Alto Los Montes',350.90,12),
    ('01CD','Kayaks','Kayaks Principiante','Bahía Loa Ahogaos',290.50,8),
    ('84X4','Parapente','Parapente Guíado','CumbresVentosas',675.50,6),
    ('46BJ','Senderismo','Rutas tranquilas','ColinasSerenas',121.16,24),
    ('86C1','Ruta Globo','Rutas Globo','Airesfrescos',1121.95,4),
    ('12ZX','Conduccion 2','Rutas en Todoterreno Zonas Abruptas','La Cuesta Empiná',520.00,16),
    ('6912','Paracaidismo','Saltos desde avioneta','Aerodromo Militar',945.56,4),
    ('01AC','Barranquismo2','Barranquismo Rutas Intermedias','Alto de la Sierra',421.75,10),
    ('46BK','Senderismo2','Marchas Avanzadas','Villa Olivos',195.80,18),
    ('84X5','Parapente2','Parapente Libre Asistido','Aires Turbulentos',721.40,5),
    ('R145','Rafting','Descenso Aguas Bravas','Rio Rapido',285.66,16);

-- Tabla alojamientos
CREATE TABLE alojamientos(
    cod_alojamientos CHAR(6) UNIQUE NOT NULL,
    nombre_alojamiento VARCHAR(100) NOT NULL, 
    tipo VARCHAR(50) NOT NULL, 
    direccion VARCHAR(100) NOT NULL, 
    localidad VARCHAR(50) NOT NULL, 
    telefono CHAR(9) NOT NULL,
    categoria VARCHAR(50), 
    CONSTRAINT cod_alojamientos primary key (cod_alojamientos)
);

 insert into alojamientos values
    ('H0001','Hotel Royal','Hotel','Calle Principal','Los Gentios','665664663','2'),
    ('P0002','Posada La Fonda','Posada','Avda. Las Encianas','Patios de Abajo','61234567','3'),
    ('H0002','Hotal Magno','Hotel','Paseo las Bodegas','Las Alforjas','987654321','4'),
    ('C0014','Camping El Fresno','Camping','Barrio El Metal','Villatranquila','123456789','2'),
    ('HO041','Hostal Regio','Hosta','Calle Secundaria','Barrio Encinar','987456445','1');

#Creo la tabla grupos
    CREATE TABLE grupos(
		id_grupo INT AUTO_INCREMENT UNIQUE NOT NULL,
        num_personas INT NOT NULL,
        CONSTRAINT id_grupo primary key(id_grupo)
    );
    
     insert into grupos values
    (1,2),
    (2,3),
    (3,3),
    (4,3),
    (5,2);

-- Tabla clientes
CREATE TABLE clientes(
    dni CHAR(9) UNIQUE NOT NULL,
    nombre_completo VARCHAR(100) NOT NULL, 
    f_nacim DATE NOT NULL,
    telefono CHAR(9) NOT NULL,
    direccion VARCHAR(100) NOT NULL, 
    email VARCHAR(100) NOT NULL,
    cp_comp CHAR(5) NOT NULL,
    CONSTRAINT dni primary key (dni),
    CONSTRAINT cp_comp foreign key(cp_comp) references localidad(cp_completo)
);

insert into clientes values
('12345678A', 'José Fernández', '1980-05-15', '612345678', 'Calle Sol, 12', 'jose.fernandez@correo.com', '39200'),
('87654321B', 'Laura Gómez', '1992-08-25', '623456789', 'Calle Luna, 45', 'laura.gomez@correo.com', '39460'),
('11223344C', 'Carlos Ruiz', '1987-02-18', '634567890', 'Calle Estrella, 32', 'carlos.ruiz@correo.com', '28000'),
('22334455D', 'María Díaz', '1990-10-05', '645678901', 'Calle del Sol, 56', 'maria.diaz@correo.com', '08000'),
('33445566E', 'David Martín', '1985-04-30', '656789012', 'Calle Aire, 78', 'david.martin@correo.com', '23000'),
('44556677F', 'Sara López', '1993-12-11', '667890123', 'Calle Olmo, 21', 'sara.lopez@correo.com', '01001'),
('12345679A', 'Juan Pérez', '1990-05-21', '611223344', 'Calle Mayor 1', 'juan.perez@email.com', '01001'),
('23456789B', 'Ana Gómez', '1985-11-15', '622334455', 'Avda. del Sol 3', 'ana.gomez@email.com', '01007'),
('34567890C', 'Carlos Rodríguez', '1992-03-30', '633445566', 'Calle Luna 5', 'carlos.rodriguez@email.com', '39200'),
('45678901D', 'María López', '1988-07-10', '644556677', 'Paseo del Prado 2', 'maria.lopez@email.com', '39400'),
('56789012E', 'Luis Martín', '1980-01-22', '655667788', 'Avda. Cataluña 10', 'luis.martin@email.com', '39460'),
('67890123F', 'Elena Sánchez', '1995-06-18', '666778899', 'Calle Flor 12', 'elena.sanchez@email.com', '11220'),
('78901234G', 'Pedro Ruiz', '1993-12-02', '677889900', 'Calle del Viento 8', 'pedro.ruiz@email.com', '28000'),
('89012345H', 'Laura Fernández', '1997-09-25', '688990011', 'Calle Nueva 15', 'laura.fernandez@email.com', '08860'),
('90123456I', 'David González', '1991-08-10', '699001122', 'Calle Los Olivos 4', 'david.gonzalez@email.com', '37000'),
('12345679J', 'Sara Martínez', '1996-04-20', '710112233', 'Calle Verde 7', 'sara.martinez@email.com', '23440'),
('23456780K', 'José Díaz', '1982-12-14', '721223344', 'Calle del Mar 18', 'jose.diaz@email.com', '28801'),
('34567891L', 'Ricardo Ramírez', '1994-11-01', '732334455', 'Avda. España 6', 'ricardo.ramirez@email.com', '08000'),
('45678902M', 'Marta Ruiz', '1986-03-17', '743445566', 'Paseo Robledal 21', 'marta.ruiz@email.com', '23000');


-- Tabla sedes
CREATE TABLE sedes(
    cod_sede INT auto_increment NOT NULL UNIQUE,
    direccion VARCHAR(100) NOT NULL,
    telefono CHAR(9) NOT NULL,
    email VARCHAR(100) NOT NULL,
    cp_completo CHAR(5) NOT NULL,
    CONSTRAINT cod_sede primary key (cod_sede),
    CONSTRAINT cp_completo foreign key (cp_completo) references localidad (cp_completo)
);

 insert into sedes values
    (1,'Los Bolaos','654321123','losbolaos@viaj.es','39200'),
    (2,'Barrio Pescadores','987654321','losbolaos@viaj.es','39460'),
    (3,'Alameda Central','987123456','losbolaos@viaj.es','28000'),
    (4,'Plaza España','654157542','losbolaos@viaj.es','08000'),
    (5,'Calle Olivar','365487546','losbolaos@viaj.es','23000'),
    (6,'Kortu Kalea','321578985','losbolaos@viaj.es','01001'),
    (7,'Espanya Kalea','325786421','losbolaos@viaj.es','01007'),
    (8,'Barrio Universidad','324879654','losbolaos@viaj.es','37000'),
    (9,'Avda. Independencia','647899878','losbolaos@viaj.es','08860'),
    (10,'Paseo Robledal','321357864','losbolaos@viaj.es','11220'),
    (11,'Calle Fulano Perez','978456546','losbolaos@viaj.es','23440'),
    (12,'Calle Los Leones','613214567','losbolaos@viaj.es','28801'),
    (13,'Carretera Soria','648791212','losbolaos@viaj.es','28000'),
    (14,'El Calçot','946547541','losbolaos@viaj.es','08000'),
    (15,'Los íberos 2','642134785','losbolaos@viaj.es','08860');

-- Tabla empleados
CREATE TABLE empleados(
    num_matricula INT AUTO_INCREMENT UNIQUE NOT NULL,
    dni CHAR(9) NOT NULL,
    num_ss CHAR(12) NOT NULL,
    nombre_completo VARCHAR(100) NOT NULL, 
    fecha_nacim DATE NOT NULL,
    telefono CHAR(9) NOT NULL,
    email VARCHAR(100) NOT NULL,
    especialidad VARCHAR(50) NOT NULL,
    cod_departamento SMALLINT NOT NULL,
    sede INT NOT NULL,
    CONSTRAINT num_matricula primary key(num_matricula),
    CONSTRAINT cod_departamento foreign key(cod_departamento) references departamento(cod_dep),
    CONSTRAINT sede foreign key(sede) references sedes(cod_sede)
);

   insert into empleados values
(1, '11111111A', '123456789012', 'Carlos Martínez', '1990-04-12', '665123456', 'carlos.m@viaj.es', 'Monitor de Barranquismo', 4, 1),
(2, '22222222B', '234567890123', 'Ana López', '1985-02-23', '611234567', 'ana.l@viaj.es', 'Instructor de Kayaks', 4, 2),
(3, '33333333C', '345678901234', 'Luis García', '1988-03-15', '622345678', 'luis.g@viaj.es', 'Monitor de Parapente', 3, 3),
(4, '44444444D', '456789012345', 'María Rodríguez', '1992-07-18', '633456789', 'maria.r@viaj.es', 'Guía de Senderismo', 2, 4),
(5, '55555555E', '567890123456', 'Pedro Sánchez', '1989-11-30', '644567890', 'pedro.s@viaj.es', 'Instructor de Rafting', 1, 5),
(6, '66666666F', '678901234567', 'Lucía Pérez', '1995-01-01', '655678901', 'lucia.p@viaj.es', 'Monitor de Paracaidismo', 1, 6),
(7, '77777777G', '678901234567', 'Paco Fernandez', '1987-01-01', '655678901', 'paco.p@viaj.es', 'Mantenimiento', 2, 3),
(8, '88888888H', '678901234567', 'Jorge Jimenez', '1999-01-01', '655678901', 'jorge.j@viaj.es', 'Mantenimiento', 2, 4),
(9, '99999999I', '678901234567', 'Lola Lorenzo', '2000-01-01', '655678901', 'lola.l@viaj.es', 'Mantenimiento', 2, 6);


 CREATE TABLE oferta(
		sede INT NOT NULL,
        paquete CHAR(4) NOT NULL,
        CONSTRAINT oferta_sedes primary key(sede, paquete),
        CONSTRAINT sedes foreign key(sede) references sedes(cod_sede),
        CONSTRAINT paquetes foreign key(paquete) references paquetes(cod_paquete)
    );
    
insert into oferta values
(1, '01AB'),
(2, '01CD'),
(3, '84X4'),
(4, '46BJ'),
(5, '86C1'),
(6, '12ZX'),
(7, '6912'),
(8, '01AC'),
(9, '46BK'),
(10, '84X5'),
(11, 'R145'), 
(1, '01CD'),  
(2, '01AC'),  
(2, '46BK'), 
(3, '46BJ'),  
(4, '12ZX'),  
(4, '6912'),  
(5, '84X5'),  
(5, 'R145'),  
(6, '01AB'),  
(6, '46BK'),  
(7, 'R145'),  
(7, '12ZX'),   
(8, '6912'),  
(9, '46BJ'),  
(9, '86C1'),  
(10, '84X4'), 
(10, '01CD'), 
(11, '46BK'), 
(11, '84X5'), 
(12, '12ZX'), 
(12, 'R145'); 

    
     CREATE TABLE componentes(
		dni CHAR(9) NOT NULL,
        id_grupo INT NOT NULL,
        CONSTRAINT componentes primary key(dni, id_grupo),
        CONSTRAINT miembro foreign key(dni) references clientes(dni),
        CONSTRAINT grupo foreign key(id_grupo) references grupos(id_grupo)
    );
    
insert into componentes values
('12345678A', 1),  
('23456789B', 1),  
('34567890C', 2),  
('45678901D', 2),  
('56789012E', 2), 
('67890123F', 3), 
('78901234G', 3),  
('89012345H', 3), 
('90123456I', 4), 
('12345679J', 4),
('23456780K', 4),
('34567891L', 5),
('45678902M', 5);

    
     CREATE TABLE reservas(
		cod_reserva INT AUTO_INCREMENT UNIQUE NOT NULL,
        fecha_reserva DATE NOT NULL,
        fecha_fin_reserva DATE,
        precio DECIMAL(6,2) NOT NULL,
        grupo_completo BOOLEAN default false,
        id_grupo INT NOT NULL,
        CONSTRAINT cod_reserva primary key(cod_reserva),
        CONSTRAINT grupo_reserva foreign key(id_grupo) references grupos(id_grupo)
    );
    
    CREATE TABLE seguros(
		num_poliza CHAR(6) UNIQUE NOT NULL,
        nombre_aseguradora VARCHAR (20) NOT NULL,
        tipo VARCHAR(12) NOT NULL,
        precio DECIMAL(6,2) NOT NULL,
        f_ini_poliza DATE NOT NULL,
        f_fin_poliza DATE NOT NULL,
        cod_sede INT NOT NULL,
        CONSTRAINT num_poliza primary key(num_poliza),
        CONSTRAINT sede_contrato foreign key(cod_sede) references sedes(cod_sede)
    );
    
    insert into seguros values
('P001', 'Aseguradora X', 'Accidente', 120.50, '2025-01-01', '2025-12-31', 1),
('P002', 'Aseguradora Y', 'Todo Riesgo', 200.00, '2025-02-01', '2025-12-31', 2),
('P003', 'Aseguradora Z', 'Accidente', 110.00, '2025-03-01', '2025-12-31', 3),
('P004', 'Aseguradora W', 'Robo', 150.00, '2025-04-01', '2025-12-31', 4),
('P005', 'Aseguradora V', 'Accidente', 130.00, '2025-05-01', '2025-12-31', 5),
('P006', 'Aseguradora U', 'Todo Riesgo', 175.00, '2025-06-01', '2025-12-31', 6);

    
#Como indico en el informe puedo realizar este cambio aquí para optimizar la BD y el diagrama
ALTER TABLE reservas ADD num_poliza CHAR(6) NOT NULL;
ALTER TABLE reservas ADD CONSTRAINT poliza_reserva foreign key(num_poliza) references seguros(num_poliza);
    
insert into reservas values
(1, '2025-01-10', '2025-01-15', 450.00, true, 1,'P001'),
(2, '2025-02-01', '2025-02-05', 320.00, false, 2,'P002'),
(3, '2025-03-12', '2025-03-20', 675.50, true, 3,'P003'),
(4, '2025-04-18', '2025-04-22', 295.00, false, 4,'P004'),
(5, '2025-05-05', '2025-05-10', 520.00, true, 5,'P005');




-- Tabla actividad
CREATE TABLE actividad(
    cod_reserva INT NOT NULL,
    cod_paquete CHAR(4) NOT NULL,
    CONSTRAINT actividades primary key(cod_reserva, cod_paquete),
    CONSTRAINT reserva foreign key (cod_reserva) references reservas(cod_reserva),
    CONSTRAINT paquete foreign key (cod_paquete) references paquetes(cod_paquete)
);

INSERT INTO actividad VALUES 
    (1, '01AB'),
    (1, '01CD'),
    (2, '6912'),
    (3, '12ZX'),
    (3, '01AC'),
    (4, '84X5'),
    (5, '46BK'),
    (5, 'R145'),
    (5, '86C1');
   

 #Vamos con la relación entre sedes y alojamientos
    CREATE TABLE reservas_alojamientos(
		cod_reserva_aloja CHAR(6) UNIQUE NOT NULL,
        fecha_inicio_reserva DATE NOT NULL,
        fecha_fin_reserva DATE NOT NULL,
        cod_sede INT NOT NULL,
        cod_alojamiento CHAR(6) NOT NULL,
        CONSTRAINT cod_reserva_aloja primary key(cod_reserva_aloja),
        CONSTRAINT sede_reserva foreign key(cod_sede) references sedes(cod_sede),
        CONSTRAINT alojamiento_reserva foreign key(cod_alojamiento) references alojamientos(cod_alojamientos)
    );
    
    
    INSERT INTO reservas_alojamientos VALUES
('RALO01', '2025-03-01', '2025-03-03', 1, 'H0001'),
('RALO02', '2025-03-05', '2025-03-07', 2, 'P0002'),
('RALO03', '2025-03-10', '2025-03-12', 3, 'H0002'),
('RALO04', '2025-03-15', '2025-03-17', 4, 'H0001'),
('RALO05', '2025-03-18', '2025-03-20', 5, 'C0014'),
('RALO06', '2025-03-22', '2025-03-24', 6, 'HO041'),
('RALO07', '2025-03-25', '2025-03-27', 7, 'H0002'),
('RALO08', '2025-03-29', '2025-03-31', 8, 'H0002');

    
    
     #Creo la tabla equipamientos
    CREATE TABLE equipamiento(
		cod_equipamiento CHAR(6) UNIQUE NOT NULL,
        tipo_equipamiento VARCHAR(50) NOT NULL,
        modelo VARCHAR(25) NOT NULL,
        plazas SMALLINT,
        cod_sede INT NOT NULL,
        CONSTRAINT cod_equipamiento primary key(cod_equipamiento),
        CONSTRAINT sede_propietaria foreign key(cod_sede) references sedes(cod_sede)
    );
    
insert into equipamiento values
('E0001', 'Cuerda de Barranquismo', 'Cuerda Alta Resistencia', 10, 1),
('E0002', 'Kayaks', 'Kayak Paddler', 6, 2),
('E0003', 'Parapente', 'Parapente Flex', 4, 3),
('E0004', 'Botas de Senderismo', 'Botas Trekking', 20, 4),
('E0005', 'Rafting', 'Balsa de Seguridad', 12, 5),
('E0006', 'Paracaídas', 'Paracaídas Desplegable', 2, 6);

    
     #Rematamos las relaciones que tengan que ver con la tabla equipamiento
    CREATE TABLE equipo(
		num_equipo SMALLINT UNIQUE NOT NULL,
        num_tecnicos SMALLINT NOT NULL default 1,
        CONSTRAINT num_equipo primary key(num_equipo)
    );
    
    INSERT INTO equipo VALUES
    (1, 1),
    (2, 2),
    (3, 2),  
    (4, 1);  

    
     CREATE TABLE miembros_equipo(
		num_equipo SMALLINT NOT NULL,
        num_matricula INT NOT NULL,
        CONSTRAINT equipo primary key(num_equipo, num_matricula),
        CONSTRAINT cod_equipo foreign key(num_equipo) references equipo(num_equipo),
        CONSTRAINT cod_empleado foreign key(num_matricula) references empleados(num_matricula)
    );
    
    insert into miembros_equipo values
    (1,'7'),
    (2,'8'),
    (2,'9'),
    (3,'4'),
    (3,'8'),
    (4,'9');
    
      #Ahora solo queda crear la tabla Ficha_revision
    CREATE TABLE ficha_revision(
		cod_revision CHAR(6) UNIQUE NOT NULL,
        fecha_revision DATE NOT NULL,
        favorable TINYINT NOT NULL,
        num_equipo SMALLINT NOT NULL,
        cod_equipamiento CHAR(6) NOT NULL,
        CONSTRAINT cod_revision primary key(cod_revision),
        CONSTRAINT equipo_encargado foreign key(num_equipo) references equipo(num_equipo),
        CONSTRAINT equipo_revisado foreign key(cod_equipamiento) references equipamiento(cod_equipamiento)
    );
    
    insert into ficha_revision values
    ('REV001','2018-12-06',1,1,'E0003'),
    ('REV002','2020-02-13',0,3,'E0006'),
    ('REV003','2021-07-11',1,4,'E0001');
    

    

-- Tabla vehículos
CREATE TABLE vehiculos(
    matricula CHAR(7) UNIQUE NOT NULL,
    tipo VARCHAR(50) NOT NULL, 
    plazas SMALLINT NOT NULL,
    cif CHAR(9) NOT NULL,
    CONSTRAINT matricula primary key(matricula),
    CONSTRAINT identificador_fiscal foreign key(cif) references empresa_vehiculos(cif)
);

 CREATE TABLE alquiler_vehiculos(
 
		cod_alquiler CHAR(6) UNIQUE NOT NULL,
        f_inicio DATE NOT NULL,
        f_fin DATE NOT NULL,
        matricula CHAR(7) NOT NULL,
        cod_sede INT NOT NULL,
        num_poliza CHAR(6) NOT NULL,
        CONSTRAINT cod_alquiler primary key(cod_alquiler),
        CONSTRAINT matricula foreign key(matricula) references vehiculos(matricula),
        CONSTRAINT sede_encargada foreign key(cod_sede) references sedes(cod_sede),
        CONSTRAINT poliza_seguro foreign key(num_poliza) references seguros(num_poliza)
    );
    
    insert into vehiculos values
('1234567', 'Furgoneta', 3, '11111111A'),
('2345678', 'Camión', 10, '22222222B'),
('3456789', 'Coche', 5, '33333333V'),
('4567890', 'Furgoneta', 4, '11111111A'),
('5678901', 'Coche', 5, '22222222B');

insert into alquiler_vehiculos values
('A001', '2025-01-15', '2025-01-20', '1234567', 1, 'P001'),
('A002', '2025-02-10', '2025-02-15', '2345678', 2, 'P002'),
('A003', '2025-03-05', '2025-03-10', '3456789', 3, 'P003'),
('A004', '2025-04-12', '2025-04-17', '4567890', 4, 'P004'),
('A005', '2025-05-20', '2025-05-25', '5678901', 5, 'P005');


-- Tabla agencias
CREATE TABLE agencias(
    cod_agencia CHAR(6) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    telefono CHAR(9) NOT NULL,
    persona_contacto VARCHAR(100) NOT NULL,
    CONSTRAINT cod_agencia primary key(cod_agencia)
);

insert into agencias values
('A001', 'Viajes Exprés', 'Calle Mayor, 45', '654123987', 'Ana Gómez'),
('A002', 'Rutas y Destinos', 'Avenida del Sol, 12', '987654321', 'Carlos Sánchez'),
('A003', 'Explora Mundo', 'Plaza Europa, 3', '612345678', 'Lucía Pérez');


CREATE TABLE oferta_agencia(
		cod_paquete CHAR(4) NOT NULL,
        cod_agencia CHAR(6) NOT NULL,
        CONSTRAINT oferta_agencias primary key(cod_paquete, cod_agencia),
        CONSTRAINT agencia foreign key(cod_agencia) references agencias(cod_agencia),
        CONSTRAINT paquete_agencia foreign key(cod_paquete) references paquetes(cod_paquete)
    );
    
insert into oferta_agencia values
('01AB','A001'),
('84X4','A002'),
('86C1','A002'),
('R145','A002'),
('6912','A003');
    
    CREATE TABLE reservas_agencias(
		cod_agencia CHAR(6) NOT NULL,
        cod_reserva INT NOT NULL,
        CONSTRAINT referencia_reservas primary key(cod_agencia, cod_reserva),
        CONSTRAINT agencia_reserva foreign key(cod_agencia) references agencias(cod_agencia),
        CONSTRAINT reserva_externa foreign key(cod_reserva) references reservas(cod_reserva)
    );
    
    insert into reservas_agencias values
    ('A001','1'),
    ('A003','5');
    
    
-- UPDATES

-- Este update es para actualizar los precios según el coste de la vida
select * from paquetes;
update paquetes p, paquetes p1 set p.precio = p.precio * 1.1, p1.precio = p1.precio * 1.05 where p.precio <= 600.00 and p1.precio > 600;
select * from paquetes;

-- Este update sirve para lo mismo pero con el rango de precio de los paquetes más caros
-- select * from paquetes;
-- update paquetes set precio = precio + (precio * 0.05) where precio > 600.00;
-- select * from paquetes;


-- Voy a intentar hacer un update del precio de las reservas basado en la suma de los precios de los paquetes contratados en esas reservas
select * from reservas;

	update reservas r
    set r.precio =
	(select sum(listado.precio) as precio_final from
	(select r.cod_reserva,p.precio, p.cod_paquete
    from paquetes p
    join actividad a on p.cod_paquete = a.cod_paquete
    join reservas r on a.cod_reserva = r.cod_reserva) as listado
    where listado.cod_reserva = 1
    group by listado.cod_reserva)
    where r.cod_reserva = 1;
    
select * from reservas;

	update reservas r
    set r.precio =
	(select sum(listado.precio) as precio_final from
	(select r.cod_reserva,p.precio, p.cod_paquete
    from paquetes p
    join actividad a on p.cod_paquete = a.cod_paquete
    join reservas r on a.cod_reserva = r.cod_reserva) as listado
    where listado.cod_reserva = 2
    group by listado.cod_reserva)
    where r.cod_reserva = 2;
select * from reservas;

	update reservas r
    set r.precio =
	(select sum(listado.precio) as precio_final from
	(select r.cod_reserva,p.precio, p.cod_paquete
    from paquetes p
    join actividad a on p.cod_paquete = a.cod_paquete
    join reservas r on a.cod_reserva = r.cod_reserva) as listado
    where listado.cod_reserva = 3
    group by listado.cod_reserva)
    where r.cod_reserva = 3;
select * from reservas;

	update reservas r
    set r.precio =
	(select sum(listado.precio) as precio_final from
	(select r.cod_reserva,p.precio, p.cod_paquete
    from paquetes p
    join actividad a on p.cod_paquete = a.cod_paquete
    join reservas r on a.cod_reserva = r.cod_reserva) as listado
    where listado.cod_reserva = 4
    group by listado.cod_reserva)
    where r.cod_reserva = 4;
select * from reservas;

	update reservas r
    set r.precio =
	(select sum(listado.precio) as precio_final from
	(select r.cod_reserva,p.precio, p.cod_paquete
    from paquetes p
    join actividad a on p.cod_paquete = a.cod_paquete
    join reservas r on a.cod_reserva = r.cod_reserva) as listado
    where listado.cod_reserva = 5
    group by listado.cod_reserva)
    where r.cod_reserva = 5;
select * from reservas;

-- Una de las agencias colaboradoras ha decidido dejar de vender el paquete viaje en globo porque le da poca salida
-- Y ha decidido cambiarlo por el otro paquete de parapente y expecializarse en ello
select * from oferta_agencia;

update oferta_agencia oa set oa.cod_paquete = '84X5' where oa.cod_paquete = '86C1' and oa.cod_agencia = 'A002';

select * from oferta_agencia;

-- Cambiar el nombre de departamento de mantenimiento y la descripción en la tabla trabajadores y corregir un error en la tabla empleados por el cual un empleado
-- de mantenimiento tenia una descripción que no concuerda

select * from departamento d join empleados e on d.cod_dep = e.cod_departamento where d.cod_dep = 2;

update departamento d, empleados e 
set d.departamento = 'Dep. Técnico', e.especialidad = 'Tecnico Mantenimiento' 
where d.departamento = 'Mantenimiento' and e.especialidad in ('Mantenimiento' , 'Guía de Senderismo');

select * from departamento d join empleados e on d.cod_dep = e.cod_departamento where d.cod_dep = 2;


-- Una de las reservas ha decidido quedarse más días de lo planeado inicialmente sobre la marcha por lo que debemos de cambiar la fecha
-- y aumentar el precio en un 25%
select * from reservas;

update reservas r set r.fecha_fin_reserva = adddate(r.fecha_fin_reserva,interval 5 day), r.precio = precio * 1.25 where r.cod_reserva = 4;

select * from reservas;

-- Contar sedes por provincia
select p.nombre, count(s.cod_sede) as 'Sucursales'
from provincia p
join localidad l on p.cp = l.cp
join sedes s on l.cp_completo = s.cp_completo 
group by p.nombre;

-- Calcular que agencia ha facturado más
select a.nombre, r.cod_reserva, r.precio
from agencias a
join reservas_agencias re on a.cod_agencia = re.cod_agencia
join reservas r on r.cod_reserva = re.cod_reserva
where r.precio in(select max(r.precio) 
from reservas r 
join reservas_agencias re on r.cod_reserva = re.cod_reserva);


-- Calcular la suma de los paquetes que componen cada reserva
select listado.cod_reserva,sum(listado.precio) from
(select r.cod_reserva,p.precio, p.cod_paquete
from paquetes p
join actividad a on p.cod_paquete = a.cod_paquete
join reservas r on a.cod_reserva = r.cod_reserva) as listado
group by listado.cod_reserva;

-- Calcular el numero medio de componentes de los grupos que reservan

select avg(g.num_personas) from grupos g;

-- Queremos conocer cuantos clientes han contratado cada paquete ordenados de mayor a menor

select listado.nombre_paquete as 'Paquete', count(listado.nombre_completo) as 'Clientes' from
(select p.nombre_paquete, c.nombre_completo from clientes c 
join componentes co on c.dni = co.dni
join grupos g on co.id_grupo = g.id_grupo
join reservas r on r.id_grupo = g.id_grupo
join actividad a on r.cod_reserva = a.cod_reserva
join paquetes p on p.cod_paquete = a.cod_paquete)as listado
group by listado.nombre_paquete
order by count(listado.nombre_completo) desc;

-- Seleccionar los paquetes que no se han venido nunca
select p.cod_paquete, p.nombre_paquete from paquetes p
where not exists
(select a.cod_paquete
from actividad a
where p.cod_paquete = a.cod_paquete);




