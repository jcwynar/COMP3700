import java.sql.*;

public class SQLiteDataAdapter implements IDataAdapter {
    Connection conn = null;

    @Override
    public int connect(String dbfile) {
        try {
            // db parameters
            String url = "jdbc:sqlite:" + dbfile;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

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

    public ProductModel loadProduct(int productID) {
        ProductModel product = null;

        try {
            String sql = "SELECT ProductID, Name, Price, Quantity FROM Products WHERE ProductID = " + productID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                product = new ProductModel();
                product.mProductID = rs.getInt("ProductID");
                product.mName = rs.getString("Name");
                product.mPrice = rs.getDouble("Price");
                product.mQuantity = rs.getDouble("Quantity");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    public int saveProduct(ProductModel product) {
        try {
            String sql = "INSERT INTO Products(ProductID, Name, Price, Quantity) VALUES " + product;
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed")) {
                return PRODUCT_SAVE_FAIL;
            }
        }
        return PRODUCT_SAVE_OK;
    }

    public int updateProduct(ProductModel product) {
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("UPDATE Products SET ProductID = " + product.mProductID
                    + ", Name = \"" + product.mName + "\""
                    + ", Price = " + product.mPrice
                    + ", Quantity = " + product.mQuantity
                    + " WHERE ProductID = " + product.mProductID);
        } catch (Exception e) {
            System.out.println("catch block entered");
            String msg = e.getMessage();
            System.out.println(msg);
        }
        return PRODUCT_SAVE_OK;
    }

    public int deleteProduct(ProductModel product) {
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("DELETE FROM Products WHERE ProductID = " + product.mProductID);
        } catch (Exception e) {
            System.out.println("catch block entered");
            String msg = e.getMessage();
            System.out.println(msg);
        }
        return PRODUCT_SAVE_OK;
    }

    public CustomerModel loadCustomer(int id) {
        CustomerModel customer = null;

        try {
            String sql = "SELECT * FROM Customers WHERE CustomerID = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                customer = new CustomerModel();
                customer.mCustomerID = id;
                customer.mName = rs.getString("Name");
                customer.mAddress = rs.getString("Address");
                customer.mPhone = rs.getString("Phone");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    public int saveCustomer(CustomerModel customer) {
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
                return CUSTOMER_SAVE_FAIL;
            }
        }
        return CUSTOMER_SAVE_OK;
    }

    public int updateCustomer(CustomerModel customer) {
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("UPDATE Customers SET CustomerID = " + customer.mCustomerID
                    + ", Name = \"" + customer.mName + "\""
                    + ", Address = \"" + customer.mAddress + "\""
                    + ", Phone = \""+ customer.mPhone + "\""
                    + " WHERE CustomerID = " + customer.mCustomerID);
        } catch (Exception e) {
            System.out.println("catch block entered");
            String msg = e.getMessage();
            System.out.println(msg);
        }
        return CUSTOMER_SAVE_OK;
    }

    public int deleteCustomer(CustomerModel customer) {
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("DELETE FROM Customers WHERE CustomerID = " + customer.mCustomerID);
        } catch (Exception e) {
            System.out.println("catch block entered");
            String msg = e.getMessage();
            System.out.println(msg);
        }
        return CUSTOMER_SAVE_OK;
    }

    public int saveOrder(OrderModel order) {
        try {
            String sql = "INSERT INTO Orders(OrderID, CustomerID, ProductID, Price, Quantity, Cost, Tax, TotalCost, OrderDate) VALUES " + order;
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return ORDER_SAVE_FAIL;
        }
        return ORDER_SAVE_OK;
    }

    public int updateOrder(OrderModel order) {
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("UPDATE Orders SET OrderID = " + order.mOrderID
                    + ", ProductID = " + order.mProductID
                    + ", CustomerID = " + order.mCustomerID
                    + ", Price = " + order.mPrice
                    + ", Quantity = " + order.mQuantity
                    + ", Cost = " + order.mCost
                    + ", Tax = " + order.mTax
                    + ", TotalCost = " + order.mTotal
                    + ", OrderDate = \"" + order.mDate + "\""
                    + " WHERE OrderID = " + order.mOrderID);
        } catch (Exception e) {
            System.out.println("catch block entered");
            String msg = e.getMessage();
            System.out.println(msg);
        }
        return ORDER_SAVE_OK;
    }

    public int deleteOrder(OrderModel order) {
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("DELETE FROM Orders WHERE OrderID = " + order.mOrderID);
        } catch (Exception e) {
            System.out.println("catch block entered");
            String msg = e.getMessage();
            System.out.println(msg);
        }
        return ORDER_SAVE_OK;
    }
}
