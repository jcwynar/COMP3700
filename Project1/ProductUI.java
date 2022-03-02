import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductUI {
   public JFrame view;

   public JButton addButton = new JButton("Add");
   
   public JButton updateButton = new JButton("Update");

   public JButton deleteButton = new JButton("Delete");

   public JButton cancelButton = new JButton("Cancel");

   public JTextField productIDText = new JTextField(20);

   public JTextField nameText = new JTextField(20);

   public JTextField priceText = new JTextField(20);

   public JTextField quantityText = new JTextField(20);

   public ProductUI() {
      this.view = new JFrame();
   
      // gets rid of content panel when user closes panel
      view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   
      // content panel settings
      view.setTitle("Product");
      view.setSize(800, 600);
      view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));
   
      // content pane entry fields
      JPanel field1 = new JPanel(new FlowLayout());
      field1.add(new JLabel("ProductID: "));
      field1.add(productIDText);
      view.getContentPane().add(field1);
   
      JPanel field2 = new JPanel(new FlowLayout());
      field2.add(new JLabel("Name: "));
      field2.add(nameText);
      view.getContentPane().add(field2);
   
      JPanel field3 = new JPanel(new FlowLayout());
      field3.add(new JLabel("Price: "));
      field3.add(priceText);
      view.getContentPane().add(field3);
   
      JPanel field4 = new JPanel(new FlowLayout());
      field4.add(new JLabel("Quantity: "));
      field4.add(quantityText);
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
         ProductTemplate product = new ProductTemplate();
      
         String id = productIDText.getText();
      
         if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
            return;
         }
      
         try {
            product.tProductID = Integer.parseInt(id);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ProductID is invalid!");
            return;
         }
      
         String name = nameText.getText();
         if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, "Product name cannot be empty!");
            return;
         }
         product.tName = name;
      
         String price = priceText.getText();
         try {
            product.tPrice = Double.parseDouble(price);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Price is invalid!");
            return;
         }
      
         String quantity = quantityText.getText();
         try {
            product.tQuantity = Double.parseDouble(quantity);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantity is invalid!");
            return;
         }
      
         switch (StoreManager.getInstance().getDataAdapter().saveProduct(product)) {
            case SQLiteDataAdapter.PRODUCT_DUPLICATE_ERROR:
               JOptionPane.showMessageDialog(null, "Product NOT added successfully! Duplicate product ID!");
               break;
            default:
               JOptionPane.showMessageDialog(null, "Product added successfully!" + product);
         }
      }
   }
      
   class UpdateButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent actionEvent) {
         ProductTemplate product = new ProductTemplate();
         
         String id = productIDText.getText();
         
         if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
            return;
         }
         
         try {
            product.tProductID = Integer.parseInt(id);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ProductID is invalid!");
            return;
         }
         
         String name = nameText.getText();
         if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, "Product name cannot be empty!");
            return;
         }
         product.tName = name;
         
         String price = priceText.getText();
         try {
            product.tPrice = Double.parseDouble(price);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Price is invalid!");
            return;
         }
         
         String quantity = quantityText.getText();
         try {
            product.tQuantity = Double.parseDouble(quantity);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantity is invalid!");
            return;
         }
         
         switch (StoreManager.getInstance().getDataAdapter().updateProduct(product)) {
            default:
               JOptionPane.showMessageDialog(null, "Product updated successfully!" + product);
         }
      }
   }

   class DeleteButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent actionEvent) {
         ProductTemplate product = new ProductTemplate();

         String id = productIDText.getText();

         if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
            return;
         }

         try {
            product.tProductID = Integer.parseInt(id);
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ProductID is invalid!");
            return;
         }

         switch (StoreManager.getInstance().getDataAdapter().deleteProduct(product)) {
            default:
               JOptionPane.showMessageDialog(null, "Product deleted successfully!" + product);
         }
      }
   }
}