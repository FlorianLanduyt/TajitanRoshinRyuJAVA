package gui.overzichten;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class OverzichtActiviteitenScherm extends AnchorPane {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label lblAdminName;
    @FXML
    private ImageView ivSignIn;
    @FXML
    private Button btnSignIn;
    @FXML
    private ImageView ivSignOff;
    @FXML
    private Button btnSignOff;

    public OverzichtActiviteitenScherm() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtActiviteitenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void meldAan(ActionEvent event) {
    }

    @FXML
    private void meldAf(ActionEvent event) {
    }

}
