package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Model.Appointments;
import Model.Contacts;
import Utilities.Alerts;
import Utilities.DateTimeHandler;
import javafx.collections.ObservableList;
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
 * The type Modify appointment.
 */
public class ModifyAppointment implements Initializable {
    /**
     * The constant appointmentToMod.
     */
    public static Appointments appointmentToMod = null;
    /**
     * The constant indexToMod.
     */
    public static int indexToMod = 0;
    /**
     * The Appt id.
     */
    public TextField apptID;
    /**
     * The Appt cust id.
     */
    public TextField apptCustID;
    /**
     * The Appt user id.
     */
    public ComboBox<Integer> apptUserID;
    /**
     * The Appt title.
     */
    public TextField apptTitle;
    /**
     * The Appt desc.
     */
    public TextField apptDesc;
    /**
     * The Appt type.
     */
    public TextField apptType;
    /**
     * The Appt contact.
     */
    public ComboBox<Contacts> apptContact;
    /**
     * The Appt date.
     */
    public DatePicker apptDate;
    /**
     * The Appt start time.
     */
    public ComboBox<LocalTime> apptStartTime;
    /**
     * The Appt end time.
     */
    public ComboBox<LocalTime> apptEndTime;
    /**
     * The Appt location.
     */
    public TextField apptLocation;
    /**
     * The Submit.
     */
    public Button Submit;
    /**
     * The Cancel.
     */
    public Button Cancel;

    /**
     * Sets appointment to mod.
     *
     * @param appointment the appointment
     * @param index       the index
     */
    public static void setAppointmentToMod(Appointments appointment, int index) {
        appointmentToMod = appointment;
        indexToMod = index;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptContact.setItems(DBContacts.getAllContacts());
        apptUserID.setItems(DBUsers.getAllUsers());
        apptDate.setValue(appointmentToMod.getApptStart().toLocalDate());
        apptStartTime.setItems(DateTimeHandler.setTimeList(apptDate.getValue()));
        apptEndTime.setItems(DateTimeHandler.setTimeList(apptDate.getValue()));
        apptID.setText(String.valueOf(appointmentToMod.getApptID()));
        apptCustID.setText(String.valueOf(appointmentToMod.getCustID()));
        apptUserID.setValue(appointmentToMod.getUserID());
        apptTitle.setText(appointmentToMod.getApptTitle());
        apptDesc.setText(appointmentToMod.getApptDescription());
        apptType.setText(appointmentToMod.getApptType());
        apptStartTime.setValue(appointmentToMod.getApptStart().toLocalTime());
        apptEndTime.setValue(appointmentToMod.getApptEnd().toLocalTime());
        apptContact.getSelectionModel().select(appointmentToMod.getApptContact());
        apptLocation.setText(appointmentToMod.getApptLocation());
    }

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
        } else if (apptLocation.getText().isEmpty()) {
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

    private boolean validateCustomer() {
        boolean b = false;
        if (DBCustomers.customerExists(Integer.parseInt(apptCustID.getText()))) {
            b = true;
        } else {
            Alerts.invalidData("\nThe chosen customer does not exist.\n");
        }
        return b;
    }

    private boolean validateTime() {
        boolean b = true;
        ObservableList<Appointments> custAppts = DBAppointments.getApptsByCustID(Integer.parseInt(apptCustID.getText()));
        for (Appointments i : custAppts) {
            LocalDateTime aStart = i.getApptStart();
            LocalDateTime aEnd = i.getApptEnd();
            LocalDateTime bStart = LocalDateTime.of(apptDate.getValue(), apptStartTime.getValue());
            LocalDateTime bEnd = LocalDateTime.of(apptDate.getValue(), apptEndTime.getValue());
            if (i.getApptID() != Integer.parseInt(apptID.getText()))
                if (aStart.isEqual(bStart) || aStart.isAfter(bStart) && aStart.isBefore(bEnd)) {
                    Alerts.timeOverlap();
                    b = false;
                } else if (aEnd.isEqual(bEnd) || aEnd.isBefore(bEnd) && aEnd.isAfter(bStart)) {
                    Alerts.timeOverlap();
                    b = false;
                } else if ((bStart.isAfter(aStart) && bEnd.isBefore(aEnd)) || (aStart.isEqual(bStart) && aEnd.isEqual(bEnd))) {
                    Alerts.timeOverlap();
                    b = false;
                }
        }

        return b;
    }

    /**
     * On submit.
     *
     * @param actionEvent the action event
     */
    public void onSubmit(ActionEvent actionEvent) {
        if (validateInput() && (validateCustomer() && validateTime())) {
            LocalDate date = apptDate.getValue();
            LocalTime startTime = apptStartTime.getValue();
            LocalTime endTime = apptEndTime.getValue();
            LocalDateTime startLDT = DateTimeHandler.startTime(date, LocalTime.from(startTime));
            LocalDateTime endLDT = DateTimeHandler.endTime(date, LocalTime.from(endTime));
            appointmentToMod = new Appointments(Integer.parseInt(apptID.getText()), apptTitle.getText(), apptDesc.getText(), apptLocation.getText(), apptContact.getValue(), startLDT, endLDT, apptType.getText(), Integer.parseInt(apptCustID.getText()), apptUserID.getValue());
            DBAppointments.modifyAppointment(appointmentToMod);
            MainScreen.appointments.set(indexToMod, appointmentToMod);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    /**
     * On cancel.
     *
     * @param actionEvent the action event
     */
    public void onCancel(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }


}
