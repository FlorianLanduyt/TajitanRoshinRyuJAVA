package domein;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Admin implements Serializable{

    /*Admin klasse dient om later in de domeincontroller een 'ingelogdeAdmin:Admin' te kunnen bijhouden,
      Op deze manier kunnen we dan bepaalde functionaliteiten enablen/disablen op basis of er een
      Admin is ingelogd of niet
     */
    @Id
    private int id;
    private String gebruikersnaam;
    private String wachtwoord;

    public Admin() {
    }

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

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Admin other = (Admin) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.gebruikersnaam.toLowerCase(), other.gebruikersnaam.toLowerCase())) {
            return false;
        }
        if (!Objects.equals(this.wachtwoord, other.wachtwoord)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
