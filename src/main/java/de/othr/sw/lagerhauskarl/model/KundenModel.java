package de.othr.sw.lagerhauskarl.model;

import de.othr.sw.lagerhauskarl.entity.Kunde;
import de.othr.sw.lagerhauskarl.service.KundenService;
import de.othr.sw.lagerhauskarl.validation.ValidNotEmptyString;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@SessionScoped
public class KundenModel implements Serializable
{
  @Inject
  private KundenService kundenservice;
  
  private Kunde aktuellerKunde;
  
  private boolean loginFehler;

  @ValidNotEmptyString
  private String benutzername;
  
  @ValidNotEmptyString
  private String passwort;

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

  public Kunde getAktuellerKunde() {
    return aktuellerKunde;
  }

  public void setAktuellerKunde(Kunde aktuellerKunde) {
    this.aktuellerKunde = aktuellerKunde;
  }

  public boolean isLoginFehler() {
    return loginFehler;
  }

  public void setLoginFehler(boolean loginFehler) {
    this.loginFehler = loginFehler;
  }
    
  public boolean hatAktuellenKunden()  {
    return !(aktuellerKunde == null);
  }
    
  public String einloggen() {
    this.aktuellerKunde = kundenservice.einloggen(benutzername, passwort);
    this.benutzername = "";
    this.passwort = "";
    this.loginFehler = false;
    if(this.aktuellerKunde == null){
       loginFehler = true;
        return "login_fehler";}
    else
        return "login_ok";
  }

  public String ausloggen() {
      this.benutzername = "";
      this.passwort = "";
      this.aktuellerKunde = null;
      loginFehler = false;
      return "logout_ok";
  }



}
