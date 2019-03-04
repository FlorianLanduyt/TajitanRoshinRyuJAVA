/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.controllers;

import domein.Lid;
import domein.enums.Formule;
import domein.enums.Functie;
import domein.enums.Graad;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author robdeputter
 */
public class LidBeheerderController {
    
    private List<Lid> leden;
    private DataController dataController;
   
    

    public LidBeheerderController() {
        dataController = new DataController();
        this.leden = dataController.geefLeden();
    }
    
    
    //
    //Overzichte van leden!
    //
    public ObservableList<Lid> geefOverzichtLeden() {
        ObservableList<Lid> ledenSorted = FXCollections.observableArrayList(leden.stream()
                .sorted(Comparator.comparing(Lid::getVoornaam).thenComparing(Lid::getAchternaam))
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(ledenSorted);
    }

    public ObservableList<Lid> geefOverzichtLid(Lid lid) {
        ObservableList<Lid> ledenSorted = FXCollections.observableArrayList(leden.stream()
                .filter(l -> l.equals(lid))
                .sorted(Comparator.comparing(Lid::getVoornaam).thenComparing(Lid::getAchternaam))
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(ledenSorted);
    }

    public ObservableList<Lid> geefOverzichtLedenVoorBepaaldeGraad(Graad graad) {
        ObservableList<Lid> ledenSorted = FXCollections.observableArrayList(leden.stream()
                .filter(l -> l.getGraad().equals(graad))
                .sorted(Comparator.comparing(Lid::getVoornaam).thenComparing(Lid::getAchternaam))
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(ledenSorted);
    }

    public ObservableList<Lid> geefOverzichtLedenVoorBepaaldType(Functie functie) {
        ObservableList<Lid> ledenSorted = FXCollections.observableArrayList(leden.stream()
                .filter(l -> l.getFunctie().equals(functie))
                .sorted(Comparator.comparing(Lid::getVoornaam).thenComparing(Lid::getAchternaam))
                .collect(Collectors.toList()));
        return FXCollections.unmodifiableObservableList(ledenSorted);
    }
    
    //
    //CRUD-operaties
    //
    
     public void wijzigLid(Lid lidMetGewijzigdeVelden) {
        dataController.wijzigLid(lidMetGewijzigdeVelden);
    }

    public void voegLidToe(Lid lid) {
        dataController.voegLidToe(lid);
    }

    public void verwijderLid(Lid lid) {
        dataController.verwijderLid(lid);
        
        
    }

    
    //
    //ENUMS
    //
    public ObservableList<Formule> geefFormules() {
        ObservableList<Formule> formules = FXCollections.observableArrayList(Arrays.asList(Formule.values()));
        return FXCollections.unmodifiableObservableList(formules);
    }

    public ObservableList<Functie> geefFuncties() {
        ObservableList<Functie> functies = FXCollections.observableArrayList(Arrays.asList(Functie.values()));
        return FXCollections.unmodifiableObservableList(functies);
    }
    
    public ObservableList<Graad> geefGraden(){
        ObservableList<Graad> graden = FXCollections.observableArrayList(Arrays.asList(Graad.values()));
        return graden;
    }
    
    public ObservableList<String> geefGeslachten(){
        ObservableList<String> geslachten = FXCollections.observableArrayList("Man","Vrouw");
        return geslachten;      
    }
    
    
    
    
    
    
}
