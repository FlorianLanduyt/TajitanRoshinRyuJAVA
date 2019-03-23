/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.util.InputMismatchException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Tim
 */
@Entity
public class Thema implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String naam;

    public Thema() {
    }

    public Thema(String naam) {
        setNaam(naam);
    }

    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isEmpty()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn.");
        }
        naam = naam.trim();
        if (naam.length() > 20) {
            throw new IllegalArgumentException("Naam mag max. 20 karakters bevatten.");
        }
        if (naam.contains(" ")) {
            String tempNaam = naam.replaceAll(" ", "");
            if (tempNaam.matches(".*[\\d\\W].*")) {
                throw new InputMismatchException("Naam mag enkel letters bevatten.");
            }
        } else {
            if (naam.matches(".*[\\d\\W].*")) {
                throw new InputMismatchException("Naam mag enkel letters bevatten.");
            }
        }
        this.naam = naam;
    }

}
