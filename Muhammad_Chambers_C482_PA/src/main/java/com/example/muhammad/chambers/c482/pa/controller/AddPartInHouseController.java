package com.example.muhammad.chambers.c482.pa.controller;

import com.example.muhammad.chambers.c482.pa.helper.*;
import com.example.muhammad.chambers.c482.pa.model.PartPassDataHelper;
import com.example.muhammad.chambers.c482.pa.model.InHouse;
import com.example.muhammad.chambers.c482.pa.model.Inventory;
import com.example.muhammad.chambers.c482.pa.model.SelectedItemHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class holds the functionality for the Add Part InHouse Screen.*/
public class AddPartInHouseController implements Initializable {
    private FilePaths filePath = new FilePaths();

    @FXML
    private TextField invTxt;
    @FXML
    private TextField machineIdTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField priceCostTxt;

    /** This is the onActionCancelPart method.
     This method cancels/exits out of the Add Part Screen and returns to the Main Menu Screen without saving the part that was being created.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionCancelPart(ActionEvent event) throws IOException {

        /*
        Condition checks to see if there are any text fields that are NOT empty.
        All text fields have to be empty, otherwise the code inside will run to show the confirmation dialog box
         */
        if(!(TextFieldManipulation.emptyTextFieldsInHouse(nameTxt, machineIdTxt, invTxt, minTxt, maxTxt, priceCostTxt))) {
            Optional<ButtonType> result = DialogBoxes.confirmationAlert("Confirmation", "Clicking cancel will not save any changes");

            if(result.isPresent() && result.get() == ButtonType.CANCEL) {
                return;
            }
        }

        /*
        Sets the mainMainPartTableSelectedItem variable to null because I did not want a situation where
        the user selects a part on the Main Menu part table, then goes to the Add part screen and clicks the cancel button on the
        Add Part Screen, then clicks the Modify part button and the Modify Part Screen being populated with the previously selected part.
         */
        SelectedItemHelper.setMainMenuPartTableSelectedItem(null);

        /*
        Resets the AddPartHelperClass values so a situation where the user presses cancel
        and clicks the Add Part button again it does not repopulate with the previous Text Field values.
         */
        PartPassDataHelper.resetFieldsExceptId();
        filePath.switchScreen(event, filePath.getMainMenuFilePath(), ScreensEnum.MAIN_MENU.toString());
    }

    /** This is the onActionSavePart method.
     This method saves the newly created part object.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionSavePart(ActionEvent event) throws IOException {
        //Checks each Text Field to see if it is empty and if it is then the Text Field border will be set to red
        if(TextFieldManipulation.areTextFieldsEmptyInHousePart(nameTxt, machineIdTxt, invTxt, minTxt, maxTxt, priceCostTxt)) {
            TextFieldManipulation.setEmptyTextFieldsBorderRedInHousePart(nameTxt, invTxt, minTxt, maxTxt, priceCostTxt, machineIdTxt);
            DialogBoxes.errorAlert("Error Dialog" , "You have empty text fields. You must fill in all text fields prior to hitting save.");
            return;
        }

        //Resets text values in case the border was set to red
        TextFieldManipulation.resetTextFieldsInHousePart(nameTxt, invTxt, priceCostTxt, minTxt, maxTxt, machineIdTxt);

        //Checks to see if the input is a number or double, and if not then will notify the user
        if(!(InputValidation.areTextFieldValuesValidPartInHouse(invTxt, minTxt, maxTxt, priceCostTxt, machineIdTxt))) {
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

        Inventory.addPart(createInHousePartFromTextFields());
        //Note: Have to reset the helper class values
        PartPassDataHelper.resetFieldsExceptId();
        filePath.switchScreen(event, filePath.getMainMenuFilePath(), ScreensEnum.MAIN_MENU.toString());
    }

    /** This is the onActionGoToOutsourcedAddPart method.
     This method is an ActionEvent for the radio buttons on the Add Part Screen and takes the user to the Add Part Outsourced Screen.
     @param event the ActionEvent this applies to*/
    @FXML
    private void onActionGoToOutsourcedAddPart(ActionEvent event) throws IOException {

        /*
        Sets the AddPartHelperClass values. This helper class will help with storing the values that will be passed
        to the Text Fields when the screen is switched from the InHouse to Outsourced screens.
         */
        PartPassDataHelper.setFieldsExceptId(nameTxt, invTxt, priceCostTxt, minTxt, maxTxt);
        filePath.switchScreenRadioBtn(event, filePath.getAddPartOutsourcedFilePath(), ScreensEnum.ADD_PART.toString());
    }

    /** This is the createInHousePartFromTextFields method.
     This method creates an InHouse Part by using the TextField values.
     @return Returns an InHouse Part object*/
    private InHouse createInHousePartFromTextFields() {
        //Creates an InHouse Part from the Text Field values
        int id = GenerateRandom.generateRandomInt(); //ID field is supposed to be randomly generated

        String name = nameTxt.getText();
        double price = Double.parseDouble(priceCostTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        int stock = Integer.parseInt(invTxt.getText());
        int machineId = Integer.parseInt(machineIdTxt.getText());

        return new InHouse(id, name, price, stock, min, max,machineId);
    }

    /** This is the initialize method.
     This method runs any code when the controller loads.
     @param url the URL this applies to
     @param rb the ResourceBundle this applies to*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*
        Checks if the fields in the helper class are not empty and if they are not then the Text Field values
        will be set based on the helper class values
         */
        if(!(PartPassDataHelper.areFieldsEmpty())) {
            PartPassDataHelper.setTextFieldsExceptId(nameTxt, priceCostTxt, invTxt, minTxt, maxTxt);
        }
    }
}