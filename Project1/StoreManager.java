import javax.swing.*;
import java.util.Scanner;

public class StoreManager {
   IDataAdapter adapter = null;

   private static StoreManager instance = null;

   public static StoreManager getInstance() {
      if (instance == null) {
         instance = new StoreManager("SQLite");
      }
      
      return instance;
   }

   private StoreManager(String db) {
      if (db.equals("SQLite")) {
         adapter = new SQLiteDataAdapter();
      }
      adapter.connect();
   }

   public IDataAdapter getDataAdapter() {
      return adapter;
   }

   public void setDataAdapter(IDataAdapter d) {
      adapter = d;
   }

   public void run() {
      MainUI ui = new MainUI();
      ui.view.setVisible(true);
   }

   public static void main(String[] args) {
      Scanner newScan = new Scanner(System.in);
   
      System.out.println("Enter your name");
   
      String userName = newScan.nextLine();
   
      System.out.println("Hello " + userName + "!");
      
      StoreManager.getInstance().run();
   
      newScan.close();
   }
}
