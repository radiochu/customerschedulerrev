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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ModifyAppointment implements Initializable {
    public TextField apptID;
    public TextField apptCustID;
    public ComboBox<Integer> apptUserID;
    public TextField apptTitle;
    public TextField apptDesc;
    public TextField apptType;
    public ComboBox<String> apptContact;
    public DatePicker apptDate;
    public ComboBox<LocalTime> apptStartTime;
    public ComboBox<LocalTime> apptEndTime;
    public TextField apptLocation;
    public Button Submit;
    public Button Cancel;

    public static Appointments appointmentToMod = null;
    public static int indexToMod = 0;



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
        apptContact.setValue(appointmentToMod.getApptContact());
        apptLocation.setText(appointmentToMod.getApptLocation());
    }

    private boolean validateInput() {
        boolean b = false;

        if (apptCustID.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer ID.\n");
        }
        else if (apptUserID.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a user.\n");
        }
        else if (apptTitle.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input an appointment title.\n");
        }
        else if (apptDesc.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input an appointment description.\n");
        }
        else if (apptType.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input an appointment type.\n");
        }
        else if (apptLocation.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input an appointment location.\n");
        }
        else if (apptDate.getValue() == null) {
            Alerts.invalidData("\nYou must choose an appointment date.\n");
        }
        else if (apptStartTime.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a start time.\n");
        }
        else if (apptEndTime.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose an end time.\n");
        }
        else if (apptContact.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a contact.\n");
        }
        else {
            b = true;
        }
        return b;
    }

    private boolean validateCustomer(int custID) {
        boolean b = false;
        if (DBCustomers.customerExists(custID)) {
            b = true;
        }
        else {
            Alerts.invalidData("\nThe chosen customer does not exist.\n");
        }
        return b;
    }

    public void onSubmit(ActionEvent actionEvent) {
        if (validateInput() && validateCustomer(Integer.parseInt(apptCustID.getText()))) {
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

    public void onCancel(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }


}
