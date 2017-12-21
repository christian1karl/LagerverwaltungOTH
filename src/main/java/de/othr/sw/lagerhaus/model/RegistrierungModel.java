package de.othr.sw.lagerhaus.model;

import de.othr.sw.lagerhaus.entity.Adresse;
import de.othr.sw.lagerhaus.entity.Kunde;
import de.othr.sw.lagerhaus.service.KundenService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class RegistrierungModel implements Serializable{
    
    @Inject
    private KundenService kundenService;
    
    private String vorname;
    private String nachname;
    private String ort;
    private String land;
    private int postleitzahl;
    private String strasse;
    private int hausnummer;
    private String hausnummerzusatz;
    private String benutzername;
    private String emailadresse;
    private String passwort;
    private String passwort2;
    

    public void pruefeRegistrierung()
    {
        Kunde neuerKunde = new Kunde();
        Adresse neueAdresse = new Adresse();
        
        neuerKunde.setVorname(vorname);
        neuerKunde.setNachname(nachname);
        neuerKunde.setEmailadresse(emailadresse);
        neuerKunde.setBenutzername(benutzername);
        neuerKunde.setPasswort(passwort);
        
        neueAdresse.setHausnummer(hausnummer);
        neueAdresse.setHausnummerzusatz(hausnummerzusatz);
        neueAdresse.setLand(land);
        neueAdresse.setOrt(ort);
        neueAdresse.setPostleitzahl(postleitzahl);
        neueAdresse.setStrasse(strasse);
        
        neuerKunde.setAdresse(neueAdresse);
        
        
        kundenService.KundeAnlegen(neuerKunde);


    }
    
    
    public boolean pruefePasswort()
    {
        return true;
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

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(int postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getHausnummerzusatz() {
        return hausnummerzusatz;
    }

    public void setHausnummerzusatz(String hausnummerzusatz) {
        this.hausnummerzusatz = hausnummerzusatz;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getEmailadresse() {
        return emailadresse;
    }

    public void setEmailadresse(String emailadresse) {
        this.emailadresse = emailadresse;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
    
      public String getPasswort2() {
        return passwort2;
    }

    public void setPasswort2(String passwort) {
        this.passwort2 = passwort;
    }
    
    
    
}
