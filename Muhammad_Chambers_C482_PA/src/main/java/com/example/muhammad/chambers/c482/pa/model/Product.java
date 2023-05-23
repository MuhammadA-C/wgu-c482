package com.example.muhammad.chambers.c482.pa.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class creates a Product.*/
public class Product {
    //Fields
    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** This is the Constructor for the Product class.
     This Constructor sets all the fields for the Product object.
     @param id the id to set
     @param max the max to set
     @param min the min to set
     @param name the name to set
     @param price the price to set
     @param stock the stock to set*/
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
        this.setMin(min);
        this.setMax(max);
        this.associatedParts = FXCollections.observableArrayList();
    }

    /** This is the setId method.
     This method sets the id field.
     @param id the id to set*/
    public void setId(int id) {
        this.id = id;
    }

    /** This is the setName method.
     This method sets the name field.
     @param name the name to set*/
    public void setName(String name) {
        this.name = name;
    }

    /** This is the setPrice method.
     This method sets the price field.
     @param price the price to set*/
    public void setPrice(double price) {
        this.price = price;
    }

    /** This is the setStock method.
     This method sets the stock field.
     @param stock the stock to set*/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** This is the setMin method.
     This method sets the min field.
     @param min the min to set*/
    public void setMin(int min) {
        this.min = min;
    }

    /** This is the setMax method.
     This method sets the max field.
     @param max the max to set*/
    public void setMax(int max) {
        this.max = max;
    }

    /** This is the getId method.
     This method returns the id field.
     @return Returns the id*/
    public int getId() {
        return this.id;
    }

    /** This is the getName method.
     This method returns the name field.
     @return Returns the name*/
    public String getName() {
        return this.name;
    }

    /** This is the getPrice method.
     This method returns the price field.
     @return Returns the price*/
    public double getPrice() {
        return this.price;
    }

    /** This is the getStock method.
     This method returns the stock field.
     @return Returns the stock*/
    public int getStock() {
        return this.stock;
    }

    /** This is the getMin method.
     This method returns the min field.
     @return Returns the min*/
    public int getMin() {
        return this.min;
    }

    /** This is the getMax method.
     This method returns the max field.
     @return Returns the max*/
    public int getMax() {
        return this.max;
    }

    /** This is the deleteAssociatedPart method.
     This method deletes the associated part from a Product object.
     @param selectedAssociatedPart the part to delete
     @return Returns a boolean*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return this.associatedParts.remove(selectedAssociatedPart);
    }

    /** This is the addAssociatedPart method.
     This method adds the associated part to a Product object.
     @param part the part to add*/
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /** This is the getAllAssociatedParts method.
     This method returns a list containing all the associated parts for a Product object.
     @return Returns a list of parts*/
    public ObservableList<Part> getAllAssociatedParts() {
        return this.associatedParts;
    }

}
