package domein.controllers;

import javafx.collections.ObservableList;
import domein.Activiteit;
import domein.Inschrijving;
import domein.Lid;
import domein.enums.Formule;
import java.time.LocalDate;
import java.util.Comparator;
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
    private final Comparator<Activiteit> sortOrder = byDate.reversed();

    public ActiviteitBeheerController() {
        dataController = new DataController();
        activiteitenList = FXCollections.observableArrayList(dataController.geefActiviteiten());
        filteredActiviteitenList = new FilteredList(activiteitenList, p -> true);
        sortedActiviteitenList = new SortedList(filteredActiviteitenList, sortOrder);
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
            boolean aantalDeelnemersFilter = activiteit.getMaxDeelnemers() == aantalDeelnemers || activiteit.getMaxDeelnemers() <= aantalDeelnemers;
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
            String huisnummer, String bus) {
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
    }

    public void voegActiviteitToe(String naam, Formule formule, int maxDeelnemers, LocalDate beginDatum, LocalDate eindDatum,
            LocalDate uitersteInschrijvingsDatum, String straat, String stad, String postcode, String huisnummer, String bus) {
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

        this.activiteitenList.add(activiteit);
    }

    public void verwijderActiviteit(Activiteit activiteit) {
        this.activiteitenList.remove(activiteit);
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
                    break;
                default:
                    break;
            }
        } else {
            inschrijving = new Inschrijving(activiteit.getFormule(), lid, LocalDate.now());
            dataController.geefInschrijvingen().add(inschrijving);
        }
        inschrijving.voegActiviteitToe(activiteit);
    }

    public void verwijderInschrijving(Activiteit activiteit, Lid lid) {
        Inschrijving inschrijving = activiteit.getInschrijvingen().stream()
                .filter(i -> i.getLid().equals(lid)).findAny().orElse(null);
        if (inschrijving != null) {
            inschrijving.verwijderActiviteit(activiteit);
            dataController.geefInschrijvingen().remove(inschrijving);
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
