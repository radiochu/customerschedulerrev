package Controller;

import DBAccess.DBCountries;
import Utilities.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {
    public TextField customerNameField;
    public TextField customerAddressField;
    public ComboBox<String> customerCountryComboBox;
    public ComboBox<String> customerFLDComboBox;
    public TextField customerPostCodeField;
    public TextField customerPhoneField;
    public TextField customerID;
    public Button addCustSaveBtn;
    public Button addCustCancelBtn;
    public Label divisionLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCountryComboBox.setItems(DBCountries.getAllCountries());
    }

    public void filterDivisions(ActionEvent actionEvent) {
    }

    public void onSaveButton(ActionEvent actionEvent) {
    }

    public void onCancelButton(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }
}
