package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;

import java.awt.*;

public class FirstScreen {
    private int width;
    private int length;
    private Button playButton;
    private Button quitButton;

    public FirstScreen(int width, int length) {
        this.width = width;
        this.length = length;
        playButton = new Button("Play!");
        quitButton = new Button("Quit");
    }
    public Scene getScene() {
        BackgroundImage image = new BackgroundImage(
                new Image("firstPageBackground.jpg",1500 ,800, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Label label = new Label("Welcome to the game!");
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        buttons.getChildren().addAll(playButton, quitButton);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(label, buttons);
        vbox.setBackground(new Background(image));
        Scene scene = new Scene(vbox, width, length);
        return scene;
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public Button getPlayButton() {
        return playButton;
    }
}
