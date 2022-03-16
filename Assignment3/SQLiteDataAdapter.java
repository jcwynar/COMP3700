import java.sql.*;

public class SQLiteDataAdapter implements DataAccess {
    Connection conn = null;

    @Override
    public int connect() {
        try {
            // parameters for database
            String url = "jdbc:sqlite:Notes.db";
            // establish connection
            System.out.println(System.getProperty("java.class.path"));
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Failed to load SQLite JDBC class", e);
            }
            conn = DriverManager.getConnection(url);

            if (conn == null) {
                System.out.println("Cannot make the connection!!!");
            }
            else {
                System.out.println("The connection object is " + conn);
            }

            System.out.println("Connection to SQLite database established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return CONNECTION_OPEN_FAIL;
        }
        return CONNECTION_OPEN_OK;
    }

    public int disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return CONNECTION_CLOSE_FAIL;
        }
        return CONNECTION_CLOSE_OK;
    }

    @Override
    public int saveNote(NoteModel note) {
        try {
            Statement stmt = conn.createStatement();

            System.out.println("entered save function");

            if (loadNote(note.id) == null) {           // this is a new note!
                String sql = "INSERT INTO Notes(NoteID, Title, Body) VALUES ("
                        + note.id + ","
                        + "\"" + note.title + "\"" + ","
                        + "\""+ note.body + "\"" + ")";
                System.out.println(sql);
                stmt.execute(sql);
            }
            else {
                stmt.executeUpdate("UPDATE Notes SET "
                        + "NoteID = " + note.id + ","
                        + "Title = " + "\"" + note.title + "\"" + ","
                        + "Body = " + "\"" + note.body + "\""
                        + " WHERE NoteID = " + note.id
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            return NOTE_SAVED_FAIL;
        }
        return NOTE_SAVED_OK;
    }

    @Override
    public NoteModel loadNote(int id) {
        NoteModel note = null;

        System.out.println("entered load function");

        try {
            String sql = "SELECT NoteID, Title, Body FROM Notes WHERE NoteID = " + id;
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                note = new NoteModel();
                note.id = rs.getInt(1);
                note.title = rs.getString(2);
                note.body = rs.getString(3);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return note;
    }

    @Override
    public NoteListModel searchNote(String keyword) {
        NoteModel note;
        NoteListModel listModel = new NoteListModel();
        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM Notes WHERE Title LIKE \'%" +  keyword + "%\'";
            System.out.println("Search SQL statement " + sql);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                note = new NoteModel();
                note.id = rs.getInt(1);
                note.title = rs.getString(2);
                note.body = rs.getString(3);

                listModel.list.add(note); // make a new NoteModel and add
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listModel;
    }
}
