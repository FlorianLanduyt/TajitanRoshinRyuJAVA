package gui.overzichten;

import domein.Activiteit;
import domein.AdminController;
import domein.Lid;
import domein.OverzichtController;
import gui.BeginScherm;
import gui.OverzichtMenu;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OverzichtActiviteitenScherm extends AnchorPane {

    @FXML
    private Label lblActiviteiten;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label lblAdminName;
    @FXML
    private ImageView ivSignOff;
    @FXML
    private Button btnSignOff;
    @FXML
    private TableView<Activiteit> tvActiviteiten;
    @FXML
    private TableColumn<Activiteit, String> colNaam;
    @FXML
    private TableColumn<Activiteit, String> colDatum;
    @FXML
    private TableColumn<Activiteit, String> colFormule;
    @FXML
    private TableColumn<Activiteit, String> colStad;
    @FXML
    private TableColumn<Activiteit, String> colPostcode;
    @FXML
    private TableColumn<Activiteit, String> colStraat;
    @FXML
    private TableColumn<Activiteit, String> colHuisnummer;
    @FXML
    private TableColumn<Activiteit, String> colBus;
    @FXML
    private ComboBox<Lid> cbLeden;
    @FXML
    private Label lblPerDeelnemer;
    @FXML
    private Button btnAlleActiviteiten;
    @FXML
    private Button btnActiviteitenPerDeelnemer;

    private BeginScherm beginScherm;
    private AdminController adminController;
    private OverzichtController overzichtController;

    public OverzichtActiviteitenScherm(BeginScherm beginScherm, AdminController adminController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtActiviteitenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.beginScherm = beginScherm;
        this.adminController = adminController;
        this.overzichtController = new OverzichtController();
        this.getChildren().add(new OverzichtMenu(this.beginScherm, this.adminController));

        lblAdminName.setText("Welkom, " + adminController
                .getAangemeldeAdmin().getGebruikersnaam());

        //Tableview setup
        colNaam.setCellValueFactory(cellData -> cellData.getValue().naamProperty());
        colDatum.setCellValueFactory(cellData -> cellData.getValue().datumProperty());
        colFormule.setCellValueFactory(cellData -> cellData.getValue().formuleProperty());
        colStad.setCellValueFactory(cellData -> cellData.getValue().stadProperty());
        colPostcode.setCellValueFactory(cellData -> cellData.getValue().postcodeProperty());
        colStraat.setCellValueFactory(cellData -> cellData.getValue().straatProperty());
        colHuisnummer.setCellValueFactory(cellData -> cellData.getValue().huisnummerProperty());
        colBus.setCellValueFactory(cellData -> cellData.getValue().busProperty());
        tvActiviteiten.setItems(overzichtController.geefOverzichtActiviteiten());

        //Combobox vullen
        cbLeden.setItems(overzichtController.geefOverzichtLeden());

    }

    @FXML
    private void toonAlleActiviteiten(ActionEvent event) {
        tvActiviteiten.setItems(overzichtController.geefOverzichtActiviteiten());
        cbLeden.getSelectionModel().clearSelection();
    }

    @FXML
    private void toonActiviteitenPerDeelnemer(ActionEvent event) {
        Lid lid = cbLeden.getSelectionModel().selectedItemProperty().getValue();
        if (lid != null) {
            tvActiviteiten.setItems(overzichtController.geefOverzichtActiviteitenVoorBepaaldeDeelnemer(lid));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Filterfout");
            alert.setHeaderText("Filteren niet geslaagd");
            alert.setContentText("U dient een lid te selecteren.");
            alert.showAndWait();
        }
    }

    @FXML
    private void meldAf(ActionEvent event) {
        BeginScherm beginScherm = new BeginScherm();
        Scene scene = new Scene(beginScherm);
        Stage stage = (Stage) (getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("Taijitan Yoshin Ryu - Adminmodule");
        stage.setResizable(false);
        stage.show();
    }

}
