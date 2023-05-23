package com.example.muhammad.chambers.c482.pa.helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

/** This class holds the file path locations to different Screens and the methods used to switch the Screen.*/
public class FilePaths {
    private Stage stage;
    private Parent scene;
    private static final String MAIN_MENU_FILE_PATH = "/com/example/muhammad/chambers/c482/pa/view/MainMenu.fxml";
    private static final String MODIFY_PART_IN_HOUSE_FILE_PATH = "/com/example/muhammad/chambers/c482/pa/view/ModifyPartInHouse.fxml";
    private static final String MODIFY_PART_OUTSOURCED_FILE_PATH = "/com/example/muhammad/chambers/c482/pa/view/ModifyPartOutsourced.fxml";
    private static final String MODIFY_PRODUCT_FILE_PATH = "/com/example/muhammad/chambers/c482/pa/view/ModifyProduct.fxml";
    private static final String ADD_PRODUCT_FILE_PATH = "/com/example/muhammad/chambers/c482/pa/view/AddProduct.fxml";
    private static final String ADD_PART_IN_HOUSE_FILE_PATH = "/com/example/muhammad/chambers/c482/pa/view/AddPartInHouse.fxml";
    private static final String ADD_PART_OUTSOURCED_FILE_PATH = "/com/example/muhammad/chambers/c482/pa/view/AddPartOutsourced.fxml";

    /** This is the getMainMenuFilePath method.
     This method returns the mainMenuFilePath.
     @return Returns the MAIN_MENU_FILE_PATH*/
    public String getMainMenuFilePath() {
        return MAIN_MENU_FILE_PATH;
    }

    /** This is the getModifyPartInHouseFilePath method.
     This method returns the modifyPartInHouseFilePath.
     @return Returns the MODIFY_PART_IN_HOUSE_FILE_PATH*/
    public String getModifyPartInHouseFilePath() {
        return MODIFY_PART_IN_HOUSE_FILE_PATH;
    }

    /** This is the getModifyProductFilePath method.
     This method returns the modifyProductFilePath.
     @return Returns the MODIFY_PRODUCT_FILE_PATH*/
    public String getModifyProductFilePath() {
        return MODIFY_PRODUCT_FILE_PATH;
    }


    /** This is the getAddProductFilePath method.
     This method returns the addProductFilePath.
     @return Returns the ADD_PRODUCT_FILE_PATH*/
    public String getAddProductFilePath() {
        return ADD_PRODUCT_FILE_PATH;
    }

    /** This is the getAddPartInHouseFilePath method.
     This method returns the addPartInHouseFilePath.
     @return Returns the ADD_PART_IN_HOUSE_FILE_PATH*/
    public String getAddPartInHouseFilePath() {
        return ADD_PART_IN_HOUSE_FILE_PATH;
    }

    /** This is the getAddPartOutsourcedFilePath method.
     This method returns the addPartOutsourcedFilePath.
     @return Returns the ADD_PART_OUTSOURCED_FILE_PATH*/
    public String getAddPartOutsourcedFilePath() {
        return ADD_PART_OUTSOURCED_FILE_PATH;
    }

    /** This is the getModifyPartOutsourcedFilePath method.
     This method returns the modifyPartOutsourcedFilePath.
     @return Returns the MODIFY_PART_OUTSOURCED_FILE_PATH*/
    public String getModifyPartOutsourcedFilePath() {
        return MODIFY_PART_OUTSOURCED_FILE_PATH;
    }

    /** This is the switchScreen method.
     This method switches the screen to another screen.
     @param event the ActionEvent event
     @param filePath the filePath to the file
     @param screenName the screeName for the screen*/
    public void switchScreen(ActionEvent event, String filePath, String screenName) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(filePath));
        stage.setTitle(screenName);
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This is the switchScreeRadioBtn method.
     This method switches the screen to another screen for radio buttons.
     @param event the ActionEvent event
     @param filePath the filePath to the file
     @param screenName the screeName for the screen*/
    public void switchScreenRadioBtn(ActionEvent event, String filePath, String screenName) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(filePath));
        stage.setTitle(screenName);
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
