package domein.controllers;

import domein.Oefening;
import domein.Thema;
import domein.enums.Formule;
import domein.enums.Graad;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class LesmateriaalBeheerController {

    private DataController dataController;
    private ObservableList<Oefening> oefeningenList;
    private FilteredList<Oefening> filteredOefeningenList;
    private SortedList<Oefening> sortedOefeningenList;

    private final Comparator<Oefening> byGraad = (p1, p2) -> p1.getGraad().compareTo(p2.getGraad());
    private final Comparator<Oefening> byName = (p1, p2) -> p1.getTitel().compareTo(p2.getTitel());
    private final Comparator<Oefening> sortOrder = byGraad.thenComparing(byName);

    public LesmateriaalBeheerController() {
        dataController = new DataController();
        oefeningenList = FXCollections.observableArrayList(dataController.geefOefeningen());
        filteredOefeningenList = new FilteredList(oefeningenList, p -> true);
        sortedOefeningenList = new SortedList(filteredOefeningenList, sortOrder);
    }

    //
    //Overzicht van oefeningen
    //
    public ObservableList<Oefening> geefAlleOefeningen() {
        return FXCollections.unmodifiableObservableList(oefeningenList);
    }

    public ObservableList<Oefening> geefObservableListOefeningen() {
        return FXCollections.unmodifiableObservableList(sortedOefeningenList);
    }

    //
    //Filtering
    //
    public void filterList(Graad graad, int aantalRaadplegingen, LocalDate laatsteRaadpleging) {
        filteredOefeningenList.setPredicate(oefening -> {
            boolean graadEmpty = graad == null || graad.name().equals("");
            boolean aantalRaadplegingenEmpty = String.valueOf(aantalRaadplegingen).isEmpty() || String.valueOf(aantalRaadplegingen).equals("");
            boolean laatsteRaadplegingEmpty = laatsteRaadpleging == null;

            boolean graadFilter = oefening.getGraad().equals(graad);
            boolean aantalRaadplegingenFilter = oefening.getAantalRaadplegingen() == aantalRaadplegingen;
            boolean laatsteRaadplegingFilter = oefening.getLaatsteRaadpleging().equals(laatsteRaadpleging);

            //000
            if (graadEmpty && aantalRaadplegingenEmpty && laatsteRaadplegingEmpty) {
                return true;
            }
            //001
            if (graadEmpty && aantalRaadplegingenEmpty && !laatsteRaadplegingEmpty) {
                return laatsteRaadplegingFilter;
            }
            //010
            if (graadEmpty && !aantalRaadplegingenEmpty && laatsteRaadplegingEmpty) {
                return aantalRaadplegingenFilter;
            }
            //011
            if (graadEmpty && !aantalRaadplegingenEmpty && !laatsteRaadplegingEmpty) {
                return aantalRaadplegingenFilter && laatsteRaadplegingFilter;
            }
            //100
            if (!graadEmpty && aantalRaadplegingenEmpty && laatsteRaadplegingEmpty) {
                return graadFilter;
            }
            //101
            if (!graadEmpty && aantalRaadplegingenEmpty && !laatsteRaadplegingEmpty) {
                return graadFilter && laatsteRaadplegingFilter;
            }
            //110
            if (!graadEmpty && !aantalRaadplegingenEmpty && laatsteRaadplegingEmpty) {
                return graadFilter && aantalRaadplegingenFilter;
            }
            //111
            if (!graadEmpty && !aantalRaadplegingenEmpty && !laatsteRaadplegingEmpty) {
                return graadFilter && aantalRaadplegingenFilter && laatsteRaadplegingFilter;
            }
            return true;
        });
    }

    //
    //CRUD-operaties
    //
    public void wijzigOefening(Oefening oefening, String titel, String urlVideo, String afbeelding, String tekst, Graad graad, Thema thema) {
        oefening.setTitel(titel);
        oefening.setUrlVideo(urlVideo);
        oefening.setAfbeelding(afbeelding);
        oefening.setTekst(tekst);
        oefening.setGraad(graad);
        oefening.setThema(thema);
    }

    public void voegOefeningToe(String titel, String urlVideo, String afbeelding, String tekst, Graad graad, Thema thema) {
        Oefening oefening = new Oefening(titel, urlVideo, afbeelding, tekst, graad, thema);
        this.oefeningenList.add(oefening);
        dataController.geefOefeningen().add(oefening);
    }

    public void verwijderOefening(Oefening oefening) {
        this.oefeningenList.remove(oefening);
        dataController.geefOefeningen().remove(oefening);
    }

    //
    //ENUMS AND METHODS FOR COMBOBOX
    //
    public ObservableList<String> geefGradenFilter() {
        ObservableList<String> graden = FXCollections.observableArrayList(dataController
                .geefGraden().stream().map(functie -> functie.name())
                .collect(Collectors.toList()));
        graden.add(0, "Alle graden");
        return graden;
    }

    //
    //Observer
    //
    public void addObserver(ListChangeListener<Oefening> listener) {
        oefeningenList.addListener(listener);
    }

    public void removeObserver(ListChangeListener<Oefening> listener) {
        oefeningenList.removeListener(listener);
    }

}
