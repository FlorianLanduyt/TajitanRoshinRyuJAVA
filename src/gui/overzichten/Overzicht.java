/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import domein.controllers.AdminController;
import domein.controllers.OverzichtController;
import gui.BeginSchermFlo;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private List<ComboBox> comboboxen;
    private List<HBox> datepickers;

    private final String titelOverzicht;

    public Overzicht(BeginSchermFlo parent, AdminController ac, String overzicht) {
        this.parent = parent;
        this.ac = ac;
        this.oc = new OverzichtController();

        comboboxen = new ArrayList<>();
        kolommen = new ArrayList<>();
        datepickers = new ArrayList<>();

        titelOverzicht = overzicht;
    }

    public void buildGui(int hoogteDetailScherm) {
        this.setPadding(new Insets(5, 30, 30, 10));
        filtersEnTabel = new VBox(20);

        parent.maakOverzichtTitle(titelOverzicht);
        maakFilters();
        maakTabel();
        maakDetailScherm(hoogteDetailScherm);
        this.setLeft(filtersEnTabel);
    }

    private void maakFilters() {
        HBox datePickerBox = new HBox(5);
        datepickers.stream().forEach(d-> {
            d.setPadding(new Insets(0,15,0,0));
            datePickerBox.getChildren().add(d);
        });
        
        datePickerBox.setPadding(new Insets(-10,0,0,0));
        
        HBox filterBox = new HBox(10);
        comboboxen.stream().forEach(f -> {
            f.setMinWidth(150);
            filterBox.getChildren().add(f);
            f.getSelectionModel().select(0);
        });
        
        filtersEnTabel.getChildren().add(filterBox);
        filtersEnTabel.getChildren().add(datePickerBox);
        
    }

    private void maakTabel() {
        VBox tabel = new VBox();
        DoubleBinding breedteScherm = this.widthProperty().multiply(0.60);
        DoubleBinding breedteKolom = breedteScherm.divide(kolommen.size());

        tabel.prefWidthProperty().bind(breedteScherm);
        kolommen.stream().forEach(k -> {
            k.prefWidthProperty().bind(breedteKolom);
            k.getStyleClass().add("titelLinks");
            tvTabel.getColumns().add(k);
        });
        opmaakTabel();
        
        

        tabel.getChildren().add(tvTabel);
        filtersEnTabel.getChildren().add(tabel);
    }

    private void opmaakTabel() {
        tvTabel.setPrefHeight(1000);
        tvTabel.setScaleShape(false);
        tvTabel.setId("table");
        tvTabel.getSelectionModel().clearSelection();
    }

    private void maakDetailScherm(int hoogteDetailScherm) {
        detailScherm.setSpacing(10);
        detailScherm.setPadding(new Insets(hoogteDetailScherm, 10, 0, 30));
        //detailScherm.setAlignment(Pos.CENTER_LEFT);
        //detailScherm.getStyleClass().add("bgr");

        tvTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.setCenter(detailScherm);
            }
        });
    }

    public void addCombobox(ComboBox cb) {
        comboboxen.add(cb);
    }

    public void addKolom(TableColumn<T, String> kol) {
        kolommen.add(kol);
    }

    public void setTvTabel(TableView<T> tvTabel) { 
        this.tvTabel = tvTabel;
    }

    public void setDetailScherm(VBox detailScherm) {
        this.detailScherm = detailScherm;
    }

    public void addDatePicker(HBox picker) {
        datepickers.add(picker);
    }
}
