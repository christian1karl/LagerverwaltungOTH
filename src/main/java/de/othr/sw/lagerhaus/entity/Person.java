package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable {
    
    @Id 
    private int PersonenNummer;
    private String Nachname;
    private String Vorname;
    //private Date Geburtsdatum;
    //@OneToOne
    private Adresse Adresse;
    private String Emailadresse;
    //private Date Erstelldatum;
    private String Benutzername;
    private String Passwort;

    public Person(){
        
    }

    public Person(String Nachname, String Vorname, Date Geburtsdatum, Adresse Adresse, String Emailadresse, Date Erstelldatum, String Benutzername, String Passwort) {
        this.Nachname = Nachname;
        this.Vorname = Vorname;
       // this.Geburtsdatum = Geburtsdatum;
        this.Adresse = Adresse;
        this.Emailadresse = Emailadresse;
       // this.Erstelldatum = Erstelldatum;
        this.Benutzername = Benutzername;
        this.Passwort = Passwort;
    }
    

    
    public int getPersonenNummer() {
        return PersonenNummer;
    }

    public void setPersonenNummer(int PersonenNummer) {
        this.PersonenNummer = PersonenNummer;
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

//    public Date getGeburtsdatum() {
//        return Geburtsdatum;
//    }
//
//    public void setGeburtsdatum(Date Geburtsdatum) {
//        this.Geburtsdatum = Geburtsdatum;
//    }

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
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this.PersonenNummer ^ (this.PersonenNummer >>> 32));
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
        if (this.PersonenNummer != other.PersonenNummer) {
            return false;
        }
        return true;
    }
    
    
    
}
