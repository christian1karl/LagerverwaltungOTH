package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Kunde extends Person implements Serializable{
    
    @Id
    private int Kundennummer;

    public int getKundennummer() {
        return Kundennummer;
    }

    public void setKundennummer(int Kundennummer) {
        this.Kundennummer = Kundennummer;
    }
    
    
}
