DROP DATABASE IF EXISTS empleados;
CREATE DATABASE IF NOT EXISTS empleados;
USE empleados;

DROP TABLE IF EXISTS dept_emp,
                     empleado, 
                     departamento;

CREATE TABLE empleado (
    cod_empleado      INT             NOT NULL,
    fecha_nacimiento  DATE            NOT NULL,
    nombre  VARCHAR(14)     NOT NULL,
    apellido   VARCHAR(16)     NOT NULL,
    sexo      ENUM ('M','F')  NOT NULL,    
    fecha_contratacion  DATE            NOT NULL,
    PRIMARY KEY (cod_empleado)
);

CREATE TABLE departamento (
    cod_departamento     CHAR(4)         NOT NULL,
    nombre_departamento   VARCHAR(40)     NOT NULL,
    PRIMARY KEY (cod_departamento),
    UNIQUE  KEY (nombre_departamento)
);

CREATE TABLE dept_emp (
    cod_empleado      INT             NOT NULL,
    cod_departamento     CHAR(4)         NOT NULL,
    fecha_desde   DATE            NOT NULL,
    fecha_hasta     DATE            NOT NULL,
    FOREIGN KEY (cod_empleado)  REFERENCES empleado   (cod_empleado)  ON DELETE CASCADE,
    FOREIGN KEY (cod_departamento) REFERENCES departamento (cod_departamento) ON DELETE CASCADE,
    PRIMARY KEY (cod_empleado,cod_departamento)
);

INSERT INTO `empleado` VALUES (10001,'1953-09-02','Georgi','Facello','M','2021-06-26'),
(10002,'1964-06-02','Bezalel','Simmel','F','2018-11-21'),
(10003,'1979-12-03','Parto','Bamford','M','2019-08-28'),
(10004,'1982-05-01','Chirstian','Koblick','M','2022-12-01'),
(10005,'1983-01-21','Kyoichi','Maliniak','M','2019-09-12'),
(10006,'1990-04-20','Anneke','Preusig','F','2018-06-02'),
(10007,'1965-05-23','Tzvetan','Zielinski','F','2018-02-10'),
(10008,'1990-02-19','Saniya','Kalloufi','M','2021-09-15'),
(10009,'1991-04-19','Sumant','Peac','F','2019-02-18'),
(10010,'1978-06-01','Duangkaew','Piveteau','F','2018-08-24'),
(10011,'1972-11-07','Mary','Sluis','F','2018-01-22'),
(10012,'1988-09-02','Lucas','Perez','M','2019-06-26');

INSERT INTO `departamento` VALUES 
('d001','Marketing'),
('d002','Finance'),
('d003','Human Resources'),
('d004','Production'),
('d005','Development'),
('d006','Quality Management'),
('d007','Sales'),
('d008','Research'),
('d009','Customer Service'),
('d010','IT');

INSERT INTO `dept_emp` VALUES (10001,'d006','2021-06-26','9999-01-01'),
(10002,'d006','2018-11-21','9999-01-01'),
(10003,'d004','2019-08-28','9999-01-01'),
(10004,'d004','2022-12-01','9999-01-01'),
(10005,'d004','2019-09-12','9999-01-01'),
(10006,'d005','2018-06-02','9999-01-01'),
(10007,'d008','2018-02-10','9999-01-01'),
(10008,'d006','2021-09-15','2022-07-31'),
(10009,'d006','2019-02-18','9999-01-01'),
(10010,'d004','2018-08-24','2020-10-11'),
(10010,'d006','2020-10-12','9999-01-01'),
(10011,'d005','2018-01-22','2019-09-10'),
(10012,'d005','2019-06-26','9999-01-01');

#Comenzamos con los ejercicios y modificaciones
update empleado set nombre = 'Helen' where nombre = 'Mary';

#Cambiar la fecha en todas la filas de la tabla
update dept_emp set fecha_hasta = '9999-02-02';
# update dept_emp set fecha_hasta = '9999-02-02' where fecha_hasta = '9999-01-01';

#Para el tercer ejercicio voy a tener que crear 2 empleados y luego insertar datos en la tabla dept_emp
insert into empleado values (10013,'1979-08-16','Alexei','Borisnikov','M','2020-11-25'), 
(10014,'1989-06-28','Pepe','Perez','M','2018-06-26'); 

insert into dept_emp values (10013,'d010','2020-11-25','9999-02-02'),(10014,'d010','2018-06-26','9999-02-02');

#Cambiar el nombre del departamento TI por Equipo de Titanes Digitales
update departamento set nombre_departamento = 'Equipo de Titanes Digitales' where nombre_departamento = 'IT';

#Mostrar todos los datos de la tabla departamento
select * from departamento;

#Mostrar solo los nombres de los departamentos
select nombre_departamento from departamento;

#Mostrar datos de empleados nacidos a partir de 1990-04-20
select nombre, apellido, fecha_nacimiento from empleado where fecha_nacimiento >= '1990-04-20';

#Mostrar datos de empleados nacidos en la decada de los 80
#select nombre, apellido, fecha_nacimiento from empleado where fecha_nacimiento like '%198%';
select nombre, apellido, fecha_nacimiento from empleado where fecha_nacimiento between '1980-01-01' and '1989-12-31';

insert into empleado values (10015,'1984-08-16','Mujer','DePrueba','F','2020-11-25');
#Mostrar datos de empleadas nacidas en las decadas 80 y 90
select nombre, apellido, fecha_nacimiento from empleado where sexo = 'F' and fecha_nacimiento between '1980-01-01' and '1999-12-31';

#Mostrar datos de empleados, apellidos y nombre en ese orden, cuyo apellido empiece con P
select apellido, nombre from empleado where apellido like 'P%';