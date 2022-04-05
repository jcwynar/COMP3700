import com.google.gson.Gson;

public class NetworkDataAdapter implements IDataAdapter {
    String host = "localhost";
    int port = 1001;

    Gson gson = new Gson();
    SocketNetworkAdapter adapter = new SocketNetworkAdapter();
    MessageModel msg = new MessageModel();

    public int connect(String dbfile) {
        int pos = dbfile.indexOf(":");
        host = dbfile.substring(0, pos);
        port = Integer.parseInt(dbfile.substring(pos + 1, dbfile.length()));
        return 0;
    }

    public int disconnect() {
        return 0;
    }

    public ProductModel loadProduct(int id) {
        msg.code = MessageModel.GET_PRODUCT;
        msg.data = Integer.toString(id);
        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return null;
        else {
            return gson.fromJson(msg.data, ProductModel.class);
        }
    }

    public int saveProduct(ProductModel model) {
        msg.code = MessageModel.PUT_PRODUCT;
        msg.data = gson.toJson(model);

        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return IDataAdapter.PRODUCT_SAVE_FAIL;
        else {
            return IDataAdapter.PRODUCT_SAVE_OK;
        }
    }

    public int updateProduct(ProductModel model) {
        msg.code = MessageModel.UPDATE_PRODUCT;
        msg.data = gson.toJson(model);

        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return IDataAdapter.PRODUCT_SAVE_FAIL;
        else {
            return IDataAdapter.PRODUCT_SAVE_OK;
        }
    }

    public int deleteProduct(ProductModel model) {
        msg.code = MessageModel.DELETE_PRODUCT;
        msg.data = gson.toJson(model);

        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return IDataAdapter.PRODUCT_SAVE_FAIL;
        else {
            return IDataAdapter.PRODUCT_SAVE_OK;
        }
    }

    public CustomerModel loadCustomer(int id) {
        msg.code = MessageModel.GET_CUSTOMER;
        msg.data = Integer.toString(id);
        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return null;
        else {
            return gson.fromJson(msg.data, CustomerModel.class);
        }
    }

    public int saveCustomer(CustomerModel model) {
        msg.code = MessageModel.PUT_CUSTOMER;
        msg.data = gson.toJson(model);

        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return IDataAdapter.CUSTOMER_SAVE_FAIL;
        else {
            return IDataAdapter.CUSTOMER_SAVE_OK;
        }
    }

    public int updateCustomer(CustomerModel model) {
        msg.code = MessageModel.UPDATE_CUSTOMER;
        msg.data = gson.toJson(model);

        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return IDataAdapter.CUSTOMER_SAVE_FAIL;
        else {
            return IDataAdapter.CUSTOMER_SAVE_OK;
        }
    }

    public int deleteCustomer(CustomerModel model) {
        msg.code = MessageModel.DELETE_CUSTOMER;
        msg.data = gson.toJson(model);

        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return IDataAdapter.CUSTOMER_SAVE_FAIL;
        else {
            return IDataAdapter.CUSTOMER_SAVE_OK;
        }
    }

    public int saveOrder(OrderModel model) {
        msg.code = MessageModel.PUT_ORDER;
        msg.data = gson.toJson(model);

        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return IDataAdapter.ORDER_SAVE_FAIL;
        else {
            return IDataAdapter.ORDER_SAVE_OK;
        }
    }

    public int updateOrder(OrderModel model) {
        msg.code = MessageModel.UPDATE_ORDER;
        msg.data = gson.toJson(model);

        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return IDataAdapter.ORDER_SAVE_FAIL;
        else {
            return IDataAdapter.ORDER_SAVE_OK;
        }
    }

    public int deleteOrder(OrderModel model) {
        msg.code = MessageModel.DELETE_ORDER;
        msg.data = gson.toJson(model);

        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return IDataAdapter.ORDER_SAVE_FAIL;
        else {
            return IDataAdapter.ORDER_SAVE_OK;
        }
    }
}
