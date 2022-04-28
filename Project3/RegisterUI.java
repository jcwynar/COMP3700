import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterUI {
    public JFrame view;

    public JButton btnRegister = new JButton("Register");

    public JTextField txtUsername = new JTextField(20);
    public JTextField txtPassword = new JTextField(20);
    public JTextField txtName = new JTextField(20);
    public JTextField txtCustName = new JTextField(20);
    public JTextField txtAddress = new JTextField(20);
    public JTextField txtPhone = new JTextField(20);

    public RegisterUI() {
        this.view = new JFrame();
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Registration");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnRegister);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("Username: "));
        line1.add(txtUsername);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Password: "));
        line2.add(txtPassword);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Full Name: "));
        line3.add(txtName);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Add the following only if you are a new customer"));
        view.getContentPane().add(line4);

        JPanel line5 = new JPanel(new FlowLayout());
        line5.add(new JLabel("Name: "));
        line5.add(txtCustName);
        view.getContentPane().add(line5);

        JPanel line6 = new JPanel(new FlowLayout());
        line6.add(new JLabel("Address: "));
        line6.add(txtAddress);
        view.getContentPane().add(line6);

        JPanel line7 = new JPanel(new FlowLayout());
        line7.add(new JLabel("Phone: "));
        line7.add(txtPhone);
        view.getContentPane().add(line7);

        btnRegister.addActionListener(new RegisterButtonListener());
    }

    public void run() {
        view.setVisible(true);
    }

    class RegisterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();

            String uName = txtUsername.getText();
            if (uName.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username is a required field.");
                return;
            }

            user.mUsername = uName;

            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name is a required field.");
                return;
            }

            user.mFullname = name;

            String pWord = txtPassword.getText();
            if (pWord.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password is a required field.");
                return;
            }

            user.mPassword = pWord;

            // set UserType to 0 (customer) since only customers are registering
            user.mUserType = 0;

            // get the row count of "Users" and add 1 to get new UserID
            ArrayList<Integer> countList = StoreManager.getInstance().getDataAdapter().getUserRowCount();
            int realCount = countList.get(0);
            realCount = realCount + 1;
            user.mUserID = realCount;

            CustomerModel customer = new CustomerModel();

            String fName = txtCustName.getText();
            if (fName.length() == 0) {
                JOptionPane.showMessageDialog(null, "Please enter your full name");
                return;
            }

            customer.mName = fName;

            String addr = txtAddress.getText();
            if (addr.length() == 0) {
                JOptionPane.showMessageDialog(null, "Please enter your address");
                return;
            }

            customer.mAddress = addr;

            String phone = txtPhone.getText();
            if (phone.length() == 0) {
                JOptionPane.showMessageDialog(null, "Please enter your phone number");
                return;
            }

            customer.mPhone = phone;

            customer.mUserID = realCount;

            // get row count of "Customers" then add 1 to get new CustomerID
            ArrayList<Integer> custCountList = StoreManager.getInstance().getDataAdapter().getCustomerRowCount();
            int realCustCount = custCountList.get(0);
            realCustCount = realCustCount + 1;
            customer.mCustomerID = realCustCount;

            int res1 = StoreManager.getInstance().getDataAdapter().registerUser(user);

            int res2 = StoreManager.getInstance().getDataAdapter().saveCustomer(customer);

            if (res1 == IDataAdapter.USER_SAVE_FAILED) {
                JOptionPane.showMessageDialog(null, "Registration failed; please try again later");
            }
            else if (res2 == IDataAdapter.CUSTOMER_SAVE_FAIL) {
                JOptionPane.showMessageDialog(null, "Registration failed; please try again later");
            }
            else {
                JOptionPane.showMessageDialog(null, "Registration complete! Please navigate back to login");
            }
        }
    }
}
