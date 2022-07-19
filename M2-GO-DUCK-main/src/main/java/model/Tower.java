package model;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public abstract class Tower {
    private Rectangle shape;
    private double layoutX;
    private double layoutY;
    private double attackRangeX;
    private double attackRangeY;
    private int level;

    public abstract void attack(Enemy enemy, Pane battlefield);

    public abstract void upgrade();

    public abstract Node getShape();

    public abstract double getLayoutX();

    public abstract double getLayoutY();

    public abstract void setLayoutX(double x);

    public abstract void setLayoutY(double y);

    public abstract double getAttackRangeX();

    public abstract double getAttackRangeY();

    public int getLevel() {return level;}

}
