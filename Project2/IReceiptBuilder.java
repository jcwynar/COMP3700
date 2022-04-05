public interface IReceiptBuilder {
    public void appendHeader(String header);
    public void appendCustomer(CustomerModel customer);
    public void appendProduct(ProductModel product);
    public void appendOrder(OrderModel order);
    public void appendFooter(String footer);
    public String toString();
}
