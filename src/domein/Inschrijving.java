package domein;

import domein.enums.Formule;
import exceptions.DatumIntervalException;
import exceptions.VolzetException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Inschrijving implements Serializable {

    @Id
    private int id;

    @ManyToOne
    private Lid lid;

    @Enumerated(EnumType.STRING)
    private Formule formule;

    @Temporal(TemporalType.DATE)
    private LocalDate tijdstip;

    //SimpleStringProperties
    private SimpleStringProperty sVoornaam = new SimpleStringProperty();
    private SimpleStringProperty sAchternaam = new SimpleStringProperty();
    private SimpleStringProperty sFormule = new SimpleStringProperty();
    private SimpleStringProperty sTijdstip = new SimpleStringProperty();

    public Inschrijving() {
    }

    public Inschrijving(Formule formule, Lid lid, LocalDate tijdstip) {
        setFormule(formule);
        setLid(lid);
        setTijdstip(tijdstip);
        setVoornaam();
        setAchternaam();
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
        if (formule == null) {
            throw new IllegalArgumentException("Formule mag niet leeg zijn.");
        }
        if (Arrays.asList(Formule.values()).contains(formule)) {
            this.formule = formule;
            sFormule.set(formule.name());
        } else {
            throw new IllegalArgumentException("Formule bestaat niet.");
        }
    }

    public Lid getLid() {
        return lid;
    }

    private void setLid(Lid lid) {
        if (lid == null) {
            throw new IllegalArgumentException("Lid mag niet leeg zijn.");
        } else {
            this.lid = lid;
        }
    }

    public LocalDate getTijdstip() {
        return LocalDate.parse(sTijdstip.get());
    }

    private void setTijdstip(LocalDate tijdstip) {
        if (tijdstip == null) {
            throw new IllegalArgumentException("Tijdstip inschrijving mag niet leeg zijn.");
        } else {
            this.tijdstip = tijdstip;
            sTijdstip.set(tijdstip.toString());
        }
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
}
