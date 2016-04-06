CREATE TABLE CPU (
        model CHAR(30)references parts (model),
        speed DECIMAL(4,2),
        Socket CHAR(10),
        cores integer,
        FSB integer,
        hasgrafics Boolean
        );