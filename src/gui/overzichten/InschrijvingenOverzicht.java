/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import domein.controllers.AdminController;
import gui.BeginSchermFlo;

/**
 *
 * @author florianlanduyt
 */
public class InschrijvingenOverzicht extends Overzicht {

    private AdminController ac;
    private BeginSchermFlo parent;

    public InschrijvingenOverzicht(BeginSchermFlo parent, AdminController ac, String titelMenu) {
        super(parent, ac, titelMenu);

        this.ac = ac;
        this.parent = parent;
        maakScherm();
    }

    private void maakScherm() {
        
    }

}
