CREATE TABLE parts (
        model CHAR(30) PRIMARY KEY,
        type Char(5),
        price numeric(11,2) CONSTRAINT positive_price CHECK (price > 0),
        stock integer CONSTRAINT positive_stock CHECK (stock >= 0),
        refillstock integer CONSTRAINT refillstock_check CHECK (refillstock >= 0),
        producer CHAR(30)
        );