package PresentationClasses;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDate;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * This class generate reports on pdf format
 *
 */
public class PdfClasss<T> {
    private static int contor = 0;

    public void generatePdf(List<T> var){
        contor++;
        Document doc =new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("pdf"+var.get(0).getClass().getSimpleName()+contor+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        doc.open();
        Paragraph par = new Paragraph(var.get(0).getClass().getSimpleName()+" report\n");
        PdfPTable table = new PdfPTable(var.get(0).getClass().getDeclaredFields().length-1);

        par.add(new Date().toString());

        for(Field fild : var.get(0).getClass().getDeclaredFields()){
            fild.setAccessible(true);
            if(fild.getName()!="id") table.addCell(fild.getName());
        }

        for (T c:var) {
            for(Field fild : c.getClass().getDeclaredFields())
            {
                fild.setAccessible(true);
                if(fild.getName()!="id")
                try {
                    table.addCell(fild.get(c).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }

        par.add(table);

        try {
            doc.add(par);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        doc.close();
    }
}
