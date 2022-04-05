import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {
    public JFrame view;

    public JButton btnProduct = new JButton("Manage Products");
    public JButton btnCustomer = new JButton("Manage Customers");
    public JButton btnOrder = new JButton("Manage Orders");

    public MainUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Store Management System");

        title.setFont(title.getFont().deriveFont(24.0f));
        view.getContentPane().add(title);

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(btnProduct);
        buttons.add(btnCustomer);
        buttons.add(btnOrder);

        view.getContentPane().add(buttons);

        btnOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrderUI or = new OrderUI();
                or.run();
            }
        });

        btnProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductUI pr = new ProductUI();
                pr.run();
            }
        });

        btnCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomerUI cs = new CustomerUI();
                cs.run();
            }
        });
    }
}
