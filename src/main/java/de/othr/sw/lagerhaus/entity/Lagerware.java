package de.othr.sw.lagerhaus.entity;

import de.othr.sw.lagerhaus.validation.ValidNotEmptyString;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Lagerware implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int lagerwarennummer;

  @ValidNotEmptyString
  private String warenbezeichnung;

  @ManyToOne
  @JoinColumn(name = "Einlagerungsauftrag")
  private Lagerauftrag einlagerungsauftrag;

  @ManyToOne
  @JoinColumn(name = "Auslagerungsauftrag")
  private Lagerauftrag auslagerungsauftrag;

  @ManyToOne
  @JoinColumn(name = "Lagerplatz")
  private Lagerplatz lagerplatz;
 
  
  public int getLagerwarennummer() {
    return lagerwarennummer;
  }

  public void setLagerwarennummer(int Lagerwarennummer) {
    this.lagerwarennummer = Lagerwarennummer;
  }

  public String getWarenbezeichnung() {
    return warenbezeichnung;
  }

  public void setWarenbezeichnung(String Warenbezeichnung) {
    this.warenbezeichnung = Warenbezeichnung;
  }

  public Lagerplatz getLagerplatz() {
    return lagerplatz;
  }

  public void setLagerplatz(Lagerplatz Lagerplatz) {
    this.lagerplatz = Lagerplatz;
  }

  public Lagerauftrag getEinlagerungsauftrag() {
    return einlagerungsauftrag;
  }

  public void setEinlagerungsauftrag(Lagerauftrag Einlagerungsauftrag) {
    this.einlagerungsauftrag = Einlagerungsauftrag;
  }

  public Lagerauftrag getAuslagerungsauftrag() {
    return auslagerungsauftrag;
  }

  public void setAuslagerungsauftrag(Lagerauftrag Auslagerungsauftrag) {
    this.auslagerungsauftrag = Auslagerungsauftrag;
  }
  

  @Override
  public String toString() {
    return warenbezeichnung;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 53 * hash + this.lagerwarennummer;
    hash = 53 * hash + Objects.hashCode(this.warenbezeichnung);
    hash = 53 * hash + Objects.hashCode(this.einlagerungsauftrag);
    hash = 53 * hash + Objects.hashCode(this.auslagerungsauftrag);
    hash = 53 * hash + Objects.hashCode(this.lagerplatz);
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
    final Lagerware other = (Lagerware) obj;
    if (this.lagerwarennummer != other.lagerwarennummer) {
      return false;
    }
    if (!Objects.equals(this.warenbezeichnung, other.warenbezeichnung)) {
      return false;
    }
    if (!Objects.equals(this.einlagerungsauftrag, other.einlagerungsauftrag)) {
      return false;
    }
    if (!Objects.equals(this.auslagerungsauftrag, other.auslagerungsauftrag)) {
      return false;
    }
    return Objects.equals(this.lagerplatz, other.lagerplatz);
  }
  
  

}
