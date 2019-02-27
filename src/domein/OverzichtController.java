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
    private List<Aanwezigheid> aanwezigheden;
    private ObservableList<Lid> leden;
    private List<Raadpleging> raadplegingen;

    public OverzichtController() {
        this.inschrijvingen = FXCollections.observableArrayList();
        this.activiteiten = FXCollections.observableArrayList();
        this.aanwezigheden = new ArrayList<>();
        this.leden = FXCollections.observableArrayList();
        this.raadplegingen = new ArrayList<>();

        //Calling testdata method
        fillTestData();
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

    public ObservableList<Inschrijving> geefOverzichtInschrijvingen() {
        ObservableList<Inschrijving> inschrijvingenSortedDatum = FXCollections.observableArrayList(inschrijvingen.stream()
                .sorted(Comparator.comparing(Inschrijving::getTijdstip).reversed())
                .collect(Collectors.toList()));
        return inschrijvingenSortedDatum;
    }

    public ObservableList<Inschrijving> geefOverzichtInschrijvingenVoorBepaaldeFormule(Formule formule) {
        ObservableList<Inschrijving> inschrijvingenVoorFormule = FXCollections.observableArrayList(inschrijvingen.stream()
                .filter(inschrijving -> inschrijving.getFormule().equals(formule))
                .sorted(Comparator.comparing(Inschrijving::getTijdstip).reversed())
                .collect(Collectors.toList()));
        return inschrijvingenVoorFormule;
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
        return inschrijvingenVoorInterval;
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
        ObservableList<Lid> ledenGesorteerdOpPunten = FXCollections.observableArrayList(leden.stream()
                .sorted(Comparator.comparing(Lid::getPuntenAantal).reversed())
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(ledenGesorteerdOpPunten);
    }

    public void berekenPuntenLeden() {
        int aantalPunten = 0;
        leden.stream().forEach(lid -> {
            lid.setPuntenAantal(
                    aanwezigheden.stream()
                            .filter(aanwezigheid -> aanwezigheid.getLid().equals(lid))
                            .collect(Collectors.summingInt(a -> a.getPuntenAantal())));
        }
        );
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

    public ObservableList<Lid> geefOverzichtLeden() {
        return FXCollections.unmodifiableObservableList(
                FXCollections.observableArrayList(leden
                        .stream()
                        .sorted(Comparator.comparing(Lid::getVoornaam).thenComparing(Lid::getAchternaam))
                        .collect(Collectors.toList())));
    }

    public ObservableList<Formule> geefFormules() {
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(Arrays.asList(Formule.values())));
    }

    //TESTMETHODS FILLING DATA
    //TestMethod fill data
    private void fillTestData() {
        DataInitializer.initializeData(inschrijvingen, activiteiten, aanwezigheden, leden, raadplegingen);
    }

}
