package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WinGameScreen {
    private int width;
    private int length;
    private Button playButton;
    private Button quitButton;
    private int totalDeadEnemy;
    private Label deadEnemyLabel;
    private Label gold;
    private Label time;
    private int totalgold;
    private double totaltime;


    public WinGameScreen(int width, int length) {
        this.width = width;
        this.length = length;
        playButton = new Button("Restart!");
        quitButton = new Button("Quit");
        totalDeadEnemy = 0;
        totalgold = 0;
        totaltime = 0 ;

        deadEnemyLabel = new Label("Total enemies killed: " + totalDeadEnemy);
        gold = new Label("Total gold remains: " + totalgold);
        time = new Label ("Total time: "+totaltime );
    }


    public Scene getScene() {
        Label label = new Label("Win!");
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        buttons.getChildren().addAll(playButton, quitButton);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(label, buttons, deadEnemyLabel, gold, time);
        Scene scene = new Scene(vbox, width, length);
        return scene;
    }

    public Button getQuitButton() {
        return quitButton;
    }
    public Button getPlayButton() {
        return playButton;
    }

    public void setTotalDeadEnemy (int newdamage){
        totalDeadEnemy = newdamage;
        deadEnemyLabel.setText("Total enemies killed: " + totalDeadEnemy);
    }

    public int getTotalDeadEnemy() {
        return totalDeadEnemy;
    }

    public void setGold(int newgold ){
        gold.setText("Total gold remains: " +newgold );
    }

    public void setTime(double newtime ){
        time.setText("Total time: " +newtime );
    }



}
