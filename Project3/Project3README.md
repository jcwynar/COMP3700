Design Requirements:
  This system requires a working java compiler and runner (I used IntelliJ), as well as a SQLIte or SQL database (I used DB Browser for SQLite). 
  The user must know what items they want to add to the DB, as well as the specific values for those items (e.g., price, quantity, names, etc.).
  The system must be able to handle misinputs, duplicates, and other various errors.
  
Technical Notes: 
  This system uses java as its main language, with an emphasis on classes, functions, singletons, adapters, and a client/server overhead design.
  My database design is very simple, consisting of 4 tables (namely Products, Customers, Orders, and Users).
  This version added a login system, with two types of users: Managers and Customers.
  Managers can add and change products in the store, while customers have access to a large range of features:
    Registering as a new user, logging in, changing profile info, and making, viewing, and changing their own orders.
  Classes are designed to be referenced throughout my system.
  The 2 jar files included in this repo are required for the system to be able to take advantage of SQLite and GSON.
  
Comments:
  I believe this system is as resilient as my previous versions. Customers are prevented from accesssing other customers' orders
  and profile info. Duplicate records are also prevented, and database and user input errors are handled at all levels.
  Overall, this version is just a more fleshed-out version of the previous 2 systems, creating a somewhat similar system to an 
  actual store's management system. The UI had to be changed a good amount to accomodate the new functionality, but the base design is still there,
  albeit borken up between different screens the user sees.
