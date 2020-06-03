package cinema.system;

import cinema.system.database.dbuitls.DbManager;
import cinema.system.utils.FillDatabase;
import cinema.system.utils.FxmlUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static final String BORDER_PAIN_MAIN_FXML = "/fxml/BorderPainMain.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // ładowanie naszego BorderPaneMain
        Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PAIN_MAIN_FXML);

        // Tworzenie sceny oraz załodowanie naszego BorderPainMain
        Scene scene = new Scene(borderPane);

        // Dodanie sceny do naszego głównego okna (Stage)
        stage.setScene(scene);
        stage.setTitle("Cinema System");
        stage.show();

        // Inicjalziacja bazy danych
        DbManager.initDatabase();
        FillDatabase.fillDatabase();
    }
}
