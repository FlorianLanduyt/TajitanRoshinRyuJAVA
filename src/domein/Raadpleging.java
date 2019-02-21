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
public class Raadpleging {

    private Lid lid;
    private Oefening oefening;
    private int teller;

    public Raadpleging(Lid lid, Oefening oefening, int teller) {
        setLid(lid);
        setOefening(oefening);
        setTeller(teller);
    }

    public Lid getLid() {
        return lid;
    }

    private void setLid(Lid lid) {
        this.lid = lid;
    }

    public Oefening getOefening() {
        return oefening;
    }

    private void setOefening(Oefening oefening) {
        this.oefening = oefening;
    }

    public int getTeller() {
        return teller;
    }

    private void setTeller(int teller) {
        this.teller = teller;
    }

}
