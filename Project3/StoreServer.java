import com.google.gson.Gson;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreServer {
    static String dbfile = "/Users/jackiechan/School/SoftwareModeling/Project3/Project3.db";

    public static void main(String[] args) {
        int port = 1001;

        if (args.length > 0) {
            System.out.println("Running arguments: ");
            for (String arg : args) {
                System.out.println(arg);
            }

            port = Integer.parseInt(args[0]);

            dbfile = args[1];
        }

        try {
            SQLiteDataAdapter adapter = new SQLiteDataAdapter();

            Gson gson = new Gson();

            adapter.connect(dbfile);

            ServerSocket server = new ServerSocket(port);

            System.out.println("Server is listening at port " + port);

            while (true) {
                Socket pipe = server.accept();
                PrintWriter out = new PrintWriter(pipe.getOutputStream(), true);
                Scanner in = new Scanner(pipe.getInputStream());

                MessageModel msg = gson.fromJson(in.nextLine(), MessageModel.class);

                if (msg.code == MessageModel.GET_PRODUCT) {
                    System.out.println("GET Product with id = " + msg.data);
                    ProductModel p = adapter.loadProduct(Integer.parseInt(msg.data));
                    if (p == null) {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_OK; // success!
                        msg.data = gson.toJson(p);
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.PUT_PRODUCT) {
                    ProductModel p = gson.fromJson(msg.data, ProductModel.class);
                    System.out.println("PUT command with Product = " + p);
                    int res = adapter.saveProduct(p);
                    if (res == IDataAdapter.PRODUCT_SAVE_OK) {
                        msg.code = MessageModel.OPERATION_OK;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.UPDATE_PRODUCT) {
                    ProductModel p = gson.fromJson(msg.data, ProductModel.class);
                    System.out.println("UPDATE command with Product = " + p);
                    int res = adapter.updateProduct(p);
                    if (res == IDataAdapter.PRODUCT_SAVE_OK) {
                        msg.code = MessageModel.OPERATION_OK;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.DELETE_PRODUCT) {
                    ProductModel p = gson.fromJson(msg.data, ProductModel.class);
                    System.out.println("DELETE command with Product = " + p);
                    int res = adapter.deleteProduct(p);
                    if (res == IDataAdapter.PRODUCT_SAVE_OK) {
                        msg.code = MessageModel.OPERATION_OK;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.GET_CUSTOMER) {
                    System.out.println("GET customer with id = " + msg.data);
                    CustomerModel c = adapter.loadCustomer(Integer.parseInt(msg.data));
                    if (c == null) {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_OK; // load successfully!!!
                        msg.data = gson.toJson(c);
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.PUT_CUSTOMER) {
                    CustomerModel c = gson.fromJson(msg.data, CustomerModel.class);
                    System.out.println("PUT command with Customer = " + c);
                    int res = adapter.saveCustomer(c);
                    if (res == IDataAdapter.CUSTOMER_SAVE_OK) {
                        msg.code = MessageModel.OPERATION_OK;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.UPDATE_CUSTOMER) {
                    CustomerModel c = gson.fromJson(msg.data, CustomerModel.class);
                    System.out.println("UPDATE command with Customer = " + c);
                    int res = adapter.updateCustomer(c);
                    if (res == IDataAdapter.CUSTOMER_SAVE_OK) {
                        msg.code = MessageModel.OPERATION_OK;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.DELETE_CUSTOMER) {
                    CustomerModel c = gson.fromJson(msg.data, CustomerModel.class);
                    System.out.println("DELETE command with Customer = " + c);
                    int res = adapter.deleteCustomer(c);
                    if (res == IDataAdapter.CUSTOMER_SAVE_OK) {
                        msg.code = MessageModel.OPERATION_OK;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.GET_ORDER) {
                    System.out.println("GET order with id = " + msg.data);
                    OrderModel o = adapter.loadOrder(Integer.parseInt(msg.data));
                    if (o == null) {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_OK; // load successfully!!!
                        msg.data = gson.toJson(o);
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.PUT_ORDER) {
                    OrderModel order = gson.fromJson(msg.data, OrderModel.class);
                    System.out.println("PUT command with Order = " + order);
                    int res = adapter.saveOrder(order);
                    if (res == IDataAdapter.ORDER_SAVE_OK) {
                        msg.code = MessageModel.OPERATION_OK;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.UPDATE_ORDER) {
                    OrderModel order = gson.fromJson(msg.data, OrderModel.class);
                    System.out.println("UPDATE command with Order = " + order);
                    int res = adapter.updateOrder(order);
                    if (res == IDataAdapter.ORDER_SAVE_OK) {
                        msg.code = MessageModel.OPERATION_OK;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.DELETE_ORDER) {
                    OrderModel order = gson.fromJson(msg.data, OrderModel.class);
                    System.out.println("DELETE command with Order = " + order);
                    int res = adapter.deleteOrder(order.mOrderID);
                    if (res == IDataAdapter.ORDER_SAVE_OK) {
                        msg.code = MessageModel.OPERATION_OK;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.GET_CUSTOMER_BY_USER) {
                    System.out.println("GET customer with user id = " + msg.data);
                    CustomerModel c = adapter.loadCustomerByUser(Integer.parseInt(msg.data));
                    if (c == null) {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_OK; // load successfully!!!
                        msg.data = gson.toJson(c);
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.GET_USER) {
                    System.out.println("GET user with username = " + msg.data);
                    UserModel u = adapter.loadUser(msg.data);
                    if (u == null) {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_OK; // load successfully!!!
                        msg.data = gson.toJson(u);
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.PUT_USER) {
                    UserModel u = gson.fromJson(msg.data, UserModel.class);
                    System.out.println("PUT command with User = " + u);
                    int res = adapter.saveUser(u);
                    if (res == IDataAdapter.USER_SAVE_OK) {
                        msg.code = MessageModel.OPERATION_OK;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.REGISTER_USER) {
                    UserModel u = gson.fromJson(msg.data, UserModel.class);
                    System.out.println("REGISTER command with User = " + u);
                    int res = adapter.registerUser(u);
                    if (res == IDataAdapter.USER_SAVE_OK) {
                        msg.code = MessageModel.OPERATION_OK;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.GET_USER_ROW_COUNT) {
                    System.out.println("GET row count");

                    ArrayList<Integer> countList = adapter.getUserRowCount();

                    if (countList == null) {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_OK;
                        msg.data = gson.toJson(countList);
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.GET_CUSTOMER_ROW_COUNT) {
                    System.out.println("GET row count");

                    ArrayList<Integer> countList = adapter.getCustomerRowCount();

                    if (countList == null) {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_OK;
                        msg.data = gson.toJson(countList);
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.GET_ORDER_ROW_COUNT) {
                    System.out.println("GET row count");

                    ArrayList<Integer> countList = adapter.getOrderRowCount();

                    if (countList == null) {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_OK;
                        msg.data = gson.toJson(countList);
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.GET_ORDER_HISTORY) {
                    System.out.println("GET order history");

                    ArrayList<OrderModel> orderList = adapter.loadOrderHistory(Integer.parseInt(msg.data));
                    if (orderList == null) {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_OK; // load successfully!!!
                        msg.data = gson.toJson(orderList);
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.SEARCH_PRODUCT_BY_PRICE) {
                    System.out.println("SEARCH Product Database for price <= " + msg.data);
                    ArrayList<ProductModel> pMList = adapter.searchProductByPrice(Double.parseDouble(msg.data));
                    if (pMList == null) {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_OK; // load successfully!!!
                        msg.data = gson.toJson(pMList);
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == MessageModel.SEARCH_PRODUCT_BY_NAME) {
                    System.out.println("SEARCH Product Database for name = " + msg.data);
                    ArrayList<ProductModel> pMList = adapter.searchProductByName(msg.data);
                    if (pMList == null) {
                        msg.code = MessageModel.OPERATION_FAILED;
                    }
                    else {
                        msg.code = MessageModel.OPERATION_OK; // load successfully!!!
                        msg.data = gson.toJson(pMList);
                    }
                    out.println(gson.toJson(msg));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
