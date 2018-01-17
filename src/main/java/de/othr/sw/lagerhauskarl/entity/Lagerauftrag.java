package de.othr.sw.lagerhauskarl.entity;

import de.othr.sw.lagerhauskarl.enums.Auftragstyp;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lagerauftrag implements Serializable{
    
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int lagerauftragsnummer;

  @OneToMany(mappedBy = "einlagerungsauftrag", fetch = FetchType.EAGER)
  private List<Lagerware> einlagerungsWaren;
  
  @OneToMany(mappedBy = "auslagerungsauftrag", fetch = FetchType.EAGER)
  private List<Lagerware> auslagerungsWaren;

  private Timestamp auftragsdatum;

  @Enumerated(EnumType.STRING)
  private Auftragstyp auftragstyp;

  @ManyToOne
  @JoinColumn(name="Auftraggeber")
  private Kunde auftraggeber;

  public int getLagerauftragsnummer() {
    return lagerauftragsnummer;
  }

  public void setLagerauftragsnummer(int lagerauftragsnummer) {
    this.lagerauftragsnummer = lagerauftragsnummer;
  }

  public List<Lagerware> getEinlagerungsWaren() {
    return einlagerungsWaren;
  }

  public void setEinlagerungsWaren(List<Lagerware> EinlagerungsWare) {
    this.einlagerungsWaren = EinlagerungsWare;
  }

  public List<Lagerware> getAuslagerungsWaren() {
    return auslagerungsWaren;
  }

  public void setAuslagerungsWaren(List<Lagerware> AuslagerungsWare) {
    this.auslagerungsWaren = AuslagerungsWare;
  }
  
  public Timestamp getAuftragsdatum() {
    return auftragsdatum;
  }

  public void setAuftragsdatum(Timestamp Auftragsdatum) {
    this.auftragsdatum = Auftragsdatum;
  }

  public Auftragstyp getAuftragstyp() {
    return auftragstyp;
  }

  public void setAuftragstyp(Auftragstyp Auftragstyp) {
    this.auftragstyp = Auftragstyp;
  }

  public Kunde getAuftraggeber() {
    return auftraggeber;
  }

  public void setAuftraggeber(Kunde Auftraggeber) {
    this.auftraggeber = Auftraggeber;
  }
  
  
    
}
