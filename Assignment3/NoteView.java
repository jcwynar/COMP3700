import javax.swing.*;
import java.awt.*;

public class NoteView extends JFrame {

    public JTextField idText = new JTextField(30);
    public JTextField titleText = new JTextField(30);
    public JTextField bodyText = new JTextField(30);

    public JTextField keyText = new JTextField(30);

    public JTextArea listArea = new JTextArea(10, 20);

    public JButton btnLoad = new JButton("Load");
    public JButton btnSave = new JButton("Save");
    public JButton btnSearch = new JButton("Search");

    public NoteView() {

        this.setTitle("Note View");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        JPanel line1 = new JPanel();
        line1.add(new JLabel("EDIT"));
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("ID:"));
        line2.add(idText);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.add(new JLabel("Title:"));
        line3.add(titleText);
        this.getContentPane().add(line3);

        JPanel line4 = new JPanel();
        line4.add(new JLabel("Body:"));
        line4.add(bodyText);
        this.getContentPane().add(line4);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLoad);
        buttonPanel.add(btnSave);

        this.getContentPane().add(buttonPanel);

        JPanel line5 = new JPanel();
        line5.add(new JLabel("SEARCH"));
        this.getContentPane().add(line5);

        JPanel line6 = new JPanel();
        line6.add(new JLabel("Keyword"));
        line6.add(keyText);
        this.getContentPane().add(line6);

        JPanel line7 = new JPanel();
        line7.add(new JLabel("List:"));
        line7.add(listArea);
        this.getContentPane().add(line7);

        JPanel searchPanel = new JPanel();
        searchPanel.add(btnSearch);

        this.getContentPane().add(searchPanel);
    }

}
