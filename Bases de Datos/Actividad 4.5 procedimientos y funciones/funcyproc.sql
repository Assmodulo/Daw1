drop database if exists procyfunc;

create database procyfunc;

use procyfunc;

drop procedure if exists suma_numeros;

delimiter //
create procedure suma_numeros(in num1 int, in num2 int, out resultado int)
begin
	set resultado = num1 + num2;
end//

call suma_numeros(3,5,@resultado);
select @resultado;

drop procedure if exists suma_producto;

delimiter //
create procedure suma_producto(in num1 int, inout num2 int, out res_suma int)
begin
		set res_suma = num1 + num2;
        set num2 = num1 * num2;
end//

set @num2 = 5;

call suma_producto(3, @num2, @res_suma);
select @num2,@res_suma;

drop function if exists recuperacion;

delimiter //
create function recuperacion(numero1 int, numero2 int)
returns int
deterministic
begin
	return numero1 + numero2;
end //

select recuperacion(5,4);

drop procedure if exists iva;

delimiter //
create procedure iva(in precio decimal(6,2),out precio_sin_iva decimal(6,2))
begin
	set precio_sin_iva = (precio/1.21);
end //

call iva (1000.00,@precio_sin_iva);
select @precio_sin_iva;

drop function if exists dia_semana;

delimiter //
create function dia_semana(numero int)
returns enum('Lunes','Martes','Miercoles','Jueves','Viernes','Sabado','Domingo')
deterministic
begin
	return case
		when numero = 1 then 'Lunes'
        when numero = 2 then 'Martes'
        when numero = 3 then 'Miercoles'
        when numero = 4 then 'Jueves'
        when numero = 5 then 'Viernes'
        when numero = 6 then 'Sabado'
        when numero = 7 then 'Domingo'
	end;
end //

select dia_semana(3);

drop function if exists calculadora;

delimiter //
create function calculadora(numero1 decimal(5,2), numero2 decimal(5,2), operacion int)
returns decimal(10,2)
deterministic
begin
	return case
		when operacion = 1 then numero1 + numero2
        when operacion = 2 then numero1 - numero2
        when operacion = 3 then numero1 * numero2
        when operacion = 4 then numero1/numero2
	end;
end //

select calculadora(100.99,100.99,1);
select calculadora(100.99,100.99,2);
select calculadora(100.99,100.99,3);
select calculadora(100.99,100.99,4);

drop procedure if exists factorial;

delimiter //
create procedure factorial(in n int, out valorFactorial int)
begin
	declare nInicial int;
    set nInicial = 2;
    set valorFactorial = 1;
    
    bucle : loop
		set valorFactorial=valorFactorial * nInicial;
        set nInicial = nInicial + 1;
        if nInicial > n then
			leave bucle;
		end if;
	end loop bucle;
end //
        
call factorial(5,@valorFactorial);
select @valorFactorial;

drop table if exists resultado;
drop temporary table if exists resultado2;

create table resultado(
	id int auto_increment not null primary key,
    resultado int not null
);

create temporary table resultado2(
	id int auto_increment not null primary key,
    resultado int not null
);

drop procedure if exists impares;

delimiter //
create procedure impares()
begin
	declare contador int;
    set contador = 0;
    
    bucle_impares : loop
		set contador = contador + 1;
		if mod(contador,2)<>0 then
			insert into resultado (resultado) values (contador);
            insert into resultado2 (resultado) values (contador);
		end if;
    
		if contador > 50 then
			leave bucle_impares;
		end if;
    end loop bucle_impares;
end //

call impares();
select * from resultado;