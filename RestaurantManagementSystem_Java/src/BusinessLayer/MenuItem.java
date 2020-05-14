package BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract class for Menu Item
 */
public abstract class MenuItem implements Serializable {
    private int menuItemId;
    private String name;
    private float price;
    private int quantity;

    public MenuItem() { }

    public MenuItem(int menuItemId, String name, float price, int quantity) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public abstract float computePrice();

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public abstract ArrayList<MenuItem> getProductItems();

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public String getName() {
        return name;
    }
}
