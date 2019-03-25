package gui.overzichten;

import domein.Aanwezigheid;
import domein.controllers.AdminController;
import domein.enums.Formule;
import domein.controllers.OverzichtController;
import gui.BeginScherm;
import gui.BeginSchermFlo;
import gui.OverzichtMenu;
import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class OverzichtAanwezighedenScherm extends AnchorPane {

    @FXML
    private Label lblAanwezigheden;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label lblAdminName;
    @FXML
    private ImageView ivSignOff;
    @FXML
    private Button btnSignOff;
    @FXML
    private TableView<Aanwezigheid> tvAanwezigheden;
    @FXML
    private TableColumn<Aanwezigheid, String> colNaamActiviteit;
    @FXML
    private TableColumn<Aanwezigheid, String> colVoornaam;
    @FXML
    private TableColumn<Aanwezigheid, String> colFamilienaam;
    @FXML
    private TableColumn<Aanwezigheid, String> colFormule;
    @FXML
    private TableColumn<Aanwezigheid, String> colDatum;
    @FXML
    private Button btnAlleAanwezigheden;
    @FXML
    private ComboBox<String> cbLeden;
    @FXML
    private Label lblPerDeelnemer;
    @FXML
    private Button btnAanwezighedenPerLid;
    @FXML
    private Label lblOpDag;
    @FXML
    private DatePicker dpDatum;
    @FXML
    private Button btnAanwezighedenOpDatum;
    @FXML
    private Label lblPerFormule;
    @FXML
    private ComboBox<String> cbFormules;
    @FXML
    private Button btnAanwezighedenPerFormule;

    private BeginSchermFlo beginScherm;
    private AdminController adminController;
    private OverzichtController overzichtController;

    public OverzichtAanwezighedenScherm(BeginSchermFlo beginScherm, AdminController adminController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtAanwezighedenScherm.fxml"));
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
        colNaamActiviteit.setCellValueFactory(cellData -> cellData.getValue().activiteitNaamProperty());
        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().voornaamProperty());
        colFamilienaam.setCellValueFactory(cellData -> cellData.getValue().achternaamProperty());
        colFormule.setCellValueFactory(cellData -> cellData.getValue().formuleProperty());
        colDatum.setCellValueFactory(cellData -> cellData.getValue().datumProperty());
        tvAanwezigheden.setItems(overzichtController.geefOverzichtAanwezigheden());
        //Combobox vullen
        cbLeden.setItems(overzichtController.geefOverzichtLedenFilter());
        cbFormules.setItems(overzichtController.geefFormulesFilter());
    }

    @FXML
    private void toonAlleAanwezigheden(ActionEvent event) {
        tvAanwezigheden.setItems(overzichtController.geefOverzichtAanwezigheden());
        cbFormules.getSelectionModel().clearSelection();
        cbLeden.getSelectionModel().clearSelection();
        dpDatum.setValue(null);
    }

    @FXML
    private void toonAanwezighedenPerLid(ActionEvent event) {
//        Lid lid = cbLeden.getSelectionModel().selectedItemProperty().getValue();
//        if (lid != null) {
//            tvAanwezigheden.setItems(overzichtController.geefOverzichtAanwezighedenVoorBepaaldLid(lid));
//            dpDatum.setValue(null);
//            cbFormules.getSelectionModel().clearSelection();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Filterfout");
//            alert.setHeaderText("Filteren niet geslaagd");
//            alert.setContentText("U dient een lid te selecteren.");
//            alert.showAndWait();
//        }
        filter();
    }

    @FXML
    private void toonAanwezighedenOpDatum(ActionEvent event) {
//        try {
//            LocalDate datum = dpDatum.getValue();
//            if (datum != null) {
//                tvAanwezigheden.setItems(overzichtController.geefOverzichtAanwezighedenVoorBepaaldeDatum(datum));
//                cbLeden.getSelectionModel().clearSelection();
//                cbFormules.getSelectionModel().clearSelection();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Filterfout");
//                alert.setHeaderText("Filteren niet geslaagd");
//                alert.setContentText("U dient een datum te selecteren.");
//                alert.showAndWait();
//            }
//        } catch (DatumIntervalException ex) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Filterfout");
//            alert.setHeaderText("Filteren niet geslaagd");
//            alert.setContentText(ex.getMessage());
//            alert.showAndWait();
//        }
        filter();

    }

    @FXML
    private void toonAanwezighedenPerFormule(ActionEvent event) {
//        Formule formule = cbFormules.getSelectionModel().selectedItemProperty().getValue();
//        if (formule != null) {
//            tvAanwezigheden.setItems(overzichtController.geefOverzichtAanwezighedenVoorBepaaldeFormule(formule));
//            dpDatum.setValue(null);
//            cbLeden.getSelectionModel().clearSelection();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Filterfout");
//            alert.setHeaderText("Filteren niet geslaagd");
//            alert.setContentText("U dient een formule te selecteren.");
//            alert.showAndWait();
//        }
        filter();
    }
    
    public void filter(){
          Formule formule = cbFormules
                .getSelectionModel()
                .getSelectedIndex() == 0
                        ? null
                        : cbFormules.getSelectionModel().getSelectedItem() == null
                        ? null
                        : Formule.valueOf(cbFormules.getSelectionModel().getSelectedItem());
          
        String slid = cbLeden.getSelectionModel().getSelectedIndex() == 0 
                ? null 
                : cbLeden.getSelectionModel().getSelectedItem();
        LocalDate datum = dpDatum.getValue();
        
//        overzichtController.veranderAanwezigheidFilter(datum, slid, formule);
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
