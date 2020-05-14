package BusinessLogicClasses;

import DataAccessClasses.ClientDA;
import DataAccessClasses.OrderDA;
import DataAccessClasses.OrderItemsDA;
import DataAccessClasses.ProductDA;
import ModelClasses.Client;
import ModelClasses.Order;
import ModelClasses.OrderItems;
import ModelClasses.Product;
import PresentationClasses.Bill;
import PresentationClasses.DataRead;
import PresentationClasses.PdfClasss;

import java.util.Date;
import java.util.List;

/**
 * This class encapsulate the application logic
 */
public class Controller {
    private DataRead dr;
    private ClientDA clientDa;
    private ProductDA productDa;
    private OrderDA orderDa;
    private OrderItemsDA orderItemsDa;
    private PdfClasss<Client> pdfClient;
    private PdfClasss<Product> pdfProduct;
    private Bill bill;

    public Controller(String inPath) {
        dr = new DataRead(inPath);
        clientDa = new ClientDA();
        productDa = new ProductDA();
        orderDa = new OrderDA();
        productDa = new ProductDA();
        orderItemsDa = new OrderItemsDA();
        pdfClient = new PdfClasss<Client>();
        pdfProduct = new PdfClasss<Product>();
        bill = new Bill(orderItemsDa,productDa,orderDa,clientDa);
    }

    /**
     * Process all comands from a text file
     */
    public void start(){
        String comand = dr.readComand();
        while (comand!=null)
        {
            String [] comands = comand.split(" ");
            if(comands[0].equals("Insert"))
            {
                if(comands.length==5) {
                    if (comands[1].equals("client:")) {
                        comands[3]=comands[3].substring(0,comands[3].length()-1);
                        insertClient(comands[2],comands[3],comands[4]);
                    }
                    else if(comands[1].equals("product:")) {
                        comands[2]=comands[2].substring(0,comands[2].length()-1);
                        comands[3]=comands[3].substring(0,comands[3].length()-1);
                        try {
                            int a = Integer.parseInt(comands[3]);
                            float b = Float.parseFloat(comands[4]);
                            insertProduct(comands[2],a,b);
                        }catch (Exception e)
                        {
                            System.out.println("Invalid command");
                        }
                    }
                }else System.out.println("Invalid comand: "+comand);
            }
            else if(comands[0].equals("Report"))
            {
                if(comands.length==2) {
                    if (comands[1].equals("client")){
                        gerateClientReport();
                    }else if(comands[1].equals("product")){
                        geratepProductReport();
                    }else if(comands[1].equals("order")){
                        bill.generateOrderReport();
                    }
                }
                else System.out.println("Invalid comand: "+comand);

            }
            else if(comands[0].equals("Order:"))
            {
                if(comands.length==5) {
                    comands[2] = comands[2].substring(0, comands[2].length() - 1);
                    comands[3] = comands[3].substring(0, comands[3].length() - 1);
                    try {
                        int a = Integer.parseInt(comands[4]);
                        createOrder(comands[1], comands[2], comands[3], a);
                    } catch (Exception e) {
                        System.out.println("Invalid command");
                    }
                }else System.out.println("Invalid comand: "+comand);
            }
            else if(comands[0].equals("Delete")){
                if (comands[1].equals("client:")){
                    comands[3]=comands[3].substring(0,comands[3].length()-1);
                    deleteClient(comands[2],comands[3],comands[4]);
                }
                else if (comands[1].equals("Product:"))
                {
                    deleteProduct(comands[2]);
                }
            }
            comand=dr.readComand();
        }
    }

    /**
     * Delete a client and all his orders
     * @param firstName
     * @param lastName
     * @param address
     */
    public void deleteClient(String firstName,String lastName,String address){
        List<Client> lc = clientDa.findAllByFullName(firstName,lastName);
        Client client = null;
        for(Client c: lc)
            if(c.getAddress().equals(address)) {
                client = c;
                break;
            }
        if(client!=null)
        {
            clientDa.deleteByIntField(client.getId(),"id");
            List<Order> orders = orderDa.selectAllByIntField(client.getId(),"clientid");
            if(orders!=null) for (Order ord:orders)
            {
                orderItemsDa.deleteByIntField(ord.getId(),"orderid");
            }
            orderDa.deleteByIntField(client.getId(),"clientid");
        }
    }

    /**
     * Delete a protact from all orders
     * @param productName
     */
    public void deleteProduct(String productName)
    {
        Product p = productDa.findByStringField(productName,"name");
        orderItemsDa.deleteByIntField(p.getId(),"productid");
        productDa.deleteByStringField(productName,"name");
    }

    /**
     * Create order for client, with
     *  quantity. Also update the
     * product stock. Generate a bill in
     * pdf format with the order and total
     * price of quantity
     * @param firstName
     * @param lastName
     * @param productName
     * @param quantity
     */
    public void createOrder(String firstName,String lastName,String productName, int quantity){
        Client c = clientDa.findByFullName(firstName,lastName);
        if(c!=null)
        {
           Product p = productDa.findByStringField(productName,"name");
           if(p!=null)
           {
               if(p.getQuamtity()>=quantity)
               {
                   productDa.updateByIntIntField(p.getQuamtity()-quantity,"quantity",p.getId(),"id");
                   Order or = orderDa.findByIntField(c.getId(),"clientid");
                   if(or==null){
                       or = new Order(c.getId(),(new Date()).toString());
                       orderDa.insertRow(or);
                       or = orderDa.findByIntField(c.getId(),"clientid");
                       OrderItems oi = new OrderItems(or.getId(),p.getId(),quantity,p.getPrice());
                       orderItemsDa.insertRow(oi);
                   }
                   else
                   {
                       OrderItems oi = orderItemsDa.findByIntField(or.getId(),"orderid");
                       if(oi==null)
                       {
                           oi = new OrderItems(or.getId(),p.getId(),quantity,p.getPrice());
                           orderItemsDa.insertRow(oi);
                       }
                       else{
                           oi = orderItemsDa.findByOrderIdAndProductId(oi.getOrderId(),p.getId());
                           if(oi!=null)
                           {
                               if(oi.getPrice()==p.getPrice()) orderItemsDa.updateByOrderIdProductId(oi.getOrderId(),oi.getProductId(),oi.getQuantity()+quantity);
                               else {
                                   oi = new OrderItems(or.getId(),p.getId(),quantity,p.getPrice());
                                   orderItemsDa.insertRow(oi);
                               }
                           }else
                           {
                               oi = new OrderItems(or.getId(),p.getId(),quantity,p.getPrice());
                               orderItemsDa.insertRow(oi);
                           }
                       }
                   }
                   bill.generateBill(c,or);
               } else bill.generateStockInsufficient(p,quantity);
           }else System.out.println("Product doesn't exist");
        }else System.out.println("Client doesn't exist");
    }

    /**
     * Generate pdf reports with all
     * clients displayed in a
     * tabular form.
     * SELECT * query, displayed in a table
     * in a PDF file.
     */
    public  void gerateClientReport(){
        List<Client> lc = clientDa.SelectAll();
        if(!lc.isEmpty()) pdfClient.generatePdf(lc);
    }

    /**
     * Generate pdf reports with all
     * products displayed in a
     * tabular form.
     * SELECT * query, displayed in a table
     * in a PDF file.
     */
    public  void geratepProductReport(){
        List<Product> lp = productDa.SelectAll();
        if(!lp.isEmpty()) pdfProduct.generatePdf(lp);
    }

    /**
     * command Insert client
     * @param firstName
     * @param lastName
     * @param address
     */
    public void insertClient(String firstName,String lastName,String address){
        Client c = new Client(firstName,lastName,address);
        if(clientDa.searchElement(c)==false) clientDa.insertRow(c);
    }

    /**
     * command Insert product
     * @param name
     * @param quantity
     * @param price
     */
    public void insertProduct(String name,int quantity,float price){
        Product p= productDa.findByStringField(name,"name");
        if(p==null){
            Product pr = new Product(name,quantity,price);
            productDa.insertRow(pr);
        }else{
            productDa.updateByIntStringField(p.getQuamtity()+quantity,"quantity",name,"name");
            if(price!=p.getPrice()) productDa.updatePrice(price,p.getId());
        }
    }
}
