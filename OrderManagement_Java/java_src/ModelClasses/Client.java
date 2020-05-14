package ModelClasses;

/**
 * A client  of the warehouse
 */
public class Client {
    private int id;
    private String first_name;
    private String last_name;
    private String address;
    //private Boolean deleted;

    public Client() {
        super();
    }

    public Client(String firstName, String lastName, String address) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.address = address;
    }

    public Client(String aaa, String firstName, String lastName, String address) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

}
