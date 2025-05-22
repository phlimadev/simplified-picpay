CREATE TABLE tb_transactions (
    id SERIAL PRIMARY KEY,
    payer_id BIGINT REFERENCES tb_users(id),
    payee_id BIGINT REFERENCES tb_users(id),
    value NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = NOW();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_updated_at
    BEFORE UPDATE ON tb_transactions
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();