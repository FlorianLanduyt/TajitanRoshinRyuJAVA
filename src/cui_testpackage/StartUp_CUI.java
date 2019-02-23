/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui_testpackage;

import domein.Aanwezigheid;
import domein.Formule;
import domein.Inschrijving;
import domein.OverzichtController;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author robdeputter
 */
public class StartUp_CUI {
    public static void main(String[] args) {
        OverzichtController oc = new OverzichtController();
        //@Tim methode initialiseerData moet hier worden aangeroepen
        
        //Testen van formules Aanwezigheden
        System.out.println(geefOverzichtAanwezighedenInString(oc.geefOverzichtAanwezigheden()));
        
        //lid moet je nog invullen!
        //System.out.println(geefOverzichtAanwezighedenInString(oc.geefOverzichtAanwezighedenVoorBepaaldLid(lid)));
        
        //datum moet je nog invullen!
        //System.out.println(geefOverzichtAanwezighedenInString(oc.geefOverzichtAanwezighedenVoorBepaaldeDatum(datum)));
        
        //formule moet je nog invullen!
        //System.out.println(geefOverzichtAanwezighedenInString(oc.geefOverzichtAanwezighedenVoorBepaaldeFormule(Formule.WO_ZA)));
        
        //Testen van formules Inschrijvingen
        System.out.println(geefOverzichtInschrijvingen(oc.geefOverzichtInschrijvingen()));
        
        //formule moet je nog invullen!
        //System.out.println(geefOverzichtInschrijvingen(oc.geefOverzichtInschrijvingenVoorBepaaldeFormule(Formule.WO_ZA)));
        
        
        
    }
    
    public static String geefOverzichtAanwezighedenInString(List<Aanwezigheid> lijst){
        String uitvoer = String.format("%20s %20s %20s %20s%n",
                "Naam_lid",
                "Naam_activiteit", 
                "Datum_activiteit",
                "Aantal_punten");
        
        uitvoer += lijst.stream().map(aanwezigheid -> String.format("%20s %20s %20s %20d%n", 
                aanwezigheid.getLid().getVoornaam() + " " + aanwezigheid.getLid().getAchternaam(),
                aanwezigheid.getActiviteit().getNaam(),
                aanwezigheid.getActiviteit().getDatum().toString(),
                aanwezigheid.getPuntenAantal()))
                .collect(Collectors.joining("\n"));
        
        return uitvoer;
    }
    
    public static String geefOverzichtInschrijvingen(List<Inschrijving> lijst){
        String uitvoer = String.format("%20s %20s %20s%n",
                "Naam_lid", 
                "Formule", 
                "Datum_inschrijving");
        
        uitvoer += lijst.stream().map(inschrijving -> String.format("%20s %20s %20s%n", 
                inschrijving.getLid().getVoornaam() + " " + inschrijving.getLid().getAchternaam(),
                inschrijving.getFormule(),
                inschrijving.getTijdstip().toString()))
                .collect(Collectors.joining("\n"));
        
        return uitvoer;
    }
}
