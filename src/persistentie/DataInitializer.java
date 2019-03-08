/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Aanwezigheid;
import domein.Activiteit;
import domein.Admin;
import domein.enums.Formule;
import domein.enums.Graad;
import domein.Inschrijving;
import domein.Lid;
import domein.Oefening;
import domein.Raadpleging;
import domein.Thema;
import domein.enums.Functie;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 *
 * @author Tim
 */
public class DataInitializer {

    public static void initializeData(
            List<Inschrijving> inschrijvingen,
            List<Activiteit> activiteiten,
            List<Aanwezigheid> aanwezigheden,
            List<Lid> leden,
            List<Raadpleging> raadplegingen,
            List<Oefening> oefeningen, List<Admin> admins) {

        Lid lid1 = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-001.23", LocalDate.now().minusYears(18),
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.LID);
        Lid lid2 = new Lid("Tybo", "Vanderstraeten", LocalDate.of(1999, Month.DECEMBER, 8),
                "99.12.08.173.04", LocalDate.now().minusYears(18),
                "0479365887", "098556880", "Kortrijk", "Prinses Clementinalaan",
                "11", "9980", "tybo.vanderstraeten@outlook.com",
                "TomatoSoup", "Gent", "Man",
                "Belg", Graad.KYU3, Functie.LID);
        Lid lid3 = new Lid("Mark", "Witthaker", LocalDate.of(1975, Month.JUNE, 6),
                "75.06.06-001.78", LocalDate.now().minusYears(18),
                "0478365887", "018556880", "Gent", "Prinses Mandarijnalaan",
                "45", "9000", "mark.witthaker@outlook.com",
                "MyMusicSucks4", "Gent", "Man",
                "Belg", Graad.DAN1, Functie.LID);
        Lid lid4 = new Lid("Florian", "Landuyt", LocalDate.of(1995, Month.DECEMBER, 12),
                "95.12.12-001.69", LocalDate.now().minusYears(18),
                "0479865887", "088556880", "Deinze", "Kerkstraat",
                "141", "8770", "florian.landuyt@outlook.com",
                "TurnenIsLeuk8", "Gent", "Man",
                "Belg", Graad.KYU3, Functie.LID);
        Lid lid5 = new Lid("Rob", "De Putter", LocalDate.of(1999, Month.MARCH, 12),
                "99.03.12-002.89", LocalDate.now().minusYears(18),
                "0478899964", "054556880", "Waregem", "Schoolstraat",
                "110", "9600", "rob.deputter@hotmail.com",
                "TurnenIsLeuk8", "Gent", "Vrouw",
                "Belg", Graad.KYU5, Functie.LID);

        leden.add(lid1);
        leden.add(lid2);
        leden.add(lid3);
        leden.add(lid4);
        leden.add(lid5);

        Activiteit s1 = new Activiteit("Hoogtestage Ardennen", Formule.STAGE, 50, LocalDate.of(2019, Month.MARCH, 12));
        Activiteit s2 = new Activiteit("Hoogtestage Vogezen", Formule.STAGE, 50, LocalDate.of(2019, Month.AUGUST, 28));
        Activiteit s3 = new Activiteit("Uitstap Nederland", Formule.ACTIVITEIT, 50, LocalDate.of(2020, Month.JANUARY, 10));

        Activiteit l1 = new Activiteit("Les 1", Formule.ZA, 50, LocalDate.of(2019, Month.FEBRUARY, 23));
        Activiteit l2 = new Activiteit("Les 1", Formule.WO_ZA, 50, LocalDate.of(2019, Month.FEBRUARY, 20));
        Activiteit l3 = new Activiteit("Les 1", Formule.DI_ZA, 50, LocalDate.of(2020, Month.FEBRUARY, 19));

        activiteiten.add(s1);
        activiteiten.add(s2);
        activiteiten.add(s3);
        activiteiten.add(l1);
        activiteiten.add(l2);
        activiteiten.add(l3);

        activiteiten.forEach(a -> {
            a.setStad("Gent");
            a.setPostcode("9000");
            a.setStraat("Korenmarkt");
            a.setHuisnummer("20");
            a.setBus("5A");
        });

        Aanwezigheid a1 = new Aanwezigheid(lid3, l1);
        Aanwezigheid a2 = new Aanwezigheid(lid4, l1);
        Aanwezigheid a3 = new Aanwezigheid(lid1, l2);
        Aanwezigheid a4 = new Aanwezigheid(lid2, l2);
        Aanwezigheid a5 = new Aanwezigheid(lid5, l3);

        aanwezigheden.add(a1);
        aanwezigheden.add(a2);
        aanwezigheden.add(a3);
        aanwezigheden.add(a4);
        aanwezigheden.add(a5);

        Oefening oef1 = new Oefening("Schoppen", "www.youtube.com/schop", "schop.jpg", "maak een snelle voorwaartse beweging met je voet", new Thema("Techniek"));
        Oefening oef2 = new Oefening("Slaan", "www.youtube.com/sla", "sla.jpg", "maak een snelle voorwaartse beweging met je arm", new Thema("Techniek"));
        oefeningen.add(oef1);
        oefeningen.add(oef2);

        Raadpleging r1 = new Raadpleging(lid1, oef1);
        Raadpleging r2 = new Raadpleging(lid1, oef2);
        Raadpleging r3 = new Raadpleging(lid2, oef1);
        Raadpleging r4 = new Raadpleging(lid2, oef2);
        Raadpleging r5 = new Raadpleging(lid3, oef2);
        Raadpleging r6 = new Raadpleging(lid4, oef1);
        Raadpleging r7 = new Raadpleging(lid5, oef1);

        raadplegingen.add(r1);
        raadplegingen.add(r2);
        raadplegingen.add(r3);
        raadplegingen.add(r4);
        raadplegingen.add(r5);
        raadplegingen.add(r6);
        raadplegingen.add(r7);

        //Aantal raadplegingen met for loops
        for (int i = 0; i < 15; i++) {
            r1.verhoogAantalRaadplegingen();
            r3.verhoogAantalRaadplegingen();
        }

        for (int i = 0; i < 4; i++) {
            r2.verhoogAantalRaadplegingen();
        }

        Inschrijving i1 = new Inschrijving(Formule.WO_ZA, lid1, LocalDate.of(2019, Month.DECEMBER, 1));
        Inschrijving i2 = new Inschrijving(Formule.STAGE, lid2, LocalDate.of(2019, Month.DECEMBER, 5));
        Inschrijving i3 = new Inschrijving(Formule.STAGE, lid3, LocalDate.of(2019, Month.DECEMBER, 8));
        Inschrijving i4 = new Inschrijving(Formule.WO_ZA, lid4, LocalDate.of(2019, Month.DECEMBER, 12));
        Inschrijving i5 = new Inschrijving(Formule.WO_ZA, lid5, LocalDate.of(2019, Month.DECEMBER, 24));

        inschrijvingen.add(i1);
        inschrijvingen.add(i2);
        inschrijvingen.add(i3);
        inschrijvingen.add(i4);
        inschrijvingen.add(i5);

        l2.voegInschrijvingToe(i1);
        s1.voegInschrijvingToe(i2);
        s2.voegInschrijvingToe(i3);
        l2.voegInschrijvingToe(i4);
        l2.voegInschrijvingToe(i5);

        Admin tybo = new Admin("Tybo", "admin");
        Admin rob = new Admin("Rob", "admin");
        Admin florian = new Admin("Florian", "admin");
        Admin tim = new Admin("Tim", "admin");

        admins.add(tybo);
        admins.add(rob);
        admins.add(florian);
        admins.add(tim);
    }
}
