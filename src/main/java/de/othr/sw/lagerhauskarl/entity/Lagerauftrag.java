package de.othr.sw.lagerhauskarl.entity;

import de.othr.sw.lagerhauskarl.enums.Auftragstyp;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Lagerauftrag implements Serializable{
    
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int lagerauftragsnummer;

  @ManyToMany
  private List<Lagerware> warenliste;

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

  public List<Lagerware> getWarenliste() {
    return warenliste;
  }

  public void setWarenliste(List<Lagerware> warenliste) {
    this.warenliste = warenliste;
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
