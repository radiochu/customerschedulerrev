package Utilities;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * The type Alerts.
 */
public class Alerts {

    /**
     * The constant a.
     */
    public static final Alert a = new Alert(Alert.AlertType.NONE);

    /**
     * Upcoming appointments.
     *
     * @param upcoming the upcoming
     */
    public static void upcomingAppointments(int upcoming) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(upcoming + " Upcoming appointments in the next 15 minutes.");
        a.show();
    }

    /**
     * Invalid data.
     *
     * @param message the message
     */
    public static void invalidData(String message) {
        a.setAlertType(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText("You have entered invalid data: " + message + "Please check your entries and try again.");
        a.show();
    }

    /**
     * Delete customer boolean.
     *
     * @return the boolean
     */
    public static Boolean deleteCustomer() {
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Are you sure you want to delete the selected customer?\nNote: This will also delete any appointments scheduled\n for this customer.");
        Optional<ButtonType> result = a.showAndWait();
        return result.get() == ButtonType.OK;
    }

    /**
     * Existing appts.
     */
    public static void existingAppts() {
        a.setAlertType(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText("You cannot delete a customer with scheduled appointments.\nPlease delete any existing appointments before deleting\nthis customer.");
        a.show();
    }

    /**
     * No selection.
     */
    public static void noSelection() {
        a.setAlertType(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText("Nothing selected! Please make a selection first.");
        a.show();
    }

    /**
     * Customer deleted.
     */
    public static void customerDeleted() {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText("Selected customer has been deleted.");
        a.show();
    }

    /**
     * Delete appointment boolean.
     *
     * @return the boolean
     */
    public static Boolean deleteAppointment() {
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Are you sure you want to delete the selected appointment?");
        Optional<ButtonType> result = a.showAndWait();
        return result.get() == ButtonType.OK;
    }

    /**
     * Cancel without saving.
     *
     * @param actionEvent the action event
     */
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

    /**
     * Exit application.
     */
    public static void exitApplication() {
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    /**
     * Appt deleted.
     *
     * @param apptToDelete the appt to delete
     * @param apptType     the appt type
     */
    public static void apptDeleted(int apptToDelete, String apptType) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText("Appointment " + apptToDelete + " of type " + apptType + " has been deleted.");
        a.show();
    }

    public static void timeOverlap() {
        a.setAlertType(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText("The proposed appointment overlaps an existing appointment\n for this customer. Please choose another time.");
        a.show();
    }
}