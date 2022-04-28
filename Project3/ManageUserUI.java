import com.google.gson.Gson;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageUserUI {
    public JFrame view;

    public JButton btnLoad = new JButton("Load User");
    public JButton btnSave = new JButton("Save User");

    public JTextField txtUsername = new JTextField(20);
    public JTextField txtPassword = new JTextField(20);
    public JTextField txtName = new JTextField(20);
    public JTextField txtUserID = new JTextField(20);

    String[] userStrings = {"Customer", "Manager", "Admin" };

    // create combo box with all user types
    JComboBox userTypes = new JComboBox(userStrings);

    public ManageUserUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Manage User Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnLoad);
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("Username "));
        line1.add(txtUsername);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Password "));
        line2.add(txtPassword);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Full Name "));
        line3.add(txtName);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("User Type "));
        line4.add(userTypes);
        view.getContentPane().add(line4);

        JPanel line5 = new JPanel(new FlowLayout());
        line5.add(new JLabel("UserID "));
        line5.add(txtUserID);
        view.getContentPane().add(line5);

        btnLoad.addActionListener(new LoadButtonListener());

        btnSave.addActionListener(new SaveButtonListener());
    }

    public void run() {
        view.setVisible(true);
    }

    class LoadButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            Gson gson = new Gson();
            UserModel user = new UserModel();
            String uName = txtUsername.getText();

            if (uName.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username cannot be null!");
                return;
            }

            try {
                user.mUsername = uName;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Username is invalid!");
                return;
            }

            user = StoreManager.getInstance().getDataAdapter().loadUser(user.mUsername);
            if (user == null) {
                JOptionPane.showMessageDialog(null, "User does not exist");
            } else {
                txtName.setText(user.mFullname);
                txtPassword.setText(user.mPassword);
                txtUserID.setText(Integer.toString(user.mUserID));
                userTypes.setSelectedIndex(user.mUserType);
            }
        }
    }

    class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();
            Gson gson = new Gson();
            String uName = txtUsername.getText();

            if (uName.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username cannot be null!");
                return;
            }

            user.mUsername = uName;

            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "User's Full Name cannot be empty!");
                return;
            }

            user.mFullname = name;

            String pWord = txtPassword.getText();
            if (pWord.length() == 0) {
                JOptionPane.showMessageDialog(null, "User password cannot be empty!");
                return;
            }
            user.mPassword = pWord;

            String id = txtUserID.getText();
            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "UserID cannot be null!");
                return;
            }

            try {
                user.mUserID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "UserID is invalid!");
                return;
            }

            int uType = userTypes.getSelectedIndex();
            user.mUserType = uType;

            int  res = StoreManager.getInstance().getDataAdapter().saveUser(user);

            if (res == IDataAdapter.USER_SAVE_FAILED) {
                JOptionPane.showMessageDialog(null, "User was not saved");
            }
            else {
                JOptionPane.showMessageDialog(null, "User saved successfully");
            }
        }
    }
}
