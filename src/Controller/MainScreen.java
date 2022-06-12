package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.Appointments;
import Model.Customers;
import Utilities.Alerts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreen implements Initializable {

    public TableView allCustomers;
    public TableColumn custID;
    public TableColumn custName;
    public TableColumn custAddress;
    public TableColumn custFLD;
    public TableColumn custPostCode;
    public TableColumn custCountry;
    public TableColumn custPhone;
    public Button custAdd;
    public Button custModify;
    public Tab allApptTab;
    public TableView allAppointments;
    public TableColumn allApptID;
    public TableColumn allApptTitle;
    public TableColumn allApptDesc;
    public TableColumn allApptLoc;
    public TableColumn allApptContact;
    public TableColumn allApptType;
    public TableColumn allApptStarts;
    public TableColumn allApptEnds;
    public TableColumn allApptCustID;
    public TableColumn allApptUserID;
    public Tab currentMonthAppts;
    public TableColumn currMonthApptID;
    public TableView currMonthApptsTable;
    public TableColumn currMonthApptTitle;
    public TableColumn currMonthApptDesc;
    public TableColumn currMonthApptLoc;
    public TableColumn currMonthApptContact;
    public TableColumn currMonthApptType;
    public TableColumn currMonthApptStarts;
    public TableColumn currMonthApptEnds;
    public TableColumn currMonthApptCustID;
    public TableColumn currMonthApptUserID;
    public Tab currentWeekAppts;
    public TableView currWeekApptsTable;
    public TableColumn currWeekApptID;
    public TableColumn currWeekApptTitle;
    public TableColumn currWeekApptDesc;
    public TableColumn currWeekApptLoc;
    public TableColumn currWeekApptContact;
    public TableColumn currWeekApptType;
    public TableColumn currWeekApptStarts;
    public TableColumn currWeekApptEnds;
    public TableColumn currWeekApptCustID;
    public TableColumn currWeekApptUserID;
    public Button apptAdd;
    public Button apptModify;
    public Button apptDelete;
    public Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custID.setCellValueFactory(new PropertyValueFactory<>("id"));
        custName.setCellValueFactory(new PropertyValueFactory<>("name"));
        custAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        custFLD.setCellValueFactory(new PropertyValueFactory<>("fld"));
        custPostCode.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        custCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        custPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        ObservableList<Customers> customers = DBCustomers.getAllCustomers();
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

    public void onCustAdd(ActionEvent actionEvent) {
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