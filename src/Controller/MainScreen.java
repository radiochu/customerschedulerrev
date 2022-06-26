package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.Appointments;
import Model.Customers;
import Utilities.Alerts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

/**
 * The type Main screen.
 */
public class MainScreen implements Initializable {

    /**
     * The Customers.
     */
    static ObservableList<Customers> customers = DBCustomers.getAllCustomers();
    /**
     * The Appointments.
     */
    static ObservableList<Appointments> appointments = DBAppointments.getAllAppointments();
    /**
     * The Curr month appts.
     */
    static ObservableList<Appointments> currMonthAppts = DBAppointments.getThisMonthAppts();
    /**
     * The Curr week appts.
     */
    static ObservableList<Appointments> currWeekAppts = DBAppointments.getThisWeekAppts();
    /**
     * The All customers.
     */
    public TableView<Customers> allCustomers;
    /**
     * The Cust id.
     */
    public TableColumn<Customers, Integer> custID;
    /**
     * The Cust name.
     */
    public TableColumn<Customers, String> custName;
    /**
     * The Cust address.
     */
    public TableColumn<Customers, String> custAddress;
    /**
     * The Cust fld.
     */
    public TableColumn<Customers, String> custFLD;
    /**
     * The Cust post code.
     */
    public TableColumn<Customers, String> custPostCode;
    /**
     * The Cust country.
     */
    public TableColumn<Customers, String> custCountry;
    /**
     * The Cust phone.
     */
    public TableColumn<Customers, String> custPhone;
    /**
     * The Cust add.
     */
    public Button custAdd;
    /**
     * The Cust modify.
     */
    public Button custModify;
    /**
     * The Cust delete.
     */
    public Button custDelete;
    /**
     * The All appt tab.
     */
    public Tab allApptTab;
    /**
     * The All appointments.
     */
    public TableView<Appointments> allAppointments;
    /**
     * The All appt id.
     */
    public TableColumn<Appointments, Integer> allApptID;
    /**
     * The All appt title.
     */
    public TableColumn<Appointments, String> allApptTitle;
    /**
     * The All appt desc.
     */
    public TableColumn<Appointments, String> allApptDesc;
    /**
     * The All appt loc.
     */
    public TableColumn<Appointments, String> allApptLoc;
    /**
     * The All appt contact.
     */
    public TableColumn<Appointments, String> allApptContact;
    /**
     * The All appt type.
     */
    public TableColumn<Appointments, String> allApptType;
    /**
     * The All appt starts.
     */
    public TableColumn<Appointments, LocalDateTime> allApptStarts;
    /**
     * The All appt ends.
     */
    public TableColumn<Appointments, LocalDateTime> allApptEnds;
    /**
     * The All appt cust id.
     */
    public TableColumn<Appointments, Integer> allApptCustID;
    /**
     * The All appt user id.
     */
    public TableColumn<Appointments, Integer> allApptUserID;
    /**
     * The Current month appts.
     */
    public Tab currentMonthAppts;
    /**
     * The Curr month appts table.
     */
    public TableView<Appointments> currMonthApptsTable;
    /**
     * The Curr month appt id.
     */
    public TableColumn<Appointments, Integer> currMonthApptID;
    /**
     * The Curr month appt title.
     */
    public TableColumn<Appointments, String> currMonthApptTitle;
    /**
     * The Curr month appt desc.
     */
    public TableColumn<Appointments, String> currMonthApptDesc;
    /**
     * The Curr month appt loc.
     */
    public TableColumn<Appointments, String> currMonthApptLoc;
    /**
     * The Curr month appt contact.
     */
    public TableColumn<Appointments, String> currMonthApptContact;
    /**
     * The Curr month appt type.
     */
    public TableColumn<Appointments, String> currMonthApptType;
    /**
     * The Curr month appt starts.
     */
    public TableColumn<Appointments, LocalDateTime> currMonthApptStarts;
    /**
     * The Curr month appt ends.
     */
    public TableColumn<Appointments, LocalDateTime> currMonthApptEnds;
    /**
     * The Curr month appt cust id.
     */
    public TableColumn<Appointments, Integer> currMonthApptCustID;
    /**
     * The Curr month appt user id.
     */
    public TableColumn<Appointments, Integer> currMonthApptUserID;
    /**
     * The Current week appts.
     */
    public Tab currentWeekAppts;
    /**
     * The Curr week appts table.
     */
    public TableView<Appointments> currWeekApptsTable;
    /**
     * The Curr week appt id.
     */
    public TableColumn<Appointments, Integer> currWeekApptID;
    /**
     * The Curr week appt title.
     */
    public TableColumn<Appointments, String> currWeekApptTitle;
    /**
     * The Curr week appt desc.
     */
    public TableColumn<Appointments, String> currWeekApptDesc;
    /**
     * The Curr week appt loc.
     */
    public TableColumn<Appointments, String> currWeekApptLoc;
    /**
     * The Curr week appt contact.
     */
    public TableColumn<Appointments, String> currWeekApptContact;
    /**
     * The Curr week appt type.
     */
    public TableColumn<Appointments, String> currWeekApptType;
    /**
     * The Curr week appt starts.
     */
    public TableColumn<Appointments, LocalDateTime> currWeekApptStarts;
    /**
     * The Curr week appt ends.
     */
    public TableColumn<Appointments, LocalDateTime> currWeekApptEnds;
    /**
     * The Curr week appt cust id.
     */
    public TableColumn<Appointments, Integer> currWeekApptCustID;
    /**
     * The Curr week appt user id.
     */
    public TableColumn<Appointments, Integer> currWeekApptUserID;
    /**
     * The Appt add.
     */
    public Button apptAdd;
    /**
     * The Appt modify.
     */
    public Button apptModify;
    /**
     * The Appt delete.
     */
    public Button apptDelete;
    public Button reportsButton;
    /**
     * The Exit button.
     */
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

        currWeekApptsTable.setItems(currWeekAppts);
    }

    /**
     * Refresh tables.
     */
    public void refreshTables() {
        customers.setAll(DBCustomers.getAllCustomers());
        appointments.setAll(DBAppointments.getAllAppointments());
        currMonthAppts.setAll(DBAppointments.getThisMonthAppts());
        currWeekAppts.setAll(DBAppointments.getThisWeekAppts());
    }

    /**
     * On cust add.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onCustAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        Stage overlay = new Stage();
        overlay.setTitle("Add Customer");
        overlay.setScene(new Scene(root, 400, 400));
        overlay.show();
    }

    /**
     * On cust modify.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onCustModify(ActionEvent actionEvent) throws IOException {
        Customers customerToMod = allCustomers.getSelectionModel().getSelectedItem();
        int indexToMod = allCustomers.getSelectionModel().getSelectedIndex();
        if (customerToMod != null) {
            ModifyCustomer.setCustomerToMod(customerToMod, indexToMod);
            Parent root = FXMLLoader.load(getClass().getResource("/view/modifyCustomer.fxml"));
            Stage overlay = new Stage();
            overlay.setTitle("Modify Customer");
            overlay.setScene(new Scene(root, 400, 400));
            overlay.show();
        } else {
            Alerts.noSelection();
        }
    }

    /**
     * On cust delete.
     *
     * @param actionEvent the action event
     */
    public void onCustDelete(ActionEvent actionEvent) {
        int custToDelete = allCustomers.getSelectionModel().getSelectedItem().getId();
        if (Alerts.deleteCustomer()) {
            if (DBAppointments.getApptsByCustID(custToDelete).size() != 0) {
                DBAppointments.deleteApptsByCust(custToDelete);
            }
        }
        if (DBCustomers.deleteCustomer(custToDelete)) {
            Alerts.customerDeleted();
            refreshTables();
        }
    }


    /**
     * On appt add.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onApptAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addAppointment.fxml"));
        Stage overlay = new Stage();
        overlay.setTitle("Add Appointment");
        overlay.setScene(new Scene(root, 560, 400));
        overlay.show();
    }

    /**
     * On appt modify.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onApptModify(ActionEvent actionEvent) throws IOException {
        Appointments appt;
        int indexToMod = 0;
        if (allApptTab.isSelected()) {
            appt = allAppointments.getSelectionModel().getSelectedItem();
            indexToMod = allAppointments.getSelectionModel().getSelectedIndex();
        } else if (currentMonthAppts.isSelected()) {
            appt = currMonthApptsTable.getSelectionModel().getSelectedItem();
            indexToMod = currMonthApptsTable.getSelectionModel().getSelectedIndex();
        } else {
            appt = currWeekApptsTable.getSelectionModel().getSelectedItem();
            indexToMod = currWeekApptsTable.getSelectionModel().getSelectedIndex();
        }
        if (appt == null) {
            Alerts.noSelection();
        } else {
            ModifyAppointment.setAppointmentToMod(appt, indexToMod);
            Parent root = FXMLLoader.load(getClass().getResource("/view/modifyAppointment.fxml"));
            Stage overlay = new Stage();
            overlay.setTitle("Modify Appointment");
            overlay.setScene(new Scene(root, 560, 400));
            overlay.show();
        }
    }

    /**
     * On appt delete.
     *
     * @param actionEvent the action event
     */
    public void onApptDelete(ActionEvent actionEvent) {
        Appointments appt;
        String apptType;
        if (allApptTab.isSelected()) {
            appt = allAppointments.getSelectionModel().getSelectedItem();
            apptType = allAppointments.getSelectionModel().getSelectedItem().getApptType();
        } else if (currentMonthAppts.isSelected()) {
            appt = currMonthApptsTable.getSelectionModel().getSelectedItem();
            apptType = currMonthApptsTable.getSelectionModel().getSelectedItem().getApptType();
        } else {
            appt = currWeekApptsTable.getSelectionModel().getSelectedItem();
            apptType = currWeekApptsTable.getSelectionModel().getSelectedItem().getApptType();
        }
        if (appt == null) {
            Alerts.noSelection();
        } else {
            int apptToDelete = appt.getApptID();
            {
                if (Alerts.deleteAppointment()) {
                    if (DBAppointments.deleteAppointment(apptToDelete)) {
                        Alerts.apptDeleted(apptToDelete, apptType);
                        refreshTables();
                    }

                }
            }
        }
    }

    public void viewReports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/reporter.fxml"));
        Stage overlay = new Stage();
        overlay.setTitle("Add Appointment");
        overlay.setScene(new Scene(root, 715, 466));
        overlay.show();
    }

    /**
     * On exit button.
     *
     * @param actionEvent the action event
     */
    public void onExitButton(ActionEvent actionEvent) {
        Alerts.exitApplication();
    }


}