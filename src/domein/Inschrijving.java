package domein;

import domein.enums.Formule;
import exceptions.VolzetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

public class Inschrijving {

    private Lid lid;
    private Formule formule;
    private LocalDate tijdstip;
    private List<Activiteit> activiteiten;

    //SimpleStringProperties
    private SimpleStringProperty sVoornaam = new SimpleStringProperty();
    private SimpleStringProperty sAchternaam = new SimpleStringProperty();
    private SimpleStringProperty sFormule = new SimpleStringProperty();
    private SimpleStringProperty sTijdstip = new SimpleStringProperty();

    public Inschrijving(Formule formule, Lid lid, LocalDate tijdstip) {
        setFormule(formule);
        setLid(lid);
        setTijdstip(tijdstip);
        setVoornaam();
        setAchternaam();
        activiteiten = new ArrayList<>();
    }

    //Getters voor SimpleStringProperties
    public SimpleStringProperty voornaamProperty() {
        return sVoornaam;
    }

    public SimpleStringProperty achternaamProperty() {
        return sAchternaam;
    }

    public SimpleStringProperty formuleProperty() {
        return sFormule;
    }

    public SimpleStringProperty tijdstipProperty() {
        return sTijdstip;
    }

    //Gewone getters en setters
    public Formule getFormule() {
        return Formule.valueOf(sFormule.get());
    }

    private void setFormule(Formule formule) {
        this.formule = formule;
        sFormule.set(formule.name());
    }

    public Lid getLid() {
        return lid;
    }

    private void setLid(Lid lid) {
        this.lid = lid;
    }

    public LocalDate getTijdstip() {
        return LocalDate.parse(sTijdstip.get());
    }

    private void setTijdstip(LocalDate tijdstip) {
        this.tijdstip = tijdstip;
        sTijdstip.set(tijdstip.toString());
    }

    public String getVoornaam() {
        return sVoornaam.get();
    }

    public void setVoornaam() {
        sVoornaam.set(getLid().getVoornaam());
    }

    public String getAchternaam() {
        return sAchternaam.get();
    }

    public void setAchternaam() {
        sAchternaam.set(getLid().getAchternaam());
    }

    public List<Activiteit> getActiviteiten() {
        return activiteiten;
    }

    public void voegActiviteitToe(Activiteit activiteit) {
        activiteit.voegInschrijvingToe(this);
        this.activiteiten.add(activiteit);
    }

    public void verwijderActiviteit(Activiteit activiteit) {
        activiteit.verwijderInschrijving(this);
        this.activiteiten.remove(activiteit);
    }
}
