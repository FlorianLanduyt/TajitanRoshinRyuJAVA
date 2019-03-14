/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import com.sun.javafx.scene.control.TableColumnComparatorBase;
import domein.Aanwezigheid;
import domein.controllers.AdminController;
import domein.controllers.OverzichtController;
import domein.enums.Formule;
import gui.BeginSchermFlo;
import java.util.Arrays;
import java.util.List;
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
public class AanwezighedenOverzicht extends Overzicht {

    private BeginSchermFlo parent;
    private AdminController ac;
    private OverzichtController oc;

    private ComboBox<String> cbFormule;
    private ComboBox<String> cbLid;
    private DatePicker dpDatum;

    private VBox detailScherm;
    private Label lblDatum;

    private TableView<Aanwezigheid> tvAanwezigheden;
    private TableColumn<Aanwezigheid, String> colFamilienaam;
    private TableColumn<Aanwezigheid, String> colVoornaam;
    private TableColumn<Aanwezigheid, String> colDatum;
    private TableColumn<Aanwezigheid, String> colNaamActiviteit;
    
    private Text txPuntenAantal;
    private Text txLid;
    private Text txFormule;

    public AanwezighedenOverzicht(BeginSchermFlo parent, AdminController ac, String overzicht) {
        super(parent, ac, overzicht);

        this.parent = parent;
        this.ac = ac;
        this.oc = oc;

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

        cbLid = new ComboBox<>();
        cbLid.setItems(oc.geefOverzichtLedenFilter());
        super.addCombobox(cbLid);
        super.addCombobox(cbLid);

        lblDatum = new Label("Van: ");
        dpDatum = new DatePicker();

        HBox HTot = new HBox(lblDatum, dpDatum);
        HTot.setAlignment(Pos.CENTER);
        super.addDatePicker(HTot);
    }

    private void maakTabel() {
        tvAanwezigheden = new TableView<>();

        tvAanwezigheden.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            vulDetailScherm(newSelection);
        });

        maakKolommenInTabel();

        tvAanwezigheden.setItems((oc.geefOverzichtAanwezigheden()));
        super.setTvTabel(tvAanwezigheden);
    }
    
     private void maakDetailScherm() {
        detailScherm = new VBox();
        geefInformatieInschrijving();

        super.setDetailScherm(detailScherm);
    }

    private void vulDetailScherm(Aanwezigheid a) {
        txFormule.setText(a.formuleProperty().getName());
        txLid.setText(a.getAchternaam() + " " + a.getVoornaam());
        txPuntenAantal.setText(Integer.toString(a.getPuntenAantal()));
    }

    private void maakKolommenInTabel() {
        colFamilienaam = new TableColumn<>("Familienaam");
        colVoornaam = new TableColumn<>("Voornaam");
        colDatum = new TableColumn<>("Datum");
        colNaamActiviteit = new TableColumn<>("Naam Activiteit");

        colFamilienaam.setCellValueFactory(cellData -> cellData.getValue().achternaamProperty());
        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().voornaamProperty());
        colDatum.setCellValueFactory(cellData -> cellData.getValue().datumProperty());
        colNaamActiviteit.setCellValueFactory(cellData -> cellData.getValue().activiteitNaamProperty());

        super.addKolom(colNaamActiviteit);
        super.addKolom(colFamilienaam);
        super.addKolom(colVoornaam);
        super.addKolom(colDatum);

    }

    private void geefInformatieInschrijving() {
        Text lblLid = new Text("Lid:");
        Text lblFormule = new Text("Datum:");
        Text lblPuntenAantal = new Text("Formules:");

        opmaakLabels(Arrays.asList(lblLid, lblFormule , lblPuntenAantal));

        txLid = new Text();
        txFormule = new Text();
        txPuntenAantal = new Text();

        zetLabelEnInfoNaastElkaar(lblLid, txLid);
        zetLabelEnInfoNaastElkaar(lblFormule, txFormule);
        zetLabelEnInfoNaastElkaar(lblPuntenAantal, txPuntenAantal);


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
