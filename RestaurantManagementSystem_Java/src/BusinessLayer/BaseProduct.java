package BusinessLayer;

import java.util.ArrayList;

/**
 * Base Product
 */
public class BaseProduct extends MenuItem{

    public BaseProduct(int menuItemId, String name, float price, int quantity) {
        super(menuItemId,name,price,quantity);
    }

    public float computePrice() {
        return super.getPrice();
    }

    @Override
    public ArrayList<MenuItem> getProductItems() {
        return null;
    }

}
