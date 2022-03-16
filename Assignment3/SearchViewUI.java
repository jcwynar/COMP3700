import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchViewUI {
    public JFrame view;

    public JTextField keyText = new JTextField(20);

    public JLabel resultsLabel = new JLabel("Results: ");

    public JTextArea resultsArea = new JTextArea();

    NoteListModel nList;

    public SearchViewUI() {
        this.view = new JFrame();

        // gets rid of content panel when user closes panel
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // content panel settings
        view.setTitle("Search View");
        view.setSize(800, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        // content pane entry fields
        JPanel field1 = new JPanel(new FlowLayout());
        field1.add(new JLabel("Keyword: "));
        field1.add(keyText);
        view.getContentPane().add(field1);

        JPanel field2 = new JPanel(new FlowLayout());
        resultsArea.setBounds(10,30,200,200);
        field2.add(resultsLabel);
        field2.add(resultsArea);
        view.getContentPane().add(field2);

        keyText.addFocusListener(new KeywordFocusListener());
    }

    public void run()  {
        view.setVisible(true);
    }

    private class KeywordFocusListener implements FocusListener {
        public void focusGained(FocusEvent focusEvent) {
        }

        public void focusLost(FocusEvent focusEvent) {
            process();
        }

        private void process() {
            String s = keyText.getText();

            if (s.length() == 0) {
                resultsArea.setText("[no keyword]");
                return;
            }

            nList = NoteEditor.getInstance().getDataAccess().searchNote(s);

            if (nList == null) {
                JOptionPane.showMessageDialog(null, "No notes with that keyword. Please try again.");
                return;
            }

            String results = "";

            for (int i = 0; i < nList.list.size(); i++) {
                int nID = nList.list.get(i).id;
                String nTitle = nList.list.get(i).title;
                String sID = String.valueOf(nID);
                String tempResult = sID + " \""  + nTitle + "\"";
                if (i == 0) {
                    results += tempResult;
                }
                else {
                    results += ", " + tempResult;
                }
            }
            resultsArea.setText(results);
        }
    }
}
