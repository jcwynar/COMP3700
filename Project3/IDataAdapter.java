import java.util.ArrayList;

public interface IDataAdapter {
    public static final int CONNECTION_OPEN_OK = 100;
    public static final int CONNECTION_OPEN_FAIL = 101;

    public static final int CONNECTION_CLOSE_OK = 200;
    public static final int CONNECTION_CLOSE_FAIL = 201;

    public static final int PRODUCT_SAVE_OK = 0;
    public static final int PRODUCT_SAVE_FAIL = 1;

    public static final int CUSTOMER_SAVE_OK = 300;
    public static final int CUSTOMER_SAVE_FAIL = 301;

    public static final int ORDER_SAVE_OK = 500;
    public static final int ORDER_SAVE_FAIL = 501;

    public static final int USER_SAVE_OK = 600;
    public static final int USER_SAVE_FAILED = 601;
    public static final int GET_USER_ROW_COUNT_FAIL = 602;
    public static final int GET_USER_ROW_COUNT_OK = 603;

    public int connect(String dbfile);
    public int disconnect();

    public ProductModel loadProduct(int id);
    public int saveProduct(ProductModel model);
    public int updateProduct(ProductModel model);
    public int deleteProduct(ProductModel model);

    public CustomerModel loadCustomer(int id);
    public int saveCustomer(CustomerModel model);
    public int updateCustomer(CustomerModel model);
    public int deleteCustomer(CustomerModel model);

    public OrderModel loadOrder(int id);
    public int saveOrder(OrderModel model);
    public int updateOrder(OrderModel model);
    public int deleteOrder(int id);

    public UserModel loadUser(String uName);
    public int saveUser(UserModel user);
    public int registerUser(UserModel user);
    public ArrayList<Integer> getUserRowCount();
    public ArrayList<Integer> getCustomerRowCount();
    public ArrayList<Integer> getOrderRowCount();

    // other methods
    public CustomerModel loadCustomerByUser(int uID);

    public ArrayList<OrderModel> loadOrderHistory(int cID);

    public ArrayList<ProductModel> searchProductByPrice(double price);
    public ArrayList<ProductModel> searchProductByName(String name);
}
