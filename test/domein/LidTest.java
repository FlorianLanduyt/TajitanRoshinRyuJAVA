package domein;

import domein.enums.Functie;
import domein.enums.Graad;
import java.time.LocalDate;
import java.time.Month;
import java.util.InputMismatchException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LidTest {

    Lid lid;

    @Before
    public void setUp() {

        lid = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-357.55",
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.LID);

    }

    @After
    public void reset() {

        lid = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-357.55",
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.LID);

    }

    //Voornaam
    //Achternaam
    //Geboortedatum
    //RijksregisterNummer
    //gsmNr
    //vasteTelefoonNr
    //Stad
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetStad_Null_ThrowsIllegalArgumentException() {
        lid.setStad(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetStad_Empty_ThrowsIllegalArgumentException() {
        lid.setStad("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetStad_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 51; i++) {
            output.concat("a");
        }
        lid.setStad(output);
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetStad_MetNummers_ThrowsInputMismatchException() {
        lid.setStad("azezae12345");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetStad_EnkelNummers_ThrowsInputMismmatchException() {
        lid.setStad("15515");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetStad_MetSymbolen_ThrowsInputMismatchException() {
        lid.setStad("aze@ze-*/151");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetStad_EnkelSymbolen_ThrowsInputMismatchException() {
        lid.setStad("@@/*-+$^");
    }

    @Test
    public void lid_SetStad_MetSpaties_Correct() {
        lid.setStad("Nieuw Gent");
        Assert.assertEquals("Nieuw Gent", lid.getStad());
    }

    @Test
    public void lid_SetStad_Correct() {
        lid.setStad("Gent");
        Assert.assertEquals("Gent", lid.getStad());
    }

    //Straat
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetStraat_Null_ThrowsIllegalArgumentException() {
        lid.setStraat(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetStraat_Empty_ThrowsIllegalArgumentException() {
        lid.setStraat("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetStraat_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 51; i++) {
            output.concat("a");
        }
        lid.setStraat(output);
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetStraat_MetNummers_ThrowsInputMismatchException() {
        lid.setStraat("azezae12345");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetStraat_EnkelNummers_ThrowsInputMismmatchException() {
        lid.setStraat("15515");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetStraat_MetSymbolen_ThrowsInputMismatchException() {
        lid.setStraat("aze@ze-*/151");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetStraat_EnkelSymbolen_ThrowsInputMismatchException() {
        lid.setStraat("@@/*-+$^");
    }

    @Test
    public void lid_SetStraat_MetSpaties_Correct() {
        lid.setStraat("De Van Eeckhoutstraat");
        Assert.assertEquals("De Van Eeckhoutstraat", lid.getStraat());
    }

    @Test
    public void lid_SetStraat_Correct() {
        lid.setStraat("Voskenslaan");
        Assert.assertEquals("Voskenslaan", lid.getStraat());
    }

    //HuisNummer
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetHNR_Null_ThrowsIllegalArgumentException() {
        lid.setHuisNr(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetHNR_Empty_ThrowsIllegalArgumentException() {
        lid.setHuisNr("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetHNR_TeLang_ThrowsIllegalArgumentException() {
        lid.setHuisNr("123456");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetHNR_MetLetters_ThrowsIllegalArgumentException() {
        lid.setHuisNr("74aa");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetHNR_EnkelLetters_ThrowsIllegalArgumentException() {
        lid.setHuisNr("aaaa");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetHNR_MetSymbolen_ThrowsIllegalArgumentException() {
        lid.setHuisNr("74@-");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetHNR_EnkelSymbolen_ThrowsIllegalArgumentException() {
        lid.setHuisNr("/*@-");
    }

    @Test
    public void lid_SetHNR_Correct() {
        lid.setHuisNr("13");
        Assert.assertEquals("13", lid.getHuisNr());
    }

    //Bus
    //Postcode
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetPC_Null_ThrowsIllegalArgumentException() {
        lid.setPostcode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetPC_Empty_ThrowsIllegalArgumentException() {
        lid.setPostcode("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetPC_TeLang_ThrowsIllegalArgumentException() {
        lid.setPostcode("55555");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetPC_MetLetters_ThrowsIllegalArgumentException() {
        lid.setPostcode("74aa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetPC_EnkelLetters_ThrowsIllegalArgumentException() {
        lid.setPostcode("aaaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetPC_MetSymbolen_ThrowsIllegalArgumentException() {
        lid.setPostcode("74@-");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetPC_EnkelSymbolen_ThrowsIllegalArgumentException() {
        lid.setPostcode("/*@-");
    }

    @Test
    public void lid_SetPC_Correct() {
        lid.setPostcode("9000");
        Assert.assertEquals("9000", lid.getPostcode());
    }
    //Email
    //EmailVader
    //EmailMoeder
    //Geboorteplaats
    //Geslacht
    //Nationaliteit
    //Beroep
}
