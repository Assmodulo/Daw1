#Con esto vamos creando la base de datos y nos aseguramos de que la borremos en caso
#de que ya exista

DROP DATABASE IF exists viajeros;
CREATE DATABASE viajeros;

Use viajeros;

#Creo las primeras tablas, aquellas que no tienen dependencias.

CREATE TABLE provincia(
	CP char(2) NOT NULL UNIQUE,
    nombre varchar(15) NOT NULL,
    CONSTRAINT CP primary key (CP)
);

#Creo la tabla localidad
CREATE table localidad(
	cp_completo char(5) NOT NULL,
    localidad varchar(20),
    cp char(2) NOT NULL,
    CONSTRAINT cp_completo primary key (cp_completo),
    CONSTRAINT cp foreign key (cp) references provincia (CP)
);

#Creo la tabla departamento
CREATE TABLE departamento(
	cod_dep SMALLINT AUTO_INCREMENT UNIQUE NOT NULL,
    departamento VARCHAR(15) NOT NULL,
    CONSTRAINT cod_dep primary key (cod_dep)
);

#Creo la tabla empresas_vehiculos
CREATE TABLE empresa_vehiculos(
	cif CHAR(9) UNIQUE NOT NULL,
    nombre_empresa VARCHAR(15) NOT NULL,
	direccion VARCHAR(25) NOT NULL,
    localidad VARCHAR(20) NOT NULL,
    telefono CHAR(9) NOT NULL,
    CONSTRAINT cif primary key (cif)
);

#Para terminar con las tablas sin dependencias, salvo que se me haya pasado alguna
#creo la tabla paquetes
CREATE TABLE paquetes(
	cod_paquete CHAR(4) UNIQUE NOT NULL,
    nombre_paquete VARCHAR(15) NOT NULL,
    descripcion VARCHAR(30) NOT NULL,
    direccion VARCHAR(25) NOT NULL,
    precio DECIMAL(6,2) NOT NULL,
    num_max_personas SMALLINT NOT NULL,
    CONSTRAINT cod primary key (cod_paquete)
);
    
#Creo la tabla alojamientos, que también es independiente.