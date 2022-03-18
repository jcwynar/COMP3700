import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class RemoteDataAdapter implements DataAccess {
    Gson gson = new Gson();
    Socket s = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;

    @Override
    public void connect() {
        try {
            s = new Socket("localhost", 5056);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveNote(NoteModel note) {
        RequestModel req = new RequestModel();
        req.code = req.SAVE_NOTE_REQUEST;
        int NoteID = note.NoteID;
        String Title = note.Title;
        String Body = note.Body;
        NoteModel newModel = new NoteModel();
        newModel.NoteID = NoteID;
        newModel.Title = Title;
        newModel.Body = Body;
        req.body = gson.toJson(newModel);

        System.out.println(req.body);

        String json = gson.toJson(req);
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
            }
            else
                System.out.println("Successfully sent note to the server! " + res.body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public NoteModel loadNote(int noteID) {
        RequestModel req = new RequestModel();
        req.code = req.LOAD_NOTE_REQUEST;
        req.body = String.valueOf(noteID);

        String json = gson.toJson(req);
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            }
            else         // this is a JSON string for a product information
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a note with that ID!");
                    return null;
                }
                else {
                    NoteModel model = gson.fromJson(res.body, NoteModel.class);
                    System.out.println("Receiving a NoteModel object");
                    System.out.println("NoteID = " + model.NoteID);
                    System.out.println("Note title = " + model.Title);
                    return model; // found this note and return!!!
                }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public NoteListModel searchNote(String keyword) {
        RequestModel req = new RequestModel();
        NoteListModel list = null;
        req.code = req.SEARCH_NOTE_REQUEST;
        req.body = keyword;

        String json = gson.toJson(req);
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            }
            else         // this is a JSON string for a product information
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a note with that keyword!");
                    return null;
                }
                else {
                    list = gson.fromJson(res.body, NoteListModel.class);
                    System.out.println("Receiving a NoteListModel object of size " + list.list.size());
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
