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
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetVoornaam_Null_ThrowsIllegalArgumentException() {
        lid.setVoornaam(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetVoornaam_Empty_ThrowsIllegalArgumentException() {
        lid.setVoornaam("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetVoornaam_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 51; i++) {
            output.concat("a");
        }
        lid.setVoornaam(output);
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetVoornaam_MetNummers_ThrowsInputMismatchException() {
        lid.setVoornaam("azezae12345");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetVoornaam_EnkelNummers_ThrowsInputMismmatchException() {
        lid.setVoornaam("15515");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetVoornaam_MetSymbolen_ThrowsInputMismatchException() {
        lid.setVoornaam("aze@ze-*/151");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetVoornaam_EnkelSymbolen_ThrowsInputMismatchException() {
        lid.setVoornaam("@@/*-+$^");
    }

    @Test
    public void lid_SetVoornaam_MetSpaties_Correct() {
        lid.setVoornaam("Pieter Jan");
        Assert.assertEquals("Pieter Jan", lid.getVoornaam());
    }

    @Test
    public void lid_SetVoornaam_Correct() {
        lid.setVoornaam("Tybo");
        Assert.assertEquals("Tybo", lid.getVoornaam());
    }

    //Achternaam
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetAchternaam_Null_ThrowsIllegalArgumentException() {
        lid.setAchternaam(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetAchternaam_Empty_ThrowsIllegalArgumentException() {
        lid.setAchternaam("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetAchternaam_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 51; i++) {
            output.concat("a");
        }
        lid.setAchternaam(output);
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetAchternaam_MetNummers_ThrowsInputMismatchException() {
        lid.setAchternaam("azezae12345");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetAchternaam_EnkelNummers_ThrowsInputMismmatchException() {
        lid.setAchternaam("15515");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetAchternaam_MetSymbolen_ThrowsInputMismatchException() {
        lid.setAchternaam("aze@ze-*/151");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetAchternaam_EnkelSymbolen_ThrowsInputMismatchException() {
        lid.setAchternaam("@@/*-+$^");
    }

    @Test
    public void lid_SetAchternaam_MetSpaties_Correct() {
        lid.setAchternaam("Pieter Jan");
        Assert.assertEquals("Pieter Jan", lid.getAchternaam());
    }

    @Test
    public void lid_SetAchternaam_Correct() {
        lid.setAchternaam("Tybo");
        Assert.assertEquals("Tybo", lid.getAchternaam());
    }

    //Geboortedatum
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetGeboortedatum_Null_ThrowsIllegalArgumentException() {
        lid.setGeboortedatum(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetGeboorteDatum_Empty_ThrowsIllegalArgumentException() {
        lid.setGeboortedatum(LocalDate.of(Integer.valueOf(""), Integer.valueOf(""), Integer.valueOf("")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetGeboorteDatum_TeJong_ThrowsIllegalArgumentException() {
        lid.setGeboortedatum(LocalDate.now().minusYears(4));
    }

    @Test
    public void lid_SetGeboorteDatum_Correct() {
        lid.setGeboortedatum(LocalDate.now().minusYears(12));
        Assert.assertEquals(LocalDate.now().minusYears(12), lid.getGeboortedatum());
    }

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
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetBus_TeLang_ThrowsIllegalArgumentException() {
        lid.setBus("123456");
    }

    @Test
    public void lid_SetBus_Correct() {
        lid.setBus("81a");
        Assert.assertEquals("81a", lid.getBus());
    }

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
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetEmail_Null_ThrowsIllegalArgumentException() {
        lid.setEmail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetEmail_Empty_ThrowsIllegalArgumentException() {
        lid.setEmail("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetEmail_MetSpaties_ThrowsIllegalArgumentException() {
        lid.setEmail("tybo. vanderstraeten@student.hogent.be");
    }

    @Test
    public void lid_SetEmail_Correct1() {
        String email = "tybo.vanderstraeten@student.hogent.be";
        lid.setEmail(email);
        Assert.assertEquals(email, lid.getEmail());
    }

    @Test
    public void lid_SetEmail_Correct2() {
        String email = "jan@skynet.com";
        lid.setEmail(email);
        Assert.assertEquals(email, lid.getEmail());
    }

    @Test
    public void lid_SetEmail_Correct3() {
        String email = "testacc@hotmail.com";
        lid.setEmail(email);
        Assert.assertEquals(email, lid.getEmail());
    }

    //EmailVader
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetEmailVader_Null_ThrowsIllegalArgumentException() {
        lid.setEmailVader(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetEmailVader_Empty_ThrowsIllegalArgumentException() {
        lid.setEmailVader("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetEmailVader_MetSpaties_ThrowsIllegalArgumentException() {
        lid.setEmailVader("tybo. vanderstraeten@student.hogent.be");
    }

    @Test
    public void lid_SetEmailVader_Correct1() {
        String email = "tybo.vanderstraeten@student.hogent.be";
        lid.setEmailVader(email);
        Assert.assertEquals(email, lid.getEmailVader());
    }

    @Test
    public void lid_SetEmailVader_Correct2() {
        String email = "jan@skynet.com";
        lid.setEmailVader(email);
        Assert.assertEquals(email, lid.getEmailVader());
    }

    @Test
    public void lid_SetEmailVader_Correct3() {
        String email = "testacc@hotmail.com";
        lid.setEmailVader(email);
        Assert.assertEquals(email, lid.getEmailVader());
    }

    //EmailMoeder
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetEmailMoeder_Null_ThrowsIllegalArgumentException() {
        lid.setEmailMoeder(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetEmailMoeder_Empty_ThrowsIllegalArgumentException() {
        lid.setEmailMoeder("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetEmailMoeder_MetSpaties_ThrowsIllegalArgumentException() {
        lid.setEmailMoeder("tybo. vanderstraeten@student.hogent.be");
    }

    @Test
    public void lid_SetEmailMoeder_Correct1() {
        String email = "tybo.vanderstraeten@student.hogent.be";
        lid.setEmailMoeder(email);
        Assert.assertEquals(email, lid.getEmailMoeder());
    }

    @Test
    public void lid_SetEmailMoeder_Correct2() {
        String email = "jan@skynet.com";
        lid.setEmailMoeder(email);
        Assert.assertEquals(email, lid.getEmailMoeder());
    }

    @Test
    public void lid_SetEmailMoeder_Correct3() {
        String email = "testacc@hotmail.com";
        lid.setEmailMoeder(email);
        Assert.assertEquals(email, lid.getEmailMoeder());
    }

    //Geboorteplaats
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetGeboorteplaats_Null_ThrowsIllegalArgumentException() {
        lid.setGeboorteplaats(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetGeboorteplaats_Empty_ThrowsIllegalArgumentException() {
        lid.setGeboorteplaats("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetGeboorteplaats_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 51; i++) {
            output.concat("a");
        }
        lid.setGeboorteplaats(output);
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetGeboorteplaats_MetNummers_ThrowsInputMismatchException() {
        lid.setGeboorteplaats("azezae12345");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetGeboorteplaats_EnkelNummers_ThrowsInputMismmatchException() {
        lid.setGeboorteplaats("15515");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetGeboorteplaats_MetSymbolen_ThrowsInputMismatchException() {
        lid.setGeboorteplaats("aze@ze-*/151");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetGeboorteplaats_EnkelSymbolen_ThrowsInputMismatchException() {
        lid.setGeboorteplaats("@@/*-+$^");
    }

    @Test
    public void lid_SetGeboorteplaats_MetSpaties_Correct() {
        lid.setGeboorteplaats("Gent Nieuw");
        Assert.assertEquals("Gent Nieuw", lid.getGeboorteplaats());
    }

    @Test
    public void lid_SetGeboorteplaats_Correct() {
        lid.setGeboorteplaats("Gent");
        Assert.assertEquals("Gent", lid.getGeboorteplaats());
    }

    //gsmNr
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetGsmNummer_Null_ThrowsIllegalArgumentException() {
        lid.setGsmNr(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetGsmNummer_Empty_ThrowsIllegalArgumentException() {
        lid.setGsmNr("");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetGsmNummer_MetLetters_ThrowsInputMismatchException() {
        lid.setGsmNr("aaaaa12345");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetGsmNummer_EnkelLetters_ThrowsInputMismmatchException() {
        lid.setGsmNr("aaaaaaaaaa");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetGsmNummer_MetSymbolen_ThrowsInputMismatchException() {
        lid.setGsmNr("@@@@@12345");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetGsmNummer_EnkelSymbolen_ThrowsInputMismatchException() {
        lid.setGsmNr("@@@@@@@@@@");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetGsmNummer_MetSpaties_ThrowsInputMismatchException() {
        lid.setGsmNr("04 77441461");
    }

    @Test
    public void lid_SetGsmNummer_Correct() {
        lid.setGsmNr("0477441462");
        Assert.assertEquals("0477441462", lid.getGsmNr());
    }

    @Test
    public void lid_SetGsmNummer_32_Correct() {
        lid.setGsmNr("+32477441462");
        Assert.assertEquals("+32477441462", lid.getGsmNr());
    }

    //vasteTelefoonNr
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetNummer_Empty_ThrowsIllegalArgumentException() {
        lid.setVasteTelefoonNr("");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetNummer_MetLetters_ThrowsInputMismatchException() {
        lid.setVasteTelefoonNr("aaaaa1235");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetNummer_EnkelLetters_ThrowsInputMismmatchException() {
        lid.setVasteTelefoonNr("aaaaaaaaa");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetNummer_MetSymbolen_ThrowsInputMismatchException() {
        lid.setVasteTelefoonNr("@@@@@1245");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetNummer_EnkelSymbolen_ThrowsInputMismatchException() {
        lid.setVasteTelefoonNr("@@@@@@@@@");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetNummer_MetSpaties_ThrowsInputMismatchException() {
        lid.setVasteTelefoonNr("04 7744146");
    }

    @Test
    public void lid_SetNummer_Correct() {
        lid.setVasteTelefoonNr("047744146");
        Assert.assertEquals("047744146", lid.getVasteTelefoonNr());
    }

    //Nationaliteit
    @Test(expected = IllegalArgumentException.class)
    public void lid_SetNationaliteit_Null_ThrowsIllegalArgumentException() {
        lid.setNationaliteit(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetNationaliteit_Empty_ThrowsIllegalArgumentException() {
        lid.setNationaliteit("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lid_SetNationaliteit_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 51; i++) {
            output.concat("a");
        }
        lid.setNationaliteit(output);
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetNationaliteit_MetNummers_ThrowsInputMismatchException() {
        lid.setNationaliteit("azezae12345");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetNationaliteit_EnkelNummers_ThrowsInputMismmatchException() {
        lid.setNationaliteit("15515");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetNationaliteit_MetSymbolen_ThrowsInputMismatchException() {
        lid.setNationaliteit("aze@ze-*/151");
    }

    @Test(expected = InputMismatchException.class)
    public void lid_SetNationaliteit_EnkelSymbolen_ThrowsInputMismatchException() {
        lid.setNationaliteit("@@/*-+$^");
    }

    @Test
    public void lid_SetNationaliteit_MetSpaties_Correct() {
        lid.setNationaliteit("Vietna mees");
        Assert.assertEquals("Vietna mees", lid.getNationaliteit());
    }

    @Test
    public void lid_SetNationaliteit_Correct() {
        lid.setNationaliteit("Belg");
        Assert.assertEquals("Belg", lid.getNationaliteit());
    }

    //-------------------------
    //-------------------------
    //-------------------------
    //-------------------------
    //Geslacht
    //RijksregisterNummer
    //Beroep (zoals bus, bus moet nog aangepast worden! alsook in activiteit!)
}
