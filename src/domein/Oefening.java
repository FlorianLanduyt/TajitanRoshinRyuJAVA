/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.enums.Graad;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Tim
 */
@Entity
public class Oefening implements Serializable {

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
        this.laatsteRaadpleging = null;
    }

    public String getTitel() {
        return titel;
    }

    private void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    private void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getAfbeelding() {
        return afbeelding;
    }

    private void setAfbeelding(String afbeelding) {
        this.afbeelding = afbeelding;
    }

    public String getTekst() {
        return tekst;
    }

    private void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Graad getGraad() {
        return graad;
    }

    public void setGraad(Graad graad) {
        this.graad = graad;
    }

    public Thema getThema() {
        return thema;
    }

    private void setThema(Thema thema) {
        this.thema = thema;
    }

    public int getAantalRaadplegingen() {
        return aantalRaadplegingen;
    }

    public void setAantalRaadplegingen(int aantalRaadplegingen) {
        this.aantalRaadplegingen = aantalRaadplegingen;
    }

    public LocalDate getLaatsteRaadpleging() {
        return laatsteRaadpleging;
    }

    public void setLaatsteRaadpleging(LocalDate laatsteRaadpleging) {
        this.laatsteRaadpleging = laatsteRaadpleging;
    }
}
