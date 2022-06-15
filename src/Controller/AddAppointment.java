package Controller;

import DBAccess.DBContacts;
import DBAccess.DBUsers;
import DBConnection.JDBC;
import Model.Appointments;
import Utilities.Alerts;
import Utilities.DateTimeHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        apptStartTime.setItems(DateTimeHandler.setTimeList());
        apptEndTime.setItems(DateTimeHandler.setTimeList());
    }

    public void onSaveButton(ActionEvent actionEvent) {
        LocalDate date = apptDate.getValue();
        LocalTime startTime = apptStartTime.getValue();
        LocalTime endTime = apptEndTime.getValue();
        LocalDateTime startLDT = DateTimeHandler.startTime(date, startTime);
        LocalDateTime endLDT = DateTimeHandler.endTime(date, endTime);
        try {
            String sql = "INSERT INTO appointments VALUES (NULL, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, apptTitle.getText());
            ps.setString(2, apptDesc.getText());
            ps.setString(3, apptLoc.getText());
            ps.setString(4, apptType.getText());
            ps.setTimestamp(5, Timestamp.valueOf(startLDT));
            ps.setTimestamp(6, Timestamp.valueOf(endLDT));
            ps.setInt(7, Integer.parseInt(apptCustID.getText()));
            ps.setInt(8, apptUserID.getValue());
            ps.setInt(9, DBContacts.getContactIDByName(apptContact.getValue()));
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int newApptID = rs.getInt(1);
            MainScreen.appointments.add(new Appointments(newApptID, apptTitle.getText(), apptDesc.getText(), apptLoc.getText(), apptContact.getValue(), startLDT, endLDT, apptType.getText(), Integer.parseInt(apptCustID.getText()), apptUserID.getValue()));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onCancel(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }
}
