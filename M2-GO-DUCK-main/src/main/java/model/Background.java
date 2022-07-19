package model;

import javafx.scene.shape.Rectangle;

public class Background {
    private Rectangle node;
    private int layoutX;
    private int layoutY;
    private boolean isTower;

    public Background(Rectangle rectangle, int x, int y) {
        node = rectangle;
        layoutX = x;
        layoutY = y;
        isTower = false;
    }

    public Rectangle getNode() {
        return node;
    }
    public int getX() {
        return layoutX;
    }
    public int getY() {
        return layoutY;
    }
    public boolean getIsTower() {
        return isTower;
    }
    public void setIsTower() {
        isTower = true;
    }
}
