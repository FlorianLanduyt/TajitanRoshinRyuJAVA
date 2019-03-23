package domein;

import java.util.InputMismatchException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ThemaTest {

    Thema thema;

    @Before
    public void setUp() {
        thema = new Thema("Test");
    }
    
    @After
    public void reset(){
        thema=new Thema("Test");
    }
    
    //Naam
    @Test(expected = IllegalArgumentException.class)
    public void thema_SetNaam_Null_ThrowsIllegalArgumentException() {
        thema.setNaam(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void thema_SetNaam_Empty_ThrowsIllegalArgumentException() {
        thema.setNaam("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void thema_SetNaam_TeLang_ThrowsIllegalArgumentException() {
        String output = "";
        for (int i = 0; i < 36; i++) {
            output.concat("a");
        }
        thema.setNaam(output);
    }

    @Test(expected = InputMismatchException.class)
    public void thema_SetNaam_MetNummers_ThrowsInputMismatchException() {
        thema.setNaam("azezae12345");
    }

    @Test(expected = InputMismatchException.class)
    public void thema_SetNaam_EnkelNummers_ThrowsInputMismmatchException() {
        thema.setNaam("15515");
    }

    @Test(expected = InputMismatchException.class)
    public void thema_SetNaam_MetSymbolen_ThrowsInputMismatchException() {
        thema.setNaam("aze@ze-*/151");
    }

    @Test(expected = InputMismatchException.class)
    public void thema_SetNaam_EnkelSymbolen_ThrowsInputMismatchException() {
        thema.setNaam("@@/*-+$^");
    }

    @Test
    public void thema_SetNaam_MetSpaties_Correct() {
        thema.setNaam("De Stage");
        Assert.assertEquals("De Stage", thema.getNaam());
    }

    @Test
    public void thema_SetNaam_Correct() {
        thema.setNaam("Hoogtestage");
        Assert.assertEquals("Hoogtestage", thema.getNaam());
    }
}
