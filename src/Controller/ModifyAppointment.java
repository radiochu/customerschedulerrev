package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
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
        apptStartTime.setItems(DateTimeHandler.setTimeList());
        apptEndTime.setItems(DateTimeHandler.setTimeList());
        apptID.setText(String.valueOf(appointmentToMod.getApptID()));
        apptCustID.setText(String.valueOf(appointmentToMod.getCustID()));
        apptUserID.setValue(appointmentToMod.getUserID());
        apptTitle.setText(appointmentToMod.getApptTitle());
        apptDesc.setText(appointmentToMod.getApptDescription());
        apptType.setText(appointmentToMod.getApptType());
        apptStartTime.setValue(appointmentToMod.getApptStart().toLocalTime());
        apptEndTime.setValue(appointmentToMod.getApptEnd().toLocalTime());
        apptDate.setValue(appointmentToMod.getApptStart().toLocalDate());
        apptContact.setValue(appointmentToMod.getApptContact());
    }

    public void onSubmit(ActionEvent actionEvent) {
        LocalDate date = apptDate.getValue();
        LocalTime startTime = apptStartTime.getValue();
        LocalTime endTime = apptEndTime.getValue();
        LocalDateTime startLDT = DateTimeHandler.startTime(date, startTime);
        LocalDateTime endLDT = DateTimeHandler.endTime(date, endTime);
        appointmentToMod = new Appointments(DBContacts.getContactIDByName(apptContact.getValue()), apptTitle.getText(), apptDesc.getText(), apptLocation.getText(), apptContact.getValue(), startLDT, endLDT, apptType.getText(), Integer.parseInt(apptCustID.getText()), apptUserID.getValue());
        DBAppointments.modifyAppointment(appointmentToMod);
        MainScreen.appointments.set(indexToMod, appointmentToMod);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onCancel(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }


}
