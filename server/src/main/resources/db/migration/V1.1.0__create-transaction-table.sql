CREATE TABLE IF NOT EXISTS finapenses.transactions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,
    transaction_type VARCHAR(10) NOT NULL CHECK (transaction_type IN ('INCOME', 'EXPENSE')),
    transaction_date DATE DEFAULT CURRENT_DATE
);