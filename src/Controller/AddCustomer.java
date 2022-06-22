package Controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Model.Customers;
import Utilities.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Add customer.
 */
public class AddCustomer implements Initializable {
    /**
     * The Cust name field.
     */
    public TextField custNameField;
    /**
     * The Cust address field.
     */
    public TextField custAddressField;
    /**
     * The Cust country cb.
     */
    public ComboBox<String> custCountryCB;
    /**
     * The Cust fldcb.
     */
    public ComboBox<String> custFLDCB;
    /**
     * The Cust post code field.
     */
    public TextField custPostCodeField;
    /**
     * The Cust phone field.
     */
    public TextField custPhoneField;
    /**
     * The Customer id.
     */
    public TextField customerID;
    /**
     * The Add cust save btn.
     */
    public Button addCustSaveBtn;
    /**
     * The Add cust cancel btn.
     */
    public Button addCustCancelBtn;
    /**
     * The Division label.
     */
    public Label divisionLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custCountryCB.setItems(DBCountries.getAllCountries());
    }

    private boolean validateInput() {
        boolean b = false;
        if (custNameField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer name.\n");
        } else if (custAddressField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer address.\n");
        } else if (custCountryCB.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a country.\n");
        } else if (custFLDCB.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a first-level division.\n");
        } else if (custPhoneField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer phone number.\n");
        } else if (custPostCodeField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer post code.\n");
        } else {
            b = true;
        }
        return b;
    }

    /**
     * Filter divisions.
     *
     * @param actionEvent the action event
     */
    public void filterDivisions(ActionEvent actionEvent) {
        String countryName = custCountryCB.getValue();
        if (countryName.equals("U.S")) {
            custFLDCB.setItems(DBDivisions.getUSDivisions());
            divisionLabel.setText("State");
        } else if (countryName.equals("UK")) {
            custFLDCB.setItems(DBDivisions.getUKDivisions());
            divisionLabel.setText("Region");
        } else {
            custFLDCB.setItems(DBDivisions.getCanadaDivisions());
            divisionLabel.setText("Province");
        }
    }

    /**
     * On save button.
     *
     * @param actionEvent the action event
     */
    public void onSaveButton(ActionEvent actionEvent) {
        if (validateInput()) {
            Customers customer = new Customers(0, custNameField.getText(), custAddressField.getText(), custFLDCB.getValue(), custPostCodeField.getText(), custCountryCB.getValue(), custPhoneField.getText());
            int newCustID = DBCustomers.addCustomer(customer);
            customer.setId(newCustID);
            MainScreen.customers.add(customer);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    /**
     * On cancel button.
     *
     * @param actionEvent the action event
     */
    public void onCancelButton(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }
}
