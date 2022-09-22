CREATE TABLE patient (
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    name     VARCHAR(50)  NOT NULL,
    phone    VARCHAR(15)  NOT NULL,
    username VARCHAR(20)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (username)
);

CREATE TABLE doctor (
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(50)  NOT NULL,
    phone      VARCHAR(15)  NOT NULL,
    username   VARCHAR(20)  NOT NULL,
    password   VARCHAR(255) NOT NULL,
    speciality VARCHAR(30)  NOT NULL,
    degree     VARCHAR(50)  NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (username)
);

CREATE TABLE appointment (
    id               BIGINT      NOT NULL AUTO_INCREMENT,
    slot             VARCHAR(20) NOT NULL,
    appointment_date DATE        NOT NULL,
    patient_id       BIGINT      NOT NULL,
    doctor_id        BIGINT      NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (patient_id) REFERENCES patient (id),
    FOREIGN KEY (doctor_id) REFERENCES doctor (id)
);