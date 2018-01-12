package de.othr.sw.lagerhaus.entity;

import de.othr.sw.lagerhaus.validation.ValidNotEmptyString;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Lagerware implements Serializable{
    
  @Id
  private int Lagerwarennummer;
  
  @ValidNotEmptyString
  private String Warenbezeichnung;
  
  @Min(1)
  @Max(10000)
  private int hoehe;
  
  @Min(1)
  @Max(10000)
  private int breite;
  
  @Min(1)
  @Max(10000)
  private int laenge;

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

  public int getHoehe() {
    return hoehe;
  }

  public void setHoehe(int hoehe) {
    this.hoehe = hoehe;
  }

  public int getBreite() {
    return breite;
  }

  public void setBreite(int breite) {
    this.breite = breite;
  }

  public int getLaenge() {
    return laenge;
  }

  public void setLaenge(int laenge) {
    this.laenge = laenge;
  }
  

  

}
