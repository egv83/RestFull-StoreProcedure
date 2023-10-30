DROP PROCEDURE IF EXISTS spPersona;

DELIMITER $$
CREATE PROCEDURE spPersona(IN operacion VARCHAR(2), IN paramData JSON)
BEGIN
	
	DECLARE id int;
    DECLARE nombre VARCHAR(255);
    DECLARE apellido VARCHAR(255);
   	DECLARE direccion VARCHAR(255);
  	DECLARE ciudad VARCHAR(255);

  /******************************/
  /*	CREACION DE PERSONA		*/
  /******************************/
	IF operacion = 'C' THEN

		/*SELECT paramData;*/


		/* OPCION 1 */
		/*SET @id = JSON_EXTRACT(paramData,'$.id');
		SET @nombre = JSON_EXTRACT(paramData,'$.nombre');
		SET @apellido = JSON_EXTRACT(paramData,'$.apellido');
		SET @direccion = JSON_EXTRACT(paramData,'$.direccion');
		SET @ciudad = JSON_EXTRACT(paramData,'$.ciudad');*/

		/*INSERT INTO pruebas.persona
		(id, nombre, apellido, direccion, ciudad)
		VALUES(JSON_UNQUOTE(@id), JSON_UNQUOTE(@nombre), JSON_UNQUOTE(@apellido)
		, JSON_UNQUOTE(@direccion), JSON_UNQUOTE(@ciudad));*/

		/* OPCION2 */
		SET id = JSON_EXTRACT(paramData,'$.id');
		SET nombre = JSON_UNQUOTE(JSON_EXTRACT(paramData,'$.nombre'));
		SET apellido = JSON_UNQUOTE(JSON_EXTRACT(paramData,'$.apellido'));
		SET direccion = JSON_UNQUOTE(JSON_EXTRACT(paramData,'$.direccion'));
		SET ciudad = JSON_UNQUOTE(JSON_EXTRACT(paramData,'$.ciudad'));

		INSERT INTO pruebas.persona (id, nombre, apellido, direccion, ciudad)
		VALUES(id, nombre, apellido, direccion, ciudad);

		SELECT * FROM pruebas.persona WHERE persona.id = id;

	END IF;

	/********************************/
	/*	BUSCAR TODAS LAS PERSONAS	*/
	/********************************/
	IF operacion = 'R' THEN
		SELECT * FROM pruebas.persona;
	END IF;


END$$
DELIMITER

/*CALL spPersona("I",'{ "key": "value" }')*/
