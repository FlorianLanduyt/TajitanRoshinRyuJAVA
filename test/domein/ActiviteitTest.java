package domein;

import domein.enums.Formule;
import domein.enums.Functie;
import domein.enums.Graad;
import exceptions.VolzetException;
import java.lang.annotation.Repeatable;
import java.time.LocalDate;
import java.time.Month;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActiviteitTest {

    Lid lid;
    Lid lid2;
    Activiteit activiteit;
    Inschrijving inschrijving;
    Inschrijving inschrijving2;

    @Before
    public void setUp() {
        lid = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-357.55", LocalDate.now().minusYears(18),
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

        activiteit = new Activiteit("Testactiviteit", Formule.ACTIVITEIT,
                15, LocalDate.of(2019, Month.DECEMBER, 20));

        inschrijving = new Inschrijving(Formule.ACTIVITEIT, lid, LocalDate.now());
        Inschrijving tweedeInschrijving = new Inschrijving(Formule.ACTIVITEIT, lid2, LocalDate.now());

    }

    @After
    public void reset() {
        lid = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-357.55", LocalDate.now().minusYears(18),
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.LID);

        activiteit = new Activiteit("Testactiviteit", Formule.ACTIVITEIT,
                1, LocalDate.now());

        inschrijving = new Inschrijving(Formule.ACTIVITEIT, lid, LocalDate.now());
    }

    @Test
    public void activiteitHeeftInschrijving() {
        inschrijving.voegActiviteitToe(activiteit);
        Assert.assertEquals(true, activiteit.getInschrijvingen().size() == 1);
    }

    @Test(expected = VolzetException.class)
    public void activiteitVolzet_InschrijvingNietToegevoegd() {
        inschrijving.voegActiviteitToe(activiteit); // de eerste wordt toegevoegd, de volgende zal niet meer lukken

        inschrijving2.voegActiviteitToe(activiteit);

    }
}
