package com.example.muhammad.chambers.c482.pa.model;

/** This class creates an Outsourced Part.*/
public class Outsourced extends Part{
    private String companyName;

    /** This is the Constructor for the Outsourced Part class.
     This Constructor sets all the fields for the Outsourced Part object.
     @param stock the stock to set
     @param price the price to set
     @param name the name to set
     @param min the min to set
     @param max the max to set
     @param id the id to set
     @param companyName the companyName to set*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.setCompanyName(companyName);
    }

    /** This is the setCompanyName method.
     This method sets the companyName field.
     @param companyName the companyName to set*/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /** This is the getCompanyName method.
     This method returns the companyName field.
     @return Returns the companyName as a String*/
    public String getCompanyName() {
        return this.companyName;
    }
}
