/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.enums.Functie;
import domein.enums.Graad;
import java.time.LocalDate;
import java.time.Month;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tim
 */
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

    @Test(expected = IllegalArgumentException.class)
    public void legeAchternaamWerptException() {
        activiteit.setAchternaam("");
    }

    @Test
    public void stelNormaleAchternaamIn() {
        activiteit.setAchternaam("Geldhof");
        Assert.assertEquals("Geldhof", activiteit.getAchternaam());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsAchternaamWerptException() {
        activiteit.setAchternaam(null);
    }

    @Test
    public void stelNormalevoornaamIn() {
        activiteit.setVoornaam("Tom");
        Assert.assertEquals("Tom", activiteit.getVoornaam());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsVoornaamWerptException() {
        activiteit.setVoornaam(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeStringAlsVoornaamWerptException() {
        activiteit.setVoornaam("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangeVoornaamWerptException() {
        activiteit.setVoornaam("fqdfqskjjdfdqfsjkmqmfljskmfsjqsdmfkjkdfsqmklqfsldk");
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangeAchternaamWerptException() {
        activiteit.setAchternaam("fqdfqskjjdfdqfsjkmqmfljskmfsjqsdmfkjkdfsqmklqfsldkfqdfqskjjdfdqfsjkmqmfljskmfsjqsdmfkjkdfsqmklqfsldkfqdfqskjjdfdqfsjkmqmfljskmfsjqsdmfkjkdfsqmklqfsldk");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setToekomstigeGeboorteDatumWerptException() {
        activiteit.setGeboortedatum(LocalDate.MAX);
    }

    @Test
    public void stelNormaleGeboorteDatumIn() {
        activiteit.setGeboortedatum(LocalDate.now().minusYears(21));
        Assert.assertEquals(LocalDate.now().minusYears(21), activiteit.getGeboortedatum());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullAlsGeboorteDatumWerptException() {
        activiteit.setGeboortedatum(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFoutRijksregisternummerMetVerkeerdeTellerWerptException() {
        activiteit.setRijksregisterNr("97.07.17-358.55");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullRijksregisternummer() {
        activiteit.setRijksregisterNr(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLeegRijksregisternummer() {
        activiteit.setRijksregisterNr("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rijksRegisternummerKomtNietOvereenMetGeboorteDatumWerptException() {
        activiteit.setRijksregisterNr("98.12.07-001.88");
    }

    @Test
    public void stelNormaalRijksregisterIn() {
        activiteit.setRijksregisterNr("97.07.17-001.23");
        Assert.assertEquals("97.07.17-001.23", activiteit.getRijksregisterNr());
    }

    @Test(expected = IllegalArgumentException.class)
    public void gsmNummerTeKortWerptException() {
        activiteit.setGsmNr("047933095");
    }

    @Test(expected = IllegalArgumentException.class)
    public void gsmNummerTeLangWerptException() {
        activiteit.setGsmNr("04793309597");
    }

    @Test
    public void stelNormaalGsmNummerIn() {
        activiteit.setGsmNr("0494511001");
        Assert.assertEquals("0494511001", activiteit.getGsmNr());
    }

    @Test(expected = IllegalArgumentException.class)
    public void verkeerdeTekensGsmNrWerptException() {
        activiteit.setGsmNr("0479a30959");
    }

    @Test(expected = IllegalArgumentException.class)
    public void leegHuisnummerWerptException() {
        activiteit.setHuisNr("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangHuisnummerWerptException() {
        activiteit.setHuisNr("123456");
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangBusnummerWerptException() {
        activiteit.setBus("123456");
    }

    @Test(expected = IllegalArgumentException.class)
    public void legePostCodeWerptException() {
        activiteit.setPostcode("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsPostCodeWerptException() {
        activiteit.setPostcode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangePostcodeWerptException() {
        activiteit.setPostcode("12345");
    }

    @Test(expected = IllegalArgumentException.class)
    public void teKortePostcodeWerptException() {
        activiteit.setPostcode("123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void verkeerdFormaatPostcodeWerptException() {
        activiteit.setPostcode("123A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeEmailWerptException() {
        activiteit.setEmail("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullEmailWerptException() {
        activiteit.setEmail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void geenApestaartjeInMailWerptException() {
        activiteit.setEmail("robdeputtergmail.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void geenPuntNaApestaartjeInMailWerptException() {
        activiteit.setEmail("robdeputter@gmailcom");
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeGebruikersnaamWerptException() {
        activiteit.setEmail("@gmailcom");
    }

    @Test(expected = IllegalArgumentException.class)
    public void leegDomeinWerptException() {
        activiteit.setEmail("robdeputter@.com");
    }

    //email setter gelijk bij setter v. mail vader en moeder
    @Test(expected = IllegalArgumentException.class)
    public void leegGeslachtWerptException() {
        activiteit.setGeslacht("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullGeslachtWerptException() {
        activiteit.setGeslacht(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verzonnenGeslachtWerptException() {
        activiteit.setGeslacht("Appel");
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeGeboorteplaatsWerptException() {
        activiteit.setGeboorteplaats("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsGeboorteplaatsWerptException() {
        activiteit.setGeboorteplaats(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangeGeboorteplaatsWerptException() {
        activiteit.setGeboorteplaats("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeNationaliteitWerptException() {
        activiteit.setNationaliteit("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsNationaliteitWerptException() {
        activiteit.setNationaliteit(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangeNationaliteitWerptException() {
        activiteit.setNationaliteit("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangBeroepWerptException() {
        activiteit.setNationaliteit("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void leegBeroepWerptException() {
        activiteit.setNationaliteit("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsBeroepWerptException() {
        activiteit.setNationaliteit(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negatiefPuntenaantalWerptException() {
        activiteit.setPuntenAantal(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeGraadWerptException() {
        activiteit.setGraad(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fouteGraadWerptException() {
        activiteit.setGraad(Graad.valueOf("Soep"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsFunctieWerptException() {
        activiteit.setFunctie(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fouteFunctieWerptException() {
        activiteit.setFunctie(Functie.valueOf("Tomaat"));
    }
}
