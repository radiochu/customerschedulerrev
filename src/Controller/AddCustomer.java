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

public class AddCustomer implements Initializable {
    public TextField custNameField;
    public TextField custAddressField;
    public ComboBox<String> custCountryCB;
    public ComboBox<String> custFLDCB;
    public TextField custPostCodeField;
    public TextField custPhoneField;
    public TextField customerID;
    public Button addCustSaveBtn;
    public Button addCustCancelBtn;
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

    public void onCancelButton(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }
}
