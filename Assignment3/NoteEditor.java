public class NoteEditor {
    private static NoteEditor instance = null;

    private SQLiteDataAdapter dao;

    public static NoteEditor getInstance() {
        if (instance == null) {
            instance = new NoteEditor("SQLite");
        }
        return instance;
    }

    private NoteEditor(String db) {
        if (db.equals("SQLite")) {
            dao = new SQLiteDataAdapter();
        }
        dao.connect();
    }

    public SQLiteDataAdapter getDataAccess() {
        return dao;
    }

    public void setDataAdapter(SQLiteDataAdapter d) {
        dao = d;
    }

    public void run() {
        MainUI ui = new MainUI();
        ui.view.setVisible(true);
    }

    public static void main(String[] args) {
        NoteEditor.getInstance().run();
    }
}