/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.BeginScherm;
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
        BeginScherm beginScherm = new BeginScherm();
        //Settings for GUI
        Scene scene = new Scene(beginScherm, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Taijitan Yoshin Ryu - Adminmodule");
        primaryStage.show();
    }

}
