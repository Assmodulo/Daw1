#comienzo con los comandos básicos de la base de datos
drop database if exists empresa;
create database empresa;

use empresa;

create table departamento(
	id smallint auto_increment not null,
    nombre varchar(15) not null,
    ubicacion varchar(15) not null,
    constraint id primary key (id)
);

create table empleados(
	dni char(9) unique not null,
    nombre varchar(15) not null,
    apellidos varchar(25) not null,
    fecha_nacimiento date not null,
    departamento smallint,
    constraint dni primary key (dni),
    constraint departamento foreign key(departamento) references departamento(id)
    );
    
create table vehiculo(
	matricula char(7) unique not null,
    marca varchar(15) not null,
    modelo varchar(15) not null,
    empleado char(9) not null,
    constraint matricula primary key (matricula),
    constraint empleado foreign key (empleado) references empleados(dni)
);

insert into departamento (nombre,ubicacion) values('CONTABILIDAD','TERCERA PLANTA');
insert into empleados values('13000001A','ANTONIO','GARCIA FERNANDEZ','2008-11-12',1);
insert into vehiculo values('0000AAA','RENAULT','RAPIDO','13000001A');

insert into empleados (dni, nombre, apellidos, fecha_nacimientos) values ('13000002B','PEPE','RODRIGUEZ','2000-12-12');
    
