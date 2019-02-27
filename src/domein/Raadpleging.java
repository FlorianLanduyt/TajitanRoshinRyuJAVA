/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Tim
 */
public class Raadpleging {

    private Lid lid;
    private Oefening oefening;
    private int aantalRaadplegingen;

    //SimpleStringProperties
    private SimpleStringProperty sVoornaam = new SimpleStringProperty();
    private SimpleStringProperty sAchternaam = new SimpleStringProperty();
    private SimpleStringProperty sOefeningNaam = new SimpleStringProperty();
    private SimpleStringProperty sAantalRaadplegingen = new SimpleStringProperty();

    public Raadpleging(Lid lid, Oefening oefening) {
        setLid(lid);
        setOefening(oefening);
        setOefeningNaam();
        setVoornaam();
        setAchternaam();
        this.aantalRaadplegingen = 0;
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
        return sVoornaam.get();
    }

    public void setVoornaam() {
        sVoornaam.set(getLid().getVoornaam());
    }

    public String getAchternaam() {
        return sAchternaam.get();
    }

    public void setAchternaam() {
        sAchternaam.set(getLid().getAchternaam());
    }

    public String getOefeningNaam() {
        return sOefeningNaam.get();
    }

    private void setOefeningNaam() {
        sOefeningNaam.set(oefening.getTitel());
    }

    public int getAantalRaadplegingen() {
        return Integer.valueOf(sAantalRaadplegingen.get());
    }

    public void verhoogAantalRaadplegingen() {
        this.aantalRaadplegingen += 1;
        sAantalRaadplegingen.set(String.valueOf(this.aantalRaadplegingen));
    }

}
