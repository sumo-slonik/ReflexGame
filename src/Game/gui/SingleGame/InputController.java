package Game.gui.SingleGame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import Game.gui.SingleGame.SingleGameController.*;
import java.io.IOException;

public class InputController {

    @FXML
    Button ok;
    @FXML
    Slider elements;
    @FXML
    Slider maxTime;
    @FXML
    Slider attempts;
    public static int elementsOnScreen;
    public static int attemptsOfPlayer;
    public static int maxTimePerAttempt;

    public void getValuesFromSliders()
    {
        elementsOnScreen = (int) elements.getValue();
        attemptsOfPlayer = (int) attempts.getValue();
        maxTimePerAttempt = (int) maxTime.getValue();
    }

    private void closeInputWindow()
    {
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void openSoloGame() throws IOException {
        closeInputWindow();
        getValuesFromSliders();
        Stage soloGameWindow = new Stage();
        soloGameWindow.setTitle("Gra z komputerem");
        Parent root = FXMLLoader.load(getClass().getResource("SingleGame.fxml"));
        soloGameWindow.setScene(new Scene(root));
        soloGameWindow.show();
    }
}
