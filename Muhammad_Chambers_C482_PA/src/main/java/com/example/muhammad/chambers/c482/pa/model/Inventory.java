package com.example.muhammad.chambers.c482.pa.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class contains methods for Part and Product objects.*/
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** This is the addPart method.
     This method adds a Part object to the allParts list.
     @param newPart the newPart to set*/
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** This is the addProduct method.
     This method adds a Product object to the allProducts list.
     @param newProduct the newProduct to set*/
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /** This is the getAllParts method.
     This method returns the allParts list.
     @return Returns a list of Parts*/
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /** This is the getAllProducts method.
     This method returns the allProducts list.
     @return Returns a list of products*/
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /** This is the lookUpPart method.
     This method searches through the allParts list comparing the inputted id to the Part ids in the list and returns the Part with the matching id.
     @param partId the partId for the part to lookup
     @return Returns a Part object if found or NULL*/
    public static Part lookUpPart(int partId) {
        for(Part i: allParts) {
            if(i.getId() == partId) {
                return i;
            }
        }
        return null;
    }

    /** This is the lookUpProduct method.
     This method searches through the allProducts list comparing the inputted id to the Product ids in the list and returns the Product with the matching id.
     @param productId the productId for the product to lookup
     @return Returns a Product object if found or NULL*/
    public static Product lookUpProduct(int productId) {
        for(Product i: allProducts) {
            if(i.getId() == productId) {
                return i;
            }
        }
        return null;
    }

    /** This is the lookUpPart method.
     This method compares all Parts in the allParts list by name to the inputted name and returns the first Part with the matching name.
     @param partName the partName for the part to lookup
     @return Returns a Part object if found or NULL*/
    public static ObservableList<Part> lookUpPart(String partName) {
        for(Part i: allParts) {
            if(i.getName().equals(partName)) {
                return allParts;
            }
        }
        return null;
    }

    /** This is the lookUpProduct method.
     This method compares all Products in the allProducts list by name to the inputted name and returns the first Product with the matching name.
     @param productName the productName for the product to lookup
     @return Returns a Product object if found or NULL*/
    public static ObservableList<Product> lookUpProduct(String productName) {
        for(Product i: allProducts) {
            if(i.getName().equals(productName)) {
                return  allProducts;
            }
        }
        return null;
    }

    /** This is the updatePart method.
     This method updates the Part in the allParts list at the specified index.
     @param index the index for the part you want to replace
     @param selectedPart the new Part you want to replace the old one with*/
    public static void updatePart(int index, Part selectedPart) {
        for(int i = 0; i < allParts.size(); i++) {
            if(i == index) {
                allParts.set(index, selectedPart);
                break;
            }
        }
    }

    /** This is the updateProduct method.
     This method updates the Product in the allProducts list at the specified index.
     @param index the index for the product you want to replace
     @param newProduct the new Product you want to replace the old one with*/
    public static void updateProduct(int index, Product newProduct) {
        for(int i = 0; i < allProducts.size(); i++) {
            if(i == index) {
                allProducts.set(index, newProduct);
                break;
            }
        }
    }

    /** This is the deletePart method.
     This method deletes the specified Part from the allParts list.
     @param selectedPart the part you want to delete
     @return Returns a boolean value*/
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /** This is the deleteProduct method.
     This method deletes the specified Product from the allProducts list.
     @param selectedProduct the product you want to delete
     @return Returns a boolean value*/
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }
}
