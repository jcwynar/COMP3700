import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class MainUI {
   public JFrame view;

   public JButton addProductButton = new JButton("Add, Update, or Delete Product");

   public JButton addCustomerButton = new JButton("Add, Update, or Delete Customer");

   public JButton addOrderButton = new JButton("Add, Update, or Delete Order");

   public MainUI() {
      this.view = new JFrame();
   
      view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      view.setTitle("Store Management System");
   
      view.setSize(1000, 600);
   
      view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));
   
      JLabel title = new JLabel("Store Management System");
   
      title.setFont(new Font("Verdana", Font.PLAIN, 18));
   
      view.getContentPane().add(title);
   
      JPanel panelButtons = new JPanel(new FlowLayout());
      panelButtons.add(addProductButton);
      panelButtons.add(addCustomerButton);
      panelButtons.add(addOrderButton);
      view.getContentPane().add(panelButtons);
   
      addProductButton.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
               ProductUI ap = new ProductUI();
               ap.run();
            }
         });
   
      addCustomerButton.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
               CustomerUI ac = new CustomerUI();
               ac.run();
            }
         }); 
   
      addOrderButton.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
               OrderUI ao = new OrderUI();
               ao.run();
            }
         });
   }
}
