import processing.core.PApplet;

public class Triangle extends Shape {
    Triangle(float x, float y,Color color){
        super(x,y,color);
    }
    @Override
    public void draw(PApplet pApplet){
        pApplet.fill(super.getColor().getRed(),super.getColor().getGreen(),super.getColor().getBlue());
        pApplet.triangle(super.getX(), super.getY(), super.getX()+23, super.getY()-50,super.getX()+56,super.getY() );
    }

    @Override
    public Triangle clone() {
        return (Triangle) super.clone();
    }

    @Override
    public boolean checkCollision(int mouseX, int mouseY) {
        return mouseX <= super.getX() + 35 && mouseX >= super.getX() && mouseY <= getY() + 35 && mouseY >= getY();
    }
}
