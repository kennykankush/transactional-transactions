CREATE DATABASE IF NOT EXISTS ecom;

USE ecom;

CREATE TABLE IF NOT EXISTS orders(
    order_id INT NOT NULL AUTO_INCREMENT,
    order_date DATE,
    customer_name VARCHAR(50),
    ship_address VARCHAR(50),
    notes text,
    tax DECIMAL(10,2),
    CONSTRAINT pk_order_id PRIMARY KEY (order_id)
);

CREATE TABLE IF NOT EXISTS order_details(
    id INT NOT NULL AUTO_INCREMENT,
    order_id INT NOT NULL,
    product VARCHAR(64),
    unit_price DECIMAL(10,2),
    discount DECIMAL(4,2),
    quantity INT,
    CONSTRAINT pk_order_detail_id PRIMARY KEY (id),
    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
)