package com.example.muhammad.chambers.c482.pa.controller;

import com.example.muhammad.chambers.c482.pa.helper.*;
import com.example.muhammad.chambers.c482.pa.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class holds the functionality for the Modify Part Outsourced Screen.*/
public class ModifyPartOutsourcedController implements Initializable {
    private FilePaths filePath = new FilePaths();
    private static boolean isCreateNewObject = false;

    @FXML
    private TextField companyNameTxt;
    @FXML
    private TextField idTxt;
    @FXML
    private TextField invTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField priceCostTxt;

    /** This is the onActionCancelModifyPart method.
     This method is an ActionEvent for the cancel button on the Modify Part Screen to exit out of the screen and return to the Main Menu.
     @param event the ActionEvent this applies to*/
    @FXML
    void onActionCancelModifyPart(ActionEvent event) throws IOException {

        /*
        Condition checks to see if there are any text fields that are NOT empty.
        All text fields have to be empty, otherwise the code inside will run to show the confirmation dialog box
         */
        if(!(TextFieldManipulation.emptyTextFieldsOutsourced(nameTxt, invTxt, minTxt, maxTxt, priceCostTxt, companyNameTxt))) {

            Optional<ButtonType> result = DialogBoxes.confirmationAlert("Confirmation", "Clicking cancel will not save any changes");

            if(result.isPresent() && result.get() == ButtonType.CANCEL) {
                return;
            }

        }

        //Checks if the AllParts list already contains the Part, and if not then the Part will be added

        /*
        Note: This was needed because the way the moving from Outsourced to InHouse Modify Part Screens
        in the initialize method the code removes the selected part. So, if the user ends up pressing cancel
        instead of save, without adding the part the selected part would be gone from the table view
        which is not expected outcome we want.
         */
        if(!(Inventory.getAllParts().contains(SelectedItemHelper.getMainMenuPartTableSelectedItem()))) {
            Inventory.addPart(SelectedItemHelper.getMainMenuPartTableSelectedItem());
        }

        SelectedItemHelper.setMainMenuPartTableSelectedItem(null);
        PartPassDataHelper.resetFields();
        PartPassDataHelper.setWasScreenSwitched(false);
        filePath.switchScreen(event, filePath.getMainMenuFilePath(), ScreensEnum.MAIN_MENU.toString());
    }

    /** This is the onActionSaveModifyPart method.
     This method is an ActionEvent for the save button on the Modify Part Screen to save all changes made to the Part.
     @param event the ActionEvent this applies to*/
    @FXML
    void onActionSaveModifyPart(ActionEvent event) throws IOException {
        //Checks if there are any empty Text Fields
        if(TextFieldManipulation.areTextFieldsEmptyOutsourcedPart(nameTxt, companyNameTxt, invTxt, minTxt, maxTxt, priceCostTxt)) {
            //Checks each Text Field and sets the border for any empty Text Field to red
            TextFieldManipulation.setEmptyTextFieldsBorderRedOutsourcedPart(nameTxt, invTxt, minTxt, maxTxt, priceCostTxt, companyNameTxt);
            DialogBoxes.errorAlert("Error Dialog" , "You have empty text fields. You must fill in all text fields prior to hitting save.");
            return;
        }

        //Resets text values in case the border was set to red
        TextFieldManipulation.resetTextFieldsOutsourcedPart(nameTxt, invTxt, priceCostTxt, minTxt, maxTxt, companyNameTxt);

        //Checks to see if the input is a number or double, and if not then will notify the user
        if(!(InputValidation.areTextFieldValuesValid(invTxt, minTxt, maxTxt, priceCostTxt))) {
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

        if(isCreateNewObject) {
            //Converts the Part to a different type and adds it to the AllParts list
            Outsourced outsourcedPart = createOutsourcedPartFromTextFields();
            Inventory.addPart(outsourcedPart);
            SelectedItemHelper.setMainMenuPartTableSelectedItem(null);
            PartPassDataHelper.resetFields();
            PartPassDataHelper.setWasScreenSwitched(false);
            filePath.switchScreen(event, filePath.getMainMenuFilePath(), ScreensEnum.MAIN_MENU.toString());
            return;

        } else if(PartPassDataHelper.getWasScreenSwitched()) {
            //Updates the Part values based on the input from the Text Fields
            updateOutsourcedPartValues();
            //Part will need to be added back to the Parts list if screen switched but part type not changed
            Inventory.addPart(SelectedItemHelper.getMainMenuPartTableSelectedItem());
            SelectedItemHelper.setMainMenuPartTableSelectedItem(null);
            PartPassDataHelper.resetFields();
            PartPassDataHelper.setWasScreenSwitched(false);
            filePath.switchScreen(event, filePath.getMainMenuFilePath(), ScreensEnum.MAIN_MENU.toString());
            return;
        }

        updateOutsourcedPartValues();
        SelectedItemHelper.setMainMenuPartTableSelectedItem(null);
        PartPassDataHelper.resetFields();
        PartPassDataHelper.setWasScreenSwitched(false);
        filePath.switchScreen(event, filePath.getMainMenuFilePath(), ScreensEnum.MAIN_MENU.toString());
    }

    /** This is the onActionGoToInHouseModifyPart method.
     This method is an ActionEvent for the radio buttons on the Modify Part Screen and takes the user to the Modify Part InHouse Screen.
     @param event the ActionEvent this applies to*/
    @FXML
    void onActionGoToInHouseModifyPart(ActionEvent event) throws IOException {
        //Need to set the values for the PartPassDataHelper so the values can be shared between the Screens to set the TextField values
        PartPassDataHelper.setId(String.valueOf(SelectedItemHelper.getMainMenuPartTableSelectedItem().getId()));
        PartPassDataHelper.setFieldsExceptId(nameTxt, invTxt, priceCostTxt, minTxt, maxTxt);
        PartPassDataHelper.setWasScreenSwitched(true);
        filePath.switchScreenRadioBtn(event, filePath.getModifyPartInHouseFilePath(), ScreensEnum.MODIFY_PART.toString());
    }

    /** This is the createOutsourcedPartFromTextFields method.
     This method creates an Outsourced Part by using the TextField values.
     @return Returns an Outsourced Part object*/
    private Outsourced createOutsourcedPartFromTextFields() {
        //Creates an Outsourced Part from the Text Field values
        int id = Integer.parseInt(idTxt.getText()); //ID field is not supposed to be modified

        String companyName = companyNameTxt.getText();
        String name = nameTxt.getText();
        double price = Double.parseDouble(priceCostTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        int stock = Integer.parseInt(invTxt.getText());

        return new Outsourced(id, name, price, stock, min, max, companyName);
    }

    /** This is the updateOutsourcedPartValues method. This method updates all the values for the Outsourced Part by using the TextField values.*/
    private void updateOutsourcedPartValues() {
        //Updates the values for the Outsourced Part based on the Text Field values
        SelectedItemHelper.getMainMenuPartTableSelectedItem().setId(Integer.parseInt(idTxt.getText()));
        SelectedItemHelper.getMainMenuPartTableSelectedItem().setName(nameTxt.getText());
        SelectedItemHelper.getMainMenuPartTableSelectedItem().setPrice(Double.parseDouble(priceCostTxt.getText()));
        SelectedItemHelper.getMainMenuPartTableSelectedItem().setMax(Integer.parseInt(maxTxt.getText()));
        SelectedItemHelper.getMainMenuPartTableSelectedItem().setMin(Integer.parseInt(minTxt.getText()));
        ((Outsourced) SelectedItemHelper.getMainMenuPartTableSelectedItem()).setCompanyName(companyNameTxt.getText());
    }

    /** This is the initialize method.
     This method runs any code when the controller loads.
     @param url the URL this applies to
     @param rb the ResourceBundle this applies to*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Checks to see if the Part is an instance of InHouse
        if(SelectedItemHelper.getMainMenuPartTableSelectedItem() instanceof InHouse) {
            //Removes the Part from the list if it is an instance of InHouse
            Inventory.getAllParts().remove(SelectedItemHelper.getMainMenuPartTableSelectedItem());
            //Sets the TextField values with the values stored in the PartPassDataHelper if they are not empty
            if(!(PartPassDataHelper.areFieldsEmpty())) {
                PartPassDataHelper.setTextFields(idTxt, nameTxt, priceCostTxt, invTxt, minTxt, maxTxt);
            }
            //This boolean is used to track if we will need to create this object as a different type and add to the list if save is pressed
            isCreateNewObject = true;
        } else {
            if(!(PartPassDataHelper.getWasScreenSwitched())) {
                PartPassDataHelper.setId(String.valueOf(SelectedItemHelper.getMainMenuPartTableSelectedItem().getId()));
                PartPassDataHelper.setName(SelectedItemHelper.getMainMenuPartTableSelectedItem().getName());
                PartPassDataHelper.setPrice(String.valueOf(SelectedItemHelper.getMainMenuPartTableSelectedItem().getPrice()));
                PartPassDataHelper.setInv(String.valueOf(SelectedItemHelper.getMainMenuPartTableSelectedItem().getStock()));
                PartPassDataHelper.setMin(String.valueOf(SelectedItemHelper.getMainMenuPartTableSelectedItem().getMin()));
                PartPassDataHelper.setMax(String.valueOf(SelectedItemHelper.getMainMenuPartTableSelectedItem().getMax()));

                //Sets TextField values
                PartPassDataHelper.setTextFields(idTxt, nameTxt, priceCostTxt, invTxt, minTxt, maxTxt);
                companyNameTxt.setText(((Outsourced)(SelectedItemHelper.getMainMenuPartTableSelectedItem())).getCompanyName());
            } else {
                //Sets TextField values
                PartPassDataHelper.setTextFields(idTxt, nameTxt, priceCostTxt, invTxt, minTxt, maxTxt);
                companyNameTxt.setText(((Outsourced)(SelectedItemHelper.getMainMenuPartTableSelectedItem())).getCompanyName());
            }
        }
    }
}