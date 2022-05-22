package main.framework.object2D;

import javafx.scene.shape.Rectangle;

public class GameObject2D extends Rectangle {

    private String name;

    private double width;
    private double height;
    private double X;
    private double Y;
    private double velocity = 0;

    public GameObject2D(String name, double width, double height, double x, double y) {
        this.name = name;
        setWidth(width);
        setHeight(height);
        setX(x);
        setY(y);
    }

    public String getName() {
        return name;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVelocity(double v) {
        velocity = v;
    }


}
