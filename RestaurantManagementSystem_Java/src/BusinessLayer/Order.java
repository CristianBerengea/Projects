package BusinessLayer;

import java.io.Serializable;
import java.util.Date;

/**
 * Order class
 */
public class Order implements Serializable {
    private int orderId;
    private Date datee;
    private int table;

    public Order(int orderId,int table) {
        this.orderId = orderId;
        this.datee = new Date();
        this.table = table;
    }

    @Override
    public int hashCode() {
        return orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getDatee() {
        return datee;
    }

    public int getTable() {
        return table;
    }

    @Override
    public boolean equals(Object obj) {
        return orderId==((Order)obj).getOrderId();
    }
}
