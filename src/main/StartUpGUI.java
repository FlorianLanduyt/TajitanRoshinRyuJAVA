/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.controllers.AdminController;
import gui.BeginScherm;
import gui.BeginSchermFlo;
import gui.MenuKnoppen;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Tybo Vanderstraeten
 */
public class StartUpGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Declaring guiController instance
       // primaryStage.setFullScreen(true);
        primaryStage.setMaximized(true);
        BeginSchermFlo beginScherm = new BeginSchermFlo(new AdminController(), false, "Hoofdmenu");
        //Settings for GUI
        Scene scene = new Scene(beginScherm, 1250, 750);
        //String css = this.getClass().getResource("/css/style.css").toExternalForm();
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        
        primaryStage.setScene(scene);
        
        primaryStage.setTitle("Taijitan Yoshin Ryu - Adminmodule");
        primaryStage.setResizable(true);
        primaryStage.show();
    }

}
