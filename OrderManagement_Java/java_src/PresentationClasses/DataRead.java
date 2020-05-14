package PresentationClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DataRead {

    private  String inPath;
    private Scanner sc;

    public DataRead(String inPath) {
        this.inPath = inPath;
        File file = new File(inPath);
        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not open the file!");
        }
    }

    public String readComand()
    {
        String comand = null;
        try{
            if(sc.hasNextLine()) comand = sc.nextLine();
            else{
                sc.close();
                return null;
            }
        } catch (Exception e)
        {
            sc.close();
            System.out.println("Invalid file!");
        }
        return comand;
    }
}
