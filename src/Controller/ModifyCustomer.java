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

public class ModifyCustomer implements Initializable {
    public TextField custNameField;
    public TextField custAddressField;
    public ComboBox<String> custCountryCB;
    public Label divisionLabel;
    public ComboBox<String> custFLDCB;
    public TextField custPostCodeField;
    public TextField custPhoneField;
    public TextField customerID;
    public Button modCustSaveBtn;
    public Button modCustCancelBtn;

    public static Customers customerToMod = null;
    public static int indexToMod = 0;


    public static void setCustomerToMod (Customers customer, int index) {
        customerToMod = customer;
        indexToMod = index;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custNameField.setText(customerToMod.getName());
        custAddressField.setText(customerToMod.getAddress());
        custCountryCB.setItems(DBCountries.getAllCountries());
        custCountryCB.getSelectionModel().select(customerToMod.getCountry());
        custFLDCB.getSelectionModel().select(customerToMod.getFld());
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
        custPostCodeField.setText(customerToMod.getPostCode());
        custPhoneField.setText(customerToMod.getPhoneNumber());
        customerID.setText(String.valueOf(customerToMod.getId()));
    }

    private boolean validateInput() {
        Boolean b = false;
        if (custNameField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer name.\n");
        }
        else if (custAddressField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer address.\n");
        }
        else if (custCountryCB.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a country.\n");
        }
        else if (custFLDCB.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a first-level division.\n");
        }
        else if (custPhoneField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer phone number.\n");
        }
        else if (custPostCodeField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer post code.\n");
        }
        else {
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
        if(validateInput()) {
            customerToMod = new Customers(customerToMod.getId(), custNameField.getText(), custAddressField.getText(), custFLDCB.getValue(), custPostCodeField.getText(), custCountryCB.getValue(), custPhoneField.getText());
            DBCustomers.modifyCustomer(customerToMod);
            MainScreen.customers.set(indexToMod, customerToMod);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    public void onCancelButton(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }
}
