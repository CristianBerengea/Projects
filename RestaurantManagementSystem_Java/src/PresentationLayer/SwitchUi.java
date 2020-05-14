package PresentationLayer;

import BusinessLayer.Restaurant;
import DataLayer.RestaurantSerializator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Gui start class
 */
public class SwitchUi extends JFrame{
    Restaurant restaurant;

    JButton waiterChef = new JButton("Waiter + Chef");
    JButton administrator = new JButton("Administrator");
    RestaurantSerializator rs = new RestaurantSerializator("restaurant.ser");
    public SwitchUi(Restaurant restaurant) {
        this.restaurant = restaurant;
        JPanel content = new JPanel();

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                rs.seerialization(restaurant);
                System.exit(0);
            }
        });

        waiterChef.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ChefGraphicalUserinterface chef = new ChefGraphicalUserinterface(restaurant);
                WaiterGraphicalUserinterface waiter = new WaiterGraphicalUserinterface(restaurant);
                restaurant.addObserver(chef);
                dispose();
            }
        });

        administrator.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                AdministratorGraphicalIserinterface adm = new AdministratorGraphicalIserinterface(restaurant);
                dispose();
            }
        });


        content.add(administrator);
        content.add(waiterChef);

        this.setContentPane(content);
        this.pack();
        setLocation(550,250);
        setTitle("Chef");
        setVisible(true);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
}
