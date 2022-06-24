package Controller;

import DBAccess.DBAppointments;
import Model.Types;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Reporter {
    public Tab typesByMonth;
    public Button closeButton;
    public Tab scheduleByContact;
    public Tab apptsByCustomer;
    public TextArea typesByMonthTA;
    public TextArea scheduleByContactTA;
    public TextArea apptsByCustomerTA;

    public void showTypesByMonth(Event event) {
        ObservableList<Types> typesByMonth = DBAppointments.getApptTypesByMonth();
        typesByMonthTA.setText("");
        for (Types i : typesByMonth) {
            typesByMonthTA.appendText(i.getMonth() + " -- " + i.getType() + " -- " + i.getCount() + "\n");
        }
    }

    public void showScheduleByContact(Event event) {
    }

    public void showApptsByCustomer(Event event) {
    }

    public void onClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
