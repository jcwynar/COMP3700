import com.google.gson.Gson;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Calendar;

public class AddOrderUI {
    public JFrame view;

    public JButton btnSave = new JButton("Add Order");

    public JTextField txtProductName = new JTextField(10);
    public JTextField txtQuantity = new JTextField(10);

    public JLabel labPrice = new JLabel("Product Price: ");
    public JLabel labDate = new JLabel("Order Date: ");
    public JLabel labCustomerName = new JLabel("Customer Name: ");
    public JLabel labCost = new JLabel("Cost: $0.00");
    public JLabel labTax = new JLabel("Tax: $0.00");
    public JLabel labTotal = new JLabel("Total Cost: $0.00");

    ProductModel product;
    CustomerModel customer;
    OrderModel order;

    public AddOrderUI(CustomerModel currentCustomer) {
        this.view = new JFrame();
        customer = currentCustomer;

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add order for " + customer.mName);
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("Product Name: "));
        line.add(txtProductName);
        line.add(labDate);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Quantity: "));
        line.add(txtQuantity);
        line.add(labPrice);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(labCost);
        line.add(labTax);
        line.add(labTotal);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnSave);
        view.getContentPane().add(line);

        txtQuantity.getDocument().addDocumentListener(new QuantityChangeListener());

        btnSave.addActionListener(new SaveButtonListener());
    }

    public void run() {
        order = new OrderModel();
        order.mDate = Calendar.getInstance().getTime().toString();
        labDate.setText("Date of Order: " + order.mDate);
        view.setVisible(true);
    }

    private class QuantityChangeListener implements DocumentListener {
        public void changedUpdate(DocumentEvent e) {
            process();
        }

        public void removeUpdate(DocumentEvent e) {
            process();
        }

        public void insertUpdate(DocumentEvent e) {
            process();
        }

        private void process() {
            // load the product we need info from
            ArrayList<ProductModel> productList = StoreManager.getInstance().getDataAdapter().searchProductByName(txtProductName.getText());
            ProductModel loadedProduct = productList.get(0);

            labPrice.setText("Product Price: " + loadedProduct.mPrice);

            String s = txtQuantity.getText();

            if (s.length() == 0) {
                return;
            }

            System.out.println("Quantity = " + s);

            try {
                order.mQuantity = Double.parseDouble(s);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error: Please enter a valid quantity", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (order.mQuantity <= 0) {
                JOptionPane.showMessageDialog(null,
                        "Error: Please enter a valid quantity", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (order.mQuantity > loadedProduct.mQuantity) {
                JOptionPane.showMessageDialog(null,
                        "Not enough available products!", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            order.mCost = order.mQuantity * loadedProduct.mPrice;
            order.mTax = order.mCost * 0.09;
            order.mTotal = order.mCost + order.mTax;

            labCost.setText("Cost: $" + String.format("%8.2f", order.mCost).trim());
            labTax.setText("Tax: $" + String.format("%8.2f", order.mTax).trim());
            labTotal.setText("Total: $" + String.format("%8.2f", order.mTotal).trim());
        }
    }

    class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            // grab amount of records in Orders, then set our OrderID
            ArrayList<Integer> orderCountList = StoreManager.getInstance().getDataAdapter().getOrderRowCount();
            int realOrderCount = orderCountList.get(0);
            realOrderCount = realOrderCount + 1;
            order.mOrderID = realOrderCount;

            // set ProductID
            // load product first
            ArrayList<ProductModel> productList = StoreManager.getInstance().getDataAdapter().searchProductByName(txtProductName.getText());
            ProductModel product = productList.get(0);
            order.mProductID = product.mProductID;

            // set CustomerID
            order.mCustomerID = customer.mCustomerID;

            // update date each time order is saved
            order.mDate = Calendar.getInstance().getTime().toString();

            int res = StoreManager.getInstance().getDataAdapter().saveOrder(order);

            if (res == SQLiteDataAdapter.ORDER_SAVE_FAIL) {
                JOptionPane.showMessageDialog(null, "Order not saved; please try again.");
            }
            else {
                JOptionPane.showMessageDialog(null, "Order saved successfully! " +
                        "Your Order ID is " + order.mOrderID + ". Please keep a record of this number " +
                        "in case you wish to cancel your order.");
            }
        }
    }
}
