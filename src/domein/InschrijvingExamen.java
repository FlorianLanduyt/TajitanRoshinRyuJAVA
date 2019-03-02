/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;

/**
 *
 * @author Tybo Vanderstraeten
 */
public class InschrijvingExamen extends Inschrijving {

    private Examen examen;

    public InschrijvingExamen(Lid lid, LocalDate tijdstip) {
        super(Formule.EXAMEN, lid, tijdstip);
        examen = null;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

}
