package PresentationLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;
import DataLayer.RestaurantSerializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ChefGraphicalUserinterface extends JFrame implements Observer {
    Restaurant restaurant;
    DefaultTableModel modelCompositeProduct;
    JTable tableCompositeProduct;
    JButton delete =new JButton("Delete");
    RestaurantSerializator rs = new RestaurantSerializator("restaurant.ser");
    /**
     * Construct and initialize  Administrator UI
     * @param restaurant
     */
    public ChefGraphicalUserinterface(Restaurant restaurant) {
        this.restaurant = restaurant;
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                rs.seerialization(restaurant);
                System.exit(0);
            }
        });

        modelCompositeProduct = new DefaultTableModel()
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        modelCompositeProduct.addColumn("Order Id");
        modelCompositeProduct.addColumn("Product Id");
        modelCompositeProduct.addColumn("Name");
        modelCompositeProduct.addColumn("Quantity");
        tableCompositeProduct = new JTable(modelCompositeProduct);
        JScrollPane pane = new JScrollPane(tableCompositeProduct);

        content.add(pane,BorderLayout.CENTER);


        delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tableCompositeProduct.getSelectedRow();
                if (i >= 0) {
                    modelCompositeProduct.removeRow(i);
                }
            }
        });
        JPanel panel = new JPanel();
        panel.add(delete);
        content.add(panel,BorderLayout.SOUTH);

        this.setContentPane(content);
        this.pack();

        setTitle("Chef");
        setVisible(true);
        setSize(1370, 730);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Object> ol =(ArrayList<Object>)arg;
        Order order = (Order)ol.get(0);
        ArrayList<MenuItem> list = (ArrayList<MenuItem>)ol.get(1);
        for(MenuItem e:list)
        {
                modelCompositeProduct.addRow(new Object[]{order.getOrderId(),e.getMenuItemId(),e.getName(),e.getQuantity()});
        }
    }
}
