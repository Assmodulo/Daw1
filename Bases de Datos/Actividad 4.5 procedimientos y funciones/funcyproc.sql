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

call suma_producto(3,5,@res_suma);

select @new.num2;
select @res_suma;