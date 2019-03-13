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
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    private List<ComboBox> comboboxen;
    private List<DatePicker> datepickers;

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

    public void buildGui() {
        this.setPadding(new Insets(5, 30, 30, 10));
        filtersEnTabel = new VBox(20);

        parent.maakOverzichtTitle(titelOverzicht);
        maakFilters();
        maakTabel();
        maakDetailScherm();
        this.setLeft(filtersEnTabel);
    }

    private void maakFilters() {
        VBox filterBox = new VBox(5);
        comboboxen.stream().forEach(f -> {
            f.setMinWidth(300);
            filterBox.getChildren().add(f);
            f.getSelectionModel().select(0);
        });

        filtersEnTabel.getChildren().add(filterBox);
    }

    private void maakTabel() {
        VBox tabel = new VBox();
        DoubleBinding breedteScherm = this.widthProperty().multiply(0.60);
        DoubleBinding breedteKolom = breedteScherm.divide(kolommen.size());

        tabel.prefWidthProperty().bind(breedteScherm);
        kolommen.stream().forEach(k -> {
            k.prefWidthProperty().bind(breedteKolom);
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

    private void maakDetailScherm() {
        detailScherm.setSpacing(10);
        detailScherm.setPadding(new Insets(45, 10, 0, 30));
        //detailScherm.setAlignment(Pos.CENTER_LEFT);

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

    public void addDatePicker(DatePicker picker) {
        datepickers.add(picker);
    }
}
