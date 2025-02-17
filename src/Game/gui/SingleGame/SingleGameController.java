package Game.gui.SingleGame;

import Game.functionality.Board;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SingleGameController extends GameController {
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
    public TextField playerPoints;
    @FXML
    Button saveToTxt;
    int posX = 1;
    int posY;
    int stayedAttempts;
    int rounds;
    TimerEngine timerEngine;

    public void initialize() {
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, getMousePosition);
        this.stayedAttempts = InputController.attemptsOfPlayer;
        this.rounds = 0;
        stayedAttemptsField.setText("" + stayedAttempts);
        roundsField.setText("" + rounds);
        timerEngine = new TimerEngine(InputController.maxTimePerAttempt, timerTextField, this, AVGTime);

    }

    public EventHandler<MouseEvent> getMousePosition = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (stayedAttempts >= 0) {
                nextRoundButton.setDisable(true);
                timerEngine.delTimer();
            }
            if (timerEngine.leftMilliSeconds > 100 && stayedAttempts > 0) {
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
        timerEngine.delTimer();
    }

    public void reset() {
        stayedAttempts = InputController.attemptsOfPlayer;
        board = new Board(10, root, InputController.attemptsOfPlayer);
        board.initialize(InputController.elementsOnScreen);
        System.out.println("Reset");
        stayedAttemptsField.setText("" + stayedAttempts);
        roundsField.setText("" + rounds);
        timerEngine.makeNewTimerReset(InputController.maxTimePerAttempt);
    }

    public void failAttempt() {
        this.stayedAttempts -= 1;
    }

    public void nextRound() {
        rounds += 1;
        stayedAttemptsField.setText("" + stayedAttempts);
        roundsField.setText("" + rounds);
        board = new Board(10, root, InputController.attemptsOfPlayer);
        board.initialize(InputController.elementsOnScreen);
        playerPoints.setText("" + (rounds - (InputController.attemptsOfPlayer - stayedAttempts)));

    }

    public void nextRoundWin() {
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
        timerEngine = new TimerEngine(InputController.maxTimePerAttempt, timerTextField, this, AVGTime);
        nextRondLoss();
    }

    public void timePass() {
        nextRoundButton.setDisable(false);
    }

    @FXML
    public void saveStatsToTXT() throws FileNotFoundException {
        StatsToTxtSaver saver = new StatsToTxtSaver();
        String stats = "" + "punkty gracza :" + playerPoints.getText()+"czas reakcji gracza :"+AVGTime.getText();
        String name = "" + rounds;
        saver.saveStats(stats, name);

    }
}
