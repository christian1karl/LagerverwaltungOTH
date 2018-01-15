package de.othr.sw.lagerhaus.model;

import de.othr.sw.lagerhaus.entity.Lagerauftrag;
import de.othr.sw.lagerhaus.entity.Lagerplatz;
import de.othr.sw.lagerhaus.entity.Lagerware;
import de.othr.sw.lagerhaus.enums.Auftragstyp;
import de.othr.sw.lagerhaus.enums.Lagerstatus;
import de.othr.sw.lagerhaus.service.LagerService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AuftragModel implements Serializable {

  @Inject
  private LagerService lagerservice;
  
  @Inject
  private LoginModel loginModel;
  
  @Inject
  private WarenkorbModel warenkorbModel;
  
  private Lagerauftrag aktuellerAuftrag = new Lagerauftrag();

  private Lagerplatz aktuellerLagerplatz = new Lagerplatz();

  public Lagerplatz getLagerplatz() {
    return aktuellerLagerplatz;
  }

  public void setLagerplatz(Lagerplatz lagerplatz) {
    this.aktuellerLagerplatz = lagerplatz;
  }

  public Lagerauftrag getAktuellerAuftrag() {
    return aktuellerAuftrag;
  }

  public void setAktuellerAuftrag(Lagerauftrag aktuellerAuftrag) {
    this.aktuellerAuftrag = aktuellerAuftrag;
  }

  public Auftragstyp[] getAuftragstyp() {
    return Auftragstyp.values();
  }

  public void lagerplatzAnlegen() {
    aktuellerLagerplatz.setLagerstatus(Lagerstatus.Frei);
    aktuellerLagerplatz.setLagerwaren(new ArrayList<Lagerware>());
    lagerservice.lagerplatzAnlegen(aktuellerLagerplatz);
  }

  public String auslagerungsAuftragBearbeiten() {
    lagerwarenZumAuftragHinzufuegen();
    aktuellerAuftrag.setAuftraggeber(loginModel.getAktuellerKunde());
    Lagerauftrag bearbeiteterAuftrag = lagerservice.auftragBearbeiten(aktuellerAuftrag);
      List<Lagerware> waren = bearbeiteterAuftrag.getAuslagerungsWaren();
      for (Lagerware ware : waren) {
        if (ware.getLagerplatz() != null) {
          return "ware_nicht_ausgelagert";
        }
      }
      return "auftrag_erfolgreich";
  }
  
  public String einlagerungsAuftragBearbeiten()
  {
    lagerplatzAnlegen();    
    aktuellerAuftrag.setAuftragstyp(Auftragstyp.Einlagerung);
    lagerwarenZumAuftragHinzufuegen();
    aktuellerAuftrag.setAuftraggeber(loginModel.getAktuellerKunde());
    Lagerauftrag bearbeiteterAuftrag = lagerservice.auftragBearbeiten(aktuellerAuftrag);
    
    List<Lagerware> waren = bearbeiteterAuftrag.getEinlagerungsWaren();
      for (Lagerware ware : waren) {
        if (ware.getLagerplatz() == null) {
          return "kein_freier_lagerplatz";
        }
      }
      this.warenkorbModel.getAktuellerWarenkorb().clear();
      this.aktuellerAuftrag = new Lagerauftrag();
      return "auftrag_erfolgreich";
  }

  public void lagerwarenZumAuftragHinzufuegen() {
    if (this.aktuellerAuftrag.getAuftragstyp() == Auftragstyp.Einlagerung) {
      aktuellerAuftrag.setEinlagerungsWaren(this.warenkorbModel.getAktuellerWarenkorb());
    } else {
      aktuellerAuftrag.setAuslagerungsWaren(this.warenkorbModel.getAktuellerWarenkorb());
    }
  }
  
}
