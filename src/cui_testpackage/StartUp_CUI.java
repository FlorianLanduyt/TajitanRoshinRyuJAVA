/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui_testpackage;

import domein.Aanwezigheid;
import domein.Admin;
import domein.AdminController;
import domein.Formule;
import domein.Inschrijving;
import domein.OverzichtController;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import persistentie.DataInitializer;

/**
 *
 * @author robdeputter
 */
public class StartUp_CUI {

    /*
    DISCLAIMER
    This class will be used to quickly test some code on-the-fly
     */
    public static void main(String[] args) {
        OverzichtController oc = new OverzichtController();
        AdminController ac = new AdminController();

        //DataInitializer aanroepen
        DataInitializer.initializeData(oc.geefOverzichtInschrijvingen(), oc.geefOverzichtActiviteiten(),
                oc.geefOverzichtAanwezigheden(), oc.geefOverzichtLeden(), oc.geefOverzichtRaadplegingen());

        //Testen van formules Aanwezigheden
        System.out.println(geefOverzichtAanwezighedenInString(oc.geefOverzichtAanwezigheden()));

        System.out.println(geefOverzichtAanwezighedenInString(oc.geefOverzichtAanwezighedenVoorBepaaldeDatum(LocalDate.of(2019, Month.MARCH, 12))));

        System.out.println(geefOverzichtAanwezighedenInString(oc.geefOverzichtAanwezighedenVoorBepaaldeFormule(Formule.WO_ZA)));

        System.out.println(geefOverzichtInschrijvingen(oc.geefOverzichtInschrijvingen()));

        System.out.println(geefOverzichtInschrijvingen(oc.geefOverzichtInschrijvingenVoorBepaaldeFormule(Formule.WO_ZA)));

        //Testen aanmelden en afmelden admin
        System.out.printf("Expected = geen admin aangemeld.%nResult = %s%n%n", geefAangemeldeAdmin(ac));
        System.out.printf("Expected = 0.%nResult = %d%n%n", ac.getAdmins().size());

        Admin admin = new Admin("admin", "admin");
        ac.voegAdminToe(admin);

        System.out.printf("Expected = 1.%nResult = %d%n%n", ac.getAdmins().size());

        ac.aanmelden(admin);
        System.out.printf("Expected = admin.%nResult = %s%n%n", geefAangemeldeAdmin(ac));

        ac.afmelden(admin);
        ac.verwijderAdmin(admin);
        System.out.printf("Expected = geen admin aangemeld.%nResult = %s%n%n", geefAangemeldeAdmin(ac));
        System.out.printf("Expected = 0.%nResult = %d%n%n", ac.getAdmins().size());
    }

    public static String geefOverzichtAanwezighedenInString(List<Aanwezigheid> lijst) {
        String uitvoer = String.format("%20s %20s %20s %20s%n",
                "Naam_lid",
                "Naam_activiteit",
                "Datum_activiteit",
                "Aantal_punten");

        uitvoer += lijst.stream().map(aanwezigheid -> String.format("%20s %20s %20s %20d%n",
                aanwezigheid.getLid().getVoornaam() + " " + aanwezigheid.getLid().getAchternaam(),
                aanwezigheid.getActiviteit().getNaam(),
                aanwezigheid.getActiviteit().getDatum().toString(),
                aanwezigheid.getPuntenAantal()))
                .collect(Collectors.joining("\n"));

        return uitvoer;
    }

    public static String geefOverzichtInschrijvingen(List<Inschrijving> lijst) {
        String uitvoer = String.format("%20s %20s %20s%n",
                "Naam_lid",
                "Formule",
                "Datum_inschrijving");

        uitvoer += lijst.stream().map(inschrijving -> String.format("%20s %20s %20s%n",
                inschrijving.getLid().getVoornaam() + " " + inschrijving.getLid().getAchternaam(),
                inschrijving.getFormule(),
                inschrijving.getTijdstip().toString()))
                .collect(Collectors.joining("\n"));

        return uitvoer;
    }

    public static String geefAangemeldeAdmin(AdminController ac) {
        if (ac.getAangemeldeAdmin() == null) {
            return String.format("Geen admin aangemeld!");
        }
        return String.format("%s", ac.getAangemeldeAdmin().getGebruikersnaam());
    }
}
