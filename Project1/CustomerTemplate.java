public class CustomerTemplate {
   public int tCustomerID;
   public String tName, tAddress, tPhone;
   
   public String toString() {
       StringBuilder sb = new StringBuilder("(");
       sb.append(tCustomerID).append(",");
       sb.append("\"").append(tName).append("\"").append(",");
       sb.append("\"").append(tAddress).append("\"").append(",");
       sb.append("\"").append(tPhone).append("\"").append(")");
       return sb.toString();
   }
}
