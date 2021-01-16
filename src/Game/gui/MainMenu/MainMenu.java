package Game.gui.MainMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {
    @FXML
    Button soloGame;
    @FXML
    Button computerGame;

    @FXML
    private void openSoloGame() throws IOException {
        closeMenuWindow();
        Stage soloGameWindow = new Stage();
        soloGameWindow.setTitle("Gra solo konfiguracja");
        Parent root = FXMLLoader.load(getClass().getResource("../SingleGame/input.fxml"));
        soloGameWindow.setScene(new Scene(root));
        soloGameWindow.show();
    }
    @FXML
    private void openComputerGame() throws IOException {
        closeMenuWindow();
        Stage soloGameWindow = new Stage();
        soloGameWindow.setTitle("Gra z komputerem konfiguracja");
        Parent root = FXMLLoader.load(getClass().getResource("../ComputerGame/inputComputer.fxml"));
        soloGameWindow.setScene(new Scene(root));
        soloGameWindow.show();
    }
    @FXML
    private void closeMenuWindow()
    {
        Stage stage = (Stage) soloGame.getScene().getWindow();
        stage.close();
    }
}
