package domein.controllers;

import domein.Aanwezigheid;
import domein.Activiteit;
import domein.enums.Formule;
import domein.enums.Graad;
import domein.Inschrijving;
import domein.Lid;
import domein.Oefening;
import domein.Raadpleging;
import domein.enums.Functie;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class OverzichtController {

    private DataController dataController;

    private ObservableList<Inschrijving> inschrijvingen;
    private FilteredList<Inschrijving> inschrijvingenFilteredList;
    private SortedList<Inschrijving> inschrijvingenSortedList;
    private Comparator<Inschrijving> byTijdstip = (i1, i2) -> i1.getTijdstip().compareTo(i2.getTijdstip());
    private Comparator<Inschrijving> inschrijvingsSortOrder = byTijdstip.reversed();

    private ObservableList<Activiteit> activiteiten;
    private FilteredList<Activiteit> activiteitenFilteredList;
    private SortedList<Activiteit> activiteitenSortedList;
    private Comparator<Activiteit> byBeginDatum = (a1, a2) -> a1.getBeginDatum().compareTo(a2.getBeginDatum());
    private Comparator<Activiteit> activiteitSortOrder = byBeginDatum.reversed();

    private ObservableList<Aanwezigheid> aanwezigheden;
    private FilteredList<Aanwezigheid> aanwezighedenFilteredList;
    private SortedList<Aanwezigheid> aanwezighedenSortedList;
    private Comparator<Aanwezigheid> byDatum = (a1, a2) -> a1.getDatum().compareTo(a2.getDatum());
    
    private ObservableList<Lid> leden; //nodig voor cbo's
    
    private ObservableList<Raadpleging> raadplegingen;
    private FilteredList<Raadpleging> raadplegingenFilteredList;
    private SortedList<Raadpleging> raadplegingenSortedList;
    private Comparator<Raadpleging> byOefnnaam = (r1, r2) -> r1.getOefeningNaam().compareTo(r2.getOefeningNaam());
    
    private ObservableList<Oefening> oefeningen; 
    private ObservableList<Formule> formulesVoorInschrijving;

    public OverzichtController() {
        dataController = new DataController();

        this.inschrijvingen = FXCollections.observableArrayList(dataController.geefInschrijvingen());
        inschrijvingenFilteredList = new FilteredList(inschrijvingen, p -> true);
        inschrijvingenSortedList = new SortedList(inschrijvingenFilteredList, inschrijvingsSortOrder);

        this.activiteiten = FXCollections.observableArrayList(dataController.geefActiviteiten());
        activiteitenFilteredList = new FilteredList(activiteiten, p -> true);
        activiteitenSortedList = new SortedList(activiteitenFilteredList, activiteitSortOrder);

        this.aanwezigheden = FXCollections.observableArrayList(dataController.geefAanwezigheden());
        aanwezighedenFilteredList = new FilteredList(aanwezigheden,p -> true);
        aanwezighedenSortedList = new SortedList(aanwezighedenFilteredList, byDatum.reversed());

        this.leden = FXCollections.observableArrayList(dataController.geefLeden());

        this.raadplegingen = FXCollections.observableArrayList(dataController.geefRaadplegingen());
        raadplegingenFilteredList = new FilteredList(raadplegingen, p -> true);
        raadplegingenSortedList = new SortedList(raadplegingenFilteredList, byOefnnaam);

        this.oefeningen = FXCollections.observableArrayList(dataController.geefOefeningen());
        

    }

    //
    //AANWEZIGHEDEN
    //
    public ObservableList<Aanwezigheid> geefOverzichtAanwezigheden() {
        return FXCollections.unmodifiableObservableList(aanwezighedenSortedList);
    }


    public void veranderAanwezigheidFilter(LocalDate datum,String lid, Formule formule){
        aanwezighedenFilteredList.setPredicate(aanwezigheid -> {
            boolean datumEmpty = datum == null;
            boolean lidEmpty = lid == null;
            boolean formuleEmpty = formule == null;
            
            boolean datumFilter = aanwezigheid.getDatum().equals(datum);
            boolean lidFilter = aanwezigheid.getLid().geefVolledigeNaam().equals(lid);
            boolean formuleFilter = aanwezigheid.getFormule().equals(formule);
            
            //000
            if (datumEmpty && lidEmpty && formuleEmpty){
                return true;
            }
            //001
            if (datumEmpty && lidEmpty && !formuleEmpty){
                return formuleFilter;
            }
            //010
            if (datumEmpty && !lidEmpty && formuleEmpty){
                return lidFilter;
            }
            //011
            if (datumEmpty && !lidEmpty && !formuleEmpty){
                return lidFilter && formuleFilter;
            }
            //100
            if (!datumEmpty && lidEmpty && formuleEmpty){
                return datumFilter;
            }
            //101
            if (!datumEmpty && lidEmpty && !formuleEmpty){
                return datumFilter && formuleFilter;
            }
            //110
            if (!datumEmpty && !lidEmpty && formuleEmpty){
                return datumFilter && lidFilter;
            }
            //111
            if(!datumEmpty && !lidEmpty && !formuleEmpty){
                return datumFilter && lidFilter && formuleFilter;
            }
            return true;
        });
        
    }
    //
    //INSCHRIJVINGEN
    //
    public ObservableList<Inschrijving> geefOverzichtInschrijvingen() {
        return FXCollections.unmodifiableObservableList(inschrijvingenSortedList);
    }


    public void veranderInschrijvingFilter(Formule formule, LocalDate van, LocalDate tot) {
        inschrijvingenFilteredList.setPredicate(inschrijving -> {
            boolean formuleEmpty = formule == null;
            boolean vanEmpty = van == null;
            boolean totEmpty = tot == null;

            boolean formuleFilter = inschrijving.getFormule().equals(formule);
            boolean vanFilter = vanEmpty ? false : inschrijving.getTijdstip().compareTo(van) >= 0;
            boolean totFilter = totEmpty ? false : inschrijving.getTijdstip().compareTo(tot) <= 0;

            //000
            if (formuleEmpty && vanEmpty && totEmpty) {
                return true;
            }
            //001
            if (formuleEmpty && vanEmpty && !totEmpty) {
                return totFilter;
            }
            //010
            if (formuleEmpty && !vanEmpty && totEmpty) {
                return vanFilter;
            }
            //011
            if (formuleEmpty && !vanEmpty && !totEmpty) {
                return vanFilter && totFilter;
            }
            //100
            if (!formuleEmpty && vanEmpty && totEmpty) {
                return formuleFilter;
            }
            //101
            if (!formuleEmpty && vanEmpty && !totEmpty) {
                return formuleFilter && totFilter;
            }
            //110
            if (!formuleEmpty && !vanEmpty && totEmpty){
                return formuleFilter && vanFilter;
            }
            //111
            if (!formuleEmpty && !vanEmpty && !totEmpty){
                return formuleFilter && vanFilter && totFilter;
            }
            return true;
        });

    }

    //
    //ACTIVITEITEN
    //
    public ObservableList<Activiteit> geefOverzichtActiviteiten() {
        return FXCollections.unmodifiableObservableList(activiteitenSortedList);
    }

    public void veranderActiviteitenFilter(Formule formule) {
        activiteitenFilteredList.setPredicate(activiteit -> {
            if (formule != null) {
                return activiteit.getFormule().equals(formule);
            }
            return true;
        });
    }

    //
    //CLUBKAMPIOENSCHAP
    //
    public ObservableList<Lid> geefOverzichtClubkampioenschap() {
        berekenPuntenLeden();
        ObservableList<Lid> ledenSortedPunten = FXCollections.observableArrayList(leden.stream()
                .sorted(Comparator.comparing(Lid::getPuntenAantal).reversed())
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(ledenSortedPunten);
    }

    private void berekenPuntenLeden() {
        int aantalPunten = 0;
        leden.stream().forEach(lid -> {
            lid.setPuntenAantal(
                    aanwezigheden.stream()
                            .filter(aanwezigheid -> aanwezigheid.getLid().equals(lid))
                            .collect(Collectors.summingInt(a -> a.getPuntenAantal())));
        }
        );
    }
    
    public ObservableList<Aanwezigheid> geefAanwezighedenVoorLid(Lid lid){
        List<Aanwezigheid> aanwezighedenVoorLid = dataController.geefAanwezigheden().stream()
                .filter(aanwezigheid -> aanwezigheid
                        .getLid().equals(lid))
                .collect(Collectors.toList());
        
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(aanwezighedenVoorLid));
    }

    //
    //RAADPLEGINGEN
    //
    
    public ObservableList<Raadpleging> geefOverzichtRaadplegingen() {
        return FXCollections.unmodifiableObservableList(raadplegingenSortedList);
    }

    
    public void veranderRaadplegingFilter(String lid , Oefening oefening){
        raadplegingenFilteredList.setPredicate(raadpleging -> {
            boolean lidEmpty = lid == null;
            boolean oefeningEmpty = oefening == null;
            boolean lidFilter = raadpleging.getLid().geefVolledigeNaam().equals(lid);
            boolean oefeningFilter = raadpleging.getOefening().equals(oefening);
            
            //00
            if(lidEmpty && oefeningEmpty){
                return true;
            }
            //01
            if (lidEmpty && !oefeningEmpty){
                return oefeningFilter;
            }
            //10
            if (!lidEmpty && oefeningEmpty){
                return lidFilter;
            }
            //11
            if (!lidEmpty && !oefeningEmpty){
                return lidFilter && oefeningFilter;
            }
            
            return true;
        });
        
    }

    //
    //LEDEN
    //
    public ObservableList<Lid> geefOverzichtLeden() {
        return FXCollections.unmodifiableObservableList(leden)
                .sorted(Comparator.comparing(Lid::getVoornaam).thenComparing(Lid::getAchternaam));
    }
    
    public ObservableList<String> geefOverzichtLedenFilter(){
         ObservableList<String> leden = FXCollections.observableArrayList(dataController
                .geefLeden().stream().map(lid -> String.format("%s %s", lid.getVoornaam(),lid.getAchternaam()))
                .collect(Collectors.toList()));
        leden.add(0, "Alle leden");
        return leden;
    }


    //
    //ENUMS
    //
    public ObservableList<Formule> geefFormules() {
        ObservableList<Formule> formules = FXCollections.observableArrayList(Arrays.asList(Formule.values()));
        return FXCollections.unmodifiableObservableList(formules);
    }
    
    public ObservableList<String> geefFormulesFilter() {
        ObservableList<String> functies = FXCollections.observableArrayList(dataController
                .geefFormules().stream().map(formule -> formule.name())
                .collect(Collectors.toList()));
        functies.add(0, "Alle formules");
        return functies;
    }

    public ObservableList<Functie> geefFuncties() {
        ObservableList<Functie> functies = FXCollections.observableArrayList(Arrays.asList(Functie.values()));
        return FXCollections.unmodifiableObservableList(functies);
    }

    public ObservableList<Graad> geefGraden() {
        ObservableList<Graad> graden = FXCollections.observableArrayList(Arrays.asList(Graad.values()));
        return graden;
    }

    public ObservableList<String> geefGeslachten() {
        ObservableList<String> geslachten = FXCollections.observableArrayList("Man", "Vrouw");
        return geslachten;
    }

    //
    //OVERIG
    //
    public ObservableList<String> geefOefeningNamen() {
        ObservableList<String> oefeningNamenSorted = FXCollections.observableArrayList(oefeningen.stream()
                .map(Oefening::getTitel)
                .distinct()
                .sorted(Comparator.comparing(String::toString))
                .collect(Collectors.toList()));
        
        oefeningNamenSorted.add(0, "Alle oefeningen");
        return FXCollections.unmodifiableObservableList(oefeningNamenSorted);
    }

    public Oefening geefOefeningOpTitel(String titel) {
        Oefening oefening = oefeningen.stream()
                .filter(o -> o.getTitel().equals(titel))
                .findAny()
                .orElse(null);
        return oefening;
    }
    
    public ObservableList<Formule> geefFormulesPerLid(Lid lid){
        return FXCollections.observableArrayList(dataController.geefFormulesVanLid(lid));
    }
}
