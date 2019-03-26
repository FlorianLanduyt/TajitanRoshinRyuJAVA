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
import domein.enums.LeeftijdsCategorie;
import gui.overzichten.ActiviteitenOverzicht;
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
    private Comparator<Inschrijving> byFamilienaamInschrijving = (i1, i2) -> i1.getLid().getAchternaam().compareTo(i2.getLid().getAchternaam());
    private Comparator<Inschrijving> byVoornaamInschrijving = (i1, i2) -> i1.getLid().getVoornaam().compareTo(i2.getLid().getVoornaam());
    private Comparator<Inschrijving> inschrijvingsSortOrder = byFamilienaamInschrijving.thenComparing(byVoornaamInschrijving).thenComparing(byTijdstip.reversed());

    private ObservableList<Activiteit> activiteiten;
    private FilteredList<Activiteit> activiteitenFilteredList;
    private SortedList<Activiteit> activiteitenSortedList;
    private Comparator<Activiteit> byBeginDatum = (a1, a2) -> a1.getBeginDatum().compareTo(a2.getBeginDatum());
    private Comparator<Activiteit> activiteitSortOrder = byBeginDatum.reversed();

    private ObservableList<Aanwezigheid> aanwezigheden;
    private FilteredList<Aanwezigheid> aanwezighedenFilteredList;
    private SortedList<Aanwezigheid> aanwezighedenSortedList;
    private Comparator<Aanwezigheid> byDatum = (a1, a2) -> a1.getDatum().compareTo(a2.getDatum());
    private Comparator<Aanwezigheid> byVoornaam = (a1, a2) -> a1.getLid().getVoornaam().compareTo(a2.getLid().getVoornaam());
    private Comparator<Aanwezigheid> byFamilienaam = (a1, a2) -> a1.getLid().getAchternaam().compareTo(a2.getLid().getAchternaam());
    private Comparator<Aanwezigheid> sortOrderAanwezigheid = byDatum.reversed().thenComparing(byFamilienaam).thenComparing(byVoornaam);

    private ObservableList<Lid> leden; //nodig voor cbo's

    private ObservableList<Raadpleging> raadplegingen;
    private FilteredList<Raadpleging> raadplegingenFilteredList;
    private SortedList<Raadpleging> raadplegingenSortedList;
    private Comparator<Raadpleging> byOefnnaam = (r1, r2) -> r1.getOefeningNaam().compareTo(r2.getOefeningNaam());
    private Comparator<Raadpleging> byFamilienaamRaadpleging = (i1, i2) -> i1.getLid().getAchternaam().compareTo(i2.getLid().getAchternaam());
    private Comparator<Raadpleging> byVoornaamRaadpleging = (i1, i2) -> i1.getLid().getVoornaam().compareTo(i2.getLid().getVoornaam());
    private Comparator<Raadpleging> sortOrderRaadpleging = byFamilienaamRaadpleging.thenComparing(byVoornaamRaadpleging).thenComparing(byOefnnaam);

    private ObservableList<Oefening> oefeningen;
    private ObservableList<Formule> formulesVoorInschrijving;

    private ObservableList<Lid> ledenClubkampioenschap;
    private FilteredList<Lid> ledenClubkampioenschapFiltered;

    public OverzichtController() {
        dataController = new DataController();

        this.inschrijvingen = FXCollections.observableArrayList(dataController.geefInschrijvingen());
        inschrijvingenFilteredList = new FilteredList(inschrijvingen, p -> true);
        inschrijvingenSortedList = new SortedList(inschrijvingenFilteredList, inschrijvingsSortOrder);

        this.activiteiten = FXCollections.observableArrayList(dataController.geefActiviteiten());
        activiteitenFilteredList = new FilteredList(activiteiten, p -> true);
        activiteitenSortedList = new SortedList(activiteitenFilteredList, activiteitSortOrder);

        this.aanwezigheden = FXCollections.observableArrayList(dataController.geefAanwezigheden());
        aanwezighedenFilteredList = new FilteredList(aanwezigheden, p -> true);
        aanwezighedenSortedList = new SortedList(aanwezighedenFilteredList, sortOrderAanwezigheid);

        this.leden = FXCollections.observableArrayList(dataController.geefLeden());

        this.raadplegingen = FXCollections.observableArrayList(dataController.geefRaadplegingen());
        raadplegingenFilteredList = new FilteredList(raadplegingen, p -> true);
        raadplegingenSortedList = new SortedList(raadplegingenFilteredList, sortOrderRaadpleging);

        this.oefeningen = FXCollections.observableArrayList(dataController.geefOefeningen());

        ledenClubkampioenschap = FXCollections.observableArrayList();

    }

    //
    //AANWEZIGHEDEN
    //
    public ObservableList<Aanwezigheid> geefOverzichtAanwezigheden() {
        return FXCollections.unmodifiableObservableList(aanwezighedenSortedList);
    }

    public void veranderAanwezigheidFilter(LocalDate datum, String familienaam, String voornaam, Formule formule) {
        aanwezighedenFilteredList.setPredicate(aanwezigheid -> {
            boolean datumEmpty = datum == null;
            boolean voornaamEmpty = voornaam == null;
            boolean familienaamEmpty = familienaam == null;
            boolean formuleEmpty = formule == null;

            boolean datumFilter = aanwezigheid.getDatum().equals(datum);
            boolean voornaamFilter = aanwezigheid.getLid().getVoornaam().toLowerCase().equals(voornaam.toLowerCase()) || aanwezigheid.getLid().getVoornaam().toLowerCase().startsWith(voornaam.toLowerCase());
            boolean familieNaamFilter = aanwezigheid.getLid().getAchternaam().toLowerCase().equals(familienaam.toLowerCase()) || aanwezigheid.getLid().getAchternaam().toLowerCase().startsWith(familienaam.toLowerCase());
            boolean formuleFilter = aanwezigheid.getFormule().equals(formule);

            //0000
            if (datumEmpty && formuleEmpty && familienaamEmpty && voornaamEmpty) {
                return true;
            }
            //0001
            if (datumEmpty && formuleEmpty && familienaamEmpty && !voornaamEmpty) {
                return voornaamFilter;
            }
            //0010
            if (datumEmpty && formuleEmpty && !familienaamEmpty && voornaamEmpty) {
                return familieNaamFilter;
            }
            //0011
            if (datumEmpty && formuleEmpty && !familienaamEmpty && !voornaamEmpty) {
                return familieNaamFilter && voornaamFilter;
            }
            //0100
            if (datumEmpty && !formuleEmpty && familienaamEmpty && voornaamEmpty) {
                return formuleFilter;
            }
            //0101
            if (datumEmpty && !formuleEmpty && familienaamEmpty && !voornaamEmpty) {
                return formuleFilter && voornaamFilter;
            }
            //0110
            if (datumEmpty && !formuleEmpty && !familienaamEmpty && voornaamEmpty) {
                return formuleFilter && familieNaamFilter;
            }
            //0111
            if (datumEmpty && !formuleEmpty && !familienaamEmpty && !voornaamEmpty) {
                return formuleFilter && familieNaamFilter && voornaamFilter;
            }
            //1000
            if (!datumEmpty && formuleEmpty && familienaamEmpty && voornaamEmpty) {
                return datumFilter;
            }
            //1001
            if (!datumEmpty && formuleEmpty && familienaamEmpty && !voornaamEmpty) {
                return datumFilter && voornaamFilter;
            }
            //1010
            if (!datumEmpty && formuleEmpty && !familienaamEmpty && voornaamEmpty) {
                return datumFilter && familieNaamFilter;
            }
            //1011
            if (!datumEmpty && formuleEmpty && !familienaamEmpty && !voornaamEmpty) {
                return datumFilter && familieNaamFilter && voornaamFilter;
            }
            //1100
            if (!datumEmpty && !formuleEmpty && familienaamEmpty && voornaamEmpty) {
                return datumFilter && formuleFilter;
            }
            //1101
            if (!datumEmpty && !formuleEmpty && familienaamEmpty && !voornaamEmpty) {
                return datumFilter && formuleFilter && voornaamFilter;
            }
            //1110
            if (!datumEmpty && !formuleEmpty && !familienaamEmpty && voornaamEmpty) {
                return datumFilter && formuleFilter && familieNaamFilter;
            }
            //1111
            if (!datumEmpty && !formuleEmpty && !familienaamEmpty && !voornaamEmpty) {
                return datumFilter && formuleFilter && familieNaamFilter && voornaamFilter;
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
            if (!formuleEmpty && !vanEmpty && totEmpty) {
                return formuleFilter && vanFilter;
            }
            //111
            if (!formuleEmpty && !vanEmpty && !totEmpty) {
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
        ledenClubkampioenschap = FXCollections.observableArrayList(leden.stream()
                .sorted(Comparator.comparing(Lid::getPuntenAantal).reversed())
                .collect(Collectors.toList()));

        ledenClubkampioenschapFiltered = new FilteredList(ledenClubkampioenschap, p -> true);
        return FXCollections.unmodifiableObservableList(ledenClubkampioenschapFiltered);
    }

    public void veranderFilterClubkampioenschap(LeeftijdsCategorie leeftijdsCategorie) {
        ledenClubkampioenschapFiltered.setPredicate((lid) -> {
            boolean leeftijdsCategorieEmpty = leeftijdsCategorie == null;
            boolean leeftijdCategorieFilter = lid
                    .getLeeftijdsCategoriën()
                    .stream()
                    .anyMatch(leef -> leef.equals(leeftijdsCategorie));

            if (!leeftijdsCategorieEmpty) {
                return leeftijdCategorieFilter;
            }
            return true;
        });

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

    public ObservableList<Aanwezigheid> geefAanwezighedenVoorLid(Lid lid) {
        List<Aanwezigheid> aanwezighedenVoorLid = dataController.geefAanwezigheden().stream()
                .filter(aanwezigheid -> aanwezigheid
                .getLid().equals(lid))
                .sorted(Comparator.comparing(Aanwezigheid::getPuntenAantal).reversed())
                .collect(Collectors.toList());

        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(aanwezighedenVoorLid));
    }

    public ObservableList<String> geefLeeftijdsCategoriën() {
        ObservableList<String> leeftijdsCategoriën = FXCollections.observableArrayList(dataController
                .geefLeeftijdsCategoriën().stream().map(lc -> lc.getDisplayName())
                .collect(Collectors.toList()));
        leeftijdsCategoriën.add(0, "Alle leeftijdscategoriën");
        return leeftijdsCategoriën;
    }

    //
    //RAADPLEGINGEN
    //
    public ObservableList<Raadpleging> geefOverzichtRaadplegingen() {
        return FXCollections.unmodifiableObservableList(raadplegingenSortedList);
    }

    public void veranderRaadplegingFilter(String lidVoornaam, String lidFamilienaam, Oefening oefening, LocalDate van, LocalDate tot) {
        raadplegingenFilteredList.setPredicate(raadpleging -> {
            boolean lidVoornaamEmpty = lidVoornaam == null;
            boolean lidFamilienaamEmpty = lidFamilienaam == null;
            boolean oefeningEmpty = oefening == null;
            boolean vanEmpty = van == null;
            boolean totEmpty = tot == null;

            boolean voornaamFilter = raadpleging.getLid().getVoornaam().toLowerCase().equals(lidVoornaam.toLowerCase()) || raadpleging.getLid().getVoornaam().toLowerCase().startsWith(lidVoornaam.toLowerCase());
            boolean familieNaamFilter = raadpleging.getLid().getAchternaam().toLowerCase().equals(lidFamilienaam.toLowerCase()) || raadpleging.getLid().getAchternaam().toLowerCase().startsWith(lidFamilienaam.toLowerCase());
            boolean oefeningFilter = raadpleging.getOefening().equals(oefening);
            boolean vanFilter = vanEmpty ? false : raadpleging.getTijdstippen().get(raadpleging.getTijdstippen().size() - 1).compareTo(van) >= 0;
            boolean totFilter = totEmpty ? false : raadpleging.getTijdstippen().get(raadpleging.getTijdstippen().size() - 1).compareTo(tot) <= 0;

            //00000
            if (vanEmpty && totEmpty && lidVoornaamEmpty && lidFamilienaamEmpty && oefeningEmpty) {
                return true;
            }
            //00001
            if (vanEmpty && totEmpty && lidVoornaamEmpty && lidFamilienaamEmpty && !oefeningEmpty) {
                return oefeningFilter;
            }
            //00010
            if (vanEmpty && totEmpty && lidVoornaamEmpty && !lidFamilienaamEmpty && oefeningEmpty) {
                return familieNaamFilter;
            }
            //00011
            if (vanEmpty && totEmpty && lidVoornaamEmpty && !lidFamilienaamEmpty && !oefeningEmpty) {
                return familieNaamFilter && oefeningFilter;
            }
            //00100
            if (vanEmpty && totEmpty && !lidVoornaamEmpty && lidFamilienaamEmpty && oefeningEmpty) {
                return voornaamFilter;
            }
            //00101
            if (vanEmpty && totEmpty && !lidVoornaamEmpty && lidFamilienaamEmpty && !oefeningEmpty) {
                return voornaamFilter && oefeningFilter;
            }
            //00110
            if (vanEmpty && totEmpty && !lidVoornaamEmpty && !lidFamilienaamEmpty && oefeningEmpty) {
                return voornaamFilter && familieNaamFilter;
            }
            //00111
            if (vanEmpty && totEmpty && !lidVoornaamEmpty && !lidFamilienaamEmpty && !oefeningEmpty) {
                return voornaamFilter && familieNaamFilter && oefeningFilter;
            }

            //01000
            if (vanEmpty && !totEmpty && lidVoornaamEmpty && lidFamilienaamEmpty && oefeningEmpty) {
                return totFilter;
            }
            //01001
            if (vanEmpty && !totEmpty && lidVoornaamEmpty && lidFamilienaamEmpty && !oefeningEmpty) {
                return totFilter && oefeningFilter;
            }
            //01010
            if (vanEmpty && !totEmpty && lidVoornaamEmpty && !lidFamilienaamEmpty && oefeningEmpty) {
                return totFilter && familieNaamFilter;
            }
            //01011
            if (vanEmpty && !totEmpty && lidVoornaamEmpty && !lidFamilienaamEmpty && !oefeningEmpty) {
                return totFilter && familieNaamFilter && oefeningFilter;
            }
            //01100
            if (vanEmpty && !totEmpty && !lidVoornaamEmpty && lidFamilienaamEmpty && oefeningEmpty) {
                return totFilter && voornaamFilter;
            }
            //01101
            if (vanEmpty && !totEmpty && !lidVoornaamEmpty && lidFamilienaamEmpty && !oefeningEmpty) {
                return totFilter && voornaamFilter && oefeningFilter;
            }
            //01110
            if (vanEmpty && !totEmpty && !lidVoornaamEmpty && !lidFamilienaamEmpty && oefeningEmpty) {
                return totFilter && voornaamFilter && familieNaamFilter;
            }
            //01111
            if (vanEmpty && !totEmpty && !lidVoornaamEmpty && !lidFamilienaamEmpty && !oefeningEmpty) {
                return totFilter && voornaamFilter && familieNaamFilter && oefeningFilter;
            }
            //10000
            if (!vanEmpty && totEmpty && lidVoornaamEmpty && lidFamilienaamEmpty && oefeningEmpty) {
                return vanFilter;
            }
            //10001
            if (!vanEmpty && totEmpty && lidVoornaamEmpty && lidFamilienaamEmpty && !oefeningEmpty) {
                return vanFilter && oefeningFilter;
            }
            //10010
            if (!vanEmpty && totEmpty && lidVoornaamEmpty && !lidFamilienaamEmpty && oefeningEmpty) {
                return vanFilter && familieNaamFilter;
            }
            //10011
            if (!vanEmpty && totEmpty && lidVoornaamEmpty && !lidFamilienaamEmpty && !oefeningEmpty) {
                return vanFilter && familieNaamFilter && oefeningFilter;
            }
            //10100
            if (!vanEmpty && totEmpty && !lidVoornaamEmpty && lidFamilienaamEmpty && oefeningEmpty) {
                return vanFilter && voornaamFilter;
            }
            //10101
            if (!vanEmpty && totEmpty && !lidVoornaamEmpty && lidFamilienaamEmpty && !oefeningEmpty) {
                return vanFilter && voornaamFilter && oefeningFilter;
            }
            //10110
            if (!vanEmpty && totEmpty && !lidVoornaamEmpty && !lidFamilienaamEmpty && oefeningEmpty) {
                return vanFilter && voornaamFilter && familieNaamFilter;
            }
            //10111
            if (!vanEmpty && totEmpty && !lidVoornaamEmpty && !lidFamilienaamEmpty && !oefeningEmpty) {
                return vanFilter && voornaamFilter && familieNaamFilter && oefeningFilter;
            }
            //11000
            if (!vanEmpty && !totEmpty && lidVoornaamEmpty && lidFamilienaamEmpty && oefeningEmpty) {
                return vanFilter && totFilter;
            }
            //11001
            if (!vanEmpty && !totEmpty && lidVoornaamEmpty && lidFamilienaamEmpty && !oefeningEmpty) {
                return vanFilter && totFilter && oefeningFilter;
            }
            //11010
            if (!vanEmpty && !totEmpty && lidVoornaamEmpty && !lidFamilienaamEmpty && oefeningEmpty) {
                return vanFilter && totFilter && familieNaamFilter;
            }
            //11011
            if (!vanEmpty && !totEmpty && lidVoornaamEmpty && !lidFamilienaamEmpty && !oefeningEmpty) {
                return vanFilter && totFilter && familieNaamFilter && oefeningFilter;
            }
            //11100
            if (!vanEmpty && !totEmpty && !lidVoornaamEmpty && lidFamilienaamEmpty && oefeningEmpty) {
                return vanFilter && totFilter && voornaamFilter;
            }
            //11101
            if (!vanEmpty && !totEmpty && !lidVoornaamEmpty && lidFamilienaamEmpty && !oefeningEmpty) {
                return vanFilter && totFilter && voornaamFilter && oefeningFilter;
            }
            //11110
            if (!vanEmpty && !totEmpty && !lidVoornaamEmpty && !lidFamilienaamEmpty && oefeningEmpty) {
                return vanFilter && totFilter && voornaamFilter && familieNaamFilter;
            }
            //11111
            if (!vanEmpty && !totEmpty && !lidVoornaamEmpty && !lidFamilienaamEmpty && !oefeningEmpty) {
                return vanFilter && totFilter && voornaamFilter && familieNaamFilter && oefeningFilter;
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

    public ObservableList<String> geefOverzichtLedenFilter() {
        ObservableList<String> leden = FXCollections.observableArrayList(dataController
                .geefLeden().stream().map(lid -> String.format("%s %s", lid.getVoornaam(), lid.getAchternaam()))
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

    public ObservableList<Formule> geefFormulesPerLid(Lid lid) {
        return FXCollections.observableArrayList(dataController.geefFormulesVanLid(lid));
    }

    public ObservableList<Activiteit> geefActiviteitenVoorInschrijving(Inschrijving inschrijving) {
       List<Activiteit> activiteitenVoorInschrijving = dataController.geefActiviteiten().stream()
               .filter(a -> a.getInschrijvingen().contains(inschrijving))
               .collect(Collectors.toList());
            

        return FXCollections.observableArrayList(activiteitenVoorInschrijving);
    }
}
