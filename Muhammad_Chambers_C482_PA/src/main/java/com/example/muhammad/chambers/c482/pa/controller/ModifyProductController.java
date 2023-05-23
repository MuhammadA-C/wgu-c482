package com.example.muhammad.chambers.c482.pa.controller;

import com.example.muhammad.chambers.c482.pa.helper.*;
import com.example.muhammad.chambers.c482.pa.model.FilteredHelper;
import com.example.muhammad.chambers.c482.pa.model.Inventory;
import com.example.muhammad.chambers.c482.pa.model.Part;
import com.example.muhammad.chambers.c482.pa.model.SelectedItemHelper;
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

/** This class holds the functionality for the Modify Product Screen.*/
public class ModifyProductController implements Initializable {
    private FilePaths filePath = new FilePaths();
    private Part selectedPartToRemove;
    private Part selectedPartToAdd;
    private ObservableList<Part> removePartsList = FXCollections.observableArrayList();
    private ObservableList<Part> addPartsList = FXCollections.observableArrayList();

    @FXML
    private TextField idTxt;
    @FXML
    private TableColumn<Part, Integer> invLevelBottomCol;
    @FXML
    private TableColumn<Part, Integer> invLevelTopCol;
    @FXML
    private TextField invTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TableView<Part> modifyProductBottomTableView;
    @FXML
    private TableView<Part> modifyProductTopTableView;
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

    /** This is the onActionAddModifyProduct method.
     This method is an ActionEvent method for the add button on the Modify Product Screen to add a part to the Product.
     @param event the ActionEvent that this applies to*/
    @FXML
    public void onActionAddModifyProduct(ActionEvent event) {
        //Adds the part that will be added to a list to track in case the user selects cancel to remove the added part
        addPartsList.add(selectedPartToAdd);
        //Adds the part to the Product object
        SelectedItemHelper.getMainMenuProductTableSelectedItem().getAllAssociatedParts().add(selectedPartToAdd);
    }

    /** This is the onActionCancelModifyProduct method.
     This method is an ActionEvent method to cancel/exit out of the Modify Product Screen.
     @param event the ActionEvent this applies to*/
    @FXML
    public void onActionCancelModifyProduct(ActionEvent event) throws IOException {

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
        Note: The two checks below are to correct any changes made to the object if the cancel button is pressed.

        Note: The remove list check to add back removed parts has to be done first, otherwise a bug will occur where
        the parts that were added then remove will be re-added.
         */

        //Checks if the removePartsList has any parts in it, and if there are the part will be added back
        if(removePartsList.size() > 0) {
            for(Part i: removePartsList) {
                SelectedItemHelper.getMainMenuProductTableSelectedItem().getAllAssociatedParts().add(i);
            }
        }

        //Checks if any parts were added prior to clicking Cancel button and removes them
        if(addPartsList.size() > 0) {
            for(int i = 0; i < addPartsList.size(); i++) {
                if(SelectedItemHelper.getMainMenuProductTableSelectedItem().getAllAssociatedParts().contains(addPartsList.get(i))) {
                    SelectedItemHelper.getMainMenuProductTableSelectedItem().getAllAssociatedParts().remove(addPartsList.get(i));
                }
            }
        }

        //Note: Have to reset the selected item each time when exiting or saving
        SelectedItemHelper.setMainMenuProductTableSelectedItem(null);
        filePath.switchScreen(event, filePath.getMainMenuFilePath(), ScreensEnum.MAIN_MENU.toString());
    }

    /** This is the onActionRemoveAssociatedPart method.
     This method is an ActionEvent method to remove an associated part of the Product on the Modify Product Screen.
     @param event the ActionEvent this applies to*/
    @FXML
    public void onActionRemoveAssociatedPart(ActionEvent event) {
        //Checks to see if there are any parts to remove from the Product
        if(SelectedItemHelper.getMainMenuProductTableSelectedItem().getAllAssociatedParts().size() == 0) {
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

        //Adds the part to remove to a list to keep track in case the user selects cancel and need to re-add the part
        removePartsList.add(selectedPartToRemove);
        //Removes the associated part from the Product object
        SelectedItemHelper.getMainMenuProductTableSelectedItem().getAllAssociatedParts().remove(selectedPartToRemove);

        /*
        Note: Have to reset the variable holding the Part to remove

        Without this rest then a bug can happen where a user:
         1. Repeatedly clicks the Remove Associated Part button after all of x Part was removed
         2. Click the Cancel button
         3. Click the Modify button for the same Product
        This can result in those extra non-existent parts that were clicked to remove added to the Product

        Root Cause: The cause of this bug is due to the Cancel method keeping track of the removed Parts
        and adding them back to the Part if the Cancel button was clicked
         */
        selectedPartToRemove = null;
    }

    /** This is the onActionSaveModifyProduct method.
     This method is an ActionEvent method for the save button on the Modify Product Screen to save all changes made to the Product.
     @param event the ActionEvent this applies to*/
    @FXML
    public void onActionSaveModifyProduct(ActionEvent event) throws IOException{
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

        //Updates the values for the product object, except for the id, based on the Text Field values
        modifyProductValues();
        //Note: Have to reset the selected item each time when exiting or saving
        SelectedItemHelper.setMainMenuProductTableSelectedItem(null);
        filePath.switchScreen(event, filePath.getMainMenuFilePath(), ScreensEnum.MAIN_MENU.toString());
    }

    /** This is the topSearchBox method.
     This is an ActionEvent method that is used with the TextField to obtain the user inputs and search for the items that matches the user input.
     @param event the ActionEvent this applies to*/
    public void topSearchBox(ActionEvent event) {
        //Sets the table view back to the all Parts list in case it changed
        modifyProductTopTableView.setItems(Inventory.getAllParts());

        /*
        Clears the filtered list if there are any items in it already.

        Note: Without this a bug will occur where if you keep hitting enter after removing what you typed
        the objects will duplicate.
         */
        FilteredHelper.clearPartsListWithItems(FilteredHelper.getFilteredParts());
        //Clears the selected item if there is one
        modifyProductTopTableView.getSelectionModel().clearSelection();

        //Checks to see if the search box input is an integer and runs the ID search if it is
        if(InputValidation.isInteger(searchBoxTxt)) {
            for(Part i: Inventory.getAllParts()) {
                if(i.getId() == Integer.parseInt(searchBoxTxt.getText())) {
                    //Selects the Part object in the Parts Table
                    modifyProductTopTableView.getSelectionModel().select(i);
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
                modifyProductTopTableView.getSelectionModel().select(part);
                //Sets the selected part variable
                selectedPartToAdd = part;
            } else if (FilteredHelper.getFilteredParts().size() > 1){
                //Sets the table view to show the filtered Parts
                modifyProductTopTableView.setItems(FilteredHelper.getFilteredParts());
            } else {
                DialogBoxes.errorAlert("Error Dialog" , "Nothing was found.");
            }
        }
    }

    /** This is the topPartTableOnClick method.
     This is a MouseEvent method and is meant to obtain the row/object in the top table view that was clicked.
     @param event the MouseEvent this applies to*/
    public void topPartTableOnClick(MouseEvent event) {
        //Obtains which row in the Top Table that was selected
        selectedPartToAdd = modifyProductTopTableView.getSelectionModel().getSelectedItem();
    }

    /** This is the bottomPartTableOnClick method.
     This is a MouseEvent method and is meant to obtain the row/object in the bottom table view that was clicked.
     @param event the MouseEvent this applies to*/
    public void bottomPartTableOnClick(MouseEvent event) {
        //Obtains which row in the Bottom Table that was selected
        selectedPartToRemove = modifyProductBottomTableView.getSelectionModel().getSelectedItem();
    }

    /** This is the setTextFields method. This method sets all the TextFields on the Modify Product Screen to the values stored in the Product object that was selected to modify.*/
    private void setTextFields() {
        //Sets the Text Field values based on the values in the Product object
        idTxt.setText(String.valueOf(SelectedItemHelper.getMainMenuProductTableSelectedItem().getId()));
        nameTxt.setText(SelectedItemHelper.getMainMenuProductTableSelectedItem().getName());
        priceTxt.setText(String.valueOf(SelectedItemHelper.getMainMenuProductTableSelectedItem().getPrice()));
        invTxt.setText(String.valueOf(SelectedItemHelper.getMainMenuProductTableSelectedItem().getStock()));
        minTxt.setText(String.valueOf(SelectedItemHelper.getMainMenuProductTableSelectedItem().getMin()));
        maxTxt.setText(String.valueOf(SelectedItemHelper.getMainMenuProductTableSelectedItem().getMax()));
    }

    /** This is the modifyProductValues method. This method updates all the values for the Product object EXCEPT for the id field.*/
    private void modifyProductValues() {
        //Updates all the values for the Product object, except for the id field, from the Text Field values
        SelectedItemHelper.getMainMenuProductTableSelectedItem().setName(nameTxt.getText());
        SelectedItemHelper.getMainMenuProductTableSelectedItem().setPrice(Double.parseDouble(priceTxt.getText()));
        SelectedItemHelper.getMainMenuProductTableSelectedItem().setStock(Integer.parseInt(invTxt.getText()));
        SelectedItemHelper.getMainMenuProductTableSelectedItem().setMin(Integer.parseInt(minTxt.getText()));
        SelectedItemHelper.getMainMenuProductTableSelectedItem().setMax(Integer.parseInt(maxTxt.getText()));
    }

    /** This is the initialize method.
     This method runs any code when the controller loads.
     @param url the URL this applies to
     @param rb the ResourceBundle this applies to*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Top Table View
        modifyProductTopTableView.setItems(Inventory.getAllParts());
        partIdTopCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameTopCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelTopCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitTopCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Bottom Table View
        modifyProductBottomTableView.setItems(SelectedItemHelper.getMainMenuProductTableSelectedItem().getAllAssociatedParts());
        partIdBottomCol.setCellValueFactory((new PropertyValueFactory<>("id")));
        partNameBottomCol.setCellValueFactory((new PropertyValueFactory<>("name")));
        invLevelBottomCol.setCellValueFactory((new PropertyValueFactory<>("stock")));
        priceCostPerUnitBottomCol.setCellValueFactory((new PropertyValueFactory<>("price")));

        //Text Field Values
        setTextFields();
    }
}