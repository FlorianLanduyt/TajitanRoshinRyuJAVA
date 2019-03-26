/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import domein.Lid;
import domein.Activiteit;
import domein.controllers.AdminController;
import domein.controllers.OverzichtController;
import domein.enums.Formule;
import gui.BeginSchermFlo;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author florianlanduyt
 */
public class ActiviteitenOverzicht extends Overzicht {

    private OverzichtController oc;
    private AdminController ac;
    private BeginSchermFlo parent;

    private TableView<Activiteit> tvActiviteiten;
    private TableColumn<Activiteit, String> colVoornaam;
    private TableColumn<Activiteit, String> colStartdatum;
    private TableColumn<Activiteit, String> colEinddatum;
    private TableColumn<Activiteit, String> colFormule;
    private TableView<Lid> deelnemers;

    private ComboBox<String> cbFormule;
    private VBox scherm;

    private Text txNaamLocatie;
    private Text txGsmnummerLocatie;
    private Text txEmailLocatie;     
    private Text txNaam;
    private Text txDatum;
    private Text txAdres;
    

    public ActiviteitenOverzicht(BeginSchermFlo parent, AdminController ac, String soortScherm) {
        super(parent, ac, soortScherm);
        this.oc = new OverzichtController();
        this.ac = ac;

        this.parent = parent;

        maakOverzicht();

        cbFormule.setOnAction((ActionEvent event) -> {
            filter();
        });

        tvActiviteiten.getSelectionModel().selectFirst();

    }

    private void maakOverzicht() {
        maakFilters();
        maakTabel();
        maakDetailScherm();

        super.buildGui(49);
    }

    private void maakFilters() {
        cbFormule = new ComboBox<>();
        cbFormule.setItems(oc.geefFormulesFilter());

        super.addCombobox(cbFormule);
    }

    private void maakTabel() {
        tvActiviteiten = new TableView<>();
        

        tvActiviteiten.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    vulDetailScherm(newSelection);
                });

        maakKolommenInTabel();
        
        Label tabelPlaceholder = new Label("Geen activiteiten beschikbaar");
        tabelPlaceholder.getStyleClass().add("placeholder");
        tvActiviteiten.setPlaceholder(tabelPlaceholder);

        tvActiviteiten.setItems((oc.geefOverzichtActiviteiten()));
        super.setTvTabel(tvActiviteiten);
    }

    private void maakKolommenInTabel() {
        colVoornaam = new TableColumn("Naam");
        colStartdatum = new TableColumn("Startdatum");
        colEinddatum = new TableColumn("Einddatum");
        colFormule = new TableColumn("Formule");

        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().naamProperty());
        colStartdatum.setCellValueFactory(cellData -> cellData.getValue().beginDatumProperty());
        colEinddatum.setCellValueFactory(cellData -> cellData.getValue().eindDatumProperty());
        colFormule.setCellValueFactory(cellData -> cellData.getValue().formuleProperty());
        

        super.addKolom(colVoornaam);
        super.addKolom(colStartdatum);
        super.addKolom(colEinddatum);
        super.addKolom(colFormule);
    }

    private void maakDetailScherm() {
        scherm = new VBox();
        geefInformatieActiviteit();

        deelnemers = new TableView();
        TableColumn<Lid, String> naam = new TableColumn("Voornaam");
        TableColumn<Lid, String> familienaam = new TableColumn("Familienaam");
        naam.setCellValueFactory(cellData -> cellData.getValue().voornaamProperty());
        familienaam.setCellValueFactory(cellData -> cellData.getValue().achternaamProperty());
        
        deelnemers.setPlaceholder(new Label("Geen deelnemers"));
        
        naam.getStyleClass().add("titelLinks");
        naam.getStyleClass().add("name-column");
        familienaam.getStyleClass().add("titelLinks");
        familienaam.getStyleClass().add("name-column");

        deelnemers.getColumns().add(familienaam);
        deelnemers.getColumns().add(naam);

        VBox deelnemersBox = opmaaDeelnemersTabel(deelnemers);
        deelnemersBox.setPadding(new Insets(-10,0,0,0));

        scherm.getChildren().add(deelnemersBox);
        super.setDetailScherm(scherm);

    }

    private void geefInformatieActiviteit() {
        Text lblNaam = new Text("Naam activiteit:");
        Text lblDatum = new Text("Datum:");
        Text lblNaamLocatie = new Text("Naam locatie:");
        Text lblGsmnummer = new Text("GSM-nummer locatie:");
        Text lblEmailLocatie = new Text("Emailadres locatie:");
        Text lblAdres = new Text("Adres locatie:");
        Text lblDeelnemers = new Text("Deelnemers:");

        opmaakLabels(Arrays.asList(lblNaam, lblDatum, lblAdres, lblDeelnemers,
                lblNaamLocatie, lblGsmnummer, lblEmailLocatie));

        txNaamLocatie = new Text();
        txGsmnummerLocatie = new Text();
        txEmailLocatie = new Text();
        txNaam = new Text();
        txDatum = new Text();
        txAdres = new Text();

        zetLabelEnInfoNaastElkaar(lblNaam, txNaam);
        zetLabelEnInfoNaastElkaar(lblDatum, txDatum);
        zetLabelEnInfoNaastElkaar(lblNaamLocatie, txNaamLocatie);
        zetLabelEnInfoNaastElkaar(lblGsmnummer, txGsmnummerLocatie);
        zetLabelEnInfoNaastElkaar(lblEmailLocatie, txEmailLocatie);
        zetLabelEnInfoNaastElkaar(lblAdres, txAdres);

        scherm.getChildren().add(lblDeelnemers);
    }

    private void zetLabelEnInfoNaastElkaar(Text label, Text info) {
        HBox HNaam = new HBox(label, info);
        HNaam.setSpacing(10);
        info.setStyle("-fx-font-size: 16px");

        scherm.getChildren().add(HNaam);
    }
    
    private void clearAlleVelden(){
        txNaam.setText("");
        txDatum.setText("");
        txNaamLocatie.setText("");;
        txGsmnummerLocatie.setText("");
        txEmailLocatie.setText("");
        txAdres.setText("");
        deelnemers.setItems(null);
    }

    private void vulDetailScherm(Activiteit a) {
        clearAlleVelden();
        try {
            txNaam.setText(a.getNaam());
            txDatum.setText(String.format("%s %s", a.beginDatumProperty().getValue(),
                   a.eindDatumProperty().getValue() == null ? "" : "tot " + a.eindDatumProperty().getValue()));
            txNaamLocatie.setText(a.getNaamLocatie());
            txGsmnummerLocatie.setText(a.getGsmnummer());
            txEmailLocatie.setText(a.getEmail());
            txAdres.setText(a.straatProperty().getValue() + " " + a.getHuisnummer() + ", " + a.getPostcode() + " " + a.getStad());
            deelnemers.setItems(FXCollections.observableList(a.getInschrijvingen()
                    .stream().
                    map(i -> i.getLid())
                    .sorted(Comparator.comparing(Lid::getAchternaam).thenComparing(Lid::getVoornaam))
                    .collect(Collectors.toList())));
        }catch(NullPointerException e){
            //als er geen activiteit is aangeduid in de tabel!
            
        }
    }

    private <T> VBox opmaaDeelnemersTabel(TableView<T> tabel) {
        VBox tabelBox = new VBox();
        tabelBox.setMaxSize(250, 200);
        tabel.getColumns().stream().forEach(k -> k.setMinWidth(124));
        tabelBox.getChildren().add(tabel);

        return tabelBox;
    }

    private void opmaakLabels(List<Text> labels) {
        labels.stream().forEach(l -> l.setStyle("-fx-font-weight: bold; -fx-font-size: 16px"));
    }

//    private void veranderTable(Object value) {
//        activiteitTabel.getColumns().stream().filter(p-> p.)
//    }
    private void filter() {
        tvActiviteiten.getSelectionModel().clearSelection();
        Formule formule = cbFormule
                .getSelectionModel()
                .getSelectedIndex() == 0
                        ? null
                        : cbFormule.getSelectionModel().getSelectedItem() == null
                        ? null
                        : Formule.valueOf(cbFormule.getSelectionModel().getSelectedItem());

        oc.veranderActiviteitenFilter(formule);
        tvActiviteiten.getSelectionModel().selectFirst();
    }
}
