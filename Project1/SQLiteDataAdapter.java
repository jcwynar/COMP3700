import java.sql.*;

public class SQLiteDataAdapter implements IDataAdapter {
   Connection conn = null;

   @Override
   public int connect() {
      try {
         // parameters for database
         String url = "jdbc:sqlite:Project1.db";
         // establish connection
         System.out.println(System.getProperty("java.class.path"));
         try {
            Class.forName("org.sqlite.JDBC");
         } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Failed to load SQLite JDBC class", e);
         }
         conn = DriverManager.getConnection(url);
         
         if (conn == null) {
            System.out.println("Cannot make the connection!!!");
         }
         else {
            System.out.println("The connection object is " + conn);
         }
      
         System.out.println("Connection to SQLite database established");
      } catch (SQLException e) {
         System.out.println(e.getMessage());
         return CONNECTION_OPEN_FAIL;
      }
      return CONNECTION_OPEN_OK;
   }

   public int disconnect() {
      try {
         conn.close();
      } catch (SQLException e) {
         System.out.println(e.getMessage());
         return CONNECTION_CLOSE_FAIL;
      }
      return CONNECTION_CLOSE_OK;
   }

   public ProductTemplate loadProduct(int productID) {
      ProductTemplate product = null;
   
      try {
         String sql = "SELECT ProductID, Name, Price, Quantity FROM Products WHERE ProductID = " + productID;
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         if (rs.next()) {
            product = new ProductTemplate();
            product.tProductID = rs.getInt("ProductID");
            product.tName = rs.getString("Name");
            product.tPrice = rs.getDouble("Price");
            product.tQuantity = rs.getDouble("Quantity");
         }
      
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      return product;
   }

   public int saveProduct(ProductTemplate product) {
      try {
         String sql = "INSERT INTO Products(ProductID, Name, Price, Quantity) VALUES " + product;
         System.out.println(sql);
         Statement stmt = conn.createStatement();
         stmt.execute(sql);
      
      } catch (Exception e) {
         String msg = e.getMessage();
         System.out.println(msg);
         if (msg.contains("UNIQUE constraint failed")) {
            return PRODUCT_DUPLICATE_ERROR;
         }
      }
      return PRODUCT_SAVED_OK;
   }
   
   public int updateProduct(ProductTemplate product) {
      try {
         Statement stmt = conn.createStatement();

         stmt.executeUpdate("UPDATE Products SET ProductID = " + product.tProductID
                 + ", Name = \"" + product.tName + "\""
                 + ", Price = " + product.tPrice
                 + ", Quantity = " + product.tQuantity
                 + " WHERE ProductID = " + product.tProductID);
      } catch (Exception e) {
         System.out.println("catch block entered");
         String msg = e.getMessage();
         System.out.println(msg);
      }
      return PRODUCT_SAVED_OK;
   }

   public int deleteProduct(ProductTemplate product) {
      try {
         Statement stmt = conn.createStatement();

         stmt.executeUpdate("DELETE FROM Products WHERE ProductID = " + product.tProductID);
      } catch (Exception e) {
         System.out.println("catch block entered");
         String msg = e.getMessage();
         System.out.println(msg);
      }
      return PRODUCT_SAVED_OK;
   }

   public CustomerTemplate loadCustomer(int id) {
      CustomerTemplate customer = null;
   
      try {
         String sql = "SELECT * FROM Customers WHERE CustomerID = " + id;
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         if (rs.next()) {
            customer = new CustomerTemplate();
            customer.tCustomerID = id;
            customer.tName = rs.getString("Name");
            customer.tAddress = rs.getString("Address");
            customer.tPhone = rs.getString("PhoneNumber");
         }
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      return customer;
   }

   public int saveCustomer(CustomerTemplate customer) {
      try {
         String sql = "INSERT INTO Customers(CustomerID, Name, Address, Phone) VALUES " + customer;
         System.out.println(sql);
         Statement stmt = conn.createStatement();
         stmt.executeUpdate(sql);
      
      } catch (Exception e) {
         System.out.println("Catch block entered");
         String msg = e.getMessage();
         System.out.println(msg);
         if (msg.contains("UNIQUE constraint failed")) {
            return CUSTOMER_DUPLICATE_ERROR;
         }
      }
      return CUSTOMER_SAVED_OK;
   }

   public int updateCustomer(CustomerTemplate customer) {
      try {
         Statement stmt = conn.createStatement();

         stmt.executeUpdate("UPDATE Customers SET CustomerID = " + customer.tCustomerID
                 + ", Name = \"" + customer.tName + "\""
                 + ", Address = \"" + customer.tAddress + "\""
                 + ", Phone = \""+ customer.tPhone + "\""
                 + " WHERE CustomerID = " + customer.tCustomerID);
      } catch (Exception e) {
         System.out.println("catch block entered");
         String msg = e.getMessage();
         System.out.println(msg);
      }
      return CUSTOMER_SAVED_OK;
   }

   public int deleteCustomer(CustomerTemplate customer) {
      try {
         Statement stmt = conn.createStatement();

         stmt.executeUpdate("DELETE FROM Customers WHERE CustomerID = " + customer.tCustomerID);
      } catch (Exception e) {
         System.out.println("catch block entered");
         String msg = e.getMessage();
         System.out.println(msg);
      }
      return CUSTOMER_SAVED_OK;
   }

   public int saveOrder(OrderTemplate order) {
      try {
         String sql = "INSERT INTO Orders(OrderID, CustomerID, ProductID, Price, Quantity, Cost, Tax, TotalCost, OrderDate) VALUES " + order;
         System.out.println(sql);
         Statement stmt = conn.createStatement();
         stmt.executeUpdate(sql);
      
      } catch (Exception e) {
         String msg = e.getMessage();
         System.out.println(msg);
         if (msg.contains("UNIQUE constraint failed"))
            return ORDER_DUPLICATE_ERROR;
      }
      return ORDER_SAVED_OK;
   }

   public int updateOrder(OrderTemplate order) {
      try {
         Statement stmt = conn.createStatement();

         stmt.executeUpdate("UPDATE Orders SET OrderID = " + order.tOrderID
                 + ", ProductID = " + order.tProductID
                 + ", CustomerID = " + order.tCustomerID
                 + ", Price = " + order.tPrice
                 + ", Quantity = " + order.tQuantity
                 + ", Cost = " + order.tCost
                 + ", Tax = " + order.tTax
                 + ", TotalCost = " + order.tTotal
                 + ", OrderDate = \"" + order.tDate + "\""
                 + " WHERE OrderID = " + order.tOrderID);
      } catch (Exception e) {
         System.out.println("catch block entered");
         String msg = e.getMessage();
         System.out.println(msg);
      }
      return ORDER_SAVED_OK;
   }

   public int deleteOrder(OrderTemplate order) {
      try {
         Statement stmt = conn.createStatement();

         stmt.executeUpdate("DELETE FROM Orders WHERE OrderID = " + order.tOrderID);
      } catch (Exception e) {
         System.out.println("catch block entered");
         String msg = e.getMessage();
         System.out.println(msg);
      }
      return ORDER_SAVED_OK;
   }
}
