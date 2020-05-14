package PresentationClasses;

import DataAccessClasses.ClientDA;
import DataAccessClasses.OrderDA;
import DataAccessClasses.OrderItemsDA;
import DataAccessClasses.ProductDA;
import ModelClasses.Client;
import ModelClasses.Order;
import ModelClasses.OrderItems;
import ModelClasses.Product;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * This class contain methods wich generate a pdf bill for an order
 */
public class Bill {
    private OrderItemsDA orderItemsDa;
    private ProductDA productDa;
    private OrderDA orderDa;
    private ClientDA clientDA;
    private static int contor = 0;
    private static int contor1 = 0;

    public Bill(OrderItemsDA orderItemsDa, ProductDA productDa, OrderDA orderDa, ClientDA clientDA) {
        this.orderItemsDa = orderItemsDa;
        this.productDa = productDa;
        this.orderDa = orderDa;
        this.clientDA = clientDA;
    }

    /**
     * Generate a bill wich contain all products for a given order
     * @param client
     * @param order
     */
    public void generateBill(Client client, Order order){
        contor++;
        Document doc =new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("BillPdf"+contor+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        doc.open();
        Paragraph par = new Paragraph("Bill "+ order.getId() + "                                        Order date: "+order.getDate() +"\n");
        par.add("Client Name: "+ client.getFirst_name()+" "+ client.getLast_name()+"           Address: " + client.getAddress()+"\n");
        PdfPTable table = new PdfPTable(3);
        table.addCell("Product");
        table.addCell("Quantity");
        table.addCell("Price");

        List<OrderItems> ls =  orderItemsDa.selectAllByIntField(order.getId(),"orderid");
        float sum = 0;
        for (OrderItems e:ls) {
            Product p = productDa.findById(e.getProductId());
            table.addCell(p.getName());
            table.addCell(String.valueOf(e.getQuantity()));
            table.addCell(String.valueOf(e.getPrice()));
           sum+=e.getPrice()*e.getQuantity();
        }
        par.add(table);
        par.add("\nTotal Price:  "+sum+"\n");

        try {
            doc.add(par);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        doc.close();
    }

    /**
     * Generate a report wich contain all orders
     */
    public void generateOrderReport(){
        contor1++;
        Document doc =new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("pdfOrder"+contor1+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        doc.open();
        Paragraph header = new Paragraph("Order Report\n");
        try {
            doc.add(header);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        List<Order> lo = orderDa.SelectAll();

        for(Order o: lo)
        {
            Client c= clientDA.findById(o.getClientId());
            Paragraph paragraf = new Paragraph("Bill "+ o.getId() + "            Order date: "+o.getDate() +"\n");
            paragraf.add("Client Name: "+ c.getFirst_name()+" "+c.getLast_name()+"           Address: " + c.getAddress()+"\n");
            PdfPTable table = new PdfPTable(3);
            table.addCell("Product");
            table.addCell("Quantity");
            table.addCell("Price");

            List<OrderItems> ls =  orderItemsDa.selectAllByIntField(o.getId(),"orderid");
            float sum = 0;
            for (OrderItems e:ls) {
                Product p = productDa.findById(e.getProductId());
                table.addCell(p.getName());
                table.addCell(String.valueOf(e.getQuantity()));
                table.addCell(String.valueOf(e.getPrice()));
                sum+=e.getPrice()*e.getQuantity();
            }
            paragraf.add(table);
            paragraf.add("\nTotal Price:  "+sum+"\n\n\n");

            try {
                doc.add(paragraf);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        doc.close();
    }

    /**
     * Generate pdf with an error mesage, demand greater than stock
     * @param p
     * @param quantity
     */
    public void generateStockInsufficient(Product p,int quantity){
        contor++;
        Document doc =new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("errorBillPdf"+contor+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        doc.open();
        Paragraph par = new Paragraph("Error \nProduct  "+ p.getName() + " stock is: "+p.getQuamtity() +"\n");
        par.add("Your demand is "+quantity);

        try {
            doc.add(par);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        doc.close();
    }
}
