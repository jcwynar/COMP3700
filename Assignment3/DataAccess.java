public interface DataAccess {
    public static final int CONNECTION_OPEN_OK = 100;
    public static final int CONNECTION_OPEN_FAIL = 101;

    public static final int CONNECTION_CLOSE_OK = 200;
    public static final int CONNECTION_CLOSE_FAIL = 201;

    public static final int NOTE_SAVED_OK = 0;
    public static final int NOTE_SAVED_FAIL = 0;

    int connect();

    int disconnect();

    int saveNote(NoteModel note);

    NoteModel loadNote(int id);

    NoteListModel searchNote(String keyword);
}
