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

   ProductTemplate product;
   OrderTemplate order;
   CustomerTemplate customer;

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
      order = new OrderTemplate();
      order.tDate = Calendar.getInstance().getTime().toString();
      dateLabel.setText("Order Date: " + order.tDate);
      view.setVisible(true);
   }

   private class ProductIDFocusListener implements FocusListener {
      public void focusGained(FocusEvent focusEvent) {
      }
   
      public void focusLost(FocusEvent focusEvent) {
         process();
      }
   
      private void process() {
         String s = productIDText.getText();
      
         if (s.length() == 0) {
            productNameLabel.setText("Product Name: [not specified]");
            NULL_PRODUCT = true;
            return;
         }
         else {
            NULL_PRODUCT = false;
         }
      
         System.out.println("ProductID = " + s);
      
         try {
            order.tProductID = Integer.parseInt(s);
         
         } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                   "Error: Invalid ProductID", "Error Message",
                   JOptionPane.ERROR_MESSAGE);
            INVALID_PRODUCT_ID = true;
            return;
         }
      
         product = StoreManager.getInstance().getDataAdapter().loadProduct(order.tProductID);
      
         if (product == null) {
            JOptionPane.showMessageDialog(null,
                   "Error: No product with id = " + order.tProductID + " in store!", "Error Message",
                   JOptionPane.ERROR_MESSAGE);
            productNameLabel.setText("Product Name: ");
            INVALID_PRODUCT_ID = true;
         
            return;
         }
         else {
            // If user previously entered an invalid ID, but has since changed it to a valid one, they can proceed.
            INVALID_PRODUCT_ID = false;
         }
      
         productNameLabel.setText("Product Name: " + product.tName);
         order.tPrice = product.tPrice;
         priceLabel.setText("Product Price: " + product.tPrice);
      }
   }

   private class CustomerIDFocusListener implements FocusListener {
      public void focusGained(FocusEvent focusEvent) {
      }
   
      public void focusLost(FocusEvent focusEvent) {
         process();
      }
   
      private void process() {
         String s = customerIDText.getText();
      
         if (s.length() == 0) {
            customerNameLabel.setText("Customer Name: [not specified]");
            NULL_CUSTOMER = true;
            return;
         }
         else {
            NULL_CUSTOMER = false;
         }
      
         System.out.println("CustomerID = " + s);
      
         try {
            order.tCustomerID = Integer.parseInt(s);
         } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                   "Error: Invalid CustomerID", "Error Message",
                   JOptionPane.ERROR_MESSAGE);
            INVALID_CUSTOMER_ID = true;
            return;
         }
      
         customer = StoreManager.getInstance().getDataAdapter().loadCustomer(order.tCustomerID);
      
         if (customer == null) {
            JOptionPane.showMessageDialog(null,
                   "Error: No customer with id = " + order.tCustomerID + " in store", "Error Message",
                   JOptionPane.ERROR_MESSAGE);
            customerNameLabel.setText("Customer Name: ");
            INVALID_CUSTOMER_ID = true;
         
            return;
         }
         else {
            INVALID_CUSTOMER_ID = false;
         }
      
         customerNameLabel.setText("Customer Name: " + customer.tName);
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
            NULL_QUANTITY = true;
            return;
         }
         else {
            NULL_QUANTITY = false;
         }
      
         System.out.println("Quantity = " + s);
      
         try {
            order.tQuantity = Double.parseDouble(s);
         
         } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                   "Error: Please enter an invalid quantity", "Error Message",
                   JOptionPane.ERROR_MESSAGE);
            INVALID_QUANTITY = true;
            return;
         }
      
         if (order.tQuantity <= 0) {
            JOptionPane.showMessageDialog(null,
                   "Error: Please enter an valid quantity", "Error Message",
                   JOptionPane.ERROR_MESSAGE);
            INVALID_QUANTITY = true;
            return;
         }
         
         else {
            INVALID_QUANTITY = false;
         }
      
         if (order.tQuantity > product.tQuantity) {
            JOptionPane.showMessageDialog(null,
                   "Not enough available products", "Information",
                   JOptionPane.INFORMATION_MESSAGE);
         }
      
         order.tCost = order.tQuantity * product.tPrice;
         order.tTax = order.tCost * 0.09;
         order.tTotal = order.tCost + order.tTax;
      
         costLabel.setText("Cost: $" + String.format("%8.2f", order.tCost).trim());
         taxLabel.setText("Tax: $" + String.format("%8.2f", order.tTax).trim());
         totalCostLabel.setText("Total: $" + String.format("%8.2f", order.tTotal).trim());
      }
   }

   class AddButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent actionEvent) {
         if (INVALID_CUSTOMER_ID) {
            JOptionPane.showMessageDialog(null, "CustomerID is invalid");
            return;
         }
      
         if (INVALID_PRODUCT_ID) {
            JOptionPane.showMessageDialog(null, "ProductID is invalid");
            return;
         }
      
         if (INVALID_QUANTITY) {
            JOptionPane.showMessageDialog(null, "Quantity is invalid");
            return;
         }
      
         if (NULL_CUSTOMER) {
            JOptionPane.showMessageDialog(null, "Customer D cannot be null");
            return;
         }
      
         if (NULL_PRODUCT) {
            JOptionPane.showMessageDialog(null, "ProductID cannot be null");
            return;
         }
      
         if (NULL_QUANTITY) {
            JOptionPane.showMessageDialog(null, "Quantity Cannot be null");
            return;
         }
      
         String id = orderIDText.getText();
      
         if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "OrderID cannot be null");
            return;
         }
      
         try {
            order.tOrderID = Integer.parseInt(id);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "OrderID is invalid");
            return;
         }
      
         switch (StoreManager.getInstance().getDataAdapter().saveOrder(order)) {
            case SQLiteDataAdapter.ORDER_DUPLICATE_ERROR:
               JOptionPane.showMessageDialog(null, "Order NOT added successfully! Duplicate order ID!");
               break;
            default:
               JOptionPane.showMessageDialog(null, "Order added successfully!" + order);
               ReceiptGenerator receipt = new ReceiptGenerator();
               receipt.appendCustomer(customer);
               receipt.appendProduct(product);
               receipt.appendOrder(order);
               JOptionPane.showMessageDialog(null, receipt.toString());
         }
      }
   }

   class UpdateButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent actionEvent) {
         if (INVALID_CUSTOMER_ID) {
            JOptionPane.showMessageDialog(null, "CustomerID is invalid");
            return;
         }

         if (INVALID_PRODUCT_ID) {
            JOptionPane.showMessageDialog(null, "ProductID is invalid");
            return;
         }

         if (INVALID_QUANTITY) {
            JOptionPane.showMessageDialog(null, "Quantity is invalid");
            return;
         }

         if (NULL_CUSTOMER) {
            JOptionPane.showMessageDialog(null, "Customer D cannot be null");
            return;
         }

         if (NULL_PRODUCT) {
            JOptionPane.showMessageDialog(null, "ProductID cannot be null");
            return;
         }

         if (NULL_QUANTITY) {
            JOptionPane.showMessageDialog(null, "Quantity Cannot be null");
            return;
         }

         String id = orderIDText.getText();

         if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "OrderID cannot be null");
            return;
         }

         try {
            order.tOrderID = Integer.parseInt(id);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "OrderID is invalid");
            return;
         }

         switch (StoreManager.getInstance().getDataAdapter().updateOrder(order)) {
            default:
               JOptionPane.showMessageDialog(null, "Order updated successfully!" + order);
               ReceiptGenerator receipt = new ReceiptGenerator();
               receipt.appendCustomer(customer);
               receipt.appendProduct(product);
               receipt.appendOrder(order);
               JOptionPane.showMessageDialog(null, receipt.toString());
         }
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
            order.tOrderID = Integer.parseInt(id);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "OrderID is invalid");
            return;
         }

         switch (StoreManager.getInstance().getDataAdapter().deleteOrder(order)) {
            default:
               JOptionPane.showMessageDialog(null, "Order deleted successfully!" + order);
               ReceiptGenerator receipt = new ReceiptGenerator();
               receipt.appendCustomer(customer);
               receipt.appendProduct(product);
               receipt.appendOrder(order);
               JOptionPane.showMessageDialog(null, receipt.toString());
         }
      }
   }
}
