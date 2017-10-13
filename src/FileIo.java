import java.io.*;
import java.util.ArrayList;

public class FileIo {

    private static final String PATH = "C:/file/fileio.txt";

    public static void serializing(ArrayList<Shape> shapes) {
        try {
            FileOutputStream fos = new FileOutputStream(PATH);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream out = new ObjectOutputStream(bos);

            out.writeObject(shapes);
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

            shapes = (ArrayList<Shape>) in.readObject();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return shapes;

    }
}


