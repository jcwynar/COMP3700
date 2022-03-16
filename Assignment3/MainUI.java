import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class MainUI {
    public JFrame view;

    public JButton upDownButton = new JButton("Upload or Download Notes");

    public JButton searchButton = new JButton("Search Notes");

    public MainUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        view.setTitle("Note Editor");

        view.setSize(1000, 600);

        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Note Editor");

        title.setFont(new Font("Verdana", Font.PLAIN, 18));

        view.getContentPane().add(title);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(upDownButton);
        panelButtons.add(searchButton);
        view.getContentPane().add(panelButtons);

        upDownButton.addActionListener(
                actionEvent -> {
                    NoteViewUI nv = new NoteViewUI();
                    nv.run();
                });

        searchButton.addActionListener(
                actionEvent -> {
                    SearchViewUI sv = new SearchViewUI();
                    sv.run();
                });
    }
}