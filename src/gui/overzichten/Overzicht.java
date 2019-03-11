/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import domein.Lid;
import domein.controllers.AdminController;
import domein.controllers.OverzichtController;
import gui.BeginSchermFlo;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author florianlanduyt
 */
public class Overzicht<T> extends BorderPane {

    private AdminController ac;
    private BeginSchermFlo parent;
    private OverzichtController oc;

    private VBox filtersEnTabel;
    private VBox detailScherm;

    private TableView<T> tvTabel;
    private List<TableColumn<T, String>> kolommen;
    private List<ComboBox> filters;
    private List<Node> detailsNodes;

    public Overzicht(BeginSchermFlo parent, AdminController ac) {
        this.parent = parent;
        this.ac = ac;
        this.oc = new OverzichtController();
        
        filters = new ArrayList<>();
        kolommen = new ArrayList<>();
        detailsNodes = new ArrayList<>();
    }

    public void buildGui() {
        this.setPadding(new Insets(5, 30, 30, 10));
        filtersEnTabel = new VBox(20);
        
        maakFilters();
        maakTabel();
        maakDetailScherm();
        this.setLeft(filtersEnTabel);
        this.setRight(this);

    }

    private void maakFilters() {
        VBox filterBox = new VBox(5);
        filters.stream().forEach(f -> f.setMinWidth(300));
        filters.stream().forEach(f -> filterBox.getChildren().add(f));
        filtersEnTabel.getChildren().add(filterBox);
    }

    private void maakTabel() {
        VBox tabel = new VBox();
        DoubleBinding breedteScherm = this.widthProperty().multiply(0.40);
        DoubleBinding breedteKolom = breedteScherm.divide(kolommen.size());
        
        tabel.prefWidthProperty().bind(breedteScherm);
        kolommen.stream().forEach(k -> k.prefWidthProperty().bind(breedteKolom));
        kolommen.stream().forEach(k -> tvTabel.getColumns().add(k));
        
        tvTabel.setPrefHeight(1000);
        tvTabel.setScaleShape(false);
        tvTabel.setId("table");

        tabel.getChildren().add(tvTabel);
        filtersEnTabel.getChildren().add(tabel);
    }
    
    private void maakDetailScherm(){
        VBox detailsScherm = new VBox();
    }

    public void addCombobox(ComboBox cb) {
        filters.add(cb);
    }

    public void addKolom(TableColumn<T, String> kol) {
        kolommen.add(kol);
    }

    public void setTvTabel(TableView<T> tvTabel) {
        this.tvTabel = tvTabel;
    }
    
    public void setDetailScherm(VBox detailScherm){
        this.detailScherm = detailScherm;
    }

    
}
