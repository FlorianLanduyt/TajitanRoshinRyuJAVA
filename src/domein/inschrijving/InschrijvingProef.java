/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.inschrijving;

import domein.enums.Formule;
import domein.Lid;
import domein.activiteit.Proef;
import java.time.LocalDate;

/**
 *
 * @author Tybo Vanderstraeten
 */
public class InschrijvingProef extends Inschrijving {

    private Proef proef;

    public InschrijvingProef(Lid lid, LocalDate tijdstip) {
        super(Formule.PROEF, lid, tijdstip);
        proef = null;
    }

    public Proef getProef() {
        return proef;
    }

    public void setProef(Proef proef) {
        this.proef = proef;
    }

}
