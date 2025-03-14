use DAW_PR4_1;

select * from alumnos;

# Update sobre el atributo f_nacimiento
UPDATE alumno SET f_nacimiento='1990-04-20' WHERE nombre='Jose';


UPDATE alumno SET apellido='Alonso' WHERE nombre='Maria';


INSERT INTO alumno VALUES ('Fernando', 'Alonso', '1981-07-29');