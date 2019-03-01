package gui;

import domein.Admin;
import domein.AdminController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.StartUpGUI;

public class BeginScherm extends AnchorPane implements PropertyChangeListener {

    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnSignOff;
    @FXML
    private Label lblAdminName;
    @FXML
    private ImageView ivSignIn;
    @FXML
    private ImageView ivSignOff;

    private AdminController adminController;
    private LoginForm loginForm;
    private HoofdMenu hoofdMenu;

    public BeginScherm() {
        this.adminController = new AdminController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeginScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.adminController.addPropertyChangeListener(this);
        signOffVisibility(false);
    }

    @FXML
    private void meldAan(ActionEvent event) {
        signInVisibility(false);
        loginForm = new LoginForm(adminController);
        Scene scene = new Scene(loginForm);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Aanmelden");
        stage.setResizable(false);
        stage.showAndWait();
        if (adminController.getAangemeldeAdmin() == null) {
            signInVisibility(true);
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

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        lblAdminName.setText("Welkom, " + adminController
                .getAangemeldeAdmin().getGebruikersnaam());

        signInVisibility(false);
        signOffVisibility(true);

        hoofdMenu = new HoofdMenu(this, adminController);
        this.getChildren().add(hoofdMenu);
    }

    private void signInVisibility(boolean value) {
        btnSignIn.setVisible(value);
        ivSignIn.setVisible(value);
    }

    private void signOffVisibility(boolean value) {
        btnSignOff.setVisible(value);
        ivSignOff.setVisible(value);
    }

}
