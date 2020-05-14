import BusinessLayer.BaseProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;
import DataLayer.RestaurantSerializator;
import PresentationLayer.SwitchUi;

public class MainClass {
    public static void main(String[] args) {

       // Order o = new Order(1,1);
       // ArrayList<MenuItem> l = new ArrayList<MenuItem>();
       MenuItem m1 = new BaseProduct(1,"pepene",3.5f,10);
        MenuItem m2 = new BaseProduct(2,"pepene",3.5f,7);
       // MenuItem m2 = new BaseProduct(1,"apa",2.5f,70);
       // l.add(m1);
       // l.add(m2);

        //Restaurant r= new Restaurant();

        //WaiterGraphicalUserinterface w = new WaiterGraphicalUserinterface(r);
        //ChefGraphicalUserinterface c = new ChefGraphicalUserinterface();
        //r.addObserver(c);

       // MenuItem m3 = new CompositeProduct(l);
        //r.createNewOrder(o,l);

        //ArrayList<MenuItem> l1 = new ArrayList<MenuItem>();
       // l1.add(m1);
       // l1.add(m2);

        //r.addNewMenuItem(m1);
       // r.addNewMenuItem(m2);

        //Order o2 = new Order(0,9);
        //Order o1 = new Order(4,5);


        //System.out.println(r);
        //System.out.println("\n");

        //c.setVisible(false);
        RestaurantSerializator rs = new RestaurantSerializator("restaurant.ser");
        Restaurant restaurant = rs.deserialization();
        SwitchUi start = new SwitchUi(restaurant);
        //restaurant.createNewOrder(o2,l1);
        //restaurant.createNewOrder(o1,l1);
        //AdministratorGraphicalIserinterface administrator = new AdministratorGraphicalIserinterface(restaurant);
        //WaiterGraphicalUserinterface waiter = new WaiterGraphicalUserinterface(restaurant);
       // ChefGraphicalUserinterface chef = new ChefGraphicalUserinterface(restaurant);
       // restaurant.addObserver(chef);


        //rs.seerialization(r);

        //Restaurant v = rs.deserialization();/////
        //assert v.isWellFormated();/////

        //FileWriter.generateBill(o,l);

        //ArrayList<MenuItem> ll = v.getMenuItems();

       // System.out.println(v);
        //System.out.println("\n");

        //System.out.println(ll);

        //AdministratorGraphicalIserinterface a = new AdministratorGraphicalIserinterface(r);

        //generateBill(o2, l1);
    }
}
