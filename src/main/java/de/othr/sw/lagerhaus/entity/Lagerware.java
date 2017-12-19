package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Lagerware implements Serializable{
    
    @Id
    private int _lagerwarennummer;
    private String _warenbezeichnung;
    //private Lagerauftrag _einlagerungsauftrag;
    //private Lagerauftrag _auslagerungsauftrag;
    @ManyToOne
    @JoinColumn(name = "_lagerplatznummer")
    private Lagerplatz _lagerplatz;

    public int getLagerwarennummer() {
        return _lagerwarennummer;
    }

    public void setLagerwarennummer(int _lagerwarennummer) {
        this._lagerwarennummer = _lagerwarennummer;
    }

    public String getWarenbezeichnung() {
        return _warenbezeichnung;
    }

    public void setWarenbezeichnung(String _warenbezeichnung) {
        this._warenbezeichnung = _warenbezeichnung;
    }

    public Lagerplatz getLagerplatz() {
        return _lagerplatz;
    }

    public void setLagerplatz(Lagerplatz _lagerplatz) {
        this._lagerplatz = _lagerplatz;
    }
    
    
}
