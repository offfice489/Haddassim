USE master
GO
IF NOT EXISTS (
   SELECT name
   FROM sys.databases
   WHERE name = N'PatientsDB'
)
CREATE DATABASE [PatientsDB]
GO

USE [PatientsDB]
-- Create a new table called 'Patients_details' in schema 'dbo'
-- Drop the table if it already exists
IF OBJECT_ID('dbo.Patients_details', 'U') IS NOT NULL
DROP TABLE dbo.Patients_details
GO


CREATE TABLE dbo.Patients_details
(
   patientId      varchar(9)    NOT NULL   PRIMARY KEY, -- primary key column
   l_name      varchar(50)  NOT NULL,
   f_name       varchar(50)  NOT NULL,
   bornDate  date  NOT NULL,
   phone      varchar(9),
   mobilePhone      varchar(10)  NOT NULL,
   city       varchar(50)  NOT NULL,
   street       varchar(50)  NOT NULL,
   numApartment      int, 
);
GO


CREATE TABLE dbo.Vaccination
(
	vaccination_id int not null primary key identity(1,1), ----PK
	patientId  varchar(9)    NOT NULL, ----FK
   vaccineDate      date,  --מועד החיסון
   vaccineManufacturer varchar(7), --יצרן החיסון
   numVaccine int,   --מס' החיסון
   foreign key (patientId) references Patients_details(patientId)
   
);
GO

CREATE TABLE dbo.Covid_sicks(
	covidID int not null primary key identity(1,1), ----PK
	patientId  varchar(9)    NOT NULL , ----FK
	positiveResult date,
	recoveryDate date,
	foreign key (patientId) references Patients_details(patientId)
);
GO


------מילוי הטבלאות
insert into dbo.Patients_details (patientId, l_name, f_name, bornDate, phone, mobilePhone, city, street, numApartment)
values ('212560544', 'Zada', 'Miri', '2001-11-02', '039093402', '0534185673', 'Elad', 'yeuda hanasi', 90);
insert into dbo.Patients_details (patientId, l_name, f_name, bornDate, phone, mobilePhone, city, street, numApartment)
values ('314886607', 'Zada', 'Yair', '1999-08-15', '036163552', '0548594986', 'Elad', 'yeuda hanasi', 90);
insert into dbo.Vaccination (patientId, vaccineDate, vaccineManufacturer, numVaccine) values ('212560544', '2020-05-02', 'Moderna', 1);
insert into dbo.Vaccination (patientId, vaccineDate, vaccineManufacturer, numVaccine) values ('212560544', '2020-05-25', 'Moderna', 2);
insert into dbo.Vaccination (patientId, vaccineDate, vaccineManufacturer, numVaccine) values ('212560544', '2020-06-30', 'Fizer', 3);
insert into dbo.Vaccination (patientId, vaccineDate, vaccineManufacturer, numVaccine) values ('314886607', '2020-05-02', 'Moderna', 1);
insert into dbo.Vaccination (patientId, vaccineDate, vaccineManufacturer, numVaccine) values ('314886607', '2020-05-29', 'Fizer', 2);
insert into dbo.Vaccination (patientId, vaccineDate, vaccineManufacturer, numVaccine) values ('214786149', '2020-06-10', 'Fizer', 1);
insert into [dbo].[Covid_sicks] ([patientId], [positiveResult], [recoveryDate]) values ('212560544', '2021-03-01', '2021-03-14');
insert into [dbo].[Covid_sicks] ([patientId], [positiveResult], [recoveryDate]) values ('314886607', '2020-12-03', '2020-12-17');

--- שאילתות לצורך בדיקה
select * from [dbo].[Patients_details];
SELECT * FROM Patients_details WHERE patientId = '212560544';
select * from [dbo].[Vaccination] where numVaccine = 2;
select * from [dbo].[Vaccination] where patientId = 212560544;
select * from [dbo].[Vaccination];
select max(numVaccine) from [dbo].[Vaccination] where patientId = '314886607' 
select patientId from [dbo].[Patients_details] where EXISTS (select patientId from [dbo].[Patients_details]) and patientId='212560544';
 SELECT COUNT(*) FROM dbo.Patients_details WHERE patientID = '212560544'


