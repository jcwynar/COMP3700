import com.google.gson.Gson;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditInfoUI {
    public JFrame view;

    UserModel user;
    CustomerModel customer;

    public JButton btnSave = new JButton("Save Changes");

    public JLabel username = new JLabel();
    public JTextField txtPassword = new JTextField(20);
    public JTextField txtName = new JTextField(20);
    public JTextField txtAddress = new JTextField(20);
    public JTextField txtPhone = new JTextField(20);

    public EditInfoUI(UserModel currentUser, CustomerModel currentCustomer) {
        this.view = new JFrame();
        user = currentUser;
        customer = currentCustomer;
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Edit Personal Info for " + user.mUsername);
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("Username: "));
        username.setText(user.mUsername);
        line1.add(username);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Password: "));
        txtPassword.setText(user.mPassword);
        line2.add(txtPassword);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Full Name: "));
        txtName.setText(user.mFullname);
        line3.add(txtName);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Address: "));
        txtAddress.setText(customer.mAddress);
        line4.add(txtAddress);
        view.getContentPane().add(line4);

        JPanel line5 = new JPanel(new FlowLayout());
        line5.add(new JLabel("Phone Number: "));
        txtPhone.setText(customer.mPhone);
        line5.add(txtPhone);
        view.getContentPane().add(line5);

        btnSave.addActionListener(new SaveButtonListener());
    }

    public void run() {
        view.setVisible(true);
    }

    class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            String pWord = txtPassword.getText();
            if (pWord.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password field is required");
                return;
            }
            user.mPassword = pWord;

            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name field is required");
                return;
            }
            user.mFullname = name;
            customer.mName = name;

            String address = txtAddress.getText();
            if (address.length() == 0) {
                JOptionPane.showMessageDialog(null, "Address field is required");
                return;
            }
            customer.mAddress = address;

            String phone = txtPhone.getText();
            if (phone.length() == 0) {
                JOptionPane.showMessageDialog(null, "Phone field is required");
                return;
            }
            customer.mPhone = phone;

            // save user
            int res1 = StoreManager.getInstance().getDataAdapter().saveUser(user);
            // save customer
            int res2 = StoreManager.getInstance().getDataAdapter().updateCustomer(customer);

            if (res1 == IDataAdapter.USER_SAVE_FAILED) {
                JOptionPane.showMessageDialog(null, "User was not saved");
            }
            else if (res2 == IDataAdapter.CUSTOMER_SAVE_FAIL) {
                JOptionPane.showMessageDialog(null, "Customer was not saved");
            }
            else {
                JOptionPane.showMessageDialog(null, "Info saved successfully!");
            }
        }
    }
}
