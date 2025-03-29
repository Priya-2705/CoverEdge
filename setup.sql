CREATE DATABASE coveredge;

DROP DATABASE coveredge;

USE coveredge;

SHOW TABLES;

INSERT INTO brokers (name) VALUES
('John Smith'),
('Emma Johnson'),
('Michael Brown'),
('Sarah Wilson'),
('David Miller');

SELECT * FROM brokers;
SELECT * FROM policies;
SELECT * FROM base_rates;
SELECT * FROM term_factors;
SELECT * FROM claims;

SELECT * FROM policies 
WHERE policy_status = 'ACTIVE' 
AND end_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY);


-- Add to your schema creation script
CREATE TABLE base_rates (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    policy_type VARCHAR(20) NOT NULL,
    min_age INT NOT NULL,
    max_age INT NOT NULL,
    rate DOUBLE NOT NULL
);

CREATE TABLE term_factors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    term_length INT UNIQUE NOT NULL,
    factor DOUBLE NOT NULL
);

-- Sample data
INSERT INTO base_rates (policy_type, min_age, max_age, rate) VALUES
('HEALTH', 18, 30, 0.04),
('HEALTH', 31, 50, 0.05),
('HEALTH', 51, 70, 0.07),
('AUTO', 18, 25, 0.06),
('AUTO', 26, 60, 0.04),
('AUTO', 61, 80, 0.08);

INSERT INTO term_factors (term_length, factor) VALUES
(1, 1.0),
(2, 0.98),
(3, 0.95),
(5, 0.90);

INSERT INTO customers 
    (first_name, last_name, email, phone_number, address, date_of_birth, identification_number, registration_date)
VALUES
    ('Peter', 'Parker', 'peter@dailybugle.com', '555-1234', '20 Ingram St, Queens', '2000-05-12', 'CUST12345', CURDATE()),
    ('Mary', 'Jane', 'mj@dailybugle.com', '555-5678', '10 Parker Ave, Manhattan', '2001-08-19', 'CUST12346', CURDATE()),
    ('Tony', 'Stark', 'tony@starkindustries.com', '555-9012', '10880 Malibu Point', '1970-04-04', 'CUST12347', '2020-01-01'),
    ('Steve', 'Rogers', 'steve@avengers.com', '555-3456', '569 Leaman Place, Brooklyn', '1918-07-04', 'CUST12348', '2021-05-15');
    
INSERT INTO policies 
    (policy_number, policy_type, coverage_amount, premium_amount, 
     start_date, end_date, policy_status, customer_id, broker_id, cancellation_reason)
VALUES
    ('POL-001', 'AUTO', 50000.00, 2000.00, 
     '2023-01-01', '2024-12-31', 'ACTIVE', 1, 1, NULL),
     
    ('POL-002', 'HOME', 250000.00, 5000.00, 
     '2023-06-01', '2024-06-01', 'ACTIVE', 2, 2, NULL),
     
    ('POL-003', 'HEALTH', 100000.00, 3000.00, 
     '2023-03-15', '2023-12-31', 'EXPIRED', 3, 3, NULL),
     
    ('POL-004', 'LIFE', 1000000.00, 10000.00, 
     '2022-01-01', '2030-01-01', 'CANCELLED', 1, 4, 
     'Client requested cancellation due to relocation');
     
INSERT INTO claims 
    (description, status, date_submitted, policy_id)
VALUES
    ('Car accident damage repair', 'APPROVED', '2023-05-01 14:30:00', 1),
    ('Roof damage from storm', 'PENDING', '2023-07-15 10:15:00', 2),
    ('Elective procedure not covered', 'REJECTED', '2023-09-01 09:00:00', 3),
    ('Life insurance cancellation request', 'PENDING', '2023-10-01 12:00:00', 4);