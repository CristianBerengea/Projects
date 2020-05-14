package BusinessLayer;

import DataLayer.FileWriter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * @invariant isWellFormed()
 */

/**
 * This class contain Menu Items and Orders of the restaurant
 */
public class Restaurant extends Observable implements IRestaurantProcessing, Serializable {
    private ArrayList<MenuItem> menuItems;
    private  Map<Order, ArrayList<MenuItem>> map;
    private int idMenuItems;
    private int idOrder;

    public Restaurant() {
        menuItems = new ArrayList<MenuItem>();
        map = new HashMap<Order, ArrayList<MenuItem>>();
        idMenuItems = 0;
        idOrder = 0;
    }

    /**
     * method type  Well Formated for invariant
     * @return
     */
    private boolean isWellFormated(){
        boolean ok = true;
        for(MenuItem m: menuItems)
        {
            if( m==null||m.getMenuItemId()<0||m.getQuantity()<0||m.getName().equals("")) ok = false;
        }
        for(Order o:map.keySet())
        {
            if(o==null||o.getOrderId()<0||o.getTable()<0) ok = false;
            ArrayList<MenuItem> l = map.get(o);
            if(l==null) ok = false;
            for(MenuItem m: l){
                if( m==null||m.getMenuItemId()<0||m.getQuantity()<0||m.getName().equals("")) ok = false;
            }
        }
        return ok;
    }

    /**
     * Add a new Menu Item
     * @param m
     */
    @Override
    public void addNewMenuItem(MenuItem m) {
        assert m!=null;
        assert isWellFormated();
        int menuItemsSize = menuItems.size();
         menuItems.add(m);
         idMenuItems++;
         assert menuItemsSize+1==menuItems.size();
        assert isWellFormated();
    }

    /**
     * Delete a Menu Item
     * @param menuItemId
     */
    @Override
    public void deleteNewMenuItem(int menuItemId) {
        assert menuItemId>=0;
        assert isWellFormated();
        int menuItemsSize = menuItems.size();
        MenuItem m=null;
        for (MenuItem e: menuItems) {
            if(e.getMenuItemId()==menuItemId) m = e;
        }
        menuItems.remove(m);
        assert menuItemsSize-1==menuItems.size();
        assert isWellFormated();
    }

    /**
     * update Menu Item
     * @param menuItemId
     * @param m
     */
    @Override
    public void editMenuItem(int menuItemId, MenuItem m) {
        assert m!=null;
        assert isWellFormated();
        int menuItemsSize = menuItems.size();
        assert menuItemId>=0;
        deleteNewMenuItem(menuItemId);
        menuItems.add(m);
        assert menuItemsSize==menuItems.size();
        assert isWellFormated();
    }

    /**
     * Create New Order
     * @param o
     * @param l
     */
    @Override
    public void createNewOrder(Order o, ArrayList<MenuItem> l) {
        assert o!=null && l!=null;
        assert isWellFormated();
        int mapSize= map.size();
        map.put(o, l);
        setChanged();
        ArrayList<MenuItem> list= new ArrayList<>();
        for(MenuItem m1:l)
        {
            boolean ok=false;
            for(MenuItem m2:menuItems)
            {
                if(m1.getMenuItemId()==m2.getMenuItemId()&&m2.getClass().getSimpleName().equals("CompositeProduct")) { ok=true; break;}
            }
            if (ok) list.add(m1);
        }

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(o);
        objects.add(list);
        notifyObservers(objects);
        idOrder++;
        assert mapSize+1==map.size();
        assert isWellFormated();
    }

    /**
     * Delete an order
     * @param o
     */
    @Override
    public void deleteOrder(Order o) {
        assert o!=null;
        assert isWellFormated();
        int mapSize= map.size();
        map.remove(o);
        assert mapSize-1==map.size();
        assert isWellFormated();
    }

    /**
     * Compute price for a list of Menu Items
     * @param l
     * @return
     */
    @Override
    public float computePrice(ArrayList<MenuItem> l) {
        assert l!=null;
        assert isWellFormated();
        float total = 0;
        for (MenuItem e: l) {
            total+=e.computePrice();
        }
        assert total > 0;
        assert isWellFormated();
        return total;
    }

    /**
     * Generate bill in .txt format
     * @param o
     * @param l
     */
    @Override
    public void generateBill(Order o, ArrayList<MenuItem> l) {
        assert o!=null && l!=null;
        assert isWellFormated();
        FileWriter.generateBill(o,l);
        assert isWellFormated();
    }

    public Map<Order, ArrayList<MenuItem>> getMap() {
        return map;
    }

    public int getIdMenuItems() {
        return idMenuItems;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }
}
