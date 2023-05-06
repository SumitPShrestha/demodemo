CREATE TABLE drone
(
    id               INT NOT NULL AUTO_INCREMENT,
    serial_number    VARCHAR(100),
    model            VARCHAR(20),
    weight_limit     INT,
    battery_capacity INT,
    state            VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE medication
(
    id        BIGINT PRIMARY KEY,
    name      VARCHAR(100)   NOT NULL,
    weight    DECIMAL(10, 2) NOT NULL,
    code      VARCHAR(50)    NOT NULL,
    image_url VARCHAR(200)   NOT NULL
);

CREATE TABLE dispatch
(
    id                  INT PRIMARY KEY AUTO_INCREMENT,
    drone_id            int NOT NULL,
    medications         VARCHAR(500),
    total_weight        DOUBLE,
    delivery_start_time TIMESTAMP,
    delivery_end_time   TIMESTAMP
);

CREATE TABLE activity_log
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    activity      VARCHAR(1000) NOT NULL,
    activity_time TIMESTAMP,
    comment       varchar(200)
);
