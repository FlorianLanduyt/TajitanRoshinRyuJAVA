/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author robdeputter
 */
public class OverzichtController {
    private List<Inschrijving> inschrijvingen;
    private List<Activiteit> activiteiten;
    private List<Aanwezigheid> aanwezigheden;
    private List<Lid> leden;
    private List<Raadpleging> raadplegingen;

    public OverzichtController() {
        this.inschrijvingen = new ArrayList<>();
        this.activiteiten = new ArrayList<>();
        this.aanwezigheden = new ArrayList<>();
        this.leden = new ArrayList<>();
        this.raadplegingen = new ArrayList<>();
    }
    
    public List<Aanwezigheid> geefOverzichtAanwezigheden(){
        return aanwezigheden;
    }
    
    public List<Aanwezigheid> geefOverzichtAanwezighedenVoorBepaaldeDatum(Date datum){
        return aanwezigheden.stream()
                .filter(aanwezigheid -> aanwezigheid.getActiviteit().getDatum().equals(datum))
                .collect(Collectors.toList());
    }
    
    public List<Aanwezigheid> geefOverzichtAanwezighedenVoorBepaaldLid(Lid lid){
        return aanwezigheden.stream()
                .filter(aanwezigheid -> aanwezigheid.getLid().equals(lid))
                .collect(Collectors.toList());
    }
    
    public List<Aanwezigheid> geefOverzichtAanwezighedenVoorBepaaldeFormule(Formule formule){
        return aanwezigheden.stream()
                .filter(aanwezigheid -> aanwezigheid.getActiviteit().getFormule().equals(formule))
                .collect(Collectors.toList());
    }
    
    public List<Inschrijving> geefOverzichtInschrijvingen(){
        return inschrijvingen;
    }
    
    public List<Inschrijving> geefOverzichtInschrijvingenVoorBepaaldeFormule(Formule formule){
        return inschrijvingen.stream()
                .filter(inschrijving -> inschrijving.getFormule().equals(formule))
                .collect(Collectors.toList());
    }
    
    //@Tim => andere formules
    
    
            
}
