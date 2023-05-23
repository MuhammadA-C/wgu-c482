package com.example.muhammad.chambers.c482.pa.controller;

import com.example.muhammad.chambers.c482.pa.helper.*;
import com.example.muhammad.chambers.c482.pa.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class holds the functionality for the Add Product Screen.*/
public class AddProductController implements Initializable {
    private FilePaths filePath = new FilePaths();
    private Part selectedPartToAdd;
    private Part selectedPartToRemove;
    private ObservableList<Part> addPartsList = FXCollections.observableArrayList();

    @FXML
    private TableView<Part> addProductBottomTableView;
    @FXML
    private TableView<Part> addProductTopTableView;
    @FXML
    private TableColumn<Product, Integer> invLevelBottomCol;
    @FXML
    private TableColumn<Product, Integer> invLevelTopCol;
    @FXML
    private TextField invTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TableColumn<Part, Integer> partIdBottomCol;
    @FXML
    private TableColumn<Part, Integer> partIdTopCol;
    @FXML
    private TableColumn<Part, String> partNameBottomCol;
    @FXML
    private TableColumn<Part, String> partNameTopCol;
    @FXML
    private TableColumn<Part, Double> priceCostPerUnitBottomCol;
    @FXML
    private TableColumn<Part, Double> priceCostPerUnitTopCol;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField searchBoxTxt;

    /** This is the onActionAddProduct method.
     This method is an ActionEvent method for the add button on the Add Product Screen to add a part to the Product.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionAddProduct(ActionEvent event) {
        //Adds selected Part to a list to track which Part to add to the Product once the save button is clicked
        if(selectedPartToAdd != null) {
            addPartsList.add(selectedPartToAdd);
        }
    }

    /** This is the onActionCancelProduct method.
     This method is an ActionEvent method to cancel/exit out of the Add Product Screen to return to the Main Menu.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionCancelProduct(ActionEvent event) throws IOException {

        /*
        Condition checks to see if there are any text fields that are NOT empty.
        All text fields have to be empty, otherwise the code inside will run to show the confirmation dialog box
         */
        if(!(TextFieldManipulation.emptyTextFields(nameTxt, invTxt, minTxt, maxTxt, priceTxt))) {
            Optional<ButtonType> result = DialogBoxes.confirmationAlert("Confirmation", "Clicking cancel will not save any changes");

            if(result.isPresent() && result.get() == ButtonType.CANCEL) {
                return;
            }
        }

        /*
        Sets the mainMainProductTableSelectedItem variable to null because I did not want a situation where
        the user selects a product on the Main Menu product table, then goes to the Add Product Screen, clicks the cancel button on the
        Add Product Screen, then clicks the Modify Product button and the Modify Product Screen being populated with the previously selected product.
         */
        SelectedItemHelper.setMainMenuProductTableSelectedItem(null);
        filePath.switchScreen(event, filePath.getMainMenuFilePath(), ScreensEnum.MAIN_MENU.toString());
    }

    /** This is the onActionRemoveAssociatedPart method.
     This method is an ActionEvent method to remove an associated part of the product.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionRemoveAssociatedPart(ActionEvent event) {
        //Checks to see if there are any parts to remove from the Product
        if(addPartsList.size() == 0) {
            DialogBoxes.errorAlert("Error Dialog" , "There are no Associated Parts to remove from this Product.");
            return;
        }

        //Checks to see if the user selected a part to remove
        if(selectedPartToRemove == null) {
            DialogBoxes.errorAlert("Error Dialog" , "You have to select a part prior to clicking the " +
                    "Remove Associated Part button.");
            return;
        }

        Optional<ButtonType> result = DialogBoxes.confirmationAlert("Confirmation", "Do you want to remove this part?");
        if(result.isPresent() && result.get() == ButtonType.CANCEL) {
            return;
        }

        addPartsList.remove(selectedPartToRemove);
        //Note: Have to reset it to null
        selectedPartToRemove = null;
    }

    /** This is the onActionSaveProduct method.
     This method is an ActionEvent method for the save button on the Product Screen to save the newly created product.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionSaveProduct(ActionEvent event) throws IOException{
        //Checks each Text Field to see if it is empty and if it is then the Text Field border will be set to red
        if(TextFieldManipulation.areTextFieldsEmpty(nameTxt, priceTxt, invTxt, minTxt, maxTxt)) {
            TextFieldManipulation.setEmptyTextFieldsBorderRed(nameTxt, invTxt, minTxt, maxTxt, priceTxt);
            DialogBoxes.errorAlert("Error Dialog" , "You have empty text fields. You must fill in all text fields prior to hitting save.");
            return;
        }
        //Resets text values in case the border was set to red
        TextFieldManipulation.resetTextFields(nameTxt, invTxt, priceTxt, minTxt, maxTxt);

        //Checks to see if the input is a number or double, and if not then will notify the user
        if(!(InputValidation.areTextFieldValuesValid(invTxt, minTxt, maxTxt, priceTxt))) {
            return;
        }

        //Checks to see if inv value is between min and max values
        if(!(InputValidation.isInvBetweenMaxAndMin(invTxt, maxTxt, minTxt))) {
            return;
        }

        //Checks to see if max between min value
        if(!(InputValidation.isMaxBetweenMin(maxTxt, minTxt))) {
            return;
        }

        /*
        If the Text Fields are not empty then the new Product object will be created with the inputted Text Field values
        by calling the createProduct() method.
         */
        Product newProduct = createProduct();

        /*
        Loops through the addPartsList for the Parts that are supposed to be added to this Product
        that is being created and adds them.
         */
        if(addPartsList.size() > 0) {
            for(Part i: addPartsList) {
                newProduct.addAssociatedPart(i);
            }
        }
        Inventory.addProduct(newProduct);
        filePath.switchScreen(event, filePath.getMainMenuFilePath(), ScreensEnum.MAIN_MENU.toString());
    }

    /** This is the topSearchBox method.
     This is an ActionEvent method that is used with the TextField to obtain the user inputs and search for the items that matches the user input.
     @param event the ActionEvent this applies to*/
    public void topSearchBox(ActionEvent event) {
        //Sets the table view back to the all Parts list in case it changed
        addProductTopTableView.setItems(Inventory.getAllParts());

        /*
        Clears the filtered list if there are any items in it already.

        Note: Without this a bug will occur where if you keep hitting enter after removing what you typed
        the objects will duplicate.
         */
        FilteredHelper.clearPartsListWithItems(FilteredHelper.getFilteredParts());
        //Clears the selected item if there is one
        addProductTopTableView.getSelectionModel().clearSelection();

        //Checks to see if the search box input is an integer and runs the ID search if it is
        if(InputValidation.isInteger(searchBoxTxt)) {
            for(Part i: Inventory.getAllParts()) {
                if(i.getId() == Integer.parseInt(searchBoxTxt.getText())) {
                    //Selects the Part object in the Parts Table
                    addProductTopTableView.getSelectionModel().select(i);
                    //Sets the selected part variable
                    selectedPartToAdd = i;
                    return;
                }
            }
            DialogBoxes.errorAlert("Error Dialog" , "Nothing was found.");
        } else {
            FilteredHelper.filterPart(searchBoxTxt);

            if(FilteredHelper.getFilteredParts().size() == 1) {
                Part part = FilteredHelper.getFilteredParts().get(0);
                //Selects the Part object in the Parts Table
                addProductTopTableView.getSelectionModel().select(part);
                //Sets the selected part variable
                selectedPartToAdd = part;

            } else if (FilteredHelper.getFilteredParts().size() > 1){
                //Sets the table view to show the filtered Parts

                addProductTopTableView.setItems(FilteredHelper.getFilteredParts());
            } else {
                DialogBoxes.errorAlert("Error Dialog" , "Nothing was found.");
            }
        }
    }

    /** this is the topPartTableOnClick method.
     This is a MouseEvent method and is meant to obtain the row/object in the top table view that was clicked.
     @param event the MouseEvent this applies to*/
    public void topPartTableOnClick(MouseEvent event) {
         selectedPartToAdd = addProductTopTableView.getSelectionModel().getSelectedItem();
    }

    /** this is the bottomPartTableOnClick method.
     This is a MouseEvent method and is meant to obtain the row/object in the bottom table view that was clicked.
     @param event the MouseEvent this applies to*/
    public void bottomPartTableOnClick(MouseEvent event) {
        selectedPartToRemove = addProductBottomTableView.getSelectionModel().getSelectedItem();
    }

    /** This is the createProduct method.
     This method creates a Product object by using the TextField values.
     @return Returns a Product object*/
    private Product createProduct() {

        /*
        Creates a product object by using the inputted information from the text fields.
        The method ends by returning the product object that was created.
         */
        int id = GenerateRandom.generateRandomInt();
        String name = nameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(invTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());

        return new Product(id, name, price, stock, min, max);
    }

    /** This is the initialize method.
     This method runs any code when the controller loads.
     @param url the URL this applies to
     @param rb the ResourceBundle this applies to*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Parts Table (Top Table)

        //Populates the top parts table view which shows all parts
        addProductTopTableView.setItems(Inventory.getAllParts());
        partIdTopCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameTopCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelTopCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitTopCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Parts Table (Bottom Table)

        //Populates the bottom associated parts table view for the product that is being created
        addProductBottomTableView.setItems(addPartsList);
        partIdBottomCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameBottomCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelBottomCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitBottomCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}