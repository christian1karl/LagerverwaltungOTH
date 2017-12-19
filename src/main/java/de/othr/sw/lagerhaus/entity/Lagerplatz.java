package de.othr.sw.lagerhaus.entity;

import de.othr.sw.lagerhaus.enums.Lagerstatus;
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
    private int Lagerplatznummer;
    private double Lagerpreis;
    @Enumerated(EnumType.STRING)
    private Lagerstatus Lagerstatus;
    @OneToMany(mappedBy= "Lagerplatz")
    private List<Lagerware> Lagerwaren;

    public int getLagerplatznummer() {
        return Lagerplatznummer;
    }

    public void setLagerplatznummer(int Lagerplatznummer) {
        this.Lagerplatznummer = Lagerplatznummer;
    }
    
    public double getLagerpreis() {
        return Lagerpreis;
    }

    public void setLagerpreis(double Lagerpreis) {
        this.Lagerpreis = Lagerpreis;
    }

    public Lagerstatus getLagerstatus() {
        return Lagerstatus;
    }

    public void setLagerstatus(Lagerstatus Lagerstatus) {
        this.Lagerstatus = Lagerstatus;
    }

    public List<Lagerware> getLagerwaren() {
        return Lagerwaren;
    }

    public void setLagerwaren(List<Lagerware> Lagerwaren) {
        this.Lagerwaren = Lagerwaren;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.Lagerplatznummer;
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
        if (this.Lagerplatznummer != other.Lagerplatznummer) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lagerplatz{" + "Lagernummer=" + Lagerplatznummer + ", Lagerpreis=" + Lagerpreis + ", Lagerstatus=" + Lagerstatus + ", Lagerwaren=" + Lagerwaren + '}';
    }
        
    
    
    
}
