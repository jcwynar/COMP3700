import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class OrderHistoryUI {
    public JFrame view;

    CustomerModel customer;
    public JTable orderTable;

    public OrderHistoryUI(CustomerModel currentCustomer) {
        this.view = new JFrame();

        customer = currentCustomer;

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Order History for " + customer.mName);
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Order History");

        title.setFont (title.getFont().deriveFont (24.0f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        view.getContentPane().add(title);
    }

    public void run() {
        view.setVisible(true);

        ArrayList<OrderModel> list = StoreManager.getInstance().getDataAdapter().loadOrderHistory(customer.mCustomerID);

        DefaultTableModel tableData = new DefaultTableModel();

        tableData.addColumn("OrderID");
        tableData.addColumn("ProductID");
        tableData.addColumn("Product Name");
        tableData.addColumn("Total");

        for (OrderModel order : list) {
            Object[] row = new Object[tableData.getColumnCount()];
            row[0] = order.mOrderID;
            row[1] = order.mProductID;
            ProductModel product = StoreManager.getInstance().getDataAdapter().loadProduct(order.mOrderID);
            row[2] = product.mName;
            row[3] = order.mTotal;
            tableData.addRow(row);
        }

        orderTable = new JTable(tableData);

        JScrollPane scrollList = new JScrollPane(orderTable);

        view.getContentPane().add(scrollList);
    }
}
