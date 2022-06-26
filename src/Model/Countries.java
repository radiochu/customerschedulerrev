package Model;

public class Countries {
    private String countryName;
    private int countryID;

    public Countries(String countryName, int countryID) {
        this.countryName = countryName;
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    @Override
    public String toString() {
        return countryName;
    }
}
