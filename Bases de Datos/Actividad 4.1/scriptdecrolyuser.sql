use DAW_PR4_1;
drop user if exists alumno;

select * from alumnos;

# Update sobre el atributo f_nacimiento
UPDATE alumnos SET f_nacimiento='1990-04-20' WHERE nombre='Jose';


UPDATE alumnos SET apellidos ='Alonso' WHERE nombre='Maria';

select * from alumnos;

INSERT INTO alumnos VALUES ('Fernando', 'Alonso', '1981-07-29');


select * from alumnos;
