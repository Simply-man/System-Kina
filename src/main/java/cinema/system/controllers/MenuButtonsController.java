package cinema.system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class MenuButtonsController {

    public static final String FILMS_FXML = "/fxml/Films.fxml";
    public static final String LIST_FILMS_FXML = "/fxml/ListFilms.fxml";
    public static final String CATEGORY_FILMS_FXML = "/fxml/Category.fxml";
    public static final String ADD_FILMS_FXML = "/fxml/AddFilms.fxml";
    public static final String ADD_CATEGORY_FXML = "/fxml/AddCategory.fxml";

    // Odwołanie do pliku MainController
    @FXML
    private MainController mainController;

    // Odsyłacza grupy przycisków
    @FXML
    private ToggleGroup toggleButtons;

    // Metody do obsługi przycisków
    @FXML
    public void showFilms(ActionEvent actionEvent) {
        mainController.setCenter(FILMS_FXML);
    }

    @FXML
    public void showList(ActionEvent actionEvent) {
        mainController.setCenter(LIST_FILMS_FXML);
    }

    @FXML
    public void showCategory(ActionEvent actionEvent) {
        mainController.setCenter(CATEGORY_FILMS_FXML);
    }

    // Metoda obsługująca przycisk "Dodaj filmy"
    @FXML
    public void addFilms(ActionEvent actionEvent) {
        resetToggleButtons();
        mainController.setCenter(ADD_FILMS_FXML);
    }

    // Metoda obsługująca przycisk "Dodaj kategorię"
    @FXML
    public void addCategory(ActionEvent actionEvent) {
        resetToggleButtons();
        mainController.setCenter(ADD_CATEGORY_FXML);
    }

    // Sprawdzanie czy jeden z przycisków w grupie toggleButtons jest "wciśnięty"
    private void resetToggleButtons() {
        if(toggleButtons.getSelectedToggle() != null){
            toggleButtons.getSelectedToggle().setSelected(false);
        }
    }

    // Setter dla MenuButtonsController aby móc wyświetlić poszczególne dane w głównym oknie
    // (referencja do MainController)
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }



}
