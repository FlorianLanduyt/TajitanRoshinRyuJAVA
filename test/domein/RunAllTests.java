package domein;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({domein.ActiviteitTest.class,
    domein.InschrijvingTest.class,
    domein.LidTest.class,
    domein.LesmateriaalTest.class,
    domein.RaadplegingTest.class,
    domein.ThemaTest.class})
public class RunAllTests {

}
