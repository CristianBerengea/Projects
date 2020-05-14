package BusinessLayer;

import java.util.ArrayList;

/**
 * Composite product created by Base Products
 */
public class CompositeProduct extends MenuItem{
    private ArrayList<MenuItem> productItems;

     public CompositeProduct(int id,String name,ArrayList<MenuItem> productItems) {
         super(id,name,0,0);
        this.productItems = new ArrayList<MenuItem>();
        this.productItems = productItems;
    }


    public float computePrice() {
         float price=0;
        for (MenuItem e: productItems) {
            price+=e.getPrice()*e.getQuantity();
        }
        return price;
    }

    public ArrayList<MenuItem> getProductItems() {
        return productItems;
    }
}
