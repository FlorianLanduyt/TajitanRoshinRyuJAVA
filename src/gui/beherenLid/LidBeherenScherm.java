/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.beherenLid;

import domein.Lid;
import domein.controllers.AdminController;
import domein.controllers.LidBeheerderController;
import domein.controllers.OverzichtController;
import domein.enums.Functie;
import domein.enums.Graad;
import gui.BeginSchermFlo;
import gui.overzichten.Overzicht;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author florianlanduyt
 */
public class LidBeherenScherm extends Overzicht {
    private BeginSchermFlo parent;
    private AdminController ac;
    private OverzichtController oc;
    private final String titel;
    private LidBeheerderController lc;
    
    private ComboBox<String> cboFilterGraad;
    private ComboBox<String> cboFilterType;
    private TextField txtFilterVoornaam;
    private TextField txtFilterFamilienaam;
    private TableView<Lid> tvOverzichtLeden;
    private TableColumn<Lid, String> colVoornaam;
    private TableColumn<Lid, String> colAchternaam;
    private TableColumn<Lid, String> colGraad;
    private TableColumn<Lid, String> colType;
    private Button btnNieuwLid;
    private Button btnWijzigingenOpslaan;
    private Button btnLidVerwijderen;
    
    
    //detailpane
    private TextField txfVoornaam;
    private TextField txfAchternaam;
    private DatePicker dpGeboorteDatum;
    private TextField txfRijksregisternummer;
    private TextField txfGsmnummer;
    private TextField txfStraat;
    private TextField txfHuisnummer;
    private TextField txfStad;
    private TextField txfPostcode;
    private TextField txfBus;
    private TextField txfTelefoon;
    private TextField txfEmail;
    private TextField txfEmailVader;
    private TextField txfEmailmoeder;
    private TextField txfGeboorteplaats;
    private TextField txfNationaliteit;
    private TextField txfBeroep;
    private TextField txfWachtwoord;
    
    private ComboBox<Graad> cboGraad;
    private ComboBox<String> cboGeslacht;
    private ComboBox<Functie> cboType_Functie;

    
    public LidBeherenScherm(BeginSchermFlo parent, AdminController ac, String titel) {
        super(parent, ac, titel);
        
        this.parent = parent;
        this.ac = ac;
        this.oc = new OverzichtController();
        this.titel = titel;
        this.lc = new LidBeheerderController();
        
        maakOverzicht();
        
        cboFilterGraad.setOnAction((ActionEvent event) -> {
            filterLedenCBO();
        });
        
        cboFilterType.setOnAction((ActionEvent event) -> {
            filterLedenCBO();
        });
        
        txtFilterFamilienaam.setOnKeyPressed((KeyEvent event) -> {
            filterLedenTXT();
        });
        
        txtFilterVoornaam.setOnKeyPressed((KeyEvent event) -> {
            filterLedenTXT();
        });
        
    }

    private void maakOverzicht() {
        maakFilters();
        maakTabel();
        maakDetailScherm();

        super.buildGui(60);
    }

    private void maakFilters() {
        cboFilterGraad = new ComboBox<>();
        cboFilterGraad.setItems(lc.geefGradenFilter());
        cboFilterType = new ComboBox<>();
        cboFilterType.setItems(lc.geefFunctiesFilter());
        
        txtFilterVoornaam = new TextField();
        txtFilterVoornaam.setPromptText("Filter op voornaam");
        
        txtFilterFamilienaam = new TextField();
        txtFilterFamilienaam.setPromptText("Filter op familienaam");
        
        super.addTextField(txtFilterFamilienaam);
        super.addTextField(txtFilterVoornaam);
        
        super.addCombobox(cboFilterType);
        super.addCombobox(cboFilterGraad);
    }

    private void maakTabel() {
        tvOverzichtLeden = new TableView<>();

        tvOverzichtLeden.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    vulDetailScherm(newSelection);
                });

        maakKolommenInTabel();

        tvOverzichtLeden.setItems((lc.geefAlleLeden()));
        super.setTvTabel(tvOverzichtLeden);
    }

    private void vulDetailScherm(Lid lid) {
        try {
            txfVoornaam.setText(lid.getVoornaam());
            txfAchternaam.setText(lid.getAchternaam());
            dpGeboorteDatum.setValue(lid.getGeboortedatum());
            txfGeboorteplaats.setText(lid.getGeboorteplaats());
            txfRijksregisternummer.setText(lid.getRijksregisterNr());
            txfGsmnummer.setText(lid.getGsmNr());
            txfTelefoon.setText(lid.getVasteTelefoonNr());
            txfStraat.setText(lid.getStraat());
            txfHuisnummer.setText(lid.getHuisNr());
            txfBus.setText(lid.getBus());
            txfStad.setText(lid.getStad());
            txfPostcode.setText(lid.getPostcode());
            txfEmail.setText(lid.getEmail());
            txfEmailVader.setText(lid.getEmail());
            txfEmailmoeder.setText(lid.getEmailMoeder());
            txfNationaliteit.setText(lid.getNationaliteit());
            cboGeslacht.getSelectionModel().select(lid.getGeslacht());
            cboGraad.getSelectionModel().select(lid.getGraad());
            txfBeroep.setText(lid.getBeroep());
            cboType_Functie.getSelectionModel().select(lid.getFunctie());
            txfWachtwoord.setText(lid.getWachtwoord());
        } catch (NullPointerException e) {
            //als je de list veranderd vindt hij geen data meer
        }

    }

    private void maakKolommenInTabel() {
        colAchternaam = new TableColumn<>("Familienaam");
        colVoornaam = new TableColumn<>("Voornaam");
        colGraad = new TableColumn<>("Graad");
        colType = new TableColumn<>("Functie");
        
        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().voornaamProperty());
        colAchternaam.setCellValueFactory(cellData -> cellData.getValue().achternaamProperty());
        colGraad.setCellValueFactory(cellData -> cellData.getValue().graadProperty());
        colType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        
        super.addKolom(colAchternaam);
        super.addKolom(colVoornaam);
        super.addKolom(colGraad);
        super.addKolom(colType);
    }

    private void maakDetailScherm() {
        Label lblNaam = new Label("Naam en voornaam *");
        Label lblGeboorteDatum = new Label("Geboortedatum *");
        Label lblGeboortePlaats = new Label("Geboorteplaats *");
        Label lblRijksregisternummer = new Label("Rijksregisternummer *");
        Label lblGeslacht = new Label("Geslacht *");
        Label lblNationaliteit = new Label("Nationaliteit *");
        Label lblGsm = new Label("GSM-nummer *");
        Label lblTelefoon = new Label("Telefoonnummer");
        Label lblAdres = new Label("Adres*");
        Label lblBeroep = new Label("Beroep");
        Label lblEmail = new Label("Email *");
        Label lblEmailVader = new Label("Email vader");
        Label lblEmailMoeder = new Label("Email moeder");
        Label lblGraad = new Label("Graad *");
        Label lblType = new Label("Type *");
        Label lblWachtwoord = new Label("Wachtwoord *");
        
        GridPane form = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        //col1.setHalignment(HPos.LEFT);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        //col2.setHalignment(HPos.CENTER);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        //col3.setHalignment(HPos.CENTER);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(25);
        //col4.setHalignment(HPos.RIGHT);
        form.getColumnConstraints().addAll(col1,col2,col3, col4);
        
        Insets insetsLabel = new Insets(3,0,-5,0);
        
        form.setAlignment(Pos.CENTER);
        form.setHgap(4);
        form.setVgap(5);
        form.setPadding(new Insets(25, 25, 25, 25));
        
        //rij1
        lblType.setPadding(insetsLabel);
        cboType_Functie = new ComboBox<>();
        cboType_Functie.setItems(lc.geefFuncties());
        cboType_Functie.setMinWidth(208);
        
        //rij2
        lblNaam.setPadding(insetsLabel);
        txfAchternaam = new TextField();
        txfVoornaam = new TextField();
        
        //rij3
        lblGeboorteDatum.setPadding(insetsLabel);
        lblGeboortePlaats.setPadding(insetsLabel);
        dpGeboorteDatum = new DatePicker();
        dpGeboorteDatum.setMinWidth(208);
        txfGeboorteplaats = new TextField();
        
        //rij4
        lblRijksregisternummer.setPadding(insetsLabel);
        lblGeslacht.setPadding(insetsLabel);
        lblNationaliteit.setPadding(insetsLabel);
        
        txfRijksregisternummer = new TextField();
        cboGeslacht = new ComboBox<>();
        cboGeslacht.setItems(lc.geefGeslachten());
        txfNationaliteit = new TextField();
        cboGeslacht.setMinWidth(103);
        
        //rij5
        lblGsm.setPadding(insetsLabel);
        lblTelefoon.setPadding(insetsLabel);
        txfGsmnummer = new TextField();
        txfTelefoon = new TextField();
        
        //rij6
        lblAdres.setPadding(insetsLabel);
        txfStraat = new TextField();
        txfHuisnummer = new TextField();
        txfBus = new TextField();
        txfStad = new TextField();
        txfPostcode = new TextField();
        
        //rij7
        lblBeroep.setPadding(insetsLabel);
        lblEmail.setPadding(insetsLabel);
        txfBeroep = new TextField();
        txfEmail = new TextField();
        
        //rij8
        lblEmailVader.setPadding(insetsLabel);
        lblGraad.setPadding(insetsLabel);
        txfEmailVader = new TextField();
        cboGraad = new ComboBox<>();
        cboGraad.setItems(lc.geefGraden());
        
        
        //rij9
        lblEmailMoeder.setPadding(insetsLabel);
        lblWachtwoord.setPadding(insetsLabel);
        txfEmailmoeder = new TextField();
        txfWachtwoord = new TextField();
        
        
        //toevoegen elementen
        form.add(lblType, 0, 0);
        form.add(cboType_Functie, 0, 1,2,1);
        form.add(lblNaam, 0,2,2,1);
        form.add(txfAchternaam, 0,3,2,1);
        form.add(txfVoornaam, 2,3,2,1);
        form.add(lblGeboorteDatum, 0, 4,2,1);
        form.add(lblGeboortePlaats, 2,4,2,1);
        form.add(dpGeboorteDatum, 0, 5,2,1);
        form.add(txfGeboorteplaats, 2, 5,2,1);
        form.add(lblRijksregisternummer, 0, 6,2,1);
        form.add(lblGeslacht, 3, 6);
        form.add(lblNationaliteit, 2, 6);
        form.add(txfRijksregisternummer,0, 7,2,1);
        form.add(cboGeslacht,3, 7);
        form.add(txfNationaliteit,2, 7);
        form.add(lblGsm, 0, 8,2,1);
        form.add(lblTelefoon, 2, 8,2,1);
        form.add(txfGsmnummer, 0, 9,2,1);
        form.add(txfTelefoon, 2, 9,2,1);
        form.add(lblAdres, 0, 10);
        form.add(txfStraat, 0,11,2,1);
        form.add(txfHuisnummer, 2,11);
        form.add(txfBus, 3,11);
        form.add(txfPostcode, 0,12);
        form.add(txfStad, 1,12,2,1);
        form.add(lblEmail, 0,13,2,1);
        form.add(lblBeroep, 2,13,2,1);
        form.add(txfEmail, 0,14,2,1);
        form.add(txfBeroep, 2,14,2,1);
        form.add(lblEmailMoeder, 0, 15,2,1);
        form.add(lblWachtwoord, 2, 15,2,1);
        form.add(txfEmailmoeder, 0, 16,2,1);
        form.add(txfWachtwoord, 2, 16,2,1);
        form.add(lblEmailVader, 0, 17,2,1);
        form.add(lblGraad, 2, 17,2,1);
        form.add(txfEmailVader, 0, 18,2,1);
        form.add(cboGraad, 2, 18,2,1);
        
        
        VBox box = new VBox(form);
        super.setDetailScherm(box);
        
    }
    
    private void filterLedenCBO() {
        String voornaam = txtFilterVoornaam.getText();
        String familienaam = txtFilterFamilienaam.getText();
        Graad graad = cboFilterGraad
                .getSelectionModel()
                .getSelectedIndex() == 0
                        ? null
                        : cboFilterGraad.getSelectionModel().getSelectedItem() == null
                        ? null
                        : Graad.valueOf(cboFilterGraad.getSelectionModel().getSelectedItem());

        Functie functie = cboFilterType
                .getSelectionModel()
                .getSelectedIndex() == 0
                        ? null
                        : cboFilterType.getSelectionModel().getSelectedItem() == null
                        ? null
                        : Functie.valueOf(cboFilterType.getSelectionModel().getSelectedItem());

        lc.filterList(voornaam, familienaam, graad, functie);
        tvOverzichtLeden.getSelectionModel().selectFirst();
    }
    
    private void filterLedenTXT() {
        String voornaam = txtFilterVoornaam.getText();
        String familienaam = txtFilterFamilienaam.getText();

        Graad graad = cboFilterGraad
                .getSelectionModel()
                .getSelectedIndex() == 0
                        ? null
                        : cboFilterGraad.getSelectionModel().getSelectedItem() == null
                        ? null
                        : Graad.valueOf(cboFilterGraad.getSelectionModel().getSelectedItem());

        Functie functie = cboFilterType
                .getSelectionModel()
                .getSelectedIndex() == 0
                        ? null
                        : cboFilterType.getSelectionModel().getSelectedItem() == null
                        ? null
                        : Functie.valueOf(cboFilterType.getSelectionModel().getSelectedItem());

        lc.filterList(voornaam, familienaam, graad, functie);
        tvOverzichtLeden.getSelectionModel().selectFirst();
    }
}
