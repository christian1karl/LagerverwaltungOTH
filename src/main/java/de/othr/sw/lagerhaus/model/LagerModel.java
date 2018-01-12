package de.othr.sw.lagerhaus.model;


import de.othr.sw.lagerhaus.entity.Lagerauftrag;
import de.othr.sw.lagerhaus.entity.Lagerware;
import de.othr.sw.lagerhaus.enums.Auftragstyp;
import de.othr.sw.lagerhaus.service.LagerService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;


@Named
@SessionScoped
public class LagerModel implements Serializable
{  
  @Inject
  private LagerService lagerservice;
  
  private Lagerauftrag aktuellerAuftrag = new Lagerauftrag();
  
  private Lagerware aktuelleWare = new Lagerware();
 
  private List<Lagerware> aktuelleWaren = new ArrayList<Lagerware>();

  public Lagerauftrag getAktuellerAuftrag() {
    return aktuellerAuftrag;
  }

  public void setAktuellerAuftrag(Lagerauftrag aktuellerAuftrag) {
    this.aktuellerAuftrag = aktuellerAuftrag;
  }

  public Lagerware getAktuelleWare() {
    return aktuelleWare;
  }

  public void setAktuelleWare(Lagerware aktuelleWare) {
    this.aktuelleWare = aktuelleWare;
  }

  public Auftragstyp[] getAuftragstyp()
  {
    return Auftragstyp.values();
  }
  
  
  public String wareAnlegen() {
    this.aktuelleWare = lagerservice.wareAnlegen(aktuelleWare);
    if(this.aktuelleWare == null){
       return "waren_fehler";}
    else
    {
      auftragBearbeiten();
        return "waren_ok";
    }
  }
  
  @Transactional
  public String auftragBearbeiten()
  {
    
    if(aktuellerAuftrag.getAuftragstyp() == Auftragstyp.Einlagerung)
    {
      try{
        aktuelleWaren.add(aktuelleWare);
        aktuelleWaren = lagerservice.warenAnlegen(aktuelleWaren);
        aktuellerAuftrag.setEinlagerungsWare(aktuelleWaren);
        lagerservice.wareEinlagern(aktuellerAuftrag);
        return "ware_eingelagern_ok";
      }
      catch (Exception ex)
      {
        return ex.getMessage();
      } 
    }
    else 
    {
      try{
        lagerservice.wareAuslagern(aktuellerAuftrag);
        return "ware_auslagern_ok";
      }
      catch (Exception ex)
      {
        return "ware_auslagern_fehlgeschlagen";
      }
    }
  }


}
