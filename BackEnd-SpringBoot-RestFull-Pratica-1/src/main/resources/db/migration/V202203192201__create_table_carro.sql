CREATE TABLE if NOT EXISTS t_carro (
	id int8 NOT NULL AUTO_INCREMENT,
	marca varchar(255) NOT NULL,
    modelo varchar(255) NOT NULL, 
    funcionando bool NULL,
	PRIMARY KEY (id)
)