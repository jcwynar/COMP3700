Design Requirements:
  This system requires a working java compiler and runner (I used IntelliJ), as well as a SQLIte or SQL database (I used DB Browser for SQLite). 
  The user must know what items they want to add to the DB, as well as the specific values for those items (e.g., price, quantity, names, etc.).
  The system must be able to handle misinputs, duplicates, and other various errors.
  
Technical Notes: 
  This system uses java as its main language, with an emphasis on classes, functions, singletons, adapters, and a client/server overhead design.
  My database design is very simple, consisting of 3 tables (namely Products, Customers, and Orders).
  Classes are designed to be referenced throughout my system.
  The 2 jar files included in this repo are required for the system to be able to take advantage of SQLite and GSON.
  
Comments:
  I believe this system is as resilient as my previous version, even with the addition of the client/server design.
  Users are prevented from making duplicate records adding records with invalid characters. They can still update/delete records.
  The client/server system requires the user to have both running at the same time, with the server being the first one to be started.
  Overall, the UI is identical to my first version. The only difference is the underlying code and how the database is updated.
