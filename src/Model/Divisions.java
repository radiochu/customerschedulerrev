package Model;

public class Divisions {
    private String divName;
    private int countryID;

    public Divisions(String divName, int countryID) {
        this.divName = divName;
        this.countryID = countryID;
    }

    public String getDivName() {
        return divName;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    @Override
    public String toString(){
        return divName;
    }


}
