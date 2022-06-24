package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
import DBConnection.JDBC;
import Model.Appointments;
import Utilities.Alerts;
import Utilities.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * The type Login.
 */
public class Login implements Initializable {
    /**
     * The constant uname.
     */
    public static String uname;
    /**
     * The A.
     */
    public Alert a = new Alert(Alert.AlertType.NONE);
    /**
     * The Timezone.
     */
    public Label timezone;
    /**
     * The Password.
     */
    public PasswordField password;
    /**
     * The Username.
     */
    public TextField username;

    private ResourceBundle resourceBundle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TimeZone tz = TimeZone.getDefault();
        timezone.setText(resourceBundle.getString("timezone") + ":\n" + tz.getDisplayName());
        this.resourceBundle = resourceBundle;
    }

    /**
     * On submit.
     *
     * @param actionEvent the action event
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public void onSubmit(ActionEvent actionEvent) throws IOException, SQLException {
        uname = username.getText();
        String sql = "SELECT * FROM users WHERE user_name = ? AND password = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, password.getText());
        ps.setString(2, username.getText());
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setHeaderText(null);
            a.setContentText(resourceBundle.getString("loginfail"));
            a.show();
            //loginSuccess = false;
            Logger.logActivity("Login by user " + uname + " was unsuccessful at UTC" + Instant.now());
        } else {
            Logger.logActivity("Login by user " + uname + " was successful at UTC " + Instant.now());
            Stage previous = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            previous.close();
            Parent root = FXMLLoader.load(getClass().getResource("/view/mainScreen.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Customer Scheduling System");
            mainStage.setScene(new Scene(root, 1020, 750));
            mainStage.show();
            upcomingAppointments(DBUsers.getUserIDByName(uname));
        }
    }

    private void upcomingAppointments(int user) {
        ObservableList<Appointments> userAppointments = DBAppointments.getApptsByUserID(user);
        ObservableList<Appointments> upcomingAppts = FXCollections.observableArrayList();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime in15Minutes = now.plusMinutes(15);
        for (Appointments a : userAppointments) {
            LocalDateTime apptTime = a.getApptStart();
            if (apptTime.isAfter(now.minusMinutes(1)) && apptTime.isBefore(in15Minutes)) {
                upcomingAppts.add(a);
            }
        }
        Alerts.upcomingAppointments(upcomingAppts.size());
    }

    /**
     * On cancel.
     *
     * @param actionEvent the action event
     */
    public void onCancel(ActionEvent actionEvent) {
        Alerts.exitApplication();
    }
}
