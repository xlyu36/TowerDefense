package model;

import javafx.scene.shape.Rectangle;

public class Path {
    private Rectangle shape;
    private double layoutX;
    private double layoutY;

    public Path(Rectangle rectangle, double x, double y) {
        shape = rectangle;
        layoutX = x;
        layoutY = y;
    }

    public Rectangle getShape() {
        return shape;
    }
    public double getX() {
        return layoutX;
    }
    public double getY() {
        return layoutY;
    }
}
