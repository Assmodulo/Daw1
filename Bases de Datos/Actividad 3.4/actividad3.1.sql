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

#AÑADIMOS EL CAMPO SALARIO EN LA TABLA DEPT_EMP
alter table dept_emp add salario decimal(8,2) not null;

#He buscado si existe una forma de hacer multiples updates en una tabla, para actualizar los salarios, y creo que iría asi, voy a prober

#Como no he encontrado una forma que conozca correctamente lo voy a tener que hacer uno por uno

update dept_emp set salario = 34000.00 where cod_empleado = '10001';
update dept_emp set salario = 20000.00 where cod_empleado = '10002';
update dept_emp set salario = 22000.00 where cod_empleado = '10003';
update dept_emp set salario = 29500.00 where cod_empleado = '10004';
update dept_emp set salario = 32000.00 where cod_empleado = '10005';
update dept_emp set salario = 19500.00 where cod_empleado = '10006';
update dept_emp set salario = 30000.00 where cod_empleado = '10007';
update dept_emp set salario = 29500.00 where cod_empleado = '10008';
update dept_emp set salario = 31250.00 where cod_empleado = '10009';
update dept_emp set salario = 36000.00 where cod_empleado = '10010';
update dept_emp set salario = 26000.00 where cod_empleado = '10011';
update dept_emp set salario = 27000.00 where cod_empleado = '10012';

#Cuantos empleados trabajan en el departamento de calidad
select nombre_departamento, count(dep.cod_empleado) from departamento d join 
dept_emp dep on d.cod_departamento = dep.cod_departamento where d.nombre_departamento = 'Quality Management';

#Quien es el empleado mejor pagado del departamento de desarrollo
select e.nombre, e.apellido, d.salario from empleado e join dept_emp d on e.cod_empleado = d.cod_empleado 
join departamento de on d.cod_departamento = de.cod_departamento 
where de.nombre_departamento = 'Development' and d.salario = 
(select max(d.salario) from dept_emp d join departamento de on d.cod_departamento = de.cod_departamento 
where de.nombre_departamento = 'Development');  

#nombre, apellido, y fecha de contratación del ultimo empleado contratado en producción
select e.nombre, e.apellido, de.fecha_desde from empleado e join dept_emp de on
e.cod_empleado = de.cod_empleado join departamento d on de.cod_departamento = d.cod_departamento
where d.nombre_departamento ='Production' and de.fecha_desde = (
select max(de.fecha_desde) from dept_emp de join departamento d on de.cod_departamento = d.cod_departamento
where d.nombre_departamento ='Production');

#Salario medio de los trabajadores del departamento de produccion
select d.nombre_departamento, avg(de.salario) from departamento d join dept_emp de
on d.cod_departamento = de.cod_departamento where d.nombre_departamento = 'Production';