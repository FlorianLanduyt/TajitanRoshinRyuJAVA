package gui;

import domein.Admin;
import domein.controllers.AdminController;
import gui.overzichten.OverzichtAanwezighedenScherm;
import gui.overzichten.OverzichtActiviteitenScherm;
import gui.overzichten.OverzichtClubkampioenschapScherm;
import gui.overzichten.OverzichtInschrijvingenScherm;
import gui.overzichten.OverzichtLesmateriaalScherm;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

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

    private BeginSchermFlo beginScherm;
    private AdminController adminController;

    public OverzichtMenu(BeginSchermFlo beginScherm, AdminController adminController) {
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
        OverzichtInschrijvingenScherm overzichtInschrijvingenScherm
                = new OverzichtInschrijvingenScherm(beginScherm, adminController);
        Scene scene = new Scene(overzichtInschrijvingenScherm);
        Stage stage = (Stage) (getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("Taijitan Yoshin Ryu - Adminmodule - Inschrijvingsoverzicht");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void toonOverzichtAanwezigheden(ActionEvent event) {
        OverzichtAanwezighedenScherm overzichtAanwezighedenScherm
                = new OverzichtAanwezighedenScherm(beginScherm, adminController);
        Scene scene = new Scene(overzichtAanwezighedenScherm);
        Stage stage = (Stage) (getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("Taijitan Yoshin Ryu - Adminmodule - Aanwezigheidsoverzicht");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void toonOverzichtClubkampioenschap(ActionEvent event) {
        OverzichtClubkampioenschapScherm overzichtClubkampioenschapScherm
                = new OverzichtClubkampioenschapScherm(beginScherm, adminController);
        Scene scene = new Scene(overzichtClubkampioenschapScherm);
        Stage stage = (Stage) (getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("Taijitan Yoshin Ryu - Adminmodule - Clubkampioenschapsoverzicht");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void toonOverzichtLesmateriaal(ActionEvent event) {
        OverzichtLesmateriaalScherm overzichtLesmateriaalScherm
                = new OverzichtLesmateriaalScherm(beginScherm, adminController);
        Scene scene = new Scene(overzichtLesmateriaalScherm);
        Stage stage = (Stage) (getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("Taijitan Yoshin Ryu - Adminmodule - Lesmateriaaloverzicht");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void terugNaarHoofdmenu(ActionEvent event) {
        beginScherm.getChildren().add(new HoofdMenu(beginScherm, adminController));
        beginScherm.getChildren().remove(this);
    }

}
