package Controller;

import Utilities.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomer implements Initializable {
    public TextField customerNameField;
    public TextField customerAddressField;
    public ComboBox<String> customerCountryComboBox;
    public ComboBox<String> customerFLDComboBox;
    public TextField customerPostCodeField;
    public TextField customerPhoneField;
    public TextField customerID;
    public Button modCustSaveBtn;
    public Button modCustCancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void onSaveButton(ActionEvent actionEvent) {
    }

    public void onCancelButton(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }
}
