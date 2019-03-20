package domein;

import domein.enums.Graad;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LesmateriaalTest {

    Oefening oef1;
    Oefening oef2;
    Oefening oef3;
    Thema thema1;

    @Before
    public void setUp() {
        thema1 = new Thema("Testthema");
        oef1 = new Oefening("Test1", "www.test1.com", "test1.jpg",
                "De eerste test", Graad.DAN1, thema1);
        oef2 = new Oefening("Test2", "www.test2.com", "test2.jpg",
                "De tweede test", Graad.DAN1, thema1);
        oef3 = new Oefening("Test3", "www.test3.com", "test3.jpg",
                "De derde test", Graad.DAN1, thema1);
    }

    @After
    public void reset() {
        thema1 = new Thema("Testthema");
        oef1 = new Oefening("Test1", "www.test1.com", "test1.jpg",
                "De eerste test", Graad.DAN1, thema1);
        oef2 = new Oefening("Test2", "www.test2.com", "test2.jpg",
                "De tweede test", Graad.DAN1, thema1);
        oef3 = new Oefening("Test3", "www.test3.com", "test3.jpg",
                "De derde test", Graad.DAN1, thema1);
    }

    //
    //Validatie testing
    //
    //Titel
    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetTitel_Null_ThrowsIllegalArgumentException() {
        oef1.setTitel(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetTitel_Empty_ThrowsIllegalArgumentException() {
        oef1.setTitel("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetTitel_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 40; i++) {
            output.concat("a");
        }
        oef1.setTitel(output);
    }

    @Test
    public void oefening_SetTitel_Correct() {
        oef1.setTitel("Schouderomwenteling");
        Assert.assertEquals("Schouderomwenteling", oef1.getTitel()); 
    }

    //UrlVideo
    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetUrlVideo_Null_ThrowsIllegalArgumentException() {
        oef1.setUrlVideo(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetUrlVideo_Empty_ThrowsIllegalArgumentException() {
        oef1.setUrlVideo("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetUrlVideo_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 110; i++) {
            output.concat("a");
        }
        oef1.setUrlVideo(output);
    }

    @Test
    public void oefening_SetUrlVideo_Correct() {
        oef1.setUrlVideo("www.video.com");
        Assert.assertEquals("www.video.com", oef1.getUrlVideo());
    }

    //UrlAfbeelding
    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetUrlAfbeelding_Null_ThrowsIllegalArgumentException() {
        oef1.setAfbeelding(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetUrlAfbeelding_Empty_ThrowsIllegalArgumentException() {
        oef1.setAfbeelding("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetUrlAfbeelding_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 110; i++) {
            output.concat("a");
        }
        oef1.setAfbeelding(output);
    }

    @Test
    public void oefening_SetUrlAfbeelding_Correct() {
        oef1.setAfbeelding("oefening.jpeg");
        Assert.assertEquals("oefening.jpeg", oef1.getAfbeelding());
    }

    //Tekst
    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetTekst_Null_ThrowsIllegalArgumentException() {
        oef1.setTekst(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetTekst_Empty_ThrowsIllegalArgumentException() {
        oef1.setTekst("");
    }

    @Test
    public void oefening_SetTekst_Correct() {
        oef1.setTekst("Een eerste tekstje");
        Assert.assertEquals("Een eerste tekstje", oef1.getTekst());
    }

    //Graad
    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetGraad_Null_ThrowsIllegalArgumentException() {
        oef1.setGraad(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oefenin_SetGraad_Empty_ThrowsIllegalArgumentException() {
        oef1.setGraad(Graad.valueOf(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetGraad_NotExisting_ThrowsIllegalArgumentException() {
        oef1.setGraad(Graad.valueOf("BESTAATNIET"));
    }

    @Test
    public void oefening_SetGraad_Correct() {
        oef1.setGraad(Graad.DAN3);
        Assert.assertEquals(Graad.DAN3, oef1.getGraad());
    }

    //Thema
    @Test(expected = IllegalArgumentException.class)
    public void oefening_SetThema_Null_ThrowsIllegalArgumentException() {
        oef1.setThema(null);
    }

    @Test
    public void oefening_SetThema_Correct() {
        Thema thema = new Thema("Tester");
        oef1.setThema(thema);
        Assert.assertEquals(thema.getNaam(), oef1.getThema().getNaam());
    }
}
