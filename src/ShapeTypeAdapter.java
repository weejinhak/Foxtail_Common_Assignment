import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ShapeTypeAdapter extends TypeAdapter<Shape> {

    @Override
    public void write(JsonWriter writer, Shape shape) throws IOException {
        writer.beginObject();
        writer.name("type").value(shape.getClass().getName());
        writer.name("x").value(shape.getX());
        writer.name("y").value(shape.getY());
        writer.name("r").value(shape.getColor().getRed());
        writer.name("g").value(shape.getColor().getGreen());
        writer.name("b").value(shape.getColor().getBlue());
        writer.endObject();
    }

    @Override
    public Shape read(JsonReader reader) throws IOException {
        reader.beginObject();

        Shape shape = null;
        while (reader.hasNext()) {
            switch (reader.nextName()) {
                case "type":
                    String type = reader.nextString();
                    if (type.equals("Rect"))
                       shape  = new Rect();
                    else if (type.equals("Circle"))
                        shape = new Circle();
                    break;
                case "x":
                    if (shape != null) {
                        shape.setX(reader.nextInt());
                    }
                    break;
                case "y":
                    if (shape != null) {
                        shape.setY(reader.nextInt());
                    }
                    break;
                case"r":
                    if(shape!=null){
                        shape.getColor().setRed(reader.nextInt());
                    }
                case"g":
                    if(shape!=null){
                        shape.getColor().setGreen(reader.nextInt());
                    }
                case"b":
                    if(shape!=null){
                        shape.getColor().setBlue(reader.nextInt());
                    }
            }
        }

        reader.endObject();
        return shape;
    }

}