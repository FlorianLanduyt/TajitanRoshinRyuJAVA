package domein.controllers;

import domein.Aanwezigheid;
import domein.Activiteit;
import domein.Admin;
import domein.Inschrijving;
import domein.Lid;
import domein.Oefening;
import domein.Raadpleging;
import domein.Thema;
import domein.enums.Formule;
import domein.enums.Functie;
import domein.enums.Graad;
import domein.enums.LeeftijdsCategorie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import persistentie.DataInitializerForDemo;

public class DataController {

    private List<Aanwezigheid> aanwezigheden;
    private List<Activiteit> activiteiten;
    private List<Inschrijving> inschrijvingen;
    private List<Lid> leden;
    private List<Oefening> oefeningen;
    private List<Raadpleging> raadplegingen;
    private List<Thema> themas;
    private List<Admin> admins;

    public DataController() {
        this.aanwezigheden = new ArrayList<>();
        this.activiteiten = new ArrayList<>();
        this.inschrijvingen = new ArrayList<>();
        this.leden = new ArrayList<>();
        this.oefeningen = new ArrayList<>();
        this.raadplegingen = new ArrayList<>();
        this.themas = new ArrayList<>();
        this.admins = new ArrayList<>();

        //Calling initializeData
        InitializeDataForDemo();
    }

    //DataInitializer method (hardcoded data for the moment)
    private void InitializeDataForDemo() {
        DataInitializerForDemo.InitializeDataForDemo(this.inschrijvingen, this.activiteiten,
                this.aanwezigheden, this.leden, this.raadplegingen, this.oefeningen,
                this.admins, this.themas);
    }

    //
    //GETTERS
    //
    public List<Aanwezigheid> geefAanwezigheden() {
        return this.aanwezigheden;
    }

    public List<Inschrijving> geefInschrijvingen() {
        return this.inschrijvingen;
    }

    public List<Raadpleging> geefRaadplegingen() {
        return this.raadplegingen;
    }

    public List<Thema> geefThemas() {
        return this.themas;
    }

    public List<Activiteit> geefActiviteiten() {
        return this.activiteiten;
    }

    public List<Lid> geefLeden() {
        return this.leden;
    }

    public List<Oefening> geefOefeningen() {
        return this.oefeningen;
    }

    public List<Admin> geefAdmins() {
        return this.admins;
    }

    //
    //ENUMS
    //
    public List<Formule> geefFormules() {
        List<Formule> formules = Arrays.asList(Formule.values());
        return formules;
    }

    public List<Functie> geefFuncties() {
        List<Functie> functies = Arrays.asList(Functie.values());
        return functies;
    }

    public List<Graad> geefGraden() {
        List<Graad> graden = Arrays.asList(Graad.values());
        return graden;
    }

    public List<String> geefGeslachten() {
        List<String> geslachten = Arrays.asList("Man", "Vrouw");
        return geslachten;
    }
    
    public List<LeeftijdsCategorie> geefLeeftijdsCategoriën(){
        List<LeeftijdsCategorie> leeftijdsCategoriën = Arrays.asList(LeeftijdsCategorie.values());
        return leeftijdsCategoriën;
    }

    public List<Formule> geefFormulesVanLid(Lid lid) {
        return inschrijvingen.stream()
                .filter(i -> i.getLid().equals(lid))
                .map(Inschrijving::getFormule)
                .distinct()
                .collect(Collectors.toList());
    }
}
