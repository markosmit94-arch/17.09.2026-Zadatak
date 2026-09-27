CREATE TABLE Tip
(
    id IDENTITY PRIMARY KEY,
    name    VARCHAR(50) NOT NULL,
    description VARCHAR(255)
);


CREATE TABLE Hardware
(
    id IDENTITY PRIMARY KEY,
    naziv   VARCHAR(50) NOT NULL,
    sifra   VARCHAR(50) NOT NULL,
    cijena  int,
    tip VARCHAR(50) NOT NULL,
    kolicina int,
    FOREIGN KEY (tip) REFERENCES Tip(name)
);