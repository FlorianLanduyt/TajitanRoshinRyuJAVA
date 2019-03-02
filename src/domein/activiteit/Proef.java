/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.activiteit;

import domein.enums.Formule;
import java.time.LocalDate;

/**
 *
 * @author Tybo Vanderstraeten
 */
public class Proef extends Activiteit {

    public Proef(String naam, LocalDate datum) {
        super(naam, datum);
        setFormule(Formule.PROEF);
    }

}
