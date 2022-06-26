package Model;

/**
 * Holds information to create the Appointment Types by Month report.
 */
public class Types {
    /**
     * The month for which to total types.
     */
    private final String month;
    /**
     * The appointment type to count.
     */
    private final String type;
    /**
     * The amount of appointments of this type per month.
     */
    private final int count;

    /**
     * Instantiates a new Types object.
     *
     * @param month     appointment month - String
     * @param type      appointment type - String
     * @param count     appointment count - int
     */
    public Types(String month, String type, int count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }

    /**
     * Gets appointment type.
     * @return String type containing the appointment type.
     */
    public String getType() {
        return type;
    }
}
