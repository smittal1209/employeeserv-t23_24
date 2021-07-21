CREATE TABLE IF NOT EXISTS employee
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    date_of_birth VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS address
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT          NOT NULL,
    line1       VARCHAR(255) NOT NULL,
    line2       VARCHAR(255),
    city        VARCHAR(255) NOT NULL,
    state       VARCHAR(255) NOT NULL,
    country     VARCHAR(255) NOT NULL,
    zip_code    VARCHAR(6)   NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee (id)
);