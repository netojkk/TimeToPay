CREATE TABLE subscription (
    id BIGSERIAL PRIMARY KEY UNIQUE,
    name VARCHAR(100) NOT NULL,
    value DECIMAL(12,2) NOT NULL,
    date_signed date NOT NULL,
    expiration_date date NOT NULL,
    client_id BIGSERIAL NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id)
);