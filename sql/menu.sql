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