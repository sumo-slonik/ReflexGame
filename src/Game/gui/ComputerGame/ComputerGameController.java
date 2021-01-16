package Game.gui.ComputerGame;

import Game.functionality.Board;
import Game.functionality.ComputerAI;
import Game.functionality.StatsToTxtSaver;
import Game.functionality.TimerEngine;
import Game.gui.GameController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ComputerGameController extends GameController {
    @FXML
    Button goToMenu;
    @FXML
    Button reset;
    Board board;
    @FXML
    Pane root;
    @FXML
    TextField roundsField;
    @FXML
    TextField stayedAttemptsField;
    @FXML
    TextField timerTextField;
    @FXML
    TextField AVGTime;
    @FXML
    public Button nextRoundButton;
    @FXML
    public TextField playerPointsField;
    @FXML
    public TextField computerPointsField;
    int posX = 1;
    int posY;
    int stayedAttempts;
    int rounds;
    @FXML
    Button saveToFile;
    TimerEngine timerEngine;
    ComputerAI computerAI = new ComputerAI(InputController.computerWinPercentVal, InputController.computerMakeDecisionTime, InputController.maxTimePerAttempt);

    public void initialize() {
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, getMousePosition);
        this.rounds = 0;
        roundsField.setText("" + rounds);
        timerEngine = new TimerEngine(InputController.maxTimePerAttempt, timerTextField, this, AVGTime, 11);

    }

    int playerPoints;
    int computerPoints;
    public EventHandler<MouseEvent> getMousePosition = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (rounds == 11) {
                nextRoundButton.setDisable(true);
                timerEngine.delTimer();
            }
            if (timerEngine.leftMilliSeconds > 100 && rounds < 11) {
                System.out.println("_______________________________");
                System.out.println(timerEngine.getLeftMilliSeconds());
                System.out.println("_______________________________");

                posX = (int) event.getX();
                posX = (int) ((int) posX / root.getWidth() * 10);
                posY = (int) event.getY();
                posY = (int) ((int) posY / root.getHeight() * 10);
                System.out.println(posX + "  " + posY);
                if (posX == Board.chosenX && posY == Board.chosenY) {
                    System.out.println("Wygranko");
                    nextRoundWin();
                } else {
                    nextRondLoss();
                }
            }
        }

    };

    public void closeSoloGameWindow() {
        Stage stage = (Stage) goToMenu.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void openMenu() throws IOException {
        closeSoloGameWindow();
        Stage menuWindow = new Stage();
        menuWindow.setTitle("Menu");
        Parent root = FXMLLoader.load(getClass().getResource("../MainMenu/MainMenu.fxml"));
        menuWindow.setScene(new Scene(root));
        menuWindow.show();
    }

    public void reset() {
        playerPoints = 0;
        playerPointsField.setText("" + playerPoints);
        computerPoints = 0;
        computerPointsField.setText("" + computerPoints);
        rounds = 0;
        board = new Board(10, root, 10);
        board.initialize(InputController.elementsOnScreen);
        System.out.println("Reset");
        roundsField.setText("" + rounds);
        timerEngine.makeNewTimerReset(InputController.maxTimePerAttempt);
    }

    public void failAttempt() {
        float passedTime = this.timerEngine.getTimePass();
        float computerReactionTime = this.computerAI.computerMakeDecision();
        boolean computerDecision = computerAI.isDecisionCorrect();
        System.out.println("======================");
        System.out.println(passedTime);
        System.out.println(InputController.maxTimePerAttempt);
        System.out.println("======================");
        if (computerReactionTime <= passedTime) {
            if (computerDecision) {
                computerGetPoint();
            } else {
                computerMistake();
            }
        } else {
            playerMistake();
        }
    }

    public void nextRound() {
        rounds += 1;
        roundsField.setText("" + rounds);
        board = new Board(10, root, 10);
        board.initialize(InputController.elementsOnScreen);
        playerPointsField.setText("" + playerPoints);
        computerPointsField.setText("" + computerPoints);
    }

    public void nextRoundWin() {
        float passedTime = this.timerEngine.getTimePass();
        float computerReactionTime = this.computerAI.computerMakeDecision();
        boolean computerDecision = computerAI.isDecisionCorrect();
        if (passedTime > computerReactionTime) {
            if (computerDecision) {
                computerGetPoint();
            } else {
                computerMistake();
            }
        } else {
            playerGetPoint();
        }
        timerEngine.makeNewTimer(InputController.maxTimePerAttempt);
        nextRound();
    }

    public void nextRondLoss() {
        failAttempt();
        timerEngine.makeNewTimerFail(InputController.maxTimePerAttempt);
        nextRound();
    }

    public String test() {
        return "to to" + this.posX;
    }

    @FXML
    public void start() {
        this.timerEngine.startTimer();
    }

    @FXML
    public void nextRoundAfterTime() {

        this.nextRoundButton.setDisable(true);
        timerEngine.delTimer();
        timerEngine = new TimerEngine(InputController.maxTimePerAttempt, timerTextField, this, AVGTime, 11);
        float computerReactionTime = this.computerAI.computerMakeDecision();
        boolean computerDecision = computerAI.isDecisionCorrect();
        System.out.println("czas minoł");
        if (computerReactionTime <= InputController.maxTimePerAttempt) {
            if (computerDecision) {
                computerGetPoint();
            } else {
                computerMistake();
            }
        }
        timerEngine.makeNewTimerFail(InputController.maxTimePerAttempt);
        nextRound();
    }

    public void timePass() {
        nextRoundButton.setDisable(false);
    }

    public void computerGetPoint() {
        this.computerPoints++;
    }

    public void playerGetPoint() {
        this.playerPoints++;
    }

    public void computerMistake() {
        this.playerPoints++;
        this.computerPoints--;
    }

    public void playerMistake() {
        this.playerPoints++;
        this.computerPoints--;
    }

    public void makeWinnerGreen() {
        if (playerPoints > computerPoints) {
            playerPointsField.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        } else if (playerPoints < computerPoints) {
            computerPointsField.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        } else {
            playerPointsField.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
            computerPointsField.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        }
    }

    public int getRounds() {
        return this.rounds;
    }

    @FXML
    public void saveStatsToTXT() throws FileNotFoundException {
        StatsToTxtSaver saver = new StatsToTxtSaver();
        String stats = "" + "punkty gracza :" + playerPoints + "  " + "punkty komputera :" + computerPoints+" średni czas reakcji gracza :"+AVGTime.getText();
        String name = "" + rounds;
        saver.saveStats(stats, name);

    }
}
