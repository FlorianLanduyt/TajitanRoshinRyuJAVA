package domein;

import domein.Lid;
import domein.enums.Formule;
import exceptions.VolzetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;

public class Activiteit {

    private String naam;
    private Formule formule;
    private LocalDate beginDatum;
    private LocalDate eindDatum;
    private String straat;
    private String stad;
    private String postcode;
    private String huisnummer;
    private String bus;
    private int maxDeelnemers;
    private int aantalDeelnemers;
    private boolean isVolzet;
    private List<Inschrijving> inschrijvingen;

    private final SimpleStringProperty sNaam = new SimpleStringProperty();
    private final SimpleStringProperty sFormule = new SimpleStringProperty();
    private final SimpleStringProperty sBeginDatum = new SimpleStringProperty();
    private final SimpleStringProperty sEindDatum = new SimpleStringProperty();
    private final SimpleStringProperty sStraat = new SimpleStringProperty();
    private final SimpleStringProperty sStad = new SimpleStringProperty();
    private final SimpleStringProperty sPostcode = new SimpleStringProperty();
    private final SimpleStringProperty sHuisnummer = new SimpleStringProperty();
    private final SimpleStringProperty sBus = new SimpleStringProperty();
    private final SimpleStringProperty sMaxDeelnemers = new SimpleStringProperty();
    private final SimpleStringProperty sAantalDeelnemers = new SimpleStringProperty();
    private final SimpleStringProperty sIsVolzet = new SimpleStringProperty();

    public Activiteit(String naam, Formule formule, int maxDeelnemers, LocalDate beginDatum) {
        setNaam(naam);
        setFormule(formule);
        setMaxDeelnemers(maxDeelnemers);
        setBeginDatum(beginDatum);
        inschrijvingen = new ArrayList<>();
    }

    public Activiteit(String naam, Formule formule, int maxDeelnemers, LocalDate beginDatum, LocalDate eindDatum) {
        this(naam, formule, maxDeelnemers, beginDatum);
        setEindDatum(eindDatum);
    }

    //Getters voor simplestringproperties
    public SimpleStringProperty naamProperty() {
        return sNaam;
    }

    public SimpleStringProperty formuleProperty() {
        return sFormule;
    }

    public SimpleStringProperty beginDatumProperty() {
        return sBeginDatum;
    }

    public SimpleStringProperty eindDatumProperty() {
        return sEindDatum;
    }

    public SimpleStringProperty straatProperty() {
        return sStraat;
    }

    public SimpleStringProperty stadProperty() {
        return sStad;
    }

    public SimpleStringProperty postcodeProperty() {
        return sPostcode;
    }

    public SimpleStringProperty huisnummerProperty() {
        return sHuisnummer;
    }

    public SimpleStringProperty busProperty() {
        return sBus;
    }

    public SimpleStringProperty maxDeelnemersProperty() {
        return sMaxDeelnemers;
    }

    public SimpleStringProperty aantalDeelnemersProperty() {
        return sAantalDeelnemers;
    }

    public SimpleStringProperty isVolzetProperty() {
        return sIsVolzet;
    }

    //Gewone getters en setters
    public String getNaam() {
        return sNaam.get();
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isEmpty()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn.");
        }
        if (naam.length() <= 35) {
            this.naam = naam;
            sNaam.set(naam);
        } else {
            throw new IllegalArgumentException("Naam mag max. 35 karakters bevatten.");
        }
    }

    public Formule getFormule() {
        return Formule.valueOf(sFormule.get());
    }

    public void setFormule(Formule formule) {
        if (formule == null) {
            throw new IllegalArgumentException("Formule mag niet leeg zijn.");
        }
        if (Arrays.asList(Formule.values()).contains(formule)) {
            this.formule = formule;
            sFormule.set(formule.name());
        } else {
            throw new IllegalArgumentException("Formule bestaat niet.");
        }
    }

    public LocalDate getBeginDatum() {
        return LocalDate.parse(sBeginDatum.get());
    }

    public void setBeginDatum(LocalDate beginDatum) {
        if (beginDatum == null) {
            throw new IllegalArgumentException("Begindatum mag niet leeg zijn.");
        } else {
            this.beginDatum = beginDatum;
            sBeginDatum.set(beginDatum.toString());
        }
    }

    public LocalDate getEindDatum() {
        return LocalDate.parse(sEindDatum.get());
    }

    public void setEindDatum(LocalDate eindDatum) {
        if (eindDatum == null) {
            throw new IllegalArgumentException("Einddatum mag niet leeg zijn.");
        } else {
            this.eindDatum = eindDatum;
            sEindDatum.set(eindDatum.toString());
        }
    }

    public String getStraat() {
        return sStraat.get();
    }

    public void setStraat(String straat) {
        if (straat == null || straat.isEmpty()) {
            throw new IllegalArgumentException("Straat mag niet leeg zijn.");
        }
        if (straat.length() <= 50) {
            this.straat = straat;
            sStraat.set(straat);
        } else {
            throw new IllegalArgumentException("Straat mag max. 50 karakters bevatten.");
        }
    }

    public String getStad() {
        return sStad.get();
    }

    public void setStad(String stad) {
        if (stad == null || stad.isEmpty()) {
            throw new IllegalArgumentException("Stad mag niet leeg zijn.");
        }
        if (stad.length() <= 50) {
            this.stad = stad;
            sStad.set(stad);
        } else {
            throw new IllegalArgumentException("Stad mag max. 50 karakters bevatten.");
        }
    }

    public String getPostcode() {
        return sPostcode.get();
    }

    public void setPostcode(String postcode) {
        if (postcode == null || postcode.isEmpty()) {
            throw new IllegalArgumentException("Postcode mag niet leeg zijn.");
        }
        if (postcode.matches("[0-9]{4}")) {
            this.postcode = postcode;
            sPostcode.set(postcode);
        } else {
            throw new IllegalArgumentException("Postcode moet 4 karakters bevatten.");
        }
    }

    public String getHuisnummer() {
        return sHuisnummer.get();
    }

    public void setHuisnummer(String huisnummer) {
        if (huisnummer == null || huisnummer.isEmpty()) {
            throw new IllegalArgumentException("Huisnummer mag niet leeg zijn.");
        }
        if (huisnummer.length() <= 5) {
            this.huisnummer = huisnummer;
            sHuisnummer.set(huisnummer);
        } else {
            throw new IllegalArgumentException("Huisnummer mag max. 5 karakters bevatten.");
        }
    }

    public String getBus() {
        return sBus.get();
    }

    public void setBus(String bus) {
        if (bus == null || bus.isEmpty()) {
            throw new IllegalArgumentException("Bus mag niet leeg zijn.");
        }
        if (bus.length() <= 5) {
            this.bus = bus;
            sBus.set(bus);
        } else {
            throw new IllegalArgumentException("Bus mag max. 5 karakters bevatten.");
        }
    }

    public int getMaxDeelnemers() {
        return Integer.valueOf(sMaxDeelnemers.get());
    }

    public void setMaxDeelnemers(int maxDeelnemers) {
        if (String.valueOf(maxDeelnemers).isEmpty() || String.valueOf(maxDeelnemers).equals("")) {
            throw new IllegalArgumentException("Max. aantal deelnemers mag niet leeg zijn.");
        } else {
            this.maxDeelnemers = maxDeelnemers;
            sMaxDeelnemers.set(String.valueOf(maxDeelnemers));
        }
    }

    public int getAantalDeelnemers() {
        setAantalDeelnemers();
        return Integer.valueOf(sAantalDeelnemers.get());
    }

    public void setAantalDeelnemers() {
        int aantal = (int) inschrijvingen.stream().count();
        this.aantalDeelnemers = aantal;
        sAantalDeelnemers.set(String.valueOf(aantal));
    }

    public void setVolzet() {
        Boolean volzet = this.maxDeelnemers == getAantalDeelnemers();
        this.isVolzet = volzet;
        sIsVolzet.set(String.valueOf(volzet));
    }

    public boolean isVolzet() {
        setVolzet();
        return Boolean.valueOf(sIsVolzet.get());
    }

    //
    //Methods
    //
    public List<Inschrijving> getInschrijvingen() {
        return inschrijvingen;
    }

    public void voegInschrijvingToe(Inschrijving inschrijving) {
        if (isVolzet()) {
            throw new VolzetException("Deze activiteit is volzet.");
        } else {
            this.inschrijvingen.add(inschrijving);
            setAantalDeelnemers();
        }
    }

    public void verwijderInschrijving(Inschrijving inschrijving) {
        this.inschrijvingen.remove(inschrijving);
        setAantalDeelnemers();
    }

}
