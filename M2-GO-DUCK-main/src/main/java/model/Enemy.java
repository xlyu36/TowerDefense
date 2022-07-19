package model;
import javafx.scene.Node;

public abstract class Enemy {
    private int health;
    private Node node;
    private double layoutX;
    private double layoutY;
    private String originalStyle;
    private double gold;
    private boolean goldAdded;
    private double speed;
    private int damage;

    public Node getNode() {
        return node;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int h) {
        health = h;
    }
    public double getLayoutX() {
        return layoutX;
    }
    public double getLayoutY() {
        return layoutY;
    }
    public void setLayoutX(double x) {
        layoutX = x;
    }
    public void setLayoutY(double y) {
        layoutY = y;
    }
    public String getOriginalStyle() {
        return originalStyle;
    }
    public double getGold() {
        return gold;
    }
    public boolean getGoldAdded() {
        return goldAdded;
    }
    public void setGoldAdded(boolean b) {
        goldAdded = b;
    }
    public double getSpeed() {
        return speed;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int d) {
        damage = d;
    }
}
