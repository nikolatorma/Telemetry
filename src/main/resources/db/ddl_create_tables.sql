CREATE TABLE Vehicle (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    license_plate VARCHAR(255),
    vehicle_type VARCHAR(255)
);

CREATE TABLE Driver (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    identification_card VARCHAR(255),
    vehicle_id BIGINT,
    CONSTRAINT fk_driver_vehicle FOREIGN KEY (vehicle_id) REFERENCES Vehicle(id)
);

CREATE TABLE VehicleMessage (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    timestamp DATETIME,
    longitude DOUBLE,
    latitude DOUBLE,
    speed INT,
    event_id INT,
    vehicle_id BIGINT,
    CONSTRAINT fk_message_vehicle FOREIGN KEY (vehicle_id) REFERENCES Vehicle(id)
);