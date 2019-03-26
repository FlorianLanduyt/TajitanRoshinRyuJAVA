/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author Tim
 */
@Entity
public class Raadpleging implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Lid lid;
    @ManyToOne
    private Oefening oefening;
    private int aantalRaadplegingen;
    private List<LocalDate> tijdstippen;

    //SimpleStringProperties
    @Transient
    private SimpleStringProperty sVoornaam = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sAchternaam = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sOefeningNaam = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sAantalRaadplegingen = new SimpleStringProperty();

    public Raadpleging() {
    }

    public Raadpleging(Lid lid, Oefening oefening) {
        setLid(lid);
        setOefening(oefening);
        setOefeningNaam();
        setVoornaam();
        setAchternaam();
        this.aantalRaadplegingen = 0;
        tijdstippen = new ArrayList<>();
        verhoogAantalRaadplegingen();
    }

    //Getters voor SimpleStringProperties
    public SimpleStringProperty voornaamProperty() {
        return sVoornaam;
    }

    public SimpleStringProperty achternaamProperty() {
        return sAchternaam;
    }

    public SimpleStringProperty oefeningNaamProperty() {
        return sOefeningNaam;
    }

    public SimpleStringProperty aantalRaadplegingenProperty() {
        return sAantalRaadplegingen;
    }
    public void setSimpleStringProperties() {
        sVoornaam.set(getLid().getVoornaam());
        sAchternaam.set(getLid().getAchternaam());
        sOefeningNaam.set(getOefening().getTitel());
        sAantalRaadplegingen.set(String.valueOf(aantalRaadplegingen));
    }
    
    //Gewone getters en setters
    public Lid getLid() {
        return lid;
    }

    private void setLid(Lid lid) {
        this.lid = lid;
    }

    public Oefening getOefening() {
        return oefening;
    }

    private void setOefening(Oefening oefening) {
        this.oefening = oefening;
    }

    public String getVoornaam() {
        return getLid().getVoornaam();
    }

    public void setVoornaam() {
        sVoornaam.set(getLid().getVoornaam());
    }

    public String getAchternaam() {
        return getLid().getAchternaam();
    }

    public void setAchternaam() {
        sAchternaam.set(getLid().getAchternaam());
    }

    public String getOefeningNaam() {
        return getOefening().getTitel();
    }

    private void setOefeningNaam() {
        sOefeningNaam.set(oefening.getTitel());
    }

    public List<LocalDate> getTijdstippen() {
        return tijdstippen;
    }

    public LocalDate getTijdstipLaatsteRaadpleging() {
        return tijdstippen.get(tijdstippen.size() - 1);
    }

    public int getAantalRaadplegingen() {
        return this.aantalRaadplegingen;
    }

    public void verhoogAantalRaadplegingen() {
        //Attributen van raadpleging
        this.aantalRaadplegingen += 1;
        sAantalRaadplegingen.set(String.valueOf(this.aantalRaadplegingen));
        //Toevoegen aan de lijst van tijdstippen
        this.tijdstippen.add(LocalDate.now());
        //Attributen van oefening
        this.oefening.setAantalRaadplegingen(this.oefening.getAantalRaadplegingen() + 1);
        this.oefening.setLaatsteRaadpleging(LocalDate.now());
    }

}
