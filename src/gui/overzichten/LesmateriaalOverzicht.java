/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import domein.Oefening;
import domein.Raadpleging;
import domein.controllers.AdminController;
import domein.controllers.OverzichtController;
import gui.BeginSchermFlo;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    private TableColumn<Raadpleging, String> colGraadLesmateriaal;
    private TableColumn<Raadpleging, String> colAantalRaadplegingen;

    //filters
    private TextField txtLedenVoornaam;
    private TextField txtLedenFamilienaam;
    private ComboBox<String> cbLesmateriaal;
    private Label lblVan;
    private DatePicker dpVan;
    private Label lblTot;
    private DatePicker dpTot;
    
    
    private VBox scherm;

    private Text txLid;
    private Text txTitel;
    private Text txTotaalAantalRaadplegingen;
    private Text txThemaOefn;
    private Text txDatumLaatsteRaadpleging;

    public LesmateriaalOverzicht(BeginSchermFlo parent, AdminController ac, String soortScherm) {
        super(parent, ac, soortScherm);
        this.oc = new OverzichtController();
        this.ac = ac;

        this.parent = parent;

        maakOverzicht();

        txtLedenVoornaam.setOnKeyReleased((KeyEvent e) -> {
            filter();
        });
        
        txtLedenFamilienaam.setOnKeyReleased((KeyEvent e) -> {
            filter();
        });

        cbLesmateriaal.setOnAction((ActionEvent event) -> {
            filter();
        });
        
        dpVan.setOnAction((ActionEvent event) -> {
            filter();
        });
        
        dpTot.setOnAction((ActionEvent event) -> {
            filter();
        });
 
       raadplegingTabel.getSelectionModel().selectFirst();

    }

    private void maakOverzicht() {
        maakFilters();
        maakTabel();
        maakDetailScherm();

        super.buildGui(44);
    }

    private void maakFilters() {
        txtLedenFamilienaam = new TextField();
        txtLedenFamilienaam.setPromptText("Filter op familienaam");
        super.addTextField(txtLedenFamilienaam);
        
        txtLedenVoornaam = new TextField();
        txtLedenVoornaam.setPromptText("Filter op voornaam");
        super.addTextField(txtLedenVoornaam);
        
        lblVan = new Label("Van: ");
        lblTot = new Label("Tot: ");
        dpVan = new DatePicker();
        dpVan.setMaxWidth(150);
        dpVan.setMinWidth(150);
        dpTot = new DatePicker();
        dpTot.setMaxWidth(150);
        dpTot.setMinWidth(150);
        HBox HVan = new HBox(lblVan, dpVan);
        HVan.setAlignment(Pos.CENTER);
        HBox HTot = new HBox(lblTot, dpTot);
        HTot.setAlignment(Pos.CENTER);
        super.addDatePicker(HVan);
        super.addDatePicker(HTot);

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
        
        Label tabelPlaceholder = new Label("Geen raadplegingen beschikbaar");
        tabelPlaceholder.getStyleClass().add("placeholder");
        raadplegingTabel.setPlaceholder(tabelPlaceholder);
        
        maakKolommenInTabel();

        raadplegingTabel.setItems((oc.geefOverzichtRaadplegingen()));
        super.setTvTabel(raadplegingTabel);
    }

    private void maakKolommenInTabel() {
        colVoornaam = new TableColumn("Voornaam");
        colFamilienaam = new TableColumn("Familienaam");
        colNaamLesmateriaal = new TableColumn("Naam oefening");
        colAantalRaadplegingen = new TableColumn("Aantal raadplegingen");
        colGraadLesmateriaal = new TableColumn("Graad oefening");

        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().getLid().voornaamProperty());
        colFamilienaam.setCellValueFactory(cellData -> cellData.getValue().getLid().achternaamProperty());
        colNaamLesmateriaal.setCellValueFactory(cellData -> cellData.getValue().oefeningNaamProperty());
        colGraadLesmateriaal.setCellValueFactory(cellData -> cellData.getValue().getOefening().graadProperty());
        colAantalRaadplegingen.setCellValueFactory(cellData -> cellData.getValue().aantalRaadplegingenProperty());

        super.addKolom(colFamilienaam);
        super.addKolom(colVoornaam);
        super.addKolom(colNaamLesmateriaal);
        super.addKolom(colGraadLesmateriaal);
        super.addKolom(colAantalRaadplegingen);
    }

    private void maakDetailScherm() {
        scherm = new VBox();
        geefInformatieRaadpleging();

        super.setDetailScherm(scherm);

    }

    private void geefInformatieRaadpleging() {
        Text lblLid = new Text("Lid:");
        Text lblTitel = new Text("Titel oefening:");
        Text lblAantalRaadplegingen = new Text("Totaal aantal raadplegingen:");
        Text lblThema = new Text("Thema:");
        Text lblDatumLaatsteRaadpleging = new Text("Datum laatste raadpleging:");

        opmaakLabels(Arrays.asList(lblTitel, lblAantalRaadplegingen, lblThema, lblLid, lblDatumLaatsteRaadpleging));

        txLid = new Text();
        txTitel = new Text();
        txTotaalAantalRaadplegingen = new Text();
        txThemaOefn = new Text();
        txDatumLaatsteRaadpleging = new Text();

        zetLabelEnInfoNaastElkaar(lblLid, txLid);
        zetLabelEnInfoNaastElkaar(lblTitel, txTitel);
        zetLabelEnInfoNaastElkaar(lblAantalRaadplegingen, txTotaalAantalRaadplegingen);
        zetLabelEnInfoNaastElkaar(lblThema, txThemaOefn);
        zetLabelEnInfoNaastElkaar(lblDatumLaatsteRaadpleging, txDatumLaatsteRaadpleging);
        
    }

    private void zetLabelEnInfoNaastElkaar(Text label, Text info) {
        HBox HNaam = new HBox(label, info);
        HNaam.setSpacing(10);
        info.setStyle("-fx-font-size: 16px");

        scherm.getChildren().add(HNaam);
    }
    
    private void clearAlleVelden(){
        txLid.setText("");
        txTitel.setText("");
        txThemaOefn.setText("");
        txTotaalAantalRaadplegingen.setText("");
        txDatumLaatsteRaadpleging.setText("");
    }

    private void vulDetailScherm(Raadpleging raadpleging) {
        clearAlleVelden();
        try {
            txLid.setText(raadpleging.getLid().geefVolledigeNaam());
            txTitel.setText(raadpleging.getOefening().getTitel());
            txThemaOefn.setText(raadpleging.getOefening().getThema().getNaam());
            txTotaalAantalRaadplegingen.setText(Integer.toString(raadpleging.getAantalRaadplegingen()));
            txDatumLaatsteRaadpleging.setText(raadpleging.getTijdstippen().get(raadpleging.getTijdstippen().size() - 1).toString());
        }catch(NullPointerException e){
            //geen waarde geselecteerd in de tabel;
        }

    }

    private void opmaakLabels(List<Text> labels) {
        labels.stream().forEach(l -> l.setStyle("-fx-font-weight: bold; -fx-font-size: 16px"));
    }

    private void filter() {
        String lidVoornaam = txtLedenVoornaam.getText();
        String lidFamilienaam = txtLedenFamilienaam.getText();
        Oefening oefening = oc.geefOefeningOpTitel(cbLesmateriaal.getSelectionModel().getSelectedItem());
        LocalDate van = dpVan.getValue();
        LocalDate tot = dpTot.getValue();
        oc.veranderRaadplegingFilter(lidVoornaam, lidFamilienaam, oefening, van, tot);
        raadplegingTabel.getSelectionModel().selectFirst();
    }
}
