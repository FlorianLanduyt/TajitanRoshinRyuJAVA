/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.controllers.AdminController;
import gui.BeginSchermFlo;
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
       // primaryStage.setFullScreen(true);
        primaryStage.setMaximized(true);
        BeginSchermFlo beginScherm = new BeginSchermFlo(new AdminController(), false, "Hoofdmenu");
        //Settings for GUI
        Scene scene = new Scene(beginScherm, 1250, 750);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        
        primaryStage.setScene(scene);
        
        primaryStage.setTitle("Taijitan Yoshin Ryu - Adminmodule");
        primaryStage.setResizable(true);
        primaryStage.show();
    }

}
