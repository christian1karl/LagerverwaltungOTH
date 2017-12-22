package de.othr.sw.lagerhaus.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import de.othr.sw.lagerhaus.validation.ValidPlz;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Embeddable
public class Adresse implements Serializable {
    
    private String Hausnummerzusatz;
    
    @Min(1)
    private int Hausnummer;
    
    @Size(min = 1)
    private String Strasse;
    
    @ValidPlz
    private String Postleitzahl;
    
    private String Ort;
    
    private String Land;

    public Adresse (){}

    public Adresse(String Hausnummerzusatz, int Hausnummer, String Strasse, String Postleitzahl, String Ort, String Land) {
        this.Hausnummerzusatz = Hausnummerzusatz;
        this.Hausnummer = Hausnummer;
        this.Strasse = Strasse;
        this.Postleitzahl = Postleitzahl;
        this.Ort = Ort;
        this.Land = Land;
    }
    
    public String getHausnummerzusatz() {
        return Hausnummerzusatz;
    }

    public void setHausnummerzusatz(String Hausnummerzusatz) {
        this.Hausnummerzusatz = Hausnummerzusatz;
    }

    public int getHausnummer() {
        return Hausnummer;
    }

    public void setHausnummer(int Hausnummer) {
        this.Hausnummer = Hausnummer;
    }

    public String getStrasse() {
        return Strasse;
    }

    public void setStrasse(String Strasse) {
        this.Strasse = Strasse;
    }

    public String getPostleitzahl() {
        return Postleitzahl;
    }

    public void setPostleitzahl(String Postleitzahl) {
        this.Postleitzahl = Postleitzahl;
    }

    public String getOrt() {
        return Ort;
    }

    public void setOrt(String Ort) {
        this.Ort = Ort;
    }

    public String getLand() {
        return Land;
    }

    public void setLand(String Land) {
        this.Land = Land;
    }

    @Override
    public String toString() {
        return "Adresse{" + "Hausnummerzusatz=" + Hausnummerzusatz + ", Hausnummer=" + Hausnummer + ", Strasse=" + Strasse + ", Postleitzahl=" + Postleitzahl + ", Ort=" + Ort + ", Land=" + Land + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.Hausnummerzusatz);
        hash = 83 * hash + this.Hausnummer;
        hash = 83 * hash + Objects.hashCode(this.Strasse);
        hash = 83 * hash + Objects.hashCode(this.Postleitzahl);
        hash = 83 * hash + Objects.hashCode(this.Ort);
        hash = 83 * hash + Objects.hashCode(this.Land);
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
        final Adresse other = (Adresse) obj;
        if (this.Hausnummer != other.Hausnummer) {
            return false;
        }
        if (!Objects.equals(this.Postleitzahl, other.Postleitzahl)) {
            return false;
        }
        if (!Objects.equals(this.Hausnummerzusatz, other.Hausnummerzusatz)) {
            return false;
        }
        if (!Objects.equals(this.Strasse, other.Strasse)) {
            return false;
        }
        if (!Objects.equals(this.Ort, other.Ort)) {
            return false;
        }
        if (!Objects.equals(this.Land, other.Land)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}


