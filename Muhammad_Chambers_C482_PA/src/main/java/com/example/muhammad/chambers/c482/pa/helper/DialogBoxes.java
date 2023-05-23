package com.example.muhammad.chambers.c482.pa.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/** This class holds the code for the dialog boxes.*/
public class DialogBoxes {
    private static Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    private static Alert warningAlert = new Alert(Alert.AlertType.WARNING);
    private static Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);


    /** This is the errorAlert method.
     This method displays an error alert dialog box.
     @param context the context explaining what the errorAlert is for
     @param title the title for the errorAlert*/
    public static void errorAlert(String title, String context) {
        errorAlert.setTitle(title);
        errorAlert.setContentText(context);
        errorAlert.showAndWait();
    }

    /** This is the confirmationAlert method.
     This method displays a confirmation alert dialog box.
     @param title the title for the errorAlert
     @param context the context explaining what the errorAlert is for
     @return Returns an Optional ButtonType object*/
    public static Optional<ButtonType> confirmationAlert(String title, String context) {
        confirmationAlert.setTitle(title);
        confirmationAlert.setContentText(context);

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        return result;
    }
}
