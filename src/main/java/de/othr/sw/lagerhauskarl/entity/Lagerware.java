package de.othr.sw.lagerhauskarl.entity;

import de.othr.sw.lagerhauskarl.validation.ValidNotEmptyString;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class Lagerware implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int lagerwarennummer;

  @ValidNotEmptyString
  private String warenbezeichnung;

  @ManyToMany(mappedBy = "warenliste", fetch=FetchType.EAGER)
  private List<Lagerauftrag> lagerauftraege;

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

  public List<Lagerauftrag> getLagerauftraege() {
    return lagerauftraege;
  }

  public void setLagerauftraege(List<Lagerauftrag> lagerauftraege) {
    this.lagerauftraege = lagerauftraege;
  }

  @Override
  public String toString() {
    return warenbezeichnung;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + this.lagerwarennummer;
    hash = 97 * hash + Objects.hashCode(this.warenbezeichnung);
    hash = 97 * hash + Objects.hashCode(this.lagerauftraege);
    hash = 97 * hash + Objects.hashCode(this.lagerplatz);
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
    if (!Objects.equals(this.lagerauftraege, other.lagerauftraege)) {
      return false;
    }
    if (!Objects.equals(this.lagerplatz, other.lagerplatz)) {
      return false;
    }
    return true;
  }

  
  

}
