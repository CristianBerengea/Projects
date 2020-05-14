package DataLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * dsdsd
 */
public class FileWriter {

    public static void generateBill(Order order, ArrayList<MenuItem> l){

        File file = new File("bill"+order.getOrderId()+".txt");
        java.io.FileWriter myWriter=null;
        try {
            file.createNewFile();
            myWriter = new java.io.FileWriter(file);
            myWriter.write("Order number: "+ order.getOrderId()+"         "+order.getDatee()+"\n\n");
            float total = 0;
            for (MenuItem e:l) {
                float aux = e.computePrice() * e.getQuantity();
                total+=aux;
                myWriter.write(e.getName()+"   x "+e.getQuantity()+"  "+ e.computePrice() +"   "+ aux +"\n");
            }
            myWriter.write("\n"+"Total: "+total);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Could create/save the file");
        }
    }
}
