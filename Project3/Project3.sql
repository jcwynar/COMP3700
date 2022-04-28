/*
 * Jack Cwynar
 * Project 3 SQL File
 */
 
 CREATE TABLE Products (
"ProductID" int not null PRIMARY KEY,
"Name" varchar(100),
"Price" float,
"Quantity" int
);

CREATE TABLE Customers(
"CustomerID" int not null PRIMARY KEY,
"Name" varchar(100),
"Address" varchar(100),
"Phone" varchar(100),
"UserID" int not NULL
);

CREATE TABLE Orders (
"OrderID" int not null PRIMARY KEY,
"CustomerID" int not null,
"ProductID" int not null,
"Price" float,
"Quantity" int,
"Cost" float,
"Tax" float,
"TotalCost" float,
"OrderDate" varchar(100)
);

CREATE TABLE Users (
"Username" varchar(100),
"Password" varchar(100),
"Name" varchar(100),
"UserType" int not null,
"UserID" int not null
);