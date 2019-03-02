package domein;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

public class Lid {

    private int id;
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
    private SimpleStringProperty sPuntenAantal = new SimpleStringProperty();

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

    public SimpleStringProperty puntenAantalProperty() {
        return sPuntenAantal;
    }

    //Gewone getters en setters
    public int getId() {
        return id;
    }

    public String getVoornaam() {
        return sVoornaam.get();
    }

    public void setVoornaam(String voornaam) {
        sVoornaam.set(voornaam);
    }

    public String getAchternaam() {
        return sAchternaam.get();
    }

    public void setAchternaam(String achternaam) {
        sAchternaam.set(achternaam);
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getRijksregisterNr() {
        return rijksregisterNr;
    }

    public void setRijksregisterNr(String rijksregisterNr) {
        this.rijksregisterNr = rijksregisterNr;
    }

    public LocalDate getDatumEersteTraining() {
        return datumEersteTraining;
    }

    public void setDatumEersteTraining(LocalDate datumEersteTraining) {
        this.datumEersteTraining = datumEersteTraining;
    }

    public String getGsmNr() {
        return gsmNr;
    }

    public void setGsmNr(String gsmNr) {
        this.gsmNr = gsmNr;
    }

    public String getVasteTelefoonNr() {
        return vasteTelefoonNr;
    }

    public void setVasteTelefoonNr(String vasteTelefoonNr) {
        this.vasteTelefoonNr = vasteTelefoonNr;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public void setHuisNr(String huisNr) {
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

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
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

    public void setGeboorteplaats(String geboorteplaats) {
        this.geboorteplaats = geboorteplaats;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public String getNationaliteit() {
        return nationaliteit;
    }

    public void setNationaliteit(String nationaliteit) {
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

    public void setGraad(String graad) {
        this.graad = graad;
    }

    public int getPuntenAantal() {
        return Integer.valueOf(sPuntenAantal.get());
    }

    public void setPuntenAantal(int puntenAantal) {
        sPuntenAantal.set(String.valueOf(puntenAantal));
    }

    @Override
    public String toString() {
        return String.format("%s %s", getVoornaam(), getAchternaam());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.rijksregisterNr);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lid other = (Lid) obj;
        if (!Objects.equals(this.rijksregisterNr, other.rijksregisterNr)) {
            return false;
        }
        return true;
    }

    

}
