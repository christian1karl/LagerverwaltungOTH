package de.othr.sw.lagerhaus.model;

import de.othr.sw.lagerhaus.entity.Lagerware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

@Named
@SessionScoped
public class WarenkorbModel implements Serializable 
{

  private List<Lagerware> aktuellerWarenkorb = new ArrayList<>();

  private Lagerware aktuelleWare = new Lagerware();

  public List<Lagerware> getAktuellerWarenkorb() {
    return aktuellerWarenkorb;
  }

  public Lagerware getAktuelleWare() {
    return aktuelleWare;
  }


  public void wareZumWarenkorbHinzufuegen() {
    Lagerware ware = new Lagerware();
    ware.setAuslagerungsauftrag(aktuelleWare.getAuslagerungsauftrag());
    ware.setEinlagerungsauftrag(aktuelleWare.getEinlagerungsauftrag());
    ware.setLagerplatz(aktuelleWare.getLagerplatz());
    ware.setWarenbezeichnung(aktuelleWare.getWarenbezeichnung());

    aktuellerWarenkorb.add(ware);
    this.aktuelleWare.setWarenbezeichnung("");
  }

  public String wareVonWarenkorbEntfernen(Lagerware ware) {
   
    for( Lagerware warenkorbItem : this.aktuellerWarenkorb)
    {
      if(ware.getWarenbezeichnung().equals(warenkorbItem.getWarenbezeichnung()))
      {
        aktuellerWarenkorb.remove(warenkorbItem);
        return"ware_aus_warenkorb_gelöscht";
      }
    }
    return "ware_nicht_aus_warenkorb_gelöscht";
  }

}
