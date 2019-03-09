/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import domein.Lid;
import domein.activiteit.Activiteit;
import domein.controllers.AdminController;
import domein.controllers.OverzichtController;
import domein.enums.Formule;
import gui.BeginSchermFlo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author florianlanduyt
 */
public class OverzichtActiviteitenSchermFlo extends BorderPane {

    private AdminController ac;
    private BeginSchermFlo parent;
    private OverzichtController oc;

    private ComboBox<Lid> cbLid;
    private ComboBox<Formule> cbFormule;
    
    private VBox filtersEnTabel;

    private VBox tabel;
    private TableView<Activiteit> tvActiviteiten;
    private TableColumn<Activiteit, String> colVoornaam;
    private TableColumn<Activiteit, String> colDatum;
    private TableColumn<Activiteit, String> colFormule;

    public OverzichtActiviteitenSchermFlo(BeginSchermFlo parent, AdminController ac) {
        this.parent = parent;
        this.ac = ac;
        this.oc = new OverzichtController();

        buildGui();
    }

    private void buildGui() {
        filtersEnTabel = new VBox();
        maakFilters();
        maakTabel();
        
        
        this.setLeft(filtersEnTabel);
    }

    private void maakFilters() {
        //Filters
        VBox filters = new VBox();
        cbLid = new ComboBox<>();
        cbLid.setItems(oc.geefOverzichtLeden());
        cbLid.setMinWidth(300);
        cbLid.setPromptText("-- Alle Leden --");
        
        cbFormule = new ComboBox<>();
        cbFormule.setItems(oc.geefFormules());
        cbFormule.setMinWidth(300);
        cbFormule.setPromptText("-- Alle Formules --");
        
        filters.getChildren().add(cbLid);
        filters.getChildren().add(cbFormule);
        
        filtersEnTabel.getChildren().add(filters);
        
        //this.getChildren().addAll(filters);
    }

    private void maakTabel() {
        //Tabel
        tabel = new VBox();
        
        //-- Kolom naam
        colVoornaam = new TableColumn<>("Naam");
        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().naamProperty());
        colVoornaam.setMinWidth(150);
        
        //-- Kolom Datum
        colDatum = new TableColumn<>("Datum");
        colDatum.setMinWidth(150);
        colDatum.setCellValueFactory(cellData -> cellData.getValue().datumProperty());
        
        //-- Kolom Formule
        colFormule = new TableColumn<>("Formule");
        colFormule.setMinWidth(150);
        colFormule.setCellValueFactory(cellData -> cellData.getValue().formuleProperty());
        
        //-- Tabel klaarmaken
        tvActiviteiten = new TableView<>();
        tvActiviteiten.setItems(oc.geefOverzichtActiviteiten());
        tvActiviteiten.getColumns().addAll(colVoornaam, colDatum, colFormule);
        
        tabel.getChildren().add(tvActiviteiten);
        filtersEnTabel.getChildren().add(tabel);
    }

}
