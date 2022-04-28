import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

public class CancelOrderUI {
    public JFrame view;

    public JButton btnDelete = new JButton("Delete");

    public JTextField txtOrderID = new JTextField(10);

    OrderModel order;
    CustomerModel customer;

    public CancelOrderUI(CustomerModel currentCustomer) {
        this.view = new JFrame();
        customer = currentCustomer;

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.setTitle("Cancel Order");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("OrderID "));
        line.add(txtOrderID);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnDelete);
        view.getContentPane().add(line);

        btnDelete.addActionListener(new DeleteButtonListener());
    }

    public void run() {
        view.setVisible(true);
    }

    class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            String id = txtOrderID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "OrderID cannot be null");
                return;
            }

            order = StoreManager.getInstance().getDataAdapter().loadOrder(Integer.parseInt(id));

            if (order.mCustomerID != customer.mCustomerID) {
                JOptionPane.showMessageDialog(null, "This order does not belong to you. " +
                        "Please enter a valid Order ID.");
            }

            int res = StoreManager.getInstance().getDataAdapter().deleteOrder(order.mOrderID);
            if (res == SQLiteDataAdapter.ORDER_SAVE_FAIL) {
                JOptionPane.showMessageDialog(null, "Order not deleted");
            }
            else {
                JOptionPane.showMessageDialog(null, "Order deleted successfully" + order);
            }
        }
    }
}
