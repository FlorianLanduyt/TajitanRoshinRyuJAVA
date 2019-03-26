package domein.controllers;

import javafx.collections.ObservableList;
import domein.Activiteit;
import domein.Inschrijving;
import domein.Lid;
import domein.enums.Formule;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class ActiviteitBeheerController {

    private DataController dataController;
    private ObservableList<Activiteit> activiteitenList;
    private FilteredList<Activiteit> filteredActiviteitenList;
    private SortedList<Activiteit> sortedActiviteitenList;
    private final Comparator<Activiteit> byDate = (p1, p2) -> p1.getBeginDatum().compareTo(p2.getBeginDatum());
    private final Comparator<Activiteit> byNaam = (p1, p2) -> p1.getNaam().compareTo(p2.getNaam());
    private final Comparator<Activiteit> sortOrder = byDate.reversed().thenComparing(byNaam);
    
    private ObservableList<Inschrijving> inschrijvingenActiviteit;
    private SortedList<Inschrijving> inschrijvingenActiviteitSorted;
    private Comparator<Inschrijving> byFamilienaamInschrijving = (l1, l2) -> l1.getLid().getAchternaam().compareTo(l2.getLid().getAchternaam());
    private Comparator<Inschrijving> byVoornaamInschrijving = (l1, l2) -> l1.getLid().getVoornaam().compareTo(l2.getLid().getVoornaam());
    private Comparator<Inschrijving> sortOrderInschrijving = byFamilienaamInschrijving.thenComparing(byVoornaamInschrijving);
    
    
    private ObservableList<Lid> nogNietIngeschrevenLeden;
    private FilteredList<Lid> nogNietIngeschrevenLedenFiltered;
    private SortedList<Lid> nogNietIngeschrevenLedenSorted;
    private final Comparator<Lid> byFamilienaam = (l1, l2) -> l1.getAchternaam().compareTo(l2.getAchternaam());
    private final Comparator<Lid> byVoornaam = (l1, l2) -> l1.getVoornaam().compareTo(l2.getVoornaam());
    private final Comparator<Lid> sortOrderLeden = byFamilienaam.thenComparing(byVoornaam);

    

    public ActiviteitBeheerController() {
        dataController = new DataController();
        activiteitenList = FXCollections.observableArrayList(dataController.geefActiviteiten());
        filteredActiviteitenList = new FilteredList(activiteitenList, p -> true);
        sortedActiviteitenList = new SortedList(filteredActiviteitenList, sortOrder);
        
        inschrijvingenActiviteit = FXCollections.observableArrayList();
        nogNietIngeschrevenLeden = FXCollections.observableArrayList();
        
    }

    //
    //Overzicht van activiteiten
    //
    public ObservableList<Activiteit> geefAlleActiviteiten() {
        return FXCollections.unmodifiableObservableList(activiteitenList);
    }

    public ObservableList<Activiteit> geefObservableListActiviteiten() {
        return FXCollections.unmodifiableObservableList(sortedActiviteitenList);
    }
    
    public ObservableList<Inschrijving> geefInschrijvingenVanActiviteit(Activiteit activiteit){
        inschrijvingenActiviteit = FXCollections.observableArrayList(activiteit.getInschrijvingen());
        inschrijvingenActiviteitSorted = new SortedList(inschrijvingenActiviteit, sortOrderInschrijving);
        return FXCollections.unmodifiableObservableList(inschrijvingenActiviteitSorted);
    }
    
    public ObservableList<Lid> geefLedenNogNietIngeschreven(Activiteit activiteit){
        ObservableList<Lid> alleLeden = FXCollections.observableArrayList(dataController.geefLeden());
        alleLeden.removeAll(activiteit
                .getInschrijvingen()
                .stream()
                .map(insch ->insch.getLid())
                .sorted(Comparator.comparing(Lid::getAchternaam).thenComparing(Lid::getVoornaam))
                .collect(Collectors.toList()));
        
        nogNietIngeschrevenLeden = FXCollections.unmodifiableObservableList(alleLeden);
        nogNietIngeschrevenLedenFiltered = new FilteredList(nogNietIngeschrevenLeden, p -> true);
        nogNietIngeschrevenLedenSorted = new SortedList(nogNietIngeschrevenLedenFiltered, sortOrderLeden);
        return FXCollections.unmodifiableObservableList(nogNietIngeschrevenLedenSorted);
    }
    
    public void veranderFilterNogNietIngeschrevenLeden(String familienaam, String voornaam){
        nogNietIngeschrevenLedenFiltered.setPredicate((lid) -> {
            boolean voornaamEmpty = voornaam.isEmpty() || voornaam.equals("");
            boolean familienaamEmpty = familienaam.isEmpty() || familienaam.equals("");
            
            boolean voornaamFilter = lid.getVoornaam().toLowerCase().equals(voornaam.toLowerCase()) || lid.getVoornaam().toLowerCase().startsWith(voornaam.toLowerCase());
            boolean familieNaamFilter = lid.getAchternaam().toLowerCase().equals(familienaam.toLowerCase()) || lid.getAchternaam().toLowerCase().startsWith(familienaam.toLowerCase());
            
            //00
            if(voornaamEmpty && familienaamEmpty){
                return true;
            }
            //01
            if(voornaamEmpty && !familienaamEmpty){
                return familieNaamFilter;
            }
            //10
            if(!voornaamEmpty && familienaamEmpty){
                return voornaamFilter;
            }
            //11
            if(!voornaamEmpty && !familienaamEmpty){
                return voornaamFilter && familieNaamFilter;
            }
            return true;
        });
        
    }
    

    //
    //Filtering
    //
    public void filterList(String naam, Formule formule, int aantalDeelnemers, boolean volzet) {
        filteredActiviteitenList.setPredicate(activiteit -> {
            boolean naamEmpty = naam.isEmpty() || naam.equals("");
            boolean formuleEmpty = formule == null || formule.name().equals("");
            boolean aantalDeelnemersEmpty = String.valueOf(aantalDeelnemers).isEmpty() || String.valueOf(aantalDeelnemers).equals("");
            boolean volzetEmpty = String.valueOf(volzet).isEmpty() || String.valueOf(volzet).equals("");

            boolean naamFilter = activiteit.getNaam().equalsIgnoreCase(naam) || activiteit.getNaam().toLowerCase().startsWith(naam.toLowerCase());
            boolean formuleFilter = activiteit.getFormule().equals(formule);
            boolean aantalDeelnemersFilter = activiteit.getAantalDeelnemers() == aantalDeelnemers || activiteit.getAantalDeelnemers()<= aantalDeelnemers;
            boolean volzetFilter = activiteit.isVolzet() == volzet;

            //0000
            if (naamEmpty && formuleEmpty && aantalDeelnemersEmpty && volzetEmpty) {
                return true;
            }

            //0001
            if (naamEmpty && formuleEmpty && aantalDeelnemersEmpty && !volzetEmpty) {
                return volzetFilter;
            }
            //0010
            if (naamEmpty && formuleEmpty && !aantalDeelnemersEmpty && volzetEmpty) {
                return aantalDeelnemersFilter;
            }
            //0011
            if (naamEmpty && formuleEmpty && !aantalDeelnemersEmpty && !volzetEmpty) {
                return aantalDeelnemersFilter && volzetFilter;
            }
            //0100
            if (naamEmpty && !formuleEmpty && aantalDeelnemersEmpty && volzetEmpty) {
                return formuleFilter;
            }
            //0101
            if (naamEmpty && !formuleEmpty && aantalDeelnemersEmpty && !volzetEmpty) {
                return formuleFilter && volzetFilter;
            }
            //0110
            if (naamEmpty && !formuleEmpty && !aantalDeelnemersEmpty && volzetEmpty) {
                return formuleFilter && aantalDeelnemersFilter;
            }
            //0111
            if (naamEmpty && !formuleEmpty && !aantalDeelnemersEmpty && !volzetEmpty) {
                return formuleFilter && aantalDeelnemersFilter && volzetFilter;
            }
            //1000
            if (!naamEmpty && formuleEmpty && aantalDeelnemersEmpty && volzetEmpty) {
                return naamFilter;
            }
            //1001
            if (!naamEmpty && formuleEmpty && aantalDeelnemersEmpty && !volzetEmpty) {
                return naamFilter && volzetFilter;
            }
            //1010
            if (!naamEmpty && formuleEmpty && !aantalDeelnemersEmpty && volzetEmpty) {
                return naamEmpty && aantalDeelnemersFilter;
            }
            //1011
            if (!naamEmpty && formuleEmpty && !aantalDeelnemersEmpty && !volzetEmpty) {
                return naamFilter && aantalDeelnemersFilter && volzetFilter;
            }
            //1100
            if (!naamEmpty && !formuleEmpty && aantalDeelnemersEmpty && volzetEmpty) {
                return naamFilter && formuleFilter;
            }
            //1101
            if (!naamEmpty && !formuleEmpty && aantalDeelnemersEmpty && !volzetEmpty) {
                return naamFilter && formuleFilter && volzetEmpty;
            }
            //1110
            if (!naamEmpty && !formuleEmpty && !aantalDeelnemersEmpty && volzetEmpty) {
                return naamFilter && formuleFilter && aantalDeelnemersFilter;
            }
            //1111
            if (!naamEmpty && !formuleEmpty && !aantalDeelnemersEmpty && !volzetEmpty) {
                return naamFilter && formuleFilter && aantalDeelnemersFilter && volzetFilter;
            }

            return true;
        });
    }

    //
    //CRUD-operaties
    //
    public void wijzigActiviteit(Activiteit activiteit, String naam, Formule formule, int maxDeelnemers,
            LocalDate beginDatum, LocalDate eindDatum, LocalDate uitersteInschrijvingsDatum, String straat, String stad, String postcode,
            String huisnummer, String bus, String naamLocatie, String gsmnummer , String email) {
        activiteit.setNaam(naam);
        activiteit.setFormule(formule);
        activiteit.setMaxDeelnemers(maxDeelnemers);
        activiteit.setBeginDatum(beginDatum);
        activiteit.setEindDatum(eindDatum);
        activiteit.setUitersteInschrijvingsDatum(uitersteInschrijvingsDatum);
        activiteit.setStraat(straat);
        activiteit.setStad(stad);
        activiteit.setPostcode(postcode);
        activiteit.setHuisnummer(huisnummer);
        activiteit.setBus(bus);
        activiteit.setNaamLocatie(naamLocatie);
        activiteit.setGsmnummer(gsmnummer);
        activiteit.setEmail(email);
    }

    public void voegActiviteitToe(String naam, Formule formule, int maxDeelnemers, LocalDate beginDatum, LocalDate eindDatum,
            LocalDate uitersteInschrijvingsDatum, String straat, String stad, String postcode, String huisnummer, String bus
            , String naamLocatie, String gsmnummer, String email) {
        Activiteit activiteit;
        
        if (eindDatum == null) {
            activiteit = new Activiteit(naam, formule, maxDeelnemers, beginDatum, uitersteInschrijvingsDatum);
        } else {
            activiteit = new Activiteit(naam, formule, maxDeelnemers, beginDatum, eindDatum, uitersteInschrijvingsDatum);
        }
        activiteit.setStraat(straat);
        activiteit.setStad(stad);
        activiteit.setPostcode(postcode);
        activiteit.setHuisnummer(huisnummer);
        activiteit.setBus(bus);
        activiteit.setNaamLocatie(naamLocatie);
        activiteit.setGsmnummer(gsmnummer);
        activiteit.setEmail(email);

        this.activiteitenList.add(activiteit);
        dataController.geefActiviteiten().add(activiteit);
    }

    public void verwijderActiviteit(Activiteit activiteit) {
        this.activiteitenList.remove(activiteit);
        dataController.geefActiviteiten().remove(activiteit);
    }

    //
    //ENUMS AND METHODS FOR COMBOBOX
    //
    public ObservableList<Formule> geefFormules() {
        ObservableList<Formule> formules = FXCollections.observableArrayList(dataController.geefFormules());
        return FXCollections.unmodifiableObservableList(formules);
    }

    public ObservableList<String> geefFormulesFilter() {
        ObservableList<String> formules = FXCollections.observableArrayList(dataController
                .geefFormules().stream().map(functie -> functie.name())
                .collect(Collectors.toList()));
        formules.add(0, "Alle types");
        return formules;
    }

    public ObservableList<Lid> geefLeden() {
        ObservableList<Lid> leden = FXCollections.observableArrayList(dataController.geefLeden());
        return FXCollections.unmodifiableObservableList(leden);
    }

    //
    //INSCHRIJVEN BIJ ACTIVITEIT
    //
    public void voegInschrijvingToe(Activiteit activiteit, Lid lid) {
        Inschrijving inschrijving = dataController.geefInschrijvingen().stream()
                .filter(i -> i.getLid().equals(lid) && i.getFormule().equals(activiteit.getFormule()))
                .findAny()
                .orElse(null);
        if (inschrijving != null) {
            switch (inschrijving.getFormule()) {
                case ACTIVITEIT:
                case UITSTAP:
                case STAGE:
                case PROEF:
                case EXAMEN:
                    inschrijving = new Inschrijving(activiteit.getFormule(), lid, LocalDate.now());
                    dataController.geefInschrijvingen().add(inschrijving);
                    inschrijvingenActiviteit.add(inschrijving);
                    activiteit.setAantalDeelnemers();
                    break;
                default:
                    break;
            }
        } else {
            inschrijving = new Inschrijving(activiteit.getFormule(), lid, LocalDate.now());
            dataController.geefInschrijvingen().add(inschrijving);
            inschrijvingenActiviteit.add(inschrijving);
            activiteit.setAantalDeelnemers();
        }
        activiteit.voegInschrijvingToe(inschrijving);
    }

    public void verwijderInschrijving(Activiteit activiteit, Lid lid) {
        Inschrijving inschrijving = activiteit.getInschrijvingen().stream()
                .filter(i -> i.getLid().equals(lid)).findAny().orElse(null);
        if (inschrijving != null) {
            activiteit.verwijderInschrijving(inschrijving);
            dataController.geefInschrijvingen().remove(inschrijving);
            inschrijvingenActiviteit.remove(inschrijving);
        } else {
            throw new IllegalArgumentException("Inschrijving bestaat niet.");
        }
    }

    //
    //Observer
    //
    public void addObserver(ListChangeListener<Activiteit> listener) {
        activiteitenList.addListener(listener);
    }

    public void removeObserver(ListChangeListener<Activiteit> listener) {
        activiteitenList.removeListener(listener);
    }
}
