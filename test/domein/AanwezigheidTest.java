/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author robdeputter
 */
@RunWith(Parameterized.class)
public class AanwezigheidTest {
    
    private static Activiteit[] activiteiten; //als men dit niet op static zit geeft hij foutmeldingen
    private final Activiteit geselecteerdeActiviteit;
    private final int puntenAantal;
    private final Aanwezigheid aanwezigheid;
    private Lid lid;
    
    
    @Before
    public void Before(){
        activiteiten = new Activiteit[]{(
            new Les("Les 1", Formule.ZA, LocalDate.of(2019, Month.FEBRUARY, 23))),
            new Les("Les 1", Formule.DI_ZA, LocalDate.of(2020, Month.FEBRUARY, 19))
        };
        lid = new Lid("Rob", "De Putter", LocalDate.of(1999, Month.MARCH, 12),
                "99.03.12-007.41", LocalDate.now(),
                "0478899964", "054556880", "Schoolstraat",
                "110", "9600", "rob.deputter@hotmail.com",
                "TurnenIsLeuk8", "Gent", "Vrouw",
                "Belg", "Kyu-2");

    }

    public AanwezigheidTest(Activiteit activiteit, int puntenAantal) {
        geselecteerdeActiviteit = activiteit;
        this.puntenAantal = puntenAantal;
        
        aanwezigheid = new Aanwezigheid(lid, geselecteerdeActiviteit);
    }
    
    @Parameters
    public static List<Object[]> getParameters(){
        return Arrays.asList(new Object[][]{
            {activiteiten[1], 10},
            {activiteiten[2], 5}
    });
    }
    
    @Test
    public void berekenPunten_werkt(){
        //uit commentaar halen nadat berekenPunten() public is gemaakt in de klasse Aanwezigheid
        
        //int result =  aanwezigheid.berekenPunten(); 
        
        //Assert.assertEquals(puntenAantal, result);
    }
    
    
    
    
    
}
