CREATE TABLE signature (
    id BIGSERIAL PRIMARY KEY UNIQUE,
    name VARCHAR(100) NOT NULL,
    dateSigned date NOT NULL,
    dueDate date NOT NULL,
    client_id BIGSERIAL NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id)
);