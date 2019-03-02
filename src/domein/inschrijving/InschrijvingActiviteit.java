package domein.inschrijving;

import domein.activiteit.Activiteit;
import domein.enums.Formule;
import domein.Lid;
import java.time.LocalDate;

public class InschrijvingActiviteit extends Inschrijving {

    private Activiteit activiteit;

    public InschrijvingActiviteit(Lid lid, LocalDate tijdstip) {
        super(Formule.ACTIVITEIT, lid, tijdstip);
        activiteit = null;
    }

    public Activiteit getActiviteit() {
        return this.activiteit;
    }

    public void setActiviteit(Activiteit activiteit) {
        this.activiteit = activiteit;
    }

}
