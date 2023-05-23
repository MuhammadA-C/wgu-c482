package com.example.muhammad.chambers.c482.pa.helper;

import com.example.muhammad.chambers.c482.pa.model.PartPassDataHelper;
import javafx.scene.control.TextField;

/** This class holds methods which manipulate the TextField.*/
public class TextFieldManipulation {
    private static final String borderColorRed = "-fx-border-color: Red;";
    private static final String borderColorDefault = "-fx-border-color: None;";

    /** This is the setTextFieldRed method.
     This method sets the TextField border to red.
     @param textField the textField to set*/
    public static void setTextFieldRed(TextField textField) {
        textField.setStyle(borderColorRed);
    }

    /** This is the resetTextField method.
     This method resets the TextField border color to its default color.
     @param textField the textField to reset*/
    public static void resetTextField(TextField textField) {
        textField.setStyle(borderColorDefault);
    }

    /** This is the resetTextFields method.
     This method resets the all TextField border colors to their default color.
     @param price the price field to reset
     @param name the name field to reset
     @param min the min field to reset
     @param max the max field to reset
     @param inv the inv field to reset*/
    public static void resetTextFields(TextField name, TextField inv, TextField price, TextField min, TextField max) {
        resetTextField(name);
        resetTextField(inv);
        resetTextField(price);
        resetTextField(min);
        resetTextField(max);
    }

    /** This is the resetTextFieldsInHousePart method.
     This method resets the all TextField border colors to their default color for the InHouse Part.
     @param max the max field to reset
     @param inv the inv field to reset
     @param min the min field to reset
     @param name the name field to reset
     @param price the price field to reset
     @param machineId the machineId to reset*/
    public static void resetTextFieldsInHousePart(TextField name, TextField inv, TextField price, TextField min, TextField max, TextField machineId) {
        resetTextFields(name, inv, price, min, max);
        resetTextField(machineId);
    }

    /** This is the resetTextFieldsOutsourcedPart method.
     This method resets the all TextField border colors to their default color for the Outsourced Part.
     @param price the price field to reset
     @param name the name field to reset
     @param min the min field to reset
     @param inv the inv field to reset
     @param max the max field to reset
     @param companyName the companyName field to reset */
    public static void resetTextFieldsOutsourcedPart(TextField name, TextField inv, TextField price, TextField min, TextField max, TextField companyName) {
        resetTextFields(name, inv, price, min, max);
        resetTextField(companyName);
    }

    /** This is the setEmptyTextFieldBorderRed method.
     This method sets any empty TextField border red.
     @param textField the textField to set border red*/
    public static void setEmptyTextFieldBorderRed(TextField textField) {
        //Checks to see if the Text Field is empty and if it is then the border color will change to red
        if(textField.getText().isEmpty()) {
            textField.setStyle(borderColorRed);
        } else {
            textField.setStyle(borderColorDefault);
        }
    }

    /** This is the setEmptyTextFieldsBorderRed method.
     This method sets any TextFields that are empty border to red.
     @param nameTxt the nameTxt field to set border red
     @param invTxt the invTxt field to set border red
     @param minTxt the minTxt field to set border red
     @param priceCostTxt the priceCostTxt field to set border red
     @param maxTxt the maxTxt field to set border red*/
    public static void setEmptyTextFieldsBorderRed(TextField nameTxt, TextField invTxt, TextField minTxt, TextField maxTxt, TextField priceCostTxt) {
        //Goes through each Text Field and sets the border to red if it is empty
        setEmptyTextFieldBorderRed(nameTxt);
        setEmptyTextFieldBorderRed(invTxt);
        setEmptyTextFieldBorderRed(minTxt);
        setEmptyTextFieldBorderRed(maxTxt);
        setEmptyTextFieldBorderRed(priceCostTxt);
    }

    /** This is the setEmptyTextFieldsBorderRedInHousePart method.
     This method sets any TextFields that are empty border to red for the InHouse Part.
     @param maxTxt the maxTxt field to set border red
     @param priceCostTxt the priceCostTxt field to set border red
     @param minTxt the minTxt field to set border red
     @param invTxt the invTxt field to set border red
     @param nameTxt the nameTxt field to set border red
     @param machineIdTxt the machineIdTxt field to set border red*/
    public static void setEmptyTextFieldsBorderRedInHousePart(TextField nameTxt, TextField invTxt, TextField minTxt, TextField maxTxt, TextField priceCostTxt, TextField machineIdTxt) {
        //Goes through each Text Field and sets the border to red if it is empty
        setEmptyTextFieldsBorderRed(nameTxt, invTxt, minTxt, maxTxt, priceCostTxt);
        setEmptyTextFieldBorderRed(machineIdTxt);
    }

    /** This is the setEmptyTextFieldsBorderRedOutsourcedPart method.
     This method sets any TextFields that are empty border to red for the Outsourced Part.
     @param nameTxt the nameTxt field to set border red
     @param invTxt the invTxt field to set border red
     @param minTxt the minTxt field to set border red
     @param priceCostTxt the priceCostTxt field to set border red
     @param maxTxt the maxTxt field to set border red
     @param companyNameTxt the companyNameTxt field to set border red*/
    public static void setEmptyTextFieldsBorderRedOutsourcedPart(TextField nameTxt, TextField invTxt, TextField minTxt, TextField maxTxt, TextField priceCostTxt, TextField companyNameTxt) {
        //Goes through each Text Field and sets the border to red if it is empty
        setEmptyTextFieldsBorderRed(nameTxt, invTxt, minTxt, maxTxt, priceCostTxt);
        setEmptyTextFieldBorderRed(companyNameTxt);
    }

    /** This is the areTextFieldsEmpty method.
     This method checks all TextFields to see if any are empty and returns true for the first TextField that is found to be empty, or false if none of them are.
     @param maxTxt the maxTxt field to check if it is empty
     @param minTxt the minTxt field to check if it is empty
     @param invTxt the invTxt field to check if it is empty
     @param nameTxt the nameTxt field to check if it is empty
     @param priceTxt the priceTxt field to check if it is empty
     @return Returns a boolean value*/
    public static boolean areTextFieldsEmpty(TextField nameTxt, TextField priceTxt, TextField invTxt, TextField minTxt, TextField maxTxt) {
        //Checks all the Text Fields (except for the id text field) to see if they are empty
        if(nameTxt.getText().isEmpty()) {
            return true;
        } else if (priceTxt.getText().isEmpty()){
            return true;
        } else if(invTxt.getText().isEmpty()) {
            return true;
        } else if(minTxt.getText().isEmpty()) {
            return true;
        } else if(maxTxt.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /** This is the areTextFieldsEmptyInHousePart method.
     This method checks all TextFields to see if any are empty and returns true for the first TextField that is found to be empty, or false if none of them are for the InHouse Part.
     @param nameTxt the nameTxt field to check if it is empty
     @param invTxt the invTxt field to check if it is empty
     @param minTxt the minTxt field to check if it is empty
     @param maxTxt the maxTxt field to check if it is empty
     @param priceCostTxt the priceTxt field to check if it is empty
     @param machineIdTxt the machineIdTxt field to check if it is empty
     @return Returns a boolean value*/
    public static boolean areTextFieldsEmptyInHousePart(TextField nameTxt, TextField machineIdTxt, TextField invTxt, TextField minTxt, TextField maxTxt, TextField priceCostTxt) {
        //Checks all the text fields (except for the id text field) to see if they are empty
        if(machineIdTxt.getText().isEmpty()) {
            return true;
        } else if(areTextFieldsEmpty(nameTxt, priceCostTxt, invTxt, minTxt, maxTxt)) {
            return true;
        } else {
            return  false;
        }
    }

    /** This is the areTextFieldsEmptyOutsourcedPart method.
     This method checks all TextFields to see if any are empty and returns true for the first TextField that is found to be empty, or false if none of them are for the Outsourced Part.
     @param priceCostTxt the priceTxt field to check if it is empty
     @param maxTxt the maxTxt field to check if it is empty
     @param minTxt the minTxt field to check if it is empty
     @param invTxt the invTxt field to check if it is empty
     @param nameTxt the nameTxt field to check if it is empty
     @param companyNameTxt the companyNameTxt field to check if it is empty
     @return Returns a boolean value*/
    public static boolean areTextFieldsEmptyOutsourcedPart(TextField nameTxt, TextField companyNameTxt, TextField invTxt, TextField minTxt, TextField maxTxt, TextField priceCostTxt) {
        //Checks all the Text Fields (except for the id text field) to see if they are empty
        if(companyNameTxt.getText().isEmpty()) {
            return true;
        } else if(areTextFieldsEmpty(nameTxt, priceCostTxt, invTxt, minTxt, maxTxt)) {
            return true;
        } else {
            return  false;
        }
    }

    /** This is the emptyTextFields method.
     This method checks all TextFields to see if any are empty and ONLY returns true if no TextFields are empty, otherwise it returns false.
     LOGICAL ERROR: I was using my areTextFieldsEmpty method and that resulted in the code not working as I intended because that method returns true or false for the first occurrence it finds then stops.
     I created this method to only return true if ALL TextFields are empty.
     @param nameTxt the nameTxt field to check if it is empty
     @param invTxt the invTxt field to check if it is empty
     @param minTxt the minTxt field to check if it is empty
     @param maxTxt the maxTxt field to check if it is empty
     @param priceTxt the priceTxt field to check if it is empty
     @return Returns a boolean value*/
    public static boolean emptyTextFields(TextField nameTxt, TextField invTxt, TextField minTxt, TextField maxTxt, TextField priceTxt) {
        //All text fields have to be empty otherwise it will return false
        if(nameTxt.getText().isEmpty() && invTxt.getText().isEmpty() && minTxt.getText().isEmpty() && maxTxt.getText().isEmpty() && priceTxt.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    /** This is the emptyTextFieldsInHouse method.
     This method checks all TextFields to see if any are empty and ONLY returns true if no TextFields are empty, otherwise it returns false for the InHouse Part.
     @param priceTxt the priceTxt field to check if it is empty
     @param maxTxt the maxTxt field to check if it is empty
     @param minTxt the minTxt field to check if it is empty
     @param invTxt the invTxt field to check if it is empty
     @param nameTxt the nameTxt field to check if it is empty
     @param machineId the machineId field to check if it is empty
     @return Returns a boolean value*/
    public static boolean emptyTextFieldsInHouse(TextField nameTxt, TextField invTxt, TextField minTxt, TextField maxTxt, TextField priceTxt,TextField machineId) {
        //All text fields have to be empty otherwise it will return false
        if(emptyTextFields(nameTxt, invTxt, minTxt, maxTxt, priceTxt) && machineId.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    /** This is the emptyTextFieldsOutsourced method.
     This method checks all TextFields to see if any are empty and ONLY returns true if no TextFields are empty, otherwise it returns false for the Outsourced Part.
     @param nameTxt the nameTxt field to check if it is empty
     @param invTxt the invTxt field to check if it is empty
     @param minTxt the minTxt field to check if it is empty
     @param maxTxt the maxTxt field to check if it is empty
     @param priceTxt  the priceTxt field to check if it is empty
     @param companyName the companyName field to check if it is empty
     @return Returns a boolean value*/
    public static boolean emptyTextFieldsOutsourced(TextField nameTxt, TextField invTxt, TextField minTxt, TextField maxTxt, TextField priceTxt,TextField companyName) {
        //All text fields have to be empty otherwise it will return false
        if(emptyTextFields(nameTxt, invTxt, minTxt, maxTxt, priceTxt) && companyName.getText().isEmpty()) {
            return true;
        }
        return false;
    }
}
