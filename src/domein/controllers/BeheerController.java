package domein.controllers;

import domein.Aanwezigheid;
import domein.activiteit.Activiteit;
import domein.Admin;
import domein.inschrijving.Inschrijving;
import domein.Lid;
import domein.Oefening;
import domein.Raadpleging;
import domein.Thema;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import persistentie.DataInitializer;

public class BeheerController {

    private List<Aanwezigheid> aanwezigheden;
    private List<Activiteit> activiteiten;
    private List<Inschrijving> inschrijvingen;
    private List<Lid> leden;
    private List<Oefening> oefeningen;
    private List<Raadpleging> raadplegingen;
    private List<Thema> themas;
    private List<Admin> admins;
    
    public BeheerController() {
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
    private Lid geefLidDoorId(int id) {
        Optional<Lid> lid = this.leden.stream().filter(l -> l.getId() == id).findFirst();
        if (lid.isPresent()) {
            return lid.get();
        }
        return null;
    }

    //Gaat pas werken eens JPA configured en de effectieve data uit db wordt gehaald -> id is dan pas echt aanwezig en niet overal null
    //Zie boven
    public void wijzigLid(Lid lidMetGewijzigdeVelden) {
        Lid lid = geefLidDoorId(lidMetGewijzigdeVelden.getId());
        lid.setVoornaam(lidMetGewijzigdeVelden.getVoornaam());
        lid.setAchternaam(lidMetGewijzigdeVelden.getAchternaam());
        lid.setGsmNr(lidMetGewijzigdeVelden.getGsmNr());
        lid.setVasteTelefoonNr(lidMetGewijzigdeVelden.getVasteTelefoonNr());
        lid.setStraat(lidMetGewijzigdeVelden.getStraat());
        lid.setHuisNr(lidMetGewijzigdeVelden.getHuisNr());
        lid.setPostcode(lidMetGewijzigdeVelden.getPostcode());
        lid.setEmail(lidMetGewijzigdeVelden.getEmail());
        lid.setWachtwoord(lidMetGewijzigdeVelden.getWachtwoord());
        lid.setEmailVader(lidMetGewijzigdeVelden.getEmailVader());
        lid.setEmailMoeder(lidMetGewijzigdeVelden.getEmailMoeder());
        lid.setBeroep(lidMetGewijzigdeVelden.getBeroep());
        lid.setGraad(lidMetGewijzigdeVelden.getGraad());
    }

    public void voegLidToe(Lid lid) {
        this.leden.add(lid);
    }

    public void verwijderLid(Lid lid) {
        this.leden.remove(lid);
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
}
