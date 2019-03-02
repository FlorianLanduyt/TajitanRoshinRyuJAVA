package domein;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private String stad;
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
    private Graad graad;

    //SimpleStringProperties voor TableView
    private SimpleStringProperty sVoornaam = new SimpleStringProperty();
    private SimpleStringProperty sAchternaam = new SimpleStringProperty();
    private SimpleStringProperty sPuntenAantal = new SimpleStringProperty();

    public Lid(String voornaam, String achternaam, LocalDate geboortedatum,
            String rijksregisterNr, LocalDate datumEersteTraining,
            String gsmNr, String vasteTelefoonNr, String stad, String straat,
            String huisNr, String postcode, String email,
            String wachtwoord, String geboorteplaats, String geslacht,
            String nationaliteit, Graad graad) {
        setVoornaam(voornaam);
        setAchternaam(achternaam);
        setGeboortedatum(geboortedatum);
        setRijksregisterNr(rijksregisterNr);
        setDatumEersteTraining(datumEersteTraining);
        setGsmNr(gsmNr);
        setVasteTelefoonNr(vasteTelefoonNr);
        setStad(stad);
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
        if (voornaam == null || voornaam.isEmpty()) {
            throw new IllegalArgumentException("Voornaam mag niet leeg zijn.");
        }
        if (voornaam.length() <= 25) {
            sVoornaam.set(voornaam);
        } else {
            throw new IllegalArgumentException("Voornaam mag max. 25 karakters bevatten.");
        }
    }

    public String getAchternaam() {
        return sAchternaam.get();
    }

    public void setAchternaam(String achternaam) {
        if (achternaam == null || achternaam.isEmpty()) {
            throw new IllegalArgumentException("Achternaam mag niet leeg zijn.");
        }
        if (achternaam.length() <= 50) {
            sAchternaam.set(achternaam);
        } else {
            throw new IllegalArgumentException("Familienaam mag max. 50 karakters bevatten.");
        }
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        if (geboortedatum == null) {
            throw new IllegalArgumentException("Geboortedatum mag niet leeg zijn.");
        }
        if (geboortedatum.compareTo(LocalDate.now()) < 0) {
            this.geboortedatum = geboortedatum;
        } else {
            throw new IllegalArgumentException("Geboortedatum moet in het verleden liggen!");
        }
    }

    public String getRijksregisterNr() {
        return rijksregisterNr;
    }

    public void setRijksregisterNr(String rijksregisterNr) {
//        String nrZonderTekens = rijksregisterNr.replaceAll(".", "").replaceAll("-", "");
//        String gebdatum = nrZonderTekens.substring(1, 7);
//        String geslacht = nrZonderTekens.substring(7, 10);
//        String controlegetal = nrZonderTekens.substring(10, 12);
//
//        boolean gebDatumCorrect = false;
//        boolean geslachtCorrect = Integer.valueOf(geslacht) % 2 == 0 ? this.geslacht.equalsIgnoreCase("VROUW") : this.geslacht.equalsIgnoreCase("MAN");
//        boolean controleCorrect = false;
//
//        //Checken of geboortedatumdeel correct is
//        if (String.valueOf(this.geboortedatum.getYear()).substring(3, 5).equals(gebdatum.substring(1, 3))) {
//            switch (this.geboortedatum.getMonthValue()) {
//                case 10:
//                case 11:
//                case 12:
//                    if (String.valueOf(this.geboortedatum.getMonthValue()).equals(gebdatum.substring(3, 5))) {
//                        switch (this.geboortedatum.getDayOfMonth()) {
//                            case 1:
//                            case 2:
//                            case 3:
//                            case 4:
//                            case 5:
//                            case 6:
//                            case 7:
//                            case 8:
//                            case 9:
//                                if (String.valueOf(this.geboortedatum.getDayOfMonth()).equals(gebdatum.substring(6, 7))) {
//                                    gebDatumCorrect = true;
//                                }
//                                break;
//                            default:
//                                if (String.valueOf(this.geboortedatum.getDayOfMonth()).equals(gebdatum.substring(5, 7))) {
//                                    gebDatumCorrect = true;
//                                }
//                        }
//                    }
//                    break;
//                default:
//                    if (String.valueOf(this.geboortedatum.getMonthValue()).equals(gebdatum.substring(4, 5))) {
//                        switch (this.geboortedatum.getDayOfMonth()) {
//                            case 1:
//                            case 2:
//                            case 3:
//                            case 4:
//                            case 5:
//                            case 6:
//                            case 7:
//                            case 8:
//                            case 9:
//                                if (String.valueOf(this.geboortedatum.getDayOfMonth()).equals(gebdatum.substring(5, 7))) {
//                                    gebDatumCorrect = true;
//                                }
//                                break;
//                            default:
//                                if (String.valueOf(this.geboortedatum.getDayOfMonth()).equals(gebdatum.substring(4, 7))) {
//                                    gebDatumCorrect = true;
//                                } else {
//                                    gebDatumCorrect = false;
//                                }
//                        }
//                    }
//            }
//
//        }
//        //Checken of controlegetal correct is
//        if (this.geboortedatum.getYear() < 2000) {
//            controleCorrect = Integer.valueOf(gebdatum.concat(geslacht)) % 97 == Integer.valueOf(controlegetal);
//        } else {
//            controleCorrect = Integer.valueOf("2".concat(gebdatum.concat(geslacht))) % 97 == Integer.valueOf(controlegetal);
//        }
//
//        if (gebDatumCorrect && geslachtCorrect && controleCorrect) {
//            this.rijksregisterNr = rijksregisterNr;
//        } else {
//            throw new IllegalArgumentException("Rijksregisternummer is niet correct.");
//        }
        this.rijksregisterNr = rijksregisterNr;
    }

    public LocalDate getDatumEersteTraining() {
        return datumEersteTraining;
    }

    public void setDatumEersteTraining(LocalDate datumEersteTraining) {
        if (datumEersteTraining == null) {
            throw new IllegalArgumentException("Datum eerste training mag niet leeg zijn.");
        }
        this.datumEersteTraining = datumEersteTraining;
    }

    public String getGsmNr() {
        return gsmNr;
    }

    public void setGsmNr(String gsmNr) {
        if (gsmNr == null || gsmNr.isEmpty()) {
            throw new IllegalArgumentException("Gsmnummer mag niet leeg zijn.");
        }
        if (gsmNr.matches("[0-9]{10}")) {
            this.gsmNr = gsmNr;
        } else {
            throw new IllegalArgumentException("Gsmnummer is niet correct.");
        }
    }

    public String getVasteTelefoonNr() {
        return vasteTelefoonNr;
    }

    public void setVasteTelefoonNr(String vasteTelefoonNr) {
        if (vasteTelefoonNr == null || vasteTelefoonNr.isEmpty()) {
            throw new IllegalArgumentException("Telefoonnummer mag niet leeg zijn.");
        }
        if (vasteTelefoonNr.matches("[0-9]{9}")) {
            this.vasteTelefoonNr = vasteTelefoonNr;
        } else {
            throw new IllegalArgumentException("Telefoonnummer is niet correct.");
        }
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        if (stad == null || stad.isEmpty()) {
            throw new IllegalArgumentException("Stad mag niet leeg zijn.");
        }
        if (stad.length() <= 50) {
            this.stad = stad;
        } else {
            throw new IllegalArgumentException("Stad mag max. 50 karakters bevatten.");
        }
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        if (straat == null || straat.isEmpty()) {
            throw new IllegalArgumentException("Straat mag niet leeg zijn.");
        }
        if (straat.length() <= 50) {
            this.straat = straat;
        } else {
            throw new IllegalArgumentException("Straat mag max. 50 karakters bevatten.");
        }
    }

    public String getHuisNr() {
        return huisNr;
    }

    public void setHuisNr(String huisNr) {
        if (huisNr == null || huisNr.isEmpty()) {
            throw new IllegalArgumentException("Huisnummer mag niet leeg zijn.");
        }
        if (huisNr.length() <= 5) {
            this.huisNr = huisNr;
        } else {
            throw new IllegalArgumentException("Huisnummer mag max. 5 karakters bevatten.");
        }
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        if (bus == null || bus.isEmpty()) {
            throw new IllegalArgumentException("Bus mag niet leeg zijn.");
        }
        if (bus.length() <= 5) {
            this.bus = bus;
        } else {
            throw new IllegalArgumentException("Bus mag max. 5 karakters bevatten.");
        }
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        if (postcode == null || postcode.isEmpty()) {
            throw new IllegalArgumentException("Postcode mag niet leeg zijn.");
        }
        if (postcode.matches("[0-9]{4}")) {
            this.postcode = postcode;
        } else {
            throw new IllegalArgumentException("Postcode moet 4 karakters bevatten.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Emailadres mag niet leeg zijn.");
        }
        if (email.matches("\\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Emailadres is niet correct.");
        }
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        if (wachtwoord == null || wachtwoord.isEmpty()) {
            throw new IllegalArgumentException("Wachtwoord mag niet leeg zijn.");
        }
        this.wachtwoord = wachtwoord;
    }

    public String getEmailVader() {
        return emailVader;
    }

    public void setEmailVader(String emailVader) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Emailadres mag niet leeg zijn.");
        }
        if (emailVader.matches("\\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b")) {
            this.emailVader = emailVader;
        } else {
            throw new IllegalArgumentException("Emailadres is niet correct.");
        }
    }

    public String getEmailMoeder() {
        return emailMoeder;
    }

    public void setEmailMoeder(String emailMoeder) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Emailadres mag niet leeg zijn.");
        }
        if (emailMoeder.matches("\\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b")) {
            this.emailMoeder = emailMoeder;
        } else {
            throw new IllegalArgumentException("Emailadres is niet correct.");
        }
    }

    public String getGeboorteplaats() {
        return geboorteplaats;
    }

    public void setGeboorteplaats(String geboorteplaats) {
        if (geboorteplaats == null || geboorteplaats.isEmpty()) {
            throw new IllegalArgumentException("Geboorteplaats mag niet leeg zijn.");
        }
        if (geboorteplaats.length() <= 50) {
            this.geboorteplaats = geboorteplaats;
        } else {
            throw new IllegalArgumentException("Geboorteplaats mag max. 50 karakters bevatten.");
        }
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        if (geslacht == null || geslacht.isEmpty()) {
            throw new IllegalArgumentException("Geslacht mag niet leeg zijn.");
        }
        if (geslacht.equalsIgnoreCase("MAN") || geslacht.equalsIgnoreCase("VROUW")) {
            this.geslacht = geslacht;
        } else {
            throw new IllegalArgumentException("Geslacht is niet correct.");
        }
    }

    public String getNationaliteit() {
        return nationaliteit;
    }

    public void setNationaliteit(String nationaliteit) {
        if (nationaliteit == null || nationaliteit.isEmpty()) {
            throw new IllegalArgumentException("Nationaliteit mag niet leeg zijn.");
        }
        if (nationaliteit.length() <= 50) {
            this.nationaliteit = nationaliteit;
        } else {
            throw new IllegalArgumentException("Nationaliteit mag max. 50 karakters bevatten.");
        }
    }

    public String getBeroep() {
        return beroep;
    }

    public void setBeroep(String beroep) {
        if (beroep == null || beroep.isEmpty()) {
            throw new IllegalArgumentException("Beroep mag niet leeg zijn.");
        }
        if (beroep.length() <= 25) {
            this.beroep = beroep;
        } else {
            throw new IllegalArgumentException("Beroep mag max. 25 karakters bevatten.");
        }
    }

    public Graad getGraad() {
        return graad;
    }

    public void setGraad(Graad graad) {
        if (graad == null) {
            throw new IllegalArgumentException("Graad mag niet leeg zijn.");
        }
        if (Arrays.asList(Graad.values()).contains(graad)) {
            this.graad = graad;
        } else {
            throw new IllegalArgumentException("Graad bestaatt niet.");
        }
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
