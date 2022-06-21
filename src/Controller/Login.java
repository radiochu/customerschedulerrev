package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
import DBConnection.JDBC;
import Model.Appointments;
import Utilities.Alerts;
import Utilities.Reporter;
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

public class Login implements Initializable {
    public static String uname;
    public Label timezone;
    public PasswordField password;
    public TextField username;
    Alert a = new Alert(Alert.AlertType.NONE);
    private ResourceBundle resourceBundle;
    public boolean loginSuccess;

    public static String getUsername() {
        return uname;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TimeZone tz = TimeZone.getDefault();
        timezone.setText(resourceBundle.getString("timezone") + ":\n" + tz.getDisplayName());
        this.resourceBundle = resourceBundle;
    }

    public void onSubmit(ActionEvent actionEvent) throws IOException, SQLException {
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
            loginSuccess = false;
            uname = username.getText();
            Reporter.logActivity("Login by user " + uname + " was unsuccessful at UTC" + Instant.now());
        } else {
            uname = username.getText();
            loginSuccess = true;
            Reporter.logActivity("Login by user " + uname + " was successful at UTC " + Instant.now());
            Stage previous = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            previous.close();
            Parent root = FXMLLoader.load(getClass().getResource("/view/mainScreen.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Customer Scheduling System");
            mainStage.setScene(new Scene(root, 1000, 745));
            mainStage.show();
            upcomingAppointments(DBUsers.getUserIDByName(uname));

        }
    }

    private void upcomingAppointments(int user) {
        System.out.println("Username is " + uname);
        System.out.println("User ID is " + user);
        ObservableList<Appointments> userAppointments = DBAppointments.getApptsByUserID(user);
        for (Appointments i:userAppointments) {
            System.out.println(i);
        }
        ObservableList<Appointments> upcomingAppts = FXCollections.observableArrayList();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime in15Minutes = now.plusMinutes(15);
        for (Appointments a : userAppointments) {
            LocalDateTime apptTime = a.getApptStart();
            if (apptTime.isAfter(now.minusMinutes(1)) && apptTime.isBefore(in15Minutes)) {
                upcomingAppts.add(a);
            }
        }
        System.out.println(upcomingAppts);
        Alerts.upcomingAppointments(upcomingAppts.size());
    }

    public void onCancel(ActionEvent actionEvent) {
        Alerts.exitApplication();
    }
}
