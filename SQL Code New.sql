CREATE TABLE parts (
        model CHAR(30) PRIMARY KEY,
        type Char(5),
        price numeric(11,2) CONSTRAINT positive_price CHECK (price > 0),
        stock integer CONSTRAINT positive_stock CHECK (stock >= 0),
        refillstock integer CONSTRAINT refillstock_check CHECK (refillstock >= 0),
        producer CHAR(30)
        
);

CREATE TABLE CPU (
        model CHAR(30)references parts (model),
        speed DECIMAL(4,2),
        Socket CHAR(10),
        cores integer,
        FSB integer,
        hasgrafics Boolean    
);

CREATE TABLE Storage (
        model CHAR(30) references parts (model),
        typessd Boolean,
        spaceGB integer

);

CREATE TABLE Motherboard (
        model CHAR(30) references parts (model),
        Socket CHAR(10),
        formfactor CHAR(10),
        RAMtype CHAR(10)
);

CREATE TABLE ram (
        model CHAR(30) references parts (model),
        sizeingb integer,
        RAMtype CHAR(10),
        FSB integer
);

CREATE TABLE computercase (
        model CHAR(30) references parts (model),
        formfactor CHAR(10)
);

CREATE TABLE graphics  (
        model CHAR(30) references parts (model),
        speed DECIMAL(4,2),
        ramgb numeric(5,2)
);


CREATE TABLE computer(
  model CHAR(30) PRIMARY KEY,
  name CHAR(30) references parts (model),
  CPU CHAR(30) references parts (model),
  ram CHAR(30) references parts (model),
  Storage CHAR(30) references parts (model),
  Motherboard Char(30)references parts (model),
  Computercase Char(30)references parts (model),
  graphics Char(30)references parts (model)
);

INSERT INTO parts (model, type, price, stock, refillstock, producer)
VALUES 
('CPU-E5-1320-v3','CPU',3553.00,9,12,'Intel'),
('CPU-I5-6500c','CPU',1623.00,11,12,'Intel'),
('CPU-I7-6700k','CPU',2890.00,12,12,'Intel'),
('CPU-I5-6600k','CPU',1999.00,12,12,'Intel'),
('CPU-I7-4790k','CPU',2699.00,12,12,'Intel'),
('CPU-I7-5820k','CPU',3166.00,12,12,'Intel'),
('CPU-I7-5930k','CPU',4448.00,12,12,'Intel'),
('CPU-E5-2999-v3','CPU',37418.00,12,12,'Intel'),
('CPU-FX-8350','CPU',1380.00,12,12,'AMD'),
('CPU-FX-9590','CPU',1810.00,12,12,'AMD'),
('CPU-FX-6300','CPU',873.00,12,12,'AMD'),
('CPU-FX-4300','CPU',603.00,12,12,'AMD'),
('CPU-I7-5960x','CPU',8490.00,11,12,'Intel'),
('RAM-Kingston-DDR3-16gb','RAM',800.00,12,12,'Kingston'),
('RAM-Kingston-DDR3-4gb','RAM',300.00,12,12,'Kingston'),
('RAM-Corsair-DDR3-8gb','RAM',500.00,12,12,'Corsair'),
('RAM-Corsair-DDR4-8gb','RAM',500.00,12,12,'Corsair'),
('RAM-Kingston-DDR4-16gb','RAM',800.00,8,12,'Kingston'),
('RAM-Kingston-DDR4-4gb','RAM',300.00,11,12,'Kingston'),
('HDD-SG-2000','HDD',800.00,12,12,'Seagate'),
('HDD-SG-4000','HDD',1600.00,12,12,'Seagate'),
('HDD-SG-8000','HDD',2600.00,12,12,'Seagate'),
('HDD-530-128','HDD',600.00,12,12,'Intel'),
('HDD-530-512','HDD',1800.00,8,12,'Intel'),
('HDD-530-256','HDD',1000.00,11,12,'Intel'),
('CASE-mini','CASE',400.00,20,20,'CaseLabs'),
('CASE-supreme','CASE',800.00,15,20,'Coolermaster'),
('MB-ASUS-Z170K','MB',900.00,12,12,'ASUS'),
('MB-ASUS-Z170D','MB',1200.00,12,12,'ASUS'),
('MB-MSI-X99k','MB',1500.00,12,12,'MSI'),
('MB-MSI-X99D','MB',2000.00,12,12,'MSI'),
('MB-ASK-AM970S','MB',400.00,12,12,'Asrock'),
('MB-ASK-AM970k','MB',700.00,12,12,'Asrock'),
('MB-ASK-AM970D','MB',100.00,12,12,'Asrock'),
('MB-MSI-X99X','MB',2400.00,8,12,'MSI'),
('MB-ASUS-Z170X','MB',1400.00,11,12,'ASUS'),
('GFX-GTX970','GFX',2600.00,12,12,'Nvidia'),
('GFX-R9-390','GFX',1800.00,12,12,'AMD'),
('GFX-Fury','GFX',300.00,12,12,'AMD'),
('GFX-GTX980','GFX',4328.00,11,12,'Nvidia'),
('GFX-GTX930','GFX',600.00,9,12,'Nvidia');


INSERT INTO CPU (model, speed, socket, cores,FSB,hasgrafics)
VALUES 
('CPU-I7-6700k', 4.2, 'LGA1151','8',160000,true),
('CPU-I5-6600k', 3.7, 'LGA1151','4',160000,true),
('CPU-I5-6500c', 3.4, 'LGA1151','4',160000,true),
('CPU-I7-4790k', 4, 'LGA1150','8',160000,true),
('CPU-I7-5820k', 3.6, 'LGA2011-v3','12',160000,false),
('CPU-I7-5960x', 3.5, 'LGA2011-v3','16',160000,false),
('CPU-I7-5930k', 3.7, 'LGA2011-v3','12',160000,false),
('CPU-E5-2999-v3', 3.6, 'LGA2011-v3','36',160000,false),
('CPU-E5-1320-v3', 3.2, 'LGA2011-v3','24',160000,false),
('CPU-FX-8350', 4, 'AM3+','8',80000,false),
('CPU-FX-9590', 4.7, 'AM3+','8',80000,false),
('CPU-FX-6300', 3.5, 'AM3+','6',80000,false),
('CPU-FX-4300', 3.8, 'AM3+','4',80000,false);


INSERT INTO Storage(model, typessd,spacegb)
VALUES
('HDD-SG-2000', false,2000),
('HDD-SG-4000', false,4000),
('HDD-SG-8000', false,8000),
('HDD-530-128', true, 256),
('HDD-530-256', true, 256),
('HDD-530-512', true, 256);



INSERT INTO motherboard(model, socket,formfactor,RAMtype)
VALUES
('MB-ASUS-Z170K','LGA1151', 'ATX','DDR4'),
('MB-ASUS-Z170X','LGA1151', 'ATX','DDR4'),
('MB-ASUS-Z170D','LGA1151', 'ITX','DDR4'),
('MB-MSI-X99k','LGA2011', 'ATX','DDR4'),
('MB-MSI-X99X','LGA2011', 'ATX','DDR4'),
('MB-MSI-X99D','LGA2011', 'ITX','DDR4'),
('MB-ASK-AM970S','AM3+', 'ATX','DDR3'),
('MB-ASK-AM970k','AM3+', 'ATX','DDR3'),
('MB-ASK-AM970D','AM3+', 'ITX','DDR3');


INSERT INTO  ram(model,RAMtype,FSB, sizeingb)
VALUES
('RAM-Kingston-DDR3-16gb','DDR3',80000, 16),
('RAM-Kingston-DDR3-4gb','DDR3',80000, 4 ),
('RAM-Corsair-DDR3-8gb','DDR3',80000, 8),
('RAM-Kingston-DDR4-16gb','DDR4',160000, 16),
('RAM-Kingston-DDR4-4gb','DDR4',160000, 4),
('RAM-Corsair-DDR4-8gb','DDR4',160000 ,8);


INSERT INTO graphics (model, speed,ramgb)
VALUES
('GFX-GTX980', 1.27, 4),
('GFX-GTX930', 0.6, 1),
('GFX-GTX970', 1.1, 4),
('GFX-R9-390', 1.27, 4),
('GFX-Fury', 1.27, 4);



INSERT INTO computerCase(model, formfactor)
VALUES
('CASE-supreme', 'ATX'),
('CASE-mini', 'ITX');



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
    
