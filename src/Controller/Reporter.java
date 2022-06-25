package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Reporter implements Initializable {
    public Tab typesByMonth;
    public Button closeButton;
    public Tab scheduleByContact;
    public Tab apptsByCustomer;
    public TableColumn<String, Types> monthCol;
    public TableColumn<String, Types> typeCol;
    public TableColumn<Integer, Types> totalCol;
    public TableColumn<String, Contacts> contactNameCol;
    public TableColumn<Appointments, Appointments> scheduleCol;
    public TableColumn<String, Customers> custNameCol;
    public TableColumn<Integer, Appointments> totalApptsCol;
    public TableView<Types> apptByMonthTV;
    public TableView<Appointments> apptByCustomerTV;
    public TextArea scheduleTA;


    ObservableList<Types> typesByMonthList = DBAppointments.getApptTypesByMonth();
    ObservableList<String> contactAppts = FXCollections.observableArrayList();

    public void showScheduleByContact() {
        contactAppts = DBContacts.getScheduleByContact();
        for (String j : contactAppts) {
            scheduleTA.appendText(j + "\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monthCol.setCellValueFactory(new PropertyValueFactory<String, Types>("month"));
        typeCol.setCellValueFactory(new PropertyValueFactory<String, Types>("type"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Integer, Types>("count"));

        apptByMonthTV.setItems(typesByMonthList);

        showScheduleByContact();
    }

    public void showTypesByMonth(Event event) {
    }



    public void showApptsByCustomer(Event event) {
    }

    public void onClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


}
