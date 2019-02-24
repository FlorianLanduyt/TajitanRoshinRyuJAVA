package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class OverzichtMenu extends VBox {

    @FXML
    private Button btnActiviteiten;
    @FXML
    private Button btnInschrijvingen;
    @FXML
    private Button btnAanwezigheden;
    @FXML
    private Button btnClubkampioenschap;
    @FXML
    private Button btnLesmateriaal;
    @FXML
    private Button btnTerug;

    private HoofdMenu parent;

    public OverzichtMenu(HoofdMenu parent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtMenu.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.parent = parent;
    }

    @FXML
    private void terugNaarHoofdmenu(ActionEvent event) {
        parent.getParentClass().getChildren().remove(this);
        parent.getParentClass().getChildren().add(parent);
    }

    public HoofdMenu getParentClass() {
        return parent;
    }

}
