package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class InitialConfigScreen {
    private int width;
    private int length;
    private ToggleButton easyButton;
    private ToggleButton mediumButton;
    private ToggleButton hardButton;
    private Button nextButton;
    private TextField usernameTextField;

    public InitialConfigScreen(int width, int length) {
        this.width = width;
        this.length = length;
        easyButton = new ToggleButton("Easy");
        mediumButton = new ToggleButton("Medium");
        hardButton = new ToggleButton("Hard");
        nextButton = new Button("Next");
        usernameTextField = new TextField("Enter your username:");
    }
    public Scene getScene() {
        BackgroundImage image = new BackgroundImage(
                new Image("firstPageBackground.jpg",1500 ,800, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Label usernameLabel = new Label("Welcome to the game!");
        Label difficultyLabel = new Label("Select Difficulties:");
        HBox difficultiesArrange = new HBox();
        difficultiesArrange.setAlignment(Pos.CENTER);
        difficultiesArrange.setSpacing(10);
        difficultiesArrange.getChildren().addAll(easyButton, mediumButton, hardButton);

        VBox layout2 = new VBox();
        layout2.setAlignment(Pos.CENTER);
        layout2.setSpacing(10);
        layout2.getChildren().addAll(usernameLabel, usernameTextField,
                difficultyLabel, difficultiesArrange, nextButton);
        layout2.setBackground(new Background(image));
        Scene scene = new Scene(layout2, width, length);
        return scene;
    }

    public ToggleButton getEasyButton() {
        return easyButton;
    }

    public ToggleButton getMediumButton() {
        return mediumButton;
    }

    public ToggleButton getHardButton() {
        return hardButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public TextField getUsername() {
        return usernameTextField;
    }
}