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
CREATE TABLE alojamientos(
	cod_alojamientos CHAR(6) UNIQUE NOT NULL,
    nombre_alojamiento VARCHAR(20) NOT NULL,
    tipo VARCHAR (15) NOT NULL,
    direccion VARCHAR(25) NOT NULL,
    localidad VARCHAR(20) NOT NULL,
    telefono CHAR(9) NOT NULL,
    categoria VARCHAR(10),
    CONSTRAINT cod_alojamientos primary key (cod_alojamientos)
    );
    
    #Creo la tabla grupos
    CREATE TABLE grupos(
		id_grupo INT AUTO_INCREMENT UNIQUE NOT NULL,
        num_personas INT NOT NULL,
        CONSTRAINT id_grupo primary key(id_grupo)
    );
    
    #Empiezo a crear tablas dependientes, en este caso la tabla sedes
    CREATE TABLE sedes(
		cod_sede INT auto_increment NOT NULL UNIQUE,
        direccion VARCHAR(25) NOT NULL,
        telefono CHAR(9) NOT NULL,
        email VARCHAR(25) NOT NULL,
        cp_completo CHAR(5) NOT NULL,
        CONSTRAINT cod_sede primary key (cod_sede),
        CONSTRAINT cp_completo foreign key (cp_completo) references localidad (cp_completo)
    );
    
    #Seguimos con la tabla clientes
    CREATE TABLE clientes(
		dni CHAR(9) UNIQUE NOT NULL,
        nombre_completo VARCHAR(25) NOT NULL,
        telefono CHAR(9) NOT NULL,
        direccion VARCHAR(25) NOT NULL,
        emali VARCHAR(25) NOT NULL,
        cp_comp CHAR(5) NOT NULL,
        CONSTRAINT dni primary key (dni),
        CONSTRAINT cp_comp foreign key(cp_comp) references localidad(cp_completo)
    );
    
    #Creando la tabla empleados
    CREATE TABLE empleados(
		num_matricula INT AUTO_INCREMENT UNIQUE NOT NULL,
        dni CHAR (9) NOT NULL,
        num_ss CHAR(12) NOT NULL,
        nombre_completo VARCHAR(30) NOT NULL,
        fecha_nacim DATE NOT NULL,
        telefono CHAR(9) NOT NULL,
        email VARCHAR(25) NOT NULL,
        especialidad VARCHAR(15) NOT NULL,
        cod_departamento SMALLINT NOT NULL,
        sede INT NOT NULL,
        CONSTRAINT num_matricula primary key(num_matricula),
        CONSTRAINT cod_departamento foreign key(cod_departamento) references departamento(cod_dep),
        CONSTRAINT sede foreign key(sede) references sedes(cod_sede)
    );
    
    CREATE TABLE oferta(
		sede INT NOT NULL,
        paquete CHAR(4) NOT NULL,
        CONSTRAINT oferta_sedes primary key(sede, paquete),
        CONSTRAINT sedes foreign key(sede) references sedes(cod_sede),
        CONSTRAINT paquetes foreign key(paquete) references paquetes(cod_paquete)
    );
    
    #Genero la tabla componentes para resolver la relación entre clientes y grupos
    CREATE TABLE componentes(
		dni CHAR(9) NOT NULL,
        id_grupo INT NOT NULL,
        CONSTRAINT componentes primary key(dni, id_grupo),
        CONSTRAINT miembro foreign key(dni) references clientes(dni),
        CONSTRAINT grupo foreign key(id_grupo) references grupos(id_grupo)
    );
    
    #Después de crear la tabla componentes puedo crear la tabla reservas
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
    
    #Creo la tabla actividad para terminar de cerrar este bloque
    CREATE TABLE actividad(
		cod_reserva INT NOT NULL,
        cod_paquete CHAR(4) NOT NULL,
        CONSTRAINT actividades primary key(cod_reserva, cod_paquete),
        CONSTRAINT reserva foreign key (cod_reserva) references reservas(cod_reserva),
        CONSTRAINT paquete foreign key (cod_paquete) references paquetes(cod_paquete)
    );
    
    #Modifico la tabla reservas para añadir el cod_alojamiento
    ALTER TABLE reservas ADD cod_alojamiento CHAR(6) NOT NULL;
    ALTER TABLE reservas ADD CONSTRAINT alojamiento foreign key(cod_alojamiento) references alojamientos(cod_alojamientos);
    
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
    
    #Creo la tabla equipamientos
    CREATE TABLE equipamiento(
		cod_equipamiento CHAR(6) UNIQUE NOT NULL,
        tipo_equipamiento VARCHAR(15) NOT NULL,
        modelo VARCHAR(15) NOT NULL,
        plazas SMALLINT,
        cod_sede INT NOT NULL,
        CONSTRAINT cod_equipamiento primary key(cod_equipamiento),
        CONSTRAINT sede_propietaria foreign key(cod_sede) references sedes(cod_sede)
    );
    
    #Rematamos las relaciones que tengan que ver con la tabla equipamiento
    CREATE TABLE equipo(
		num_equipo SMALLINT UNIQUE NOT NULL,
        num_tecnicos SMALLINT NOT NULL default 1,
        CONSTRAINT num_equipo primary key(num_equipo)
    );
    
    CREATE TABLE miembros_equipo(
		num_equipo SMALLINT NOT NULL,
        num_matricula INT NOT NULL,
        CONSTRAINT equipo primary key(num_equipo, num_matricula),
        CONSTRAINT cod_equipo foreign key(num_equipo) references equipo(num_equipo),
        CONSTRAINT cod_empleado foreign key(num_matricula) references empleados(num_matricula)
    );
    
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
    
    #Como indico en el informe puedo realizar este cambio aquí para optimizar la BD y el diagrama
    ALTER TABLE reservas ADD num_poliza CHAR(6) NOT NULL;
    ALTER TABLE reservas ADD CONSTRAINT poliza_reserva foreign key(num_poliza) references seguros(num_poliza);
    
    #Vamos con la parte de vehículos
    CREATE TABLE vehiculos(
		matricula CHAR(7) UNIQUE NOT NULL,
        tipo VARCHAR(15) NOT NULL,
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
    
    #Última parte ya, creando la tabla agencias y las auxiliares o intermedias.
    CREATE TABLE agencias(
		cod_agencia CHAR(6) UNIQUE NOT NULL,
        nombre VARCHAR(15) NOT NULL,
        direccion VARCHAR(25) NOT NULL,
        telefono CHAR(9) NOT NULL,
        persona_contacto VARCHAR(25) NOT NULL,
        CONSTRAINT cod_agencia primary key(cod_agencia)
    );
  
	CREATE TABLE oferta_agencia(
		cod_paquete CHAR(4) NOT NULL,
        cod_agencia CHAR(6) NOT NULL,
        CONSTRAINT oferta_agencias primary key(cod_paquete, cod_agencia),
        CONSTRAINT agencia foreign key(cod_agencia) references agencias(cod_agencia),
        CONSTRAINT paquete_agencia foreign key(cod_paquete) references paquetes(cod_paquete)
    );
    
    CREATE TABLE reservas_agencias(
		cod_agencia CHAR(6) NOT NULL,
        cod_reserva INT NOT NULL,
        CONSTRAINT referencia_reservas primary key(cod_agencia, cod_reserva),
        CONSTRAINT agencia_reserva foreign key(cod_agencia) references agencias(cod_agencia),
        CONSTRAINT reserva_externa foreign key(cod_reserva) references reservas(cod_reserva)
    );