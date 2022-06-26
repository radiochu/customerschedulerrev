package Model;

/**
 * The type Customers.
 */
public class Customers {
    /**
     * The Customers.
     */
    private String name;
    private String address;
    private int id;
    private Divisions fld;
    private String postCode;
    private Countries country;
    private String phoneNumber;

    /**
     * Instantiates a new Customers.
     *
     * @param id          the id
     * @param name        the name
     * @param address     the address
     * @param fld         the fld
     * @param postCode    the post code
     * @param country     the country
     * @param phoneNumber the phone number
     */
    public Customers(int id, String name, String address, Divisions fld, String postCode, Countries country, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.fld = fld;
        this.postCode = postCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets post code.
     *
     * @return the post code
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets post code.
     *
     * @param postCode the post code
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }


    /**
     * Gets country.
     *
     * @return the country
     */
    public Countries getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(Countries country) {
        this.country = country;
    }


    /**
     * Gets fld.
     *
     * @return the fld
     */
    public Divisions getFld() {
        return fld;
    }

    /**
     * Sets fld.
     *
     * @param fld the fld
     */
    public void setFld(Divisions fld) {
        this.fld = fld;
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
