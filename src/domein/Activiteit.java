/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Tim
 */
public class Activiteit {
    private String naam;
    private Formule formule;
    private LocalDate datum;
    private String straat;
    private String stad;
    private String postcode;
    private String huisnummer;
    private String bus;
    private List<Lid> deelnemers;

    // enkel naam en datum zijn verplicht in de DB, 
    // vandaar krijgen deze private setters 
    // en worden deze altijd in de constructor geïnitialiseerd
    public Activiteit(String naam,Formule formule, LocalDate datum) {
        setNaam(naam);
        setFormule(formule);
        setDatum(datum);
    }
    

    public String getNaam() {
        return naam;
    }

    private void setNaam(String naam) {
        this.naam = naam;
    }

    public Formule getFormule() {
        return formule;
    }

    private void setFormule(Formule formule) {
        this.formule = formule;
    }
    

    public LocalDate getDatum() {
        return datum;
    }

    private void setDatum(LocalDate datum) {
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

    public List<Lid> getDeelnemers() {
        return deelnemers;
    }

    public void setDeelnemers(List<Lid> deelnemers) {
        this.deelnemers = deelnemers;
    }
    public void voegDeelnemerToe(Lid lid){
        this.deelnemers.add(lid);
    }
    public void verwijderDeelnemer(Lid lid){
        this.deelnemers.remove(lid);
    }
    
}
