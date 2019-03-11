/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import domein.activiteit.Activiteit;
import domein.controllers.AdminController;
import domein.controllers.OverzichtController;
import gui.BeginSchermFlo;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

/**
 *
 * @author florianlanduyt
 */
public class ActiviteitenOverzicht extends Overzicht{ 
    
    private OverzichtController oc;
    
    private TableColumn<Activiteit, String> colVoornaam;
    private TableColumn<Activiteit, String> colDatum;
    private TableColumn<Activiteit, String> colFormule;
    
    private ComboBox cbFormule;
    
    public ActiviteitenOverzicht(BeginSchermFlo parent, AdminController ac){
        super(parent, ac);
        this.oc = new OverzichtController();
        
        maakFilters();
        tabelMaken();
        detailSchermMaken();
        
        super.buildGui();
    }

    private void maakFilters() {
        cbFormule = new ComboBox<>();
        cbFormule.setItems(oc.geefFormules());
        cbFormule.setPromptText("-- Alle Formules --");
        
        super.addCombobox(cbFormule);
    }

    private void tabelMaken() {
        TableView<Activiteit> activiteitTabel = new TableView<>();
        
        colVoornaam = new TableColumn("Naam");
        colDatum = new TableColumn("Datum");
        colFormule = new TableColumn("Formule");
        
        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().naamProperty());
        colDatum.setCellValueFactory(cellData -> cellData.getValue().datumProperty());
        colFormule.setCellValueFactory(cellData -> cellData.getValue().formuleProperty());
        
        super.addKolom(colVoornaam);
        super.addKolom(colDatum);
        super.addKolom(colFormule);
        
        activiteitTabel.setItems((oc.geefOverzichtActiviteiten()));
        super.setTvTabel(activiteitTabel);
    }
    
    private void detailSchermMaken(){
        VBox scherm = new VBox();
        
        
        
    }
    
}
