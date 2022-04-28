public class UserModel {
    public static final int CUSTOMER = 0;
    public static final int MANAGER = 1;

    public String mUsername, mPassword, mFullname;
    public int mUserType;
    public int mUserID;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append("\"").append(mUsername).append("\"").append(",");
        sb.append("\"").append(mPassword).append("\"").append(",");
        sb.append("\"").append(mFullname).append("\"").append(",");
        sb.append(mUserType).append(",");
        sb.append(mUserID).append(")");
        return sb.toString();
    }
}
