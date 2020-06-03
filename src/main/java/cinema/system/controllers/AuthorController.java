package cinema.system.controllers;

import cinema.system.modelFx.AuthorFx;
import cinema.system.modelFx.AuthorModel;
import cinema.system.utils.DialogsUtils;
import cinema.system.utils.expections.AppExpections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class AuthorController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private Button addButton;
    @FXML
    private TableView<AuthorFx> authorTableView;
    @FXML
    private TableColumn<AuthorFx, String> nameColumn;
    @FXML
    private TableColumn<AuthorFx, String> surnameColumn;

    private AuthorModel authorModel;

    public void initialize(){
        this.authorModel = new AuthorModel();
        try {
            this.authorModel.init();
        } catch (AppExpections expections) {
            DialogsUtils.errorDialog(expections.getMessage());
        }
        this.authorModel.authorFxObjectPropertyProperty().get().nameProperty().bind(this.nameTextField.textProperty());
        this.authorModel.authorFxObjectPropertyProperty().get().surnameProperty().bind(this.surnameTextField.textProperty());
        this.addButton.disableProperty().bind(this.nameTextField.textProperty().isEmpty().or(this.surnameTextField.textProperty().isEmpty()));

        this.authorTableView.setItems(this.authorModel.getAuthorFxObservableList());
        this.nameColumn.setCellValueFactory(cellData-> cellData.getValue().nameProperty());
        this.surnameColumn.setCellValueFactory(cellData-> cellData.getValue().surnameProperty());

    }

    public void addAuthorOnAction() {
        try {
            this.authorModel.saveAuthorInDatabase();
        } catch (AppExpections expections) {
            DialogsUtils.errorDialog(expections.getMessage());
        }
        this.nameTextField.clear();
        this.surnameTextField.clear();
    }
}
