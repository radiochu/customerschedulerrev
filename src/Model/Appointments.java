package Model;

import java.time.LocalDateTime;

/**
 * The type Appointments.
 */
public class Appointments {
    /**
     * The Appt types.
     */
    private final String apptTitle;
    private final String apptDescription;
    private final String apptLocation;
    private final Contacts apptContact;
    private final String apptType;
    private final LocalDateTime apptStart;
    private final LocalDateTime apptEnd;
    private final int custID;
    private final int userID;
    private int apptID;

    /**
     * Instantiates a new Appointments.
     *
     * @param apptID          the appt id
     * @param apptTitle       the appt title
     * @param apptDescription the appt description
     * @param apptLocation    the appt location
     * @param apptContact     the appt contact
     * @param apptStart       the appt start
     * @param apptEnd         the appt end
     * @param apptType        the appt type
     * @param custID          the cust id
     * @param userID          the user id
     */
    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, Contacts apptContact, LocalDateTime apptStart, LocalDateTime apptEnd, String apptType, int custID, int userID) {
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

    /**
     * Gets appt id.
     *
     * @return the appt id
     */
    public int getApptID() {
        return apptID;
    }

    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    /**
     * Gets appt title.
     *
     * @return the appt title
     */
    public String getApptTitle() {
        return apptTitle;
    }

    /**
     * Gets appt description.
     *
     * @return the appt description
     */
    public String getApptDescription() {
        return apptDescription;
    }

    /**
     * Gets appt location.
     *
     * @return the appt location
     */
    public String getApptLocation() {
        return apptLocation;
    }

    /**
     * Gets appt type.
     *
     * @return the appt type
     */
    public String getApptType() {
        return apptType;
    }

    /**
     * Gets appt start.
     *
     * @return the appt start
     */
    public LocalDateTime getApptStart() {
        return apptStart;
    }

    /**
     * Gets appt end.
     *
     * @return the appt end
     */
    public LocalDateTime getApptEnd() {
        return apptEnd;
    }

    /**
     * Gets cust id.
     *
     * @return the cust id
     */
    public int getCustID() {
        return custID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Gets appt contact.
     *
     * @return the appt contact
     */
    public Contacts getApptContact() {
        return apptContact;
    }

}
