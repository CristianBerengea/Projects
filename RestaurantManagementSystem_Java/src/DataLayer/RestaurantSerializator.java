package DataLayer;

import BusinessLayer.Restaurant;

import java.io.*;

public class RestaurantSerializator {
    String fis;

    public RestaurantSerializator(String fis){
        this.fis=fis;
    }

    public void seerialization(Restaurant restaurant)
    {
        try {
            FileOutputStream fileOutputStream;
            ObjectOutputStream objectOutputStream;
            fileOutputStream = new FileOutputStream(fis);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(restaurant);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Restaurant deserialization(){

        Restaurant restaurant = null;
        try {
            FileInputStream fileInputStream;
            ObjectInputStream objectInputStream;
            fileInputStream = new FileInputStream(fis);
            objectInputStream = new ObjectInputStream(fileInputStream);
            restaurant = (Restaurant) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            restaurant = new Restaurant();
        }
        return restaurant;
    }

}
