/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Tybo Vanderstraeten
 */
public class HoofdMenu extends VBox {

    @FXML
    private Button btnOverzicht;
    @FXML
    private Button btnBeheerLeden;
    @FXML
    private Button btnBeheerActiviteiten;
    @FXML
    private Button btnInschrijvenActiviteit;
    @FXML
    private Button btnBeheerLesmateriaal;

    public HoofdMenu() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HoofdMenu.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
