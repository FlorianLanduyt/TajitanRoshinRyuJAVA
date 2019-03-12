package gui;

import domein.controllers.AdminController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    
    private BeginSchermFlo beginscherm;

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

        hoofdMenu = new HoofdMenu(beginscherm, adminController);
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
