public class ProductTemplate {
   // have to use different naming convention for object vs UI 
   public int tProductID;
   public String tName;
   public double tPrice, tQuantity;

   public String toString() {
      StringBuilder sb = new StringBuilder("(");
      sb.append(tProductID).append(",");
      sb.append("\"").append(tName).append("\"").append(",");
      sb.append(tPrice).append(",");
      sb.append(tQuantity).append(")");
      return sb.toString();
   }
}
