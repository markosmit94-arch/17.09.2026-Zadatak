DROP TABLE IF EXISTS Tip;

CREATE TABLE Tip
(
    id int PRIMARY KEY,
    name        VARCHAR(50)    NOT NULL,
    description VARCHAR(255)
);

DROP TABLE IF EXISTS Hardware;

CREATE TABLE Hardware
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    naziv    VARCHAR(50) NOT NULL,
    sifra    VARCHAR(50) NOT NULL,
    cijena   DECIMAL(10, 2) NOT NULL,
    tipId    INT NOT NULL,
    kolicina INT,
    FOREIGN KEY (tipId) REFERENCES Tip(id)
);

INSERT INTO Type(id, name, description)
VALUES(1, 'Procesor', 'New and used');

INSERT INTO Type(id, name, description)
VALUES(2, 'Ram', 'New and used');

INSERT INTO Type(id, name, description)
VALUES(3, 'Ssd', 'New and used');

INSERT INTO Type(id, name, description)
VALUES(4, 'Maticna ploca', 'New and used');

INSERT INTO Hardware(naziv, sifra, cijena, tipId, kolicina)
VALUES('Silicon Power', 'FJH6K67D', 23.45, 2, 7);

INSERT INTO Hardware(naziv, sifra, cijena, tipId, kolicina)
VALUES('Intel Core i3', '12100F', 124.99, 1, 9);

INSERT INTO Hardware(naziv, sifra, cijena, tipId, kolicina)
VALUES('Kingston A400', 'GFJ49DFJG', 611.35, 3, 11);

SELECT * FROM Hardware;