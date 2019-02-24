package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import main.StartUpGUI;

public class BeginScherm extends AnchorPane {

    @FXML
    private Button btnSignIn;

    public BeginScherm() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeginScherm.fxml"));
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("aaa");
        alert.setHeaderText("aa");
        alert.setContentText("aaa");
        alert.showAndWait();
    }

}
