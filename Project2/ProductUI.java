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

    class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            ProductModel product = new ProductModel();
            String id = productIDText.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
                return;
            }

            try {
                product.mProductID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ProductID is invalid!");
                return;
            }

            String name = nameText.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Product name cannot be empty!");
                return;
            }

            product.mName = name;

            String price = priceText.getText();
            try {
                product.mPrice = Double.parseDouble(price);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Price is invalid!");
                return;
            }

            String quantity = quantityText.getText();
            try {
                product.mQuantity = Double.parseDouble(quantity);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantity is invalid!");
                return;
            }

            int res = StoreManager.getInstance().getDataAdapter().saveProduct(product);

            if (res == IDataAdapter.PRODUCT_SAVE_FAIL)
                JOptionPane.showMessageDialog(null, "Product was NOT saved");
            else
                JOptionPane.showMessageDialog(null, "Product saved!");
        }
    }

    class UpdateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            ProductModel product = new ProductModel();
            String id = productIDText.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
                return;
            }

            try {
                product.mProductID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ProductID is invalid!");
                return;
            }

            String name = nameText.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Product name cannot be empty!");
                return;
            }
            product.mName = name;

            String price = priceText.getText();
            try {
                product.mPrice = Double.parseDouble(price);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Price is invalid!");
                return;
            }

            String quantity = quantityText.getText();
            try {
                product.mQuantity = Double.parseDouble(quantity);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantity is invalid!");
                return;
            }

            int res = StoreManager.getInstance().getDataAdapter().updateProduct(product);

            if (res == IDataAdapter.PRODUCT_SAVE_FAIL)
                JOptionPane.showMessageDialog(null, "Product update failed");
            else
                JOptionPane.showMessageDialog(null, "Product updated successfully!");
        }
    }

    class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            ProductModel product = new ProductModel();

            String id = productIDText.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
                return;
            }

            try {
                product.mProductID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ProductID is invalid!");
                return;
            }

            int res = StoreManager.getInstance().getDataAdapter().deleteProduct(product);

            if (res == IDataAdapter.PRODUCT_SAVE_FAIL)
                JOptionPane.showMessageDialog(null, "Product deletion failed");
            else
                JOptionPane.showMessageDialog(null, "Product deleted successfully");
        }
    }
}
