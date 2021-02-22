DROP SCHEMA IF EXISTS c_o_a_database_test;
CREATE SCHEMA c_o_a_database_test;
USE c_o_a_database_test;

CREATE TABLE `account`(
id INT AUTO_INCREMENT NOT NULL,
industry VARCHAR(255),
employee_count INT,
city VARCHAR(255),
country VARCHAR(255),
PRIMARY KEY(id)
);

CREATE TABLE contact(
id INT AUTO_INCREMENT NOT NULL,
`name` VARCHAR(255),
email VARCHAR(255),
company_name VARCHAR(255),
phone_number VARCHAR(255),
account_id INT,
PRIMARY KEY(id),
FOREIGN KEY(account_id) REFERENCES `account`(id)
);

CREATE TABLE opportunity(
id INT AUTO_INCREMENT NOT NULL,
product VARCHAR(255),
quantity INT,
contact_id INT,
`status` VARCHAR(255),
sales_rep_id INT,
account_id INT,
PRIMARY KEY(id),
FOREIGN KEY(contact_id) REFERENCES contact(id),
FOREIGN KEY(account_id) REFERENCES `account`(id)
);
