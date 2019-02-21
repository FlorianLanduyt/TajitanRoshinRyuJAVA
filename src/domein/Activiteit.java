/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Date;

/**
 *
 * @author Tim
 */
public class Activiteit {
    private String naam;
    private Date datum;
    private String straat;
    private String stad;
    private String postcode;
    private String huisnummer;
    private String bus;

    // enkel naam en datum zijn verplicht in de DB, 
    // vandaar krijgen deze private setters 
    // en worden deze altijd in de constructor geïnitialiseerd
    public Activiteit(String naam, Date datum) {
        setNaam(naam);
        setDatum(datum);
    }

    public String getNaam() {
        return naam;
    }

    private void setNaam(String naam) {
        this.naam = naam;
    }

    public Date getDatum() {
        return datum;
    }

    private void setDatum(Date datum) {
        this.datum = datum;
    }

    private String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }
    
}
