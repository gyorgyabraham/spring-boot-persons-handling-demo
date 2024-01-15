CREATE TABLE persons.dbo.person (
	id bigint IDENTITY(1,1) NOT NULL,
	first_name varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	last_name varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	permanent_address_id bigint NULL,
	temporary_address_id bigint NULL,
	CONSTRAINT PK__person__3213E83F3A8598EB PRIMARY KEY (id),
	CONSTRAINT UK6wjuvbalf4x2thqkfxejnlbvh UNIQUE (first_name,last_name)
);

CREATE TABLE persons.dbo.address (
	id bigint IDENTITY(1,1) NOT NULL,
	city varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	country varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	house_nr varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	street varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	zipcode varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	owning_person_id bigint NULL,
	CONSTRAINT PK__address__3213E83F8DB3118E PRIMARY KEY (id)
);

CREATE TABLE persons.dbo.contact (
	contact_type varchar(31) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	id bigint IDENTITY(1,1) NOT NULL,
	email_address varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	phone_number varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	person_id bigint NULL,
	CONSTRAINT PK__contact__3213E83FA7C1FBC7 PRIMARY KEY (id)
);

-- foreign key definitions are added AFTER data is loaded
-- this is because SQL server does not allow deferring constraint to the end of transaction