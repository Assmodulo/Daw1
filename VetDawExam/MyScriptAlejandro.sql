drop database VetDaw;

create database if not exists VetDaw;

use VetDaw;

drop user if exists developer@localhost;

create user developer@localhost identified by '1234';

grant all on VetDaw.* to developer@localhost;

drop table if exists Propietario,
					 Tipo,
                     Mascota,
                     Consulta;
                     
create table if not exists Propietario(
	dni VARCHAR(9) NOT NULL primary key,
	Nombre VARCHAR(45) NOT NULL,
	Apellido VARCHAR(45) NOT NULL,
	Telefono VARCHAR(12) NOT NULL,
	Direcion VARCHAR(100) NOT NULL,
	Email VARCHAR(45) NOT NULL
);


create table if not exists Tipo(
	idTipo int not null auto_increment primary key,
    tipo varchar(45) not null
);

CREATE TABLE IF NOT EXISTS Mascota (
  Pasaporte VARCHAR(9) NOT NULL primary key,
  Nombre VARCHAR(45) NOT NULL,
  Peso DOUBLE NOT NULL,
  FechaNacimiento DATE NOT NULL,
  Propietario_dni VARCHAR(10) NOT NULL,
  Tipo_idTipo INT NOT NULL,    
  FOREIGN KEY (Tipo_idTipo)  references Tipo(idTipo)
  );
  
CREATE TABLE IF NOT EXISTS Consulta(
  idConsulta INT NOT NULL AUTO_INCREMENT primary key,
  Fecha DATETIME NOT NULL,
  Duracion INT NOT NULL,
  Observaciones TEXT NULL,
  Mascota_Pasaporte VARCHAR(9) NOT NULL,
  Mascota_Propietario_dni VARCHAR(10) NOT NULL,
  foreign key (Mascota_Pasaporte) references Mascota(Pasaporte),
  foreign key (Mascota_Propietario_dni) references Propietario(dni)
);

select * from Mascota;

select * from Tipo;

select * from Propietario;

select * from Consulta;