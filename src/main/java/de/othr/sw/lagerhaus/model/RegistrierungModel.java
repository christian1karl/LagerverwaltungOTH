package de.othr.sw.lagerhaus.model;

import de.othr.sw.lagerhaus.entity.Kunde;
import de.othr.sw.lagerhaus.entity.Person;
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

    private Kunde kunde = new Kunde();

    public Person getKunde() {
        return kunde;
    }
    
    public void pruefeRegistrierung()
    {
      //kundenService.KundeAnlegen(kunde);
    }

}
