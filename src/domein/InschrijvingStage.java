package domein;

import java.time.LocalDate;

public class InschrijvingStage extends Inschrijving {

    private Stage stage;

    public InschrijvingStage(Formule formule, Lid lid, LocalDate tijdstip) {
        super(formule, lid, tijdstip);
        stage = null;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
