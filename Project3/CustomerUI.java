import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerUI {
    public CustomerModel customer = null;

    public JFrame view;

    public JButton btnEditInfo = new JButton("Edit Customer Info");
    public JButton btnMakeOrder = new JButton("Make an Order");
    public JButton btnUpdateOrder = new JButton("Update an Order");
    public JButton btnCancelOrder = new JButton("Cancel an Order");
    public JButton btnViewOrders = new JButton("View Order History");
    public JButton btnSearchProduct = new JButton("Search Product");

    public CustomerUI(final UserModel user) {
        customer = StoreManager.getInstance().getDataAdapter().loadCustomerByUser(user.mUserID);

        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System - Customer View");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Customer Store View");

        title.setFont (title.getFont ().deriveFont (24.0f));
        view.getContentPane().add(title);

        JPanel panelUser = new JPanel(new FlowLayout());
        panelUser.add(new JLabel("Name: " + user.mFullname));

        view.getContentPane().add(panelUser);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnMakeOrder);
        panelButtons.add(btnUpdateOrder);
        panelButtons.add(btnViewOrders);
        panelButtons.add(btnCancelOrder);
        panelButtons.add(btnSearchProduct);
        panelButtons.add(btnEditInfo);

        view.getContentPane().add(panelButtons);

        btnEditInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditInfoUI ui = new EditInfoUI(user, customer);
                ui.run();
            }
        });

        btnViewOrders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                OrderHistoryUI ui = new OrderHistoryUI(customer);
                ui.run();
            }
        });

        btnMakeOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AddOrderUI ui = new AddOrderUI(customer);
                ui.run();
            }
        });

        btnUpdateOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                UpdateOrderUI ui = new UpdateOrderUI();
                ui.run();
            }
        });

        btnSearchProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ProductSearchUI ui = new ProductSearchUI();
                ui.view.setVisible(true);
            }
        });

        btnCancelOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CancelOrderUI ui = new CancelOrderUI(customer);
                ui.run();
            }
        });
    }
}
