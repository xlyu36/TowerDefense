package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Enemy2 extends Enemy {
    private int health;
    private Node node;
    private double layoutX;
    private double layoutY;
    private String originalStyle;
    private double gold;
    private boolean goldAdded = false;
    private double speed;
    private int damage;
    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50),
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    if (health <= 0) {
                        node.setVisible(false);
                    }
                }
            }));

    public Enemy2(double initialX, double initialY) {
        Circle enemyShape = new Circle();
        enemyShape.setStyle("-fx-fill: #ce1a1a");
        enemyShape.setRadius(15);
        node = enemyShape;
        health = 2500;
        layoutX = initialX;
        layoutY = initialY;
        originalStyle = "-fx-fill: #ce1a1a";
        gold = 5;
        speed = 0.13;
        damage = 2;
    }

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
