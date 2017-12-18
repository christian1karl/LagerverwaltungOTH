package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Mitarbeiter extends Person implements Serializable{
    
    private int _mitarbeiternummer;
    private String _taetigkeit;
    private double _gehalt;
    
    public Mitarbeiter()
    {
        
    }
    
    public int getMitarbeiternummer() {
        return _mitarbeiternummer;
    }

    public void setMitarbeiternummer(int Mitarbeiternummer) {
        this._mitarbeiternummer = Mitarbeiternummer;
    }

    public String getTaetigkeit() {
        return _taetigkeit;
    }

    public void setTaetigkeit(String Taetigkeit) {
        this._taetigkeit = Taetigkeit;
    }

    public double getGehalt() {
        return _gehalt;
    }

    public void setGehalt(double Gehalt) {
        this._gehalt = Gehalt;
    }
    
    
    
    
    
}
