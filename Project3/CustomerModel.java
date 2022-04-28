public class CustomerModel {
    public int mCustomerID, mUserID;
    public String mName, mAddress, mPhone;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(mCustomerID).append(",");
        sb.append("\"").append(mName).append("\"").append(",");
        sb.append("\"").append(mAddress).append("\"").append(",");
        sb.append("\"").append(mPhone).append("\"").append(",");
        sb.append(mUserID).append(")");
        return sb.toString();
    }
}
