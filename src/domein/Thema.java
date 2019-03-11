/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Tim
 */
@Entity
public class Thema implements Serializable{
    public String naam;

    public Thema() {
    }
    
    public Thema(String naam){
        this.naam = naam;
    }
    
}
