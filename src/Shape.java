import processing.core.PApplet;

abstract class Shape implements Cloneable {

    private float x;
    private float y;
    private Color color;

    Shape(float x, float y, Color color){
        this.x=x;
        this.y=y;
        this.color = color;
    }


    abstract void draw(PApplet pApplet);

    abstract boolean checkCollision(int mouseX, int mouseY);
    @Override
    public Shape clone() {
        try {
            Shape s = (Shape) super.clone();
            s.color = this.color.clone();
            return s;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
