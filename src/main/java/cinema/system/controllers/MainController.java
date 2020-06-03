package cinema.system.controllers;

import cinema.system.utils.DialogsUtils;
import cinema.system.utils.FxmlUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

import java.util.Optional;

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

    // PLIK
    // Zamkniecie aplikacji
    public void closeApp() {
        Optional<ButtonType> result = DialogsUtils.dialogsConfirmExit();
        if(result.get()== ButtonType.OK){
            Platform.exit();
            System.exit(0);
        }

    }

    // EDYCJA
    // Wybor stylu 1 - Modena
    public void setStyle1() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }
    // Wybor stylu 2 - Caspian
    public void setStyle2() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    // POMOC - informacja o kinie
    public void about() {
        DialogsUtils.dialogsAboutApp();
    }
}
