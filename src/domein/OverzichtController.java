/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.DataInitializer;

/**
 *
 * @author robdeputter
 */
public class OverzichtController {

    private List<Inschrijving> inschrijvingen;
    private ObservableList<Activiteit> activiteiten;
    private List<Aanwezigheid> aanwezigheden;
    private List<Lid> leden;
    private List<Raadpleging> raadplegingen;

    public OverzichtController() {
        this.inschrijvingen = new ArrayList<>();
        this.activiteiten = FXCollections.observableArrayList();
        this.aanwezigheden = new ArrayList<>();
        this.leden = new ArrayList<>();
        this.raadplegingen = new ArrayList<>();

        //Calling testdata methods
        fillActiviteitenWithTestData(activiteiten);
    }

    public List<Aanwezigheid> geefOverzichtAanwezigheden() {
        return aanwezigheden;
    }

    public List<Aanwezigheid> geefOverzichtAanwezighedenVoorBepaaldeDatum(LocalDate datum) {
        return aanwezigheden.stream()
                .filter(aanwezigheid -> aanwezigheid.getActiviteit().getDatum().equals(datum))
                .collect(Collectors.toList());
    }

    public List<Aanwezigheid> geefOverzichtAanwezighedenVoorBepaaldLid(Lid lid) {
        return aanwezigheden.stream()
                .filter(aanwezigheid -> aanwezigheid.getLid().equals(lid))
                .collect(Collectors.toList());
    }

    public List<Aanwezigheid> geefOverzichtAanwezighedenVoorBepaaldeFormule(Formule formule) {
        return aanwezigheden.stream()
                .filter(aanwezigheid -> aanwezigheid.getActiviteit().getFormule().equals(formule))
                .collect(Collectors.toList());
    }

    public List<Inschrijving> geefOverzichtInschrijvingen() {
        return inschrijvingen;
    }

    public List<Inschrijving> geefOverzichtInschrijvingenVoorBepaaldeFormule(Formule formule) {
        return inschrijvingen.stream()
                .filter(inschrijving -> inschrijving.getFormule().equals(formule))
                .collect(Collectors.toList());
    }

    // Is het wel nodig om de naam van je methode zo expliciet uit te schrijven? Volstaat method overloading niet? --edit by tybo: we dont do that here
    public List<Inschrijving> geefOverzichtInschrijvingenVoorBepaaldInterval(LocalDate van, LocalDate tot) {
        return inschrijvingen.stream()
                .filter(inschrijving
                        -> inschrijving.getTijdstip().compareTo(tot) <= 0
                && inschrijving.getTijdstip().compareTo(van) >= 0)
                .collect(Collectors.toList());
    }

    public ObservableList<Activiteit> geefOverzichtActiviteiten() {
        return FXCollections.unmodifiableObservableList(activiteiten);
    }

    public List<Activiteit> geefOverzichtActiviteitenVoorBepaaldeDeelnemer(Lid lid) {
        return activiteiten.stream()
                .filter(activiteit -> activiteit.getDeelnemers().contains(lid))
                .collect(Collectors.toList());
    }

    public Map<Lid, Integer> geefOverzichtClubkampioenschap() {
        Map<Lid, Integer> deelnemersMetPunten = aanwezigheden.stream()
                .collect(Collectors.groupingBy(Aanwezigheid::getLid,
                        Collectors.summingInt(
                                aanwezigheid -> aanwezigheid.getPuntenAantal())));
        return deelnemersMetPunten;
    }

    public List<Raadpleging> geefOverzichtRaadplegingen() {
        return raadplegingen;
    }

    public List<Raadpleging> geefOverzichtRaadplegingenVoorBepaaldLid(Lid lid) {
        return raadplegingen.stream()
                .filter(r -> r.getLid().equals(lid))
                .collect(Collectors.toList());
    }

    public List<Raadpleging> geefOverzichtRaadplegingenVoorBepaaldeOefening(Oefening oefening) {
        return raadplegingen.stream()
                .filter(r -> r.getOefening().equals(oefening))
                .collect(Collectors.toList());
    }

    public List<Lid> geefOverzichtLeden() {
        return leden;
    }

    //TESTMETHODS FILLING DATA
    //TestMethod fill data to check tableview
    private void fillActiviteitenWithTestData(ObservableList<Activiteit> activiteiten) {
        Activiteit s1 = new Stage("Hoogtestage Ardennen", Formule.STAGE, LocalDate.of(2019, Month.MARCH, 12));
        Activiteit s2 = new Stage("Hoogtestage Vogezen", Formule.STAGE, LocalDate.of(2019, Month.AUGUST, 28));
        Activiteit s3 = new Stage("Uitstap Nederland", Formule.STAGE, LocalDate.of(2020, Month.JANUARY, 10));

        Activiteit l1 = new Les("Les 1", Formule.ZA, LocalDate.of(2019, Month.FEBRUARY, 23));
        Activiteit l2 = new Les("Les 1", Formule.WO_ZA, LocalDate.of(2019, Month.FEBRUARY, 20));
        Activiteit l3 = new Les("Les 1", Formule.DI_ZA, LocalDate.of(2020, Month.FEBRUARY, 19));

        activiteiten.add(s1);
        activiteiten.add(s2);
        activiteiten.add(s3);
        activiteiten.add(l1);
        activiteiten.add(l2);
        activiteiten.add(l3);
    }
}
