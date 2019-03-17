/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import domein.Aanwezigheid;
import domein.Activiteit;
import domein.Lid;
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
public class Clubkampioenschap extends Overzicht{
   
    private OverzichtController oc;
    private AdminController ac;
    private BeginSchermFlo parent;
    
    private TableView<Lid> clubkampioenschapTabel;
    private TableColumn<Lid, String> colVoornaam;
    private TableColumn<Lid, String> colFamilienaam;
    private TableColumn<Lid, String> colPuntenaantal;
    
    private TableView<Aanwezigheid> aanwezighedenTabel;
    private TableColumn<Aanwezigheid, String> colNaamActiviteit;
    private TableColumn<Aanwezigheid, String> colPuntenActiviteit;
    
    private VBox scherm;

    private Text txNaam;


    public Clubkampioenschap(BeginSchermFlo parent, AdminController ac, String soortScherm) {
        super(parent, ac, soortScherm);
        this.oc = new OverzichtController();
        this.ac = ac;
        
        this.parent = parent;

        maakOverzicht();
        clubkampioenschapTabel.getSelectionModel().selectFirst();
        
    }

    private void maakOverzicht() {
        maakTabel();
        maakDetailScherm();
        
        super.buildGui(38);
    }

    

    private void maakTabel() {
        clubkampioenschapTabel = new TableView<>();

        clubkampioenschapTabel.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
            vulDetailScherm(newSelection);
        });

        maakKolommenInTabel();

        clubkampioenschapTabel.setItems((oc.geefOverzichtClubkampioenschap()));
        super.setTvTabel(clubkampioenschapTabel);
        
        
    }

    private void maakKolommenInTabel() {
        colVoornaam = new TableColumn("Voornaam");
        colFamilienaam = new TableColumn("Familienaam");
        colPuntenaantal = new TableColumn("Puntenaantal");
        
        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().voornaamProperty());
        colFamilienaam.setCellValueFactory(cellData -> cellData.getValue().achternaamProperty());
        colPuntenaantal.setCellValueFactory(cellData -> cellData.getValue().puntenAantalProperty());
        
        super.addKolom(colFamilienaam);
        super.addKolom(colVoornaam);
        super.addKolom(colPuntenaantal);
    }

    private void maakDetailScherm() {
        scherm = new VBox();
        geefInformatieAanwezigheid();

        aanwezighedenTabel = new TableView();
        colNaamActiviteit= new TableColumn("Naam activiteit");
        colPuntenActiviteit = new TableColumn("Aantal punten");
        colNaamActiviteit.setCellValueFactory(cellData -> cellData.getValue().activiteitNaamProperty());
        colPuntenActiviteit.setCellValueFactory(cellData -> cellData.getValue().puntenAantalProperty());
        
        
        aanwezighedenTabel.getColumns().add(colNaamActiviteit);
        aanwezighedenTabel.getColumns().add(colPuntenActiviteit);
        

        VBox raadplegingBox = opmaakAanwezigheidsTabel(aanwezighedenTabel);

        scherm.getChildren().add(raadplegingBox);
        super.setDetailScherm(scherm);

    }
    
    private void geefInformatieAanwezigheid(){
        Text lblAanwezigheden = new Text("Aanwezigheden:");
        Text lblNaam = new Text("Naam lid:");
        

        opmaakLabels(Arrays.asList(lblAanwezigheden, lblNaam));


        txNaam = new Text();

        zetLabelEnInfoNaastElkaar(lblNaam, txNaam);
        
        scherm.getChildren().add(lblAanwezigheden);
    }
    
    private void zetLabelEnInfoNaastElkaar(Text label, Text info) {
        HBox HNaam = new HBox(label, info);
        HNaam.setSpacing(10);
        info.setStyle("-fx-font-size: 16px");
        
        scherm.getChildren().add(HNaam);
    }

    

    private void vulDetailScherm(Lid lid) {
        try{
           txNaam.setText(lid.geefVolledigeNaam());
        aanwezighedenTabel.setItems(oc
                .geefAanwezighedenVoorLid(clubkampioenschapTabel
                        .getSelectionModel()
                        .getSelectedItem())); 
        }catch(NullPointerException e){
            //geen waarde geselecteerd in tabel
        }
        
    }
    
    private <T> VBox opmaakAanwezigheidsTabel(TableView<T> tabel) {
        VBox tabelBox = new VBox();
        tabelBox.setMaxSize(250, 200);
        tabel.getColumns().stream().forEach(k -> k.setMinWidth(124));
        tabelBox.getChildren().add(tabel);

        return tabelBox;
    }

    private void opmaakLabels(List<Text> labels) {
        labels.stream().forEach(l -> l.setStyle("-fx-font-weight: bold; -fx-underline: true; -fx-font-size: 16px"));
    }

//    private void veranderTable(Object value) {
//        activiteitTabel.getColumns().stream().filter(p-> p.)
//    }
}

