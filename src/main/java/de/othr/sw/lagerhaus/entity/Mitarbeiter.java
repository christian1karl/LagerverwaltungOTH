package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mitarbeiter extends Person implements Serializable{
    
    @Id
    private int Mitarbeiternummer;
    
    private String Taetigkeit;
    private double Gehalt;
    
    public Mitarbeiter()
    {
        
    }
    
    public int getMitarbeiternummer() {
        return Mitarbeiternummer;
    }

    public void setMitarbeiternummer(int Mitarbeiternummer) {
        this.Mitarbeiternummer = Mitarbeiternummer;
    }

    public String getTaetigkeit() {
        return Taetigkeit;
    }

    public void setTaetigkeit(String Taetigkeit) {
        this.Taetigkeit = Taetigkeit;
    }

    public double getGehalt() {
        return Gehalt;
    }

    public void setGehalt(double Gehalt) {
        this.Gehalt = Gehalt;
    }
    
    
    
    
    
}
