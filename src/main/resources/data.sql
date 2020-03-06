DROP TABLE IF EXISTS ExchangeRates;

CREATE TABLE ExchangeRates (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  origin_currency VARCHAR(3) NOT NULL,
  target_currency VARCHAR(3) NOT NULL,
  rate DECIMAL NOT NULL,
  is_active INT NOT NULL DEFAULT 1
);

INSERT INTO ExchangeRates (origin_currency, target_currency, rate) VALUES
  ('PEN', 'USD', 3.4),
  ('USD', 'PEN', 1.2),
  ('EUR', 'USD', 2.34);
