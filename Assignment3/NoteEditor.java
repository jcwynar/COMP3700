public class NoteEditor {

    private static NoteEditor instance = null;

    private RemoteDataAdapter dao;

    private NoteView view = null;

    public NoteView getNoteView() {
        return view;
    }

    private NoteController noteController = null;

    public static NoteEditor getInstance() {
        if (instance == null)
            instance = new NoteEditor("SQLite");
        return instance;
    }

    public RemoteDataAdapter getDataAccess() {
        return dao;
    }

    private NoteEditor(String db) {
        // do some initialization here!!!
        dao = new RemoteDataAdapter();
        dao.connect();
        view = new NoteView();
        noteController = new NoteController(view, dao);
    }






}
