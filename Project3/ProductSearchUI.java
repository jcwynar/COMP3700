import com.google.gson.Gson;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchUI {
    public JFrame view;

    public JTextField  txtSearchTerm = new JTextField(20);

    public JButton btnSearch = new JButton("Search");

    public JTable productTable = new JTable();

    public ProductSearchUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Search Product");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("Enter Product Name or Price:"));
        line1.add(txtSearchTerm);
        line1.add(btnSearch);
        view.getContentPane().add(line1);

        JLabel title = new JLabel("Search Results");

        title.setFont(title.getFont().deriveFont(24.0f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        view.getContentPane().add(title);

        btnSearch.addActionListener(new SearchButtonListener());
    }

    class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            boolean isDub = false;
            double searchDub = -1;

            try {
                isDub = true;
                searchDub = Double.parseDouble(txtSearchTerm.getText());
            } catch (NumberFormatException e) {
                isDub = false;
            }

            ArrayList<ProductModel> list;

            if (isDub) {
                list = StoreManager.getInstance().getDataAdapter().searchProductByPrice(searchDub);
            }
            else {
                list = StoreManager.getInstance().getDataAdapter().searchProductByName(txtSearchTerm.getText());
            }

            DefaultTableModel tableData = new DefaultTableModel();

            tableData.addColumn("ProductID");
            tableData.addColumn("Product Name");
            tableData.addColumn("Price");
            tableData.addColumn("Quantity");

            for (ProductModel p : list) {
                Object[] row = new Object[tableData.getColumnCount()];
                row[0] = p.mProductID;
                row[1] = p.mName;
                row[2] = p.mPrice;
                row[3] = p.mQuantity;
                tableData.addRow(row);
            }

            productTable = new JTable(tableData);

            JScrollPane scrollableList = new JScrollPane(productTable);

            view.getContentPane().add(scrollableList);
        }
    }
}
