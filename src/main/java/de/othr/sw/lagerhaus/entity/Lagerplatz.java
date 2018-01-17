package de.othr.sw.lagerhaus.entity;

import de.othr.sw.lagerhaus.enums.Lagerstatus;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Lagerplatz implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int lagerplatznummer;

  private double lagerpreis;

  @Enumerated(EnumType.STRING)
  private Lagerstatus lagerstatus;

  @OneToMany(mappedBy = "lagerplatz",  fetch=FetchType.EAGER)
  private List<Lagerware> lagerwaren;

  @Min(1)
  @Max(50)
  private int lagerwarenkapazitaet;

  public int getLagerplatznummer() {
    return lagerplatznummer;
  }

  public void setLagerplatznummer(int Lagerplatznummer) {
    this.lagerplatznummer = Lagerplatznummer;
  }

  public double getLagerpreis() {
    return lagerpreis;
  }

  public void setLagerpreis(double Lagerpreis) {
    this.lagerpreis = Lagerpreis;
  }

  public Lagerstatus getLagerstatus() {
    return lagerstatus;
  }

  public void setLagerstatus(Lagerstatus Lagerstatus) {
    this.lagerstatus = Lagerstatus;
  }

  public List<Lagerware> getLagerwaren() {
    return lagerwaren;
  }

  public void setLagerwaren(List<Lagerware> Lagerwaren) {
    this.lagerwaren = Lagerwaren;
  }

  public int getLagerwarenkapazitaet() {
    return lagerwarenkapazitaet;
  }

  public void setLagerwarenkapazitaet(int Lagerwarenkapazitet) {
    this.lagerwarenkapazitaet = Lagerwarenkapazitet;
  }
  
  public int getAnzahlFreierStellplaetze()
  {
    return this.getLagerwarenkapazitaet()-this.getLagerwaren().size();
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 59 * hash + this.lagerplatznummer;
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
    final Lagerplatz other = (Lagerplatz) obj;
    if (this.lagerplatznummer != other.lagerplatznummer) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Lagerplatz{" + "Lagernummer=" + lagerplatznummer + ", Lagerpreis=" + lagerpreis + ", Lagerstatus=" + lagerstatus + ", Lagerwaren=" + lagerwaren + '}';
  }

}
