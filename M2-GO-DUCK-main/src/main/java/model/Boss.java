package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Boss extends Enemy {
    private Image img;
    private Node node;
    private int health;
    private double layoutX;
    private double layoutY;
    private String originalStyle;
    private double gold;
    private boolean goldAdded;
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

    public Boss(double initialX, double initialY) {
        img = new Image("boss.jpg");
        node = new ImageView(img);
        ((ImageView)node).setFitWidth(30);
        ((ImageView)node).setFitHeight(30);
        health = 15000;
        layoutX = initialX;
        layoutY = initialY;
        gold = 50;
        goldAdded = false;
        speed = 0.1;
        damage = 5;
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

