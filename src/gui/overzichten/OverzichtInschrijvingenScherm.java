package gui.overzichten;

import domein.Activiteit;
import domein.AdminController;
import domein.Lid;
import domein.OverzichtController;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OverzichtInschrijvingenScherm extends AnchorPane {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label lblAdminName;
    @FXML
    private ImageView ivSignOff;
    @FXML
    private Button btnSignOff;
    @FXML
    private TableView<?> tvInschrijvingen;
    @FXML
    private TableColumn<?, ?> colVoornaam;
    @FXML
    private TableColumn<?, ?> colFamilienaam;
    @FXML
    private TableColumn<?, ?> colFormule;
    @FXML
    private TableColumn<?, ?> colDatum;
    @FXML
    private Label lblVan;
    @FXML
    private DatePicker dpDatumVan;
    @FXML
    private Button btnInschrijvingenInInterval;
    @FXML
    private Label lblPerFormule;
    @FXML
    private ComboBox<?> cbFormules;
    @FXML
    private Button btnInschrijvingenPerFormule;
    @FXML
    private Label lblTot;
    @FXML
    private DatePicker dpDatumTot;

    private BeginScherm beginScherm;
    private AdminController adminController;
    private OverzichtController overzichtController;

    public OverzichtInschrijvingenScherm(BeginScherm beginScherm, AdminController adminController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtInschrijvingenScherm.fxml"));
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
    }

    @FXML
    private void toonInschrijvingenInInterval(ActionEvent event) {
    }

    @FXML
    private void toonInschrijvingenPerFormule(ActionEvent event) {
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
