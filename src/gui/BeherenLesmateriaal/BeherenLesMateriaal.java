/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.BeherenLesmateriaal;

import domein.Oefening;
import domein.Thema;
import domein.controllers.AdminController;
import domein.controllers.LesmateriaalBeheerController;
import domein.enums.Graad;
import gui.BeginSchermFlo;
import gui.overzichten.Overzicht;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
public class BeherenLesMateriaal extends Overzicht {

    private BeginSchermFlo parent;
    private AdminController ac;
    private LesmateriaalBeheerController lesmateriaalBeheerController;

    //filters
    private TextField txtTitel;
    private ComboBox<String> cbThema;
    private ComboBox<String> cbGraad;

    //tabel
    private TableView<Oefening> tblOefeningen;
    private TableColumn<Oefening, String> colTitel;
    private TableColumn<Oefening, String> colThema;
    private TableColumn<Oefening, String> colGraad;
    private TableColumn<Oefening, String> colAantalRaadplegingen;
    
    //detailscherm
    private VBox detailScherm;

    public BeherenLesMateriaal(BeginSchermFlo parent, AdminController ac, String naamVenster) {
        super(parent, ac, naamVenster);
        this.parent = parent;
        this.ac = ac;
        lesmateriaalBeheerController = new LesmateriaalBeheerController();

        maakOverzicht();
        txtTitel.setOnKeyReleased((KeyEvent event) -> {
            filter();
        });

        cbThema.setOnAction((ActionEvent event) -> {
            filter();
        });

        cbGraad.setOnAction((ActionEvent event) -> {
            filter();
        });
        
        

    }

    private void maakOverzicht() {
        maakFilters();
        maakTabel();
        maakDetailScherm();

        super.buildGui(82);
    }

    private void maakFilters() {
        txtTitel = new TextField();
        txtTitel.setPromptText("Filter op titel");
        super.addTextField(txtTitel);

        cbThema = new ComboBox<>();
        cbThema.setItems(lesmateriaalBeheerController.geefThemasFilter());
        super.addCombobox(cbThema);

        cbGraad = new ComboBox<>();
        cbGraad.setItems(lesmateriaalBeheerController.geefGradenFilter());
        super.addCombobox(cbGraad);

        

    }

    private void maakTabel() {
        tblOefeningen = new TableView<>();

        tblOefeningen.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            vulDetailScherm(newSelection);
        });

        maakKolommenInTabel();

        tblOefeningen.setItems((lesmateriaalBeheerController.geefObservableListOefeningen()));
        super.setTvTabel(tblOefeningen);
    }

    private void maakDetailScherm() {
        detailScherm = new VBox();
        geefInformatieOefening();
        detailScherm.setPadding(new Insets(80,0,0,0));

        super.setDetailScherm(detailScherm);
    }

    private void maakKolommenInTabel() {
        colTitel = new TableColumn<>("Titel");
        colThema = new TableColumn<>("Thema");
        colGraad = new TableColumn<>("Graad");
        colAantalRaadplegingen = new TableColumn<>("Aantal raadplegingen");

        colTitel.setCellValueFactory(cellData -> cellData.getValue().naamProperty());
        colThema.setCellValueFactory(cellData -> cellData.getValue().themaProperty());
        colGraad.setCellValueFactory(cellData -> cellData.getValue().graadProperty());
        colAantalRaadplegingen.setCellValueFactory(cellData -> cellData.getValue().aantalRaadplegingenProperty());

        super.addKolom(colTitel);
        super.addKolom(colThema);
        super.addKolom(colGraad);
        super.addKolom(colAantalRaadplegingen);

    }

    private void geefInformatieOefening() {
        Text lblLid = new Text("Lid:");
        Text lblDatum2 = new Text("Datum:");
        Text lblPuntenAantal = new Text("Puntenaantal:");
        Text lblFormule = new Text("Formule:");

    }

    private void vulDetailScherm(Oefening a) {
        try {

        } catch (NullPointerException e) {
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
        
            Graad graad = cbGraad
                    .getSelectionModel()
                    .getSelectedIndex() == 0
                            ? null
                            : cbGraad.getSelectionModel().getSelectedItem() == null
                            ? null
                            : Graad.valueOf(cbGraad.getSelectionModel().getSelectedItem());

            Thema thema = cbThema
                    .getSelectionModel()
                    .getSelectedIndex() == 0
                            ? null
                            : cbThema.getSelectionModel().getSelectedItem() == null
                            ? null
                            : lesmateriaalBeheerController.geefThemas().stream().filter(themaa -> themaa.getNaam().equals(cbThema.getSelectionModel().getSelectedItem())).findAny().orElse(null);

            String titel = txtTitel.getText();
           

            lesmateriaalBeheerController.filterList(graad, thema, titel);
            tblOefeningen.getSelectionModel().selectFirst();
        

    }

}
