package domein.controllers;

import domein.Aanwezigheid;
import domein.activiteit.Activiteit;
import domein.Admin;
import domein.inschrijving.Inschrijving;
import domein.Lid;
import domein.Oefening;
import domein.Raadpleging;
import domein.Thema;
import domein.enums.Formule;
import domein.enums.Functie;
import domein.enums.Graad;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import persistentie.DataInitializer;

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
        InitializeData();
    }

    //DataInitializer method (hardcoded data for the moment)
    private void InitializeData() {
        DataInitializer.initializeData(this.inschrijvingen, this.activiteiten,
                this.aanwezigheden, this.leden, this.raadplegingen, this.oefeningen,
                this.admins);
    }

    //Getters voor aanwezigheden, inschrijvingen, raadplegingen, themas
    //Geen CRUD operaties at the moment
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
    
    //
    //CRUD OPERATIES ACTIVITEITEN
    //
    public List<Activiteit> geefActiviteiten() {
        return this.activiteiten;
    }

    public void voegActiviteitToe(Activiteit activiteit) {
        this.activiteiten.add(activiteit);
    }

    public void verwijderActiviteit(Activiteit activiteit) {
        this.activiteiten.remove(activiteit);
    }

    //
    //CRUD OPERATIES LEDEN
    //
    public List<Lid> geefLeden() {
        return this.leden;
    }

    //Gaat pas werken eens JPA configured en de effectieve data uit db wordt gehaald -> id is dan pas echt aanwezig en niet overal null
    //Als je deze methode toch nodig hebt, kan je het aanpassen naar parameter rijksregisternr, tot we een DB hebben
    public Lid geefLidDoorId(int id) {
        Optional<Lid> lid = this.leden.stream().filter(l -> l.getId() == id).findFirst();
        if (lid.isPresent()) {
            return lid.get();
        }
        return null;
    }


    //
    //CRUD OPERATIES OEFENINGEN
    // 
    public List<Oefening> geefOefeningen() {
        return this.oefeningen;
    }

    public void voegOefeningToe(Oefening oefening) {
        this.oefeningen.add(oefening);
    }

    public void verwijderOefening(Oefening oefening) {
        this.oefeningen.remove(oefening);
    }
    
    //
    //CRUD OPERATIES ADMIN
    //
    public List<Admin> geefAdmins() {
        return this.admins;
    }

    public void voegAdminToe(Admin admin) {
        this.admins.add(admin);
    }

    public void verwijderAdmin(Admin admin) {
        this.admins.remove(admin);
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
    
    public List<Graad> geefGraden(){
        List<Graad> graden = Arrays.asList(Graad.values());
        return graden;
    }
    
    public List<String> geefGeslachten(){
        List<String> geslachten = Arrays.asList("Man","Vrouw");
        return geslachten;      
    }
}
