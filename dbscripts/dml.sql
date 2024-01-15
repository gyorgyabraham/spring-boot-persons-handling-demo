SET IDENTITY_INSERT persons.dbo.person ON;
INSERT INTO persons.dbo.person
(id, first_name, last_name, permanent_address_id, temporary_address_id)
VALUES(1, N'Bobita', N'Miklos', 1, 2);
INSERT INTO persons.dbo.person
(id, first_name, last_name, permanent_address_id, temporary_address_id)
VALUES(2, N'Teszt', N'Janos', 3, 4);
SET IDENTITY_INSERT persons.dbo.person OFF;

SET IDENTITY_INSERT persons.dbo.address ON;
INSERT INTO persons.dbo.address
(id, city, country, house_nr, street, zipcode, owning_person_id)
VALUES(1, N'Lajosmizse', N'Hungary', N'65', N'Vas utca', N'1111', 1);
INSERT INTO persons.dbo.address
(id, city, country, house_nr, street, zipcode, owning_person_id)
VALUES(2, N'Paris', N'France', N'6543', N'Thibault street', N'F1234', 1);
INSERT INTO persons.dbo.address
(id, city, country, house_nr, street, zipcode, owning_person_id)
VALUES(3, N'Gyor', N'Hungary', N'42', N'Pilis utca', N'9665', 2);
INSERT INTO persons.dbo.address
(id, city, country, house_nr, street, zipcode, owning_person_id)
VALUES(4, N'Köln', N'Germany', N'42', N'Jürgen strasse', N'D1234', 2);
SET IDENTITY_INSERT persons.dbo.address OFF;

SET IDENTITY_INSERT persons.dbo.contact ON;
INSERT INTO persons.dbo.contact
(contact_type, id, email_address, phone_number, person_id)
VALUES(N'EMAIL', 1, N'a1@a2.com', NULL, 1);
INSERT INTO persons.dbo.contact
(contact_type, id, email_address, phone_number, person_id)
VALUES(N'PHONE', 2, NULL, N'011223344', 1);
INSERT INTO persons.dbo.contact
(contact_type, id, email_address, phone_number, person_id)
VALUES(N'EMAIL', 3, N'aa@bb.com', NULL, 2);
INSERT INTO persons.dbo.contact
(contact_type, id, email_address, phone_number, person_id)
VALUES(N'EMAIL', 4, N'ee@ff.com', NULL, 2);
SET IDENTITY_INSERT persons.dbo.contact OFF;

-- add missing foreign key constraints, which are already met in the loaded data

ALTER TABLE persons.dbo.address ADD CONSTRAINT FKa2pyn895mh4csa7ye9n2d3yr1 FOREIGN KEY (owning_person_id) REFERENCES persons.dbo.person(id);
ALTER TABLE persons.dbo.contact ADD CONSTRAINT FKjbcdaayhsa4dhcuc5q0kkw8et FOREIGN KEY (person_id) REFERENCES persons.dbo.person(id);
ALTER TABLE persons.dbo.person ADD CONSTRAINT FKjnq0mw388ehmkcwip9f3ecedi FOREIGN KEY (permanent_address_id) REFERENCES persons.dbo.address(id);
ALTER TABLE persons.dbo.person ADD CONSTRAINT FKput4er121vdnjoqfdwxlg0085 FOREIGN KEY (temporary_address_id) REFERENCES persons.dbo.address(id);