package de.othr.sw.lagerhauskarl.model;

import de.othr.sw.lagerhauskarl.entity.Kunde;
import de.othr.sw.lagerhauskarl.service.KundenService;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class RegistierungModel implements Serializable {

  @Inject
  private KundenService kundenservice;

  private Kunde kunde = new Kunde();

  public Kunde getKunde() {
    return this.kunde;
  }

  public void doOk() {
    kundenservice.registrieren(kunde);
  }

}
