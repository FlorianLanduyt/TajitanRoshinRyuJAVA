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

public class Lid2Test {

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

    @Test(expected = InputMismatchException.class)
    public void activiteit_SetStad_MetNummers_ThrowsInputMismatchException() {
        activiteit.setStad("azezae12345");
    }

    @Test(expected = InputMismatchException.class)
    public void activiteit_SetStad_EnkelNummers_ThrowsInputMismmatchException() {
        activiteit.setStad("15515");
    }

    @Test(expected = InputMismatchException.class)
    public void activiteit_SetStad_MetSymbolen_ThrowsInputMismatchException() {
        activiteit.setStad("aze@ze-*/151");
    }

    @Test(expected = InputMismatchException.class)
    public void activiteit_SetStad_EnkelSymbolen_ThrowsInputMismatchException() {
        activiteit.setStad("@@/*-+$^");
    }

    @Test
    public void activiteit_SetStad_MetSpaties_Correct() {
        activiteit.setStad("Nieuw Gent");
        Assert.assertEquals("Nieuw Gent", activiteit.getStad());
    }

    @Test
    public void activiteit_SetStad_Correct() {
        activiteit.setStad("Gent");
        Assert.assertEquals("Gent", activiteit.getStad());
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

    @Test(expected = InputMismatchException.class)
    public void activiteit_SetStraat_MetNummers_ThrowsInputMismatchException() {
        activiteit.setStraat("azezae12345");
    }

    @Test(expected = InputMismatchException.class)
    public void activiteit_SetStraat_EnkelNummers_ThrowsInputMismmatchException() {
        activiteit.setStraat("15515");
    }

    @Test(expected = InputMismatchException.class)
    public void activiteit_SetStraat_MetSymbolen_ThrowsInputMismatchException() {
        activiteit.setStraat("aze@ze-*/151");
    }

    @Test(expected = InputMismatchException.class)
    public void activiteit_SetStraat_EnkelSymbolen_ThrowsInputMismatchException() {
        activiteit.setStraat("@@/*-+$^");
    }

    @Test
    public void activiteit_SetStraat_MetSpaties_Correct() {
        activiteit.setStraat("De Van Eeckhoutstraat");
        Assert.assertEquals("De Van Eeckhoutstraat", activiteit.getStraat());
    }

    @Test
    public void activiteit_SetStraat_Correct() {
        activiteit.setStraat("Voskenslaan");
        Assert.assertEquals("Voskenslaan", activiteit.getStraat());
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

    @Test(expected = InputMismatchException.class)
    public void activiteit_SetHNR_MetLetters_ThrowsIllegalArgumentException() {
        activiteit.setHuisnummer("74aa");
    }

    @Test(expected = InputMismatchException.class)
    public void activiteit_SetHNR_EnkelLetters_ThrowsIllegalArgumentException() {
        activiteit.setHuisnummer("aaaa");
    }

    @Test(expected = InputMismatchException.class)
    public void activiteit_SetHNR_MetSymbolen_ThrowsIllegalArgumentException() {
        activiteit.setHuisnummer("74@-");
    }

    @Test(expected = InputMismatchException.class)
    public void activiteit_SetHNR_EnkelSymbolen_ThrowsIllegalArgumentException() {
        activiteit.setHuisnummer("/*@-");
    }

    @Test
    public void activiteit_SetHNR_Correct() {
        activiteit.setHuisnummer("13");
        Assert.assertEquals("13", activiteit.getHuisnummer());
    }

    //Bus
    //Postcode
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

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetPC_MetLetters_ThrowsIllegalArgumentException() {
        activiteit.setPostcode("74aa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetPC_EnkelLetters_ThrowsIllegalArgumentException() {
        activiteit.setPostcode("aaaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetPC_MetSymbolen_ThrowsIllegalArgumentException() {
        activiteit.setPostcode("74@-");
    }

    @Test(expected = IllegalArgumentException.class)
    public void activiteit_SetPC_EnkelSymbolen_ThrowsIllegalArgumentException() {
        activiteit.setPostcode("/*@-");
    }

    @Test
    public void activiteit_SetPC_Correct() {
        activiteit.setPostcode("9000");
        Assert.assertEquals("9000", activiteit.getPostcode());
    }
    //Email
    //EmailVader
    //EmailMoeder
    //Geboorteplaats
    //Geslacht
    //Nationaliteit
    //Beroep
}
