drop table if exists line_item cascade;
drop table if exists `order` cascade;
drop table if exists product cascade;
drop table if exists category cascade;
drop table if exists shipping_details cascade;
drop table if exists `admin` cascade;

CREATE TABLE `admin` (
    id VARCHAR(36) PRIMARY KEY DEFAULT UUID(),
    user_id VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE category (
    id VARCHAR(36) PRIMARY KEY DEFAULT UUID(),
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE product (
    id VARCHAR(36) PRIMARY KEY DEFAULT UUID(),
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    category_id VARCHAR(36) NOT NULL,
    filter ENUM('NONE', 'LANDING', 'FEATURED') DEFAULT 'NONE',
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE shipping_details (
    id VARCHAR(36) PRIMARY KEY DEFAULT UUID(),
    user_id VARCHAR(36) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    postal_code VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE `order` (
    id VARCHAR(36) PRIMARY KEY DEFAULT UUID(),
    user_id VARCHAR(36) NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    payment_status ENUM('UNPAID', 'PAID') DEFAULT 'UNPAID',
    shipping_details_id VARCHAR(36) NOT NULL,
    shipping_status ENUM('UNSHIPPED', 'SHIPPED') DEFAULT 'UNSHIPPED',
    FOREIGN KEY (shipping_details_id) REFERENCES shipping_details(id)
);

CREATE TABLE line_item (
    id VARCHAR(36) PRIMARY KEY DEFAULT UUID(),
    order_id VARCHAR(36) NOT NULL,
    product_id VARCHAR(36) NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES `order` (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);