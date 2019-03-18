package domein;

import domein.Lid;
import domein.enums.Formule;
import exceptions.DatumIntervalException;
import exceptions.VolzetException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Activiteit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String naam;

    @Enumerated(EnumType.STRING)
    private Formule formule;
    @Temporal()
    private LocalDate beginDatum;

    private LocalDate eindDatum;

    private LocalDate uitersteInschrijvingsDatum;

    //info locatie
    private String naamLocatie;
    private String gsmnummer;
    private String email;
    private String straat;
    private String stad;
    private String postcode;
    private String huisnummer;
    private String bus;

    private int maxDeelnemers;
    private int aantalDeelnemers;
    private boolean isVolzet;
    @ManyToMany
    private List<Inschrijving> inschrijvingen;

    @Transient
    private final SimpleStringProperty sNaam = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sFormule = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sBeginDatum = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sEindDatum = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sUitersteInschrijvingsDatum = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sStraat = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sStad = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sPostcode = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sHuisnummer = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sBus = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sMaxDeelnemers = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sAantalDeelnemers = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sIsVolzet = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sNaamLocatie = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sGsmnummer = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty sEmail = new SimpleStringProperty();

    public Activiteit() {
    }

    public Activiteit(String naam, Formule formule, int maxDeelnemers, LocalDate beginDatum, LocalDate uitersteInschrijvingsDatum) {
        setNaam(naam);
        setFormule(formule);
        setMaxDeelnemers(maxDeelnemers);
        setBeginDatum(beginDatum);
        setUitersteInschrijvingsDatum(uitersteInschrijvingsDatum);
        inschrijvingen = new ArrayList<>();
        this.setVolzet();
    }

    public Activiteit(String naam, Formule formule, int maxDeelnemers, LocalDate beginDatum, LocalDate eindDatum, LocalDate uitersteInschrijvingsDatum) {
        this(naam, formule, maxDeelnemers, beginDatum, uitersteInschrijvingsDatum);
        setEindDatum(eindDatum);
        this.setVolzet();
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

    public SimpleStringProperty uitersteInschrijvingsDatumProperty() {
        return sUitersteInschrijvingsDatum;
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

    public SimpleStringProperty naamLocatieProperty() {
        return sNaamLocatie;
    }

    public SimpleStringProperty gsmNummerLocatieProperty() {
        return sGsmnummer;
    }

    public SimpleStringProperty emailLocatieProperty() {
        return sEmail;
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
        } else if (eindDatum != null) {
            if (beginDatum.compareTo(this.eindDatum) > 0) {
                throw new DatumIntervalException("Begindatum mag niet na einddatum liggen.");
            }
        } else {
            this.beginDatum = beginDatum;
            sBeginDatum.set(beginDatum.toString());
        }
    }

    public LocalDate getEindDatum() {
        if (sEindDatum.get() != null) {
            return LocalDate.parse(sEindDatum.get());
        }
        return null;

    }

    public void setEindDatum(LocalDate eindDatum) {
        if (eindDatum != null) {
            if (eindDatum.compareTo(this.beginDatum) < 0) {
                throw new DatumIntervalException("Einddatum mag niet voor begindatum liggen.");
            } else {
                this.eindDatum = eindDatum;
                sEindDatum.set(eindDatum.toString());
            }
        } else {
            this.eindDatum = null;
        }
    }

    public LocalDate getUitersteInschrijvingsDatum() {
        return LocalDate.parse(sUitersteInschrijvingsDatum.get());
    }

    public void setUitersteInschrijvingsDatum(LocalDate uitersteInschrijvingsDatum) {
        if (uitersteInschrijvingsDatum == null) {
            throw new IllegalArgumentException("Uiterste inschrijvingsdatum mag niet leeg zijn.");
        } else if (uitersteInschrijvingsDatum.compareTo(LocalDate.now()) < 0) {
            throw new IllegalArgumentException("Uiterste inschrijvingsdatum mag niet in het verleden liggen.");
        } else if (this.beginDatum.compareTo(uitersteInschrijvingsDatum) < 0) {
            throw new IllegalArgumentException("Uiterste inschrijvingsdatum mag niet na begindatum liggen.");
        } else {
            this.uitersteInschrijvingsDatum = uitersteInschrijvingsDatum;
            sUitersteInschrijvingsDatum.set(uitersteInschrijvingsDatum.toString());
        }
    }

    public String getNaamLocatie() {
        return sNaamLocatie.get();
    }

    public void setNaamLocatie(String naamLocatie) {
        if (naamLocatie == null || naamLocatie.isEmpty()) {
            throw new IllegalArgumentException("Naam locatie mag niet leeg zijn.");
        }
        if (naamLocatie.length() <= 50) {
            this.naamLocatie = naamLocatie;
            sNaamLocatie.set(naamLocatie);
        } else {
            throw new IllegalArgumentException("Naam locatie mag max. 50 karakters bevatten.");
        }
    }

    public String getGsmnummer() {
        return sGsmnummer.get();
    }

    public void setGsmnummer(String gsmNr) {
        if (gsmNr == null || gsmNr.isEmpty()) {
            throw new IllegalArgumentException("GSM-nummer mag niet leeg zijn.");
        }
        if (gsmNr.matches("(([+]32){1}[0-9]{9})|([0-9]{10})")) {
            this.gsmnummer = gsmNr;
            sGsmnummer.set(gsmNr);
        } else {
            throw new IllegalArgumentException("GSM-nummer is niet correct.");
        }
    }

    public String getEmail() {
        return sEmail.get();
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Emailadres mag niet leeg zijn.");
        } else if (email.matches("\\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b")) {
            this.email = email;
            sEmail.set(email);
        } else {
            throw new IllegalArgumentException("Emailadres is niet correct.");
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
        if (bus != null) {
            if (bus.length() <= 5) {
                this.bus = bus;
                sBus.set(bus);
            } else {
                throw new IllegalArgumentException("Bus mag max. 5 karakters bevatten.");
            }
        } else {
            this.bus = null;
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
        sIsVolzet.set(volzet == false ? "Nee" : "Ja"); //vooraf -> String.valueOf(volzet);

    }

    public boolean isVolzet() {
        setVolzet();
        return sIsVolzet.get().equals("Ja") ? true : false;
    }

    //
    //Methods
    //
    public List<Inschrijving> getInschrijvingen() {
        return inschrijvingen;
    }

    public void voegInschrijvingToe(Inschrijving inschrijving) {
        if (this.inschrijvingen.contains(inschrijving)) {
            throw new IllegalArgumentException("Inschrijving is al toegevoegd aan deze activiteit.");
        } else if (isVolzet()) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
