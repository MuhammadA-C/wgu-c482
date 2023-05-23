package com.example.muhammad.chambers.c482.pa.model;

/** This class creates an InHouse Part.*/
public class InHouse extends Part{
    private int machineId;

    /** This is the Constructor for the InHouse Part class.
     This Constructor sets all the fields for the InHouse Part object.
     @param machineId the machineId to set
     @param id the id to set
     @param max the max to set
     @param min the min to set
     @param name the name to set
     @param price the price to set
     @param stock the stock to set*/
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.setMachineId(machineId);
    }

    /** This is the setMachineId method.
     This method sets the machineId field.
     @param machineId the machineId to set*/
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /** This is the getMachineId method.
     This method returns the machineId field.
     @return Returns the machineId as an integer*/
    public int getMachineId() {
        return this.machineId;
    }
}
