package ModelClasses;

import java.util.Date;

/**
 * An order
 * made by a customer
 * wich contain products from warehouse
 */
public class Order {
    private int id;
    private int clientId;
    private String datee;

    public Order()
    {
        super();
    }

    public Order(int id, int clientId, String date) {
        this.id = id;
        this.clientId = clientId;
        this.datee = date;
    }

    public Order(int clientId, String date) {
        this.id = id;
        this.clientId = clientId;
        this.datee = date;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getDate() {
        return datee;
    }

    public void setDate(String date) {
        this.datee = date;
    }

}
