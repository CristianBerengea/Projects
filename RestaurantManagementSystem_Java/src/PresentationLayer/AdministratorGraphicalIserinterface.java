package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;
import DataLayer.RestaurantSerializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AdministratorGraphicalIserinterface extends JFrame {
    Restaurant restaurant;
    RestaurantSerializator rs = new RestaurantSerializator("restaurant.ser");
    JTable tableMenuItems;
    JTable table1;
    JTextField textId = new JTextField(60);
    JTextField textName = new JTextField(60);
    JTextField textPrice = new JTextField(60);
    JTextField textQuantity = new JTextField(60);
    JTextField textId1 = new JTextField(60);
    JTextField textName1 = new JTextField(60);
    JButton add = new JButton("Add Base Product");
    JButton update = new JButton("Update");
    JButton delete = new JButton("Delete");
    JButton clear = new JButton("Clear");
    JTextField quantityChooser = new JTextField(5);
    JButton move = new JButton("<<<");
    JButton addComposite = new JButton("Add Composite Product");
    JButton deleteIngredient = new JButton("Delete ingredient");
    JButton clearIngredients = new JButton("Clear Ingredients");
    DefaultTableModel model;
    DefaultTableModel model1;
    private  boolean movee = false;

    /**
     * Construct and initialize  Administrator UI
     * @param restaurant
     */
    public AdministratorGraphicalIserinterface(Restaurant restaurant) {
        this.restaurant = restaurant;

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                rs.seerialization(restaurant);
                System.exit(0);
            }
        });

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        JPanel pp = new JPanel();
        pp.setLayout(new BoxLayout(pp, BoxLayout.X_AXIS));

        JPanel pVert1 = new JPanel();
        pVert1.setLayout(new BoxLayout(pVert1, BoxLayout.Y_AXIS));
        JPanel pVert2 = new JPanel();
        pVert2.setLayout(new BoxLayout(pVert2, BoxLayout.Y_AXIS));
        JPanel pHoriz = new JPanel();
        pHoriz.setLayout(new BoxLayout(pHoriz, BoxLayout.X_AXIS));

        model = new DefaultTableModel()
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Quantity");
        tableMenuItems = new JTable(model);
        populateTableMenuItems();
        tableMenuItems.setFillsViewportHeight(true);
        JScrollPane pane = new JScrollPane(tableMenuItems);

        JLabel l1 = new JLabel("Product Name: ");
        JLabel l2 = new JLabel("Product Price: ");
        JLabel l3 = new JLabel("Product Quantity: ");
        JLabel l4 = new JLabel("Product Id: ");

        Panel p1 = new Panel();
        p1.add(l1);
        p1.add(textName);
        Panel p2 = new Panel();
        p2.add(l2);
        p2.add(textPrice);
        Panel p3 = new Panel();
        p3.add(l3);
        p3.add(textQuantity);
        Panel p4 = new Panel();
        p4.add(l4);
        p4.add(textId);
        Panel p5 = new Panel();
        p5.add(add);
        p5.add(update);
        p5.add(delete);
        p5.add(clear);

        JLabel l11 = new JLabel("Product Name: ");
        JLabel l22 = new JLabel("Product Price: ");
        JLabel l33 = new JLabel("Product Quantity: ");
        JLabel l44 = new JLabel("Product Id: ");

        Panel pane4 = new Panel();
        pane4.add(l11);
        pane4.add(textName1);
        Panel pane3 = new Panel();
        pane3.add(addComposite);
        pane3.add(deleteIngredient);
        pane3.add(clearIngredients);

        model1 = new DefaultTableModel()
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        model1.addColumn("Id");
        model1.addColumn("Name");
        model1.addColumn("Price");
        model1.addColumn("Quantity");
        table1 = new JTable(model1);
        JScrollPane pane1 = new JScrollPane(table1);

        pVert1.add(p1);
        pVert1.add(p2);
        pVert1.add(p3);
        pVert1.add(p4);
        pVert1.add(p5);
        JPanel aux = new JPanel();
        aux.setLayout(new BoxLayout(aux, BoxLayout.Y_AXIS));
        JLabel labelCompositeIngredients=new JLabel("Composite Product Ingredients :");
        aux.add(labelCompositeIngredients);
        aux.add(pane1);
        pHoriz.add(aux);
        JPanel pane2= new JPanel();
        pane2.add(move);
        pane2.add(quantityChooser);
        pHoriz.add(pane2);
        pVert1.add(pHoriz);
        pVert1.add(pane4);
        pVert1.add(pane3);
        pp.add(pVert1);
        JPanel aux1 = new JPanel();
        aux1.setLayout(new BoxLayout(aux1, BoxLayout.Y_AXIS));
        JLabel labelMenuItems=new JLabel("Composite Product Ingredients :");
        aux1.add(labelMenuItems);
        aux1.add(pane);
        pp.add(aux1);
        textId.setEditable(false);
        textId1.setEditable(false);

        add.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ok = true;
                int quantity=0;
                float price = 0;
                try{
                    price = Float.parseFloat(textPrice.getText());
                }catch (Exception z)
                {
                    ok=false;
                    errMsg("Price should be a number!");
                }

                try{
                    quantity = Integer.parseInt(textQuantity.getText());
                }catch (Exception z)
                {
                    ok=false;
                    errMsg("Quantity should be a number!");
                }
                if(ok) {
                    int id=-1;
                    int quant=0;
                    for (int i=0;i<model.getRowCount();i++)
                    {
                        if(model.getValueAt(i, 1).toString().equals(textName.getText()) && Float.parseFloat(model.getValueAt(i, 2).toString())==price){
                            id = Integer.parseInt(model.getValueAt(i, 0).toString());
                            quant = Integer.parseInt(model.getValueAt(i, 3).toString());
                            model.setValueAt(quantity + quant,i,3);
                            break;
                        }
                    }
                    if(id==-1) {
                        textId.setText(String.valueOf(restaurant.getIdMenuItems()));
                        restaurant.addNewMenuItem(new BaseProduct(Integer.parseInt(textId.getText()), textName.getText(), price, quantity));
                        model.addRow(new Object[]{textId.getText(), textName.getText(), textPrice.getText(), textQuantity.getText()});
                    }
                    else restaurant.editMenuItem(id,new BaseProduct(Integer.parseInt(textId.getText()),textName.getText(),price,quantity+quant));
                    clear();
                }
            }
        });

        addComposite.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MenuItem> l = new ArrayList<MenuItem>();
                boolean ok =true;
                for(int i=0;i<model1.getRowCount();i++){
                    int quantity=0;
                    int id = 0;
                    float price = 0;
                    price = Float.parseFloat(model1.getValueAt(i, 2).toString());
                    quantity = Integer.parseInt(model1.getValueAt(i, 3).toString());
                    id = Integer.parseInt(model1.getValueAt(i, 0).toString());
                    if(quantity>0) {
                        l.add(new BaseProduct(id,model1.getValueAt(i, 1).toString(),price,quantity));
                    } else {
                        ok = false;
                        errMsg("Quantity can't be negative!");
                    }
                }
                if(ok){
                    MenuItem m = new CompositeProduct(restaurant.getIdMenuItems(),textName1.getText(),l);
                    model.addRow(new Object[]{m.getMenuItemId(),textName1.getText(),m.computePrice(),"-"});
                    System.out.println(m);
                    restaurant.addNewMenuItem(m);
                    clear();
                }
            }
        });

        update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ok = true;
                int quantity=0;
                float price = 0;
                int id = Integer.parseInt(textId.getText());
                try{
                    price = Float.parseFloat(textPrice.getText());
                }catch (Exception z)
                {
                    ok=false;
                    errMsg("Price should be a number!");
                }

                try{
                    quantity = Integer.parseInt(textQuantity.getText());
                }catch (Exception z)
                {
                    ok=false;
                    errMsg("Quantity should be a number!");
                }

                boolean ok1 = false;
                for (int i=0;i<model.getRowCount();i++)
                {
                    if(Integer.parseInt(model.getValueAt(i, 0).toString())==id){
                        ok1 = true;
                    }
                }

                if(!ok1)  errMsg("This product doesn't exist! Add it!");

                if(ok&&ok1) {
                    restaurant.editMenuItem(id,new BaseProduct(id,textName.getText(),price,quantity));
                    model.removeRow(tableMenuItems.getSelectedRow());
                    model.addRow(new Object[]{id, textName.getText(), textPrice.getText(), textQuantity.getText()});
                    int x= model.getRowCount()-1;
                    tableMenuItems.setRowSelectionInterval(x,x);
                    clear();
                }
            }
        });

        delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tableMenuItems.getSelectedRow();
                if(i>=0) {
                    restaurant.deleteNewMenuItem(Integer.parseInt(textId.getText()));
                    if (i >= 0) {
                        model.removeRow(i);
                    }
                    clear();
                }
            }
        });

        deleteIngredient.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table1.getSelectedRow();
                    if (i >= 0) {
                        model1.removeRow(i);
                    }
            }
        });

        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        tableMenuItems.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int i = tableMenuItems.getSelectedRow();
                if(!movee) cleearTable1();
                textId.setText(model.getValueAt(i, 0).toString());
                textName.setText(model.getValueAt(i, 1).toString());
                textPrice.setText(model.getValueAt(i, 2).toString());
                textQuantity.setText(model.getValueAt(i, 3).toString());

                if(!movee) {
                    MenuItem found = null;
                    for (MenuItem m : restaurant.getMenuItems()) {
                        if (m.getMenuItemId() == Integer.parseInt(model.getValueAt(i, 0).toString())) found = m;
                    }

                    if (found.getClass().getSimpleName().equals("CompositeProduct")) {
                        ArrayList<MenuItem> ll = found.getProductItems();
                        for (MenuItem el : ll) {
                            model1.addRow(new Object[]{el.getMenuItemId(), el.getName(), el.getPrice(), el.getQuantity()});
                        }
                    }
                }
                else{
                    int pos=-1;
                    int id = Integer.parseInt(model.getValueAt(i, 0).toString());
                    for(int j=0;j<model1.getRowCount();j++) {
                        if(id==Integer.parseInt(model1.getValueAt(j,0).toString()))
                        {
                            pos=j;
                        }
                    }
                    int nededQuantity=-1;
                    try{
                        nededQuantity = Integer.parseInt(quantityChooser.getText());
                    }
                    catch (Exception ex)
                    {
                        errMsg("Chose a quantity!");
                    }
                    if(nededQuantity!=-1){
                    if(pos != -1){
                        nededQuantity+=Integer.parseInt(model1.getValueAt(pos,3).toString());
                    }
                        if(pos!=-1) model1.removeRow(pos);
                        model1.addRow(new Object[]{model.getValueAt(i, 0).toString(),model.getValueAt(i, 1).toString(),model.getValueAt(i, 2).toString(),nededQuantity});
                    }
                }
            }
        });

        move.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                activateMove();
            }
        });

        clearIngredients.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cleearTable1();
                textName1.setText("");
            }
        });

        content.add(pp,BorderLayout.CENTER);
        this.setContentPane(content);
        this.pack();

        setTitle("Administrator");
        setVisible(true);
        setSize(1370, 730);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void populateTableMenuItems(){
        for(MenuItem e: restaurant.getMenuItems())
        {
            if(e.getClass().getSimpleName().equals("BaseProduct")) model.addRow(new Object[]{e.getMenuItemId(), e.getName(), e.computePrice(),e.getQuantity()});
            else model.addRow(new Object[]{e.getMenuItemId(), e.getName(), e.computePrice(),"-"});
        }
    }

    private void activateMove(){
        if(movee) {
            movee = false;
            move.setBackground(Color.lightGray);
        }
        else
        {
            cleearTable1();
            movee = true;
            move.setBackground(Color.green);
        }
    }

    private void clear(){
        tableMenuItems.clearSelection();
        textId.setText(String.valueOf(restaurant.getIdMenuItems()));
        textPrice.setText("");
        textName.setText("");
        textQuantity.setText("");
        textName1.setText("");
        cleearTable1();
    }

    private void cleearTable1(){
        model1.setRowCount(0);
    }

    private void errMsg(String msg){
        JOptionPane.showMessageDialog(this, msg, "User error", JOptionPane.ERROR_MESSAGE);
    }
}
