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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
    private HBox crudKnoppenBox;

    private TableView<T> tvTabel;
    private List<TableColumn<T, String>> kolommen;
    private List<ComboBox> comboboxen;
    private List<HBox> datepickers;
    private List<TextField> textfields;
    private List<TextField> textFilters;
    private List<Button> crudKnoppen;
    private List<Button> knoppenOnderTabel;
    private Label lblError;

    private final String titelOverzicht;
    private VBox paddingBox;

    public Overzicht(BeginSchermFlo parent, AdminController ac, String overzicht) {
        this.parent = parent;
        this.ac = ac;
        this.oc = new OverzichtController();

        comboboxen = new ArrayList<>();
        kolommen = new ArrayList<>();
        datepickers = new ArrayList<>();
        textfields = new ArrayList<>();
        crudKnoppen = new ArrayList<>();
        knoppenOnderTabel = new ArrayList<>();
        lblError = new Label();

        titelOverzicht = overzicht;
    }

    public void buildGui(int hoogteDetailScherm) {
        this.setPadding(new Insets(5, 30, 30, 10));
        filtersEnTabel = new VBox(20);

        parent.maakOverzichtTitle(titelOverzicht);
        maakFilters();
        maakTabel();
        maakDetailScherm(hoogteDetailScherm);
        maakCrudKnoppen();
        maakErrorLabel();
        this.setLeft(filtersEnTabel);
    }

    private void maakFilters() {
        HBox datePickerBox = new HBox(10);
        datepickers.stream().forEach(d -> {
            d.setPadding(new Insets(0, 15, 0, 0));
            datePickerBox.getChildren().add(d);
        });

        //datePickerBox.setPadding(new Insets(-10,0,0,0));
        HBox cbFilterBox = new HBox(10);
        comboboxen.stream().forEach(f -> {
            f.setMinWidth(150);
            f.setMaxWidth(150);
            cbFilterBox.getChildren().add(f);
            f.getSelectionModel().select(0);
            f.getStyleClass().add("greenBtn");
        });

        HBox txtFilterBox = new HBox(10);
        textfields.stream().forEach(f -> {
            f.setMinWidth(150);
            f.setMaxWidth(150);
            f.setStyle("-fx-background-color:white");
            txtFilterBox.getChildren().add(f);
        });

        // txtFilterBox.setPadding(new Insets(,0,0,0));
        HBox alleFilters = new HBox(10);

        if (!comboboxen.isEmpty()) {
            alleFilters.getChildren().add(cbFilterBox);
        }
        if (!textfields.isEmpty()) {
            alleFilters.getChildren().add(txtFilterBox);
        }
        if (!datepickers.isEmpty()) {
            alleFilters.getChildren().add(datePickerBox);
        }

        filtersEnTabel.getChildren().add(alleFilters);
    }

    private void maakTabel() {
        VBox tabel = new VBox();
        DoubleBinding breedteScherm = this.widthProperty().multiply(0.60);
        DoubleBinding breedteKolom = breedteScherm.divide(kolommen.size());

        tabel.prefWidthProperty().bind(breedteScherm);
        kolommen.stream().forEach(k -> {
            k.setResizable(false);
            k.setSortable(true);
            k.prefWidthProperty().bind(breedteKolom);
            k.getStyleClass().add("titelLinks");
            k.getStyleClass().add("name-column");

            tvTabel.getColumns().add(k);
        });
        opmaakTabel();

        tabel.getChildren().add(tvTabel);
        filtersEnTabel.getChildren().add(tabel);
        HBox box = new HBox();
        box.setPadding(new Insets(-10, 0, 0, 0));
        if (!knoppenOnderTabel.isEmpty()) {
            tvTabel.setMinHeight(800);
            tvTabel.setMaxHeight(800);

            Region r = new Region();
            HBox.setHgrow(r, Priority.ALWAYS);
            box.getChildren().add(r);
            knoppenOnderTabel.stream().forEach(k -> {
                k.setMinWidth(150);
                //k.setStyle("-fx-background-color: #F3CAAA; ");
                box.getChildren().add(k);

            });
        }

        filtersEnTabel.getChildren().add(box);
    }

    private void opmaakTabel() {
        tvTabel.setPrefHeight(1000);
        tvTabel.setScaleShape(false);
        tvTabel.setId("table");
        tvTabel.getSelectionModel().clearSelection();

        tvTabel.getStyleClass().add("table-row-cell");
    }

    private void maakDetailScherm(int hoogteDetailScherm) {
//        String cssLayout = "-fx-border-color: grey;\n"
//                + //#dee8e2
//                "-fx-border-insets: 5;\n"
//                + "-fx-border-width: 1;\n"
//                + "-fx-border-radius: 15px;\n"
//                + "-fx-border-style: solid;\n";

        String cssLayout = "-fx-background-color: #F3CAAA; -fx-background-radius:15px;";
        Pane bovenPadding = new Pane();
        bovenPadding.setMinHeight(hoogteDetailScherm);

        detailScherm.setSpacing(10);
        detailScherm.setPadding(new Insets(8, 20, 20, 20));
        detailScherm.setStyle(cssLayout);

        Pane zijPadding = new Pane();
        //zijPadding.setMinWidth(50);

        HBox zijPaddingBox = new HBox(detailScherm, zijPadding);

        paddingBox = new VBox(bovenPadding, zijPaddingBox);
        paddingBox.setPadding(new Insets(0, 0, 0, 30));

        tvTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.setCenter(paddingBox);
            }
        });
    }

    public void maakCrudKnoppen() {
        crudKnoppenBox = new HBox();
        if (!this.crudKnoppen.isEmpty()) {
            this.crudKnoppen.stream().forEach(b -> {
                b.setMinWidth(150);
            });

            Region r1 = new Region();
            HBox.setHgrow(r1, Priority.ALWAYS);

            crudKnoppenBox.setPadding(new Insets(10, 0, 15, 0));
            crudKnoppenBox.getChildren().addAll(this.crudKnoppen.get(0), r1, this.crudKnoppen.get(1));
            paddingBox.getChildren().add(crudKnoppenBox);
        }
        
        paddingBox.getChildren().remove(lblError);
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

    public void addTextField(TextField txt) {
        textfields.add(txt);
    }

    public void addCrudKnop(Button b) {
        this.crudKnoppen.add(b);
    }

    public void addExtraKnop(Button b) {
        knoppenOnderTabel.add(b);
    }

    public void emptyCrudKnoppenList() {
        paddingBox.getChildren().remove(crudKnoppenBox);
        this.crudKnoppen = new ArrayList<>();

    }

    public void disableFilters(boolean b) {
        this.filtersEnTabel.setDisable(b);
    }

    private void maakErrorLabel() {
        lblError.setTextFill(Color.web("#B14643"));
        lblError.setStyle("-fx-font-size: 20px");
        this.paddingBox.getChildren().add(lblError);
    }
    
    public void setErrorLabelText(String text){
        lblError.setText(text);
    }
    
    public void resetLabel(){
        this.paddingBox.getChildren().remove(lblError);
        lblError = new Label();
        maakErrorLabel();
    }

}
