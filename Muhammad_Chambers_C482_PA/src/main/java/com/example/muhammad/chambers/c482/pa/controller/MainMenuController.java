package com.example.muhammad.chambers.c482.pa.controller;

import com.example.muhammad.chambers.c482.pa.helper.DialogBoxes;
import com.example.muhammad.chambers.c482.pa.helper.FilePaths;
import com.example.muhammad.chambers.c482.pa.helper.InputValidation;
import com.example.muhammad.chambers.c482.pa.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.muhammad.chambers.c482.pa.helper.ScreensEnum;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/** This class holds the functionality for the Main Menu. FUTURE ENHANCEMENT: 1.Improve the UI, 2.Refactor code, 3.Add a database for persistent data.*/
public class MainMenuController implements Initializable {
    private FilePaths filePath = new FilePaths();

    @FXML
    private TableColumn<Part, Integer> invLevelLeftCol;
    @FXML
    private TableColumn<Product, Integer> invLevelRightCol;
    @FXML
    private TableView<Part> mainMenuLeftTableView;
    @FXML
    private TableView<Product> mainMenuRightTableView;
    @FXML
    private TableColumn<Part, Integer> partIdLeftCol;
    @FXML
    private TableColumn<Product, Integer> partIdRightCol;
    @FXML
    private TableColumn<Part, String> partNameLeftCol;
    @FXML
    private TableColumn<Product, String> partNameRightCol;
    @FXML
    private TableColumn<Part, Double> priceCostPerUnitLeftCol;
    @FXML
    private TableColumn<Product, Double> priceCostPerUnitRightCol;
    @FXML
    private TextField searchBoxTextRight;
    @FXML
    private TextField searchBoxTxtLeft;

    /** This is the onActionAddPart method.
     This method takes the user to the Add Part Screen.
     @param event the ActionEvent this applies to*/
    @FXML
    public void onActionAddPart(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getAddPartInHouseFilePath(), ScreensEnum.ADD_PART.toString());
    }

    /** This is the onActionAddProduct method.
     This method takes the user to the Add Product Screen.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionAddProduct(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getAddProductFilePath(), ScreensEnum.ADD_PRODUCT.toString());
    }

    /** This is the onActionDeletePart method.
     This method deletes a Part object and removes it from the part table view.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionDeletePart(ActionEvent event) {
        //Checks to see if the user selected an item and if not inform the user to select an item to delete
        if(SelectedItemHelper.getMainMenuPartTableSelectedItem() == null) {
            DialogBoxes.errorAlert("Error Dialog" , "You need to select a Part first then click the Delete button.");
            return;
        }

        Optional<ButtonType> result = DialogBoxes.confirmationAlert("Confirmation", "Do you want to delete this Part?");
        if(result.isPresent() && result.get() == ButtonType.CANCEL) {
            return;
        }

        //Removes the selected Part from the allParts list
        Inventory.getAllParts().remove(SelectedItemHelper.getMainMenuPartTableSelectedItem());

        /*
        Have to reset the mainMenuPartTableSelectedItem variable to null after deleting the part item
        from the part table. Otherwise, the variable would still be holding the deleted part which we do not want.
        */
        SelectedItemHelper.setMainMenuPartTableSelectedItem(null);
    }

    /** This is the onActionDeleteProduct method.
     This method deletes a Product object and removes it from the product table view.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionDeleteProduct(ActionEvent event) {
        //Checks to see if the user selected an item and if not inform the user to select an item to delete
        if(SelectedItemHelper.getMainMenuProductTableSelectedItem() == null) {
            DialogBoxes.errorAlert("Error Dialog" , "You need to select a Product first then click the Delete button.");
            return;
        }

        /*
        Checks to see if the selected Product has any associated parts, if it does then will notify the user
        they cannot delete the Product until they removed all associated parts.
         */
        if(SelectedItemHelper.getMainMenuProductTableSelectedItem().getAllAssociatedParts().size() != 0) {
            DialogBoxes.errorAlert("Error Dialog" , "You cannot delete a Product with associated parts. " +
                    "You must first remove all associated parts.");
            return;
        }

        Optional<ButtonType> result = DialogBoxes.confirmationAlert("Confirmation", "Do you want to delete this Product?");
        if(result.isPresent() && result.get() == ButtonType.CANCEL) {
            return;
        }

        //Removes the selected product from the allProducts list
        Inventory.getAllProducts().remove(SelectedItemHelper.getMainMenuProductTableSelectedItem());
        //Note: Have to reset the select item to null
        SelectedItemHelper.setMainMenuProductTableSelectedItem(null);
    }

    /** This is the onActionExitMainMenu method.
     This method exits out of the application.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionExitMainMenu(ActionEvent event) {
        System.exit(0);
    }

    /** This is the onActionModifyPart method.
     This method takes the user to the Modify Part Screen.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionModifyPart(ActionEvent event) throws IOException {
        //Checks to see if the user selected an item and if not inform the user to select an item to modify
        if(SelectedItemHelper.getMainMenuPartTableSelectedItem() == null) {
            DialogBoxes.errorAlert("Error Dialog" , "You need to select a Part first then click the Modify button.");
            return;
        }
        //Checks to see if the selected Part is an InHouse Part. If it is then the InHouse Modify Part Screen will open
        if(SelectedItemHelper.getMainMenuPartTableSelectedItem() instanceof InHouse) {
            filePath.switchScreen(event, filePath.getModifyPartInHouseFilePath(), ScreensEnum.MODIFY_PART.toString());
            return;
        }

        filePath.switchScreen(event, filePath.getModifyPartOutsourcedFilePath(), ScreensEnum.MODIFY_PART.toString());
    }

    /** This is the onActionModifyProduct method.
     This method takes the user to the Modify Product Screen.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionModifyProduct(ActionEvent event) throws IOException {
        //Checks to see if the user selected an item and if not inform the user to select an item to modify
        if(SelectedItemHelper.getMainMenuProductTableSelectedItem() == null) {
            DialogBoxes.errorAlert("Error Dialog" , "You need to select a Product first then click the Modify button.");
            return;
        }

        filePath.switchScreen(event, filePath.getModifyProductFilePath(), ScreensEnum.MODIFY_PRODUCT.toString());
    }

    /** this is the partTableOnClick method.
     This is a MouseEvent method and is meant to obtain the row/object in the part table view that was clicked.
     @param event the MouseEvent this applies to*/
    @FXML
    public void partTableOnClick(MouseEvent event) {
        SelectedItemHelper.setMainMenuPartTableSelectedItem(mainMenuLeftTableView.getSelectionModel().getSelectedItem());
    }

    /** this is the productTableOnClick method.
     This is a MouseEvent method and is meant to obtain the row/object in the product table view that was clicked.
     @param event the MouseEvent this applies to*/
    @FXML
    public void productTableOnClick(MouseEvent event) {
        SelectedItemHelper.setMainMenuProductTableSelectedItem(mainMenuRightTableView.getSelectionModel().getSelectedItem());
    }

    /** This is the leftSearchBox method.
     This is an ActionEvent method that is used with the TextField to obtain the user inputs and search for the items that matches the user input.
     @param event the ActionEvent this applies to*/
    public void leftSearchBox(ActionEvent event) {
        //Sets the table view back to the all Parts list in case it changed
        mainMenuLeftTableView.setItems(Inventory.getAllParts());

        /*
        Clears the filtered list if there are any items in it already.

        Note: Without this a bug will occur where if you keep hitting enter after removing what you typed
        the objects will duplicate.
         */
        FilteredHelper.clearPartsListWithItems(FilteredHelper.getFilteredParts());
        //Clears the selected item if there is one
        mainMenuLeftTableView.getSelectionModel().clearSelection();

        //Checks to see if the search box input is an integer and runs the ID search if it is
        if(InputValidation.isInteger(searchBoxTxtLeft)) {
            for(Part i: Inventory.getAllParts()) {
                if(i.getId() == Integer.parseInt(searchBoxTxtLeft.getText())) {
                    //Selects the Part object in the Parts Table
                    mainMenuLeftTableView.getSelectionModel().select(i);
                    //Sets the selected part variable
                    SelectedItemHelper.setMainMenuPartTableSelectedItem(i);
                    return;
                }
            }
            DialogBoxes.errorAlert("Error Dialog" , "Nothing was found.");
        } else {
            FilteredHelper.filterPart(searchBoxTxtLeft);

            if(FilteredHelper.getFilteredParts().size() == 1) {
                Part part = FilteredHelper.getFilteredParts().get(0);
                //Selects the Part object in the Parts Table
                mainMenuLeftTableView.getSelectionModel().select(part);
                //Sets the selected part variable
                SelectedItemHelper.setMainMenuPartTableSelectedItem(part);
            } else if (FilteredHelper.getFilteredParts().size() > 1){
                //Sets the table view to show the filtered Parts
                mainMenuLeftTableView.setItems(FilteredHelper.getFilteredParts());
            } else {
                DialogBoxes.errorAlert("Error Dialog" , "Nothing was found.");
            }
        }
    }

    /** This is the rightSearchBox method.
     This is an ActionEvent method that is used with the TextField to obtain the user inputs and search for the items that matches the user input.
     @param event the ActionEvent this applies to*/
    public void rightSearchBox(ActionEvent event) {
        //Sets the table view back to the all Products list in case it changed
        mainMenuRightTableView.setItems(Inventory.getAllProducts());

        /*
        Clears the filtered list if there are any items in it already.

        Note: Without this a bug will occur where if you keep hitting enter after removing what you typed
        the objects will duplicate.
         */
        FilteredHelper.clearProductsListWithItems(FilteredHelper.getFilteredProducts());
        //Clears the selected item if there is one
        mainMenuRightTableView.getSelectionModel().clearSelection();

        //Checks to see if the search box input is an integer and runs the ID search if it is
        if(InputValidation.isInteger(searchBoxTextRight)) {
            for(Product i: Inventory.getAllProducts()) {
                if(i.getId() == Integer.parseInt(searchBoxTextRight.getText())) {
                    //Selects the Product object in the Products Table
                    mainMenuRightTableView.getSelectionModel().select(i);
                    //Sets the selected product variable
                    SelectedItemHelper.setMainMenuProductTableSelectedItem(i);
                    return;
                }
            }
            DialogBoxes.errorAlert("Error Dialog" , "Nothing was found.");
        } else {
            FilteredHelper.filterProduct(searchBoxTextRight);

            if(FilteredHelper.getFilteredProducts().size() == 1) {
                //Selects the Product object in the Products Table
                Product product = FilteredHelper.getFilteredProducts().get(0);
                mainMenuRightTableView.getSelectionModel().select(product);
                //Sets the selected product variable
                SelectedItemHelper.setMainMenuProductTableSelectedItem(product);
            } else if (FilteredHelper.getFilteredProducts().size() > 1){
                //Sets the table view to show the filtered Products
                mainMenuRightTableView.setItems(FilteredHelper.getFilteredProducts());
            } else {
                DialogBoxes.errorAlert("Error Dialog" , "Nothing was found.");
            }
        }
    }

    /** This is the initialize method.
     This method runs any code when the controller loads.
     @param url the URL this applies to
     @param rb the ResourceBundle this applies to*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Products Table (Right Table)
        mainMenuRightTableView.setItems(Inventory.getAllProducts());
        partIdRightCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameRightCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelRightCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitRightCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Parts Table (Left Table)
        mainMenuLeftTableView.setItems(Inventory.getAllParts());
        partIdLeftCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameLeftCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelLeftCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitLeftCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}