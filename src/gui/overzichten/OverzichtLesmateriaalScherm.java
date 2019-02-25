package gui.overzichten;

import domein.Activiteit;
import domein.AdminController;
import domein.Lid;
import domein.Oefening;
import domein.OverzichtController;
import domein.Raadpleging;
import gui.BeginScherm;
import gui.OverzichtMenu;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OverzichtLesmateriaalScherm extends AnchorPane {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label lblAdminName;
    @FXML
    private ImageView ivSignOff;
    @FXML
    private Button btnSignOff;
    @FXML
    private TableView<Raadpleging> tvRaadplegingen;
    @FXML
    private TableColumn<Raadpleging, String> colVoornaam;
    @FXML
    private TableColumn<Raadpleging, String> colFamilienaam;
    @FXML
    private TableColumn<Raadpleging, String> colNaamLesmateriaal;
    @FXML
    private TableColumn<Raadpleging, String> colAantalRaadplegingen;
    @FXML
    private Label lblPerLesmateriaal;
    @FXML
    private Button btnRaadplegingenPerLesmateriaal;
    @FXML
    private Label lblPerLid;
    @FXML
    private ComboBox<Lid> cbLeden;
    @FXML
    private Button btnRaadplegingenPerLid;
    @FXML
    private ComboBox<Oefening> cbLesmateriaal;

    private BeginScherm beginScherm;
    private AdminController adminController;
    private OverzichtController overzichtController;

    public OverzichtLesmateriaalScherm(BeginScherm beginScherm, AdminController adminController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtLesmateriaalScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.beginScherm = beginScherm;
        this.adminController = adminController;
        this.overzichtController = new OverzichtController();
        this.getChildren().add(new OverzichtMenu(this.beginScherm, this.adminController));

        lblAdminName.setText("Welkom, " + adminController
                .getAangemeldeAdmin().getGebruikersnaam());

        //Tableview setup
        //Combobox vullen
        cbLeden.setItems(overzichtController.geefOverzichtLeden());

    }

    @FXML
    private void toonRaadplegingenPerLesmateriaal(ActionEvent event) {
    }

    @FXML
    private void toonRaadplegingenPerLid(ActionEvent event) {
    }

    @FXML
    private void meldAf(ActionEvent event) {
        BeginScherm beginScherm = new BeginScherm();
        Scene scene = new Scene(beginScherm);
        Stage stage = (Stage) (getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("Taijitan Yoshin Ryu - Adminmodule");
        stage.setResizable(false);
        stage.show();
    }

}
