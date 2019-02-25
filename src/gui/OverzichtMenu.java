package gui;

import domein.AdminController;
import gui.overzichten.OverzichtActiviteitenScherm;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class OverzichtMenu extends VBox {
    
    @FXML
    private Button btnActiviteiten;
    @FXML
    private Button btnInschrijvingen;
    @FXML
    private Button btnAanwezigheden;
    @FXML
    private Button btnClubkampioenschap;
    @FXML
    private Button btnLesmateriaal;
    @FXML
    private Button btnTerug;
    
    private BeginScherm beginScherm;
    private AdminController adminController;
    
    public OverzichtMenu(BeginScherm beginScherm, AdminController adminController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtMenu.fxml"));
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
    private void toonOverzichtActiviteiten(ActionEvent event) {
        OverzichtActiviteitenScherm overzichtActiviteitenScherm
                = new OverzichtActiviteitenScherm(beginScherm, adminController);
        Scene scene = new Scene(overzichtActiviteitenScherm);
        Stage stage = (Stage) (getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("Taijitan Yoshin Ryu - Adminmodule - Activiteitenoverzicht");
        stage.setResizable(false);
        stage.show();
    }
    
    @FXML
    private void toonOverzichtInschrijvingen(ActionEvent event) {
        
    }
    
    @FXML
    private void toonOverzichtAanwezigheden(ActionEvent event) {
        
    }
    
    @FXML
    private void toonOverzichtClubkampioenschap(ActionEvent event) {
        
    }
    
    @FXML
    private void toonOverzichtLesmateriaal(ActionEvent event) {
        
    }
    
    @FXML
    private void terugNaarHoofdmenu(ActionEvent event) {
        beginScherm.getChildren().remove(this);
        beginScherm.getChildren().add(new HoofdMenu(beginScherm, adminController));
    }
    
}
