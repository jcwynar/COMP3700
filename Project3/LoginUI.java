import com.google.gson.Gson;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class LoginUI {
    public JFrame view;

    public JButton btnLogin = new JButton("Login");

    public JButton btnRegister = new JButton("Register as a new User");

    public JTextField txtUsername = new JTextField(20);
    public JTextField txtPassword = new JPasswordField(20);

    Socket link;
    Scanner input;
    PrintWriter output;

    int accessToken;

    public LoginUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Login");
        view.setSize(600, 400);

        Container pane = view.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel();
        line.add(new JLabel("Username"));
        line.add(txtUsername);
        pane.add(line);

        line = new JPanel();
        line.add(new JLabel("Password"));
        line.add(txtPassword);
        pane.add(line);

        pane.add(btnLogin);
        pane.add(btnRegister);

        btnLogin.addActionListener(new LoginActionListener());

        btnRegister.addActionListener(new RegisterActionListener());
    }

    public void run() {
        LoginUI ui = new LoginUI();
        ui.view.setVisible(true);
    }

    private class LoginActionListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();
            user.mUsername = txtUsername.getText();
            user.mPassword = txtPassword.getText();

            if (txtUsername.getText().length() == 0 || txtPassword.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Username or password cannot be null");
                return;
            }

            UserModel usernameResult = StoreManager.getInstance().getDataAdapter().loadUser(txtUsername.getText());

            if (usernameResult == null) {
                JOptionPane.showMessageDialog(null, "User does not exist!");
                return;
            }
            else if (!(usernameResult.mPassword.equals(txtPassword.getText()))) {
                JOptionPane.showMessageDialog(null, "Password is incorrect!");
                return;
            }
            else {
                user = usernameResult;
                //Close login window. No longer needed
                view.setVisible(false);
            }

            // now let's load UI, since our user has been logged in
            System.out.println("User = " + user);
            if (user.mUserType == UserModel.MANAGER) {
                ManagerUI ui = new ManagerUI();
                System.out.println("MainUI = " + ui);
                ui.view.setVisible(true);
            }
            else if (user.mUserType == UserModel.CUSTOMER) {
                CustomerUI ui = new CustomerUI(user);
                System.out.println("MainUI = " + ui);
                ui.view.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "Usertype NOT supported!");
                view.setVisible(true);
            }
        }
    }

    private class RegisterActionListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            RegisterUI ui = new RegisterUI();
            System.out.println("MainUI = " + ui);
            ui.view.setVisible(true);
        }
    }
}
