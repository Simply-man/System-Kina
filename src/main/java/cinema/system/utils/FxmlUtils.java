package cinema.system.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

// Klasa odpowiadająca za łądowanie naszych plików fxml
public class FxmlUtils {
    // Metoda służąca do zwaracania konkretnego Pane
    public static Pane fxmlLoader(String fxmlPath){
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        try {
            return loader.load();
        } catch (Exception e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        return null;
    }
}
