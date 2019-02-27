package domein;

import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;

public class Lid {
    
    private String voornaam;
    private String achternaam;
    private LocalDate geboortedatum;
    private String rijksregisterNr;
    private LocalDate datumEersteTraining;
    private String gsmNr;
    private String vasteTelefoonNr;
    private String straat;
    private String huisNr;
    private String bus;
    private String postcode;
    private String email;
    private String wachtwoord;
    private String emailVader;
    private String emailMoeder;
    private String geboorteplaats;
    private String geslacht;
    private String nationaliteit;
    private String beroep;
    private String graad;

    //SimpleStringProperties voor TableView
    private SimpleStringProperty sVoornaam = new SimpleStringProperty();
    private SimpleStringProperty sAchternaam = new SimpleStringProperty();
    
    public Lid(String voornaam, String achternaam, LocalDate geboortedatum,
            String rijksregisterNr, LocalDate datumEersteTraining,
            String gsmNr, String vasteTelefoonNr, String straat,
            String huisNr, String postcode, String email,
            String wachtwoord, String geboorteplaats, String geslacht,
            String nationaliteit, String graad) {
        setVoornaam(voornaam);
        setAchternaam(achternaam);
        setGeboortedatum(geboortedatum);
        setRijksregisterNr(rijksregisterNr);
        setDatumEersteTraining(datumEersteTraining);
        setGsmNr(gsmNr);
        setVasteTelefoonNr(vasteTelefoonNr);
        setStraat(straat);
        setHuisNr(huisNr);
        setPostcode(postcode);
        setEmail(email);
        setWachtwoord(wachtwoord);
        setGeboorteplaats(geboorteplaats);
        setGeslacht(geslacht);
        setNationaliteit(nationaliteit);
        setGraad(graad);
    }

    //Getters voor SimpleStringProperties
    public SimpleStringProperty voornaamProperty() {
        return sVoornaam;
    }
    
    public SimpleStringProperty achternaamProperty() {
        return sAchternaam;
    }
    
    public String getVoornaam() {
        return sVoornaam.get();
    }
    
    private void setVoornaam(String voornaam) {
        sVoornaam.set(voornaam);
    }
    
    public String getAchternaam() {
        return sAchternaam.get();
    }
    
    private void setAchternaam(String achternaam) {
        sAchternaam.set(achternaam); 
    }
    
    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }
    
    private void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }
    
    public String getRijksregisterNr() {
        return rijksregisterNr;
    }
    
    private void setRijksregisterNr(String rijksregisterNr) {
        this.rijksregisterNr = rijksregisterNr;
    }
    
    public LocalDate getDatumEersteTraining() {
        return datumEersteTraining;
    }
    
    private void setDatumEersteTraining(LocalDate datumEersteTraining) {
        this.datumEersteTraining = datumEersteTraining;
    }
    
    public String getGsmNr() {
        return gsmNr;
    }
    
    private void setGsmNr(String gsmNr) {
        this.gsmNr = gsmNr;
    }
    
    public String getVasteTelefoonNr() {
        return vasteTelefoonNr;
    }
    
    private void setVasteTelefoonNr(String vasteTelefoonNr) {
        this.vasteTelefoonNr = vasteTelefoonNr;
    }
    
    public String getStraat() {
        return straat;
    }
    
    private void setStraat(String straat) {
        this.straat = straat;
    }
    
    public String getHuisNr() {
        return huisNr;
    }
    
    private void setHuisNr(String huisNr) {
        this.huisNr = huisNr;
    }
    
    public String getBus() {
        return bus;
    }
    
    public void setBus(String bus) {
        this.bus = bus;
    }
    
    public String getPostcode() {
        return postcode;
    }
    
    private void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    public String getEmail() {
        return email;
    }
    
    private void setEmail(String email) {
        this.email = email;
    }
    
    public String getWachtwoord() {
        return wachtwoord;
    }
    
    private void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
    
    public String getEmailVader() {
        return emailVader;
    }
    
    public void setEmailVader(String emailVader) {
        this.emailVader = emailVader;
    }
    
    public String getEmailMoeder() {
        return emailMoeder;
    }
    
    public void setEmailMoeder(String emailMoeder) {
        this.emailMoeder = emailMoeder;
    }
    
    public String getGeboorteplaats() {
        return geboorteplaats;
    }
    
    private void setGeboorteplaats(String geboorteplaats) {
        this.geboorteplaats = geboorteplaats;
    }
    
    public String getGeslacht() {
        return geslacht;
    }
    
    private void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }
    
    public String getNationaliteit() {
        return nationaliteit;
    }
    
    private void setNationaliteit(String nationaliteit) {
        this.nationaliteit = nationaliteit;
    }
    
    public String getBeroep() {
        return beroep;
    }
    
    public void setBeroep(String beroep) {
        this.beroep = beroep;
    }
    
    public String getGraad() {
        return graad;
    }
    
    private void setGraad(String graad) {
        this.graad = graad;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", getVoornaam(), getAchternaam());
    }
}
