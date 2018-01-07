package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Lagerware implements Serializable{
    
  @Id
  private int Lagerwarennummer;
  
  private String Warenbezeichnung;

  @ManyToOne
  @JoinColumn(name="Einlagerungsauftrag")
  private Lagerauftrag Einlagerungsauftrag;

  @ManyToOne
  @JoinColumn(name="Auslagerungsauftrag")
  private Lagerauftrag Auslagerungsauftrag;

  @ManyToOne
  @JoinColumn(name="Lagerplatz")
  private Lagerplatz Lagerplatz;

  public int getLagerwarennummer() {
      return Lagerwarennummer;
  }

  public void setLagerwarennummer(int Lagerwarennummer) {
      this.Lagerwarennummer = Lagerwarennummer;
  }

  public String getWarenbezeichnung() {
      return Warenbezeichnung;
  }

  public void setWarenbezeichnung(String Warenbezeichnung) {
      this.Warenbezeichnung = Warenbezeichnung;
  }

  public Lagerplatz getLagerplatz() {
      return Lagerplatz;
  }

  public void setLagerplatz(Lagerplatz Lagerplatz) {
      this.Lagerplatz = Lagerplatz;
  }


}
