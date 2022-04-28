public class MessageModel {
    public static final int GET_PRODUCT = 100;
    public static final int PUT_PRODUCT = 101;
    public static final int UPDATE_PRODUCT = 102;
    public static final int DELETE_PRODUCT = 103;

    public static final int GET_CUSTOMER = 200;
    public static final int PUT_CUSTOMER = 201;
    public static final int UPDATE_CUSTOMER = 202;
    public static final int DELETE_CUSTOMER = 203;
    public static final int GET_CUSTOMER_BY_USER = 205;

    public static final int GET_ORDER = 300;
    public static final int PUT_ORDER = 301;
    public static final int UPDATE_ORDER = 302;
    public static final int DELETE_ORDER = 303;

    public static final int GET_USER = 400;
    public static final int PUT_USER = 401;
    public static final int REGISTER_USER = 402;

    public static final int GET_ORDER_HISTORY = 500;
    public static final int SEARCH_PRODUCT_BY_PRICE = 600;
    public static final int SEARCH_PRODUCT_BY_NAME = 700;
    public static final int GET_USER_ROW_COUNT = 800;
    public static final int GET_CUSTOMER_ROW_COUNT = 801;
    public static final int GET_ORDER_ROW_COUNT = 802;

    public static final int OPERATION_OK = 1000; // server responses!
    public static final int OPERATION_FAILED = 1001;

    public int code;
    public String data;

    public MessageModel() {
        code = 0;
        data = null;
    }

    public MessageModel(int code, String data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() { return code; }
}