package de.othr.sw.lagerhauskarl.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mitarbeiter extends Person implements Serializable{
    
  @Id
  private int mitarbeiternummer;

  private String taetigkeit;

  private double gehalt;

  public int getMitarbeiternummer() {
      return mitarbeiternummer;
  }

  public void setMitarbeiternummer(int Mitarbeiternummer) {
      this.mitarbeiternummer = Mitarbeiternummer;
  }

  public String getTaetigkeit() {
      return taetigkeit;
  }

  public void setTaetigkeit(String Taetigkeit) {
      this.taetigkeit = Taetigkeit;
  }

  public double getGehalt() {
      return gehalt;
  }

  public void setGehalt(double Gehalt) {
      this.gehalt = Gehalt;
  }    
}
