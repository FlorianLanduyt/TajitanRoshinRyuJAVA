package gui;

import domein.Admin;
import domein.AdminController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeginScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        adminController = new AdminController();
        btnSignOff.setVisible(false);
        ivSignOff.setVisible(false);
    }

    @FXML
    private void meldAan(ActionEvent event) {
        loginForm = new LoginForm(adminController);
        loginForm.addObserver(this);
        Scene scene = new Scene(loginForm);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Aanmelden");
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void meldAf(ActionEvent event) {
        adminController.setAangemeldeAdmin(null);
        lblAdminName.setText("Aanmelden");
        btnSignOff.setVisible(false);
        ivSignOff.setVisible(false);
        btnSignIn.setVisible(true);
        ivSignIn.setVisible(true);

        this.getChildren().remove(hoofdMenu);
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        adminController.setAangemeldeAdmin((Admin) pce.getNewValue());
        lblAdminName.setText("Welkom, " + adminController.getAangemeldeAdmin().getGebruikersnaam());
        btnSignIn.setVisible(false);
        ivSignIn.setVisible(false);
        btnSignOff.setVisible(true);
        ivSignOff.setVisible(true);

        hoofdMenu = new HoofdMenu();
        this.getChildren().add(hoofdMenu);
    }

}
