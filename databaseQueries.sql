CREATE TABLE category (
	id int,
	name VARCHAR(50),
	description VARCHAR(255),
	image_url VARCHAR(50),
	is_active BOOLEAN,
	CONSTRAINT pk_category_id PRIMARY KEY (id) 

);

CREATE TABLE user_detail (
	id int,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enabled BOOLEAN,
	password VARCHAR(60),
	email VARCHAR(100),
	contact_number VARCHAR(15),	
	CONSTRAINT pk_user_id PRIMARY KEY(id)
);


CREATE TABLE product (
	id int,
	code VARCHAR(20),
	name VARCHAR(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY (id),
 	CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail(id)	
);	

-- the address table to store the user billing and shipping addresses
CREATE TABLE address (
	id int,
	user_id int,
	address VARCHAR(100),
	city VARCHAR(20),
	country VARCHAR(20),
	postal_code VARCHAR(10),
	is_billing BOOLEAN,
	is_shipping BOOLEAN,
	CONSTRAINT fk_address_user_id FOREIGN KEY (user_id ) REFERENCES user_detail (id),
	CONSTRAINT pk_address_id PRIMARY KEY (id)
);

-- the cart table to store the user cart top-level details
CREATE TABLE cart (
	id int,
	user_id int,
	grand_total DECIMAL(10,2),
	cart_lines int,
	CONSTRAINT fk_cart_user_id FOREIGN KEY (user_id ) REFERENCES user_detail (id),
	CONSTRAINT pk_cart_id PRIMARY KEY (id)
);
-- the cart line table to store the cart details

CREATE TABLE cart_line (
	id int,
	cart_id int,
	total DECIMAL(10,2),
	product_id int,
	product_count int,
	buying_price DECIMAL(10,2),
	is_available boolean,
	CONSTRAINT fk_cartline_product_id FOREIGN KEY (product_id ) REFERENCES product (id),
	CONSTRAINT pk_cartline_id PRIMARY KEY (id)
);


-- the order detail table to store the order

CREATE TABLE order_detail (
	id int,
	user_id int,
	order_total DECIMAL(10,2),
	order_count int,
	shipping_id int,
	billing_id int,
	order_date date,
	CONSTRAINT fk_order_detail_user_id FOREIGN KEY (user_id) REFERENCES user_detail (id),
	CONSTRAINT fk_order_detail_shipping_id FOREIGN KEY (shipping_id) REFERENCES address (id),
	CONSTRAINT fk_order_detail_billing_id FOREIGN KEY (billing_id) REFERENCES address (id),
	CONSTRAINT pk_order_detail_id PRIMARY KEY (id)
);

-- the order item table to store order items

CREATE TABLE order_item (
	id int,
	order_id int,
	total DECIMAL(10,2),
	product_id int,
	product_count int,
	buying_price DECIMAL(10,2),
	CONSTRAINT fk_order_item_product_id FOREIGN KEY (product_id) REFERENCES product (id),
	CONSTRAINT fk_order_item_order_id FOREIGN KEY (order_id) REFERENCES order_detail (id),
	CONSTRAINT pk_order_item_id PRIMARY KEY (id)
);


-- adding three categories
INSERT INTO category (id, name, description,image_url,is_active) VALUES (1,'Parfum', 'This is description for Laptop category!', 'CAT_1.png', true);
INSERT INTO category (id, name, description,image_url,is_active) VALUES (2,'Accessoires', 'This is description for Television category!', 'CAT_2.png', true);
INSERT INTO category (id, name, description,image_url,is_active) VALUES (3,'Maquillage', 'This is description for Mobile category!', 'CAT_3.png', true);
INSERT INTO category (id, name, description,image_url,is_active) VALUES (4,'Chaussures', 'This is description for Mobile category!', 'CAT_3.png', true);
-- adding three users 
INSERT INTO user_detail 
(id, first_name, last_name, role, enabled, password, email, contact_number) 
VALUES (1,'issam', 'ben ahmed', 'ADMIN', true, '$2a$10$xv5/L4nuYdZvtV7VvvVdQOIts30WCUnw2eE9CvIxvIN6F6mXuV0qy', 'ba@gmail.com', '8888888888');
INSERT INTO user_detail 
(id, first_name, last_name, role, enabled, password, email, contact_number) 
VALUES (2,'Fournisseur', 'Frs', 'SUPPLIER', true, '$2a$10$vmiNDRhSg4CpL/xUxK89vOWqt8w.hC6HSPK2ZrqrUpVHw85COjBbq', 'frs@gmail.com', '9999999999');
INSERT INTO user_detail 
(id, first_name, last_name, role, enabled, password, email, contact_number) 
VALUES (3,'tijani', 'chleka', 'USER', true, '$2a$10$xv5/L4nuYdZvtV7VvvVdQOIts30WCUnw2eE9CvIxvIN6F6mXuV0qy', 'tij@gmail.com', '7777777777');

-- adding five products
INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (1,'PRDABC123DEFX', 'Parfum 1', 'Cristian Lay', 'La description de parfum 1!', 18.00, 0, true, 2, 1, 0, 0 );
INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (2,'PRDDEF123DEFX', 'Parfum 2', 'Cristian Lay', 'La description de parfum 2!', 32.00, 2, true, 2, 1, 0, 0 );
INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (3,'PRDPQR123WGTX', 'Bijoux 2', 'Cristian Lay', 'Les meilleurs bijoux du monde', 57.00, 5, true, 3, 1, 0, 0 );
INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (4,'PRDMNO123PQRX', 'Bijoux 1', 'Cristian Lay', 'Les meilleurs bijoux du monde', 54.00, 3, true, 3, 1, 0, 0 );
INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (5,'PRDABCXYZDEFX', 'Maquillage 1', 'Cristian Lay', 'Le meilleur maquillage pour les femmes', 48.00, 5, true, 3, 1, 0, 0 );
INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (6,'PRD27167D654F', 'Maquillage 2', 'Cristian Lay', 'Le meilleur maquillage pour les femmes', 25.00, 5, true, 3, 1, 0, 0 );
INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (7,'PRD247E9AD46D', 'Parfum 3', 'Cristian Lay', 'La description de parfum 3!', 15.00, 5, true, 2, 1, 0, 0 );
INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (8,'PRDAEFB3E2188', 'Chaussures 1', 'Cristian Lay', 'Chaussures CL', 100.00, 5, true, 4, 1, 0, 0 );
-- adding a supplier correspondece address
INSERT INTO address( user_id, address, city, country, postal_code, is_billing, is_shipping) 
VALUES (1, 'CHATT MERIEM', 'Sousse', 'Tunisie', '4042', true, false );
INSERT INTO address( user_id, address, city, country, postal_code, is_billing, is_shipping) 
VALUES (2, 'CHATT MERIEM', 'Sousse', 'Tunisie', '4042', true, false );
INSERT INTO address( user_id, address, city, country, postal_code, is_billing, is_shipping) 
VALUES (3, 'CHATT MERIEM', 'Sousse', 'Tunisie', '4042', true, false );
-- adding a cart for testing 
INSERT INTO cart (user_id, grand_total, cart_lines) VALUES (3, 32, 1);

