drop database if exists dawdata;
create database dawdata;

use dawdata;

drop user if exists developer@localhost;
create user developer@localhost identified by '1234';




create table tipos(
	id smallint auto_increment not null,
    tipo enum ('EN_PREPARACION', 'PREPARADO', 'ENVIADO', 'EN_REPARTO', 'CANCELADO', 'DESCATALOGADO'),
    constraint id primary key(id)
);

create table productos(
	id smallint auto_increment not null,
    referencia char(9) not null,
    nombre varchar(50) not null,
    descriptcion varchar(100) not null,
    id_tipo smallint not null,
    cantidad int not null,
    precio decimal(6,2) not null,
    descuento int not null,
    iva int not null,
    aplicar_iva boolean not null,
    constraint id primary key (id)
);

insert into tipos values
(1,'EN_PREPARACION'),
(2,'PREPARADO'),
(3,'ENVIADO'),
(4,'EN_REPARTO'),
(5,'CANCELADO'),
(6,'DESCATALOGADO');

insert into productos values
(1,'EQG-43584','WallArt','Abstract canvas print to enhance home decor.',4,24,45,19,11,false),
(2,'EOL-89294','Adjustable Garden Rake','Heavy-duty rake with adjustable width for different gardening needs',5,15,22.99,24,7,true),
(3,'PNP-05579','Chocolate Covered Pretzels','Crunchy pretzels coated in rich chocolate, a sweet treat.',6,3,3.29,16,7,false),
(4,'LRG-93594','Water-Resistant Bluetooth Speaker','Durable speaker designed for outdoor use with water resistance.',2,23,59.99,20,11,false),
(5,'IIE-86036','Insulated Coffee Mug','Double-walled mug to keep your drink hot for longer.',3,17,24.99,21,7,false),
(6,'QGM-72629','Fresh Strawberries','Sweet and juicy strawberries, perfect for desserts',2,9,3.99,6,11,true),
(7,'KAK-95107','Spicy Roasted Nuts','"A mix of nuts roasted with spicy seasonings, perfect for snacking."',2,24,4.99,15,11,true),
(8,'VEZ-67300','Dried Cranberries','"Sweet and tart dried cranberries, great for salads and baking."',6,9,4.29,14,7,true),
(9,'YYK-51417','Deep Tissue Massage Gun','Rechargeable massage gun for relieving muscle soreness.',5,16,89.99,21,21,false),
(10,'PAF-70537','Smart LED Desk Lamp','Adjustable lamp with multiple brightness levels and colors.',4,13,39.99,9,11,false),
(11,'NDR-83704','Stainless Steel Travel Mug','Insulated mug to keep drinks hot on the go.',4,12,19.99,15,21,true),
(12,'BCN-64628','Stainless Steel Straws','Set of reusable stainless steel straws for drinks.',5,24,12.99,10,21,false),
(13,'GZO-46996','Workstation Laptop Stand','Adjustable stand to improve ergonomics while working on a laptop.',5,13,49.99,18,7,true),
(14,'YPA-04494','Homemade Salsa','"Fresh salsa made with tomatoes, onions, and cilantro."',3,24,3.79,16,11,true),
(15,'BIV-79229','Crew Neck Sweater','"A comfortable crew neck sweater, great for all seasons."',1,12,39.99,9,7,false),
(16,'QSP-65163','Fall-Themed Table Runner','Decorative table runner perfect for autumn gatherings.',5,23,19.99,14,21,true),
(17,'YWE-92143','Fitness Smartwatch','Multifunctional smartwatch for fitness tracking.',4,18,99.99,22,7,true),
(18,'UPZ-22824','Ribbed Knit Dress','A fitted ribbed knit dress that hugs your curves perfectly.',3,12,59.99,12,7,true),
(19,'FIG-61158','Lemon Dill Salmon','"Salmon fillets seasoned with lemon and dill, perfect for grilling."',6,18,9.99,15,21,true);


grant all privileges on dawdata.tipos to developer@localhost;
grant all privileges on dawdata.productos to developer@localhost;

grant select on tipos to developer@localhost;
grant select on productos to developer@localhost;

show grants for developer@localhost;
