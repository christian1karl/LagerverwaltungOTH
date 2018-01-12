package de.othr.sw.lagerhaus.entity;

import de.othr.sw.lagerhaus.enums.Auftragstyp;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


@Entity
public class Lagerauftrag implements Serializable{
    
  @Id
  private int lagerauftragsnummer;

  @OneToMany(mappedBy = "Einlagerungsauftrag")
  private List<Lagerware> EinlagerungsWare;
  
  @OneToMany(mappedBy = "Auslagerungsauftrag")
  private List<Lagerware> AuslagerungsWare;

  @Temporal(javax.persistence.TemporalType.DATE)
  private Date Auftragsdatum;

  @Enumerated(EnumType.STRING)
  private Auftragstyp Auftragstyp;

  @ManyToOne
  @JoinColumn(name="Auftraggeber")
  private Kunde Auftraggeber;

  public int getLagerauftragsnummer() {
    return lagerauftragsnummer;
  }

  public void setLagerauftragsnummer(int lagerauftragsnummer) {
    this.lagerauftragsnummer = lagerauftragsnummer;
  }

  public List<Lagerware> getEinlagerungsWare() {
    return EinlagerungsWare;
  }

  public void setEinlagerungsWare(List<Lagerware> EinlagerungsWare) {
    this.EinlagerungsWare = EinlagerungsWare;
  }

  public List<Lagerware> getAuslagerungsWare() {
    return AuslagerungsWare;
  }

  public void setAuslagerungsWare(List<Lagerware> AuslagerungsWare) {
    this.AuslagerungsWare = AuslagerungsWare;
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
