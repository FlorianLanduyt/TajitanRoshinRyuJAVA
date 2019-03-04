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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 *
 * @author robdeputter
 */
public class LidBeheerderController {
    
    private DataController dataController;
    private ObservableList<Lid> ledenList;
    private FilteredList<Lid> filteredList;
    private SortedList<Lid> sortedList;
    
    private final Comparator<Lid> byVoornaam = (p1, p2) -> p1.getVoornaam().compareToIgnoreCase(p2.getVoornaam());
    
    private final Comparator<Lid> byAchternaam = (p1, p2) -> p1.getAchternaam().compareToIgnoreCase(p2.getAchternaam());
    
    private final Comparator<Lid> sortOrder = byVoornaam.thenComparing(byAchternaam);
    
   
    

    public LidBeheerderController() {
        dataController = new DataController();
        ledenList = FXCollections.observableArrayList(dataController.geefLeden());
        filteredList = new FilteredList(ledenList , p -> true);
        sortedList = new SortedList(filteredList,sortOrder);
    }
    
    
    //
    //Overzichte van leden!
    //
    public ObservableList<Lid> geefAlleLeden(){ //voor combobox
        return FXCollections.unmodifiableObservableList(ledenList);
    }
    public ObservableList<Lid> geefObservableListLeden(){
        return FXCollections.unmodifiableObservableList(sortedList);
    }
    
    public void geefOverzichtLeden() {
        filteredList.setPredicate(lid -> {
            return true;
        });
    }

    public void geefOverzichtLid(Lid lid) {
        filteredList.setPredicate(l -> {
            if(l.equals(lid)){
                return true;
            }
            return false;
        } );
        
    }

    public void geefOverzichtLedenVoorBepaaldeGraad(Graad graad) {
        filteredList.setPredicate(lid -> lid.getGraad().equals(graad));
    }

    public void geefOverzichtLedenVoorBepaaldType(Functie functie) {
        filteredList.setPredicate(lid -> lid.getFunctie().equals(functie));
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
        ledenList.remove(lid); //dit zou eigenlijk niet mogen
        
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
