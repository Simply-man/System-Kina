package cinema.system.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML
    private BorderPane MainBorderPain;

    @FXML
    private TextField SearchInput;

    @FXML
    private Button SerachButton;


    @FXML
    private void initialize(){
        SearchMenuController.setMainController(this);
    }

}
