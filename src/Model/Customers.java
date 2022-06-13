package Model;

public class Customers {
    private int id;
    private final String name;
    private final String address;
    private String fld;
    private String postCode;
    private String country;
    private String phoneNumber;

    public Customers(int id, String name, String address, String fld, String postCode, String country, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.fld = fld;
        this.postCode = postCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getFld() {
        return fld;
    }

    public void setFld(String fld) {
        this.fld = fld;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
