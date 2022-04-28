import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class SQLiteDataAdapter implements IDataAdapter {
    Connection conn = null;

    public int connect(String dbfile) {
        try {
            // db params
            String url = "jdbc:sqlite:" + dbfile;
            // create a connection to db
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
            String sql = "Select ProductID, Name, Price, Quantity FROM Products WHERE ProductID = " + productID;
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
                customer.mUserID = rs.getInt("UserID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    public int saveCustomer(CustomerModel customer) {
        try {
            String sql = "INSERT INTO Customers(CustomerID, Name, Address, Phone, UserID) VALUES " + customer;
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
                    + ", UserID = " + customer.mUserID
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

    public OrderModel loadOrder(int id) {
        OrderModel order = null;
        try {
            String sql = "SELECT * FROM Orders WHERE OrderID = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                order = new OrderModel();
                order.mOrderID = id;
                order.mCustomerID = Integer.parseInt(rs.getString("CustomerID"));
                order.mProductID = Integer.parseInt(rs.getString("ProductID"));
                order.mPrice = Double.parseDouble(rs.getString("Price"));
                order.mQuantity = Double.parseDouble(rs.getString("Quantity"));
                order.mCost = Double.parseDouble(rs.getString("Cost"));
                order.mTax = Double.parseDouble(rs.getString("Tax"));
                order.mTotal = Double.parseDouble(rs.getString("TotalCost"));
                order.mDate = rs.getString("OrderDate");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
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

    public int deleteOrder(int oID) {
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("DELETE FROM Orders WHERE OrderID = " + oID);
        } catch (Exception e) {
            System.out.println("catch block entered");
            String msg = e.getMessage();
            System.out.println(msg);
        }
        return ORDER_SAVE_OK;
    }

    public UserModel loadUser(String uName) {
        UserModel user = null;
        try {
            String sql = ("SELECT Username, Password, Name, UserType, UserID FROM Users WHERE Username = \"" + uName + "\"");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                user = new UserModel();
                user.mUsername = rs.getString("Username");
                user.mPassword = rs.getString("Password");
                user.mFullname = rs.getString("Name");
                user.mUserType = rs.getInt("UserType");
                user.mUserID = rs.getInt("UserID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public int saveUser(UserModel user) {
        try {
            Statement stmt = conn.createStatement();
            UserModel usr = loadUser(user.mUsername); // check if user exists
            if (usr != null) {
                stmt.executeUpdate("DELETE FROM Users WHERE Username = \"" + user.mUsername + "\"");
            }

            String sql = ("INSERT INTO Users(Username, Password, Name, Usertype, UserID) VALUES " + user);
            System.out.println(sql);

            stmt.executeUpdate(sql);
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return USER_SAVE_FAILED;
        }

        return USER_SAVE_OK;
    }

    public int registerUser(UserModel user) {
        try {
            Statement stmt = conn.createStatement();
            UserModel usr = loadUser(user.mUsername); // check if user exists
            if (usr != null) {
                stmt.executeUpdate("DELETE FROM Users WHERE Username = \"" + user.mUsername + "\"");
            }

            String sql = ("INSERT INTO Users(Username, Password, Name, Usertype, UserID) VALUES " + user);

            System.out.println(sql);

            stmt.executeUpdate(sql);
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return USER_SAVE_FAILED;
        }

        return USER_SAVE_OK;
    }

    public ArrayList<Integer> getUserRowCount() {
        ArrayList<Integer> count = new ArrayList<Integer>();

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS [Count of Instances] FROM Users");

            rs.next();

            count.add(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public ArrayList<Integer> getCustomerRowCount() {
        ArrayList<Integer> count = new ArrayList<Integer>();

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS [Count of Instances] FROM Customers");

            rs.next();

            count.add(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public ArrayList<Integer> getOrderRowCount() {
        ArrayList<Integer> count = new ArrayList<Integer>();

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS [Count of Instances] FROM Orders");

            rs.next();

            count.add(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public CustomerModel loadCustomerByUser(int uID) {
        CustomerModel customer = null;

        try {
            String sql = "SELECT * FROM Customers WHERE UserID = " + uID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                customer = new CustomerModel();
                customer.mCustomerID = rs.getInt("CustomerID");
                customer.mName = rs.getString("Name");
                customer.mAddress = rs.getString("Address");
                customer.mPhone = rs.getString("Phone");
                customer.mUserID = rs.getInt("UserID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    public ArrayList<OrderModel> loadOrderHistory(int cID) {
        ArrayList<OrderModel> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Orders WHERE CustomerID = " + cID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                OrderModel order = new OrderModel();
                order.mOrderID = Integer.parseInt(rs.getString("OrderID"));
                order.mCustomerID = Integer.parseInt(rs.getString("CustomerID"));
                order.mProductID = Integer.parseInt(rs.getString("ProductID"));
                order.mPrice = Double.parseDouble(rs.getString("Price"));
                order.mQuantity = Double.parseDouble(rs.getString("Quantity"));
                order.mCost = Double.parseDouble(rs.getString("Cost"));
                order.mTax = Double.parseDouble(rs.getString("Tax"));
                order.mTotal = Double.parseDouble(rs.getString("TotalCost"));
                order.mDate = rs.getString("OrderDate");
                list.add(order);
            }
        } catch (Exception e) {
            System.out.println("Unable to make query");
        }
        return list;
    }

    public ArrayList<ProductModel> searchProductByPrice(double price){
        ArrayList<ProductModel> list = new ArrayList<ProductModel>();
        try {
            String sql = "SELECT * FROM Products WHERE Price <= " + price;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.mProductID = rs.getInt("ProductId");
                product.mName = rs.getString("Name");
                product.mPrice = rs.getDouble("Price");
                product.mQuantity = rs.getDouble("Quantity");
                list.add(product);
            }
        }
        catch (Exception e) {
            System.out.println("Unable to make query");
        }
        return list;

    }

    public ArrayList<ProductModel> searchProductByName(String name) {
        ArrayList<ProductModel> list = new ArrayList<ProductModel>();
        try {
            String sql = "SELECT * FROM Products WHERE Name = \"" + name + "\"";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.mProductID = rs.getInt("ProductId");
                product.mName = rs.getString("Name");
                product.mPrice = rs.getDouble("Price");
                product.mQuantity = rs.getDouble("Quantity");
                list.add(product);
            }
        }
        catch (Exception e) {
            System.out.println("Unable to make query");
        }
        return list;
    }
}
