import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteController implements ActionListener {

    NoteView myView;
    DataAccess myDAO;

    public NoteController(NoteView view, DataAccess dao) {
        myView = view;
        myDAO = dao;
        myView.btnLoad.addActionListener(this);
        myView.btnSave.addActionListener(this);
        myView.btnSearch.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myView.btnLoad) {      // button Load is clicked
            loadNoteAndDisplay();
        }

        if (e.getSource() == myView.btnSave) {      // button Save is clicked
            saveNote();
        }

        if (e.getSource() == myView.btnSearch) {
            searchNote();
        }
    }

    private void saveNote() {
        NoteModel note = new NoteModel();

        try {
            int noteID = Integer.parseInt(myView.idText.getText());
            note.NoteID = noteID;
            note.Title = myView.titleText.getText();
            note.Body = myView.bodyText.getText();

            myDAO.saveNote(note);
            JOptionPane.showMessageDialog(null, "Note saved successfully!");
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for numbers!");
            ex.printStackTrace();
        }
    }

    private void loadNoteAndDisplay() {
        try {
            int id = Integer.parseInt(myView.idText.getText());
            NoteModel note = myDAO.loadNote(id);

            if (note == null)
                JOptionPane.showMessageDialog(null, "No existing note with this ID " + id);
            else {

                myView.titleText.setText(note.Title);
                myView.bodyText.setText(note.Body);
            }

        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for NoteID");
            ex.printStackTrace();
        }
    }

    private void searchNote() {
        try {
            String key = myView.keyText.getText();
            NoteListModel nList = myDAO.searchNote(key);

            String results = "";

            for (int i = 0; i < nList.list.size(); i++) {
                int id = nList.list.get(i).NoteID;
                String title = nList.list.get(i).Title;
                String sID = String.valueOf(id);
                String temp = sID + " \"" + title + "\"";
                if (i == 0) {
                    results += temp;
                }
                else {
                    results += ", " + temp;
                }
            }

            if (nList == null) {
                JOptionPane.showMessageDialog(null, "No existing notes with this keyword in their titles " + key);
            }
            else {
                myView.listArea.setText(results);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
