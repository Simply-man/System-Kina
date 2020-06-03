package cinema.system.controllers;

import cinema.system.database.models.Category;
import cinema.system.modelFx.AuthorFx;
import cinema.system.modelFx.FilmFx;
import cinema.system.modelFx.ListFilmsModel;
import cinema.system.utils.DialogsUtils;
import cinema.system.utils.expections.AppExpections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class ListFilmsController {
    @FXML
    private TableView<FilmFx> filmsTableView;
    @FXML
    private TableColumn<FilmFx, String> titleColumn;
    @FXML
    private TableColumn<FilmFx, String> descColumn;
    @FXML
    private TableColumn<FilmFx, AuthorFx> authorColumn;
    @FXML
    private TableColumn<FilmFx, Category> categoryColumn;
    @FXML
    private TableColumn<FilmFx, Number> ratingColumn;
    @FXML
    private TableColumn<FilmFx, LocalDate> releaseColumn;

    private ListFilmsModel listFilmsModel;

    @FXML
    void initialize(){
        this.listFilmsModel = new ListFilmsModel();
        try {
            this.listFilmsModel.init();
        } catch (AppExpections expections) {
            DialogsUtils.errorDialog(expections.getMessage());
        }

        this.filmsTableView.setItems(this.listFilmsModel.getFilmFxObservableList());
        this.titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        this.descColumn.setCellValueFactory(cellData -> cellData.getValue().descritpionProperty());
        this.ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        this.releaseColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
        this.authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorFxProperty());
        //this.categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryFxProperty());

    }
}
