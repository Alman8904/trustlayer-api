ALTER TABLE clients
    ADD COLUMN email VARCHAR(255) NOT NULL;

CREATE UNIQUE INDEX uq_clients_email_lower
    ON clients (LOWER(email));