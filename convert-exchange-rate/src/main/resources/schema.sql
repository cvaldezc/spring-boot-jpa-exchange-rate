CREATE TABLE IF NOT EXISTS product (
  id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    originalPrice NUMERIC(10,2) NOT NULL,
    finalPrice NUMERIC(10,2) NOT NULL,
    currency VARCHAR(3) NOT NULL
    );