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
public class Formule{
    private String tijdstip;
    private String beschrijving;

    public Formule(String tijdstip, String beschrijving) {
        setTijdstip(tijdstip);
        setBeschrijving(beschrijving);
    }
    
    public String getTijdstip() {
        return tijdstip;
    }

    private void setTijdstip(String tijdstip) {
        this.tijdstip = tijdstip;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    private void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
    
    
}