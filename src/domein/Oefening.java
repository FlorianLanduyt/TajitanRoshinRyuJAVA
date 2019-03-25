/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.enums.Formule;
import domein.enums.Graad;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author Tim
 */
@Entity
public class Oefening implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String titel;
    private String urlVideo;
    private String afbeelding;
    private String tekst;
    private Graad graad;
    private int aantalRaadplegingen;
    private LocalDate laatsteRaadpleging;
    @ManyToOne
    private Thema thema;

    //SimpleStringproperties voor tableview
    @Transient
    private SimpleStringProperty sNaam = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sGraad = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sThema = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty sAantalRaadplegingen = new SimpleStringProperty();

    public Oefening() {
    }

    public Oefening(String titel, String urlVideo, String afbeelding, String tekst, Graad graad, Thema thema) {
        setTitel(titel);
        setUrlVideo(urlVideo);
        setAfbeelding(afbeelding);
        setTekst(tekst);
        setGraad(graad);
        setThema(thema);

        this.aantalRaadplegingen = 0;
        sAantalRaadplegingen.set(String.valueOf(0));
        this.laatsteRaadpleging = null;
    }

    //Getters voor SimpleStringProperties
    public SimpleStringProperty naamProperty() {
        return sNaam;
    }

    public SimpleStringProperty graadProperty() {
        return sGraad;
    }

    public SimpleStringProperty themaProperty() {
        return sThema;
    }

    public SimpleStringProperty aantalRaadplegingenProperty() {
        return sAantalRaadplegingen;
    }
    public void setSimpleStringProperties() {
        sNaam.set(titel);
        sGraad.set(getGraad().name());
        sThema.set(getThema().getNaam());
        sAantalRaadplegingen.set(String.valueOf(aantalRaadplegingen));
    }
    //Gewone getters en setters
    public String getTitel() {
        return this.titel;
    }

    public void setTitel(String titel) {
        if (titel == null || titel.isEmpty()) {
            throw new IllegalArgumentException("Titel mag niet leeg zijn.");
        }
        titel = titel.trim();
        if (titel.length() > 25) {
            throw new IllegalArgumentException("Titel mag max. 25 karakters bevatten.");
        }
        if (titel.contains(" ")) {
            String tempTitel = titel.replaceAll(" ", "");
            if (tempTitel.matches(".*[\\d\\W].*")) {
                throw new InputMismatchException("Titel mag enkel letters bevatten.");
            }
        } else {
            if (titel.matches(".*[\\d\\W].*")) {
                throw new InputMismatchException("Titel mag enkel letters bevatten.");
            }
        }
        this.titel = titel;
        sNaam.setValue(titel);
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        if (urlVideo == null || urlVideo.isEmpty()) {
            throw new IllegalArgumentException("Video-url mag niet leeg zijn.");
        }
        if (urlVideo.length() <= 100) {
            this.urlVideo = urlVideo;
        } else {
            throw new IllegalArgumentException("Video-url mag max. 100 karakters bevatten.");
        }
    }

    public String getAfbeelding() {
        return afbeelding;
    }

    public void setAfbeelding(String afbeelding) {
        if (afbeelding == null || afbeelding.isEmpty()) {
            throw new IllegalArgumentException("Afbeelding-url mag niet leeg zijn.");
        }
        if (afbeelding.length() <= 100) {
            this.afbeelding = afbeelding;
        } else {
            throw new IllegalArgumentException("Afbeelding-url mag max. 100 karakters bevatten.");
        }
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        if (tekst == null || tekst.isEmpty()) {
            throw new IllegalArgumentException("Tekst mag niet leeg zijn.");
        } else {
            this.tekst = tekst;
        }
    }

    public Graad getGraad() {
        return this.graad;
    }

    public void setGraad(Graad graad) {
        if (graad == null) {
            throw new IllegalArgumentException("Graad mag niet leeg zijn.");
        }
        if (Arrays.asList(Graad.values()).contains(graad)) {
            this.graad = graad;
            sGraad.set(graad.name());
        } else {
            throw new IllegalArgumentException("Graad bestaat niet.");
        }

    }

    public Thema getThema() {
        return thema;
    }

    public void setThema(Thema thema) {
        if (thema == null) {
            throw new IllegalArgumentException("Thema mag niet leeg zijn.");
        } else {
            this.thema = thema;
            sThema.set(thema.getNaam());
        }
    }

    public int getAantalRaadplegingen() {
        return aantalRaadplegingen;
    }

    public void setAantalRaadplegingen(int aantalRaadplegingen) {
        this.aantalRaadplegingen = aantalRaadplegingen;
        sAantalRaadplegingen.set(String.valueOf(aantalRaadplegingen));
    }

    public LocalDate getLaatsteRaadpleging() {
        return laatsteRaadpleging;
    }

    public void setLaatsteRaadpleging(LocalDate laatsteRaadpleging) {
        this.laatsteRaadpleging = laatsteRaadpleging;
    }
}
