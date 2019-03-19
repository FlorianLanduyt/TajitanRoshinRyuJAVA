package domein;

import domein.enums.Functie;
import domein.enums.Graad;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Lid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private int puntenAantal;

    @Enumerated(EnumType.STRING)
    private Graad graad;

    @Enumerated(EnumType.STRING)
    private Functie functie; // dit is hetzelfde als type!!!!

    //SimpleStringProperties voor TableView
    @Transient
    private SimpleStringProperty sVoornaam = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sAchternaam = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sPuntenAantal = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sGraad = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sType = new SimpleStringProperty();

    public Lid() {
    }

    public Lid(String voornaam, String achternaam, LocalDate geboortedatum,
            String rijksregisterNr,
            String gsmNr, String vasteTelefoonNr, String stad, String straat,
            String huisNr, String postcode, String email,
            String wachtwoord, String geboorteplaats, String geslacht,
            String nationaliteit, Graad graad, Functie functie) {
        setVoornaam(voornaam);
        setAchternaam(achternaam);
        setGeboortedatum(geboortedatum);
        setGeslacht(geslacht);
        setRijksregisterNr(rijksregisterNr);
        setGsmNr(gsmNr);
        setVasteTelefoonNr(vasteTelefoonNr);
        setStad(stad);
        setStraat(straat);
        setHuisNr(huisNr);
        setPostcode(postcode);
        setEmail(email);
        setWachtwoord(wachtwoord);
        setGeboorteplaats(geboorteplaats);
        setNationaliteit(nationaliteit);
        setGraad(graad);
        setFunctie(functie);
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

    public SimpleStringProperty graadProperty() {
        return sGraad;
    }

    public SimpleStringProperty typeProperty() {
        return sType;
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
            this.voornaam = voornaam;
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
            this.achternaam = achternaam;
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
        if (rijksregisterNr == null || rijksregisterNr.isEmpty()) {
            throw new IllegalArgumentException("Rijksregisternummer mag niet leeg zijn");
        }

        String nrZonderTekens = rijksregisterNr.replaceAll("[.]", "").replaceAll("-", "");
        String gebdatum = nrZonderTekens.substring(0, 6);
        String geslacht = nrZonderTekens.substring(6, 9);
        String controlegetal = nrZonderTekens.substring(9, 11);

        boolean gebDatumCorrect = false;
        boolean controleCorrect = false;
        //Geslacht checken
        boolean geslachtCorrect = Integer.valueOf(geslacht) % 2 == 0 ? this.geslacht.equalsIgnoreCase("VROUW") : this.geslacht.equalsIgnoreCase("MAN");

        //Checken of geboortedatumdeel correct is
        if (String.valueOf(this.geboortedatum.getYear()).substring(2, 4).equals(gebdatum.substring(0, 2))) {
            switch (this.geboortedatum.getMonthValue()) {
                case 10:
                case 11:
                case 12:
                    if (String.valueOf(this.geboortedatum.getMonthValue()).equals(gebdatum.substring(2, 4))) {
                        switch (this.geboortedatum.getDayOfMonth()) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                                gebDatumCorrect = String.valueOf(this.geboortedatum.getDayOfMonth()).equals(gebdatum.substring(5, 6));
                                break;
                            default:
                                gebDatumCorrect = String.valueOf(this.geboortedatum.getDayOfMonth()).equals(gebdatum.substring(4, 6));
                        }
                    }
                    break;
                default:
                    if (String.valueOf(this.geboortedatum.getMonthValue()).equals(gebdatum.substring(3, 4))) {
                        switch (this.geboortedatum.getDayOfMonth()) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                                gebDatumCorrect = String.valueOf(this.geboortedatum.getDayOfMonth()).equals(gebdatum.substring(5, 6));
                                break;
                            default:
                                gebDatumCorrect = String.valueOf(this.geboortedatum.getDayOfMonth()).equals(gebdatum.substring(4, 6));
                        }
                    }
            }
        }
        //Controlegetal check
        if (geboortedatum.getYear() < 2000) {
            controleCorrect = (97 - (Integer.valueOf(gebdatum.concat(geslacht)) % 97)) == Integer.valueOf(controlegetal);
        } else {
            controleCorrect = (97 - (Long.valueOf("2".concat(gebdatum.concat(geslacht))) % 97)) == Integer.valueOf(controlegetal);
        }
        //Alle booleans checken
        if (gebDatumCorrect && geslachtCorrect && controleCorrect) {
            this.rijksregisterNr = rijksregisterNr;
        } else {
            throw new IllegalArgumentException("Rijksregisternummer is niet correct.");
        }
    }

    public String getGsmNr() {
        return gsmNr;
    }

    public void setGsmNr(String gsmNr) {
        if (gsmNr == null || gsmNr.isEmpty()) {
            throw new IllegalArgumentException("Gsmnummer mag niet leeg zijn.");
        }
        if (gsmNr.matches("(([+]32){1}[0-9]{9})|([0-9]{10})")) {
            this.gsmNr = gsmNr;
        } else {
            throw new IllegalArgumentException("Gsmnummer is niet correct.");
        }
    }

    public String getVasteTelefoonNr() {
        return vasteTelefoonNr;
    }

    public void setVasteTelefoonNr(String vasteTelefoonNr) {
        if (vasteTelefoonNr != null) {
            if (vasteTelefoonNr.isEmpty() || vasteTelefoonNr.equals("")) {
                this.vasteTelefoonNr = "";
            } else if (vasteTelefoonNr.matches("[0-9]{9}")) {
                this.vasteTelefoonNr = vasteTelefoonNr;
            } else {
                throw new IllegalArgumentException("Telefoonnummer is niet correct.");
            }
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
        if (bus != null) {
            if (bus.length() <= 5) {
                this.bus = bus;
            } else {
                throw new IllegalArgumentException("Bus mag max. 5 karakters bevatten.");
            }
        } else {
            this.bus = null;
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
        } else if (email.matches("\\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b")) {
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
        } else {
            this.wachtwoord = wachtwoord;
        }
    }

    public String getEmailVader() {
        return emailVader;
    }

    public void setEmailVader(String emailVader) {
        if (emailVader != null) {
            if (emailVader.isEmpty() || emailVader.equals("")) {
                this.emailVader = "";
            } else {
                if (emailVader.matches("\\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b")) {
                    this.emailVader = emailVader;
                } else {
                    throw new IllegalArgumentException("Emailadres vader is niet correct.");
                }
            }

        } else {
            this.emailVader = "";
        }

    }

    public String getEmailMoeder() {
        return emailMoeder;
    }

    public void setEmailMoeder(String emailMoeder) {
        if (emailMoeder != null) {
            if (emailMoeder.isEmpty() || emailMoeder.equals("")) {
                this.emailMoeder = "";
            } else {
                if (emailMoeder.matches("\\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b")) {
                    this.emailMoeder = emailMoeder;
                } else {
                    throw new IllegalArgumentException("Emailadres moeder is niet correct.");
                }
            }

        } else {
            this.emailMoeder = "";
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
        if (beroep != null) {
            if (beroep.length() <= 50) {
                this.beroep = beroep;
            } else {
                throw new IllegalArgumentException("Beroep mag max. 50 karakters bevatten.");
            }
        } else {
            this.beroep = null;
        }

    }

    public int getPuntenAantal() {
        return Integer.valueOf(sPuntenAantal.get());
    }

    public void setPuntenAantal(int puntenAantal) {
        if (puntenAantal < 0) {
            throw new IllegalArgumentException("Puntenaantal mag niet negatief zijn");
        } else {
            this.puntenAantal = puntenAantal;
            sPuntenAantal.set(String.valueOf(puntenAantal));
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
            sGraad.set(graad.name());
        } else {
            throw new IllegalArgumentException("Graad bestaat niet.");
        }
    }

    public Functie getFunctie() {
        return functie;
    }

    public void setFunctie(Functie functie) {
        if (functie == null) {
            throw new IllegalArgumentException("Functie mag niet leeg zijn.");
        }
        if (Arrays.asList(Functie.values()).contains(functie)) {
            this.functie = functie;
            sType.set(functie.name());
        } else {
            throw new IllegalArgumentException("Functie bestaat niet.");
        }
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
    public boolean equals(Object obj
    ) {
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

    public String geefVolledigeNaam() {
        return voornaam + " " + achternaam;
    }

}
