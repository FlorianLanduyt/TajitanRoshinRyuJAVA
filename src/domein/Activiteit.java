package domein;

import domein.Lid;
import domein.enums.Formule;
import java.time.LocalDate;
import java.util.ArrayList;
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
        this.naam = naam;
        sNaam.set(naam);
    }

    public Formule getFormule() {
        return Formule.valueOf(sFormule.get());
    }

    public void setFormule(Formule formule) {
        this.formule = formule;
        sFormule.set(formule.name());
    }

    public LocalDate getBeginDatum() {
        return LocalDate.parse(sBeginDatum.get());
    }

    public void setBeginDatum(LocalDate beginDatum) {
        this.beginDatum = beginDatum;
        sBeginDatum.set(beginDatum.toString());
    }

    public LocalDate getEindDatum() {
        return LocalDate.parse(sEindDatum.get());
    }

    public void setEindDatum(LocalDate eindDatum) {
        this.eindDatum = eindDatum;
        sEindDatum.set(eindDatum.toString());
    }

    public String getStraat() {
        return sStraat.get();
    }

    public void setStraat(String straat) {
        this.straat = straat;
        sStraat.set(straat);
    }

    public String getStad() {
        return sStad.get();
    }

    public void setStad(String stad) {
        this.stad = stad;
        sStad.set(stad);
    }

    public String getPostcode() {
        return sPostcode.get();
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
        sPostcode.set(postcode);
    }

    public String getHuisnummer() {
        return sHuisnummer.get();
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
        sHuisnummer.set(huisnummer);
    }

    public String getBus() {
        return sBus.get();
    }

    public void setBus(String bus) {
        this.bus = bus;
        sBus.set(bus);
    }

    public int getMaxDeelnemers() {
        return Integer.valueOf(sMaxDeelnemers.get());
    }

    public void setMaxDeelnemers(int maxDeelnemers) {
        this.maxDeelnemers = maxDeelnemers;
        sMaxDeelnemers.set(String.valueOf(maxDeelnemers));
    }

    public int getAantalDeelnemers() {
        return Integer.valueOf(sAantalDeelnemers.get());
    }

    public void setAantalDeelnemers() {
        Number aantal = inschrijvingen.stream().count();
        this.aantalDeelnemers = (int) aantal;
        sAantalDeelnemers.set(String.valueOf(aantal));
    }

    public void setVolzet() {
        Boolean volzet = this.maxDeelnemers == this.aantalDeelnemers;
        this.isVolzet = volzet;
        sIsVolzet.set(String.valueOf(volzet));
    }

    //
    //Methods
    //
    public List<Inschrijving> getInschrijvingen() {
        return inschrijvingen;
    }

    public void voegInschrijvingToe(Inschrijving inschrijving) {
        this.inschrijvingen.add(inschrijving);
    }

    public void verwijderInschrijving(Inschrijving inschrijving) {
        this.inschrijvingen.remove(inschrijving);
    }

    public boolean isVolzet() {
        return Boolean.valueOf(sIsVolzet.get());
    }

}
