package Entity;

import java.io.*;
import javax.swing.*;

public class Medicine {
    private String id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private String manufacturer;
    
    public Medicine() {}
    
    public Medicine(String id, String name, String category, double price, 
                   int quantity, String manufacturer) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
    }
    
    // Setters and Getters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public void saveToFile() {
        try {
            File file = new File("./Data/medicines.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            
            FileWriter fwriter = new FileWriter(file, true);
            fwriter.write("========================================\n");
            fwriter.write("ID: " + id + "\n");
            fwriter.write("Name: " + name + "\n");
            fwriter.write("Category: " + category + "\n");
            fwriter.write("Price: Taka" + price + "\n");
            fwriter.write("Quantity: " + quantity + "\n");
            fwriter.write("Manufacturer: " + manufacturer + "\n");
            fwriter.write("========================================\n");
            
            fwriter.flush();
            fwriter.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving medicine data");
        }
    }
}