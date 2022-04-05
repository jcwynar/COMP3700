public class ReceiptBuilder implements IReceiptBuilder {
    StringBuilder sb = new StringBuilder();

    public void appendHeader(String header) {
        sb.append(header).append("\n");
    }

    public void appendCustomer(CustomerModel customer) {
        sb.append("Customer ID: ").append(customer.mCustomerID).append("\n");
        sb.append("Customer Name: ").append(customer.mName).append("\n");
    }

    public void appendProduct(ProductModel product) {

    }

    public void appendOrder(OrderModel order) {

    }

    public void appendFooter(String footer) {
        sb.append(footer).append("\n");
    }
}
