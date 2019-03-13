/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.controllers.AdminController;
import gui.overzichten.ActiviteitenOverzicht;
import gui.overzichten.InschrijvingenOverzicht;
import gui.overzichten.Overzicht;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author florianlanduyt
 */
public class OverzichtMenuKnoppen extends MenuKnoppen {

    private BeginSchermFlo parent;
    private final AdminController ac;

    public OverzichtMenuKnoppen(BeginSchermFlo beginscherm, AdminController ac) {
        super(beginscherm);
        this.ac = ac;
        this.parent = beginscherm;
        voegKnoppenToe();
    }

    private void voegKnoppenToe() {
        //BUTTON Activiteiten
        Button btnActiviteiten = new Button("Activiteiten");
        btnActiviteiten.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parent.ToonOverzichtenScherm(new ActiviteitenOverzicht(parent, ac, "Activiteiten overzicht"));
            }
        });
        btnActiviteiten.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    parent.ToonOverzichtenScherm(new ActiviteitenOverzicht(parent, ac, "Activiteiten overzicht"));
                }
            }
        });
        super.addKnop(btnActiviteiten);

        //BUTTON Inschrijvingen
        Button btnInschrijvingen = new Button("Inschrijvingen");
        btnInschrijvingen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parent.ToonOverzichtenScherm(new InschrijvingenOverzicht(parent, ac, "Inschrijvingen overzicht"));
            }
        });
        super.addKnop(btnInschrijvingen);

        //BUTTON Aanwezigheden
        Button btnAanwezigheden = new Button("Aanwezigheden");
        btnAanwezigheden.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        super.addKnop(btnAanwezigheden);

        //BUTTON ClubKampioenschap
        Button btnClubKampioenschap = new Button("Club kampioenschap");
        btnClubKampioenschap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        super.addKnop(btnClubKampioenschap);

        //BUTTON BEHEREN LESMATERIALEN 
        Button btnOverzichtRaadplegingenLesmateriaal = new Button("Overzicht lesmaterialen  ");
        btnOverzichtRaadplegingenLesmateriaal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        super.addKnop(btnOverzichtRaadplegingenLesmateriaal);

        super.buildGui();
    }

}
