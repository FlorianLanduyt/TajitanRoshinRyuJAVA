package domein;

import domein.enums.Formule;
import domein.enums.Functie;
import domein.enums.Graad;
import exceptions.DatumIntervalException;
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

        lid2 = new Lid("Tybo", "Vanderstraeten", LocalDate.of(1999, Month.DECEMBER, 8),
                "99.12.08.173.04", LocalDate.now().minusYears(18),
                "0479365887", "098556880", "Kortrijk", "Prinses Clementinalaan",
                "11", "9980", "tybo.vanderstraeten@outlook.com",
                "TomatoSoup", "Gent", "Man",
                "Belg", Graad.KYU3, Functie.LID);

        activiteit = new Activiteit("Testactiviteit", Formule.ACTIVITEIT,
                1, LocalDate.of(2019, Month.DECEMBER, 20));

        inschrijving = new Inschrijving(Formule.ACTIVITEIT, lid, LocalDate.now());
        inschrijving2 = new Inschrijving(Formule.ACTIVITEIT, lid2, LocalDate.now());
    }

    @After
    public void reset() {
        lid = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
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

        activiteit = new Activiteit("Testactiviteit", Formule.ACTIVITEIT,
                1, LocalDate.of(2019, Month.DECEMBER, 20));

        inschrijving = new Inschrijving(Formule.ACTIVITEIT, lid, LocalDate.now());
        inschrijving2 = new Inschrijving(Formule.ACTIVITEIT, lid2, LocalDate.now());
    }

    @Test
    public void activiteitHeeftInschrijving() {
        inschrijving.voegActiviteitToe(activiteit);
        Assert.assertEquals(true, activiteit.getInschrijvingen().size() == 1);
    }

    @Test
    public void inschrijvingHeeftActiviteit() {
        inschrijving.voegActiviteitToe(activiteit);
        Assert.assertEquals(true, inschrijving.getActiviteiten().size() == 1);
    }

    @Test(expected = VolzetException.class)
    public void activiteitVolzet_InschrijvingNietToegevoegd_ThrowsVolzetException() {
        inschrijving.voegActiviteitToe(activiteit); // de eerste wordt toegevoegd, de volgende zal niet meer lukken
        inschrijving2.voegActiviteitToe(activiteit);
    }

    @Test
    public void activiteitVolzet_InschrijvingNietToegevoegd_InschrijvingHeeftGeenActiviteit() {
        inschrijving.voegActiviteitToe(activiteit); // de eerste wordt toegevoegd, de volgende zal niet meer lukken
        try {
            inschrijving2.voegActiviteitToe(activiteit);
        } catch (VolzetException ex) {
            Assert.assertEquals(true, inschrijving.getActiviteiten().size() == 1);
        }
    }

    @Test
    public void activiteitVolzet_InschrijvingNietToegevoegd_ActiviteitHeeft1Inschrijving() {
        inschrijving.voegActiviteitToe(activiteit); // de eerste wordt toegevoegd, de volgende zal niet meer lukken
        try {
            inschrijving2.voegActiviteitToe(activiteit);
        } catch (VolzetException ex) {
            Assert.assertEquals(true, activiteit.getInschrijvingen().size() == 1);
        }
    }

    @Test
    public void verwijderenInschrijving() {
        inschrijving.voegActiviteitToe(activiteit); // de eerste wordt toegevoegd, de volgende zal niet meer lukken
        Assert.assertEquals(true, activiteit.getInschrijvingen().size() == 1);
        Assert.assertEquals(true, inschrijving.getActiviteiten().size() == 1);
        inschrijving.verwijderActiviteit(activiteit);
        Assert.assertEquals(true, activiteit.getInschrijvingen().size() == 0);
        Assert.assertEquals(true, inschrijving.getActiviteiten().size() == 0);
    }

    //Setter tests
    //Naam
    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetNaam_Null_ThrowsIllegalArgumentException() {
        activiteit.setNaam(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetNaam_Empty_ThrowsIllegalArgumentException() {
        activiteit.setNaam("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetNaam_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 36; i++) {
            output.concat("a");
        }
        activiteit.setNaam(output);
    }

    @Test
    public void activiteit_SetNaam_Correct() {
        activiteit.setNaam("Meerdaagse hoogtebeproeving");
    }

    //Straat
    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetStraat_Null_ThrowsIllegalArgumentException() {
        activiteit.setStraat(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetStraat_Empty_ThrowsIllegalArgumentException() {
        activiteit.setStraat("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetStraat_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 51; i++) {
            output.concat("a");
        }
        activiteit.setStraat(output);
    }

    @Test
    public void activiteit_SetStraat_Correct() {
        activiteit.setStraat("Voskenslaan");
    }

    //Stad
    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetStad_Null_ThrowsIllegalArgumentException() {
        activiteit.setStad(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetStad_Empty_ThrowsIllegalArgumentException() {
        activiteit.setStad("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetStad_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 51; i++) {
            output.concat("a");
        }
        activiteit.setStad(output);
    }

    @Test
    public void activiteit_SetStad_Correct() {
        activiteit.setStad("Gent");
    }

    //PostCode
    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetPC_Null_ThrowsIllegalArgumentException() {
        activiteit.setPostcode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetPC_Empty_ThrowsIllegalArgumentException() {
        activiteit.setPostcode("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetPC_TeLang_ThrowsIllegalArgumentException() {
        activiteit.setPostcode("55555");
    }

    @Test
    public void activiteit_SetPC_Correct() {
        activiteit.setPostcode("9000");
    }

    //HuisNummer
    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetHNR_Null_ThrowsIllegalArgumentException() {
        activiteit.setHuisnummer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetHNR_Empty_ThrowsIllegalArgumentException() {
        activiteit.setHuisnummer("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetHNR_TeLang_ThrowsIllegalArgumentException() {
        activiteit.setHuisnummer("123456");
    }

    @Test
    public void activiteit_SetHNR_Correct() {
        activiteit.setHuisnummer("13");
    }

    //Bus
    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetBus_Null_ThrowsIllegalArgumentException() {
        activiteit.setBus(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetBus_Empty_ThrowsIllegalArgumentException() {
        activiteit.setBus("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetBus_TeLang_ThrowsIllegalArgumentException() {
        activiteit.setBus("123456");
    }

    @Test
    public void activiteit_SetBus_Correct() {
        activiteit.setBus("81a");
    }

    //Formule
    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetFormule_Null_ThrowsIllegalArgumentException() {
        activiteit.setFormule(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetFormule_Empty_ThrowsIllegalArgumentException() {
        activiteit.setFormule(Formule.valueOf(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetFormule_NotExisting_ThrowsIllegalArgumentException() {
        activiteit.setFormule(Formule.valueOf("BESTAATNIET"));
    }

    @Test
    public void activiteit_SetFormule_Correct() {
        activiteit.setFormule(Formule.WO_ZA);
    }

    //BeginDatum
    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetBeginDatum_Null_ThrowsIllegalArgumentException() {
        activiteit.setBeginDatum(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetBeginDatum_Empty_ThrowsIllegalArgumentException() {
        activiteit.setBeginDatum(LocalDate.of(Integer.valueOf(""), Integer.valueOf(""), Integer.valueOf("")));
    }

    @Test
    public void activiteit_SetBeginDatum_Correct() {
        activiteit.setBeginDatum(LocalDate.now());
    }

    //EindDatum
    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetEindDatum_Null_ThrowsIllegalArgumentException() {
        activiteit.setEindDatum(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetEindDatum_Empty_ThrowsIllegalArgumentException() {
        activiteit.setEindDatum(LocalDate.of(Integer.valueOf(""), Integer.valueOf(""), Integer.valueOf("")));
    }

    @Test
    public void activiteit_SetEindDatum_Correct() {
        activiteit.setBeginDatum(LocalDate.now());
        activiteit.setEindDatum(LocalDate.now().plusDays(10));
    }

    //Einddatum voor begindatum
    @Test(expected = DatumIntervalException.class)
    public void activiteit_SetBeginDatum_SetEindDatum_EindKomtVoorBegin_ThrowsDatumIntervalException() {
        activiteit.setBeginDatum(LocalDate.now());
        activiteit.setEindDatum(LocalDate.now().minusDays(1));
    }

    @Test(expected = DatumIntervalException.class)
    public void activiteit_SetBeginDatum_SetEindDatum_BeginKomtVoorEinde_ThrowsDatumIntervalException() {
        Activiteit activiteit = new Activiteit("TestActiviteit", Formule.WO_ZA, 50, LocalDate.now());
        activiteit.setEindDatum(LocalDate.now().plusDays(10));
        activiteit.setBeginDatum(LocalDate.now().plusDays(15));
    }

    //MaxDeelnemers
    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetMaxDeelnemers_Null_ThrowsIllegalArgumentException() {
        activiteit.setMaxDeelnemers(Integer.valueOf(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetMaxDeelnemers_Empty_ThrowsIllegalArgumentException() {
        activiteit.setMaxDeelnemers(Integer.valueOf(""));
    }

    @Test
    public void activiteit_SetMaxDeelnemers_Correct() {
        activiteit.setMaxDeelnemers(25);
    }
}
