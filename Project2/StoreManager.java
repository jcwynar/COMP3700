public class StoreManager {
    public static String dbms = "Network";
    public static String path = "localhost:1001";

    IDataAdapter dataAdapter;
    private static StoreManager instance = null;

    public static StoreManager getInstance() {
        if (instance == null) {
            instance = new StoreManager(dbms, path);
        }
        return instance;
    }

    private StoreManager(String dbms, String dbfile) {
        dataAdapter = new NetworkDataAdapter();
        dataAdapter.connect(dbfile);
    }

    public IDataAdapter getDataAdapter() {
        return dataAdapter;
    }

    public void run() {
        MainUI ui = new MainUI();
        ui.view.setVisible(true);
    }

    public static void main(String[] args) {
        StoreManager.getInstance().run();
    }
}
