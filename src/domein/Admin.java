package domein;

public class Admin {

    /*Admin klasse dient om later in de domeincontroller een 'ingelogdeAdmin:Admin' te kunnen bijhouden,
      Op deze manier kunnen we dan bepaalde functionaliteiten enablen/disablen op basis of er een
      Admin is ingelogd of niet
     */

    private int id;
    private String gebruikersnaam;
    private String wachtwoord;

    public Admin(String gebruikersnaam, String wachtwoord) {
        setGebruikersnaam(gebruikersnaam);
        setWachtwoord(wachtwoord);
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    private void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    private void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

}