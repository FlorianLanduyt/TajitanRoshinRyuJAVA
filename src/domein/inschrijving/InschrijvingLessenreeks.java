package domein.inschrijving;

import domein.enums.Formule;
import domein.Lid;
import domein.activiteit.Les;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InschrijvingLessenreeks extends Inschrijving {

    private List<Les> lessen;

    public InschrijvingLessenreeks(Formule formule, Lid lid, LocalDate tijdstip) {
        super(formule, lid, tijdstip);
        lessen = new ArrayList<>();
    }

    public List<Les> geefLessen() {
        return this.lessen;
    }

    public void voegLesToe(Les les) {
        this.lessen.add(les);
    }

    public void verwijderLes(Les les) {
        this.lessen.remove(les);
    }

}
