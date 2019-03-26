package domein;

import domein.Lid;
import domein.enums.Formule;
import exceptions.DatumIntervalException;
import exceptions.VolzetException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
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

    public void setSimpleStringProperties() {
        sNaam.set(getNaam());
        sFormule.set(getFormule().name());
        sBeginDatum.set(getBeginDatum().toString());
        if (eindDatum != null) {
            sEindDatum.set(getEindDatum().toString());
        }
        sUitersteInschrijvingsDatum.set(getUitersteInschrijvingsDatum().toString());
        sStraat.set(getStraat());
        sStad.set(getStad());
        sPostcode.set(getPostcode());
        sHuisnummer.set(getHuisnummer());
        sBus.set(getBus());
        sMaxDeelnemers.set(String.valueOf(getMaxDeelnemers()));
        sAantalDeelnemers.set(String.valueOf(getAantalDeelnemers()));
        sIsVolzet.set(this.isVolzet == false ? "Nee" : "Ja");
        sNaamLocatie.set(getNaamLocatie());
        sGsmnummer.set(getGsmnummer());
        sEmail.set(getEmail());
    }

    //Gewone getters en setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isEmpty()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn.");
        }
        naam = naam.trim();
        if (naam.length() > 35) {
            throw new IllegalArgumentException("Naam mag max. 35 karakters bevatten.");
        }
        if (naam.contains(" ")) {
            String tempNaam = naam.replaceAll(" ", "");
            if (tempNaam.matches(".*[\\d\\W].*")) {
                throw new InputMismatchException("Naam mag enkel letters bevatten.");
            }
        } else {
            if (naam.matches(".*[\\d\\W].*")) {
                throw new InputMismatchException("Naam mag enkel letters bevatten.");
            }
        }
        this.naam = naam;
        sNaam.set(naam);
    }

    public Formule getFormule() {
        return this.formule;
    }

    public void setFormule(Formule formule) {
        if (formule == null) {
            throw new IllegalArgumentException("Formule mag niet leeg zijn.");
        }
        if (!Arrays.asList(Formule.values()).contains(formule)) {
            throw new IllegalArgumentException("Formule bestaat niet.");
        }
        this.formule = formule;
        sFormule.set(formule.name());
    }

    public LocalDate getBeginDatum() {
        return this.beginDatum;
    }

    public void setBeginDatum(LocalDate beginDatum) {
        if (beginDatum == null) {
            throw new IllegalArgumentException("Begindatum mag niet leeg zijn.");
        } else if (eindDatum != null) {
            if (beginDatum.compareTo(this.eindDatum) > 0) {
                throw new DatumIntervalException("Begindatum mag niet na einddatum liggen.");
            }
        }
        this.beginDatum = beginDatum;
        sBeginDatum.set(beginDatum.toString());
    }

    public LocalDate getEindDatum() {
        return this.eindDatum;
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
        return this.uitersteInschrijvingsDatum;
    }

    public void setUitersteInschrijvingsDatum(LocalDate uitersteInschrijvingsDatum) {
        if (uitersteInschrijvingsDatum == null) {
            throw new IllegalArgumentException("Uiterste inschrijvingsdatum mag niet leeg zijn.");
        } else if (uitersteInschrijvingsDatum.compareTo(LocalDate.now()) < 0) {
            throw new IllegalArgumentException("Uiterste inschrijvingsdatum mag niet in het verleden liggen.");
        } else if (this.beginDatum.compareTo(uitersteInschrijvingsDatum) < 0) {
            throw new IllegalArgumentException("Uiterste inschrijvingsdatum mag niet na begindatum liggen.");
        }
        this.uitersteInschrijvingsDatum = uitersteInschrijvingsDatum;
        sUitersteInschrijvingsDatum.set(uitersteInschrijvingsDatum.toString());
    }

    public String getNaamLocatie() {
        return this.naamLocatie;
    }

    public void setNaamLocatie(String naamLocatie) {
        if (naamLocatie == null || naamLocatie.isEmpty()) {
            throw new IllegalArgumentException("Naam locatie mag niet leeg zijn.");
        }
        naamLocatie = naamLocatie.trim();
        if (naamLocatie.length() > 50) {
            throw new IllegalArgumentException("Naam locatie mag max. 50 karakters bevatten.");
        }
        if (naamLocatie.contains(" ")) {
            String tempNaamLocatie = naamLocatie.replaceAll(" ", "");
            if (tempNaamLocatie.matches(".*[\\d\\W].*")) {
                throw new InputMismatchException("Naam locatie mag enkel letters bevatten.");
            }
        } else {
            if (naamLocatie.matches(".*[\\d\\W].*")) {
                throw new InputMismatchException("Naam locatie mag enkel letters bevatten.");
            }
        }
        this.naamLocatie = naamLocatie;
        sNaamLocatie.set(naamLocatie);
    }

    public String getGsmnummer() {
        return this.gsmnummer;
    }

    public void setGsmnummer(String gsmNr) {
        if (gsmNr == null || gsmNr.isEmpty()) {
            throw new IllegalArgumentException("GSM-nummer mag niet leeg zijn.");
        }
        gsmNr = gsmNr.trim();
        if (gsmNr.contains(" ")) {
            String tempGsmNr = gsmNr.replaceAll(" ", "");
            if (gsmNr.charAt(0) == '+') {
                String tempGsmNrWithoutSpaces = gsmNr.replace(" ", "");
                if (tempGsmNrWithoutSpaces.matches(".*[a-zA-Z\\W].*")) {
                    throw new InputMismatchException("GSM-nummer mag enkel cijfers of +32 gevolgd door cijfers bevatten");
                }
            } else {
                if (tempGsmNr.matches(".*[a-zA-Z\\W].*")) {
                    throw new InputMismatchException("GSM-nummer mag enkel cijfers of +32 gevolgd door cijfers bevatten");
                }
            }
            if (!tempGsmNr.matches("(([+]32){1}[0-9]{9})|([0-9]{10})")) {
                throw new IllegalArgumentException("GSM-nummer is niet correct.");
            }
        } else {
            if (gsmNr.charAt(0) == '+') {
                String tempGsmNr = gsmNr.replace("+", "");
                if (tempGsmNr.matches(".*[a-zA-Z\\W].*")) {
                    throw new InputMismatchException("GSM-nummer mag enkel cijfers of +32 gevolgd door cijfers bevatten");
                }
            } else {
                if (gsmNr.matches(".*[a-zA-Z\\W].*")) {
                    throw new InputMismatchException("GSM-nummer mag enkel cijfers of +32 gevolgd door cijfers bevatten");
                }
            }
            if (!gsmNr.matches("(([+]32){1}[0-9]{9})|([0-9]{10})")) {
                throw new IllegalArgumentException("GSM-nummer is niet correct.");
            }
        }
        this.gsmnummer = gsmNr;
        sGsmnummer.set(gsmNr);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Emailadres mag niet leeg zijn.");
        }
        email = email.trim();
        if (!email.matches("\\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b")) {
            throw new IllegalArgumentException("Emailadres is niet correct.");
        }
        this.email = email;
        sEmail.set(email);
    }

    public String getStraat() {
        return this.straat;
    }

    public void setStraat(String straat) {
        if (straat == null || straat.isEmpty()) {
            throw new IllegalArgumentException("Straat mag niet leeg zijn.");
        }
        straat = straat.trim();
        if (straat.length() > 50) {
            throw new IllegalArgumentException("Straat mag max. 50 karakters bevatten.");
        }
        if (straat.contains(" ")) {
            String tempStraat = straat.replaceAll(" ", "");
            if (tempStraat.matches(".*[\\d\\W].*")) {
                throw new InputMismatchException("Straat mag enkel letters bevatten.");
            }
        } else {
            if (straat.matches(".*[\\d\\W].*")) {
                throw new InputMismatchException("Straat mag enkel letters bevatten.");
            }
        }
        this.straat = straat;
        sStraat.set(straat);
    }

    public String getStad() {
        return this.stad;
    }

    public void setStad(String stad) {
        if (stad == null || stad.isEmpty()) {
            throw new IllegalArgumentException("Stad mag niet leeg zijn.");
        }
        stad = stad.trim();
        if (stad.length() > 50) {
            throw new IllegalArgumentException("Stad mag max. 50 karakters bevatten.");
        }
        if (stad.contains(" ")) {
            String tempStad = stad.replaceAll(" ", "");
            if (tempStad.matches(".*[\\d\\W].*")) {
                throw new InputMismatchException("Stad mag enkel letters bevatten.");
            }
        } else {
            if (stad.matches(".*[\\d\\W].*")) {
                throw new InputMismatchException("Stad mag enkel letters bevatten.");
            }
        }
        this.stad = stad;
        sStad.set(stad);
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        if (postcode == null || postcode.isEmpty()) {
            throw new IllegalArgumentException("Postcode mag niet leeg zijn.");
        }
        postcode = postcode.trim();
        if (!postcode.matches("[0-9]{4}")) {
            throw new IllegalArgumentException("Postcode moet 4 cijfers bevatten.");
        }
        this.postcode = postcode;
        sPostcode.set(postcode);
    }

    public String getHuisnummer() {
        return this.huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        if (huisnummer == null || huisnummer.isEmpty()) {
            throw new IllegalArgumentException("Huisnummer mag niet leeg zijn.");
        }
        huisnummer = huisnummer.trim();
        if (huisnummer.length() > 5) {
            throw new IllegalArgumentException("Huisnummer mag max. 5 karakters bevatten.");
        }
        if (!huisnummer.matches("\\d{1,5}")) {
            throw new InputMismatchException("Huisnummer mag geen letters/symbolen bevatten.");
        }
        this.huisnummer = huisnummer;
        sHuisnummer.set(huisnummer);
    }

    public String getBus() {
        return this.bus;
    }

    public void setBus(String bus) {
        if (bus != null) {
            if (bus.length() > 5) {
                throw new IllegalArgumentException("Bus mag max. 5 karakters bevatten.");
            }
            this.bus = bus;
            sBus.set(bus);
        } else {
            this.bus = null;
        }
    }

    public int getMaxDeelnemers() {
        return this.maxDeelnemers;
    }

    public void setMaxDeelnemers(int maxDeelnemers) {
        if (String.valueOf(maxDeelnemers).isEmpty() || String.valueOf(maxDeelnemers).equals("")) {
            throw new IllegalArgumentException("Max. aantal deelnemers mag niet leeg zijn.");
        }
        if (!String.valueOf(maxDeelnemers).matches("\\d+")) {
            throw new NumberFormatException("Max. aantal deelnemers mag enkel cijfers bevatten.");
        }
        this.maxDeelnemers = maxDeelnemers;
        sMaxDeelnemers.set(String.valueOf(maxDeelnemers));
    }

    public int getAantalDeelnemers() {
        setAantalDeelnemers();
        return this.aantalDeelnemers;
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
        return this.isVolzet;
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
}
