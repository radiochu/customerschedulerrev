package Utilities;

import DBAccess.DBAppointments;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class Alerts {

    public static Alert a = new Alert(Alert.AlertType.NONE);

    public static void upcomingAppointments(int user) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(DBAppointments.getApptsByUserID(user).size() + " Upcoming appointments in the next 15 minutes");
        a.show();
    }

    public static void invalidData(String message) {
        a.setAlertType(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText("You have entered invalid data: " + message + "Please check your entries and try again.");
        a.show();
    }

    public static void cancelWithoutSaving(ActionEvent actionEvent) {
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Quit without saving changes?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    public static void exitApplication() {
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}