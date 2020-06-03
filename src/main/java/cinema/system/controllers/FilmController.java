package cinema.system.controllers;

import cinema.system.modelFx.AuthorFx;
import cinema.system.modelFx.CategoryFx;
import cinema.system.modelFx.FilmModel;
import cinema.system.utils.DialogsUtils;
import cinema.system.utils.expections.AppExpections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FilmController {
    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    @FXML
    private ComboBox<AuthorFx> authorComboBox;
    @FXML
    private TextArea descTextArea;
    @FXML
    private Slider ratingSlider;
    @FXML
    private DatePicker releaseDatePicker;
    @FXML
    private TextField titleTextField;

    private FilmModel filmModel;

    @FXML
    public void initialize(){
        this.filmModel = new FilmModel();
        try {
            this.filmModel.init();
        } catch (AppExpections expections) {
            DialogsUtils.errorDialog(expections.getMessage());
        }
        bindings();

    }

    private void bindings() {
        //powiazanie list z combobox
        this.categoryComboBox.setItems(this.filmModel.getCategoryFxObservableList());
        this.authorComboBox.setItems(this.filmModel.getAuthorFxObservableList());
        //odwolanie sie do objectProperty i pobieranie danych
        this.filmModel.getFilmFxObjectProperty().categoryFxProperty().bind(this.categoryComboBox.valueProperty());
        this.filmModel.getFilmFxObjectProperty().authorFxProperty().bind(this.authorComboBox.valueProperty());
        this.filmModel.getFilmFxObjectProperty().titleProperty().bind(this.titleTextField.textProperty());
        this.filmModel.getFilmFxObjectProperty().descritpionProperty().bind(this.descTextArea.textProperty());
        this.filmModel.getFilmFxObjectProperty().ratingProperty().bind(this.ratingSlider.valueProperty());
        this.filmModel.getFilmFxObjectProperty().releaseDateProperty().bind(this.releaseDatePicker.valueProperty());
    }

    //przycisk - dodanie do bazy
    public void addFilmOnAction() {
        try {
            this.filmModel.saveFilmInDatabase();
        } catch (AppExpections expections) {
            DialogsUtils.errorDialog(expections.getMessage());
        }
    }
}
