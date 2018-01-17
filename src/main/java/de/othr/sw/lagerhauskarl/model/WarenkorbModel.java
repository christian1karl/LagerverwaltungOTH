package de.othr.sw.lagerhauskarl.model;

import de.othr.sw.lagerhauskarl.entity.Lagerware;
import de.othr.sw.lagerhauskarl.service.LagerService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import javax.inject.Named;

@Named
@SessionScoped
public class WarenkorbModel implements Serializable {

  @Inject
  private LagerService lagerservice;

  @Inject
  private KundenModel kundenModel;

  private List<Lagerware> alleWaren = new ArrayList<>();

  private List<Lagerware> aktuellerWarenkorb = new ArrayList<>();

  private Lagerware aktuelleWare = new Lagerware();

  private double Gesamtkosten;

  private String Status;

  public List<Lagerware> getAlleWaren() {
    return alleWaren;
  }

  public void setAlleWaren(List<Lagerware> alleWaren) {
    this.alleWaren = alleWaren;
  }

  public List<Lagerware> getAktuellerWarenkorb() {
    return aktuellerWarenkorb;
  }

  public Lagerware getAktuelleWare() {
    return aktuelleWare;
  }

  public double getGesamtkosten() {
    return Gesamtkosten;
  }

  public void setGesamtkosten(double Gesamtkosten) {
    this.Gesamtkosten = Gesamtkosten;
  }

  public String getStatus() {
    return Status;
  }

  public void setStatus(String Status) {
    this.Status = Status;
  }

  public LagerService getLagerservice() {
    return lagerservice;
  }

  public void setLagerservice(LagerService lagerservice) {
    this.lagerservice = lagerservice;
  }

  public KundenModel getKundenmodel() {
    return kundenModel;
  }

  public void setKundenmodel(KundenModel kundenModel) {
    this.kundenModel = kundenModel;
  }

  public void wareZumWarenkorbHinzufuegen() {
    this.Status = "";
    Lagerware ware = new Lagerware();
    ware.setAuslagerungsauftrag(aktuelleWare.getAuslagerungsauftrag());
    ware.setEinlagerungsauftrag(aktuelleWare.getEinlagerungsauftrag());
    ware.setLagerplatz(aktuelleWare.getLagerplatz());
    ware.setWarenbezeichnung(aktuelleWare.getWarenbezeichnung());

    if (aktuellerWarenkorb.size() < lagerservice.anzahlFreierLagerwarenplaetze()) {
      aktuellerWarenkorb.add(ware);
      this.aktuelleWare.setWarenbezeichnung("");
      this.Gesamtkosten = lagerservice.kostenFreierLagerwarenplaetze(aktuellerWarenkorb.size());
      Status = "Ware zur Einlagerungsliste hinzugefügt!";
    } else {
      Status = "Keine weiteren Waren können der Liste hinzugefügt werden, da kein weiterer Lagerplatz verfügbar ist!";
    }
  }

  public void wareZumAuslagernWarenkorbHinzufuegen(Lagerware aktuelleWare) {
      aktuellerWarenkorb.add(aktuelleWare);  
  }
  
   public void wareVonAuslagernWarenkorbEntfernen(Lagerware ware) {

//    for (Lagerware warenkorbItem : this.aktuellerWarenkorb) {
//      if (ware.getWarenbezeichnung().equals(warenkorbItem.getWarenbezeichnung())) {
//        aktuellerWarenkorb.remove(warenkorbItem);
//        break;
//      }
//    }

    aktuellerWarenkorb.remove(ware);
    
  }

  public String wareVonWarenkorbEntfernen(Lagerware ware) {

    for (Lagerware warenkorbItem : this.aktuellerWarenkorb) {
      if (ware.getWarenbezeichnung().equals(warenkorbItem.getWarenbezeichnung())) {
        aktuellerWarenkorb.remove(warenkorbItem);
        this.Gesamtkosten = lagerservice.kostenFreierLagerwarenplaetze(aktuellerWarenkorb.size());
        Status = "Ware aus Einlagerungsliste gelöscht!";
        return "ware_aus_warenkorb_gelöscht";
      }
    }
    return "ware_nicht_aus_warenkorb_gelöscht";
  }

  public boolean warenkorbEnthältElement(Lagerware ware) {
    for (Lagerware warenkorbItem : this.aktuellerWarenkorb) {
      if (ware.getWarenbezeichnung().equals(warenkorbItem.getWarenbezeichnung())) {
        return true;
      }
    }
    return false;
  }

  public void sucheAlleWarenDesAngemeldetenKunden() {
    alleWaren = lagerservice.sucheAlleEingelagertenWarenEinesKunden(kundenModel.getAktuellerKunde().getKundennummer());
  }

  public void sucheAlleWaren() {
    alleWaren = lagerservice.sucheAlleEingelagertenWaren();
  }

}
