public class ReceiptGenerator implements IReceiptGenerator {
    StringBuilder sb = new StringBuilder();

    public ReceiptGenerator() {
        System.out.println("Receipt Builder Initialized");
    }

    public void appendHeader(String header) {
        sb.append(header).append("\n");
    }

    public void appendCustomer(CustomerTemplate customer) {
        sb.append("Customer ID: ").append(customer.tCustomerID).append("\n");
        sb.append("Customer Name: ").append(customer.tName).append("\n");
        sb.append("**********************************************\n");
    }

    public void appendProduct(ProductTemplate product) {
        sb.append("Product ID: ").append(product.tProductID).append("\n");
        sb.append("Product Name: ").append(product.tName).append("\n");
        sb.append("**********************************************\n");
    }

    public void appendOrder(OrderTemplate order) {
        sb.append("Order ID: ").append(order.tOrderID).append("\n");
        sb.append("Price Per: ").append(order.tPrice).append("\n");
        sb.append("Quantity: ").append(order.tQuantity).append("\n");
        sb.append("Subtotal: ").append(order.tCost).append("\n");
        sb.append("Tax: ").append(order.tTax).append("\n");
        sb.append("Total Price: ").append(order.tTotal).append("\n");
        sb.append("Order Date: ").append(order.tDate).append("\n");
        sb.append("**********************************************\n");
    }

    public void appendFooter(String footer) {
        sb.append(footer).append("\n");
    }

    public String toString() {
        return sb.toString();
    }
}
