package domein;

import domein.enums.Formule;
import domein.enums.Functie;
import domein.enums.Graad;
import exceptions.DatumIntervalException;
import exceptions.VolzetException;
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
                1, LocalDate.of(2020, Month.DECEMBER, 20), LocalDate.of(2020, Month.MARCH, 10));

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
                1, LocalDate.of(2020, Month.DECEMBER, 20), LocalDate.of(2020, Month.MARCH, 10));
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
        activiteit.setEindDatum(LocalDate.now().plusDays(10));
        activiteit.setBeginDatum(LocalDate.now().plusDays(15));
    }

    //UitersteInschrijvingsDatum
    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetUitersteInschrijvingsDatum_Null_ThrowsIllegalArgumentException() {
        activiteit.setUitersteInschrijvingsDatum(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetUitersteInschrijvingsDatum_Empty_ThrowsIllegalArgumentException() {
        activiteit.setUitersteInschrijvingsDatum(LocalDate.of(Integer.valueOf(""), Integer.valueOf(""), Integer.valueOf("")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetUitersteInschrijvingsDatum_InVerleden_ThrowsIllegalArgumentException() {
        activiteit.setUitersteInschrijvingsDatum(LocalDate.now().minusDays(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetUitersteInschrijvingsDatum_NaBeginDatum_ThrowsIllegalArgumentException() {
        activiteit.setBeginDatum(LocalDate.now().plusDays(10));
        activiteit.setUitersteInschrijvingsDatum(LocalDate.now().plusDays(15));
    }

    @Test
    public void activiteit_SetUitersteInschrijvingsDatum_Correct() {
        activiteit.setBeginDatum(LocalDate.now().plusDays(5));
        activiteit.setUitersteInschrijvingsDatum(LocalDate.now());
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
