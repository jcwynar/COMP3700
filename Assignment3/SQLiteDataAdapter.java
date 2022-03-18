import java.sql.*;

public class SQLiteDataAdapter implements DataAccess {
    Connection conn = null;

    @Override
    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:Notes.db";
            // create a connection to the database
            System.out.println(System.getProperty("java.class.path"));
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Failed to load SQLite JDBC class", e);
            }
            conn = DriverManager.getConnection(url);

            System.out.println(" conn = " + conn);

            if (conn == null)
                System.out.println("Cannot make the connection!!!");
            else
                System.out.println("The connection object is " + conn);

            System.out.println("Connection to SQLite has been established.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveNote(NoteModel note) {
        try {
            Statement stmt = conn.createStatement();

            if (loadNote(note.NoteID) == null) {           // this is a new note!
                stmt.execute("INSERT INTO Notes(NoteID, Title, Body) VALUES ("
                        + note.NoteID + ","
                        + '\'' + note.Title + '\'' + ","
                        + '\''+ note.Body + '\'' + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Notes SET "
                        + "NoteID = " + note.NoteID + ","
                        + "Title = " + '\'' + note.Title + '\'' + ","
                        + "Body = " + '\'' + note.Body + '\''
                        + " WHERE NoteID = " + note.NoteID
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public NoteModel loadNote(int id) {
        NoteModel note = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Notes WHERE NoteID = " + id);
            if (rs.next()) {
                note = new NoteModel();
                note.NoteID = rs.getInt(1);
                note.Title = rs.getString(2);
                note.Body = rs.getString(3);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return note;
    }

    @Override
    public NoteListModel searchNote(String keyword) {
        NoteModel note = null;
        NoteListModel listModel = new NoteListModel();
        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM Notes WHERE Title LIKE \'%" +  keyword + "%\'";
            System.out.println("Search SQL statement " + sql);

            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {
                note = new NoteModel();
                note.NoteID = rs.getInt(1);
                note.Title = rs.getString(2);
                note.Body = rs.getString(3);

                listModel.list.add(note); // make a new NoteModel and add
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listModel;
    }


}
