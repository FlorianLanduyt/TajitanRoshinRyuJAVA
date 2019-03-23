/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.enums.Formule;
import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author Tim
 */
@Entity
public class Aanwezigheid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Lid lid;
    @ManyToOne
    private Activiteit activiteit;
    private int puntenAantal;

    //SimpleStringProperties
    @Transient
    private SimpleStringProperty sActiviteitNaam = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sVoornaam = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sAchternaam = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sFormule = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sDatum = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sPuntenAantal = new SimpleStringProperty();

    public Aanwezigheid() {
    }

    public Aanwezigheid(Lid lid, Activiteit activiteit) {
        setLid(lid);
        setActiviteit(activiteit);
        setDatum();
        setVoornaam();
        setAchternaam();
        setFormule();
        setActiviteitNaam();
        berekenPunten();
    }

    //Getters voor SimpleStringProperties
    public SimpleStringProperty activiteitNaamProperty() {
        return sActiviteitNaam;
    }

    public SimpleStringProperty voornaamProperty() {
        return sVoornaam;
    }

    public SimpleStringProperty achternaamProperty() {
        return sAchternaam;
    }

    public SimpleStringProperty formuleProperty() {
        return sFormule;
    }

    public SimpleStringProperty datumProperty() {
        return sDatum;
    }

    public SimpleStringProperty puntenAantalProperty() {
        return sPuntenAantal;
    }

    //Gewone getters en setters
    public Lid getLid() {
        return lid;
    }

    private void setLid(Lid lid) {
        this.lid = lid;
    }

    public Activiteit getActiviteit() {
        return activiteit;
    }

    private void setActiviteit(Activiteit activiteit) {
        this.activiteit = activiteit;
    }

    public int getPuntenAantal() {
        return puntenAantal;
    }

    public String getActiviteitNaam() {
        return sActiviteitNaam.get();
    }

    private void setActiviteitNaam() {
        sActiviteitNaam.set(activiteit.getNaam());
    }

    public Formule getFormule() {
        return Formule.valueOf(sFormule.get());
    }

    private void setFormule() {
        sFormule.set(activiteit.getFormule().name());
    }

    public LocalDate getDatum() {
        return LocalDate.parse(sDatum.get());
    }

    private void setDatum() {
        sDatum.set(LocalDate.now().toString());
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

    private void berekenPunten() {
        switch (activiteit.getFormule().name()) {
            case "DI_DO":
            case "DI_ZA":
            case "WO_ZA":
                this.puntenAantal = 5;
                sPuntenAantal.set("5");
                break;
            case "WO":
            case "ZA":
                this.puntenAantal = 10;
                sPuntenAantal.set("10");
                break;
            case "ACTIVITEIT":
            case "UITSTAP":
                this.puntenAantal = 75;
                sPuntenAantal.set("75");
                break;
            case "STAGE":
                this.puntenAantal = 150;
                sPuntenAantal.set("150");
                break;
            case "EXAMEN":
            case "PROEF":
                this.puntenAantal = 500;
                sPuntenAantal.set("500");
                break;
            default:
                this.puntenAantal = 0;
                sPuntenAantal.set("0");
        }
    }
}
