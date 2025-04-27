drop database if exists videoDawData;
create database videoDawData;
drop user if exists developer@localhost;


-- Despues de crear la base de datos le indicamos que use esa base de datos
use videoDawData;

-- Tenemos que crear el usuario developer

create user developer@localhost identified by '1234';

grant all on videodawdata.* to developer@localhost;

-- Para ejecutar el script las veces que me haga falta y no de errores le indico que dropee las tablas si existen al inicio

drop table if exists videoclubs,
					 clientes,
                     cli_video,
                     articulos,
                     alquileres;

-- Vamos creando ahora las tablas que necesito

-- Voy a necesitar una tabla para almacenar los diferentes videoclubs de la franquicia
                     
create table videoclubs(
	cod_videoclub char(9) unique not null,
    nombre_videoclub varchar(50) not null,
    direccion varchar(50) not null,
    telefono char(9),
    primary key (cod_videoclub)
);

-- Necesito después una tabla que vaya almacenando todos los clientes
-- Para intentar normalizar la tabla lo que necesito es indicar en la tabla clientes la clave del 
-- videoclub al que pertenece como foreign key para no tener que crear una tabla intermedia

create table clientes(
	cod_cliente char(8) unique not null,
    dni char(9) unique,
    nombre varchar(25) not null,
    apellidos varchar(50) not null,
    f_nacimiento date not null,
    f_alta date not null,
    f_baja date default null,
    telefono char(9),
    cod_videoclub char(9) not null,
    
    primary key (cod_cliente),
    foreign key (cod_videoclub) references videoclubs (cod_videoclub)
		on delete cascade
);

-- Despues necesito una tabla de artículos, en principio iba a crear dos, una para películas y otra para videojuegos
-- Pero al ir trabajando con más tablas se ve que no es factible porque tendría que tener diversas tablas intermedias
-- Como un artículo sólo puede pertenecer a un videoclub en un determinado momento eso es una relación 1:N, por lo que
-- incluyo la clave de videoclub como FK en la tabla articulos

create table articulos(
	cod_articulo char(8) unique not null,
    tipo enum ('PELICULA','VIDEOJUEGO'),
    titulo varchar(30) not null,
    genero varchar(25)not null,
    f_alta date not null,
    f_baja date default null,
    alquilada boolean default false,
    cod_videoclub char(9) not null,
    
    primary key (cod_articulo),
    foreign key (cod_videoclub) references videoclubs (cod_videoclub)
		on delete cascade
);


-- Creo una tabla alquileres en la que vamos a ir almacenando las diferentes transacciones del videoclub

create table alquileres(
	cod_videoclub char(9) not null,
    cod_articulo char(8) not null,
    cod_socio char(8) not null,
    f_alquiler datetime not null,
    f_devolucion datetime default (null),
    primary key(cod_videoclub, cod_articulo, cod_socio, f_alquiler),
    foreign key (cod_socio) references clientes (cod_cliente)
		on delete cascade,
    constraint fkarticulo1 foreign key (cod_articulo) references articulos(cod_articulo)
		on delete cascade
);


select * from clientes;

select cod_cliente, dni, nombre, apellidos, f_nacimiento,
f_alta, telefono from clientes where cod_videoclub = 'A12345678' and f_baja is null;

select * from alquileres;
