package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Lagerplatz implements Serializable {
    
    @Id
    private int _lagerplatznummer;
    private double _lagerpreis;
    @Enumerated(EnumType.STRING)
    private Lagerstatus _lagerstatus;
    @OneToMany(mappedBy= "_lagerplatz")
    private List<Lagerware> _lagerwaren;

    public int getLagerplatznummer() {
        return _lagerplatznummer;
    }

    public void setLagerplatznummer(int _lagerplatznummer) {
        this._lagerplatznummer = _lagerplatznummer;
    }
    
    public double getLagerpreis() {
        return _lagerpreis;
    }

    public void setLagerpreis(double _lagerpreis) {
        this._lagerpreis = _lagerpreis;
    }

    public Lagerstatus getLagerstatus() {
        return _lagerstatus;
    }

    public void setLagerstatus(Lagerstatus _lagerstatus) {
        this._lagerstatus = _lagerstatus;
    }

    public List<Lagerware> getLagerwaren() {
        return _lagerwaren;
    }

    public void setLagerwaren(List<Lagerware> _lagerwaren) {
        this._lagerwaren = _lagerwaren;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this._lagerplatznummer;
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
        final Lagerplatz other = (Lagerplatz) obj;
        if (this._lagerplatznummer != other._lagerplatznummer) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lagerplatz{" + "_lagerplatznummer=" + _lagerplatznummer + ", _lagerpreis=" + _lagerpreis + ", _lagerstatus=" + _lagerstatus + ", _lagerwaren=" + _lagerwaren + '}';
    }
        
    
    
    
}
