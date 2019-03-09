package gui;

import domein.controllers.AdminController;
import gui.beherenActiviteit.BeherenActiviteitSchermController;
import gui.beherenLid.BeherenLidSchermController;
import gui.overzichten.OverzichtAanwezighedenScherm;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HoofdMenu extends VBox {

    @FXML
    private Button btnOverzicht;
    @FXML
    private Button btnBeheerLeden;
    @FXML
    private Button btnBeheerActiviteiten;
    @FXML
    private Button btnInschrijvenActiviteit;
    @FXML
    private Button btnBeheerLesmateriaal;

    private BeginScherm beginScherm;
    private OverzichtMenu overzichtMenu;
    
    private AdminController adminController;

    public HoofdMenu(BeginScherm beginScherm, AdminController adminController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HoofdMenu.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.beginScherm = beginScherm;
        this.adminController = adminController;
    }

    @FXML
    private void toonOverzichtMenu(ActionEvent event) {
        beginScherm.getChildren().remove(this);
        overzichtMenu = new OverzichtMenu(beginScherm, adminController);
        beginScherm.getChildren().add(overzichtMenu);
    }
    
    @FXML
    private void toonBeherenLidMenu(ActionEvent event) {
        BeherenLidSchermController beherenLidSchermController
                = new BeherenLidSchermController(beginScherm, adminController);
        Scene scene = new Scene(beherenLidSchermController);
        Stage stage = (Stage) (getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("Taijitan Yoshin Ryu - Adminmodule - Beheer leden");
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.show();
    }
    
    @FXML
    private void toonBeherenActiviteit(ActionEvent event) {
        BeherenActiviteitSchermController beherenActiviteitSchermController
                = new BeherenActiviteitSchermController(beginScherm, adminController);
        Scene scene = new Scene(beherenActiviteitSchermController);
        Stage stage = (Stage) (getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("Taijitan Yoshin Ryu - Adminmodule - Beheer activiteiten");
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.show();
        
        
    }
    
    
}
