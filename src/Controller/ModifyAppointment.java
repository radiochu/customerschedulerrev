package Controller;

import Model.Appointments;
import Utilities.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ModifyAppointment implements Initializable {
    public static TextField apptID;
    public static TextField apptCustID;
    public static TextField apptUserID;
    public static TextField apptTitle;
    public static TextField apptDesc;
    public static ComboBox<String> apptType;
    public ComboBox<String> apptContact;
    public DatePicker apptDate;
    public ChoiceBox<LocalTime> apptStartTime;
    public ChoiceBox<LocalTime> apptEndTime;
    public Button Submit;
    public Button Cancel;


    public static void getApptToModify(int appointmentID, Appointments appointment) {
        apptID.setText(String.valueOf(appointmentID));
        apptCustID.setText(String.valueOf(appointment.getCustID()));
        apptUserID.setText(String.valueOf(appointment.getUserID()));
        apptTitle.setText(appointment.getApptTitle());
        apptDesc.setText(appointment.getApptDescription());
        apptType.setValue(appointment.getApptType());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onSubmit(ActionEvent actionEvent) {
    }

    public void onCancel(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }


}
