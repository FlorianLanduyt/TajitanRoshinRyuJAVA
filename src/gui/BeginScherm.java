package gui;

import domein.Admin;
import domein.AdminController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.StartUpGUI;

public class BeginScherm extends AnchorPane implements PropertyChangeListener {

    @FXML
    private Button btnSignIn;

    @FXML
    private Label lblAdminName;

    private AdminController adminController;
    private Admin admin;

    private LoginForm loginForm;

    public BeginScherm() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeginScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        adminController = new AdminController();
        admin = null;

        loginForm = new LoginForm(adminController);
        loginForm.addObserver(this);
    }

    @FXML
    private void meldAan(ActionEvent event) {
        Scene scene = new Scene(loginForm);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Aanmelden");
        stage.setResizable(false);
        stage.showAndWait();
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        Admin admin = (Admin) pce.getNewValue();
        lblAdminName.setText(admin.getGebruikersnaam());
    }

}
