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
public class Aanwezigheid {

    private Lid lid;
    private Activiteit activiteit;
    private int puntenAantal;

    public Aanwezigheid(Lid lid, Activiteit activiteit) {
        setLid(lid);
        setActiviteit(activiteit);
        berekenPunten();
    }

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
    
    private void berekenPunten() {
        switch (activiteit.getFormule().name()) {
            case "DI_DO":
            case "DI_ZA":
            case "WO_ZA":
                this.puntenAantal = 5;
                break;
            case "WO":
            case "ZA":
                this.puntenAantal = 10;
                break;
            default:
                this.puntenAantal = 0;
        }
    }
}
