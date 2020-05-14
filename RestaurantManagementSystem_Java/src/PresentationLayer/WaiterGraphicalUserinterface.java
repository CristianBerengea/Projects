package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;
import DataLayer.RestaurantSerializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class WaiterGraphicalUserinterface extends JFrame {
    Restaurant restaurant;
    RestaurantSerializator rs = new RestaurantSerializator("restaurant.ser");
    DefaultTableModel modelMenu;
    DefaultTableModel modelOrder;
    DefaultTableModel modelOItems;
    DefaultTableModel modelNewOrder;
    JTable tableMenu;
    JTable tableOrder;
    JTable tableOItems;
    JTable tableNewOrder;
    JButton compPrice = new JButton("Compute Price");
    JButton billGen = new JButton("Generate bill");
    JButton createOrder = new JButton("Create Order");
    JButton deleteOrder = new JButton("Delete Order");
    JTextField quantityChooser = new JTextField(5);
    JTextField toalPrice = new JTextField(7);
    JTextField orderTable = new JTextField(7);
    /**
     * Construct and initialize  Administrator UI
     * @param restaurant
     */
    public WaiterGraphicalUserinterface(Restaurant restaurant) {
        this.restaurant = restaurant;
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                rs.seerialization(restaurant);
                System.exit(0);
            }
        });
        content.setLayout(new BorderLayout());

        JPanel pVert1 = new JPanel();
        pVert1.setLayout(new BoxLayout(pVert1, BoxLayout.Y_AXIS));
        JPanel pVert2 = new JPanel();
        pVert2.setLayout(new BoxLayout(pVert2, BoxLayout.Y_AXIS));
        JPanel pHoriz = new JPanel();
        pHoriz.setLayout(new BoxLayout(pHoriz, BoxLayout.X_AXIS));

        modelMenu = new DefaultTableModel()
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        modelMenu.addColumn("Id");
        modelMenu.addColumn("Name");
        modelMenu.addColumn("Price");
        modelMenu.addColumn("Quantity");
        tableMenu = new JTable(modelMenu);

        populateTableMenu();

        tableMenu.setFillsViewportHeight(true);
        JScrollPane pane = new JScrollPane(tableMenu);
        JPanel panelMenuItems = new JPanel();
        panelMenuItems.add(new JLabel("Menu Items:    Click for add to order   Select Quantity to be added:"));
        panelMenuItems.add(quantityChooser);
        pVert2.add(panelMenuItems);
        pVert2.add(pane);

        Panel panel1 = new Panel();
        panel1.add(new JLabel("Order Table: "));
        panel1.add(orderTable);
        panel1 .add(createOrder);

        Panel panel2 = new Panel();
        panel2.add(new JLabel("Order Total Price:"));
        panel2.add(toalPrice);
        panel2.add(compPrice);

        modelOrder = new DefaultTableModel()
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        modelOrder.addColumn("Id");
        modelOrder.addColumn("Table");
        modelOrder.addColumn("Date");
        modelOrder.addColumn("Price");
        tableOrder = new JTable(modelOrder);

        populateOrderTable();
        JScrollPane pane1 = new JScrollPane(tableOrder);
        pVert2.add(new JLabel("Orders : "));
        pVert2.add(pane1);
        Panel panel3 = new Panel();
        panel3.add(deleteOrder);
        panel3 .add(billGen);
        pVert2.add(panel3);

        modelOItems= new DefaultTableModel()
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        modelOItems.addColumn("Id");
        modelOItems.addColumn("Name");
        modelOItems.addColumn("Price");
        modelOItems.addColumn("Quantity");
        tableOItems = new JTable(modelOItems);
        JScrollPane pane3 = new JScrollPane(tableOItems);

        modelNewOrder= new DefaultTableModel()
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        modelNewOrder.addColumn("Id");
        modelNewOrder.addColumn("Name");
        modelNewOrder.addColumn("Price");
        modelNewOrder.addColumn("Quantity");
        tableNewOrder = new JTable(modelNewOrder);
        JScrollPane pane4 = new JScrollPane(tableNewOrder);

        pVert1.add(new JLabel("New Order Items: "));
        pVert1.add(pane4);
        pVert1.add(panel1);
        pVert1.add(panel2);
        pVert1.add(new JLabel("Order Items: "));
        pVert1.add(pane3);
        pHoriz.add(pVert1);
        pHoriz.add(pVert2);

        deleteOrder.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteOrder();
            }
        });

        billGen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });

        compPrice.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                compPrice();
            }
        });

        tableMenu.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int i = tableMenu.getSelectedRow();
                if(i>=0) {
                    int nededQuantity = 0;
                    boolean ok = true;
                    try {
                        nededQuantity = Integer.parseInt(quantityChooser.getText());
                    } catch (NumberFormatException ex) {
                        errMsg("Quantity should be a number!");
                        ok = false;
                    }
                    if (ok) {
                        int id = Integer.parseInt(modelMenu.getValueAt(i, 0).toString());
                        MenuItem found = null;
                        for (MenuItem m : restaurant.getMenuItems()) {
                            if (m.getMenuItemId() == id) found = m;
                        }
                        if (found.getClass().getSimpleName().equals("BaseProduct")) {
                            if (found.getQuantity() >= nededQuantity) {
                                modelMenu.setValueAt(found.getQuantity() - nededQuantity, i, 3);
                                found.setQuantity(found.getQuantity() - nededQuantity);
                                int pos = -1;
                                for (int j = 0; j < modelNewOrder.getRowCount(); j++) {
                                    int idd = Integer.parseInt(modelNewOrder.getValueAt(j, 0).toString());
                                    if (idd == id) {
                                        pos = j;
                                        break;
                                    }
                                }
                                if (pos != -1)
                                    modelNewOrder.setValueAt(Integer.parseInt(modelNewOrder.getValueAt(pos, 3).toString()) + nededQuantity, pos, 3);
                                else
                                    modelNewOrder.addRow(new Object[]{modelMenu.getValueAt(i, 0).toString(), modelMenu.getValueAt(i, 1).toString(), modelMenu.getValueAt(i, 2).toString(), nededQuantity});
                            } else errMsg("Insufficient stock");
                        } else {
                            for (MenuItem el : found.getProductItems()) {
                                for (MenuItem m : restaurant.getMenuItems()) {
                                    if (el.getMenuItemId() == m.getMenuItemId()) {
                                        if (el.getQuantity() * nededQuantity > m.getQuantity()) ok = false;
                                    }
                                }
                            }
                            if (ok) {
                                int pos = -1;
                                for (int j = 0; j < modelNewOrder.getRowCount(); j++) {
                                    int idd = Integer.parseInt(modelNewOrder.getValueAt(j, 0).toString());
                                    if (idd == id) {
                                        pos = j;
                                        break;
                                    }
                                }
                                if (pos != -1)
                                    modelNewOrder.setValueAt(Integer.parseInt(modelNewOrder.getValueAt(pos, 3).toString()) + nededQuantity, pos, 3);
                                else
                                    modelNewOrder.addRow(new Object[]{modelMenu.getValueAt(i, 0).toString(), modelMenu.getValueAt(i, 1).toString(), modelMenu.getValueAt(i, 2).toString(), nededQuantity});
                                for (MenuItem el : found.getProductItems()) {
                                    for (MenuItem m : restaurant.getMenuItems()) {
                                        if (el.getMenuItemId() == m.getMenuItemId()) {
                                            m.setQuantity(m.getQuantity() - el.getQuantity() * nededQuantity);
                                        }
                                    }
                                }
                                modelMenu.setRowCount(0);
                                for (MenuItem el : restaurant.getMenuItems()) {
                                    if (el.getClass().getSimpleName().equals("BaseProduct"))
                                        modelMenu.addRow(new Object[]{el.getMenuItemId(), el.getName(), el.computePrice(), el.getQuantity()});
                                    else
                                        modelMenu.addRow(new Object[]{el.getMenuItemId(), el.getName(), el.computePrice(), "-"});
                                }
                            } else errMsg("Insufficient stock");
                        }
                    }
                }
            }
        });

        tableOrder.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e){
                clearTableOItems();
                int i = tableOrder.getSelectedRow();
                int id = Integer.parseInt(modelOrder.getValueAt(i,0).toString());
                Order o = null;
                for(Order el: restaurant.getMap().keySet()) {
                    if(el.getOrderId()==id) o=el;
                }
                for(MenuItem m:restaurant.getMap().get(o)) {
                    modelOItems.addRow(new Object[]{m.getMenuItemId(),m.getName(),m.computePrice(),m.getQuantity()});
                }
            }
        });

        createOrder.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
              createOrder();
            }
        });

        content.add(pHoriz,BorderLayout.CENTER);
        this.setContentPane(content);
        this.pack();
        setTitle("Waiter");
        setVisible(true);
        setSize(1370, 730);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void populateOrderTable(){
        for(Order o: restaurant.getMap().keySet())
        {
            float price = 0;
            for(MenuItem m:restaurant.getMap().get(o))
            {
                price+=m.computePrice()*m.getQuantity();
            }
            modelOrder.addRow(new Object[]{o.getOrderId(),o.getTable(),o.getDatee(),price});
        }
    }

    private void populateTableMenu(){
        for(MenuItem e: restaurant.getMenuItems())
        {
            if(e.getClass().getSimpleName().equals("BaseProduct")) modelMenu.addRow(new Object[]{e.getMenuItemId(), e.getName(), e.computePrice(),e.getQuantity()});
            else modelMenu.addRow(new Object[]{e.getMenuItemId(), e.getName(), e.computePrice(),"-"});
        }
    }

    private void generateBill(){
        int i = tableOrder.getSelectedRow();
        if(i>=0)
        {
            int id = Integer.parseInt(modelOrder.getValueAt(i,0).toString());
            Order o = null;
            for(Order el: restaurant.getMap().keySet()) {
                if(el.getOrderId()==id) o=el;
            }
            restaurant.generateBill(o,restaurant.getMap().get(o));
            msg("Bill was successfully generated!");
        }
    }

    private void deleteOrder(){
        int i = tableOrder.getSelectedRow();
        if(i>=0)
        {
            int id = Integer.parseInt(modelOrder.getValueAt(i,0).toString());
            Order o = null;
            for(Order el: restaurant.getMap().keySet()) {
                if(el.getOrderId()==id) o=el;
            }
            restaurant.deleteOrder(o);
            modelOrder.removeRow(i);
            clearTableOItems();
        }
    }

    private void compPrice(){
        float priceT = 0;
        for (int i = 0; i < modelNewOrder.getRowCount(); i++) {
            float price = Float.parseFloat(modelNewOrder.getValueAt(i, 2).toString());
            int quantity = Integer.parseInt(modelNewOrder.getValueAt(i, 3).toString());
            priceT+=price*quantity;
        }
        toalPrice.setText(String.valueOf(priceT));
    }

    private void createOrder(){
        boolean ok =true;
        int tableNr=0;
        try{
            tableNr = Integer.parseInt(orderTable.getText());
        } catch (NumberFormatException ex) {
            ok = false;
        }
        if(ok) {
            Order o = new Order(restaurant.getIdOrder(),tableNr);
            ArrayList<MenuItem> l = new ArrayList<MenuItem>();
            float priceT = 0;
            for (int i = 0; i < modelNewOrder.getRowCount(); i++) {
                int id = Integer.parseInt(modelNewOrder.getValueAt(i, 0).toString());
                float price = Float.parseFloat(modelNewOrder.getValueAt(i, 2).toString());
                int quantity = Integer.parseInt(modelNewOrder.getValueAt(i, 3).toString());
                priceT+=price*quantity;
                l.add(new BaseProduct(id, modelNewOrder.getValueAt(i, 1).toString(), price, quantity));
            }
            restaurant.createNewOrder(o,l);
            modelOrder.addRow(new Object[]{o.getOrderId(),o.getTable(),o.getDatee(),priceT});
            clearNewOrder();
        } else errMsg("Table number should be a number!");
    }
    private void clearTableOItems(){
        modelOItems.setRowCount(0);
    }
    private void clearNewOrder(){
        modelNewOrder.setRowCount(0);
    }
    private void errMsg(String msg){
        JOptionPane.showMessageDialog(this, msg, "User error", JOptionPane.ERROR_MESSAGE);
    }
    private void msg(String msg){
        JOptionPane.showMessageDialog(this, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

}
