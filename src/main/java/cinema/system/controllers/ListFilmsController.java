package cinema.system.controllers;

import cinema.system.modelFx.AuthorFx;
import cinema.system.modelFx.CategoryFx;
import cinema.system.modelFx.FilmFx;
import cinema.system.modelFx.ListFilmsModel;
import cinema.system.utils.DialogsUtils;
import cinema.system.utils.expections.AppExpections;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private TableColumn<FilmFx, CategoryFx> categoryColumn;
    @FXML
    private TableColumn<FilmFx, Number> ratingColumn;
    @FXML
    private TableColumn<FilmFx, LocalDate> releaseColumn;
    @FXML
    private TableColumn<FilmFx, FilmFx> deleteColumn;

    private ListFilmsModel listFilmsModel;



    @FXML
    void initialize(){
        this.listFilmsModel = new ListFilmsModel();
        try {
            this.listFilmsModel.init();
        } catch (AppExpections expections) {
            DialogsUtils.errorDialog(expections.getMessage());
        }

        // bindowanie kolumn z FilmTableView z danymi z bazy danych
        this.filmsTableView.setItems(this.listFilmsModel.getFilmFxObservableList());
        this.titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        this.descColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        this.ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        this.releaseColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
        this.authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorFxProperty());
        this.categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryFxProperty());
        this.deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        // Tworzenie ikonki delete dla każdej kolumny
        this.deleteColumn.setCellFactory(param -> new TableCell<FilmFx, FilmFx>(){
            // Budowanie konkretnej komórki w kolumnie
            Button button = createDeleteButton();
            @Override
            protected void updateItem(FilmFx filmFx, boolean empty) {
                super.updateItem(filmFx, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                setGraphic(button);

                button.setOnAction(event ->{
                    try {
                        listFilmsModel.deleteFilm(filmFx);
                    } catch (AppExpections appExpections) {
                        DialogsUtils.errorDialog(appExpections.getMessage());
                    }
                });
            }
        });
    }

    private Button createDeleteButton(){
        Button button = new Button();
        Image image = new Image(this.getClass().getResource("/icons/delete.png").toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }
}
