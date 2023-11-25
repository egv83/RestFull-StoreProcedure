/*USE pruebas;*/

DROP TABLE IF EXISTS menu;

CREATE table menu(
	id 			INT not null, /*AUTO_INCREMENT,*/
	menu_id		INT, 
	nombre		VARCHAR(255),
	url			TEXT,
	orden		INT,
	activo		TINYINT(1),
	PRIMARY KEY (id),
	FOREIGN KEY (menu_id) REFERENCES menu (id)
);

/* CONSULTAS Y PAGINACIÓN */
/*SELECT DISTINCT @w_num_reg = COUNT(*) -> obtener total de registros
select @o_tot_consulta = isnull(@w_num_reg, 0) -> cero si no hay registros
/*en i_filas se pasa ek numero de paginas*/
/*en i_sec se pasa hasta que pagina se va*/

@i_filas=20
select @w_offset = (@i_sec - 1) * @i_filas -> calcular paginas

SE HACE CONSULTA SQL PARA PAGINAR RESULTADOS
SELECT * FROM tabla
WHERE campos a validar condiciones
ORDER BY campo id o etc
OFFSET     @w_offset  ROWS
FETCH NEXT @i_filas   ROWS ONLY



SELECT * FROM menu
SELECT COUNT(*) FROM menu

SELECT (3-1)*20

SELECT * FROM menu m
/*WHERE campos a validar condiciones*/
ORDER BY m.id
OFFSET  @w_offset  ROWS
FETCH NEXT @i_filas   ROWS ONLY

SELECT * FROM menu m 
LIMIT 2,3

SELECT * FROM menu m
WHERE ISNULL(m.menu_id)

SELECT * FROM menu m
WHERE m.menu_id IN (SELECT m2.id FROM menu m2)
AND m.menu_id = 6

SELECT * FROM menu m
WHERE m.menu_id = 6*/