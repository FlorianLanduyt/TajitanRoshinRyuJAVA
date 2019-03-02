package domein.inschrijving;

import domein.enums.Formule;
import domein.Lid;
import domein.activiteit.Stage;
import java.time.LocalDate;

public class InschrijvingStage extends Inschrijving {

    private Stage stage;

    public InschrijvingStage(Lid lid, LocalDate tijdstip) {
        super(Formule.STAGE, lid, tijdstip);
        stage = null;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
