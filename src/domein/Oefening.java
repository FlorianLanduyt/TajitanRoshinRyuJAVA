/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author Tim
 */
public class Oefening {

    private String titel;
    private String urlVideo;
    private String afbeelding;
    private String tekst;
    private Thema thema;

    public Oefening(String titel, String urlVideo, String afbeelding, String tekst, Thema thema) {
        setTitel(titel);
        setUrlVideo(urlVideo);
        setAfbeelding(afbeelding);
        setTekst(tekst);
        setThema(thema);
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

    public Thema getThema() {
        return thema;
    }

    private void setThema(Thema thema) {
        this.thema = thema;
    }

}
