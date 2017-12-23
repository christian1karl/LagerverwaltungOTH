package de.othr.sw.lagerhaus.model;

import de.othr.sw.lagerhaus.service.KundenService;
import de.othr.sw.lagerhaus.validation.ValidNotEmptyString;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@SessionScoped
public class LoginModel implements Serializable
{
  @Inject
  private KundenService kundenservice;

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

  public void doOk()
  {
     //
  }



}
