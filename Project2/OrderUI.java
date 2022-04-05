import com.google.gson.Gson;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

public class OrderUI {
    public JFrame view;

    public JButton addButton = new JButton("Add");

    public JButton updateButton = new JButton("Update");

    public JButton deleteButton = new JButton("Delete");

    public JButton cancelButton = new JButton("Cancel");

    public JTextField orderIDText = new JTextField(10);

    public JTextField customerIDText = new JTextField(10);

    public JTextField productIDText = new JTextField(10);

    public JTextField quantityText = new JTextField(10);

    public JLabel priceLabel = new JLabel("Product Price: ");

    public JLabel dateLabel = new JLabel("Order Date: ");

    public JLabel customerNameLabel = new JLabel("Customer Name: ");

    public JLabel productNameLabel = new JLabel("Product Name: ");

    public JLabel costLabel = new JLabel("Cost: $0.00");

    public JLabel taxLabel = new JLabel("Tax: $0.00");

    public JLabel totalCostLabel = new JLabel("Total Cost: $0.00");

    ProductModel product;
    OrderModel order;
    CustomerModel customer;

    // all false as there are no errors yet
    static boolean INVALID_CUSTOMER_ID = false;
    static boolean INVALID_PRODUCT_ID = false;
    static boolean INVALID_QUANTITY = false;
    static boolean NULL_CUSTOMER = false;
    static boolean NULL_PRODUCT = false;
    static boolean NULL_QUANTITY = false;

    public OrderUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Order");
        view.setSize(800, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("OrderID: "));
        line.add(orderIDText);
        line.add(dateLabel);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("CustomerID: "));
        line.add(customerIDText);
        line.add(customerNameLabel);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("ProductID: "));
        line.add(productIDText);
        line.add(productNameLabel);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Quantity: "));
        line.add(quantityText);
        line.add(priceLabel);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(costLabel);
        line.add(taxLabel);
        line.add(totalCostLabel);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(addButton);
        line.add(updateButton);
        line.add(deleteButton);
        line.add(cancelButton);
        view.getContentPane().add(line);

        productIDText.addFocusListener(new ProductIDFocusListener());
        customerIDText.addFocusListener(new CustomerIDFocusListener());
        quantityText.getDocument().addDocumentListener(new QuantityChangeListener());

        addButton.addActionListener(new AddButtonListener());

        updateButton.addActionListener(new UpdateButtonListener());

        deleteButton.addActionListener(new DeleteButtonListener());

        cancelButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        view.dispose();
                    }
                });
    }

    public void run() {
        order = new OrderModel();
        order.mDate = Calendar.getInstance().getTime().toString();
        dateLabel.setText("Order Date: " + order.mDate);
        view.setVisible(true);
    }

    private class ProductIDFocusListener implements FocusListener {
        public void focusGained(FocusEvent focusEvent) {}

        public void focusLost(FocusEvent focusEvent) {
            process();
        }

        private void process() {
            String s = productIDText.getText();

            if (s.length() == 0) {
                productNameLabel.setText("Product Name: [not specified!]");
                return;
            }

            System.out.println("ProductID = " + s);

            try {
                order.mProductID = Integer.parseInt(s);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error: Invalid ProductID", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            product = StoreManager.getInstance().getDataAdapter().loadProduct(order.mProductID);

            if (product == null) {
                JOptionPane.showMessageDialog(null,
                        "Error: No product with id = " + order.mProductID + " in store!", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                productNameLabel.setText("Product Name: ");

                return;
            }

            productNameLabel.setText("Product Name: " + product.mName);
            order.mPrice = product.mPrice;
            priceLabel.setText("Product Price: " + product.mPrice);
        }
    }

    private class CustomerIDFocusListener implements FocusListener {
        public void focusGained(FocusEvent focusEvent) {}

        public void focusLost(FocusEvent focusEvent) {
            process();
        }

        private void process() {
            String s = customerIDText.getText();

            if (s.length() == 0) {
                customerNameLabel.setText("Customer Name: [not specified!]");
                return;
            }

            System.out.println("CustomerID = " + s);

            try {
                order.mCustomerID = Integer.parseInt(s);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error: Invalid CustomerID", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            customer = StoreManager.getInstance().getDataAdapter().loadCustomer(order.mCustomerID);

            if (customer == null) {
                JOptionPane.showMessageDialog(null,
                        "Error: No customer with id = " + order.mCustomerID + " in store!", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                customerNameLabel.setText("Customer Name: ");
                return;
            }

            customerNameLabel.setText("Customer Name: " + customer.mName);
        }
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
            String s = quantityText.getText();

            if (s.length() == 0) {
                return;
            }

            System.out.println("Quantity = " + s);

            try {
                order.mQuantity = Double.parseDouble(s);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error: Please enter an invalid quantity", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (order.mQuantity <= 0) {
                JOptionPane.showMessageDialog(null,
                        "Error: Please enter an invalid quantity", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (order.mQuantity > product.mQuantity) {
                JOptionPane.showMessageDialog(null,
                        "Not enough available products!", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            order.mCost = order.mQuantity * product.mPrice;
            order.mTax = order.mCost * 0.09;
            order.mTotal = order.mCost + order.mTax;

            costLabel.setText("Cost: $" + String.format("%8.2f", order.mCost).trim());
            taxLabel.setText("Tax: $" + String.format("%8.2f", order.mTax).trim());
            totalCostLabel.setText("Total: $" + String.format("%8.2f", order.mTotal).trim());
        }
    }

    class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            String id = orderIDText.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "OrderID cannot be null");
                return;
            }

            try {
                order.mOrderID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "OrderID is invalid");
                return;
            }

            // update date when an order is saved
            order.mDate = Calendar.getInstance().getTime().toString();

            int res = StoreManager.getInstance().getDataAdapter().saveOrder(order);
            if (res == SQLiteDataAdapter.ORDER_SAVE_FAIL)
                JOptionPane.showMessageDialog(null, "Order not added");
            else
                JOptionPane.showMessageDialog(null, "Order added successfully" + order);
        }
    }

    class UpdateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            String id = orderIDText.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "OrderID cannot be null");
                return;
            }

            try {
                order.mOrderID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "OrderID is invalid");
                return;
            }

            order.mDate = Calendar.getInstance().getTime().toString();

            int res = StoreManager.getInstance().getDataAdapter().updateOrder(order);
            if (res == SQLiteDataAdapter.ORDER_SAVE_FAIL)
                JOptionPane.showMessageDialog(null, "Order not updated");
            else
                JOptionPane.showMessageDialog(null, "Order updated successfully" + order);
        }
    }

    class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            String id = orderIDText.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "OrderID cannot be null");
                return;
            }

            try {
                order.mOrderID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "OrderID is invalid");
                return;
            }

            int res = StoreManager.getInstance().getDataAdapter().deleteOrder(order);
            if (res == SQLiteDataAdapter.ORDER_SAVE_FAIL)
                JOptionPane.showMessageDialog(null, "Order not deleted");
            else
                JOptionPane.showMessageDialog(null, "Order deleted successfully" + order);
        }
    }
}
