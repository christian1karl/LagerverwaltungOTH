package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kunde extends Person implements Serializable{
    
    @Id
    private int Kundennummer;
//    private List<Lagerauftrag> Lagerauftraege;
//
//    @OneToMany(mappedBy="Auftraggeber")
//    public List<Lagerauftrag> getLagerauftraege() {
//        return Lagerauftraege;
//    }
//
//    public void setLagerauftraege(List<Lagerauftrag> Lagerauftraege) {
//        this.Lagerauftraege = Lagerauftraege;
//    }

    public int getKundennummer() {
        return Kundennummer;
    }

    public void setKundennummer(int Kundennummer) {
        this.Kundennummer = Kundennummer;
    }
    
    
    
}
