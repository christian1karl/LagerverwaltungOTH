package de.othr.sw.lagerhaus.entity;

import de.othr.sw.lagerhaus.enums.Auftragstyp;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lagerauftrag implements Serializable{
    
  @Id
  private int lagerauftragsnummer;

  @OneToMany(mappedBy = "Einlagerungsauftrag")
  private List<Lagerware> Waren;

  private Date Auftragsdatum;

  private Auftragstyp Auftragstyp;
    
  @ManyToOne
  private Kunde Auftraggeber;

  public int getLagerauftragsnummer() {
    return lagerauftragsnummer;
  }

  public void setLagerauftragsnummer(int lagerauftragsnummer) {
    this.lagerauftragsnummer = lagerauftragsnummer;
  }

  public List<Lagerware> getWaren() {
    return Waren;
  }

  public void setWaren(List<Lagerware> Waren) {
    this.Waren = Waren;
  }

  public Date getAuftragsdatum() {
    return Auftragsdatum;
  }

  public void setAuftragsdatum(Date Auftragsdatum) {
    this.Auftragsdatum = Auftragsdatum;
  }

  public Auftragstyp getAuftragstyp() {
    return Auftragstyp;
  }

  public void setAuftragstyp(Auftragstyp Auftragstyp) {
    this.Auftragstyp = Auftragstyp;
  }

  public Kunde getAuftraggeber() {
    return Auftraggeber;
  }

  public void setAuftraggeber(Kunde Auftraggeber) {
    this.Auftraggeber = Auftraggeber;
  }
  
  
    
}
