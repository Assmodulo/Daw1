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
    marca varchar(15),
    modelo varchar(15),
    empleado char(9),
    constraint matricula primary key (matricula),
    constraint empleado foreign key (empleado) references empleados(dni)
);

insert into departamento (nombre,ubicacion) values('CONTABILIDAD','TERCERA PLANTA');
insert into empleados values('13000001A','ANTONIO','GARCIA FERNANDEZ','2008-11-12',1);
insert into vehiculo values('0000AAA','RENAULT','RAPIDO','13000001A');

insert into empleados (dni, nombre, apellidos, fecha_nacimiento) values ('13000002B','PEPE','RODRIGUEZ','2000-12-12'),('13000003C','JUAN','DIAZ PEREZ','1995-11-06'),('13000004D','MARIA','GONZALEZ PI','1996-09-07');
insert into vehiculo (matricula) values ('1234ABC');

#En el enunciado del ejercicio dice cambiar el nombre al empleado numero 2, en nuestro caso, como el numero
#2 no existe, si no que los identificamos por el dni es aquel cuyo dni sea el 13000002B

update empleados set nombre = 'CARLOS' where dni='13000002B';

#cambiar el nombre del departamento 1, el único que hay, por IT

update departamento set nombre ='IT' where id=1;

#En el mismo caso que cambiar el nombre del empleado, para el vehiculo el where será la matricula

update vehiculo set marca='SEAT', modelo='IBIZA' where matricula='1234ABC';

#Eliminar al empleado numero 4, en este caso por dni 13000004D

delete from empleados where dni='13000004D';

#elimina el coche de empresa numero 2, where, matricula = 1234ABC

delete from vehiculo where matricula='1234ABC';

delete from vehiculo;
delete from empleados;
delete from departamento;


