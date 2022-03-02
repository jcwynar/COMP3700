public class OrderTemplate {
    public int tOrderID, tProductID, tCustomerID;
    public double tPrice, tQuantity, tCost, tTax, tTotal;
    public String tDate;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(tOrderID).append(",");
        sb.append(tCustomerID).append(",");
        sb.append(tProductID).append(",");
        sb.append(tPrice).append(",");
        sb.append(tQuantity).append(",");
        sb.append(tCost).append(",");
        sb.append(tTax).append(",");
        sb.append(tTotal).append(",");
        sb.append("\"").append(tDate).append("\"").append(")");
        return sb.toString();
    } 
}
