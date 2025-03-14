drop database if exists DAW_PR4_1;
drop user decroly;
create database DAW_PR4_1;


use DAW_PR4_1;

create table alumnos(
	nombre varchar(20) not null,
    apellidos varchar(20) not null,
    f_nacimiento date not null
);

insert into alumnos values
('Jose','Perez','2000-01-01'),
('Agustin','Rodriguez','2001-01-01'),
('Maria','Rodríguiz','1999-01-01');

create user 'decroly' identified by 'decroly';

show databases;

#Después de hacer las pruebas del ejercicio 1 como el usuario decroly, volvemos a partir de aquí a conectarnos como root
grant select on alumnos to decroly;
grant update (f_nacimiento) on DAW_PR4_1.alumnos to decroly;

