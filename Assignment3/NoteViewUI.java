import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteViewUI extends JFrame {
    public JFrame view;

    public JButton downloadButton = new JButton("Download");

    public JButton uploadButton = new JButton("Upload");

    public JTextField idText = new JTextField(20);

    public JTextField titleText = new JTextField(20);

    public JTextField bodyText = new JTextField(20);

    public NoteViewUI() {
        this.view = new JFrame();

        // gets rid of content panel when user closes panel
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // content panel settings
        view.setTitle("Note View");
        view.setSize(800, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        // content pane entry fields
        JPanel field1 = new JPanel(new FlowLayout());
        field1.add(new JLabel("NoteID: "));
        field1.add(idText);
        view.getContentPane().add(field1);

        JPanel field2 = new JPanel(new FlowLayout());
        field2.add(new JLabel("Title: "));
        field2.add(titleText);
        view.getContentPane().add(field2);

        JPanel field3 = new JPanel(new FlowLayout());
        field3.add(new JLabel("Body: "));
        field3.add(bodyText);
        view.getContentPane().add(field3);

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(uploadButton);
        buttons.add(downloadButton);
        view.getContentPane().add(buttons);

        uploadButton.addActionListener(new UploadButtonListener());

        downloadButton.addActionListener(new DownloadButtonListener());
    }

    public void run()  {
        view.setVisible(true);
    }

    class UploadButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            NoteModel note = new NoteModel();

            String id = idText.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "NoteID cannot be null!");
                return;
            }

            try {
                note.id = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid format for NoteID");
                return;
            }

            String title = titleText.getText();
            if (title.length() == 0) {
                JOptionPane.showMessageDialog(null, "Note title cannot be empty!");
                return;
            }
            note.title = title;

            String body = bodyText.getText();
            if (body.length() == 0) {
                JOptionPane.showMessageDialog(null, "Note body cannot be empty!");
                return;
            }
            note.body = body;

            switch (NoteEditor.getInstance().getDataAccess().saveNote(note)) {
                default -> JOptionPane.showMessageDialog(null, "Note saved successfully!" + note);
            }
        }
    }

    class DownloadButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            NoteModel note = new NoteModel();

            String id = idText.getText();
            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "NoteID cannot be null!");
                return;
            }

            try {
                note.id = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid format for NoteID");
                return;
            }

            int newID = Integer.parseInt(id);

            NoteEditor.getInstance().getDataAccess().loadNote(newID);

            JOptionPane.showMessageDialog(null, "Product loaded successfully!" + note);
        }
    }
}
