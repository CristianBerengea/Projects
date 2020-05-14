package BusinessLayer;

import java.util.ArrayList;

public interface IRestaurantProcessing {

    /**
     *
     * @pre l!=null
     * @post restaurant map.size()==pre.map.size()+1
     */
    public void createNewOrder(Order o, ArrayList<MenuItem> l);

    /**
     * @pre o!=null
     * @post restaurant map.size()==pre.map.size()-1
     */
    public void deleteOrder(Order o);

    /**
     * @pre l!=null
     * @post return value greater than 0
     */
    public float computePrice(ArrayList<MenuItem> l);

    /**
     * @pre o != null and l != null
     * @post
     */
    public void generateBill(Order o, ArrayList<MenuItem> l);

    //administrator

    /**
     * @pre m != null
     * @post restaurant.MenuItems.size==pre.restaurant.MenuItems.size+1
     */
    public void addNewMenuItem(MenuItem m);

    /**
     * @pre menuItemId positive number
     * @post restaurant.MenuItems.size==pre.restaurant.MenuItems.size-1
     */
    public void deleteNewMenuItem(int menuItemId);

    /**
     * @pre menuItemId positive number and m!= null
     * @post restaurant.MenuItems.size==pre.restaurant.MenuItems.size
     */
    public void editMenuItem(int menuItemId, MenuItem m);
}
