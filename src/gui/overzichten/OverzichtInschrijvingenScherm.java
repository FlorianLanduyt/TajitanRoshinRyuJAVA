package gui.overzichten;

import domein.Activiteit;
import domein.AdminController;
import domein.Formule;
import domein.Inschrijving;
import domein.Lid;
import domein.OverzichtController;
import exceptions.DatumIntervalException;
import gui.BeginScherm;
import gui.OverzichtMenu;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private TableView<Inschrijving> tvInschrijvingen;
    @FXML
    private TableColumn<Inschrijving, String> colVoornaam;
    @FXML
    private TableColumn<Inschrijving, String> colFamilienaam;
    @FXML
    private TableColumn<Inschrijving, String> colFormule;
    @FXML
    private TableColumn<Inschrijving, String> colDatum;
    @FXML
    private Button btnAlleInschrijvingen;
    @FXML
    private Label lblVan;
    @FXML
    private DatePicker dpDatumVan;
    @FXML
    private Button btnInschrijvingenInInterval;
    @FXML
    private Label lblPerFormule;
    @FXML
    private ComboBox<Formule> cbFormules;
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
        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().voornaamProperty());
        colFamilienaam.setCellValueFactory(cellData -> cellData.getValue().achternaamProperty());
        colFormule.setCellValueFactory(cellData -> cellData.getValue().formuleProperty());
        colDatum.setCellValueFactory(cellData -> cellData.getValue().tijdstipProperty());
        tvInschrijvingen.setItems(overzichtController.geefOverzichtInschrijvingen());
        //Combobox vullen
        cbFormules.setItems(overzichtController.geefFormules());
    }

    @FXML
    private void toonAlleInschrijvingen(ActionEvent event) {
        tvInschrijvingen.setItems(overzichtController.geefOverzichtInschrijvingen());
        cbFormules.getSelectionModel().clearSelection();
        dpDatumVan.setValue(null);
        dpDatumTot.setValue(null);
    }

    @FXML
    private void toonInschrijvingenInInterval(ActionEvent event) {
        try {
            LocalDate van = dpDatumVan.getValue();
            LocalDate tot = dpDatumTot.getValue();
            if (van != null && tot != null) {
                tvInschrijvingen.setItems(overzichtController.geefOverzichtInschrijvingenVoorBepaaldInterval(van, tot));
                cbFormules.getSelectionModel().clearSelection();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Filterfout");
                alert.setHeaderText("Filteren niet geslaagd");
                alert.setContentText("U dient een begin- en einddatum te selecteren.");
                alert.showAndWait();
            }
        } catch (DatumIntervalException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Filterfout");
            alert.setHeaderText("Filteren niet geslaagd");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void toonInschrijvingenPerFormule(ActionEvent event) {
        Formule formule = cbFormules.getSelectionModel().selectedItemProperty().getValue();
        if (formule != null) {
            tvInschrijvingen.setItems(overzichtController.geefOverzichtInschrijvingenVoorBepaaldeFormule(formule));
            dpDatumVan.setValue(null);
            dpDatumTot.setValue(null);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Filterfout");
            alert.setHeaderText("Filteren niet geslaagd");
            alert.setContentText("U dient een formule te selecteren.");
            alert.showAndWait();
        }
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
