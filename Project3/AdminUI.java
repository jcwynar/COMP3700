import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI {
    public JFrame view;

    public JButton btnManageUser = new JButton("Manage Users");

    public AdminUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System - Admin View");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Administrator System");

        title.setFont(title.getFont().deriveFont(24.0f));

        view.getContentPane().add(title);

        JPanel panelButtons = new JPanel(new FlowLayout());

        panelButtons.add(btnManageUser);

        view.getContentPane().add(panelButtons);

        btnManageUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ManageUserUI ui = new ManageUserUI();
                ui.run();
            }
        });
    }
}
