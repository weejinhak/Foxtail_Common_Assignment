import processing.core.PApplet;

public class Circle extends Shape {

    Circle(){}
    public Circle(float x, float y, Color color) {
        super(x, y, color);
    }


    @Override
    public void draw(PApplet pApplet){
        pApplet.fill(super.getColor().getRed(),super.getColor().getGreen(),super.getColor().getBlue());
        pApplet.ellipse(super.getX(),super.getY(),35,35);
    }
    @Override
    public Circle clone() {
        return (Circle) super.clone();
    }

    @Override
    public boolean checkCollision(int mouseX, int mouseY) {
        return (super.getX() - mouseX) * (super.getX() - mouseX) + (super.getY() - mouseY) * (super.getY() - mouseY) <= 35 * 35;
    }
}
