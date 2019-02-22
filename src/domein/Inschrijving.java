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
public class Inschrijving {

    private Formule formule;
    private Lid lid;
    private String tijdstip;

    public Inschrijving(Formule formule, Lid lid, String tijdstip) {
        setFormule(formule);
        setLid(lid);
        setTijdstip(tijdstip);
    }

    public Formule getFormule() {
        return formule;
    }

    private void setFormule(Formule formule) {
        this.formule = formule;
    }

    public Lid getLid() {
        return lid;
    }

    private void setLid(Lid lid) {
        this.lid = lid;
    }

    public String getTijdstip() {
        return tijdstip;
    }

    private void setTijdstip(String tijdstip) {
        this.tijdstip = tijdstip;
    }
    

}
