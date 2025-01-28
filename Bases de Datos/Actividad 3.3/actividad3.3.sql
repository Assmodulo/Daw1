drop database if exists compania_taxis;
create database if not exists compania_taxis;
use compania_taxis;

create table taxis(
	matricula char(8) not null unique,
    marca varchar(15) not null,
    modelo varchar(15) not null,
    num_pasajeros char(1) not null,
    adaptado enum('S','N') not null,
    primary key (matricula)
);

create table choferes(
	dni char(9) unique not null,
    nombre varchar(15) not null,
    apellidos varchar (25) not null,
    direccion varchar (30) not null,
    matricula char(8),
    primary key (dni),
    foreign key (matricula) references taxis (matricula)
);

create table carreras(
	cod_carrera char(6) unique not null,
    origen varchar(15) not null,
    destino varchar(15) not null,
    precio decimal(6,2) not null,
    nocturna enum('S','N') not null default 'N',
    conductor char(9) not null,
    taxi char(8) not null,
    primary key (cod_carrera),
    foreign key (conductor) references choferes(dni),
    foreign key (taxi) references taxis(matricula)
);

insert into taxis values ('1234 CHB','RENAULT','LAGUNA','4','N'),
('3243 HGF','TOYOTA','PRIUS','5','N'),
('2345 CKD','MERCEDES','JLK','4','S'),
('2378 FHG','MERCEDES','VITO','8','S'),
('7069 DLV','DACIA','SANDERO','4','N'),
('0101 ABD','DACIA','SANDERO','3','N'),
('1424 XYZ','KIA','SPORTAGE','5','S'),
('6666 FJK','PEUGEOT','806','5','S');

insert into choferes values ('11111111H','PEPE','PEREZ','CALLE FALSA 123','3243 HGF'),
('22222222J','MATIAS','MARTIN','AVENIDA LOS PINOS 1','2345 CKD'),
('33333333P','EUGENIO','FERNANDEZ','PLAZA CENTRAL 2','2378 FHG'),
('44444444A','LUIS','GONZALEZ','PASEO SIN NOMBRE 3','7069 DLV'),
('55555555B','ROBERTO','RODRIGUEZ','CALLE FALSA 456','1234 CHB'),
('66666666C','ALBERTO','SANCHEZ','AVENIDA LOS PINOS 2','7069 DLV'),
('77777777D','RUBEN','RECIO','PLAZA CENTRAL 3','1234 CHB'),
('88888888E','AMADOR','DIEZ','PASEO SIN NOMBRE 4','3243 HGF'),
('99999999X','TEOFILO','SAN MARTIN','CALLE PRINCIPAL S.N','2345 CKD');

insert into carreras values('1','SANTANDER','PEÑACASTILLO',6.25,'N','11111111H','3243 HGF'),
('2','SANTANDER','MALIAÑO',15.20 ,'S','22222222J','2345 CKD'),
('3','PASEO PEREDA','GENERAL DAVILA',4.52 ,'N','22222222J','2345 CKD'),
('4','PUERTO CHICO','VALDECILLA',5.75 ,'N','33333333P','2378 FHG'),
('5','CAZOÑA','AYUNTAMIENTO',6.12 ,'S','33333333P','2378 FHG'),
('6','LA ALBERICIA','PRONILLO',8.40 ,'S','44444444A','7069 DLV'),
('7','SARDINERO','PEÑACASTILLO', 10.34,'N','44444444A','7069 DLV'),
('8','VALDENOJA','MONTE',6.49 ,'N','44444444A','7069 DLV');

#Mostrar los coches con choferes asignados
select taxis.matricula, marca, modelo, dni, nombre, apellidos from taxis inner join choferes where taxis.matricula = choferes.matricula;

#Mostrar origen/destino/matricula/adaptado
select origen, destino, matricula, adaptado from carreras inner join taxis where taxi = matricula;

#Conductores y coches respectivos
select  nombre, apellidos,dni, marca, modelo, taxis.matricula, num_pasajeros from choferes inner join taxis where choferes.matricula = taxis.matricula order by nombre;

#Listado completo
select marca, modelo, num_pasajeros, adaptado from taxis left join choferes on taxis.matricula = choferes.matricula UNION select dni, nombre, apellidos, direccion from taxis right join choferes on choferes.matricula = taxis.matricula;

#Listado taxis y carreras
select matricula, marca, modelo, num_pasajeros, adaptado from taxis left join carreras on matricula = taxi union select cod_carrera, origen, destino, precio, nocturna from taxis right join carreras on matricula = taxi;