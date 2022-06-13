package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.Appointments;
import Model.Customers;
import Utilities.Alerts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MainScreen implements Initializable {

    public TableView<Customers> allCustomers;
    public TableColumn<Customers,Integer> custID;
    public TableColumn<Customers,String> custName;
    public TableColumn<Customers,String> custAddress;
    public TableColumn<Customers,String> custFLD;
    public TableColumn<Customers,String> custPostCode;
    public TableColumn<Customers,String> custCountry;
    public TableColumn<Customers,String> custPhone;
    public Button custAdd;
    public Button custModify;
    public Tab allApptTab;
    public TableView<Appointments> allAppointments;
    public TableColumn<Appointments,Integer> allApptID;
    public TableColumn<Appointments,String> allApptTitle;
    public TableColumn<Appointments,String> allApptDesc;
    public TableColumn<Appointments,String> allApptLoc;
    public TableColumn<Appointments,String> allApptContact;
    public TableColumn<Appointments,String> allApptType;
    public TableColumn<Appointments,LocalDateTime> allApptStarts;
    public TableColumn<Appointments,LocalDateTime> allApptEnds;
    public TableColumn<Appointments,Integer> allApptCustID;
    public TableColumn<Appointments,Integer> allApptUserID;
    public Tab currentMonthAppts;
    public TableView<Appointments> currMonthApptsTable;
    public TableColumn<Appointments,Integer> currMonthApptID;
    public TableColumn<Appointments,String> currMonthApptTitle;
    public TableColumn<Appointments,String> currMonthApptDesc;
    public TableColumn<Appointments,String> currMonthApptLoc;
    public TableColumn<Appointments,String> currMonthApptContact;
    public TableColumn<Appointments,String> currMonthApptType;
    public TableColumn<Appointments,LocalDateTime> currMonthApptStarts;
    public TableColumn<Appointments,LocalDateTime> currMonthApptEnds;
    public TableColumn<Appointments,Integer> currMonthApptCustID;
    public TableColumn<Appointments,Integer> currMonthApptUserID;
    public Tab currentWeekAppts;
    public TableView<Appointments> currWeekApptsTable;
    public TableColumn<Appointments,Integer> currWeekApptID;
    public TableColumn<Appointments,String> currWeekApptTitle;
    public TableColumn<Appointments,String> currWeekApptDesc;
    public TableColumn<Appointments,String> currWeekApptLoc;
    public TableColumn<Appointments,String> currWeekApptContact;
    public TableColumn<Appointments,String> currWeekApptType;
    public TableColumn<Appointments,LocalDateTime> currWeekApptStarts;
    public TableColumn<Appointments,LocalDateTime> currWeekApptEnds;
    public TableColumn<Appointments,Integer> currWeekApptCustID;
    public TableColumn<Appointments,Integer> currWeekApptUserID;
    public Button apptAdd;
    public Button apptModify;
    public Button apptDelete;
    public Button exitButton;

    static ObservableList<Customers> customers = DBCustomers.getAllCustomers();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custID.setCellValueFactory(new PropertyValueFactory<>("id"));
        custName.setCellValueFactory(new PropertyValueFactory<>("name"));
        custAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        custFLD.setCellValueFactory(new PropertyValueFactory<>("fld"));
        custPostCode.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        custCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        custPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));


        allCustomers.setItems(customers);

        allApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        allApptTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        allApptDesc.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        allApptLoc.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        allApptContact.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        allApptType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        allApptStarts.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        allApptEnds.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        allApptCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));
        allApptUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        ObservableList<Appointments> appointments = DBAppointments.getAllAppointments();
        allAppointments.setItems(appointments);

        currMonthApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        currMonthApptTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        currMonthApptDesc.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        currMonthApptLoc.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        currMonthApptContact.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        currMonthApptType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        currMonthApptStarts.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        currMonthApptEnds.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        currMonthApptCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));
        currMonthApptUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        ObservableList<Appointments> currMonthAppts = DBAppointments.getThisMonthAppts();
        currMonthApptsTable.setItems(currMonthAppts);

        currWeekApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        currWeekApptTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        currWeekApptDesc.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        currWeekApptLoc.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        currWeekApptContact.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        currWeekApptType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        currWeekApptStarts.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        currWeekApptEnds.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        currWeekApptCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));
        currWeekApptUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        ObservableList<Appointments> currWeekAppts = DBAppointments.getThisWeekAppts();
        currWeekApptsTable.setItems(currWeekAppts);
    }

    public void custNameUpdate(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void custAddressUpdate(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void custFLDUpdate(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void custPostCodeUpdate(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void custCountryUpdate(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void custPhoneUpdate(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void custEditName(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void custEditAddress(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void custEditFLD(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void custEditPostCode(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void custEditCountry(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void custEditPhone(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void onCustAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        Stage overlay = new Stage();
        overlay.setTitle("Add Customer");
        overlay.setScene(new Scene(root, 400, 400));
        overlay.show();
    }

    public void onCustModify(ActionEvent actionEvent) {
    }

    public void onCustDelete(ActionEvent actionEvent) {
    }

    public void showAllAppts(Event event) {
    }

    public void showCurrentMonth(Event event) {
    }

    public void showCurrentWeek(Event event) {
    }

    public void onApptAdd(ActionEvent actionEvent) {
    }

    public void onApptModify(ActionEvent actionEvent) {
    }

    public void onApptDelete(ActionEvent actionEvent) {
    }

    public void onExitButton(ActionEvent actionEvent) {
        Alerts.exitApplication();
    }
}