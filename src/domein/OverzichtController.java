/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import persistentie.DataInitializer;

/**
 *
 * @author robdeputter
 */
public class OverzichtController {

    private List<Inschrijving> inschrijvingen;
    private List<Activiteit> activiteiten;
    private List<Aanwezigheid> aanwezigheden;
    private List<Lid> leden;
    private List<Raadpleging> raadplegingen;

    public OverzichtController() {
        this.inschrijvingen = new ArrayList<>();
        this.activiteiten = new ArrayList<>();
        this.aanwezigheden = new ArrayList<>();
        this.leden = new ArrayList<>();
        this.raadplegingen = new ArrayList<>();
        DataInitializer.initializeData(inschrijvingen, activiteiten, aanwezigheden, leden, raadplegingen);
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

    // Is het wel nodig om de naam van je methode zo expliciet uit te schrijven? Volstaat method overloading niet?
    
    public List<Inschrijving> geefOverzichtInschrijvingenVoorBepaaldInterval(LocalDate van, LocalDate tot) {
        return inschrijvingen.stream()
                .filter(inschrijving
                        -> inschrijving.getTijdstip().compareTo(tot) <= 0
                && inschrijving.getTijdstip().compareTo(van) >= 0)
                .collect(Collectors.toList());
    }

    public List<Activiteit> geefOverzichtActiviteiten() {
        return activiteiten;
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
}