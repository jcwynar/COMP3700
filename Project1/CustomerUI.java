import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerUI {
   public JFrame view;

   public JButton addButton = new JButton("Add");
   
   public JButton updateButton = new JButton("Update");

   public JButton deleteButton = new JButton("Delete");

   public JButton cancelButton = new JButton("Cancel");

   public JTextField customerIDText = new JTextField(20);

   public JTextField nameText = new JTextField(20);

   public JTextField phoneText = new JTextField(20);

   public JTextField addressText = new JTextField(20);

   public CustomerUI() {
      this.view = new JFrame();
   
      // gets rid of content panel when user closes panel
      view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   
      // content panel settings
      view.setTitle("Customer");
      view.setSize(800, 600);
      view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));
   
      // content pane entry fields
      JPanel field1 = new JPanel(new FlowLayout());
      field1.add(new JLabel("CustomerID: "));
      field1.add(customerIDText);
      view.getContentPane().add(field1);
   
      JPanel field2 = new JPanel(new FlowLayout());
      field2.add(new JLabel("Name: "));
      field2.add(nameText);
      view.getContentPane().add(field2);
   
      JPanel field3 = new JPanel(new FlowLayout());
      field3.add(new JLabel("Address: "));
      field3.add(addressText);
      view.getContentPane().add(field3);
   
      JPanel field4 = new JPanel(new FlowLayout());
      field4.add(new JLabel("Phone: "));
      field4.add(phoneText);
      view.getContentPane().add(field4);
   
      JPanel buttons = new JPanel(new FlowLayout());
      buttons.add(addButton);
      buttons.add(updateButton);
      buttons.add(deleteButton);
      buttons.add(cancelButton);
      view.getContentPane().add(buttons);
   
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

   public void run()  {
      view.setVisible(true);
   }

   class AddButtonListener implements ActionListener   {
      public void actionPerformed(ActionEvent actionEvent) {
         CustomerTemplate customer = new CustomerTemplate();
      
         String id = customerIDText.getText();
      
         if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
            return;
         }
      
         try {
            customer.tCustomerID = Integer.parseInt(id);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
            return;
         }
      
         String name = nameText.getText();
         if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, "Customer name cannot be empty!");
            return;
         }
         customer.tName = name;
      
         String phone = phoneText.getText();
         if (phone.length() == 0) {
            JOptionPane.showMessageDialog(null, "Customer phone cannot be empty!");
            return;
         }
         customer.tPhone = phone;
      
         String address = addressText.getText();
         if (address.length() == 0) {
            JOptionPane.showMessageDialog(null, "Customer address cannot be empty!");
         }
         customer.tAddress = address;
      
         switch (StoreManager.getInstance().getDataAdapter().saveCustomer(customer)) {
            case SQLiteDataAdapter.CUSTOMER_DUPLICATE_ERROR:
               JOptionPane.showMessageDialog(null, "Customer NOT added successfully! Duplicate customer ID!");
               break;
            default:
               JOptionPane.showMessageDialog(null, "Customer added successfully!" + customer);
         }
      }
   }

   class UpdateButtonListener implements ActionListener   {
      public void actionPerformed(ActionEvent actionEvent) {
         CustomerTemplate customer = new CustomerTemplate();

         String id = customerIDText.getText();

         if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
            return;
         }

         try {
            customer.tCustomerID = Integer.parseInt(id);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
            return;
         }

         String name = nameText.getText();
         if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, "Customer name cannot be empty!");
            return;
         }
         customer.tName = name;

         String phone = phoneText.getText();
         if (phone.length() == 0) {
            JOptionPane.showMessageDialog(null, "Customer phone cannot be empty!");
            return;
         }
         customer.tPhone = phone;

         String address = addressText.getText();
         if (address.length() == 0) {
            JOptionPane.showMessageDialog(null, "Customer address cannot be empty!");
         }
         customer.tAddress = address;

         switch (StoreManager.getInstance().getDataAdapter().updateCustomer(customer)) {
            default:
               JOptionPane.showMessageDialog(null, "Customer updated successfully!" + customer);
         }
      }
   }

   class DeleteButtonListener implements ActionListener   {
      public void actionPerformed(ActionEvent actionEvent) {
         CustomerTemplate customer = new CustomerTemplate();

         String id = customerIDText.getText();

         if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
            return;
         }

         try {
            customer.tCustomerID = Integer.parseInt(id);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
            return;
         }

         switch (StoreManager.getInstance().getDataAdapter().deleteCustomer(customer)) {
            default:
               JOptionPane.showMessageDialog(null, "Customer deleted successfully!" + customer);
         }
      }
   }
}