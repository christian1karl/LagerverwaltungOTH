package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class Adresse implements Serializable {
    
    private String _hausnummerzusatz;
    private int _hausnummer;
    private String _strasse;
    private int _postleitzahl;
    private String _ort;
    private String _land;

    public Adresse (){
        
    }

    public Adresse(String _hausnummerzusatz, int _hausnummer, String _strasse, int _postleitzahl, String _ort, String _land) {
        this._hausnummerzusatz = _hausnummerzusatz;
        this._hausnummer = _hausnummer;
        this._strasse = _strasse;
        this._postleitzahl = _postleitzahl;
        this._ort = _ort;
        this._land = _land;
    }
    
    public String getHausnummerzusatz() {
        return _hausnummerzusatz;
    }

    public void setHausnummerzusatz(String _hausnummerzusatz) {
        this._hausnummerzusatz = _hausnummerzusatz;
    }

    public int getHausnummer() {
        return _hausnummer;
    }

    public void setHausnummer(int _hausnummer) {
        this._hausnummer = _hausnummer;
    }

    public String getStraße() {
        return _strasse;
    }

    public void setStraße(String _straße) {
        this._strasse = _straße;
    }

    public int getPostleitzahl() {
        return _postleitzahl;
    }

    public void setPostleitzahl(int _postleitzahl) {
        this._postleitzahl = _postleitzahl;
    }

    public String getOrt() {
        return _ort;
    }

    public void setOrt(String _ort) {
        this._ort = _ort;
    }

    public String getLand() {
        return _land;
    }

    public void setLand(String _land) {
        this._land = _land;
    }

    @Override
    public String toString() {
        return "Adresse{" + "Hausnummerzusatz=" + _hausnummerzusatz + ", Hausnummer=" + _hausnummer + ", Strasse=" + _strasse + ", Postleitzahl=" + _postleitzahl + ", Ort=" + _ort + ", Land=" + _land + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this._hausnummerzusatz);
        hash = 83 * hash + this._hausnummer;
        hash = 83 * hash + Objects.hashCode(this._strasse);
        hash = 83 * hash + this._postleitzahl;
        hash = 83 * hash + Objects.hashCode(this._ort);
        hash = 83 * hash + Objects.hashCode(this._land);
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
        if (this._hausnummer != other._hausnummer) {
            return false;
        }
        if (this._postleitzahl != other._postleitzahl) {
            return false;
        }
        if (!Objects.equals(this._hausnummerzusatz, other._hausnummerzusatz)) {
            return false;
        }
        if (!Objects.equals(this._strasse, other._strasse)) {
            return false;
        }
        if (!Objects.equals(this._ort, other._ort)) {
            return false;
        }
        if (!Objects.equals(this._land, other._land)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}


