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
    public void filterList(Graad graad, Thema thema, String titel) {
        filteredOefeningenList.setPredicate(oefening -> {
            boolean titelEmpty = titel == null;
            boolean themaEmpty = thema == null;
            boolean graadEmpty = graad == null || graad.name().equals("");
            //boolean laatsteRaadplegingEmpty = laatsteRaadpleging == null;

            boolean graadFilter = oefening.getGraad().equals(graad);
            boolean titelFilter = oefening.getTitel().toLowerCase().equals(titel.toLowerCase()) || oefening.getTitel().toLowerCase().startsWith(titel.toLowerCase());
            boolean themaFilter = oefening.getThema().equals(thema);
            //boolean laatsteRaadplegingFilter = oefening.getLaatsteRaadpleging().equals(laatsteRaadpleging);

            //000
            if (titelEmpty && themaEmpty && graadEmpty) {
                return true;
            }
            //001
            if (titelEmpty && themaEmpty && !graadEmpty) {
                return graadFilter;
            }
            //010
            if (titelEmpty && !themaEmpty && graadEmpty) {
                return themaFilter;
            }
            //011
            if (titelEmpty && !themaEmpty && !graadEmpty) {
                return themaFilter && graadFilter;
            }
            //100
            if (!titelEmpty && themaEmpty && graadEmpty) {
                return titelFilter;
            }
            //101
            if (!titelEmpty && themaEmpty && !graadEmpty) {
                return titelFilter && graadFilter;
            }
            //110
            if (!titelEmpty && !themaEmpty && graadEmpty) {
                return titelFilter && themaFilter;
            }
            //111
            if (!titelEmpty && !themaEmpty && !graadEmpty) {
                return titelFilter && themaFilter && graadFilter;
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
    
     public ObservableList<String> geefThemasFilter() {
        ObservableList<String> themas = FXCollections.observableArrayList(dataController
                .geefThemas().stream().map(thema -> thema.getNaam())
                .collect(Collectors.toList()));
        themas.add(0, "Alle thema's");
        return themas;
    }
     
     public ObservableList<Thema> geefThemas(){
         return FXCollections.observableArrayList(dataController.geefThemas());
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
