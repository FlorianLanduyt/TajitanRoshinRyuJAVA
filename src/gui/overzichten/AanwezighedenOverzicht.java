/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import domein.Aanwezigheid;
import domein.controllers.AdminController;
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
    private Text txDatum;

    public AanwezighedenOverzicht(BeginSchermFlo parent, AdminController ac, String overzicht) {
        super(parent, ac, overzicht);

        this.parent = parent;
        this.ac = ac;
        this.oc = new OverzichtController();

        maakOverzicht();
        
        cbFormule.setOnAction((ActionEvent event) -> {
            filter();
        });
        
        cbLid.setOnAction((ActionEvent event) -> {
            filter();
        });
        
        dpDatum.setOnAction((ActionEvent event) -> {
            filter();
        });
        
        tvAanwezigheden.getSelectionModel().selectFirst();
    }

    private void maakOverzicht() {
        maakFilters();
        maakTabel();
        maakDetailScherm();

        super.buildGui(82);
    }

    private void maakFilters() {
        cbFormule = new ComboBox<>();
        cbFormule.setItems(oc.geefFormulesFilter());
        super.addCombobox(cbFormule);

        cbLid = new ComboBox<>();
        cbLid.setItems(oc.geefOverzichtLedenFilter());
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
        //detailScherm.setPadding(new Insets(80,0,0,0));

        super.setDetailScherm(detailScherm);
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
        Text lblDatum2 = new Text("Datum:");
        Text lblPuntenAantal = new Text("Puntenaantal:");
        Text lblFormule = new Text("Formule:");

        opmaakLabels(Arrays.asList(lblLid, lblDatum2 , lblPuntenAantal, lblFormule));

        txLid = new Text();
        txDatum = new Text();
        txPuntenAantal = new Text();
        txFormule = new Text();

        zetLabelEnInfoNaastElkaar(lblLid, txLid);
        zetLabelEnInfoNaastElkaar(lblDatum2, txDatum);
        zetLabelEnInfoNaastElkaar(lblPuntenAantal, txPuntenAantal);
        zetLabelEnInfoNaastElkaar(lblFormule, txFormule);
    }
    
    private void vulDetailScherm(Aanwezigheid a) {
        try{
            txDatum.setText(a.getDatum().toString());
        txLid.setText(a.getAchternaam() + " " + a.getVoornaam());
        txPuntenAantal.setText((Integer.toString(a.getPuntenAantal())));
        txFormule.setText(a.formuleProperty().getValue());
        }catch (NullPointerException e){
            //wanneer er geen aanwezigheid is geselecteerd in de tabel
        }
        
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

    private void filter() {
        Formule formule = cbFormule
                .getSelectionModel()
                .getSelectedIndex() == 0
                        ? null
                        : cbFormule.getSelectionModel().getSelectedItem() == null
                        ? null
                        : Formule.valueOf(cbFormule.getSelectionModel().getSelectedItem());
          
        String slid = cbLid.getSelectionModel().getSelectedIndex() == 0 
                ? null 
                : cbLid.getSelectionModel().getSelectedItem();
        LocalDate datum = dpDatum.getValue();
        
        oc.veranderAanwezigheidFilter(datum, slid, formule);
        tvAanwezigheden.getSelectionModel().selectFirst();
    }

   

}
