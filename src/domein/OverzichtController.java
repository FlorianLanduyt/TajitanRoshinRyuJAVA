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
    private ObservableList<Lid> leden;
    private List<Raadpleging> raadplegingen;

    public OverzichtController() {
        this.inschrijvingen = new ArrayList<>();
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

    public ObservableList<Activiteit> geefOverzichtActiviteitenVoorBepaaldeDeelnemer(Lid lid) {
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(activiteiten.stream()
                .filter(activiteit -> activiteit.getDeelnemers().contains(lid))
                .collect(Collectors.toList())));
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

    public ObservableList<Lid> geefOverzichtLeden() {
        return FXCollections.unmodifiableObservableList(
                FXCollections.observableArrayList(leden
                        .stream()
                        .sorted(Comparator.comparing(Lid::getVoornaam).thenComparing(Lid::getAchternaam))
                        .collect(Collectors.toList())));
    }

    public Lid geefLidDoorRijksregisternr(String rijksregisternr) {
        return leden
                .stream()
                .filter(l -> l.getRijksregisterNr().equals(rijksregisternr))
                .findAny()
                .orElse(null);
    }

    //TESTMETHODS FILLING DATA
    //TestMethod fill data
    private void fillTestData() {
        Lid lid1 = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-003.21", LocalDate.now(),
                "0479330959", "051303050", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", "Dan-1");
        Lid lid2 = new Lid("Tybo", "Vanderstraeten", LocalDate.of(1999, Month.DECEMBER, 8),
                "99.12.10-007.41", LocalDate.now(),
                "0479365887", "098556880", "Prinses Clementinalaan",
                "11", "9980", "tybo.vanderstraeten@outlook.com",
                "TomatoSoup", "Gent", "Man",
                "Belg", "Kyu-2");
        Lid lid3 = new Lid("Mark", "Witthaker", LocalDate.of(1975, Month.JUNE, 6),
                "75.12.10-007.41", LocalDate.now(),
                "0478365887", "018556880", "Prinses Mandarijnalaan",
                "45", "9000", "mark.witthaker@outlook.com",
                "MyMusicSucks4", "Gent", "Man",
                "Belg", "Kyu-2");
        Lid lid4 = new Lid("Florian", "Landuyt", LocalDate.of(1995, Month.DECEMBER, 12),
                "95.12.12-007.41", LocalDate.now(),
                "0479865887", "088556880", "Kerkstraat",
                "141", "8770", "florian.landuyt@outlook.com",
                "TurnenIsLeuk8", "Gent", "Man",
                "Belg", "Dan-2");
        Lid lid5 = new Lid("Rob", "De Putter", LocalDate.of(1999, Month.MARCH, 12),
                "99.03.12-007.41", LocalDate.now(),
                "0478899964", "054556880", "Schoolstraat",
                "110", "9600", "rob.deputter@hotmail.com",
                "TurnenIsLeuk8", "Gent", "Vrouw",
                "Belg", "Kyu-2");

        leden.add(lid1);
        leden.add(lid2);
        leden.add(lid3);
        leden.add(lid4);
        leden.add(lid5);

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

        Aanwezigheid a1 = new Aanwezigheid(lid3, l1, 5);
        Aanwezigheid a2 = new Aanwezigheid(lid4, l1, 5);
        Aanwezigheid a3 = new Aanwezigheid(lid1, l2, 5);
        Aanwezigheid a4 = new Aanwezigheid(lid2, l2, 5);
        Aanwezigheid a5 = new Aanwezigheid(lid5, l3, 5);

        aanwezigheden.add(a1);
        aanwezigheden.add(a2);
        aanwezigheden.add(a3);
        aanwezigheden.add(a4);
        aanwezigheden.add(a5);
    }

}
