package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person implements Serializable {
    
    @Id 
    private int _personenNummer;
    private String _nachname;
    private String _vorname;
    //private Date _geburtsdatum;
    private Adresse _adresse;
    private String _emailadresse;
    //private Date _erstelldatum;
    private String _benutzername;
    private String _passwort;

    public Person(){
        
    }

    public Person(String _nachname, String _vorname, Date _geburtsdatum, Adresse _adresse, String _emailadresse, Date _erstelldatum, String _benutzername, String _passwort) {
        this._nachname = _nachname;
        this._vorname = _vorname;
       // this._geburtsdatum = _geburtsdatum;
        this._adresse = _adresse;
        this._emailadresse = _emailadresse;
       // this._erstelldatum = _erstelldatum;
        this._benutzername = _benutzername;
        this._passwort = _passwort;
    }
    

    
    public int getPersonenNummer() {
        return _personenNummer;
    }

    public void setPersonenNummer(int PersonenNummer) {
        this._personenNummer = PersonenNummer;
    }

    public String getNachname() {
        return _nachname;
    }

    public void setNachname(String Nachname) {
        this._nachname = Nachname;
    }

    public String getVorname() {
        return _vorname;
    }

    public void setVorname(String Vorname) {
        this._vorname = Vorname;
    }

//    public Date getGeburtsdatum() {
//        return _geburtsdatum;
//    }
//
//    public void setGeburtsdatum(Date Geburtsdatum) {
//        this._geburtsdatum = Geburtsdatum;
//    }

    public Adresse getAdresse() {
        return _adresse;
    }

    public void setAdresse(Adresse Adresse) {
        this._adresse = Adresse;
    }

    public String getEmailadresse() {
        return _emailadresse;
    }

    public void setEmailadresse(String Emailadresse) {
        this._emailadresse = Emailadresse;
    }

//    public Date getErstelldatum() {
//        return _erstelldatum;
//    }

    /* Frage: Sollte es so eine Funktion geben?
    public void setErstelldatum(Date Erstelldatum) {
        this._erstelldatum = Erstelldatum;
    }
    */
    
    public String getBenutzername() {
        return _benutzername;
    }

    public void setBenutzername(String Benutzername) {
        this._benutzername = Benutzername;
    }

    public String getPasswort() {
        return _passwort;
    }

    public void setPasswort(String Passwort) {
        this._passwort = Passwort;
    }

    @Override
    public String toString() {
        return "Person{" + "Nachname=" + _nachname + ", Vorname=" + _vorname + ", Benutzername=" + _benutzername + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this._personenNummer ^ (this._personenNummer >>> 32));
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
        if (this._personenNummer != other._personenNummer) {
            return false;
        }
        return true;
    }
    
    
    
}
