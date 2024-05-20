-- liquibase formatted sql

-- changeset anna:1
CREATE TABLE IF NOT EXISTS department (
                                          id SERIAL PRIMARY KEY,
                                          name_department VARCHAR(255) NOT NULL,
                                          address VARCHAR(255) NOT NULL
);
-- changeset anna:2
CREATE TABLE IF NOT EXISTS employee (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        surname VARCHAR(255) NOT NULL,
                                        family_name VARCHAR(255) NOT NULL,
                                        phone VARCHAR(20),
                                        birthday DATE NOT NULL,
                                        department_id INTEGER,
                                        FOREIGN KEY (department_id) REFERENCES department(id)
);
-- changeset anna:3
INSERT INTO department (name_department, address) VALUES ('HR', '123 Main St');
INSERT INTO department (name_department, address) VALUES ('IT', '456 Elm St');
INSERT INTO department (name_department, address) VALUES ('Sales', '789 Oak St');
-- changeset anna:4
INSERT INTO employee (name, surname, family_name, phone, birthday, department_id) VALUES ('John', 'Doe', 'Smith', '1234567890', '1980-01-01', 1);
INSERT INTO employee (name, surname, family_name, phone, birthday, department_id) VALUES ('Jane', 'Doe', 'Johnson', '0987654321', '1985-02-02', 2);
INSERT INTO employee (name, surname, family_name, phone, birthday, department_id) VALUES ('Bob', 'Smith', 'Brown', '1122334455', '1990-03-03', 3);