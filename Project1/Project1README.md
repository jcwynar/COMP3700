Design Requirements:
  This system requires a working java compiler and runner (I used IntelliJ), as well as a SQLIte or SQL database (I used DB Browser for SQLite). 
  The user must know what items they want to add to the DB, as well as the specific values for those items (e.g., price, quantity, names, etc.).
  The system must be able to handle misinputs, duplicates, and other various errors.
  
Technical Notes: 
  This system uses java as its main language, with an emphasis on classes, functions, singletons, and adapters.
  My database design is very simple, consisting of 3 tables (namely Products, Customers, and Orders).
  Classes are designed to be referenced throughout my system.
  The jar file included in this repo will need to be added to your class path prior to compilation and running. It allows the SQL plugins to work.
  
Comments: 
  My system is fairly resilient against errors, including user error, namely duplicate database additions. 
  I prevent my users from duplicating records, but they can update or delete records. 
  Overall, I believe my system, while not the most aesthetically pleasing, gets the job done quickly and efficiently.
