package domein;

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
