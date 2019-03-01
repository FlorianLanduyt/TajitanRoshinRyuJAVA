package domein;

import java.time.LocalDate;

public class InschrijvingActiviteit extends Inschrijving {

    private Activiteit activiteit;

    public InschrijvingActiviteit(Formule formule, Lid lid, LocalDate tijdstip) {
        super(formule, lid, tijdstip);
        activiteit = null;
    }

    public Activiteit getActiviteit() {
        return this.activiteit;
    }

    public void setActiviteit(Activiteit activiteit) {
        this.activiteit = activiteit;
    }

}
