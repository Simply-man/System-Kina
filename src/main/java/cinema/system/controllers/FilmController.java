package cinema.system.controllers;

import cinema.system.modelFx.AuthorFx;
import cinema.system.modelFx.CategoryFx;
import cinema.system.modelFx.FilmModel;
import cinema.system.utils.DialogsUtils;
import cinema.system.utils.expections.AppExpections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FilmController {

    @FXML
    private Button addButton;
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
        } catch (AppExpections appExpections) {
            DialogsUtils.errorDialog(appExpections.getMessage());
        }
        bindings();
        validation();
    }

    // walidacja dla przycisku "Dodaj" w dodaj filmy
    private void validation() {
        this.addButton.disableProperty().bind(this.categoryComboBox.valueProperty().isNull()
                                        .or(this.authorComboBox.valueProperty().isNull())
                                        .or(this.titleTextField.textProperty().isEmpty())
                                        .or(this.descTextArea.textProperty().isEmpty())
                                        .or(this.releaseDatePicker.valueProperty().isNull()));
    }

    private void bindings() {
        //powiazanie list z combobox
        this.categoryComboBox.setItems(this.filmModel.getCategoryFxObservableList());
        this.authorComboBox.setItems(this.filmModel.getAuthorFxObservableList());

        //odwolanie sie do objectProperty i pobieranie danych
        this.authorComboBox.valueProperty().bindBidirectional(this.filmModel.getFilmFxObjectProperty().authorFxProperty());
        this.categoryComboBox.valueProperty().bindBidirectional(this.filmModel.getFilmFxObjectProperty().categoryFxProperty());
        this.titleTextField.textProperty().bindBidirectional(this.filmModel.getFilmFxObjectProperty().titleProperty());
        this.descTextArea.textProperty().bindBidirectional(this.filmModel.getFilmFxObjectProperty().descriptionProperty());
        this.ratingSlider.valueProperty().bindBidirectional(this.filmModel.getFilmFxObjectProperty().ratingProperty());
        this.releaseDatePicker.valueProperty().bindBidirectional(this.filmModel.getFilmFxObjectProperty().releaseDateProperty());
    }

    //przycisk - dodanie do bazy
    public void addFilmOnAction() {
        try {
            this.filmModel.saveFilmInDatabase();
            clearFields();
        } catch (AppExpections appExpections) {
            DialogsUtils.errorDialog(appExpections.getMessage());
        }
    }

    private void clearFields() {
        this.authorComboBox.getSelectionModel().clearSelection();
        this.categoryComboBox.getSelectionModel().clearSelection();
        this.titleTextField.clear();
        this.descTextArea.clear();
        this.ratingSlider.setValue(1);
        this.releaseDatePicker.getEditor().clear();
    }
}
