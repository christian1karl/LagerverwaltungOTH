package de.othr.sw.lagerhauskarl.model;

import de.othr.sw.lagerhauskarl.entity.Lagerauftrag;
import de.othr.sw.lagerhauskarl.entity.Lagerplatz;
import de.othr.sw.lagerhauskarl.entity.Lagerware;
import de.othr.sw.lagerhauskarl.enums.Auftragstyp;
import de.othr.sw.lagerhauskarl.enums.Lagerstatus;
import de.othr.sw.lagerhauskarl.service.LagerService;

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
  private KundenModel kundenModel;
  
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
    aktuellerLagerplatz.setLagerpreis(10);
    aktuellerLagerplatz.setLagerwarenkapazitaet(20);
    aktuellerLagerplatz.setLagerwaren(new ArrayList<Lagerware>());
    lagerservice.lagerplatzAnlegen(aktuellerLagerplatz);
  }

  public String auslagerungsAuftragBearbeiten() {
    lagerwarenZumAuftragHinzufuegen();
    aktuellerAuftrag.setAuftragstyp(Auftragstyp.Auslagerung);
    if(aktuellerAuftrag.getAuslagerungsWaren().isEmpty())
      return "auslagerungsliste_leer";
    aktuellerAuftrag.setAuftraggeber(kundenModel.getAktuellerKunde());
    Lagerauftrag bearbeiteterAuftrag = lagerservice.auftragBearbeiten(aktuellerAuftrag);
      List<Lagerware> waren = bearbeiteterAuftrag.getAuslagerungsWaren();
      for (Lagerware ware : waren) {
        if (ware.getLagerplatz() != null) {
          return "ware_nicht_ausgelagert";
        }
      }
      this.warenkorbModel.getAlleWaren().clear();
      this.warenkorbModel.getAktuellerWarenkorb().clear();
      this.aktuellerAuftrag = new Lagerauftrag();
      this.warenkorbModel.setGesamtkosten(0);
      return "auftrag_erfolgreich";
  }
  
  public String einlagerungsAuftragBearbeiten(){
    this.warenkorbModel.setStatus("");
    aktuellerAuftrag.setAuftragstyp(Auftragstyp.Einlagerung);
    lagerwarenZumAuftragHinzufuegen();
    if(aktuellerAuftrag.getEinlagerungsWaren().isEmpty()){
      this.warenkorbModel.setStatus("Einlagerungliste ist leer. Keine Waren wurden eingelagert!");
      return "einlagerungsliste_leer";
    }
    aktuellerAuftrag.setAuftraggeber(kundenModel.getAktuellerKunde());
    Lagerauftrag bearbeiteterAuftrag = lagerservice.auftragBearbeiten(aktuellerAuftrag);
    
    List<Lagerware> waren = bearbeiteterAuftrag.getEinlagerungsWaren();
      for (Lagerware ware : waren) {
        if (ware.getLagerplatz() == null) {
          return "einlagerung_nicht_erfolgreich";
        }
      }
      this.warenkorbModel.getAktuellerWarenkorb().clear();
      this.aktuellerAuftrag = new Lagerauftrag();
      this.warenkorbModel.setGesamtkosten(0);
      return "einlagerung_erfolgreich";
  }

  public void lagerwarenZumAuftragHinzufuegen() {
    if (this.aktuellerAuftrag.getAuftragstyp() == Auftragstyp.Einlagerung) {
      aktuellerAuftrag.setEinlagerungsWaren(this.warenkorbModel.getAktuellerWarenkorb());
    } else {
      aktuellerAuftrag.setAuslagerungsWaren(this.warenkorbModel.getAktuellerWarenkorb());
    }
  }
  
}
