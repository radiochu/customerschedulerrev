package Model;

import DBAccess.DBCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The type Customers.
 */
public class Customers {
    /**
     * The Customers.
     */
    public static ObservableList<Customers> customers = FXCollections.observableArrayList();
    private final String name;
    private final String address;
    private int id;
    private String fld;
    private String postCode;
    private String country;
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
    public Customers(int id, String name, String address, String fld, String postCode, String country, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.fld = fld;
        this.postCode = postCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets all customers.
     *
     * @return the all customers
     */
    public static ObservableList<Customers> getAllCustomers() {
        customers = DBCustomers.getAllCustomers();
        return customers;
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
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }


    /**
     * Gets fld.
     *
     * @return the fld
     */
    public String getFld() {
        return fld;
    }

    /**
     * Sets fld.
     *
     * @param fld the fld
     */
    public void setFld(String fld) {
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

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }
}
