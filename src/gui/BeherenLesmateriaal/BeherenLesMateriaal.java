/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.BeherenLesmateriaal;

import domein.Lid;
import domein.Oefening;
import domein.Thema;
import domein.controllers.AdminController;
import domein.controllers.LesmateriaalBeheerController;
import domein.enums.Graad;
import gui.BeginSchermFlo;
import gui.overzichten.Overzicht;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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
    private TextField txtTitelDetail;
    private ComboBox<String> cboThemaDetail;
    private ComboBox<Graad> cboGraadDetail;
    private TextField txtUrlAfbeelding;
    private TextField txtUrlVideo;
    private TextArea txaTekst;
   

    //CRUD
    private Button btnLesmateriaalToevoegen;
    private Button btnLesmateriaalVerwijderen;
    private Button btnLesmateriaalWijzigen;
    private Button btnCancel;
    private Button btnSlaNieuweGegevensLesmateriaalOp;
    
    private VBox overzicht;

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

        btnCancel.setOnAction((ActionEvent action) -> {
            cancelToevoegenNieuwLesmateriaal();
        });

        btnLesmateriaalVerwijderen.setOnAction((ActionEvent event) -> {
            verwijderenLesmateriaal();
        });
        btnLesmateriaalToevoegen.setOnAction((ActionEvent event) -> {
            toevoegenLesmateriaal();
        });

        btnLesmateriaalWijzigen.setOnAction((ActionEvent event) -> {
            opslaanWijzigingen();
        });

        btnSlaNieuweGegevensLesmateriaalOp.setOnAction((ActionEvent event) -> {
            slaGegevensNieuwLesMateriaalOp();
        });

        tblOefeningen.getSelectionModel().selectedItemProperty()
                .addListener((observer, oldValue, newValue) -> {
                    vulDetailScherm(newValue);
                });

        tblOefeningen.getSelectionModel().selectFirst();

    }

    private void clearAlleVelden() {
        txtTitelDetail.clear();
        cboGraadDetail.getSelectionModel().clearSelection();
        cboThemaDetail.getSelectionModel().clearSelection();
        txtUrlAfbeelding.clear();
        txtUrlVideo.clear();
        txaTekst.clear();
    }

    private void maakOverzicht() {
        maakFilters();
        maakTabel();
        maakDetailScherm();
        maakCrudknoppen();

        super.buildGui(47);
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
        
        tblOefeningen.setPlaceholder(new Label("Geen oefeningen"));
        
        maakKolommenInTabel();

        tblOefeningen.setItems((lesmateriaalBeheerController.geefObservableListOefeningen()));
        super.setTvTabel(tblOefeningen);
    }

    private void maakDetailScherm() {
        Label lblTitel = new Label("Titel *");
        Label lblThema = new Label("Thema *");
        Label lblGraad = new Label("Graad *");
        Label lblAfbeelding = new Label("URL afbeelding *");
        Label lblTekst = new Label("Tekst *");
        Label lblVideo = new Label("URL video *");

        txtTitelDetail = new TextField();

        cboThemaDetail = new ComboBox();
        cboThemaDetail.setPromptText("Thema");
        cboThemaDetail.setItems(lesmateriaalBeheerController.geefThemasDetaillijst());

        cboGraadDetail = new ComboBox();
        cboGraadDetail.setPromptText("Graad");
        cboGraadDetail.setItems(lesmateriaalBeheerController.geefGraden());

        txtUrlAfbeelding = new TextField();
        txtUrlVideo = new TextField();
        txaTekst = new TextArea();

        GridPane form = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(25);
        form.getColumnConstraints().addAll(col1, col2, col3, col4);

        Insets insetsLabel = new Insets(3, 0, -5, 0);

        form.setAlignment(Pos.CENTER);
        form.setHgap(4);
        form.setVgap(5);
        
        lblTitel.setPadding(insetsLabel);
        lblThema.setPadding(insetsLabel);
        lblGraad.setPadding(insetsLabel);
        lblTekst.setPadding(insetsLabel);
        lblAfbeelding.setPadding(insetsLabel);
        lblVideo.setPadding(insetsLabel);
        
        cboGraadDetail.setMaxWidth(300);
        
        txaTekst.getStyleClass().add("txa");
        cboGraadDetail.getStyleClass().add("greyDropdown");
        cboThemaDetail.getStyleClass().add("greyDropdown");

        //rij1
        form.add(lblTitel, 0, 0);
        form.add(txtTitelDetail, 0, 1, 2, 1);

        form.add(lblThema, 2, 0);
        form.add(cboThemaDetail, 2, 1);
        form.add(lblGraad, 3, 0);
        form.add(cboGraadDetail, 3, 1);

        //rij2
        form.add(lblAfbeelding, 0, 3, 2, 1);
        form.add(txtUrlAfbeelding, 0, 4, 4, 1);

        //rij3
        form.add(lblVideo, 0, 5, 2, 1);
        form.add(txtUrlVideo, 0, 6, 4, 1);

        //rij4
        form.add(lblTekst, 0, 7);
        form.add(txaTekst, 0, 8, 4, 4);
        txaTekst.getStylesheets().add("allButtons");

        form.getChildren().stream().forEach(c -> c.getStyleClass().add("allButtons"));

        VBox detailscherm = new VBox(form);
        super.setDetailScherm(detailscherm);
    }

    private void maakCrudknoppen() {
        btnLesmateriaalVerwijderen = new Button("Lesmateriaal verwijderen");
        btnLesmateriaalVerwijderen.getStyleClass().add("allButtons");
        btnLesmateriaalVerwijderen.getStyleClass().add("greyBtn");

        btnLesmateriaalToevoegen = new Button("Lesmateriaal toevoegen");
        btnLesmateriaalToevoegen.getStyleClass().add("allButtons");
        btnLesmateriaalToevoegen.getStyleClass().add("crud");

        btnLesmateriaalWijzigen = new Button("Wijzigingen opslaan");
        btnLesmateriaalWijzigen.getStyleClass().add("allButtons");
        btnLesmateriaalWijzigen.getStyleClass().add("crud");

        btnCancel = new Button("Cancel");
        btnCancel.getStyleClass().add("greyBtn");
        btnCancel.getStyleClass().add("allButtons");

        btnSlaNieuweGegevensLesmateriaalOp = new Button("Lesmateriaal toevoegen");
        btnSlaNieuweGegevensLesmateriaalOp.getStyleClass().add("crud");
        btnSlaNieuweGegevensLesmateriaalOp.getStyleClass().add("allButtons");

        super.addCrudKnop(btnLesmateriaalVerwijderen);
        super.addExtraKnop(btnLesmateriaalToevoegen);
        super.addCrudKnop(btnLesmateriaalWijzigen);

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

    private void vulDetailScherm(Oefening a) {
        clearAlleVelden();
        try {
            txtTitelDetail.setText(a.getTitel());
            cboThemaDetail.getSelectionModel().select(a.getThema().getNaam());
            cboGraadDetail.getSelectionModel().select(a.getGraad());
            txtUrlAfbeelding.setText(a.getAfbeelding());
            txtUrlVideo.setText(a.getUrlVideo());
            txaTekst.setText(a.getTekst());
        } catch (NullPointerException e) {
            //wanneer er geen aanwezigheid is geselecteerd in de tabel
        }

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

    private void cancelToevoegenNieuwLesmateriaal() {
        tblOefeningen.getSelectionModel().selectFirst();

        super.emptyCrudKnoppenList();
        super.addCrudKnop(btnLesmateriaalVerwijderen);
        super.addCrudKnop(btnLesmateriaalWijzigen);
        super.maakCrudKnoppen();
        super.resetLabel();
        super.disableFilters(false);
    }

    private void verwijderenLesmateriaal() {
        
        if (tblOefeningen.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FOUT");
            alert.setHeaderText("Oefening niet gevonden!");
            alert.setContentText(String.format("Er is geen oefening geselecteerd in de lijst!"));
        } else {
            Oefening oefening = tblOefeningen.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bevestiging verwijderen");
            alert.setHeaderText("Bevestiging");
            alert.setContentText(String.format("Ben je zeker dat je oefening %s wil verwijderen?", oefening.getTitel()));

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                lesmateriaalBeheerController.verwijderOefening(oefening);
            }
        }

    }

    private void toevoegenLesmateriaal() {
        super.emptyCrudKnoppenList();
        super.addCrudKnop(btnCancel);
        super.addCrudKnop(btnSlaNieuweGegevensLesmateriaalOp);
        super.maakCrudKnoppen();
        super.resetLabel();
        super.disableFilters(true);

        clearAlleVelden();
        tblOefeningen.getSelectionModel().clearSelection();
    }

    private void opslaanWijzigingen() {
        if (!tblOefeningen.getSelectionModel().isEmpty()) {
            Oefening oefening = tblOefeningen.getSelectionModel().getSelectedItem();

            //alles opvragen
            //alle parameters mee te geven aan wijzigLid + het lid zelf!
            try {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Bevestiging wijziging");
                alert.setHeaderText("Bevestiging");
                alert.setContentText(String.format("Ben je zeker dat je oefening %s wil wijzigen?", oefening.getTitel()));

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    lesmateriaalBeheerController.wijzigOefening(oefening, txtTitelDetail.getText(),
                            txtUrlVideo.getText(), txtUrlAfbeelding.getText(), txaTekst.getText(),
                            cboGraadDetail.getSelectionModel().getSelectedItem(),
                            lesmateriaalBeheerController.geefThemas()
                                    .stream()
                                    .filter(thema -> thema.getNaam().equals(cboThemaDetail.getSelectionModel().getSelectedItem()))
                                    .findAny()
                                    .orElse(null));

                    super.setErrorLabelText("");

                }
            } catch (IllegalArgumentException e) {
                super.setErrorLabelText(e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fout");
            alert.setHeaderText("U heeft geen oefening geselecteerd!");
            alert.setContentText("U moet een oefening selecteren uit de lijst!");
            alert.showAndWait();
        }
    }

    private void slaGegevensNieuwLesMateriaalOp() {
        try {
            
            lesmateriaalBeheerController.voegOefeningToe(txtTitelDetail.getText(), txtUrlVideo.getText(),
                    txtUrlAfbeelding.getText(), txaTekst.getText(), cboGraadDetail.getSelectionModel().getSelectedItem(),
                    lesmateriaalBeheerController.geefThemas()
                            .stream()
                            .filter(thema -> thema.getNaam().equals(cboThemaDetail.getSelectionModel().getSelectedItem()))
                            .findAny()
                            .orElse(null));

            super.setErrorLabelText("");
            btnSlaNieuweGegevensLesmateriaalOp.setText("Lesmateriaal toevoegen");
            cancelToevoegenNieuwLesmateriaal();
        } catch (IllegalArgumentException e) {
            super.setErrorLabelText(e.getMessage());
        }
    }
    
    
        
    

}
