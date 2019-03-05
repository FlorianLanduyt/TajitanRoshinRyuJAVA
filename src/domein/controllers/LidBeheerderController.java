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
import java.time.LocalDate;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
    
    //Gaat pas werken eens JPA configured en de effectieve data uit db wordt gehaald -> id is dan pas echt aanwezig en niet overal null
    //Zie boven
    public void wijzigLid(Lid lid, String voornaam, String achternaam,LocalDate geboorteDatum,String rijksregisterNr
            , LocalDate datumEersteTraining, String gsmNr, String vasteTelefoonNr,
             String straat,String stad, String huisNr, String bus, String postcode, String email
            ,  String emailVader, String emailMoeder,String geboorteplaats ,String wachtwoord, String nationaliteit
            , String beroep, Graad graad, Functie functie, String geslacht) {
        
        lid.setVoornaam(voornaam);
        lid.setAchternaam(achternaam);
        lid.setGeboortedatum(geboorteDatum);
        lid.setRijksregisterNr(rijksregisterNr);
        lid.setDatumEersteTraining(datumEersteTraining);
        lid.setGsmNr(gsmNr);
        lid.setVasteTelefoonNr(vasteTelefoonNr);
        lid.setStraat(straat);
        lid.setStad(stad);
        lid.setHuisNr(huisNr);
        lid.setBus(bus);
        lid.setPostcode(postcode);
        lid.setEmail(email);
        lid.setEmailVader(emailVader);
        lid.setEmailMoeder(emailMoeder);
        lid.setGeboorteplaats(geboorteplaats);
        lid.setWachtwoord(wachtwoord);
        lid.setNationaliteit(nationaliteit);
        lid.setBeroep(beroep);
        lid.setGraad(graad);
        lid.setFunctie(functie);
        lid.setGeslacht(geslacht);
    }

    public void voegLidToe(String voornaam, String achternaam,LocalDate geboorteDatum,String rijksregisterNr
            , LocalDate datumEersteTraining, String gsmNr, String vasteTelefoonNr,
             String straat,String stad, String huisNr, String bus, String postcode, String email
            ,  String emailVader, String emailMoeder,String geboorteplaats ,String wachtwoord, String nationaliteit
            , String beroep, Graad graad, Functie functie,String geslacht) {
        
        Lid lid = new Lid(voornaam, achternaam,geboorteDatum,rijksregisterNr,datumEersteTraining
                ,gsmNr,vasteTelefoonNr,stad,straat,huisNr,postcode,email,wachtwoord,geboorteplaats,geslacht,nationaliteit,graad,functie);
        lid.setEmailMoeder(emailMoeder);
        lid.setEmailVader(emailVader);
        lid.setBus(bus);
        lid.setBeroep(beroep);
        
        this.ledenList.add(lid);
    }

    public void verwijderLid(Lid lid) {
        this.ledenList.remove(lid);
    }

    
    //
    //ENUMS
    //
    public ObservableList<Formule> geefFormules() {
        ObservableList<Formule> formules = FXCollections.observableArrayList(dataController.geefFormules());
        return FXCollections.unmodifiableObservableList(formules);
    }

    public ObservableList<Functie> geefFuncties() {
        ObservableList<Functie> functies = FXCollections.observableArrayList(dataController.geefFuncties());
        return FXCollections.unmodifiableObservableList(functies);
    }
    
    public ObservableList<Graad> geefGraden(){
        ObservableList<Graad> graden = FXCollections.observableArrayList(dataController.geefGraden());
        return graden;
    }
    
    public ObservableList<String> geefGeslachten(){
        ObservableList<String> geslachten = FXCollections.observableArrayList(dataController.geefGeslachten());
        return geslachten;      
    }
    
    
    //
    //Observer
    //
    public void addObserver(ListChangeListener<Lid> listener){
        ledenList.addListener(listener);
                
    }
    
    
}
