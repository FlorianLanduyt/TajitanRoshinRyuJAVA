/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.beherenActiviteit;

import domein.Activiteit;
import domein.Lid;
import domein.controllers.ActiviteitBeheerController;
import domein.controllers.AdminController;
import domein.enums.Formule;
import domein.enums.Functie;
import gui.BeginScherm;
import gui.BeginSchermFlo;
import java.awt.Checkbox;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author robdeputter
 */
public class BeherenActiviteitSchermController extends AnchorPane {

    //Overzicht Activiteiten
    @FXML
    private TableView<Activiteit> tblActiviteiten;
    @FXML
    private TableColumn<Activiteit, String> colNaamActiviteit;
    @FXML
    private TableColumn<Activiteit, String> colFormuleActiviteit;
    @FXML
    private TableColumn<Activiteit, String> colDeelnemers;
    @FXML
    private TableColumn<Activiteit, String> colVolzet;
    @FXML
    private TableColumn<Activiteit, String> colStartdatum;
    @FXML
    private TableColumn<Activiteit, String> colEinddatum;

    //filters
    @FXML
    private TextField txtFilterNaam;
    @FXML
    private ComboBox<String> cboFilterType;
    @FXML
    private TextField txtFilterAantalDeelnemers;
    @FXML
    private CheckBox cbFilterVolzet;

    //gedetailleerde lijst
    @FXML
    private TextField txtNaamActiviteit;
    @FXML
    private ComboBox<Formule> cboType;
    @FXML
    private DatePicker dpStartdatum;
    @FXML
    private DatePicker dpEinddatum;
    @FXML
    private TextField txtMaxAantalDeelnemers;
    @FXML
    private CheckBox cbIsVolzet;
    @FXML
    private TextField txtStraat;
    @FXML
    private TextField txtStad;
    @FXML
    private TextField txtHuisnr;
    @FXML
    private TextField txtBus;
    @FXML
    private TextField txtPostcode;
    @FXML
    private Button btnVoegDeelnemerToe;
    @FXML
    private TableView<Lid> tblDeelnemers;
    @FXML
    private TableColumn<Lid, String> colDeelnemerNaam;
    @FXML
    private TableColumn<Button, Button> colVerwijderDeelnemer; // moet nog bijgewerkt worden!
    @FXML
    private Label lblFoutopvang;

    //CRUD
    @FXML
    private Button btnNieuweActiviteit;
    @FXML
    private Button btnWijzigActiviteit;
    @FXML
    private Button btnVerwijderActiviteit;
    @FXML
    private Button btnOpslaanNieuweActiviteit;

    //algemeen
    private ActiviteitBeheerController activiteitBeheerController;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Button btnVerwijderDeelnemer;

    /**
     * Initializes the controller class.
     */
    public BeherenActiviteitSchermController(BeginSchermFlo beginScherm, AdminController adminController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeherenActiviteitScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.activiteitBeheerController = new ActiviteitBeheerController();

        //Activiteitentabel opvullen
        colNaamActiviteit.setCellValueFactory(cellData -> cellData.getValue().naamProperty());
        colFormuleActiviteit.setCellValueFactory(cellData -> cellData.getValue().formuleProperty());
        colDeelnemers.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(
                        String.format("%s / %s", cellData.getValue().aantalDeelnemersProperty().get() == null
                                ? 0
                                : cellData.getValue().aantalDeelnemersProperty().get(),
                                cellData.getValue().maxDeelnemersProperty().get())));

        colStartdatum.setCellValueFactory(cellData -> cellData.getValue().beginDatumProperty());

        colEinddatum.setCellValueFactory(cellData -> cellData.getValue().eindDatumProperty());

        colVolzet.setCellValueFactory(cellData -> cellData.getValue().isVolzetProperty());

        tblActiviteiten.setItems(activiteitBeheerController.geefObservableListActiviteiten());

        //wanneer je een nieuwe rij in de activiteitentabel selecteert verandert de gedetailleerde lijst
        tblActiviteiten.getSelectionModel().selectedItemProperty()
                .addListener((obsValue, oldValue, newValue) -> {
                    updateGedetaileerdeLijst(newValue);
                });
        tblActiviteiten.getSelectionModel().selectFirst();
        //comboboxen opvullen!
        cboFilterType.setItems(activiteitBeheerController.geefFormulesFilter());
        cboType.setItems(activiteitBeheerController.geefFormules());

        btnOpslaanNieuweActiviteit.setVisible(false);

    }

    //
    // opvullen gedetailleerde lijst
    //
    public void updateGedetaileerdeLijst(Activiteit newValue) {
        try {
            clearGedetailleerdeLijst();
            txtNaamActiviteit.setText(newValue.getNaam());
            cboType.getSelectionModel().select(newValue.getFormule());
            dpStartdatum.setValue(newValue.getBeginDatum());
            dpEinddatum.setValue(newValue.getEindDatum() == null ? null : newValue.getEindDatum());
            txtMaxAantalDeelnemers.setText(Integer.toString(newValue.getMaxDeelnemers()));
            cbIsVolzet.setSelected(newValue.isVolzet());
            txtStraat.setText(newValue.getStraat());
            txtHuisnr.setText(newValue.getHuisnummer());
            txtBus.setText(newValue.getBus());
            txtStad.setText(newValue.getStad());
            txtPostcode.setText(newValue.getPostcode());
            //tblDeelnemers.setSelectionModel(newValue); --> UC3
        } catch (NullPointerException e) {
            //als de lijst leeg begint te kome 
        }

    }

    public void clearGedetailleerdeLijst() {
        lblFoutopvang.setText("");
        btnNieuweActiviteit.setDisable(false);
        btnWijzigActiviteit.setDisable(false);
        btnVerwijderActiviteit.setDisable(false);
        btnOpslaanNieuweActiviteit.setVisible(false);
        txtNaamActiviteit.clear();
        cboType.getSelectionModel().clearSelection();
        dpStartdatum.setValue(null);
        dpEinddatum.setValue(null);
        txtMaxAantalDeelnemers.clear();
        cbIsVolzet.setSelected(false);
        txtStraat.clear();
        txtHuisnr.clear();
        txtBus.clear();
        txtStad.clear();
        txtPostcode.clear();
        tblDeelnemers.setSelectionModel(null);
    }

    //
    //CRUD - activiteiten
    //
    @FXML
    private void voegActiviteitToe(ActionEvent event) {
        clearGedetailleerdeLijst();
        btnWijzigActiviteit.setDisable(true);
        btnVerwijderActiviteit.setDisable(true);
        btnOpslaanNieuweActiviteit.setVisible(true);
    }

    @FXML
    private void opslaanNieuweActiviteit(ActionEvent event) {
        try {
            activiteitBeheerController.voegActiviteitToe(txtNaamActiviteit.getText(), cboType.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(txtMaxAantalDeelnemers.getText()),
                    dpStartdatum.getValue(), dpEinddatum.getValue(),
                    txtStraat.getText(), txtStad.getText(), txtPostcode.getText(), txtHuisnr.getText(), txtBus.getText());
            lblFoutopvang.setText("");
            btnWijzigActiviteit.setDisable(false);
            btnVerwijderActiviteit.setDisable(false);
            btnOpslaanNieuweActiviteit.setVisible(false);

        } catch (NumberFormatException ex) {
            lblFoutopvang.setText("U moet een nummer geven bij max. aantal deelnemers!");
            txtMaxAantalDeelnemers.clear();
        } catch (IllegalArgumentException e) {
            lblFoutopvang.setText(e.getMessage());
        }

    }

    @FXML
    private void wijzigActiviteit(ActionEvent event) {
        Activiteit activiteit = tblActiviteiten.getSelectionModel().getSelectedItem();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bevestiging wijziging");
            alert.setHeaderText("Bevestiging");
            alert.setContentText(String.format("Ben je zeker dat je activiteit %s wilt wijzigen?", activiteit.getNaam()));

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                activiteitBeheerController.wijzigActiviteit(activiteit, txtNaamActiviteit.getText(), cboType.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(txtMaxAantalDeelnemers.getText()), dpStartdatum.getValue(), dpEinddatum.getValue(),
                        txtStraat.getText(), txtStad.getText(), txtPostcode.getText(), txtHuisnr.getText(), txtBus.getText());
            }
            lblFoutopvang.setText("");
        } catch (NumberFormatException ex) {
            lblFoutopvang.setText("U moet een nummer geven bij max. aantal deelnemers!");
            txtMaxAantalDeelnemers.clear();
        } catch (IllegalArgumentException e) {
            lblFoutopvang.setText(e.getMessage());
        }

    }

    @FXML
    private void verwijderActiviteit(ActionEvent event) {
        Activiteit activiteit = tblActiviteiten.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bevestiging verwijderen");
        alert.setHeaderText("Bevestiging");
        alert.setContentText(String.format("Ben je zeker dat je activiteit %s wilt verwijderen?", activiteit.getNaam()));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            activiteitBeheerController.verwijderActiviteit(activiteit);
        }

    }

    //
    //CRUD - inschrijvingen
    //
    @FXML
    private void voegDeelnemerToe(ActionEvent event) {
        //UC4 --> inschrijvingen
    }

    @FXML
    private void verwijderDeelnemer(ActionEvent event) {
        //UC4 --> inschrijvingen
    }

    //
    //filters
    //
    @FXML
    private void filterActiviteitenTXT(KeyEvent event) {
        filter();
    }

    @FXML
    private void filterActiviteiten(ActionEvent event) {
        filter();
    }

    public void filter() {
        try {
            String naam = txtFilterNaam.getText();
            Formule formule = cboFilterType
                    .getSelectionModel()
                    .getSelectedIndex() == 0
                            ? null
                            : cboFilterType.getSelectionModel().getSelectedItem() == null
                            ? null
                            : Formule.valueOf(cboFilterType.getSelectionModel().getSelectedItem());

            int aantalDeelnemers = (txtFilterAantalDeelnemers.getText() == null || txtFilterAantalDeelnemers.getText().equals("")) ? Integer.MAX_VALUE : Integer.parseInt(txtFilterAantalDeelnemers.getText());
            Boolean volzet = cbFilterVolzet.isSelected();
            activiteitBeheerController.filterList(naam, formule, aantalDeelnemers, volzet);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fout!");
            alert.setHeaderText("Foute input!");
            alert.setContentText("U moet een numerieke waarde ingeven voor filter '# deelnemers'!");
            alert.showAndWait();
            txtFilterAantalDeelnemers.clear();
        }
        tblActiviteiten.getSelectionModel().selectFirst();

    }

}
