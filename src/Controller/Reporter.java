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
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Reporter implements Initializable {
    public Tab typesByMonth;
    public Button closeButton;
    public Tab scheduleByContact;
    public TableColumn<String, Types> monthCol;
    public TableColumn<String, Types> typeCol;
    public TableColumn<Integer, Types> totalCol;
    public TableView<Types> apptByMonthTV;
    public ComboBox<String> contactCB;
    public TableView<Appointments> scheduleTV;
    public TableColumn<Integer, Appointments> apptIDCol;
    public TableColumn<String, Appointments> apptTitleCol;
    public TableColumn<String, Appointments> apptTypeCol;
    public TableColumn<String, Appointments> apptDescCol;
    public TableColumn<LocalDateTime, Appointments> apptStartCol;
    public TableColumn<LocalDateTime, Appointments> apptEndCol;
    public TableColumn<Integer, Appointments> apptCustIDCol;
    public TextArea apptByMonthTA;
    public Tab apptsByMonth;


    ObservableList<Types> typesByMonthList = DBAppointments.getApptTypesByMonth();
    ObservableList<String> allContacts = DBContacts.getAllContacts();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactCB.setItems(allContacts);

        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        apptByMonthTV.setItems(typesByMonthList);

        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptDescCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        apptCustIDCol.setCellValueFactory(new PropertyValueFactory<>("custID"));

        showApptsByMonth();

    }

    public void showTypesByMonth(Event event) {
    }

    public void showScheduleByContact() {
        scheduleTV.setItems(DBAppointments.getApptsByContact(DBContacts.getContactIDByName(contactCB.getValue())));
    }

    public void showApptsByMonth() {
        for (String i : DBAppointments.getApptsByMonth()) {
            apptByMonthTA.appendText(i);
        }
    }

    public void onClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


}
