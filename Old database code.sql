CREATE TABLE computer(
  model CHAR(30) PRIMARY KEY,
  name CHAR(30),
  CPU CHAR(30),
  ram CHAR(30),
  Storage CHAR(30),
  Motherboard Char(30),
  Computercase Char(30),
  graphics Char(30)
);

CREATE TABLE CPU (
        model CHAR(30) PRIMARY KEY,
        speed DECIMAL(4,2),
        Socket CHAR(10),
        cores integer,
        FSB integer,
        hasgrafics Boolean,
        price numeric(11,2) CONSTRAINT positive_price CHECK (price > 0),
        stock integer CONSTRAINT positive_stock CHECK (stock >= 0),
        refillstock integer CONSTRAINT refillstock_check CHECK (refillstock >= 0),
        producer CHAR(30)
        
);

CREATE TABLE part (
        model CHAR(30) PRIMARY KEY,
        type Char(5),
        price numeric(11,2) CONSTRAINT positive_price CHECK (price > 0),
        stock integer CONSTRAINT positive_stock CHECK (stock >= 0),
        refillstock integer CONSTRAINT refillstock_check CHECK (refillstock >= 0),
        producer CHAR(30)
        
);

CREATE TABLE Storage (
        model CHAR(30) PRIMARY KEY,
        typessd Boolean,
        spaceGB integer,
        price numeric(11,2) CONSTRAINT positive_price CHECK (price > 0),
        stock integer CONSTRAINT positive_stock CHECK (stock >= 0),
        refillstock integer CONSTRAINT refillstock_check CHECK (refillstock >= 0),
        producer CHAR(30)
);

CREATE TABLE Motherboard (
        model CHAR(30) PRIMARY KEY,
        Socket CHAR(10),
        formfactor CHAR(10),
        RAMtype CHAR(10),
        price numeric(11,2) CONSTRAINT positive_price CHECK (Price > 0),
        stock integer CONSTRAINT positive_stock CHECK (stock >= 0),
        refillstock integer CONSTRAINT refillstock_check CHECK (refillstock >= 0),
        producer CHAR(30)
);

CREATE TABLE ram (
        model CHAR(30) PRIMARY KEY,
        sizeingb integer,
        RAMtype CHAR(10),
        FSB integer,
        price numeric(11,2) CONSTRAINT positive_price CHECK (Price > 0),
        stock integer CONSTRAINT positive_stock CHECK (stock >= 0),
        refillstock integer CONSTRAINT refillstock_check CHECK (refillstock >= 0),
        producer CHAR(30)
);

CREATE TABLE computercase (
        model CHAR(30) PRIMARY KEY,
        formfactor CHAR(10),
        price numeric(11,2) CONSTRAINT positive_price CHECK (Price > 0),
        stock integer CONSTRAINT positive_stock CHECK (stock >= 0),
        refillstock integer CONSTRAINT refillstock_check CHECK (refillstock >= 0),
        producer CHAR(30)
);

CREATE TABLE graphics  (
        model CHAR(30) PRIMARY KEY,
        speed DECIMAL(4,2),
        ramgb numeric(5,2),
        price numeric(11,2) CONSTRAINT positive_price CHECK (Price > 0),
        stock integer CONSTRAINT positive_stock CHECK (stock >= 0),
        refillstock integer CONSTRAINT refillstock_check CHECK (refillstock >= 0),
        producer CHAR(30)
);





INSERT INTO CPU (model, speed, socket, cores,FSB,hasgrafics, price, stock, refillstock, producer)
VALUES 
('CPU-I7-6700k', 4.2, 'LGA1151','8',160000,true,'2890',1,12,'Intel'),
('CPU-I5-6600k', 3.7, 'LGA1151','4',160000,true,'1999',1,12,'Intel'),
('CPU-I5-6500c', 3.4, 'LGA1151','4',160000,true,'1623',1,12,'Intel'),
('CPU-I7-4790k', 4, 'LGA1150','8',160000,true,'2699',1,12,'Intel'),
('CPU-I7-5820k', 3.6, 'LGA2011-v3','12',160000,false,'3166',1,12,'Intel'),
('CPU-I7-5960x', 3.5, 'LGA2011-v3','16',160000,false,'8490',1,12,'Intel'),
('CPU-I7-5930k', 3.7, 'LGA2011-v3','12',160000,false,'4448',1,12,'Intel'),
('CPU-E5-2999-v3', 3.6, 'LGA2011-v3','36',160000,false,'37418',1,12,'Intel'),
('CPU-E5-1320-v3', 3.2, 'LGA2011-v3','24',160000,false,'3553',1,12,'Intel'),
('CPU-FX-8350', 4, 'AM3+','8',80000,false,'1380',1,12,'AMD'),
('CPU-FX-9590', 4.7, 'AM3+','8',80000,false,'1810',1,12,'AMD'),
('CPU-FX-6300', 3.5, 'AM3+','6',80000,false,'873',1,12,'AMD'),
('CPU-FX-4300', 3.8, 'AM3+','4',80000,false,'603',1,12,'AMD');


INSERT INTO Storage(model, typessd,spacegb,price,stock, refillstock, producer)
VALUES
('HDD-SG-2000', false,2000,800,1,12,'Seagate'),
('HDD-SG-4000', false,4000,1600,1,12,'Seagate'),
('HDD-SG-8000', false,8000,2600,1,12,'Seagate'),
('HDD-530-128', true, 256,600,1,12,'Intel'),
('HDD-530-256', true, 256,1000,1,12,'Intel'),
('HDD-530-512', true, 256,1800,1,12,'Intel');



INSERT INTO motherboard(model, socket,formfactor,RAMtype,price,stock, refillstock, producer)
VALUES
('MB-ASUS-Z170K','LGA1151', 'ATX','DDR4',900,1,12,'ASUS'),
('MB-ASUS-Z170X','LGA1151', 'ATX','DDR4',1400,1,12,'ASUS'),
('MB-ASUS-Z170D','LGA1151', 'ITX','DDR4',1200,1,12,'ASUS'),
('MB-MSI-X99k','LGA2011', 'ATX','DDR4',1500,1,12,'MSI'),
('MB-MSI-X99X','LGA2011', 'ATX','DDR4',2400,1,12,'MSI'),
('MB-MSI-X99D','LGA2011', 'ITX','DDR4',2000,1,12,'MSI'),
('MB-ASK-AM970S','AM3+', 'ATX','DDR3',400,1,12,'Asrock'),
('MB-ASK-AM970k','AM3+', 'ATX','DDR3',700,1,12,'Asrock'),
('MB-ASK-AM970D','AM3+', 'ITX','DDR3',100,1,12,'Asrock');


INSERT INTO  ram(model,RAMtype,FSB, sizeingb,price,stock, refillstock, producer)
VALUES
('RAM-Kingston-DDR3-16gb','DDR3',80000, 16 ,800 ,1,12,'Kingston'),
('RAM-Kingston-DDR3-4gb','DDR3',80000, 4 ,300 ,1,12,'Kingston'),
('RAM-Corsair-DDR3-8gb','DDR3',80000, 8 ,500 ,1,12,'Corsair'),
('RAM-Kingston-DDR4-16gb','DDR4',160000, 16 ,800,1,12,'Kingston'),
('RAM-Kingston-DDR4-4gb','DDR4',160000, 4 ,300 ,1,12,'Kingston'),
('RAM-Corsair-DDR4-8gb','DDR4',160000 ,8 ,500 ,1,12,'Corsair');


INSERT INTO graphics (model, speed,ramgb,price,stock, refillstock, producer)
VALUES
('GFX-GTX980', 1.27, 4,4328,1,12,'Nvidia'),
('GFX-GTX930', 0.6, 1,600,1,12,'Nvidia'),
('GFX-GTX970', 1.1, 4,2600,1,12,'Nvidia'),
('GFX-R9-390', 1.27, 4,1800,1,12,'AMD'),
('GFX-Fury', 1.27, 4,300,1,12,'AMD');



INSERT INTO computerCase(model, formfactor,price,stock, refillstock, producer)
VALUES
('CASE-supreme', 'ATX',800,1,20,'Coolermaster'),
('CASE-mini', 'ITX',400,1,20,'Case Labs');



INSERT INTO computer(model, name,cpu,ram, storage, motherboard,computercase,graphics )
VALUES
('SYS-1', 'Blzing Firestorm','CPU-I7-5960x','RAM-Kingston-DDR4-16gb','HDD-530-512','MB-MSI-X99X','CASE-supreme','GFX-GTX980'),
('SYS-2', 'Starstruck','CPU-I5-6600k','RAM-Kingston-DDR4-4gb','HDD-530-512','MB-ASUS-Z170D','CASE-mini',null),
('SYS-3', 'Workstation','CPU-E5-2999-v3','RAM-Kingston-DDR4-16gb','HDD-530-512','MB-MSI-X99X','CASE-supreme','GFX-GTX980'),
('SYS-4', 'Budget','CPU-FX-4300','RAM-Kingston-DDR3-4gb','HDD-SG-2000','MB-ASK-AM970S','CASE-supreme','GFX-GTX930'),
('SYS-5', 'Officecomp','CPU-I5-6500c','RAM-Kingston-DDR4-4gb','HDD-530-256','MB-ASUS-Z170X','CASE-supreme',null),
('SYS-6', 'server','CPU-E5-2999-v3','RAM-Kingston-DDR4-16gb','HDD-530-512','MB-MSI-X99X','CASE-supreme','GFX-GTX930'),
('SYS-7', 'Officecomp2','CPU-I5-6500c','RAM-Corsair-DDR4-8gb','HDD-530-256','MB-ASUS-Z170K','CASE-supreme',null),
('SYS-8', 'Low-End-server','CPU-E5-1320-v3','RAM-Kingston-DDR4-16gb','HDD-530-512','MB-MSI-X99X','CASE-supreme','GFX-GTX930');
    
