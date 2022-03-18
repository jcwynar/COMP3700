public interface DataAccess {
    void connect();

    void saveNote(NoteModel product);

    NoteModel loadNote(int noteID);

    NoteListModel searchNote(String keyword);
}
