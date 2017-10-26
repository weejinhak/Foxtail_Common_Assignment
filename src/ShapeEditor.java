import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import processing.core.PApplet;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShapeEditor extends PApplet implements Serializable{

    private ArrayList<Shape> shapeList = new ArrayList<>();
    private int keyState;
    private boolean isControlPressed;

    public static void main(String[] args) {
        PApplet.main("ShapeEditor");
    }

    @Override
    public void draw() {
        background(255);
        for (Shape e : shapeList) {
            e.draw(this);
        }
    }

    @Override
    public void setup() {
        background(255);
        keyState = 0;
        isControlPressed = false;
    }

    @Override
    public void settings() {
        size(600, 600);
    }


    @Override
    public void mouseClicked() {
        if (keyState == 1) {
            shapeList.add(new Rect(mouseX, mouseY, new Color(0, 0, 0)));
        } else if (keyState == 2) {
            shapeList.add(new Circle(mouseX, mouseY, new Color(0, 0, 0)));
        } else if (keyState == 3) {
            shapeList.add(new Triangle(mouseX, mouseY, new Color(0, 0, 0)));
        }
        keyState = 0;
    }

    @Override
    public void keyPressed() {
        int key = Character.toUpperCase(keyCode);
        if (keyCode == CONTROL)
            isControlPressed = true;
        if ((char) keyCode == '1')
            keyState = 1;
        if ((char) keyCode == '2')
            keyState = 2;
        if ((char) keyCode == '3')
            keyState = 3;
        if ((char)key=='d')
            keyState = 9;
        if (keyState == 9 && isControlPressed)
            duplicateShape();
        if((char)key=='s')
            keyState=8;
        if(keyState==8 && isControlPressed)
            System.out.print("저장되냐");
            saveShape();
        if((char)key=='o')
            keyState=7;
        if(keyState==7 && isControlPressed){
            System.out.println("불러오냐");
            openShape();
        }
    }

    @Override
    public void keyReleased() {
        if (keyCode == CONTROL)
            isControlPressed = false;
    }

    private void duplicateShape() {
        for (Shape e : shapeList) {
            if (e.checkCollision(mouseX, mouseY)) {
                Shape shape = e.clone();
                shape.setX(shape.getX() + 15);
                shapeList.add(shape);
                return;
            }
        }
    }

    @Override
    public void mouseDragged() {
        for (Shape e : shapeList) {
            if (e.checkCollision(mouseX, mouseY)) {
                e.setX(mouseX);
                e.setY(mouseY);
                clickColor(e);
                return;
            }
        }
    }

    @Override
    public void mouseReleased() {
        originColor();
    }

    private void originColor() {
        for (Shape e : shapeList) {
            Color changeColor = e.getColor();
            changeColor.setRed(0);
            e.setColor(changeColor);
        }
    }

    private void clickColor(Shape e) {
        Color changeColor = e.getColor();
        changeColor.setRed(255);
        e.setColor(changeColor);

    }
    private void saveShape(){
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Shape.class, new ShapeTypeAdapter()).create();
        try(Writer writer= new FileWriter("shape.json")){
            gson.toJson(shapeList,writer);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void openShape(){
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Shape.class, new ShapeTypeAdapter()).create();
        Type type = new TypeToken<List<Shape>>() {}.getType();
        try(BufferedReader br = new BufferedReader(new FileReader("Shape.json"))){
            shapeList = gson.fromJson(br, type);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
