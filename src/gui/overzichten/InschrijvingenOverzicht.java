/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import domein.Inschrijving;
import domein.controllers.AdminController;
import domein.controllers.DataController;
import domein.controllers.OverzichtController;
import domein.enums.Formule;
import gui.BeginSchermFlo;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
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
    private TableColumn<Inschrijving, String> colDatum;
    private TableColumn<Inschrijving, String> colFormules;

    private DataController dc;

    private VBox detailScherm;
    private TableView<Formule> tvFormules;
    private Text txLid;
    private Text txDatum;

    public InschrijvingenOverzicht(BeginSchermFlo parent, AdminController ac, String titelMenu) {
        super(parent, ac, titelMenu);

        this.dc = new DataController();
        this.oc = new OverzichtController();
        this.ac = ac;
        this.parent = parent;
        maakOverzicht();
    }

    private void maakOverzicht() {
        maakFilters();
        maakTabel();
        maakDetailScherm();

        super.buildGui();
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
        HBox HTot = new HBox(lblTot, dpDatumTot);
        super.addDatePicker(dpDatumVan);
        super.addDatePicker(dpDatumTot);

    }

    private void maakTabel() {
        tvInschrijvingenTabel = new TableView<>();

        tvInschrijvingenTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            vulDetailScherm();
        });

        maakKolommenInTabel();

        tvInschrijvingenTabel.setItems((oc.geefOverzichtInschrijvingen()));
        super.setTvTabel(tvInschrijvingenTabel);
    }

    private void maakKolommenInTabel() {
        colVoornaam = new TableColumn("Familienaam");
        colFamilienaam = new TableColumn("Voornaam");
        colFormules = new TableColumn("Formules");

        colFamilienaam.setCellValueFactory(cellData -> cellData.getValue().achternaamProperty());
        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().voornaamProperty());
        colFormules.setCellValueFactory(cellData -> cellData.getValue().formuleProperty());

        
        //Kolommen worden niet toegevoegd
        super.addKolom(colVoornaam);
        super.addKolom(colDatum);
        super.addKolom(colFormules);
    }

    private void maakDetailScherm() {
        detailScherm = new VBox();
        geefInformatieInschrijving();

        tvFormules = new TableView();
        TableColumn<Formule, String> formules = new TableColumn("Formules");
        //formules.setCellValueFactory(cellData -> cellData.getValue());
        tvFormules.getColumns().add(formules);

        VBox formulesBox = opmaakFormulesTabel(tvFormules);

        detailScherm.getChildren().add(formulesBox);
        super.setDetailScherm(detailScherm);
    }

    private void vulDetailScherm() {
        Inschrijving i = tvInschrijvingenTabel.getSelectionModel().getSelectedItem();
        txLid.setText(i.getAchternaam() + i.getVoornaam());
        txDatum.setText(i.getTijdstip().toString());

        tvFormules.setItems((oc.geefFormulesPerLid(i.getLid())));
    }

    private void geefInformatieInschrijving() {
        Text lblLid = new Text("Lid:");
        Text lblDatum = new Text("Datum:");
        Text lblAdres = new Text("Adres:");
        Text lblDeelnemers = new Text("Deelnemers:");

        opmaakLabels(Arrays.asList(lblLid, lblDatum, lblAdres, lblDeelnemers));

        txLid = new Text();
        txDatum = new Text();

        zetLabelEnInfoNaastElkaar(lblLid, txLid);
        zetLabelEnInfoNaastElkaar(lblDatum, txDatum);

        detailScherm.getChildren().add(lblDeelnemers);

    }

    private VBox opmaakFormulesTabel(TableView<Formule> formules) {
        VBox tabelBox = new VBox();
        tabelBox.setMaxSize(200, 200);
        formules.getColumns().stream().forEach(k -> k.setMinWidth(199));
        tabelBox.getChildren().add(formules);

        return tabelBox;
    }

    private void opmaakLabels(List<Text> labels) {
        labels.stream().forEach(l -> l.setStyle("-fx-font-weight: bold; -fx-underline: true; -fx-font-size: 16px"));
    }

    private void zetLabelEnInfoNaastElkaar(Text label, Text info) {
        HBox HNaam = new HBox(label, info);
        HNaam.setSpacing(10);
        info.setStyle("-fx-font-size: 16px");

        detailScherm.getChildren().add(HNaam);
    }

}
