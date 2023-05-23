package com.example.muhammad.chambers.c482.pa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.muhammad.chambers.c482.pa.helper.ScreensEnum;

/*
     Muhammad Chambers
     Western Governors University
     C482 Software 1
     Term 1
     5 May 2023
 */

/** This class creates the application which allows you to create parts and products. FUTURE ENHANCEMENT: 1.Improve the UI, 2.Refactor code, 3.Add a database for persistent data*/
public class Main extends Application {

    /** This is the start method.
     This method creates the Main Menu Screen when the program starts.
     @param stage the stage to set*/
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(String.valueOf(ScreensEnum.MAIN_MENU));
        stage.setScene(scene);
        stage.show();
    }

    //The JavaDoc can be found in a subfolder called javadoc that is inside the main folder C482 where the code for this project is also found
    /** This is the main method.
     This method is where the program starts and executes code.
     @param args the args to use*/
    public static void main(String[] args) {
        launch();
    }
}