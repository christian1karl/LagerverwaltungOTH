package de.othr.sw.lagerhauskarl.entity;

import de.othr.sw.lagerhauskarl.validation.ValidPlz;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import de.othr.sw.lagerhauskarl.validation.ValidNotEmptyString;

@Embeddable
@Access(AccessType.FIELD)
public class Adresse implements Serializable
{
    
  @ValidNotEmptyString
  private String strasse;

  @ValidPlz
  private String plz;

  @ValidNotEmptyString
  private String ort;
  
  @ValidNotEmptyString
  private String hausnummer;
  
   public String getStrasse()
  {
    return this.strasse;
  }

  public void setStrasse(String strasse)
  {
    this.strasse = strasse;
  }

  public String getPlz()
  {
    return this.plz;
  }

  public void setPlz(String plz)
  {
    this.plz = plz;
  }

  public String getOrt()
  {
    return this.ort;
  }

  public void setOrt(String ort)
  {
    this.ort = ort;
  }


  public String getHausnummer() {
      return hausnummer;
  }

  public void setHausnummer(String hausnummer) {
      this.hausnummer = hausnummer;
  }


  @Override
  public String toString() {
    return "Adresse [strasse=" + this.strasse + ", plz=" + this.plz + ", ort=" + this.ort + "]";
  }

  @Override
  public int hashCode() {
      int hash = 7;
      hash = 13 * hash + Objects.hashCode(this.strasse);
      hash = 13 * hash + Objects.hashCode(this.plz);
      hash = 13 * hash + Objects.hashCode(this.ort);
      hash = 13 * hash + Objects.hashCode(this.hausnummer);
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
      final Adresse other = (Adresse) obj;
      if (!Objects.equals(this.strasse, other.strasse)) {
          return false;
      }
      if (!Objects.equals(this.plz, other.plz)) {
          return false;
      }
      if (!Objects.equals(this.ort, other.ort)) {
          return false;
      }
      if (!Objects.equals(this.hausnummer, other.hausnummer)) {
          return false;
      }
      return true;
  }

  
}
