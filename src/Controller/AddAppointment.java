package Controller;

import DBAccess.DBContacts;
import DBAccess.DBUsers;
import Utilities.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class AddAppointment implements Initializable {
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
    public Button Submit;
    public Button Cancel;
    public TextField apptLoc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptContact.setItems(DBContacts.getAllContacts());
        apptUserID.setItems(DBUsers.getAllUsers());
        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(17, 0);
        while (start.isBefore(end.plusSeconds(1))) {
            timeList.add(start);
            start = start.plusMinutes(10);
        }
        apptStartTime.setItems(timeList);
        apptEndTime.setItems(timeList);
    }

    public void onSubmit(ActionEvent actionEvent) {
    }

    public void onCancel(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }
}
