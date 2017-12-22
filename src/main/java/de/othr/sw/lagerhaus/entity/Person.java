package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;



@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable {
    
    private String Nachname;
    private String Vorname;
    
    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    private Date Geburtsdatum;
    
    @Valid
    private Adresse Adresse = new Adresse();
    
    @Pattern(regexp = "[^@]+@[^@]+\\.[^@]+")
    private String Emailadresse;
    
    //private Date Erstelldatum;
    private String Benutzername;
    private String Passwort;

    public Person(){
    }

    public Person(String Nachname, String Vorname, Date Geburtsdatum, Adresse Adresse, String Emailadresse, Date Erstelldatum, String Benutzername, String Passwort) {
        this.Nachname = Nachname;
        this.Vorname = Vorname;
        this.Geburtsdatum = Geburtsdatum;
        this.Adresse = Adresse;
        this.Emailadresse = Emailadresse;
       // this.Erstelldatum = Erstelldatum;
        this.Benutzername = Benutzername;
        this.Passwort = Passwort;
    }
    

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String Nachname) {
        this.Nachname = Nachname;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String Vorname) {
        this.Vorname = Vorname;
    }

    public Date getGeburtsdatum() {
        return Geburtsdatum;
    }

    public void setGeburtsdatum(Date Geburtsdatum) {
        this.Geburtsdatum = Geburtsdatum;
    }

    public Adresse getAdresse() {
        return Adresse;
    }

    public void setAdresse(Adresse Adresse) {
        this.Adresse = Adresse;
    }

    public String getEmailadresse() {
        return Emailadresse;
    }

    public void setEmailadresse(String Emailadresse) {
        this.Emailadresse = Emailadresse;
    }

//    public Date getErstelldatum() {
//        return Erstelldatum;
//    }

    /* Frage: Sollte es so eine Funktion geben?
    public void setErstelldatum(Date Erstelldatum) {
        this.Erstelldatum = Erstelldatum;
    }
    */
    
    public String getBenutzername() {
        return Benutzername;
    }

    public void setBenutzername(String Benutzername) {
        this.Benutzername = Benutzername;
    }

    public String getPasswort() {
        return Passwort;
    }

    public void setPasswort(String Passwort) {
        this.Passwort = Passwort;
    }

    @Override
    public String toString() {
        return "Person{" + "Nachname=" + Nachname + ", Vorname=" + Vorname + ", Benutzername=" + Benutzername + '}';
    }
  
    
    
}
