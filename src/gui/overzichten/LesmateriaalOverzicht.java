/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import domein.Activiteit;
import domein.Lid;
import domein.Oefening;
import domein.Raadpleging;
import domein.controllers.AdminController;
import domein.controllers.OverzichtController;
import domein.enums.Formule;
import gui.BeginSchermFlo;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author robdeputter
 */
public class LesmateriaalOverzicht extends Overzicht {

    private OverzichtController oc;
    private AdminController ac;
    private BeginSchermFlo parent;

    private TableView<Raadpleging> raadplegingTabel;
    private TableColumn<Raadpleging, String> colVoornaam;
    private TableColumn<Raadpleging, String> colFamilienaam;
    private TableColumn<Raadpleging, String> colNaamLesmateriaal;
    private TableColumn<Raadpleging, String> colAantalRaadplegingen;

    private ComboBox<String> cbLeden;
    private ComboBox<String> cbLesmateriaal;
    private VBox scherm;

    private Text txTitel;
    private Text txTotaalAantalRaadplegingen;
    private Text txThemaOefn;

    public LesmateriaalOverzicht(BeginSchermFlo parent, AdminController ac, String soortScherm) {
        super(parent, ac, soortScherm);
        this.oc = new OverzichtController();
        this.ac = ac;

        this.parent = parent;

        maakOverzicht();

        cbLeden.setOnAction((ActionEvent event) -> {
            filter();
        });

        cbLesmateriaal.setOnAction((ActionEvent event) -> {
            filter();
        });
        
       raadplegingTabel.getSelectionModel().selectFirst();

    }

    private void maakOverzicht() {
        maakFilters();
        maakTabel();
        maakDetailScherm();

        super.buildGui(63);
    }

    private void maakFilters() {
        cbLeden = new ComboBox();
        cbLeden.setPromptText("Alle leden");
        cbLeden.setItems(oc.geefOverzichtLedenFilter());
        super.addCombobox(cbLeden);

        cbLesmateriaal = new ComboBox();
        cbLesmateriaal.setPromptText("Alle oefeningen");
        cbLesmateriaal.setItems(oc.geefOefeningNamen());
        super.addCombobox(cbLesmateriaal);
    }

    private void maakTabel() {
        raadplegingTabel = new TableView<>();

        raadplegingTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            vulDetailScherm(newSelection);
        });

        maakKolommenInTabel();

        raadplegingTabel.setItems((oc.geefOverzichtRaadplegingen()));
        super.setTvTabel(raadplegingTabel);
    }

    private void maakKolommenInTabel() {
        colVoornaam = new TableColumn("Voornaam");
        colFamilienaam = new TableColumn("Familienaam");
        colNaamLesmateriaal = new TableColumn("Naam oefening");
        colAantalRaadplegingen = new TableColumn("Aantal raadplegingen");

        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().getLid().voornaamProperty());
        colFamilienaam.setCellValueFactory(cellData -> cellData.getValue().getLid().achternaamProperty());
        colNaamLesmateriaal.setCellValueFactory(cellData -> cellData.getValue().oefeningNaamProperty());
        colAantalRaadplegingen.setCellValueFactory(cellData -> cellData.getValue().aantalRaadplegingenProperty());

        super.addKolom(colVoornaam);
        super.addKolom(colFamilienaam);
        super.addKolom(colNaamLesmateriaal);
        super.addKolom(colAantalRaadplegingen);
    }

    private void maakDetailScherm() {
        scherm = new VBox();
        geefInformatieRaadpleging();

        super.setDetailScherm(scherm);

    }

    private void geefInformatieRaadpleging() {
        Text lblTitel = new Text("Titel oefening:");
        Text lblAantalRaadplegingen = new Text("Totaal aantal raadplegingen:");
        Text lblThema = new Text("Thema:");

        opmaakLabels(Arrays.asList(lblTitel, lblAantalRaadplegingen, lblThema));

        txTitel = new Text();
        txTotaalAantalRaadplegingen = new Text();
        txThemaOefn = new Text();

        zetLabelEnInfoNaastElkaar(lblTitel, txTitel);
        zetLabelEnInfoNaastElkaar(lblAantalRaadplegingen, txTotaalAantalRaadplegingen);
        zetLabelEnInfoNaastElkaar(lblThema, txThemaOefn);
        
    }

    private void zetLabelEnInfoNaastElkaar(Text label, Text info) {
        HBox HNaam = new HBox(label, info);
        HNaam.setSpacing(10);
        info.setStyle("-fx-font-size: 16px");

        scherm.getChildren().add(HNaam);
    }

    private void vulDetailScherm(Raadpleging raadpleging) {
        try {
            txTitel.setText(raadpleging.getOefening().getTitel());
            txThemaOefn.setText(raadpleging.getOefening().getThema().naam);
            txTotaalAantalRaadplegingen.setText(Integer.toString(raadpleging.getAantalRaadplegingen()));
        }catch(NullPointerException e){
            //geen waarde geselecteerd in de tabel;
        }

    }

    private void opmaakLabels(List<Text> labels) {
        labels.stream().forEach(l -> l.setStyle("-fx-font-weight: bold; -fx-underline: true; -fx-font-size: 16px"));
    }

    private void filter() {
        String lid = cbLeden.getSelectionModel().getSelectedIndex() == 0
                ? null
                : cbLeden.getSelectionModel().getSelectedItem();
        Oefening oefening = oc.geefOefeningOpTitel(cbLesmateriaal.getSelectionModel().getSelectedItem());
        oc.veranderRaadplegingFilter(lid, oefening);
        raadplegingTabel.getSelectionModel().selectFirst();
    }
}
