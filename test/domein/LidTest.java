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
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tim
 */
public class LidTest {
    Lid lid;
    
    @Before
    public void setUp(){

        lid = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-357.55", LocalDate.now().minusYears(18),
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.LID);
    
    }
    @After
    public void reset(){

        lid = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-357.55", LocalDate.now().minusYears(18),
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.LID);
    
    }
    
    
    @Test(expected= IllegalArgumentException.class)
    public void legeAchternaamWerptException(){
        lid.setAchternaam("");
    }
     @Test(expected= IllegalArgumentException.class)
    public void nullAlsAchternaamWerptException(){
        lid.setAchternaam(null);
    }
    @Test(expected= IllegalArgumentException.class)
    public void nullAlsVoornaamWerptException(){
        lid.setVoornaam(null);
    }
    @Test(expected= IllegalArgumentException.class)
    public void legeStringAlsVoornaamWerptException(){
        lid.setVoornaam("");
    }
    @Test(expected= IllegalArgumentException.class)
    public void teLangeVoornaamWerptException(){
        lid.setVoornaam("fqdfqskjjdfdqfsjkmqmfljskmfsjqsdmfkjkdfsqmklqfsldk");
    }
    @Test(expected= IllegalArgumentException.class)
    public void teLangeAchternaamWerptException(){
        lid.setAchternaam("fqdfqskjjdfdqfsjkmqmfljskmfsjqsdmfkjkdfsqmklqfsldkfqdfqskjjdfdqfsjkmqmfljskmfsjqsdmfkjkdfsqmklqfsldkfqdfqskjjdfdqfsjkmqmfljskmfsjqsdmfkjkdfsqmklqfsldk");
    }
    @Test(expected= IllegalArgumentException.class)
    public void setToekomstigeGeboorteDatumWerptException(){
        lid.setGeboortedatum(LocalDate.MAX);
    }
    @Test(expected= IllegalArgumentException.class)
    public void setNullAlsGeboorteDatumWerptException(){
        lid.setGeboortedatum(null);
    }
    @Test(expected= IllegalArgumentException.class)
    public void setFoutRijksregisternummerMetVerkeerdeTellerWerptException(){
        lid.setRijksregisterNr("97.07.17-358.55");
    }
    @Test(expected= IllegalArgumentException.class)
    public void setNullRijksregisternummer(){
        lid.setRijksregisterNr(null);
    }
    @Test(expected= IllegalArgumentException.class)
    public void setLeegRijksregisternummer(){
        lid.setRijksregisterNr("");
    }
    @Test(expected= IllegalArgumentException.class)
    public void rijksRegisternummerKomtNietOvereenMetGeboorteDatumWerptException(){
        lid.setRijksregisterNr("98.12.07-001.88");
    }
    
    @Test(expected= IllegalArgumentException.class)
    public void legeEersteTrainingsdatumWerptException(){
        lid.setDatumEersteTraining(null);
    }
    @Test(expected= IllegalArgumentException.class)
    public void gsmNummerTeKortWerptException(){
        lid.setGsmNr("047933095");
    }
    @Test(expected= IllegalArgumentException.class)
    public void gsmNummerTeLangWerptException(){
        lid.setGsmNr("04793309597");
    }
    @Test(expected= IllegalArgumentException.class)
    public void verkeerdeTekensGsmNrWerptException(){
        lid.setGsmNr("0479a30959");
    }
    
    @Test(expected= IllegalArgumentException.class)
    public void leegHuisnummerWerptException(){
        lid.setHuisNr("");
    }
    @Test(expected= IllegalArgumentException.class)
    public void teLangHuisnummerWerptException(){
        lid.setHuisNr("123456");
    }
     @Test(expected= IllegalArgumentException.class)
    public void teLangBusnummerWerptException(){
        lid.setBus("123456");
    }
    @Test(expected= IllegalArgumentException.class)
    public void leegBusnummerWerptException(){
        lid.setBus("");
    }
    
    
    /*
    @Test(expected= IllegalArgumentException.class)
    public void methodeNaam(){
        lid
    }
    
    */
    
    
    
    
    
}
