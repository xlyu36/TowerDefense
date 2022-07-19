package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.util.Duration;
import model.GameModel;
import view.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller extends Application {
    private Stage mainWindow;
    private GameModel gameModel;
    private final int width = 1500;
    private final int length = 800;

    private EasyGameScreen easyGameScreen = new EasyGameScreen(width, length);
    private HardGameScreen hardGameScreen = new HardGameScreen(width, length);
    private MediumGameScreen mediumGameScreen = new MediumGameScreen(width, length);
    private boolean gameOver = false;
    private GameOver gameOverScreen = new GameOver(width, length);
    private WinGameScreen winScreen = new WinGameScreen(width, length);


    private Timeline fiveSecondsWonder = new Timeline(
            new KeyFrame(Duration.seconds(0.5),
                    new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            if (gameOver) {
                                return;
                            }
                            int easycurr = easyGameScreen.getHealth();
                            int mediumcurr = mediumGameScreen.getHealth();
                            int hardcurr = hardGameScreen.getHealth();
                            boolean easywin = easyGameScreen.getwin();
                            boolean meddiumwin = mediumGameScreen.getwin();
                            boolean hardwin = hardGameScreen.getwin();
                            int easyDeadEnemy = easyGameScreen.getDeadenemy();
                            int easygold = easyGameScreen.getgold();
                            double easytime= easyGameScreen.getTime();

                            int mediumDeadEnemy = mediumGameScreen.getDeadenemy();
                            int mediumgold = mediumGameScreen.getgold();
                            double mediumtime= mediumGameScreen.getTime();

                            int hardDeadEnemy = hardGameScreen.getDeadenemy();
                            int hardgold = hardGameScreen.getgold();
                            double hardtime= hardGameScreen.getTime();


                            if (easycurr <= 0 || mediumcurr <= 0 || hardcurr <= 0) {
                                gameOver = true;
                                System.out.println("Close !");
                                mainWindow.close();

                                if (gameModel.getGameDifficulties() == 1){
                                    gameOverScreen.setTotalDeadEnemy((easyDeadEnemy));
                                    gameOverScreen.setGold(easygold);
                                    gameOverScreen.setTime(easytime);
                                    Scene scene = gameOverScreen.getScene();
                                    mainWindow.setScene(scene);
                                    mainWindow.show();
                                    gameOver();
                                } else if (gameModel.getGameDifficulties() == 2){
                                    gameOverScreen.setTotalDeadEnemy((mediumDeadEnemy));
                                    gameOverScreen.setGold(mediumgold);
                                    gameOverScreen.setTime(mediumtime);
                                    Scene scene = gameOverScreen.getScene();
                                    mainWindow.setScene(scene);
                                    mainWindow.show();
                                    gameOver();
                                } else {
                                    gameOverScreen.setTotalDeadEnemy((hardDeadEnemy));
                                    gameOverScreen.setGold(hardgold);
                                    gameOverScreen.setTime(hardtime);
                                    Scene scene = gameOverScreen.getScene();
                                    mainWindow.setScene(scene);
                                    mainWindow.show();
                                    gameOver();
                                }
                            }

                            if(gameModel.getGameDifficulties() == 1){
                                if (easywin){
                                    mainWindow.close();
                                    System.out.println("Close !");
                                    winScreen.setTotalDeadEnemy((easyDeadEnemy));
                                    winScreen.setGold(easygold);
                                    winScreen.setTime(easytime);

                                    Scene scene = winScreen.getScene();
                                    mainWindow.setScene(scene);
                                    mainWindow.show();
                                    win();
                                }
                            }

                            else if (gameModel.getGameDifficulties() == 2){
                                if (meddiumwin){
                                    mainWindow.close();
                                    System.out.println("Close !");
                                    winScreen.setTotalDeadEnemy((mediumDeadEnemy));
                                    winScreen.setGold(mediumgold);
                                    winScreen.setTime(mediumtime);

                                    Scene scene = winScreen.getScene();
                                    mainWindow.setScene(scene);
                                    mainWindow.show();
                                    win();
                                }
                            }

                            else{
                                if (hardwin){
                                    mainWindow.close();
                                    System.out.println("Close !");
                                    winScreen.setTotalDeadEnemy((hardDeadEnemy));
                                    winScreen.setGold(hardgold);
                                    winScreen.setTime(hardtime);
                                    Scene scene = winScreen.getScene();
                                    mainWindow.setScene(scene);
                                    mainWindow.show();
                                    win();
                                }
                            }
                        }
                    }));


    @Override
    public void start(Stage primaryStage) throws IOException {
        mainWindow = primaryStage;
        mainWindow.setTitle("Tower Defense Game - Go Duck");
        gameModel = new GameModel();
        initFirstScreen();
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }

    private void initFirstScreen() {
        gameOver = false;
        FirstScreen screen = new FirstScreen(width, length);
        Button quitButton = screen.getQuitButton();
        quitButton.setOnAction(e -> mainWindow.close());
        Button playButton = screen.getPlayButton();
        playButton.setOnAction(e -> initInitialConfigScreen());

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void initInitialConfigScreen() {
        InitialConfigScreen screen = new InitialConfigScreen(width, length);
        ToggleButton easyButton = screen.getEasyButton();
        easyButton.setOnAction(e -> gameModel.setGameDifficulties(1));
        ToggleButton mediumButton = screen.getMediumButton();
        mediumButton.setOnAction(e -> gameModel.setGameDifficulties(2));
        ToggleButton hardButton = screen.getHardButton();
        hardButton.setOnAction(e -> gameModel.setGameDifficulties(3));
        TextField usernameT = screen.getUsername();
        Button nextButton = screen.getNextButton();
        nextButton.setOnAction((ActionEvent event) -> {
            String username = usernameT.getCharacters().toString();
            boolean notEmptySpace = false;
            for (int i = 0; i < username.length(); i++) {
                if (username.charAt(i) != ' ') {
                    notEmptySpace = true;
                }
            }
            if (username == null || username.isEmpty() || !notEmptySpace) {
                ButtonType tryAnotherName = new
                        ButtonType("Try Another Username",
                        ButtonBar.ButtonData.OK_DONE);
                Alert empty = new Alert(Alert.AlertType.ERROR,
                        "Try Another Username!", tryAnotherName);
                empty.setTitle("Error");
                empty.setHeaderText("Invalid Input");
                empty.setContentText("Try Another Username!");
                empty.showAndWait(); //empty input error
            } else if (gameModel.getGameDifficulties() == 0) {
                ButtonType chooseDiff = new ButtonType("Choose Difficulties",
                        ButtonBar.ButtonData.OK_DONE);
                Alert empty = new Alert(Alert.AlertType.ERROR,
                        "Choose a difficult!", chooseDiff);
                empty.setTitle("Error");
                empty.setHeaderText("Invalid Difficulties");
                empty.setContentText("Choose a difficult!");
                empty.showAndWait(); //empty input error
            } else {
                if (gameModel.getGameDifficulties() == 1) {
                    initEasyGameScreen();
                } else if (gameModel.getGameDifficulties() == 2) {
                    initMediumGameScreen();
                } else {
                    initHardGameScreen();
                }
            }
        });

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void initHardGameScreen() {

        Scene scene = hardGameScreen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();


    }

    private void initMediumGameScreen() {
        Scene scene = mediumGameScreen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();

    }

    private void initEasyGameScreen() {
        Scene easyscene = easyGameScreen.getScene();
        easyGameScreen.setHealth();
        mainWindow.setScene(easyscene);
        mainWindow.show();

    }

    private void gameOver() {

        Button quitButton = gameOverScreen.getQuitButton();
        quitButton.setOnAction(e -> mainWindow.close());
        Button playButton = gameOverScreen.getPlayButton();
        playButton.setOnAction(e -> initFirstScreen());
        // playButton.setOnAction(e -> gameOver = false);
        Scene scene = gameOverScreen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
        easyGameScreen = new EasyGameScreen(width, length);
        mediumGameScreen = new MediumGameScreen(width, length);
        hardGameScreen = new HardGameScreen(width, length);

    }

    private void win(){
        Button quitButton = winScreen.getQuitButton();
        quitButton.setOnAction(e -> mainWindow.close());
        Button playButton = winScreen.getPlayButton();
        playButton.setOnAction(e -> initFirstScreen());
        Scene scene = winScreen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
        easyGameScreen = new EasyGameScreen(width, length);
        mediumGameScreen = new MediumGameScreen(width, length);
        hardGameScreen = new HardGameScreen(width, length);

    }

    public static void main(String[] args) {
        launch();
    }
}
