import BusinessLogicClasses.Controller;
import DataAccessClasses.*;
import ModelClasses.Client;
import ModelClasses.Order;
import ModelClasses.OrderItems;
import ModelClasses.Product;
import PresentationClasses.DataRead;
import PresentationClasses.PdfClasss;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;



public class MainClass {
    public static void main(String[] args){

        if(args.length==1){
            Controller c = new Controller(args[0]);
            c.start();
            System.out.println("Success");
        }
    }
}
