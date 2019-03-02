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
public class Examen extends Activiteit {

    public Examen(String naam, LocalDate datum) {
        super(naam, datum);
        setFormule(Formule.EXAMEN);
    }

}
