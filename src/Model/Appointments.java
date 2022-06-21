package Model;

import Utilities.DateTimeHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointments {
    public static ObservableList<String> apptTypes = FXCollections.observableArrayList();
    private final int apptID;
    private final String apptTitle;
    private final String apptDescription;
    private final String apptLocation;
    private final String apptContact;
    private final String apptType;
    private final LocalDateTime apptStart;
    private final LocalDateTime apptEnd;
    private final int custID;
    private final int userID;

    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptContact, LocalDateTime apptStart, LocalDateTime apptEnd, String apptType, int custID, int userID) {
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptContact = apptContact;
        this.apptType = apptType;
        this.apptStart = apptStart;
        this.apptEnd = apptEnd;
        this.custID = custID;
        this.userID = userID;
    }

    public int getApptID() {
        return apptID;
    }

    public String getApptTitle() {
        return apptTitle;
    }

    public String getApptDescription() {
        return apptDescription;
    }

    public String getApptLocation() {
        return apptLocation;
    }

    public String getApptType() {
        return apptType;
    }

    public LocalDateTime getApptStart() {
        return apptStart;
    }

    public LocalDateTime getApptEnd() {
        return apptEnd;
    }

    public int getCustID() {
        return custID;
    }

    public int getUserID() {
        return userID;
    }

    public String getApptContact() {
        return apptContact;
    }
}
