/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import domein.Activiteit;
import domein.Inschrijving;
import domein.controllers.AdminController;
import domein.controllers.DataController;
import domein.controllers.OverzichtController;
import domein.enums.Formule;
import gui.BeginSchermFlo;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class InschrijvingenOverzicht extends Overzicht {

    private AdminController ac;
    private BeginSchermFlo parent;
    private OverzichtController oc;

    private ComboBox<String> cbFormule;
    private DatePicker dpDatumVan;
    private DatePicker dpDatumTot;
    private Label lblTot;
    private Label lblVan;

    private TableView<Inschrijving> tvInschrijvingenTabel;
    private TableColumn<Inschrijving, String> colVoornaam;
    private TableColumn<Inschrijving, String> colFamilienaam;
    private TableColumn<Inschrijving, String> colFormules;

    private DataController dc;

    private VBox detailScherm;
    private TableView<Activiteit> tvActiviteiten;
    private TableColumn<Activiteit, String> colNaamActiviteit;
    private TableColumn<Activiteit, String> colStartdatumActiviteit;
    private TableColumn<Activiteit, String> colEinddatumActiviteit;
    private Text txLid;
    private Text txDatum;

    public InschrijvingenOverzicht(BeginSchermFlo parent, AdminController ac, String titelMenu) {
        super(parent, ac, titelMenu);

        this.oc = new OverzichtController();
        this.ac = ac;
        this.parent = parent;
        maakOverzicht();

        cbFormule.setOnAction((ActionEvent event) -> {
            filter();
        });

        dpDatumVan.setOnAction((ActionEvent event) -> {
            filter();
        });

        dpDatumTot.setOnAction((ActionEvent event) -> {
            filter();
        });

        tvInschrijvingenTabel.getSelectionModel().selectFirst();

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

        lblVan = new Label("Van: ");
        lblTot = new Label("Tot: ");
        dpDatumVan = new DatePicker();
        dpDatumTot = new DatePicker();
        HBox HVan = new HBox(lblVan, dpDatumVan);
        HVan.setAlignment(Pos.CENTER);
        HBox HTot = new HBox(lblTot, dpDatumTot);
        HTot.setAlignment(Pos.CENTER);

        super.addDatePicker(HVan);
        super.addDatePicker(HTot);

    }

    private void maakTabel() {
        tvInschrijvingenTabel = new TableView<>();
        

        tvInschrijvingenTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            vulDetailScherm(newSelection);
        });

        maakKolommenInTabel();
        Label tabelPlaceholder = new Label("Geen inschrijvingen beschikbaar");
        tabelPlaceholder.getStyleClass().add("placeholder");
        tvInschrijvingenTabel.setPlaceholder(tabelPlaceholder);

        tvInschrijvingenTabel.setItems((oc.geefOverzichtInschrijvingen()));
        super.setTvTabel(tvInschrijvingenTabel);
        
    }

    private void maakKolommenInTabel() {
        colVoornaam = new TableColumn("Voornaam");
        colFamilienaam = new TableColumn("Familienaam");
        colFormules = new TableColumn("Formules");

        colFamilienaam.setCellValueFactory(cellData -> cellData.getValue().achternaamProperty());
        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().voornaamProperty());
        colFormules.setCellValueFactory(cellData -> cellData.getValue().formuleProperty());

        
        super.addKolom(colFamilienaam);
        super.addKolom(colVoornaam);
        super.addKolom(colFormules);
    }

    private void maakDetailScherm() {
        detailScherm = new VBox();
        geefInformatieInschrijving();

        tvActiviteiten = new TableView();
        colNaamActiviteit = new TableColumn("Naam");
        colStartdatumActiviteit = new TableColumn("Startdatum");
        colEinddatumActiviteit = new TableColumn("Einddatum");
        colNaamActiviteit.setCellValueFactory(cellData -> cellData.getValue().naamProperty());
        colStartdatumActiviteit.setCellValueFactory(cellData -> cellData.getValue().beginDatumProperty());
        colEinddatumActiviteit.setCellValueFactory(cellData -> cellData.getValue().eindDatumProperty());
        tvActiviteiten.getColumns().addAll(colNaamActiviteit,colStartdatumActiviteit, colEinddatumActiviteit);
        
        tvActiviteiten.getStyleClass().add("titelLinks");
        colNaamActiviteit.getStyleClass().add("name-column");
        colStartdatumActiviteit.getStyleClass().add("name-column");
        colEinddatumActiviteit.getStyleClass().add("name-column");
        
        tvActiviteiten.setMaxHeight(200);
        tvActiviteiten.setPlaceholder(new Label("Geen deelnemers"));
        tvActiviteiten.setPadding(new Insets(-10,0,0,0));

        VBox formulesBox = opmaakActiviteitenTabel(tvActiviteiten);

        detailScherm.getChildren().add(formulesBox);
        super.setDetailScherm(detailScherm);
    }
    
    private void clearAlleVelden(){
        txLid.setText("");
        txDatum.setText("");
        tvActiviteiten.setItems(null);
    }

    private void vulDetailScherm(Inschrijving inschrijving) {
        clearAlleVelden();
        try {
            txLid.setText(inschrijving.getAchternaam() + ", " + inschrijving.getVoornaam());
            txDatum.setText(inschrijving.getTijdstip().toString());

            tvActiviteiten.setItems(oc.geefActiviteitenVoorInschrijving(inschrijving));
        }catch(NullPointerException e){
            //wanneer er geen inschrijving is geselecteerd
        }

    }

    private void geefInformatieInschrijving() {
        Text lblLid = new Text("Lid:");
        Text lblDatum = new Text("Datum:");
        Text lblDeelnemers = new Text("Formules:");
        

        opmaakLabels(Arrays.asList(lblLid, lblDatum, lblDeelnemers));

        txLid = new Text();
        txDatum = new Text();

        zetLabelEnInfoNaastElkaar(lblLid, txLid);
        zetLabelEnInfoNaastElkaar(lblDatum, txDatum);

        detailScherm.getChildren().add(lblDeelnemers);

    }

    private VBox opmaakActiviteitenTabel(TableView<Activiteit> activiteitTabel) {
        VBox tabelBox = new VBox();
        tabelBox.setMaxSize(300, 300);
        activiteitTabel.getColumns().stream().forEach(k -> k.setMinWidth(99));
        tabelBox.getChildren().add(activiteitTabel);

        return tabelBox;
    }

    private void opmaakLabels(List<Text> labels) {
        labels.stream().forEach(l -> l.setStyle("-fx-font-weight: bold;-fx-font-size: 16px"));
    }

    private void zetLabelEnInfoNaastElkaar(Text label, Text info) {
        HBox HNaam = new HBox(label, info);
        HNaam.setSpacing(10);
        info.setStyle("-fx-font-size: 16px");

        detailScherm.getChildren().add(HNaam);
    }

    private void filter() {
        Formule formule = cbFormule
                .getSelectionModel()
                .getSelectedIndex() == 0
                        ? null
                        : cbFormule.getSelectionModel().getSelectedItem() == null
                        ? null
                        : Formule.valueOf(cbFormule.getSelectionModel().getSelectedItem());
        LocalDate van = dpDatumVan.getValue();
        LocalDate tot = dpDatumTot.getValue();
        oc.veranderInschrijvingFilter(formule, van, tot);
        tvInschrijvingenTabel.getSelectionModel().selectFirst();
    }

}
