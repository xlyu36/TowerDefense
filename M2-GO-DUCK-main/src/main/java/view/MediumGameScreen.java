package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.*;
import model.Background;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MediumGameScreen {
    private int width;
    private int length;
    private Text moneyText;
    private int currentGold;
    private boolean cs1331isOn;
    private boolean cs1332isOn;
    private boolean cs2340isOn;
    private int cs1331Price;
    private int cs1332Price;
    private int cs2340Price;
    private int health;
    private Label healthLabel;
    private int numRow;
    private int numColumn;
    private int pixelWidth;
    private int pixelHeight;
    private Node monument;
    private List<Background> backgroundList;
    private List<Enemy> enemyList;
    private List<Tower> towerList;
    private StackPane enemyInitialGrid;
    private GridPane battlefield;
    private BorderPane borderPane;
    private boolean combatStarted;
    private boolean iswin = false;
    private double totalTime;
    private int deadenemy = 0;

    //private ObservableList<Tower> towerObservableList;
    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1),
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent someEvent) {
                    if (combatStarted) {
                        totalTime += 0.001;
                        for (int i = 0; i < enemyList.size(); i++) {
                            Enemy enemy = enemyList.get(i);
                            if (enemy.getHealth() > 0 && enemy.getNode().isVisible()) {
                                //update enemyLout X and Y
                                int marker = 0;
                                if (Math.abs(enemy.getLayoutX() - 624) < 0.1) {
                                    marker = 1;
                                }
                                if (Math.abs(enemy.getLayoutY() - 620.8) < 0.1) {
                                    marker = 0;
                                }
                                if (Math.abs(enemy.getLayoutX() - 936) < 0.1) {
                                    marker = -1;
                                }
                                if (marker == 0) {
                                    enemy.setLayoutX(enemy.getLayoutX() + enemy.getSpeed());
                                } else if (marker == 1) {
                                    enemy.setLayoutY(enemy.getLayoutY() + enemy.getSpeed());
                                } else if (marker == -1) {
                                    enemy.setLayoutY(enemy.getLayoutY() - enemy.getSpeed());
                                }
                                //checking if inside tower range
                                for (Tower tower : towerList) {
                                    if (enemy.getLayoutX()
                                            >= tower.getLayoutX() - tower.getAttackRangeX()
                                            && enemy.getLayoutX()
                                            <= tower.getLayoutX() + tower.getAttackRangeX()
                                            && enemy.getLayoutY()
                                            >= tower.getLayoutY() - tower.getAttackRangeY()
                                            && enemy.getLayoutY()
                                            <= tower.getLayoutY() + tower.getAttackRangeY()
                                            && enemy.getHealth() > 0) {
                                        tower.attack(enemy, battlefield);
                                    }
                                }
                                if (Math.abs(enemy.getLayoutX() - 936) < 0.5
                                        && Math.abs(enemy.getLayoutY() - 121.6) < 0.5) {
                                    health -= enemy.getDamage();
                                    healthLabel.setText(health + "/10");
                                    enemy.getNode().setVisible(false);
                                }
                            } else {
                                enemy.getNode().setVisible(false);
                            }
                        }
                        for (Enemy enemy: enemyList) {
                            if (!enemy.getGoldAdded() && enemy.getHealth() <= 0) {
                                currentGold += enemy.getGold();
                                deadenemy++;
                                moneyText.setText("Current gold: " + currentGold);
                                enemy.setGoldAdded(true);
                            }
                            if (enemy instanceof Boss){
                                if (enemy.getHealth() <=0){
                                    iswin =true;
                                }
                            }

                        }
                    }
                }
            }));


    public MediumGameScreen(int width, int length) {
        this.width = width;
        this.length = length;
        borderPane = new BorderPane();
        battlefield = new GridPane();
        currentGold = 100;
        moneyText = new Text("Initial Money: " + currentGold);
        cs1331isOn = false;
        cs1332isOn = false;
        cs2340isOn = false;
        cs1331Price = 15;
        cs1332Price = 25;
        cs2340Price = 25;
        health = 10;
        numRow = 30;
        numColumn = 40;
        pixelWidth = 30;
        pixelHeight = 30;
        monument = new ImageView(new Image("monument.jpg"));
        ((ImageView)monument).setFitWidth(30);
        ((ImageView)monument).setFitHeight(30);
        healthLabel = new Label(health + "/10");
        healthLabel.setFont(new Font(10));
        //towerObservableList = FXCollections.observableList(towerList);
        backgroundList = new ArrayList<>();
        enemyInitialGrid = new StackPane();
        enemyList = new ArrayList<>();
        towerList = new ArrayList<>();
        for (int i = 0; i < health; i++) {
            Enemy currEnemy;
            currEnemy = new Enemy1(-i * 103, 151.2);
            enemyList.add(currEnemy);
        }
        for (int i = health; i < 2 * health; i++) {
            Enemy currEnemy;
            currEnemy = new Enemy2(-i * 134, 151.2);
            enemyList.add(currEnemy);
        }
        for (int i = 2 * health; i < 3 * health; i++) {
            Enemy currEnemy;
            currEnemy = new Enemy3(-i * 155, 151.2);
            enemyList.add(currEnemy);
        }
        Boss boss = new Boss(-(enemyList.size()) * 103, 151.2);
        boss.setDamage(health);
        enemyList.add(boss);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public Scene getScene() {
        borderPane.setPadding(new Insets(5));

        //Mid
        setPath(battlefield);
        borderPane.setCenter(battlefield);

        //Top
        moneyText.setFill(Color.GOLD);
        moneyText.setFont(Font.font("Bradley Hand", FontWeight.BOLD, 50));
        HBox top = new HBox(moneyText);
        top.setAlignment(Pos.CENTER);
        borderPane.setTop(top);

        //Right
        VBox right = new VBox();
        Label description = new Label("Choose a tower: ");
        description.setFont(Font.font("Bradley Hand", 20));
        VBox towerBox = new VBox();
        Label towerDescrip1 = new Label("Attacks single enermy."
                + "\nHigh attack speed.\nPrice: " + cs1331Price);
        Label towerDescrip2 = new Label("Attacks single enermy."
                + "\nHigher attack speed." + "Range: 3 * 3."
                + "\nPrice: " + cs1332Price);
        Label towerDescrip3 = new Label("Attacks multiple enermies."
                + "\nDeals more damage.\nPrice: " + cs2340Price);
        Button cs1331 = new Button("CS1331");
        Button cs1332 = new Button("CS1332");
        Button cs2340 = new Button("CS2340");
        Button move = new Button("Start Combat");
        VBox tower1 = new VBox(cs1331, towerDescrip1);
        VBox tower2 = new VBox(cs1332, towerDescrip2);
        VBox tower3 = new VBox(cs2340, towerDescrip3);
        VBox tower4 = new VBox(move);
        towerBox.getChildren().addAll(tower1, tower2, tower3, tower4);
        towerBox.setSpacing(50);
        right.getChildren().addAll(description, towerBox);
        right.setAlignment(Pos.CENTER);
        towerBox.setAlignment(Pos.CENTER);
        borderPane.setRight(right);

        cs1331.setOnMouseClicked(e -> {
            cs1331isOn = true;
            cs2340isOn = false;
            cs1332isOn = false;
        });
        cs1332.setOnMouseClicked(e -> {
            cs1331isOn = false;
            cs1332isOn = true;
            cs2340isOn = false;
        });
        cs2340.setOnMouseClicked(e -> {
            cs1331isOn = false;
            cs1332isOn = false;
            cs2340isOn = true;
        });

        move.setOnMouseClicked(e -> {
            moveEnemy();
            combatStarted = true;
        });


        Scene scene = new Scene(borderPane, width, length);
        if (health <= 0) {
            GameOver screen = new GameOver(width, length);
            return screen.getScene();
        }
        return scene;
    }

    private void setPath(GridPane battleField) {
        for (int row = 0; row <= numRow; row++) {
            for (int column = 0; column <= numColumn; column++) {
                Rectangle path = new Rectangle(pixelWidth, pixelHeight);
                path.setStyle("-fx-fill: transparent; -fx-stroke: lightBlue");
                Image backgroundImg = new Image("background.png");
                Node background = new ImageView(backgroundImg);
                ((ImageView)background).setFitWidth(30);
                ((ImageView)background).setFitHeight(30);
                background.setStyle("-fx-fill: transparent");

                if (row == 5 && column <= 20) {
                    battleField.add(path, column, row);
                } else if (column == 20 && row >= 5 && row <= 20) {
                    battleField.add(path, column, row);
                } else if (row == 20 && column >= 20) {
                    battleField.add(path, column, row);
                } else if (column == 30 && row >= 5 && row <= 20) {
                    battleField.add(path, column, row);
                } else if (row == 3 && column == 30) {
                    battleField.add(healthLabel, column, row);
                } else {
                    battleField.add(background, column, row);

                    //Place a tower in the battlefield
                    background.setOnMouseClicked(e -> {
                        System.out.println("X: " + background.getLayoutX());
                        System.out.println("Y: " + background.getLayoutY());
                        Tower tower;
                        if (background.getStyle() == "-fx-fill: transparent") {
                            if (currentGold >= cs1331Price && cs1331isOn) {
                                background.setStyle("-fx-fill: gold");
                                ((ImageView)background).setImage(new Image("CS1331.jpg"));
                                currentGold -= cs1331Price;
                                moneyText.setText("Current gold: " + currentGold);
                                tower = new CS1331(background, background.getLayoutX(),
                                        background.getLayoutY());
                                towerList.add(tower);
                            } else if (currentGold >= cs1332Price && cs1332isOn) {
                                background.setStyle("-fx-fill: silver");
                                ((ImageView)background).setImage(new Image("CS1332.jpg"));
                                currentGold -= cs1332Price;
                                moneyText.setText("Current gold: " + currentGold);
                                tower = new CS1332(background, background.getLayoutX(),
                                        background.getLayoutY());
                                towerList.add(tower);
                            } else if (currentGold >= cs2340Price && cs2340isOn) {
                                background.setStyle("-fx-fill: black");
                                ((ImageView)background).setImage(new Image("CS2340.jpg"));
                                currentGold -= cs2340Price;
                                moneyText.setText("Current gold: " + currentGold);
                                tower = new CS2340(background, background.getLayoutX(),
                                        background.getLayoutY());
                                towerList.add(tower);
                            }
                        } else {
                            int rowOfTower = GridPane.getRowIndex(background);
                            int columnOfTower = GridPane.getColumnIndex(background);
                            ObservableList<Node> children = battleField.getChildren();
                            int towerRange = 0;
                            if (background.getStyle() != "-fx-fill: transparent"
                                    && background.getStyle() != "-fx-fill: #6acc6a") {
                                Tower thisTower = null;
                                for (Tower tower1 : towerList) {
                                    if (tower1.getLayoutX() == background.getLayoutX()
                                            && tower1.getLayoutY() == background.getLayoutY()) {
                                        thisTower = tower1;
                                    }
                                }
                                if (thisTower != null && thisTower.getLevel() < 3) {
                                    ButtonType upgrade = new ButtonType("upgrade", ButtonBar.ButtonData.OK_DONE);
                                    ButtonType cancel = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                                    Alert alert = new Alert(Alert.AlertType.WARNING,
                                            "Do you want to upgrade this tower for $25?. ", upgrade, cancel);
                                    alert.setTitle("Upgrade");
                                    Optional<ButtonType> result = alert.showAndWait();

                                    if (result.get() == upgrade && currentGold >= 25) {
                                        thisTower.upgrade();
                                        currentGold -= 25;
                                        moneyText.setText("Current gold: " + currentGold);
                                    }
                                }
                            }
                            if (background.getStyle() == "-fx-fill: gold") {
                                towerRange = 2;
                            } else if (background.getStyle() == "-fx-fill: silver") {
                                towerRange = 3;
                            } else if (background.getStyle() == "-fx-fill: black") {
                                towerRange = 4;
                            }
                            for (Node rectangle : children) {
                                if (rectangle.getStyle()
                                        != "-fx-fill: transparent; -fx-stroke: lightBlue") {
                                    int currRow = GridPane.getRowIndex(rectangle);
                                    int currColumn = GridPane.getColumnIndex(rectangle);
                                    if (currRow >= rowOfTower - towerRange
                                            && currRow <= rowOfTower + towerRange
                                            && currColumn >= columnOfTower - towerRange
                                            && currColumn <= columnOfTower + towerRange) {
                                        if (!rectangle.equals(background)
                                                && rectangle.getStyle() != "-fx-fill: gold"
                                                && rectangle.getStyle() != "-fx-fill: silver"
                                                && rectangle.getStyle() != "-fx-fill: black"
                                                && rectangle.getStyle()
                                                != "-fx-fill: transparent; -fx-stroke: lightBlue"
                                                && !rectangle.equals(monument)) {
                                            rectangle.setStyle("-fx-fill: #6acc6a");
                                            ((ImageView)rectangle).setImage(new Image("towerRange.png"));
                                        }
                                    }
                                }
                            }
                        }
                        background.setOnMouseExited(e2 -> {
                            if (background.getStyle() != "-fx-fill: transparent") {
                                int rowOfTower = GridPane.getRowIndex(background);
                                int columnOfTower = GridPane.getColumnIndex(background);
                                ObservableList<Node> children = battleField.getChildren();
                                int towerRange = 0;
                                if (background.getStyle() == "-fx-fill: gold") {
                                    towerRange = 2;
                                } else if (background.getStyle() == "-fx-fill: silver") {
                                    towerRange = 3;
                                } else if (background.getStyle() == "-fx-fill: black") {
                                    towerRange = 4;
                                }
                                for (Node rectangle : children) {
                                    if (rectangle.getStyle()
                                            != "-fx-fill: transparent; -fx-stroke: lightBlue") {
                                        int currRow = GridPane.getRowIndex(rectangle);
                                        int currColumn = GridPane.getColumnIndex(rectangle);
                                        if (currRow >= rowOfTower - towerRange
                                                && currRow <= rowOfTower + towerRange
                                                && currColumn >= columnOfTower - towerRange
                                                && currColumn <= columnOfTower + towerRange) {
                                            if (!rectangle.equals(background)
                                                    && rectangle.getStyle() != "-fx-fill: gold"
                                                    && rectangle.getStyle() != "-fx-fill: silver"
                                                    && rectangle.getStyle() != "-fx-fill: black"
                                                    && rectangle.getStyle()
                                                    != "-fx-fill: transparent; -fx-stroke: lightBlue"
                                                    && !rectangle.equals(monument)) {
                                                rectangle.setStyle("-fx-fill: transparent");
                                                ((ImageView)rectangle).setImage(new Image("background.png"));
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    });
                }
            }
        }
        battleField.add(monument, 30, 4);
    }
    private void moveEnemy() {
        for (int i = 0; i < enemyList.size(); i++) {
            Enemy currEnemy = enemyList.get(i);
            enemyInitialGrid.getChildren().add(currEnemy.getNode());
        }
        battlefield.add(enemyInitialGrid, 0, 5);
        for (int i = 0; i < enemyList.size(); i++) {
            Enemy currEnemy = enemyList.get(i);
            Node n = currEnemy.getNode();
            double x = n.getLayoutX();
            double y = n.getLayoutY();
            double stepSize = currEnemy.getSpeed();
            double durationSize = 1;
            double currentDelay = 0;
            double initialDelay = i * 1000;
            Duration duration = Duration.millis(durationSize);
            Duration delay = Duration.millis(currentDelay);
            boolean end = false;
            while (!end) {
                TranslateTransition move = new TranslateTransition();
                if (x == 0 && y == 0) {
                    move.setFromX(x);
                    move.setToX(x + stepSize);
                    x += stepSize;
                    move.setDuration(duration);
                    move.setDelay(new Duration(initialDelay));
                    delay = new Duration(currentDelay + durationSize);
                    currentDelay += initialDelay;
                    move.setNode(n);
                    move.play();
                    n.setLayoutX(n.getLayoutX() + stepSize);
                    continue;
                } else if (x < 624 && y == 0) {
                    move.setFromX(x);
                    move.setToX(x + stepSize);
                    x += stepSize;
                    n.setLayoutX(n.getLayoutX() + stepSize);

                } else if (Math.abs(x - 624) < 0.2 && y < 468) {
                    move.setFromY(y);
                    move.setToY(y + stepSize);
                    y += stepSize;
                    n.setLayoutY(n.getLayoutY() + stepSize);

                } else if (x < 936.0 && Math.abs(y - 468) < 0.2) {
                    move.setFromX(x);
                    move.setToX(x + stepSize);
                    x += stepSize;
                    n.setLayoutX(n.getLayoutX() + stepSize);

                } else if (Math.abs(x - 936.0) < 1 && y > 0) {
                    move.setFromY(y);
                    move.setToY(y - stepSize);
                    y -= stepSize;
                    n.setLayoutY(n.getLayoutY() - stepSize);

                }
                if (Math.abs(x - 936.0) < 1 && Math.abs(y - 0) < 1) {
                    end = true;
                }


                move.setDuration(duration);
                move.setDelay(delay);
                delay = new Duration(currentDelay + durationSize);
                currentDelay += durationSize;
                move.setNode(n);
                move.play();
            }
        }
    }
    public int getHealth() {
        return health;
    }
    public void setHealth() {
        health = 12;
    }

    public boolean getwin (){return iswin;}

    public int getDeadenemy(){ return deadenemy;}

    public int getgold() {return currentGold;}

    public double getTime(){ return totalTime;}
}


