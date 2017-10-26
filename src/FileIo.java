import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileIo {

    private static final String PATH = "/Users/wee/workspace/IdeaProjects/file.txt";

    public static void serializing(ArrayList<Shape> shapes) {
        try {
            FileOutputStream fos = new FileOutputStream(PATH);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream out = new ObjectOutputStream(bos);

            Gson gson= new Gson();

            String json = gson.toJson(shapes);

            out.writeObject(json);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Shape> deserializing() {
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        try {
            FileInputStream fis = new FileInputStream(PATH);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream in = new ObjectInputStream(bis);

            Gson gson= new Gson();

            shapes = (ArrayList<Shape>) in.readObject();

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return shapes;

    }
}


