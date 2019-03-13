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
                "97.07.17-357.55", LocalDate.now().minusYears(18),
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.LID);

    }

    @After
    public void reset() {

        lid = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-357.55", LocalDate.now().minusYears(18),
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.LID);

    }

    @Test(expected = IllegalArgumentException.class)
    public void legeAchternaamWerptException() {
        lid.setAchternaam("");
    }

    @Test
    public void stelNormaleAchternaamIn() {
        lid.setAchternaam("Geldhof");
        Assert.assertEquals("Geldhof", lid.getAchternaam());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsAchternaamWerptException() {
        lid.setAchternaam(null);
    }

    @Test
    public void stelNormalevoornaamIn() {
        lid.setVoornaam("Tom");
        Assert.assertEquals("Tom", lid.getVoornaam());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsVoornaamWerptException() {
        lid.setVoornaam(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeStringAlsVoornaamWerptException() {
        lid.setVoornaam("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangeVoornaamWerptException() {
        lid.setVoornaam("fqdfqskjjdfdqfsjkmqmfljskmfsjqsdmfkjkdfsqmklqfsldk");
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangeAchternaamWerptException() {
        lid.setAchternaam("fqdfqskjjdfdqfsjkmqmfljskmfsjqsdmfkjkdfsqmklqfsldkfqdfqskjjdfdqfsjkmqmfljskmfsjqsdmfkjkdfsqmklqfsldkfqdfqskjjdfdqfsjkmqmfljskmfsjqsdmfkjkdfsqmklqfsldk");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setToekomstigeGeboorteDatumWerptException() {
        lid.setGeboortedatum(LocalDate.MAX);
    }

    @Test
    public void stelNormaleGeboorteDatumIn() {
        lid.setGeboortedatum(LocalDate.now().minusYears(21));
        Assert.assertEquals(LocalDate.now().minusYears(21), lid.getGeboortedatum());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullAlsGeboorteDatumWerptException() {
        lid.setGeboortedatum(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFoutRijksregisternummerMetVerkeerdeTellerWerptException() {
        lid.setRijksregisterNr("97.07.17-358.55");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullRijksregisternummer() {
        lid.setRijksregisterNr(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLeegRijksregisternummer() {
        lid.setRijksregisterNr("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rijksRegisternummerKomtNietOvereenMetGeboorteDatumWerptException() {
        lid.setRijksregisterNr("98.12.07-001.88");
    }

    @Test
    public void stelNormaalRijksregisterIn() {
        lid.setRijksregisterNr("97.07.17-001.23");
        Assert.assertEquals("97.07.17-001.23", lid.getRijksregisterNr());
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeEersteTrainingsdatumWerptException() {
        lid.setDatumEersteTraining(null);
    }

    @Test
    public void stelNormaleDatumEersteTrainingIn() {
        lid.setDatumEersteTraining(LocalDate.now().minusMonths(2));
        Assert.assertEquals(LocalDate.now().minusMonths(2), lid.getDatumEersteTraining());
    }

    @Test(expected = IllegalArgumentException.class)
    public void gsmNummerTeKortWerptException() {
        lid.setGsmNr("047933095");
    }

    @Test(expected = IllegalArgumentException.class)
    public void gsmNummerTeLangWerptException() {
        lid.setGsmNr("04793309597");
    }

    @Test
    public void stelNormaalGsmNummerIn() {
        lid.setGsmNr("0494511001");
        Assert.assertEquals("0494511001", lid.getGsmNr());
    }

    @Test(expected = IllegalArgumentException.class)
    public void verkeerdeTekensGsmNrWerptException() {
        lid.setGsmNr("0479a30959");
    }

    @Test(expected = IllegalArgumentException.class)
    public void leegHuisnummerWerptException() {
        lid.setHuisNr("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangHuisnummerWerptException() {
        lid.setHuisNr("123456");
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangBusnummerWerptException() {
        lid.setBus("123456");
    }

    @Test(expected = IllegalArgumentException.class)
    public void legePostCodeWerptException() {
        lid.setPostcode("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsPostCodeWerptException() {
        lid.setPostcode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangePostcodeWerptException() {
        lid.setPostcode("12345");
    }

    @Test(expected = IllegalArgumentException.class)
    public void teKortePostcodeWerptException() {
        lid.setPostcode("123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void verkeerdFormaatPostcodeWerptException() {
        lid.setPostcode("123A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeEmailWerptException() {
        lid.setEmail("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullEmailWerptException() {
        lid.setEmail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void geenApestaartjeInMailWerptException() {
        lid.setEmail("robdeputtergmail.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void geenPuntNaApestaartjeInMailWerptException() {
        lid.setEmail("robdeputter@gmailcom");
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeGebruikersnaamWerptException() {
        lid.setEmail("@gmailcom");
    }

    @Test(expected = IllegalArgumentException.class)
    public void leegDomeinWerptException() {
        lid.setEmail("robdeputter@.com");
    }

    //email setter gelijk bij setter v. mail vader en moeder
    @Test(expected = IllegalArgumentException.class)
    public void leegGeslachtWerptException() {
        lid.setGeslacht("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullGeslachtWerptException() {
        lid.setGeslacht(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verzonnenGeslachtWerptException() {
        lid.setGeslacht("Appel");
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeGeboorteplaatsWerptException() {
        lid.setGeboorteplaats("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsGeboorteplaatsWerptException() {
        lid.setGeboorteplaats(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangeGeboorteplaatsWerptException() {
        lid.setGeboorteplaats("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeNationaliteitWerptException() {
        lid.setNationaliteit("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsNationaliteitWerptException() {
        lid.setNationaliteit(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangeNationaliteitWerptException() {
        lid.setNationaliteit("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void teLangBeroepWerptException() {
        lid.setNationaliteit("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void leegBeroepWerptException() {
        lid.setNationaliteit("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsBeroepWerptException() {
        lid.setNationaliteit(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negatiefPuntenaantalWerptException() {
        lid.setPuntenAantal(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void legeGraadWerptException() {
        lid.setGraad(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fouteGraadWerptException() {
        lid.setGraad(Graad.valueOf("Soep"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAlsFunctieWerptException() {
        lid.setFunctie(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fouteFunctieWerptException() {
        lid.setFunctie(Functie.valueOf("Tomaat"));
    }
}
