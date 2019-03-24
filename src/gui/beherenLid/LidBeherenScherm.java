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
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.paint.Color;

/**
 *
 * @author florianlanduyt
 */
public class LidBeherenScherm extends Overzicht {
    private BeginSchermFlo parent;
    private AdminController ac;
    private final String titel;
    private LidBeheerderController lc;
    
    private ComboBox<String> cboFilterGraad;
    private ComboBox<String> cboFilterType;
    private TextField txtFilterVoornaam;
    private TextField txtFilterFamilienaam;
    private TableView<Lid> tvOverzichtLeden;
    private TableColumn<Lid, String> colVoornaam, colAchternaam, colGraad, colType;
    
    private Button btnNieuwLid, btnWijzigingenOpslaan, btnLidVerwijderen, btnCancel, btnSlaGegevensNieuwLidOp;
    
    //detailpane
    private TextField txfVoornaam, txfAchternaam, txfRijksregisternummer,txfGsmnummer,txfStraat; 
    private TextField txfStad,txfPostcode,txfBus,txfTelefoon,txfEmail,txfEmailVader,txfEmailmoeder;
    private TextField txfNationaliteit,txfBeroep, txfWachtwoord, txfHuisnummer,txfGeboorteplaats;
//    
//    
    private Label lblVoornaam, lblFamilienaam, lblRijksregisternummer,lblGsmnummer,lblAdres, lblGeslacht; 
    private Label lblTelefoon,lblEmail,lblEmailVader,lblEmailmoeder, lblType, lblGeboorteDatum;
    private Label lblNationaliteit,lblBeroep, lblWachtwoord, lblGeboortePlaats, lblGraad;
    
    
    private DatePicker dpGeboorteDatum;
    
    private ComboBox<Graad> cboGraad;
    private ComboBox<String> cboGeslacht;
    private ComboBox<Functie> cboType_Functie;
    

    
    public LidBeherenScherm(BeginSchermFlo parent, AdminController ac, String titel) {
        super(parent, ac, titel);
        
        this.parent = parent;
        this.ac = ac;
        this.titel = titel;
        this.lc = new LidBeheerderController();
        
        maakOverzicht();
        
        cboFilterGraad.setOnAction((ActionEvent event) -> {
            filterLedenCBO();
        });
        
        cboFilterType.setOnAction((ActionEvent event) -> {
            filterLedenCBO();
        });
        
        txtFilterFamilienaam.setOnKeyReleased((KeyEvent event) -> {
            filterLedenTXT();
        });
        
        txtFilterVoornaam.setOnKeyReleased((KeyEvent event) -> {
            filterLedenTXT();
        });
        
        btnCancel.setOnAction((ActionEvent action) -> {
            cancelToevoegenNieuwLid();
        });
        
        btnLidVerwijderen.setOnAction((ActionEvent event) -> {
            verwijderenLid();
        });
        btnNieuwLid.setOnAction((ActionEvent event) -> {
            toevoegenLid();
        });
        
        btnWijzigingenOpslaan.setOnAction((ActionEvent event) -> {
            opslaanWijzigingen();
        });
        
        btnSlaGegevensNieuwLidOp.setOnAction((ActionEvent event)-> {
            slaGegevensNieuwLidOp();
        });
        
        tvOverzichtLeden.getSelectionModel().selectFirst();
    }

    private void maakOverzicht() {
        maakFilters();
        maakTabel();
        maakDetailScherm();
        maakCrudknoppen();

        super.buildGui(44);
    }

    private void maakFilters() {
        cboFilterGraad = new ComboBox<>();
        cboFilterGraad.setItems(lc.geefGradenFilter());
        cboFilterGraad.getStyleClass().add("greenBtn");
        cboFilterType = new ComboBox<>();
        cboFilterType.setItems(lc.geefFunctiesFilter());
        cboFilterType.getStyleClass().add("greenBtn");
        
        txtFilterVoornaam = new TextField();
        txtFilterVoornaam.setPromptText("Filter op voornaam");
        
        txtFilterFamilienaam = new TextField();
        txtFilterFamilienaam.setPromptText("Filter op familienaam");
        
        super.addTextField(txtFilterFamilienaam);
        super.addTextField(txtFilterVoornaam);
        super.addCombobox(cboFilterGraad);
        super.addCombobox(cboFilterType);
    }

    private void maakTabel() {
        tvOverzichtLeden = new TableView<>();

        tvOverzichtLeden.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    btnNieuwLid.setDisable(false);
                    vulDetailScherm(newSelection);
                });

        maakKolommenInTabel();

        tvOverzichtLeden.setItems((lc.geefObservableListLeden()));
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
            //als je de list verandert vindt hij geen data meer
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
        lblVoornaam = new Label("Voornaam *");
        lblFamilienaam = new Label("Naam *");
        lblGeboorteDatum = new Label("Geboortedatum *");
        lblGeboortePlaats = new Label("Geboorteplaats *");
        lblRijksregisternummer = new Label("Rijksregisternummer *");
        lblGeslacht = new Label("Geslacht *");
        lblNationaliteit = new Label("Nationaliteit *");
        lblGsmnummer = new Label("GSM-nummer *");
        lblTelefoon = new Label("Telefoonnummer");
        lblAdres = new Label("Adres*");
        lblBeroep = new Label("Beroep");
        lblEmail = new Label("Email *");
        lblEmailVader = new Label("Email vader");
        lblEmailmoeder = new Label("Email moeder");
        lblGraad = new Label("Graad *");
        lblType = new Label("Type *");
        lblWachtwoord = new Label("Wachtwoord *");
        
        GridPane form = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(25);
        form.getColumnConstraints().addAll(col1,col2,col3, col4);
        
        Insets insetsLabel = new Insets(3,0,-5,0);
        
        form.setAlignment(Pos.CENTER);
        form.setHgap(4);
        form.setVgap(5);
        //form.setPadding(new Insets(25, 25, 25, 25));
        
        //rij1
        lblType.setPadding(insetsLabel);
        cboType_Functie = new ComboBox<>();
        cboType_Functie.setItems(lc.geefFuncties());
        cboType_Functie.setMinWidth(239);
        cboType_Functie.getStyleClass().add("greyDropdown");
        
        //rij2
        lblVoornaam.setPadding(insetsLabel);
        lblFamilienaam.setPadding(insetsLabel);
        txfAchternaam = new TextField();
        txfAchternaam.setPromptText("Familienaam");
        txfVoornaam = new TextField();
        txfVoornaam.setPromptText("Voornaam");
        txfVoornaam.setStyle("-fx-background-color: #F3F2ED");
        
        //rij3
        lblGeboorteDatum.setPadding(insetsLabel);
        lblGeboortePlaats.setPadding(insetsLabel);
        dpGeboorteDatum = new DatePicker();
        dpGeboorteDatum.setMinWidth(239);
        dpGeboorteDatum.setStyle("-fx-background-color: #F3F2ED");
        txfGeboorteplaats = new TextField();
        txfGeboorteplaats.setPromptText("Stad");
        
        //rij4
        lblRijksregisternummer.setPadding(insetsLabel);
        lblGeslacht.setPadding(insetsLabel);
        lblNationaliteit.setPadding(insetsLabel);
        
        txfRijksregisternummer = new TextField();
        cboGeslacht = new ComboBox<>();
        cboGeslacht.setItems(lc.geefGeslachten());
        txfNationaliteit = new TextField();
        cboGeslacht.setMinWidth(118);
        cboGeslacht.getStyleClass().add("greyDropdown");
        
        //rij5
        lblGsmnummer.setPadding(insetsLabel);
        lblTelefoon.setPadding(insetsLabel);
        txfGsmnummer = new TextField();
        txfTelefoon = new TextField();
        
        //rij6
        lblAdres.setPadding(insetsLabel);
        txfStraat = new TextField();
        txfStraat.setPromptText("Straat *");
        txfHuisnummer = new TextField();
        txfHuisnummer.setPromptText("Nummer *");
        txfBus = new TextField();
        txfBus.setPromptText("Bus");
        txfStad = new TextField();
        txfStad.setPromptText("Gemeente *");
        txfPostcode = new TextField();
        txfPostcode.setPromptText("Postcode *");
        
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
        cboGraad.getStyleClass().add("greyDropdown");
        cboGraad.setMinWidth(118);
        
        //rij9
        lblEmailmoeder.setPadding(insetsLabel);
        lblWachtwoord.setPadding(insetsLabel);
        txfEmailmoeder = new TextField();
        txfWachtwoord = new TextField();
        
        //toevoegen elementen
        form.add(lblType, 0, 0);
        form.add(cboType_Functie, 0, 1,2,1);
        form.add(lblVoornaam, 0,2,2,1);
        form.add(lblFamilienaam, 2,2,2,1);
        form.add(txfVoornaam, 0,3,2,1);
        form.add(txfAchternaam, 2,3,2,1);
        form.add(lblGeboorteDatum, 0, 4,2,1);
        form.add(lblGeboortePlaats, 2,6,2,1);
        form.add(dpGeboorteDatum, 0, 5,2,1);
        form.add(txfGeboorteplaats, 2, 7,2,1);
        form.add(lblRijksregisternummer, 2, 4,2,1);
        form.add(lblGeslacht, 0, 6);
        form.add(lblNationaliteit, 1, 6);
        form.add(txfRijksregisternummer,2, 5,2,1);
        form.add(cboGeslacht,0, 7);
        form.add(txfNationaliteit,1, 7);
        form.add(lblGsmnummer, 0, 8,2,1);
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
        form.add(lblEmailmoeder, 0, 15,2,1);
        form.add(lblWachtwoord, 2, 15,2,1);
        form.add(txfEmailmoeder, 0, 16,2,1);
        form.add(txfWachtwoord, 2, 16,2,1);
        form.add(lblEmailVader, 0, 17,2,1);
        form.add(lblGraad, 2, 17,2,1);
        form.add(txfEmailVader, 0, 18,2,1);
        form.add(cboGraad, 2, 18,2,1);
        
        form.getChildren().stream().forEach(c->c.getStyleClass().add("allButtons"));
        
        VBox box = new VBox(form);
        super.setDetailScherm(box);
        
        cboType_Functie.setOnAction((ActionEvent event) -> {
            veranderForm();
        }); 
    }

    
    private void maakCrudknoppen() {
        btnLidVerwijderen = new Button("Lid verwijderen");
        btnLidVerwijderen.getStyleClass().add("allButtons");
        btnLidVerwijderen.getStyleClass().add("greyBtn");
        
        btnNieuwLid = new Button("Lid toevoegen");
        btnNieuwLid.getStyleClass().add("allButtons");
        btnNieuwLid.getStyleClass().add("orangeBtn");
        
        btnWijzigingenOpslaan = new Button("Wijzigingen opslaan");
        btnWijzigingenOpslaan.getStyleClass().add("allButtons");
        btnWijzigingenOpslaan.getStyleClass().add("orangeBtn");
        
        btnCancel = new Button("Cancel");
        btnCancel.getStyleClass().add("greyBtn");
        btnCancel.getStyleClass().add("allButtons");
        
        btnSlaGegevensNieuwLidOp = new Button("Lid toevoegen");
        btnSlaGegevensNieuwLidOp.getStyleClass().add("orangeBtn");
        btnSlaGegevensNieuwLidOp.getStyleClass().add("allButtons");
        
        
        super.addCrudKnop(btnLidVerwijderen);
        super.addExtraKnop(btnNieuwLid);
        super.addCrudKnop(btnWijzigingenOpslaan);
        
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
    
    private void verwijderenLid() {
        Lid lid = tvOverzichtLeden.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bevestiging verwijderen");
        alert.setHeaderText("Bevestiging");
        alert.setContentText(String.format("Ben je zeker dat je lid %s wil verwijderen?", lid.getVoornaam() + " " + lid.getAchternaam()));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            lc.verwijderLid(lid);
        }

    }
    
    private void opslaanWijzigingen() {
        if (!tvOverzichtLeden.getSelectionModel().isEmpty()) {
            Lid lid = tvOverzichtLeden.getSelectionModel().getSelectedItem();

            //alles opvragen
            //alle parameters mee te geven aan wijzigLid + het lid zelf!
            try {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Bevestiging verwijderen");
                alert.setHeaderText("Bevestiging");
                alert.setContentText(String.format("Ben je zeker dat je lid %s wil wijzigen?", lid.getVoornaam() + " " + lid.getAchternaam()));

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    lc.wijzigLid(lid, txfVoornaam.getText(), txfAchternaam.getText(), dpGeboorteDatum.getValue(),
                            txfRijksregisternummer.getText(), txfGsmnummer.getText(), txfTelefoon.getText(),
                            txfStraat.getText(), txfStad.getText(), txfHuisnummer.getText(), txfBus.getText(), txfPostcode.getText(),
                            txfEmail.getText(), txfEmailVader.getText(), txfEmailmoeder.getText(), txfGeboorteplaats.getText(), txfWachtwoord.getText(),
                            txfNationaliteit.getText(), txfBeroep.getText(), cboGraad.getSelectionModel().getSelectedItem(),
                            cboType_Functie.getSelectionModel().getSelectedItem(), cboGeslacht.getSelectionModel().getSelectedItem());
                    super.setErrorLabelText("");
                    
                }
            } catch (IllegalArgumentException e) {
                super.setErrorLabelText(e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fout");
            alert.setHeaderText("U heeft geen lid geselecteerd!");
            alert.setContentText("U moet een lid selecteren uit de lijst!");
            alert.showAndWait();
        }
        
    }
    private void toevoegenLid() {
        super.emptyCrudKnoppenList();
        super.addCrudKnop(btnCancel);
        super.addCrudKnop(btnSlaGegevensNieuwLidOp);
        super.maakCrudKnoppen();
        super.resetLabel();
        super.disableFilters(true);
        
        clearAlleVelden();
        tvOverzichtLeden.getSelectionModel().clearSelection();
    }
    
    
    private void cancelToevoegenNieuwLid() {
        tvOverzichtLeden.getSelectionModel().selectFirst();
        
        super.emptyCrudKnoppenList();
        super.addCrudKnop(btnLidVerwijderen);
        super.addCrudKnop(btnWijzigingenOpslaan);
        super.maakCrudKnoppen();
        super.resetLabel();
        super.disableFilters(false);
    }
    
    public void clearAlleVelden() {
        txfVoornaam.clear();
        txfAchternaam.clear();
        dpGeboorteDatum.setValue(null);
        txfGeboorteplaats.clear();
        txfRijksregisternummer.clear();
        txfGsmnummer.clear();
        txfTelefoon.clear();
        txfStraat.clear();
        txfHuisnummer.clear();
        txfBus.clear();
        txfStad.clear();
        txfPostcode.clear();
        txfEmail.clear();
        txfEmailVader.clear();
        txfEmailmoeder.clear();
        txfNationaliteit.clear();
        cboGeslacht.getSelectionModel().clearSelection();
        cboGraad.getSelectionModel().clearSelection();
        txfBeroep.clear();
        cboType_Functie.getSelectionModel().clearSelection();
        txfWachtwoord.clear();
    }
    
    private void slaGegevensNieuwLidOp() {
        try {
            lc.voegLidToe(txfVoornaam.getText(), txfAchternaam.getText(), dpGeboorteDatum.getValue(),
                            txfRijksregisternummer.getText(), txfGsmnummer.getText(), txfTelefoon.getText(),
                            txfStraat.getText(), txfStad.getText(), txfHuisnummer.getText(), txfBus.getText(), txfPostcode.getText(),
                            txfEmail.getText(), txfEmailVader.getText(), txfEmailmoeder.getText(), txfGeboorteplaats.getText(), txfWachtwoord.getText(),
                            txfNationaliteit.getText(), txfBeroep.getText(), cboGraad.getSelectionModel().getSelectedItem(),
                            cboType_Functie.getSelectionModel().getSelectedItem(), cboGeslacht.getSelectionModel().getSelectedItem());
            
            super.setErrorLabelText("");
            btnWijzigingenOpslaan.setText("Lid toevoegen");
        } catch (IllegalArgumentException e) {
            super.setErrorLabelText(e.getMessage());
        }
    }

    private void veranderForm() {
        Functie soortLid = cboType_Functie.getSelectionModel().getSelectedItem();
        
        if(soortLid == Functie.PROEFLID){
            txfGsmnummer.setDisable(true);
            txfStraat.setDisable(true);
            txfHuisnummer.setDisable(true);
            txfStad.setDisable(true);
            txfPostcode.setDisable(true);
            txfBus.setDisable(true);
            txfTelefoon.setDisable(true);
            txfEmail.setDisable(true);
            txfEmailVader.setDisable(true);
            txfEmailmoeder.setDisable(true);
            txfGeboorteplaats.setDisable(true);
            txfNationaliteit.setDisable(true);
            txfBeroep.setDisable(true);
            txfWachtwoord.setDisable(true);
            cboGraad.setDisable(true);
            
            lblGsmnummer.setDisable(true);
            lblAdres.setDisable(true);
            lblTelefoon.setDisable(true);
            lblEmail.setDisable(true);
            lblEmailVader.setDisable(true);
            lblEmailmoeder.setDisable(true);
            lblGeboortePlaats.setDisable(true);
            lblNationaliteit.setDisable(true);
            lblBeroep.setDisable(true);
            lblWachtwoord.setDisable(true);
            lblGraad.setDisable(true);
            
            
        } else {
            
            txfGsmnummer.setDisable(false);
            txfStraat.setDisable(false);
            txfHuisnummer.setDisable(false);
            txfStad.setDisable(false);
            txfPostcode.setDisable(false);
            txfBus.setDisable(false);
            txfTelefoon.setDisable(false);
            txfEmail.setDisable(false);
            txfEmailVader.setDisable(false);
            txfEmailmoeder.setDisable(false);
            txfGeboorteplaats.setDisable(false);
            txfNationaliteit.setDisable(false);
            txfBeroep.setDisable(false);
            txfWachtwoord.setDisable(false);
            cboGraad.setDisable(false);
            
            
            lblGsmnummer.setDisable(false);
            lblAdres.setDisable(false);
            lblTelefoon.setDisable(false);
            lblEmail.setDisable(false);
            lblEmailVader.setDisable(false);
            lblEmailmoeder.setDisable(false);
            lblGeboortePlaats.setDisable(false);
            lblNationaliteit.setDisable(false);
            lblBeroep.setDisable(false);
            lblWachtwoord.setDisable(false);
            lblGraad.setDisable(false);
        }
    }

}
