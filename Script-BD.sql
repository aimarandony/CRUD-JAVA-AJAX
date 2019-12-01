CREATE DATABASE BDCrudJavaAjax;
USE BDCrudJavaAjax;

CREATE TABLE PRODUCTOS(
	id_prod int primary key auto_increment,
    nom_prod varchar(50) not null,
    precio_prod decimal(10,2) not null,
    estado_prod tinyint(1) default true
);
