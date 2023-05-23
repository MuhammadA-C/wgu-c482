package com.example.muhammad.chambers.c482.pa.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

/** This class is a data container to help with storing the data for the search box filter.*/
public class FilteredHelper {
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();

    /** This is the addFilteredParts method.
     This method adds a Part to the filteredParts list.
     @param part the part to add*/
    public static void addFilteredParts(Part part) {
        filteredParts.add(part);
    }

    /** This is the addFilteredProducts method.
     This method adds a Product to the filteredProducts list.
     @param product the product to add*/
    public static void addFilteredProducts(Product product) {
        filteredProducts.add(product);
    }

    /** This is the getFilteredParts method.
     This method returns the filteredParts list.
     @return Returns a list of Part objects*/
    public static ObservableList<Part> getFilteredParts() {
        return filteredParts;
    }

    /** This is the getFilteredProducts method.
     This method returns the filteredProducts list.
     @return Returns a list of Product objects*/
    public static ObservableList<Product> getFilteredProducts() {
        return filteredProducts;
    }

    /** This is the filterPart method.
     This method compares the inputted name from the searchBox TextField to Parts names in the allParts list and adds any matches to the filteredParts list.
     @param searchBox the TextField searchBox
     @return Returns a list of Part objects*/
    public static ObservableList<Part> filterPart(TextField searchBox) {
        /*
        Searches through the allParts list comparing the part name
        to the inputted search value, and adds any matches it finds to a list.
         */
        for(Part i: Inventory.getAllParts()) {
            if(i.getName().contains(searchBox.getText())) {
                addFilteredParts(i);
            }
        }
        return getFilteredParts();
    }

    /** This is the filterProduct method.
     This method compares the inputted name from the searchBox TextField to Products names in the allProducts list and adds any matches to the filteredProducts list.
     @param searchBox the TextField searchBox
     @return Returns a list of Product objects*/
    public static ObservableList<Product> filterProduct(TextField searchBox) {
        /*
        Searches through the allProducts list comparing the product name
        to the inputted search value, and adds any matches it finds to a list.
         */
        for(Product i: Inventory.getAllProducts()) {
            if(i.getName().contains(searchBox.getText())) {
                addFilteredProducts(i);
            }
        }
        return getFilteredProducts();
    }

    /** This is the clearPartsListWithItems method.
     This method removes all items from the Parts list if there are any.
     @param list a Part list to clear all of its contents*/
    public static void clearPartsListWithItems(ObservableList<Part> list) {
        if(list.size() > 0) {
            list.clear();
        }
    }

    /** This is the clearProductsListWithItems method.
     This method removes all items from the Products list if there are any.
     @param list a Product list to clear all of its contents*/
    public static void clearProductsListWithItems(ObservableList<Product> list) {
        if(list.size() > 0) {
            list.clear();
        }
    }

}
