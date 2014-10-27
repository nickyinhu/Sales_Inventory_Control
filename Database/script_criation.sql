
CREATE TABLE client (
	id SERIAL NOT NULL,
	name VARCHAR(50),
	ssn VARCHAR(17),
	telephone VARCHAR(16) ,
	address VARCHAR(200),
	date_register DATE ,
	PRIMARY KEY (id) 
  )
;  
CREATE TABLE shopping (
	id SERIAL NOT NULL ,
	client_id INT,
	value FLOAT NOT NULL,
	date_shopping DATE NOT NULL,
  	PRIMARY KEY (id),
  	CONSTRAINT shopping_client
    FOREIGN KEY (client_id)
    REFERENCES client(id)	
)
;
CREATE TABLE product (
	id SERIAL NOT NULL ,
	name VARCHAR(50) NOT NULL,
	price_cost FLOAT NOT NULL,
	price_sale FLOAT NOT NULL,
	description VARCHAR(255),
  PRIMARY KEY (id) 
)
;
CREATE TABLE stock (
	id SERIAL NOT NULL,
	product_id INT NOT NULL,
	quantity INT,
	date_register DATE,
  PRIMARY KEY (id) ,
  CONSTRAINT stock_product
    FOREIGN KEY (product_id)
    REFERENCES product(id)	
) 
;
CREATE TABLE report (
	id SERIAL NOT NULL,
	date_report DATE,
	venda FLOAT,
	lucro FLOAT,
  PRIMARY KEY (id) 
)
;
