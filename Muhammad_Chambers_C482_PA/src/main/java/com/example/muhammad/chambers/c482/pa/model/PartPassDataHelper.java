package com.example.muhammad.chambers.c482.pa.model;

import javafx.scene.control.TextField;

/** This is a data holder class to temporary hold the TextField values that will be passed between Screens.*/
public class PartPassDataHelper {
    private static String id = null;
    private static String name = null;
    private static String price = null;
    private static String min = null;
    private static String max = null;
    private static String inv = null;
    private static boolean wasScreenSwitched = false;

    /** This is the setId method.
     This method sets the id field.
     @param setId the id to set*/
    public static void setId(String setId) {
        id = String.valueOf(setId);
    }

    /** This is the setName method.
     This method sets the name field.
     @param nameTxtField the nameTxtField to set*/
    public static void setName(String nameTxtField) {
        name = nameTxtField;
    }

    /** This is the setPrice method.
     This method sets the price field.
     @param priceTxtField the priceTxtField to set*/
    public static void setPrice(String priceTxtField) {
        price = priceTxtField;
    }

    /** This is the setMin method.
     This method sets the min field.
     @param minTxtField the minTxtField to set*/
    public static void setMin(String minTxtField) {
        min = minTxtField;
    }

    /** This is the setMax method.
     This method sets the max field.
     @param maxTxtField the maxTxtField to set*/
    public static void setMax(String maxTxtField) {
        max = maxTxtField;
    }

    /** This is the setInv method.
     This method sets the inv field.
     @param invTxtField the invTxtField to set*/
    public static void setInv(String invTxtField) {
        inv = invTxtField;
    }

    /** This is the setInv method.
     This method sets the wasScreenSwitched field.
     @param setWasScreenSwitched the wasScreenSwitched to set*/
    public static void setWasScreenSwitched(Boolean setWasScreenSwitched) {
        wasScreenSwitched = setWasScreenSwitched;
    }

    /** This is the getId method.
     This method returns the id field.
     @return Returns the id*/
    public static String getId() {
        return id;
    }

    /** This is the getName method.
     This method returns the name field.
     @return Returns the name*/
    public static String getName() {
        return name;
    }

    /** This is the getPrice method.
     This method returns the price field.
     @return Returns the price*/
    public static String getPrice() {
        return price;
    }

    /** This is the getMin method.
     This method returns the min field.
     @return Returns the min*/
    public static String getMin() {
        return min;
    }

    /** This is the getMax method.
     This method returns the max field.
     @return Returns the max*/
    public static String getMax() {
        return max;
    }

    /** This is the getInv method.
     This method returns the inv field.
     @return Returns the inv*/
    public static String getInv() {
        return inv;
    }

    /** This is the getWasScreenSwitched method.
     This method returns the wasScreenSwitched field.
     @return Returns the wasScreenSwitched*/
    public static boolean getWasScreenSwitched() {
        return wasScreenSwitched;
    }

    /** This is the isFieldEmpty method.
     This method checks to see if a field is empty and returns a boolean value.
     @param value1 the string variable to test to see if it is empty
     @return Returns a boolean value*/
    public static boolean isFieldEmpty(String value1) {
        if(value1 == null) {
            return true;
        }
        return false;
    }

    /** This is the areFieldsEmpty method.
     This method checks all fields to see if they are empty and returns a boolean value.
     @return Returns a boolean value*/
    public static boolean areFieldsEmpty() {

        if(isFieldEmpty(name)) {
            return true;
        } else if(isFieldEmpty(inv)) {
            return true;
        } else if(isFieldEmpty(price)) {
            return true;
        } else if(isFieldEmpty(min)) {
            return true;
        } else if(isFieldEmpty(max)) {
            return true;
        }
        return false;
    }

    /** This is the setFieldsExceptId method.
     This method sets all the TextFields.
     @param invTxt the invTxt to set
     @param maxTxt the maxTxt to set
     @param priceCostTxt the priceCostTxt to set
     @param minTxt the minTxt to
     @param nameTxt the nameTxt to set*/
    public static void setFieldsExceptId(TextField nameTxt, TextField invTxt, TextField priceCostTxt, TextField minTxt, TextField maxTxt) {
        //Sets the values for the fields
        setName(nameTxt.getText());
        setInv(invTxt.getText());
        setPrice(priceCostTxt.getText());
        setMin(minTxt.getText());
        setMax(maxTxt.getText());
    }

    /** This is the setTextFieldsExceptId method.
     This method sets all TextFields except for the ID TextField with the values stored in the fields if they are not empty.
     @param maxTxt the maxTxt Text Field to set
     @param minTxt the minTxt Text Field to set
     @param invTxt the invTxt Text Field to set
     @param nameTxt the nameTxt Text Field to set
     @param priceCostTxt the priceCostTxt Text Field to set*/
    public static void setTextFieldsExceptId(TextField nameTxt, TextField priceCostTxt, TextField invTxt, TextField minTxt, TextField maxTxt) {

        /*
        Checks every field to see if it is not empty and if it is not empty
        then the Text Field value will be set based on the value stored in the field
         */
        if(!(isFieldEmpty(getName()))) {
            nameTxt.setText(getName());
        }

        if(!(isFieldEmpty(getPrice()))) {
            priceCostTxt.setText(getPrice());
        }

        if(!(isFieldEmpty(getInv()))) {
            invTxt.setText(getInv());
        }

        if(!(isFieldEmpty(getMin()))) {
            minTxt.setText(getMin());
        }

        if(!(isFieldEmpty(getMax()))) {
            maxTxt.setText(getMax());
        }
    }

    /** This is the setTextFieldsExceptId method.
     This method sets all the TextFields with the values stored in the fields if they are not empty.
     @param idTxt the idTxt Text field to set
     @param maxTxt the maxTxt Text Field to set
     @param minTxt the minTxt Text Field to set
     @param invTxt the invTxt Text Field to set
     @param nameTxt the nameTxt Text Field to set
     @param priceTxt the priceTxt Text Field to set*/
    public static void setTextFields(TextField idTxt, TextField nameTxt, TextField priceTxt, TextField invTxt, TextField minTxt, TextField maxTxt) {
        idTxt.setText(getId());
        nameTxt.setText(getName());
        priceTxt.setText(getPrice());
        invTxt.setText(getInv());
        minTxt.setText(getMin());
        maxTxt.setText(getMax());
    }

    /** This is the resetFieldsExceptId method. This method resets the fields, except for ID, values if they are NOT empty.*/
    public static void resetFieldsExceptId() {
        //Resets the field values if they are not empty
        if(!(areFieldsEmpty())) {
            setName("");
            setInv("");
            setPrice("");
            setMin("");
            setMax("");
        }
    }

    /** This is the resetFieldsExceptId method. This method resets the fields values if they are NOT empty.*/
    public static void resetFields() {
        //Resets the field values if they are not empty
        if(!(isFieldEmpty(id))) {
            setId("");
        }
        if(!(isFieldEmpty(name))) {
            setName("");
        }
        if(!(isFieldEmpty(inv))) {
            setInv("");
        }
        if(!(isFieldEmpty(price))) {
            setPrice("");
        }
        if(!(isFieldEmpty(min))) {
            setMin("");
        }
        if(!(isFieldEmpty(max))) {
            setMax("");
        }
    }
}
