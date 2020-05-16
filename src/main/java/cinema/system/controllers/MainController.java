package cinema.system.controllers;

import cinema.system.utils.FxmlUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    // Głównego okno w celu sterowania
    @FXML
    private BorderPane borderPane;

    // Nasz kontroler z przyciskami
    @FXML
    private MenuButtonsController menuButtonsController;

    // Metoda uruchamiana się zaraz po wywołaniu się konstruktora
    @FXML
    private void initialize(){
        menuButtonsController.setMainController(this);
    }

    // Meotda centrująca nasz template w głównym oknie
    public void setCenter(String fxmlpath){
        borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlpath));
    }


}
