package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import java.util.List;

public class Kunde extends Person implements Serializable{
    
    private int Kundennummer;
    private List<Lagerauftrag> Lagerauftraege;
    
    
    
}
