package cinema.system.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

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

    public static void dialogsAboutApp(){
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("O aplikacji");
        informationAlert.setHeaderText("Kino");
        informationAlert.setContentText("Aplikacja prezentująca bibloteke filmową.");
        informationAlert.showAndWait();
    }

    public static Optional<ButtonType> dialogsConfirmExit(){
        Alert confirmexit = new Alert(Alert.AlertType.CONFIRMATION);
        confirmexit.setTitle("Wyjscie");
        confirmexit.setHeaderText("Czy na pewno chcesz wyjsc ?");
        Optional<ButtonType> result = confirmexit.showAndWait();
        return result;
    }
    //EDYCJA
    //WYSWIETLANIE DIALOGOW PO WCISNIECIU PRZYCISKU
    public static String editDialog(String value){
        TextInputDialog dialog = new TextInputDialog(value);
        dialog.setTitle("Edycja");
        dialog.setHeaderText("Edytuj");
        dialog.setContentText("Wprowadź nową nazwe:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        return null;
    }
}