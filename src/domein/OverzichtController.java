/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import exceptions.DatumIntervalException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.runtime.ScriptRuntime;
import persistentie.DataInitializer;

/**
 *
 * @author robdeputter
 */
public class OverzichtController {

    private ObservableList<Inschrijving> inschrijvingen;
    private ObservableList<Activiteit> activiteiten;
    private ObservableList<Aanwezigheid> aanwezigheden;
    private ObservableList<Lid> leden;
    private ObservableList<Raadpleging> raadplegingen;
    private ObservableList<Oefening> oefeningen;

    public OverzichtController() {
        this.inschrijvingen = FXCollections.observableArrayList();
        this.activiteiten = FXCollections.observableArrayList();
        this.aanwezigheden = FXCollections.observableArrayList();
        this.leden = FXCollections.observableArrayList();
        this.raadplegingen = FXCollections.observableArrayList();
        this.oefeningen = FXCollections.observableArrayList();

        //Calling testdata method
        fillTestData();
    }

    public ObservableList<Aanwezigheid> geefOverzichtAanwezigheden() {
        ObservableList<Aanwezigheid> aanwezighedenSortedDatum = FXCollections.observableArrayList(aanwezigheden.stream()
                .sorted(Comparator.comparing(Aanwezigheid::getDatum).reversed())
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(aanwezighedenSortedDatum);
    }

    public ObservableList<Aanwezigheid> geefOverzichtAanwezighedenVoorBepaaldeDatum(LocalDate datum) {
        if (LocalDate.now().compareTo(datum) < 0) {
            throw new DatumIntervalException("Datum mag niet in de toekomst liggen!");
        }
        ObservableList<Aanwezigheid> aanwezighedenVoorDatum = FXCollections.observableArrayList(aanwezigheden.stream()
                .filter(aanwezigheid -> aanwezigheid.getDatum().equals(datum))
                .sorted(Comparator.comparing(Aanwezigheid::getDatum).reversed())
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(aanwezighedenVoorDatum);

    }

    public ObservableList<Aanwezigheid> geefOverzichtAanwezighedenVoorBepaaldLid(Lid lid) {
        ObservableList<Aanwezigheid> aanwezighedenVoorLid = FXCollections.observableArrayList(aanwezigheden.stream()
                .filter(aanwezigheid -> aanwezigheid.getLid().equals(lid))
                .sorted(Comparator.comparing(Aanwezigheid::getDatum).reversed())
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(aanwezighedenVoorLid);
    }

    public ObservableList<Aanwezigheid> geefOverzichtAanwezighedenVoorBepaaldeFormule(Formule formule) {
        ObservableList<Aanwezigheid> aanwezighedenVoorFormule = FXCollections.observableArrayList(aanwezigheden.stream()
                .filter(aanwezigheid -> aanwezigheid.getActiviteit().getFormule().equals(formule))
                .sorted(Comparator.comparing(Aanwezigheid::getDatum).reversed())
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(aanwezighedenVoorFormule);
    }

    public ObservableList<Inschrijving> geefOverzichtInschrijvingen() {
        ObservableList<Inschrijving> inschrijvingenSortedDatum = FXCollections.observableArrayList(inschrijvingen.stream()
                .sorted(Comparator.comparing(Inschrijving::getTijdstip).reversed())
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(inschrijvingenSortedDatum);
    }

    public ObservableList<Inschrijving> geefOverzichtInschrijvingenVoorBepaaldeFormule(Formule formule) {
        ObservableList<Inschrijving> inschrijvingenVoorFormule = FXCollections.observableArrayList(inschrijvingen.stream()
                .filter(inschrijving -> inschrijving.getFormule().equals(formule))
                .sorted(Comparator.comparing(Inschrijving::getTijdstip).reversed())
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(inschrijvingenVoorFormule);
    }

    public ObservableList<Inschrijving> geefOverzichtInschrijvingenVoorBepaaldInterval(LocalDate van, LocalDate tot) {
        if (tot.compareTo(van) < 0) {
            throw new DatumIntervalException();
        }
        ObservableList<Inschrijving> inschrijvingenVoorInterval = FXCollections.observableArrayList(inschrijvingen.stream()
                .filter(inschrijving
                        -> inschrijving.getTijdstip().compareTo(tot) <= 0
                && inschrijving.getTijdstip().compareTo(van) >= 0)
                .sorted(Comparator.comparing(Inschrijving::getTijdstip).reversed())
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(inschrijvingenVoorInterval);
    }

    public ObservableList<Activiteit> geefOverzichtActiviteiten() {
        ObservableList<Activiteit> activiteitenSortedDatum = FXCollections.observableArrayList(activiteiten.stream()
                .sorted(Comparator.comparing(Activiteit::getDatum).reversed())
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(activiteitenSortedDatum);
    }

    public ObservableList<Activiteit> geefOverzichtActiviteitenVoorBepaaldeDeelnemer(Lid lid) {

        ObservableList<Activiteit> activiteitenVoorLid = FXCollections.observableArrayList(activiteiten.stream()
                .filter(activiteit -> activiteit.getDeelnemers().contains(lid))
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(activiteitenVoorLid);
    }

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

    public ObservableList<Raadpleging> geefOverzichtRaadplegingen() {
        ObservableList<Raadpleging> raadplegingenSortedTitel = FXCollections.observableArrayList(raadplegingen.stream()
                //.sorted(Comparator.comparing(Raadpleging::getOefeningNaam))
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(raadplegingenSortedTitel);
    }

    public ObservableList<Raadpleging> geefOverzichtRaadplegingenVoorBepaaldLid(Lid lid) {
        ObservableList<Raadpleging> raadplegingenVoorLid = FXCollections.observableArrayList(raadplegingen.stream()
                .filter(r -> r.getLid().equals(lid))
                .sorted(Comparator.comparing(Raadpleging::getOefeningNaam))
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(raadplegingenVoorLid);
    }

    public ObservableList<Raadpleging> geefOverzichtRaadplegingenVoorBepaaldeOefening(Oefening oefening) {
        ObservableList<Raadpleging> raadplegingenVoorOefening = FXCollections.observableArrayList(raadplegingen.stream()
                .filter(r -> r.getOefening().equals(oefening))
                .sorted(Comparator.comparing(Raadpleging::getOefeningNaam))
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(raadplegingenVoorOefening);
    }

    public ObservableList<Lid> geefOverzichtLeden() {
        ObservableList<Lid> ledenSorted = FXCollections.observableArrayList(leden.stream()
                .sorted(Comparator.comparing(Lid::getVoornaam).thenComparing(Lid::getAchternaam))
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(ledenSorted);
    }

    public ObservableList<Formule> geefFormules() {
        ObservableList<Formule> formules = FXCollections.observableArrayList(Arrays.asList(Formule.values()));
        return FXCollections.unmodifiableObservableList(formules);
    }

    public ObservableList<String> geefOefeningNamen() {
        ObservableList<String> oefeningNamenSorted = FXCollections.observableArrayList(oefeningen.stream()
                .map(Oefening::getTitel)
                .distinct()
                .sorted(Comparator.comparing(String::toString))
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(oefeningNamenSorted);
    }

    public Oefening geefOefeningOpTitel(String titel) {
        Oefening oefening = oefeningen.stream()
                .filter(o -> o.getTitel().equals(titel))
                .findAny()
                .orElse(null);
        return oefening;
    }

    //TESTMETHODS FILLING DATA
    //TestMethod fill data
    private void fillTestData() {
        DataInitializer.initializeData(inschrijvingen, activiteiten, aanwezigheden, leden, raadplegingen, oefeningen);
    }

}
