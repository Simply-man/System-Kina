package cinema.system.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;

// Klasa służąca do przetrzymywania okien dialogowych
public class DialogsUtils {

    // Metoda statyczna tworząca okno dialogowe o błędach
    public static void errorDialog(String error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Błąd!");
        errorAlert.setHeaderText("Uwaga coś poszło nie tak");

        Label label = new Label(error);
        errorAlert.getDialogPane().setContent(label);
        errorAlert.showAndWait();
    }
}