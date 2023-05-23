package com.example.muhammad.chambers.c482.pa.helper;

import javafx.scene.control.TextField;

/** This class holds the methods that deal with input validation.*/
public class InputValidation {

    /** This is the isInteger method.
     This method checks to see if the value stored in the TextField is an integer and returns true if it is and false otherwise.
     @param textField the textField to check to see if its value is an integer
     @return Returns a boolean value*/
    public static boolean isInteger(TextField textField) {
        try {
            Integer.parseInt(textField.getText());
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    /** This is the isDouble method.
     This method checks to see if the value stored in the TextField is a double and returns true if it is and false otherwise.
     @param textField the textField to check to see if its value is a double
     @return Returns a boolean value*/
    public static boolean isDouble(TextField textField) {
        try {
            Double.parseDouble(textField.getText());
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    /** This is the areTextFieldValuesValid method.
     This method checks all TextField values that are numbers (integer or double) and returns false for the first one it finds that is NOT valid, or otherwise true if all are valid.
     @param invTxt the invTxt field to check if it is a valid value
     @param minTxt the minTxt field to check if it is a valid value
     @param maxTxt the maxTxt field to check if it is a valid value
     @param priceCostTxt the priceCostTxt field to check if it is a valid value
     @return Returns a boolean value*/
    public static boolean areTextFieldValuesValid(TextField invTxt, TextField minTxt, TextField maxTxt, TextField priceCostTxt) {
        if(!(isInteger(invTxt))) {
            DialogBoxes.errorAlert("Exception" , "Inventory has to be a number");
            return false;
        } else if(!(isInteger(minTxt))) {
            DialogBoxes.errorAlert("Exception" , "Minimum has to be a number");
            return false;
        } else if(!(isInteger(maxTxt))) {
            DialogBoxes.errorAlert("Exception" , "Maximum ID has to be a number");
            return false;
        } else if(!(isDouble(priceCostTxt))) {
            DialogBoxes.errorAlert("Exception" , "Price/Cost has to be a number");
            return false;
        }
        return true;
    }

    /** This is the areTextFieldValuesValidPartInHouse method.
     This method checks all TextField values that are numbers (integer or double) and returns false for the first one it finds that is NOT valid, or otherwise true if all are valid for the InHouse Part.
     @param priceCostTxt the priceCostTxt field to check if it is a valid value
     @param maxTxt the maxTxt field to check if it is a valid value
     @param minTxt the minTxt field to check if it is a valid value
     @param invTxt the invTxt field to check if it is a valid value
     @param machineIdTxt the machineIdTxt field to check if it is a valid value
     @return Returns a boolean value*/
    public static boolean areTextFieldValuesValidPartInHouse(TextField invTxt, TextField minTxt, TextField maxTxt, TextField priceCostTxt, TextField machineIdTxt) {
       if(!(areTextFieldValuesValid(invTxt, minTxt, maxTxt, priceCostTxt))) {
           return false;
        } else if(!(isInteger(machineIdTxt))) {
           DialogBoxes.errorAlert("Exception" , "Machine ID has to be a number");
           return false;
       }
       return true;
    }

    /** This is the maxValueGreaterThanMinValue method.
     This method checks to see if the max field is greater than the min field and if it is then true will be returned, otherwise false will be returned.
     @param max the max field to compare
     @param min the min field to compare
     @return Returns a boolean value*/
    private static boolean maxValueGreaterThanMinValue(TextField max, TextField min) {
        if(Integer.parseInt(max.getText()) > Integer.parseInt(min.getText())) {
            return true;
        }
        return false;
    }

    /** This is the inventoryValueBetweenMinAndMax method.
     This method checks to see if the inv field is between the min and max field and returns true if it is and false if not.
     @param min the min value to compare
     @param max the max value to compare
     @param inv the inv value to compare
     @return Returns a boolean value*/
    private static boolean inventoryValueBetweenMinAndMax(TextField inv, TextField max, TextField min) {
        if(Integer.parseInt(inv.getText()) <= Integer.parseInt(max.getText())
                && Integer.parseInt(inv.getText()) >= Integer.parseInt(min.getText())) {
            return true;
        }
        return false;
    }

    /** This is the isMaxBetweenMin method. This method checks to see if max is greater than min.
     * @param minTxt the minTxt value to compare
     * @param maxTxt the maxTxt value to compare
     * @return Returns a boolean value*/
    public static boolean isMaxBetweenMin(TextField maxTxt, TextField minTxt) {
        if(!(InputValidation.maxValueGreaterThanMinValue(maxTxt, minTxt))) {
            TextFieldManipulation.setTextFieldRed(maxTxt);
            TextFieldManipulation.setTextFieldRed(minTxt);

            DialogBoxes.errorAlert("Error Dialog:" , "Maximum value must be greater than Minimum value");
            return false;
        }
        return true;
    }

    /** This is the isMaxBetweenMin method. This method checks to see inv is between max and min.
     * @param maxTxt the maxTxt value to compare
     * @param minTxt the minTxt value to compare
     * @param invTxt the invTxt value to compare
     * @return Returns a boolean value*/
    public static boolean isInvBetweenMaxAndMin(TextField invTxt, TextField maxTxt, TextField minTxt) {
        if (!(InputValidation.inventoryValueBetweenMinAndMax(invTxt, maxTxt, minTxt))) {
            TextFieldManipulation.setTextFieldRed(maxTxt);
            TextFieldManipulation.setTextFieldRed(minTxt);
            TextFieldManipulation.setTextFieldRed(invTxt);

            DialogBoxes.errorAlert("Error Dialog:" , "Inventory value must be between Maximum value and Minimum value");
            return false;
        }
        return true;
    }
}
