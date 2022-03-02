public interface IReceiptGenerator {
    public void appendHeader(String header);
    public void appendCustomer(CustomerTemplate customer);
    public void appendProduct(ProductTemplate product);
    public void appendOrder(OrderTemplate order);
    public void appendFooter(String footer);
    public String toString();
}
