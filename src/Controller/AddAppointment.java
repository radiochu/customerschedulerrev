package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Model.Appointments;
import Utilities.Alerts;
import Utilities.DateTimeHandler;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;


/**
 * Controller that handles the logic for the Add Appointment screen based on addAppointment.fxml.
 */
public class AddAppointment implements Initializable {
    /**
     * Appointment ID.
     */
    public TextField apptID;
    /**
     * Customer ID for the customer associated with this appointment.
     */
    public TextField apptCustID;
    /**
     * User ID for the user associated with this appointment.
     */
    public ComboBox<Integer> apptUserID;
    /**
     * Appointment title.
     */
    public TextField apptTitle;
    /**
     * Appointment description.
     */
    public TextField apptDesc;
    /**
     *
     * Appointment type.
     */
    public TextField apptType;
    /**
     * Appointment location.
     */
    public TextField apptLoc;
    /**
     * Appointment contact.
     */
    public ComboBox<String> apptContact;
    /**
     * Appointment date.
     */
    public DatePicker apptDate;
    /**
     * Appointment start time.
     */
    public ComboBox<LocalTime> apptStartTime;
    /**
     * Appointment end time.
     */
    public ComboBox<LocalTime> apptEndTime;
    /**
     * Button to submit form elements.
     */
    public Button Submit;
    /**
     * Button to cancel adding the customer and exit the screen.
     */
    public Button Cancel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptContact.setItems(DBContacts.getAllContacts());
        apptUserID.setItems(DBUsers.getAllUsers());
        apptDate.setValue(LocalDate.now());
        apptStartTime.setItems(DateTimeHandler.setTimeList(apptDate.getValue()));
        apptEndTime.setItems(DateTimeHandler.setTimeList(apptDate.getValue()));
    }

    /**
     * Validates the data entered into the add appointment fields. If any field is found invalid, the user is alerted
     * via a custom notification from the Alerts class, populated with the provided string to explain the error.
     * @return boolean b; false if information is not valid, true if it is.
     */
    private boolean validateInput() {
        boolean b = false;

        if (apptCustID.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer ID.\n");
        } else if (apptUserID.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a user.\n");
        } else if (apptTitle.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input an appointment title.\n");
        } else if (apptDesc.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input an appointment description.\n");
        } else if (apptType.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input an appointment type.\n");
        } else if (apptLoc.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input an appointment location.\n");
        } else if (apptDate.getValue() == null) {
            Alerts.invalidData("\nYou must choose an appointment date.\n");
        } else if (apptStartTime.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a start time.\n");
        } else if (apptEndTime.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose an end time.\n");
        } else if (apptContact.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a contact.\n");
        } else {
            b = true;
        }
        return b;
    }

    /**
     * Checks to see if the customer entered for the appointment actually exists. Provides an alert
     * from the Alerts class if the customer does not exist.
     * @param custID The customer ID to check for in the database.
     * @return boolean b; returns false if customer does not exist, true if the customer is found.
     */
    private boolean validateCustomer(int custID) {
        boolean b = false;
        if (DBCustomers.customerExists(custID)) {
            b = true;
        } else {
            Alerts.invalidData("\nThe chosen customer does not exist.\n");
        }
        return b;
    }

    /**
     * Method to handle saving the new appointment. After validating input and checking to be sure the customer entered exists,
     * reads in data from all fields and creates a new Appointments object to pass to the DBAppointments class for adding to the database.
     * The database transaction will return the ID for the new appointment, which will be set just before the object is
     * added to the list of appointments in the MainScreen controller.
     *
     * @param actionEvent Used to identify the window from which the save was triggered to close it on a successful execution.
     */
    public void onSaveButton(ActionEvent actionEvent) {
        if (validateInput() && validateCustomer(Integer.parseInt(apptCustID.getText()))) {
            LocalDate date = apptDate.getValue();
            LocalTime startTime = LocalTime.from(apptStartTime.getValue());
            LocalTime endTime = LocalTime.from(apptEndTime.getValue());
            LocalDateTime startLDT = DateTimeHandler.startTime(date, startTime);
            LocalDateTime endLDT = DateTimeHandler.endTime(date, endTime);
            Appointments apptToAdd = new Appointments(0, apptTitle.getText(), apptDesc.getText(), apptLoc.getText(), apptContact.getValue(), startLDT, endLDT, apptType.getText(), Integer.parseInt(apptCustID.getText()), apptUserID.getValue());
            apptToAdd.setApptID(DBAppointments.addAppointment(apptToAdd));
            MainScreen.appointments.add(apptToAdd);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Confirms that the user wants to cancel adding the new appointment by providing an alert from the Alerts class.
     *
     * @param actionEvent Passed to a method in the Alerts class; used to close the window if the user confirms they want
     *                    to close without saving.
     */
    public void onCancel(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }
}
