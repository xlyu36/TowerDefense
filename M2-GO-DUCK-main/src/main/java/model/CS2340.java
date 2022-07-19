package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class CS2340 extends Tower {
    private Node shape;
    private double layoutX;
    private double layoutY;
    private double attackRangeX;
    private double attackRangeY;
    private int count;
    private int damage;
    private int level;

    public CS2340(Node node, double x, double y) {
        shape = node;
        layoutX = x;
        layoutY = y;
        attackRangeX = 170;
        attackRangeY = 140;
        damage = 3;
        level = 1;
    }
    public void upgrade() {
        damage += 2;
        level++;
    }

    public void attack(Enemy enemy, Pane battlefield) {
        if (count % 1 == 0) {
            enemy.getNode().setStyle(enemy.getOriginalStyle());
        }
        if (count % 5 == 0) {
            Circle bullet = new Circle(2);
            bullet.setLayoutX(layoutX + 30);
            bullet.setLayoutY(layoutY + 90);
            battlefield.getChildren().add(bullet);
            moveBullet(bullet, enemy);
            enemy.setHealth(enemy.getHealth() - damage);
        }
        count++;
    }
    private void moveBullet(Node bullet, Enemy enemy) {
        TranslateTransition move = new TranslateTransition();
        move.setFromX(layoutX);
        move.setFromY(layoutY);
        move.setToX(enemy.getLayoutX());
        move.setToY(enemy.getLayoutY());
        move.setDuration(Duration.seconds(0.2));
        move.setNode(bullet);
        move.play();
        move.setOnFinished(e -> {
            bullet.setVisible(false);
            enemy.getNode().setStyle("-fx-fill: green");
        });
    }

    public Node getShape() {
        return shape;
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

    public double getAttackRangeX() {
        return attackRangeX;
    }

    public double getAttackRangeY() {
        return attackRangeY;
    }

    public int getLevel() {return level;}

    public int getDamage() {return damage;}
}
