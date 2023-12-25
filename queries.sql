USE reservationTaxi;
SELECT schema_name
FROM information_schema.schemata;
-- Client table

CREATE TABLE client (

 idClient int IDENTITY(1,1) PRIMARY KEY,
 nom VARCHAR(10),
 prenom VARCHAR(10),
 telephone VARCHAR(10),
 email VARCHAR(40),
 password VARCHAR(15)

);
SELECT * FROM client;
DROP TABLE client;
DELETE FROM client WHERE idClient = 11;
DELETE FROM client WHERE idClient = 15;

--Taxi
CREATE TABLE taxi (

 matricule VARCHAR(10) PRIMARY KEY,
 modele VARCHAR(10),
 status VARCHAR(10)

);
SELECT * FROM taxi;
DELETE taxi;
DROP TABLE taxi;
UPDATE taxi set status = 'Occupe' WHERE matricule= 'JKL012';
--Conducteur

CREATE TABLE conducteur (

 idConducteur int IDENTITY(1,1) PRIMARY KEY ,
 nom VARCHAR(10),
 prenom VARCHAR(10),
 telephone VARCHAR(10),
 email VARCHAR(40),
 matricule VARCHAR(10) FOREIGN KEY REFERENCES taxi(matricule)

);
SELECT * FROM conducteur;
--Ajout d'une colonne affecte
DROP TABLE conducteur;
ALTER TABLE conducteur ADD password VARCHAR(15);



--Reservation

CREATE TABLE reservation (

 idReservation int IDENTITY(1,1) PRIMARY KEY,
 lieuSource VARCHAR(15),
 lieuDestination VARCHAR(15),
 typePaiement VARCHAR(15),
 tarif FLOAT,
 date DATE,
 heure TIME,
 idClient INT FOREIGN KEY REFERENCES client(idClient),
 idConducteur INT FOREIGN KEY REFERENCES conducteur(idConducteur),
 matricule  VARCHAR(10) FOREIGN KEY REFERENCES taxi(matricule)

);
-- Modify lieuSource column
ALTER TABLE reservation
ALTER COLUMN lieuSource VARCHAR(255);

-- Modify lieuDestination column
ALTER TABLE reservation
ALTER COLUMN lieuDestination VARCHAR(255);
SELECT * FROM reservation;
DROP TABLE reservation;




--- insertion ---
-- Insertion dans la table client
INSERT INTO client (nom, prenom, telephone, email, password) VALUES ('Guer', 'Maria', '123456789', 'maria.guer@gmail.com', 'M123@');

-- Insertion dans la table taxi
INSERT INTO taxi (matricule, modele, status) VALUES ('ABC123', 'Dacia', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('XYZ789', 'Toyota', 'Occupe');
INSERT INTO taxi (matricule, modele, status) VALUES ('DEF456', 'Honda', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('GHI789', 'Ford', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('JKL012', 'Nissan', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('MNO345', 'Chevrolet', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('PQR678', 'Volkswagen', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('STU901', 'Hyundai', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('VWX234', 'Mercedes', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('YZB567', 'Subaru', 'Disponible');

INSERT INTO taxi (matricule, modele, status) VALUES ('ABC456', 'Kia', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('DEF789', 'Mazda', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('GHI012', 'Audi', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('JKL345', 'BMW', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('MNO678', 'Lexus', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('PQR901', 'Jeep', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('STU234', 'Chrysler', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('VWX567', 'Buick', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('YZA890', 'Fiat', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('BCD123', 'Tesla', 'Disponible');

INSERT INTO taxi (matricule, modele, status) VALUES ('CDE234', 'Renault', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('EFG567', 'Volvo', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('HIJ890', 'Jaguar', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('KLM123', 'Porsche', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('NOP456', 'Infiniti', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('QRS789', 'Cadillac', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('TUV012', 'Mitsubishi', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('VWX345', 'Suzuki', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('YZA678', 'Acura', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('BCD901', 'Land Rover', 'Disponible');

INSERT INTO taxi (matricule, modele, status) VALUES ('CDE567', 'Alfa Romeo', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('EFG890', 'Chrysler', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('HIJ123', 'Maserati', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('KLM456', 'Smart', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('NOP789', 'Mini', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('QRS012', 'Hummer', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('TUV345', 'Lincoln', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('VWX678', 'Ferrari', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('YZA901', 'Bentley', 'Disponible');
INSERT INTO taxi (matricule, modele, status) VALUES ('BCD234', 'Rolls-Royce', 'Disponible');










-- Insertion dans la table conducteur
INSERT INTO conducteur (nom, prenom, telephone, email, matricule) VALUES ('Ahmadi', 'Ahmad', '987654321', 'ahmad.ah@gmail.com', 'ABC123');
INSERT INTO conducteur (nom, prenom, telephone, email, matricule) VALUES ('Youness', 'Sabir', '0856743523', 'youness.sa@gmail.com', 'XYZ789');

-- Insertion dans la table reservation
INSERT INTO reservation (lieuSource, lieuDestination, typePaiement, tarif, date, heure, idClient, idConducteur, matricule)
VALUES ('Mhamid', 'Gueliz', 'CarteBancaire', 50.0, '2023-01-01', '12:00:00', 1, 1, 'ABC123');
