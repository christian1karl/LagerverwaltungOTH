package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable
{
    
    @Size(min = 1)
    private String vorname;
    
    @Size(min = 1)
    private String nachname;

    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    private Date geburtsDatum;

    @Valid
    private Adresse adresse = new Adresse();

    @Pattern(regexp = "[^@]+@[^@]+\\.[^@]+")
    private String email;

    @Size(min = 1)
    private String benutzername;
    
    @Size(min = 1)
    private String passwort;


    
    public Date getGeburtsDatum()
    {
      return this.geburtsDatum;
    }

    public void setGeburtsDatum(Date gebDate)
    {
      this.geburtsDatum = gebDate;
    }

    public Adresse getAdresse()
    {
      return this.adresse;
    }

    public String getEmail()
    {
      return this.email;
    }

    public void setEmail(String email)
    {
      this.email = email;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    @Override
    public String toString() {
        return "Person{" + "vorname=" + vorname + ", nachname=" + nachname + ", benutzername=" + benutzername + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.vorname);
        hash = 97 * hash + Objects.hashCode(this.nachname);
        hash = 97 * hash + Objects.hashCode(this.benutzername);
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.vorname, other.vorname)) {
            return false;
        }
        if (!Objects.equals(this.nachname, other.nachname)) {
            return false;
        }
        if (!Objects.equals(this.benutzername, other.benutzername)) {
            return false;
        }
        return true;
    }

  
}
