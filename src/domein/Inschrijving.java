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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Inschrijving implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Lid lid;

    @Enumerated(EnumType.STRING)
    private Formule formule;

    private LocalDate tijdstip;

    //SimpleStringProperties
    @Transient
    private SimpleStringProperty sVoornaam = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sAchternaam = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sFormule = new SimpleStringProperty();
    @Transient
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

    public void setSimpleStringProperties() {
        sVoornaam.set(getVoornaam());
        sAchternaam.set(getAchternaam());
        sFormule.set(getFormule().name());
        sTijdstip.set(getTijdstip().toString());
    }

    //Gewone getters en setters
    public Formule getFormule() {
        return this.formule;
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
        return this.tijdstip;
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
        return getLid().getVoornaam();
    }

    public void setVoornaam() {
        sVoornaam.set(getLid().getVoornaam());
    }

    public String getAchternaam() {
        return getLid().getAchternaam();
    }

    public void setAchternaam() {
        sAchternaam.set(getLid().getAchternaam());
    }
}
