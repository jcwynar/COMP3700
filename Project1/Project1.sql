/*
Jack Cwynar
COMP 3700
Project 1
*/

CREATE TABLE Products (
"ProductID" int not null,
"Name" varchar(100),
"Price" float,
"Quantity" int
);

CREATE TABLE Customers (
"CustomerID" int not null,
"Name" varchar(100),
"Address" varchar(100),
"Phone" varchar(100)
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