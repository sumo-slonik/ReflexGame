package Game.gui.ComputerGame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;

public class InputController {

    @FXML
    Button ok;
    @FXML
    Slider elementsSlider;
    @FXML
    Slider maxTimeSlider;
    @FXML
    Slider computerMakeDecisionSlider;
    @FXML
    Slider computerWinPercentSlider;

    public static int elementsOnScreen;
    public static int maxTimePerAttempt;
    public static int computerWinPercentVal;
    public static int computerMakeDecisionTime;

    public void getValuesFromSliders()
    {
        elementsOnScreen = (int) elementsSlider.getValue();
        computerWinPercentVal = (int) computerWinPercentSlider.getValue();
        maxTimePerAttempt = (int) maxTimeSlider.getValue();
        computerMakeDecisionTime = (int) computerMakeDecisionSlider.getValue();
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
        soloGameWindow.setTitle("Gra solo");
        Parent root = FXMLLoader.load(getClass().getResource("ComputerGame.fxml"));
        soloGameWindow.setScene(new Scene(root));
        soloGameWindow.show();
    }
}
