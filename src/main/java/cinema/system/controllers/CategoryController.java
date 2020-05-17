package cinema.system.controllers;

import cinema.system.modelFx.CategoryFx;
import cinema.system.modelFx.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CategoryController {

    // Przyciski oraz pola z template AddCategory
    @FXML
    private TextField categoryText;
    @FXML
    private Button categoryButton;
    @FXML
    private Button deleteCategoryButton;
    @FXML
    private ComboBox<CategoryFx> categoryComboBox;

    // Odwołanie do CategoryModel
    private CategoryModel categoryModel;

    @FXML
    public void initialize(){
        this.categoryModel = new CategoryModel();
        this.categoryModel.init();
        this.categoryComboBox.setItems(this.categoryModel.getCategoryList());
        initBindings();
    }

    private void initBindings() {
        // Wyłączenie przycisków gdy pola categoryText oraz categoryComboBox są puste
        this.categoryButton.disableProperty().bind(categoryText.textProperty().isEmpty());
        this.deleteCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
    }

    // Metody obsługujące przyciski należące do AddCategory.fxml
    public void addCategoryOnAction(ActionEvent actionEvent) {
        // Dodawanie wpisanego rekordu w pole TextField do bazy danych
        categoryModel.saveCategoryInDatabase(categoryText.getText());
        categoryText.clear();
    }

    // Usunięcie danej katerogii poprzez przycisk "Usuń"
    public void deleteCategoryOnAction(ActionEvent actionEvent) {
        this.categoryModel.deleteCategoryById();
    }

    // Przekazanie konkretnie wybranej wartości z ComboBoxa do metody setCategory
    public void deleteFromComboBoxOnAction(ActionEvent actionEvent) {
        this.categoryModel.setCategory(this.categoryComboBox.getSelectionModel().getSelectedItem());
    }
}
