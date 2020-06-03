package cinema.system.controllers;

import cinema.system.modelFx.CategoryFx;
import cinema.system.modelFx.CategoryModel;
import cinema.system.utils.DialogsUtils;
import cinema.system.utils.expections.AppExpections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CategoryController {

    // Przyciski oraz pola z template AddCategory
    @FXML
    private TextField categoryText;
    @FXML
    private Button categoryButton;
    @FXML
    private Button deleteCategoryButton;
    @FXML
    private Button editCategoryButton;
    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    @FXML
    private TreeView<String> categoryTreeView;

    // Odwołanie do CategoryModel
    private CategoryModel categoryModel;

    @FXML
    public void initialize(){
        this.categoryModel = new CategoryModel();
        try {
            this.categoryModel.init();
        } catch (AppExpections appExpections) {
            DialogsUtils.errorDialog(appExpections.getMessage());
        }
        this.categoryComboBox.setItems(this.categoryModel.getCategoryList());
        this.categoryTreeView.setRoot(this.categoryModel.getRoot());
        initBindings();
    }

    private void initBindings() {
        // Wyłączenie przycisków gdy pola categoryText oraz categoryComboBox są puste
        this.categoryButton.disableProperty().bind(categoryText.textProperty().isEmpty());
        this.deleteCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
        this.editCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
    }

    // Metody obsługujące przyciski należące do AddCategory.fxml
    public void addCategoryOnAction(ActionEvent actionEvent) {
        // Dodawanie wpisanego rekordu w pole TextField do bazy danych
        try {
            categoryModel.saveCategoryInDatabase(categoryText.getText());
        } catch (AppExpections appExpections) {
            DialogsUtils.errorDialog(appExpections.getMessage());
        }
        categoryText.clear();
    }

    // Usunięcie danej katerogii poprzez przycisk "Usuń"
    public void deleteCategoryOnAction(ActionEvent actionEvent) {
        try {
            this.categoryModel.deleteCategoryById();
        } catch (AppExpections appExpections) {
            DialogsUtils.errorDialog(appExpections.getMessage());
        }
    }

    // Przekazanie konkretnie wybranej wartości z ComboBoxa do metody setCategory
    public void deleteFromComboBoxOnAction(ActionEvent actionEvent) {
        this.categoryModel.setCategory(this.categoryComboBox.getSelectionModel().getSelectedItem());
    }

    // Zmiana nazwy kategori podczas edycji
    public void onActionEditCategory(ActionEvent actionEvent) {
        String newCategoryName = DialogsUtils.editDialog(this.categoryModel.getCategory().getName());
        if(newCategoryName != null){
            this.categoryModel.getCategory().setName(newCategoryName);
            try {
                this.categoryModel.updateCategoryInDataBase();
            } catch (AppExpections appExpections) {
                DialogsUtils.errorDialog(appExpections.getMessage());
            }
        }
    }
}
