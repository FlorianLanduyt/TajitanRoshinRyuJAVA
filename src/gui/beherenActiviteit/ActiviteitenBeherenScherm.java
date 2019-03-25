/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.beherenActiviteit;

import domein.Activiteit;
import domein.Inschrijving;
import domein.Lid;
import domein.controllers.ActiviteitBeheerController;
import domein.controllers.AdminController;
import domein.enums.Formule;
import gui.BeginSchermFlo;
import gui.overzichten.Overzicht;
import java.awt.event.MouseEvent;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author florianlanduyt
 */
public class ActiviteitenBeherenScherm extends Overzicht {

    private ActiviteitBeheerController abc;
    
    //filters
    private TextField txtFilterNaam, txtFilterAantalDeelnemers;
    private ComboBox<String> cboFilterType;
    private CheckBox cbFilterVolzet;
    
    //tabel
    private TableView<Activiteit> tvActiviteiten;
    private TableColumn<Activiteit, String> colNaamActiviteit,colFormuleActiviteit,colDeelnemers,colVolzet,colStartdatum,colEinddatum;
    
    //detailpanel
    private TextField txtNaamActiviteit,txtNaamLocatie,txtGsmnummerLocatie, txtEmailLocatie, txtStraat, txtStad;
    private TextField txtHuisnr, txtBus, txtPostcode, txtMaxAantalDeelnemers;
    private ComboBox<Formule> cboType;
    private DatePicker dpStartdatum, dpEinddatum, dpInschrijvingsDatum;
    private CheckBox cbIsVolzet;
    private Button btnVoegDeelnemerToe;
    
    private TableView<Inschrijving> tvDeelnemers;
    private TableColumn<Inschrijving, String> colDeelnemerVoornaam, colDeelnemerFamilienaam;
    
    private Label lblNaamActiviteit, lblNaamLocatie, lblGsmnummerLocatie,lblEmailLocatie; 
    private Label lblAdresLocatie,lblMaxAantalDeelnemers,lblCboType,lbldatum, lblInschrijvingsDatum;
    private Label lblDeelnemers;
    
    private Label lblFoutopvang;
    private Button btnActiviteitVerwijderen;
    private Button btnNieuwActiviteit;
    private Button btnWijzigActiviteit;
    private Button btnCancel;
    private Button btnSlaGegevensNieuweActiviteitOp;
    
    private InschrijvingToevoegenScherm inschrijvingToevoegenScherm;
    

    public ActiviteitenBeherenScherm(BeginSchermFlo parent, AdminController ac, String overzicht) {
        super(parent, ac, overzicht);

        abc = new ActiviteitBeheerController();

        maakOverzicht();
        
        cbFilterVolzet.setOnAction((ActionEvent event) -> {
            filter();
        });
        
        cboFilterType.setOnKeyReleased((KeyEvent event) -> {
            filter();
        });
        
        txtFilterNaam.setOnAction((ActionEvent event) -> {
            filter();
        });
        
        txtFilterAantalDeelnemers.setOnAction((ActionEvent event) -> {
            filter();
        });
        
        btnCancel.setOnAction((ActionEvent action) -> {
            cancelToevoegenNieuweActiviteit();
        });
        
        btnActiviteitVerwijderen.setOnAction((ActionEvent event) -> {
            verwijderActiviteit();
        });

        btnNieuwActiviteit.setOnAction((ActionEvent event) -> {
            toevoegenActiviteit();
        });
        
        btnWijzigActiviteit.setOnAction((ActionEvent event) -> {
            wijzigActiviteit();
        });
        
        btnSlaGegevensNieuweActiviteitOp.setOnAction((ActionEvent event)-> {
            opslaanNieuweActiviteit();
        });
        
        tvActiviteiten.getSelectionModel().selectFirst();
    }

    private void maakOverzicht() {
        maakFilters();
        maakTabel();
        maakDetailScherm();
        maakCrudknoppen();

        super.buildGui(44);
    }

    private void maakFilters() {
        txtFilterAantalDeelnemers = new TextField();
        txtFilterAantalDeelnemers.setPromptText("# deelnemers");
        txtFilterNaam = new TextField();
        txtFilterNaam.setPromptText("Filter op naam");
        
        cboFilterType = new ComboBox<>();
        cboFilterType.setItems(abc.geefFormulesFilter());
        
        cbFilterVolzet = new CheckBox();
        cbFilterVolzet.setText("Volzet");
        
        super.addTextField(txtFilterNaam);
        super.addTextField(txtFilterAantalDeelnemers);
        super.addCombobox(cboFilterType);
        super.addCheckBox(cbFilterVolzet);
    }

    private void maakTabel() {
        tvActiviteiten = new TableView<>();
        
        tvActiviteiten.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    vulDetailScherm(newSelection);
                });
        
        maakKolommenInTabel();
        tvActiviteiten.setItems((abc.geefAlleActiviteiten()));
        super.setTvTabel(tvActiviteiten);

    }

    private void vulDetailScherm(Activiteit newValue) {
        try {
            txtNaamActiviteit.setText(newValue.getNaam());
            cboType.getSelectionModel().select(newValue.getFormule());
            dpStartdatum.setValue(newValue.getBeginDatum());
            dpEinddatum.setValue(newValue.getEindDatum() == null ? null : newValue.getEindDatum());
            dpInschrijvingsDatum.setValue(newValue.getUitersteInschrijvingsDatum());
            txtMaxAantalDeelnemers.setText(Integer.toString(newValue.getMaxDeelnemers()));
            cbIsVolzet.setSelected(newValue.isVolzet());
            txtStraat.setText(newValue.getStraat());
            txtHuisnr.setText(newValue.getHuisnummer());
            txtBus.setText(newValue.getBus());
            txtStad.setText(newValue.getStad());
            txtPostcode.setText(newValue.getPostcode());
            tvDeelnemers.setItems(abc.geefInschrijvingenVanActiviteit(newValue));
            txtNaamLocatie.setText(newValue.getNaamLocatie());
            txtGsmnummerLocatie.setText(newValue.getGsmnummer());
            txtEmailLocatie.setText(newValue.getEmail());
        } catch (NullPointerException e) {
            //als je de list verandert vindt hij geen data meer
        }
    }

    private void maakKolommenInTabel() {
        colNaamActiviteit = new TableColumn<>("Naam ");
        colFormuleActiviteit = new TableColumn<>("Formule");
        colDeelnemers = new TableColumn<>("Aantal Deelnemers");
        colVolzet = new TableColumn<>("Volzet");
        colStartdatum = new TableColumn<>("Startdatum");
        colEinddatum = new TableColumn<>("Einddatum");
        
        colNaamActiviteit.setCellValueFactory(cellData -> cellData.getValue().naamProperty());
        colFormuleActiviteit.setCellValueFactory(cellData -> cellData.getValue().formuleProperty());
        colDeelnemers.setCellValueFactory(cellData -> cellData.getValue().aantalDeelnemersProperty());
        colVolzet.setCellValueFactory(cellData -> cellData.getValue().isVolzetProperty());
        colStartdatum.setCellValueFactory(cellData -> cellData.getValue().beginDatumProperty());
        colEinddatum.setCellValueFactory(cellData -> cellData.getValue().eindDatumProperty());
        
        super.addKolom(colNaamActiviteit);
        super.addKolom(colFormuleActiviteit);
        super.addKolom(colDeelnemers);
        super.addKolom(colVolzet);
        super.addKolom(colStartdatum);
        super.addKolom(colEinddatum);
    }

    private void maakDetailScherm() {
        lblNaamActiviteit = new Label("Naam *");
        lblCboType = new Label("Type *");
        lbldatum = new Label("Begin- en einddatum  *");
        lblMaxAantalDeelnemers = new Label("Max aantal *");
        lblInschrijvingsDatum = new Label("Uiterste inschrijving *");
        lblNaamLocatie = new Label("Naam locatie *");
        lblGsmnummerLocatie = new Label("Gsm nummer locatie *");
        lblEmailLocatie = new Label("Email locatie *");
        lblAdresLocatie = new Label("Adres locatie *");
        lblDeelnemers = new Label("Deelnemers");
        
        //!!
        //cboGeslacht.setMinWidth(118);
        
        GridPane form = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(25);
        form.getColumnConstraints().addAll(col1,col2,col3, col4);
        
        Insets insetsLabel = new Insets(3,0,-5,0);
        
        form.setAlignment(Pos.CENTER);
        form.setHgap(4);
        form.setVgap(5);
        //form.setPadding(new Insets(25, 25, 25, 25));
        
        //rij1
        lblNaamActiviteit.setPadding(insetsLabel);
        lblCboType.setPadding(insetsLabel);
        txtNaamActiviteit = new TextField();
        cboType = new ComboBox<>();
        cboType.setMaxWidth(300);
        cboType.setItems(abc.geefFormules());
        cboType.getStyleClass().add("greyDropdown");
        
        
        //rij2
        lbldatum.setPadding(insetsLabel);
        dpStartdatum = new DatePicker();
        dpEinddatum = new DatePicker();
        
        //rij3
        lblMaxAantalDeelnemers.setPadding(insetsLabel);
        lblInschrijvingsDatum.setPadding(insetsLabel);
        txtMaxAantalDeelnemers = new TextField();
        txtMaxAantalDeelnemers.setMaxWidth(50);
        cbIsVolzet = new CheckBox("Volzet");
        dpInschrijvingsDatum = new DatePicker();
        
        //rij4
        lblNaamLocatie.setPadding(insetsLabel);
        lblGsmnummerLocatie.setPadding(insetsLabel);
        txtNaamLocatie = new TextField();
        txtGsmnummerLocatie = new TextField();
        
        //cboGeslacht.getStyleClass().add("greyDropdown");
        
        //rij5
        lblEmailLocatie.setPadding(insetsLabel);
        txtEmailLocatie = new TextField();
        
        //rij6
        lblAdresLocatie.setPadding(insetsLabel);
        txtStraat = new TextField();
        txtStraat.setPromptText("Straat");
        txtHuisnr = new TextField();
        txtHuisnr.setPromptText("Nummer");
        txtBus = new TextField();
        txtBus.setPromptText("Bus");
        txtStad = new TextField();
        txtStad.setPromptText("Gemeente");
        txtPostcode = new TextField();
        txtPostcode.setPromptText("Postcode");
        
        //rij7
        lblDeelnemers.setPadding(insetsLabel);
        tvDeelnemers = new TableView<>();
        colDeelnemerFamilienaam = new TableColumn<>("Familienaam");
        colDeelnemerVoornaam = new TableColumn<>("Voornaam");
        colDeelnemerVoornaam.setCellValueFactory(cellData -> cellData.getValue().voornaamProperty());
        colDeelnemerFamilienaam.setCellValueFactory(cellData -> cellData.getValue().achternaamProperty());
        
        colDeelnemerFamilienaam.prefWidthProperty().bind(tvDeelnemers.widthProperty().divide(2));
        colDeelnemerVoornaam.prefWidthProperty().bind(tvDeelnemers.widthProperty().divide(2));
        tvDeelnemers.setMaxHeight(200);
        
        tvDeelnemers.getColumns().addAll(colDeelnemerFamilienaam, colDeelnemerVoornaam);
        tvDeelnemers.getStyleClass().add("name-column");
        tvDeelnemers.getStyleClass().add("titelLinks");
        tvDeelnemers.getStyleClass().add("table-row-cell");
        
        BorderPane volzetPane = new BorderPane();
        volzetPane.setLeft(txtMaxAantalDeelnemers);
        volzetPane.setRight(cbIsVolzet);
        
        
        //toevoegen elementen
        form.add(lblNaamActiviteit, 0, 0, 2,1);
        form.add(lblCboType, 2, 0, 2,1);
        form.add(txtNaamActiviteit, 0, 1, 2,1);
        //form.add(cbIsVolzet, 2, 1);
        form.add(cboType, 2, 1);
        form.add(lbldatum, 0, 2, 2,1);
        form.add(dpStartdatum, 0, 3);
        form.add(dpEinddatum, 1, 3);
        form.add(lblMaxAantalDeelnemers, 3, 0, 2,1);
        form.add(lblInschrijvingsDatum, 2, 2, 2,1);
        //form.add(txtMaxAantalDeelnemers, 3, 1);
        form.add(volzetPane, 3, 1);
        form.add(dpInschrijvingsDatum, 2, 3, 2,1);
        form.add(lblNaamLocatie, 0, 4, 2,1);
        form.add(lblGsmnummerLocatie, 2, 4, 2,1);
        form.add(txtNaamLocatie, 0, 5, 2,1);
        form.add(txtGsmnummerLocatie, 2, 5, 2,1);
        form.add(lblEmailLocatie, 0, 6, 2,1);
        form.add(txtEmailLocatie, 0, 7, 3,1);
        form.add(lblAdresLocatie, 0, 8);
        form.add(txtStraat, 0,9,2,1);
        form.add(txtHuisnr, 2,9);
        form.add(txtBus, 3,9);
        form.add(txtPostcode, 0,10);
        form.add(txtStad, 1,10,2,1);
        
        form.add(lblDeelnemers, 0, 11);
        form.add(tvDeelnemers, 0,12,2,2);
        
        form.getChildren().stream().forEach(c-> {
            c.getStyleClass().add("allButtons");
        });
        
        VBox box = new VBox(form);
        super.setDetailScherm(box);
    }

    private void maakCrudknoppen() {
        btnActiviteitVerwijderen = new Button("Activiteit verwijderen");
        btnActiviteitVerwijderen.getStyleClass().add("allButtons");
        btnActiviteitVerwijderen.getStyleClass().add("greyBtn");
        
        btnNieuwActiviteit = new Button("Activiteit toevoegen");
        btnNieuwActiviteit.getStyleClass().add("allButtons");
        btnNieuwActiviteit.getStyleClass().add("orangeBtn");
        
        btnWijzigActiviteit = new Button("Wijzigingen opslaan");
        btnWijzigActiviteit.getStyleClass().add("allButtons");
        btnWijzigActiviteit.getStyleClass().add("orangeBtn");
        
        btnCancel = new Button("Cancel");
        btnCancel.getStyleClass().add("greyBtn");
        btnCancel.getStyleClass().add("allButtons");
        
        btnSlaGegevensNieuweActiviteitOp = new Button("Activiteit toevoegen");
        btnSlaGegevensNieuweActiviteitOp.getStyleClass().add("orangeBtn");
        btnSlaGegevensNieuweActiviteitOp.getStyleClass().add("allButtons");
        
        
        super.addCrudKnop(btnActiviteitVerwijderen);
        super.addExtraKnop(btnNieuwActiviteit);
        super.addCrudKnop(btnWijzigActiviteit);
    }
    
    private void toevoegenActiviteit() {
        
        super.emptyCrudKnoppenList();
        super.addCrudKnop(btnCancel);
        super.addCrudKnop(btnSlaGegevensNieuweActiviteitOp);
        super.maakCrudKnoppen();
        super.resetLabel();
        super.disableFilters(true);
        
        clearGedetailleerdeLijst();
        
        tvActiviteiten.getSelectionModel().clearSelection();
    }
    
    public void clearGedetailleerdeLijst() {
        txtNaamActiviteit.clear();
        cboType.getSelectionModel().clearSelection();
        dpStartdatum.setValue(null);
        dpEinddatum.setValue(null);
        dpInschrijvingsDatum.setValue(null);
        txtMaxAantalDeelnemers.clear();
        cbIsVolzet.setSelected(false);
        txtStraat.clear();
        txtHuisnr.clear();
        txtBus.clear();
        txtStad.clear();
        txtPostcode.clear();
        tvDeelnemers.setItems(null);
        txtNaamLocatie.clear();
        txtGsmnummerLocatie.clear();
        txtEmailLocatie.clear();
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
            abc.filterList(naam, formule, aantalDeelnemers, volzet);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fout!");
            alert.setHeaderText("Foute input!");
            alert.setContentText("U moet een numerieke waarde ingeven voor filter '# deelnemers'!");
            alert.showAndWait();
            txtFilterAantalDeelnemers.clear();
        }
        tvActiviteiten.getSelectionModel().selectFirst();

    }
    
    
    private void voegActiviteitToe() {
        btnWijzigActiviteit.setDisable(true);
        btnActiviteitVerwijderen.setDisable(true);
        btnSlaGegevensNieuweActiviteitOp.setVisible(true);
    }

    
    

    //
    //CRUD - inschrijvingen
    //
    private void voegDeelnemerToe(ActionEvent event) {
        //UC4 --> inschrijvingen
        Activiteit activiteit = tvActiviteiten.getSelectionModel().getSelectedItem();
        if (activiteit == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FOUT!");
            alert.setHeaderText("Activiteit ongekend");
            alert.setContentText("U moet eerst een activiteit selecteren");
            alert.showAndWait();
        } else {
            inschrijvingToevoegenScherm = new InschrijvingToevoegenScherm(this,
                    abc, activiteit);

            Scene scene = new Scene(inschrijvingToevoegenScherm, 400, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Toevoegen lid");
            stage.setResizable(true);
            stage.showAndWait();
        }

    }

    private void verwijderDeelnemer(ActionEvent event) {
        //UC4 --> inschrijvingen
        Lid lid = tvDeelnemers.getSelectionModel().getSelectedItem() == null
                ? null
                : tvDeelnemers.getSelectionModel().getSelectedItem().getLid();
        Activiteit activiteit = tvActiviteiten.getSelectionModel().getSelectedItem();
        if (activiteit != null && lid != null) {
            abc.verwijderInschrijving(activiteit, lid);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FOUT!");
            alert.setHeaderText("Ongekend lid");
            alert.setContentText("U moet nog een lid selecteren!");
            alert.showAndWait();
        }
    }
    
    private void opslaanNieuweActiviteit() {
        try {
            abc.voegActiviteitToe(txtNaamActiviteit.getText(), cboType.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(txtMaxAantalDeelnemers.getText()),
                    dpStartdatum.getValue(), dpEinddatum.getValue(), dpInschrijvingsDatum.getValue(),
                    txtStraat.getText(), txtStad.getText(), txtPostcode.getText(), txtHuisnr.getText(), txtBus.getText(),
                    txtNaamLocatie.getText(), txtGsmnummerLocatie.getText(), txtEmailLocatie.getText());
            super.setErrorLabelText("");
            btnWijzigActiviteit.setDisable(false);
            btnActiviteitVerwijderen.setDisable(false);
            btnSlaGegevensNieuweActiviteitOp.setVisible(false);

        } catch (NumberFormatException ex) {
            super.setErrorLabelText("U moet een nummer geven bij max. aantal deelnemers!");
            txtMaxAantalDeelnemers.clear();
        } catch (IllegalArgumentException e) {
            super.setErrorLabelText(e.getMessage());
        }

    }

    private void wijzigActiviteit() {
        Activiteit activiteit = tvActiviteiten.getSelectionModel().getSelectedItem();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bevestiging wijziging");
            alert.setHeaderText("Bevestiging");
            alert.setContentText(String.format("Ben je zeker dat je activiteit %s wilt wijzigen?", activiteit.getNaam()));

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                abc.wijzigActiviteit(activiteit, txtNaamActiviteit.getText(), cboType.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(txtMaxAantalDeelnemers.getText()), dpStartdatum.getValue(), dpEinddatum.getValue(), dpInschrijvingsDatum.getValue(),
                        txtStraat.getText(), txtStad.getText(), txtPostcode.getText(), txtHuisnr.getText(), txtBus.getText(),
                        txtNaamLocatie.getText(), txtGsmnummerLocatie.getText(), txtEmailLocatie.getText());
            }
            super.setErrorLabelText("");
        } catch (NumberFormatException ex) {
            super.setErrorLabelText("U moet een nummer geven bij max. aantal deelnemers!");
            txtMaxAantalDeelnemers.clear();
        } catch (IllegalArgumentException e) {
            super.setErrorLabelText(e.getMessage());
        }

    }

    private void verwijderActiviteit() {
        Activiteit activiteit = tvActiviteiten.getSelectionModel().getSelectedItem();
        if (abc.geefInschrijvingenVanActiviteit(activiteit).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bevestiging verwijderen");
            alert.setHeaderText("Bevestiging");
            alert.setContentText(String.format("Ben je zeker dat je activiteit %s wilt verwijderen?", activiteit.getNaam()));

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                abc.verwijderActiviteit(activiteit);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FOUT!");
            alert.setHeaderText("Verwijderen gaat niet!");
            alert.setContentText(String.format("%s bevat nog deelnemers!", activiteit.getNaam()));
            alert.showAndWait();
        }

    }

    private void cancelToevoegenNieuweActiviteit() {
        tvActiviteiten.getSelectionModel().selectFirst();
        
        super.emptyCrudKnoppenList();
        super.addCrudKnop(btnActiviteitVerwijderen);
        super.addCrudKnop(btnWijzigActiviteit);
        super.maakCrudKnoppen();
        super.resetLabel();
        super.disableFilters(false);
    }
    
    
}
