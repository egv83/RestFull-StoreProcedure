/*CREATE DATABASE pruebas;*/

USE pruebas;

DROP TABLE IF EXISTS persona;

CREATE table persona(
	id 			int not null,
	nombre		varchar(255),
	apellido	varchar(255),
	direccion	varchar(255),
	ciudad		varchar(255),
	PRIMARY KEY (id)
);