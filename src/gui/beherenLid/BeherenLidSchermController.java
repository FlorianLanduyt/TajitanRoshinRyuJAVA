/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.beherenLid;

import domein.Lid;
import domein.controllers.AdminController;
import domein.controllers.DataController;
import domein.controllers.LidBeheerderController;
import domein.controllers.OverzichtController;
import domein.enums.Functie;
import domein.enums.Graad;
import gui.BeginScherm;
import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author robdeputter
 */
public class BeherenLidSchermController extends AnchorPane {

    @FXML
    private ComboBox<Lid> cboFilterNaam;
    @FXML
    private ComboBox<Graad> cboFilterGraad;
    @FXML
    private ComboBox<Functie> cboFilterType;
    @FXML
    private TableView<Lid> tblOverzichtLeden;
    @FXML
    private TableColumn<Lid, String> colVoornaam;
    @FXML
    private TableColumn<Lid, String> colAchternaam;
    @FXML
    private TableColumn<Lid, String> colGraad;
    @FXML
    private TableColumn<Lid, String> colType;
    @FXML
    private Button btnNieuwLid;
    @FXML
    private Button btnWijzigingenOpslaan;
    @FXML
    private Button btnLidVerwijderen;

    // Gespecifieerde data
    @FXML
    private TextField txtVoornaam;
    @FXML
    private TextField txtAchternaam;
    @FXML
    private DatePicker dpGeboorteDatum;
    @FXML
    private TextField txtRijksregisternummer;

    @FXML
    private TextField txtGsmnummer;

    @FXML
    private TextField txtStraat;
    @FXML
    private TextField txtHuisnummer;
    @FXML
    private TextField txtStad;
    @FXML
    private TextField txtPostcode;
    @FXML
    private TextField txtBus;

    @FXML
    private TextField txtTelefoon;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtEmailVader;
    @FXML
    private TextField txtEmailmoeder;
    @FXML
    private TextField txtGeboorteplaats;
    @FXML
    private TextField txtNationaliteit;
    @FXML
    private TextField txtBeroep;
    @FXML
    private ComboBox<Graad> cboGraad;
    @FXML
    private Button btnTerug;
    @FXML
    private ComboBox<String> cboGeslacht;
    @FXML
    private ComboBox<Functie> cboType_Functie;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label lblAdminName;
    @FXML
    private Button btnSignOff;
    @FXML
    private DatePicker dpEersteTraining;
    @FXML
    private TextField txtWachtwoord;
    //andere variabelen
    private BeginScherm beginscherm;
    private AdminController adminController;
    private LidBeheerderController lidBeheerderController;
    @FXML
    private Button btnSlaGegevensNieuwLidOp;

    public BeherenLidSchermController(BeginScherm beginScherm, AdminController adminController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeherenLidScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        this.beginscherm = beginScherm;
        this.adminController = adminController;
        this.lidBeheerderController = new LidBeheerderController();

        btnSlaGegevensNieuwLidOp.setVisible(false);
        
        //tabel opvullen
        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().voornaamProperty());
        colAchternaam.setCellValueFactory(cellData -> cellData.getValue().achternaamProperty());
        colGraad.setCellValueFactory(cellData -> cellData.getValue().graadProperty());
        colType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        tblOverzichtLeden.setItems(lidBeheerderController.geefObservableListLeden());

        //comboboxen opvullen
        cboFilterNaam.setItems(lidBeheerderController.geefAlleLeden());
        cboFilterGraad.setItems(lidBeheerderController.geefGraden());
        cboFilterType.setItems(lidBeheerderController.geefFuncties());
        cboGraad.setItems(lidBeheerderController.geefGraden());
        cboType_Functie.setItems(lidBeheerderController.geefFuncties());
        cboGeslacht.setItems(lidBeheerderController.geefGeslachten());

        //veranderen bij het klikken in de tabel
        tblOverzichtLeden.getSelectionModel().selectedItemProperty()
                .addListener((obsValue, oldValue, newValue) -> {
                    toonSpecifiekeData(newValue);
                });

        //eerste rij selecteren!
        tblOverzichtLeden.getSelectionModel().selectFirst();

//        lidBeheerderController.addObserver(o
//                -> btnWijzigingenOpslaan.setDisable(tblOverzichtLeden.getSelectionModel().isEmpty()));

    }

    public void toonSpecifiekeData(Lid lid) {
        //alle tekstvelden clearen
        try {
            clearAlleVelden();
            txtVoornaam.setText(lid.getVoornaam());
            txtAchternaam.setText(lid.getAchternaam());
            dpGeboorteDatum.setValue(lid.getGeboortedatum());
            txtGeboorteplaats.setText(lid.getGeboorteplaats());
            txtRijksregisternummer.setText(lid.getRijksregisterNr());
            txtGsmnummer.setText(lid.getGsmNr());
            txtTelefoon.setText(lid.getVasteTelefoonNr());
            txtStraat.setText(lid.getStraat());
            txtHuisnummer.setText(lid.getHuisNr());
            txtBus.setText(lid.getBus());
            txtStad.setText(lid.getStad());
            txtPostcode.setText(lid.getPostcode());
            txtEmail.setText(lid.getEmail());
            txtEmailVader.setText(lid.getEmail());
            txtEmailmoeder.setText(lid.getEmailMoeder());
            txtNationaliteit.setText(lid.getNationaliteit());
            cboGeslacht.getSelectionModel().select(lid.getGeslacht());
            cboGraad.getSelectionModel().select(lid.getGraad());
            txtBeroep.setText(lid.getBeroep());
            cboType_Functie.getSelectionModel().select(lid.getFunctie());
            dpEersteTraining.setValue(lid.getDatumEersteTraining());
            txtWachtwoord.setText(lid.getWachtwoord());
        } catch (NullPointerException e) {
            //als je de list veranderd vindt hij geen data meer
        }

    }

    public void clearAlleVelden() {
        btnSlaGegevensNieuwLidOp.setVisible(false);//tijdelijk
        btnWijzigingenOpslaan.setDisable(false);//tijdelijk
        btnLidVerwijderen.setDisable(false);//tijdelijk
        txtVoornaam.clear();
        txtAchternaam.clear();
        dpGeboorteDatum.setValue(null);
        txtGeboorteplaats.clear();
        txtRijksregisternummer.clear();
        txtGsmnummer.clear();
        txtTelefoon.clear();
        txtStraat.clear();
        txtHuisnummer.clear();
        txtBus.clear();
        txtStad.clear();
        txtPostcode.clear();
        txtEmail.clear();
        txtEmailVader.clear();
        txtEmailmoeder.clear();
        txtNationaliteit.clear();
        cboGeslacht.getSelectionModel().clearSelection();
        cboGraad.getSelectionModel().clearSelection();
        txtBeroep.clear();
        cboType_Functie.getSelectionModel().clearSelection();
        dpEersteTraining.setValue(null);
        txtWachtwoord.clear();
    }

    @FXML
    private void toonLedenOpNaam(ActionEvent event) {
        Lid lid = cboFilterNaam.getSelectionModel().getSelectedItem();
        lidBeheerderController.geefOverzichtLid(lid);
        //andere cbo's resetten
        cboFilterGraad.getSelectionModel().clearSelection();
        cboFilterType.getSelectionModel().clearSelection();
        //eerste rij selecteren
        tblOverzichtLeden.getSelectionModel().selectFirst();

    }

    @FXML
    private void toonLedenOpGraad(ActionEvent event) {
        Graad graad = cboFilterGraad.getSelectionModel().getSelectedItem();
        lidBeheerderController.geefOverzichtLedenVoorBepaaldeGraad(graad);
        //andere cbo's resetten
        cboFilterNaam.getSelectionModel().clearSelection();
        cboFilterType.getSelectionModel().clearSelection();
        //eerste rij selecteren
        tblOverzichtLeden.getSelectionModel().selectFirst();

    }

    @FXML
    private void toonLedenOpType(ActionEvent event) {
        Functie functie = cboFilterType.getSelectionModel().getSelectedItem();
        lidBeheerderController.geefOverzichtLedenVoorBepaaldType(functie);
        //cbo's resetten
        cboFilterGraad.getSelectionModel().clearSelection();
        cboFilterNaam.getSelectionModel().clearSelection();
        //eerst rij selecteren
        tblOverzichtLeden.getSelectionModel().selectFirst();

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

    @FXML
    private void toevoegenLid(ActionEvent event) {
        tblOverzichtLeden.getSelectionModel().clearSelection();
        btnSlaGegevensNieuwLidOp.setVisible(true);
        btnWijzigingenOpslaan.setDisable(true);//tijdelijk
        btnLidVerwijderen.setDisable(true);//tijdelijk
        

    }
     @FXML
    private void slaGegevensNieuwLidOp(ActionEvent event) {
        try {
            lidBeheerderController.voegLidToe(txtVoornaam.getText(), txtAchternaam.getText(), dpGeboorteDatum.getValue(),
                    txtRijksregisternummer.getText(), dpEersteTraining.getValue(), txtGsmnummer.getText(), txtTelefoon.getText(),
                    txtStraat.getText(), txtStad.getText(), txtHuisnummer.getText(), txtBus.getText(), txtPostcode.getText(),
                    txtEmail.getText(), txtEmailVader.getText(), txtEmailmoeder.getText(), txtGeboorteplaats.getText(),txtWachtwoord.getText(),
                    txtNationaliteit.getText(), txtBeroep.getText(), cboGraad.getSelectionModel().getSelectedItem(),
                    cboType_Functie.getSelectionModel().getSelectedItem(), cboGeslacht.getSelectionModel().getSelectedItem());
            btnSlaGegevensNieuwLidOp.setVisible(false);//tijdelijk
            btnWijzigingenOpslaan.setDisable(false);// tijdelijk
            btnLidVerwijderen.setDisable(false);//tijdelijk
            
        }
        catch(IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Foute input!");
                alert.setHeaderText("U heeft een fout ingegeven voor de volgende waarde");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
        }
        
    }

    @FXML
    private void opslaanWijzigingen(ActionEvent event) {
        if (!tblOverzichtLeden.getSelectionModel().isEmpty()) {
            Lid lid = tblOverzichtLeden.getSelectionModel().getSelectedItem();
            
            //alles opvragen
            //alle parameters mee te geven aan wijzigLid + het lid zelf!
            try {
                lidBeheerderController.wijzigLid(lid, txtVoornaam.getText(), txtAchternaam.getText(), dpGeboorteDatum.getValue(),
                        txtRijksregisternummer.getText(), dpEersteTraining.getValue(), txtGsmnummer.getText(), txtTelefoon.getText(),
                        txtStraat.getText(), txtStad.getText(), txtHuisnummer.getText(), txtBus.getText(), txtPostcode.getText(),
                        txtEmail.getText(), txtEmailVader.getText(), txtEmailmoeder.getText(), txtGeboorteplaats.getText(), txtWachtwoord.getText(),
                        txtNationaliteit.getText(), txtBeroep.getText(), cboGraad.getSelectionModel().getSelectedItem(),
                        cboType_Functie.getSelectionModel().getSelectedItem(), cboGeslacht.getSelectionModel().getSelectedItem());
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Foute input!");
                alert.setHeaderText("U heeft een fout ingegeven voor de volgende waarde(n)");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fout");
            alert.setHeaderText("U heeft geen lid geselecteerd!");
            alert.setContentText("U moet een lid selecteren uit de lijst!");
            alert.showAndWait();
        }
    }

    @FXML
    private void verwijderenLid(ActionEvent event) {
        Lid lid = tblOverzichtLeden.getSelectionModel().getSelectedItem();
        lidBeheerderController.verwijderLid(lid);

    }

   

}
