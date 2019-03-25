/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.controllers.AdminController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author florianlanduyt
 */
public class LinksPane extends BorderPane implements PropertyChangeListener{

    //private String title;
    private MenuKnoppen knoppen;
    private final AdminController ac;
    private BeginSchermFlo parent;
    private boolean ingelogd;

    public LinksPane(MenuKnoppen knoppen, AdminController ac, BeginSchermFlo beginscherm, boolean ingelogd) {
        //this.title = title;
        this.ac = ac;
        this.knoppen = knoppen;
        this.parent = beginscherm;
        this.ingelogd = ingelogd;
        this.ac.addPropertyChangeListener(this);
        buildGui();
    }


    private void buildGui() {
        this.setMaxWidth(220);
        this.setMinWidth(220);
        this.setCenter(knoppen);

        Button terug = new Button();
        terug.setText("Hoofdmenu");
        this.setBottom(terug);
        
        ImageView afb = new ImageView(new Image("/images/home.png"));
        afb.setFitHeight(28);
        afb.setFitWidth(28);
        
        terug.setGraphic(afb);
        
        this.setPadding(new Insets(5));
        
        terug.getStyleClass().add("terugBtn");

        terug.setOnAction((ActionEvent event) -> {
            terugNaarHoofdMenu();
        });
        
        if(knoppen instanceof HoofdmenuKnoppen){
            terug.setDisable(true);
            terug.setVisible(false);
        } else {
            terug.setDisable(false);
            terug.setVisible(true);
        }

        //Opmaak
        this.setDisable(!ingelogd);
        this.getStyleClass().add("bgr");
        terug.setStyle("-fx-font-size: 18px");
    }

    public void terugNaarHoofdMenu() {
        parent.setLeft(null);
        parent.setLeft(new LinksPane(new HoofdmenuKnoppen(parent), ac, parent, ingelogd));
        parent.setMenuTitle("Hoofdmenu");
        parent.maakCenterPane();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        parent.setIngelogd(true);
        ingelogd = true;
        this.setDisable(!ingelogd);
    }
}
