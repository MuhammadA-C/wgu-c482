package com.example.muhammad.chambers.c482.pa.model;

/** This class is a data container to hold the selected items from the Main Menu to be used with other Screens.*/
public class SelectedItemHelper {
    private static Product mainMenuProductTableSelectedItem;
    private static Part mainMenuPartTableSelectedItem;

    /** This is the setMainMenuProductTableSelectedItem method.
     This method sets the mainMenuProductTableSelectedItem field.
     @param setProduct The product object to set*/
    public static void setMainMenuProductTableSelectedItem(Product setProduct) {
        mainMenuProductTableSelectedItem = setProduct;
    }

    /** This is the setMainMenuParTableSelectedItem method.
     This method sets the mainMenuPartTableSelectedItem field.
     @param setPart The part object to set*/
    public static void setMainMenuPartTableSelectedItem(Part setPart) {
        mainMenuPartTableSelectedItem = setPart;
    }

    /** This is the getMainMenuProductTableSelectedItem method.
     This method returns the mainMenuProductTableSelectedItem field.
     @return Returns the selected Product object*/
    public static Product getMainMenuProductTableSelectedItem() {
        return mainMenuProductTableSelectedItem;
    }

    /** This is the getMainMenuParTableSelectedItem method.
     This method returns the mainMenuPartTableSelectedItem field.
     @return Returns the selected Part object*/
    public static Part getMainMenuPartTableSelectedItem() {
        return mainMenuPartTableSelectedItem;
    }
}
