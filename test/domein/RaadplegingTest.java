package domein;

import domein.enums.Functie;
import domein.enums.Graad;
import java.time.LocalDate;
import java.time.Month;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RaadplegingTest {

    /* 
    Dit is eigenlijk .NET functionaliteit!!
     */
    Oefening oef1;
    Lid lid1;
    Thema thema1;
    Raadpleging r1;

    @Before
    public void setUp() {
        thema1 = new Thema("Testthema");

        oef1 = new Oefening("Test1", "www.test1.com", "test1.jpg",
                "De eerste test", Graad.DAN1, thema1);

        lid1 = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-357.55",
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.LID);

        r1 = new Raadpleging(lid1, oef1);
    }

    @After
    public void reset() {
        thema1 = new Thema("Testthema");

        oef1 = new Oefening("Test1", "www.test1.com", "test1.jpg",
                "De eerste test", Graad.DAN1, thema1);

        lid1 = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-357.55",
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.LID);

        r1 = new Raadpleging(lid1, oef1);
    }

    @Test
    public void raadpleging_Lid1_AantalRaadplegingen1() {
        Assert.assertEquals(1, r1.getAantalRaadplegingen());
    }

    @Test
    public void raadpleging_Lid1_2xVerhogen_AantalRaadplegingen3() {
        r1.verhoogAantalRaadplegingen();
        r1.verhoogAantalRaadplegingen();
        Assert.assertEquals(3, r1.getAantalRaadplegingen());
    }

    @Test
    public void raadpleging_Lid1_TijdstipLaatsteRaadpleging_Nu() {
        Assert.assertEquals(LocalDate.now(), oef1.getLaatsteRaadpleging());
    }

    @Test
    public void raadpleging_Lid1_2xVerhogen_3Tijdstippen() {
        r1.verhoogAantalRaadplegingen();
        r1.verhoogAantalRaadplegingen();
        Assert.assertEquals(3, r1.getTijdstippen().size());
    }
}
