CREATE TABLE Tip
(
    id IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE Hardware
(
    id IDENTITY PRIMARY KEY,
    naziv    VARCHAR(50) NOT NULL,
    sifra    VARCHAR(50) NOT NULL,
    cijena   DOUBLE,
    tipId    INT NOT NULL,
    kolicina INT,
    FOREIGN KEY (tipId) REFERENCES Tip(id)
);
