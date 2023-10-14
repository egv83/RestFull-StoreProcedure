DELIMITER $$
CREATE PROCEDURE spPersona(IN operacion VARCHAR(2))
BEGIN
	
	IF operacion = 'I' THEN
		SELECT * FROM persona;
	END IF;
	
END$$
DELIMITER