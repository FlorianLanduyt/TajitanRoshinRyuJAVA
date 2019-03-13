package domein;

import domein.controllers.ActiviteitBeheerController;
import domein.enums.Formule;
import domein.enums.Functie;
import domein.enums.Graad;
import exceptions.VolzetException;
import java.time.LocalDate;
import java.time.Month;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InschrijvingTest {

    Lid lid1;
    Lid lid2;
    Activiteit activiteitStage;
    Activiteit activiteitWo1;
    Activiteit activiteitWo2;
    ActiviteitBeheerController ac;

    @Before
    public void setUp() {
        lid1 = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-357.55", LocalDate.now().minusYears(18),
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.LID);

        lid2 = new Lid("Tybo", "Vanderstraeten", LocalDate.of(1999, Month.DECEMBER, 8),
                "99.12.08.173.04", LocalDate.now().minusYears(18),
                "0479365887", "098556880", "Kortrijk", "Prinses Clementinalaan",
                "11", "9980", "tybo.vanderstraeten@outlook.com",
                "TomatoSoup", "Gent", "Man",
                "Belg", Graad.KYU3, Functie.LID);

        activiteitStage = new Activiteit("Testactiviteit", Formule.STAGE,
                15, LocalDate.of(2020, Month.DECEMBER, 20), LocalDate.of(2020, Month.DECEMBER, 15));

        activiteitWo1 = new Activiteit("Woensdag1", Formule.WO, 15, LocalDate.of(2020, Month.DECEMBER, 20), LocalDate.of(2020, Month.DECEMBER, 15));
        activiteitWo2 = new Activiteit("Woensdag2", Formule.WO, 15, LocalDate.of(2020, Month.DECEMBER, 27), LocalDate.of(2020, Month.DECEMBER, 22));

        ac = new ActiviteitBeheerController();
    }

    @After
    public void reset() {
        lid1 = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-357.55", LocalDate.now().minusYears(18),
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.LID);

        lid2 = new Lid("Tybo", "Vanderstraeten", LocalDate.of(1999, Month.DECEMBER, 8),
                "99.12.08.173.04", LocalDate.now().minusYears(18),
                "0479365887", "098556880", "Kortrijk", "Prinses Clementinalaan",
                "11", "9980", "tybo.vanderstraeten@outlook.com",
                "TomatoSoup", "Gent", "Man",
                "Belg", Graad.KYU3, Functie.LID);

        activiteitStage = new Activiteit("Testactiviteit", Formule.STAGE,
                15, LocalDate.of(2020, Month.DECEMBER, 20), LocalDate.of(2020, Month.DECEMBER, 15));

        activiteitWo1 = new Activiteit("Woensdag1", Formule.WO, 15, LocalDate.of(2020, Month.DECEMBER, 20), LocalDate.of(2020, Month.DECEMBER, 15));
        activiteitWo2 = new Activiteit("Woensdag2", Formule.WO, 15, LocalDate.of(2020, Month.DECEMBER, 27), LocalDate.of(2020, Month.DECEMBER, 22));

        ac = new ActiviteitBeheerController();
    }

    //Inschrijving voor een eenmalige activiteit -- testen
    @Test
    public void inschrijvenActiviteitStage_Lid1_Success() {
        ac.voegInschrijvingToe(activiteitStage, lid1);
        Assert.assertEquals(1, activiteitStage.getInschrijvingen().size());
    }

    @Test
    public void inschrijvenActiviteitStage_Lid2_Success() {
        ac.voegInschrijvingToe(activiteitStage, lid2);
        Assert.assertEquals(1, activiteitStage.getInschrijvingen().size());
    }

    @Test
    public void inschrijvenActiviteitStage_BeideLeden_Success() {
        ac.voegInschrijvingToe(activiteitStage, lid1);
        ac.voegInschrijvingToe(activiteitStage, lid2);
        Assert.assertEquals(2, activiteitStage.getInschrijvingen().size());
    }

    @Test(expected = VolzetException.class)
    public void inschrijvenActiviteitStage_BeideLeden_Capaciteit1_ThrowsVolzetException() {
        activiteitStage.setMaxDeelnemers(1);
        ac.voegInschrijvingToe(activiteitStage, lid1);
        ac.voegInschrijvingToe(activiteitStage, lid2);
    }

    //Verwijderen van een inschrijving 
    @Test
    public void verwijderenInschrijving_ActiviteitStage_Lid1_Success() {
        ac.voegInschrijvingToe(activiteitStage, lid1);
        Assert.assertEquals(1, activiteitStage.getInschrijvingen().size());
        ac.verwijderInschrijving(activiteitStage, lid1);
        Assert.assertEquals(0, activiteitStage.getInschrijvingen().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void verwijderenInschrijving_ActiviteitStage_InschrijvingBestaatNiet_ThrowsIllegalArgumentException() {
        ac.verwijderInschrijving(activiteitStage, lid1);
    }
}
