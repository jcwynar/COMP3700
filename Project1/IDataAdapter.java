public interface IDataAdapter {
   public static final int CONNECTION_OPEN_OK = 100;
   public static final int CONNECTION_OPEN_FAIL = 101;
  
   public static final int CONNECTION_CLOSE_OK = 200;
   public static final int CONNECTION_CLOSE_FAIL = 201;

   public static final int PRODUCT_SAVED_OK = 0;
   public static final int PRODUCT_DUPLICATE_ERROR = 1;

   public static final int CUSTOMER_SAVED_OK = 0;
   public static final int CUSTOMER_DUPLICATE_ERROR = 1;

   public static final int ORDER_SAVED_OK = 500;
   public static final int ORDER_DUPLICATE_ERROR = 501;

   public int connect();
   
   public int disconnect();

   public ProductTemplate loadProduct(int id);
   public int saveProduct(ProductTemplate template);
   public int updateProduct(ProductTemplate template);
   public int deleteProduct(ProductTemplate template);

   public CustomerTemplate loadCustomer(int id);
   public int saveCustomer(CustomerTemplate template);
   public int updateCustomer(CustomerTemplate template);
   public int deleteCustomer(CustomerTemplate template);

   public int saveOrder(OrderTemplate template);
   public int updateOrder(OrderTemplate template);
   public int deleteOrder(OrderTemplate template);
}
