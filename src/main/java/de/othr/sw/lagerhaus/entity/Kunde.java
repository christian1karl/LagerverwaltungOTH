package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kunde extends Person implements Serializable{
    
  @Id
  private int kundennummer;

  @OneToMany(mappedBy = "auftraggeber", fetch = FetchType.EAGER)
  private List<Lagerauftrag> lagerauftraege;

  public List<Lagerauftrag> getLagerauftraege() {
    return lagerauftraege;
  }

  public void setLagerauftraege(List<Lagerauftrag> lagerauftraege) {
    this.lagerauftraege = lagerauftraege;
  }
  
  public int getKundennummer() {
    return kundennummer;
  }

  public void setKundennummer(int Kundennummer) {
    this.kundennummer = Kundennummer;
  }
}
