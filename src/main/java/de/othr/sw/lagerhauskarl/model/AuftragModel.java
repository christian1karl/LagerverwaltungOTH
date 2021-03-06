package de.othr.sw.lagerhauskarl.model;

import de.othr.sw.lagerhauskarl.entity.Lagerauftrag;
import de.othr.sw.lagerhauskarl.entity.Lagerplatz;
import de.othr.sw.lagerhauskarl.entity.Lagerware;
import de.othr.sw.lagerhauskarl.enums.Auftragstyp;
import de.othr.sw.lagerhauskarl.enums.Lagerstatus;
import de.othr.sw.lagerhauskarl.service.LagerService;
import de.othr.sw.lagerhauskarl.service.TransaktionServiceLagerhaus;

import java.io.Serializable;
import java.sql.Timestamp;
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
  private TransaktionServiceLagerhaus transaktionService; 
  
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
    if (aktuellerAuftrag.getWarenliste().isEmpty()) {
      return "auslagerungsliste_leer";
    }
    aktuellerAuftrag.setAuftraggeber(kundenModel.getAktuellerKunde());
    aktuellerAuftrag.setAuftragsdatum(new Timestamp(System.currentTimeMillis()));
    Lagerauftrag bearbeiteterAuftrag = lagerservice.auftragBearbeiten(aktuellerAuftrag);
    List<Lagerware> waren = bearbeiteterAuftrag.getWarenliste();
    for (Lagerware ware : waren) {
      if (ware.getLagerplatz() != null) {
        return "auslagerung_nicht_erfolgreich";
      }
    }
    
    this.warenkorbModel.getAlleWaren().clear();
    this.warenkorbModel.getAktuellerWarenkorb().clear();
    this.aktuellerAuftrag = new Lagerauftrag();
    this.warenkorbModel.setGesamtkosten(0);
    return "auslagerung_erfolgreich";
  }

  public String einlagerungsAuftragBearbeiten() {
    aktuellerAuftrag.setBezahlt(false);
    this.warenkorbModel.setStatus("");
    aktuellerAuftrag.setAuftragstyp(Auftragstyp.Einlagerung);
    lagerwarenZumAuftragHinzufuegen();
    if (aktuellerAuftrag.getWarenliste().isEmpty()) {
      this.warenkorbModel.setStatus("Einlagerungliste ist leer. Keine Waren wurden eingelagert!");
      return "einlagerungsliste_leer";
    }
    aktuellerAuftrag.setAuftraggeber(kundenModel.getAktuellerKunde());
    aktuellerAuftrag.setGesamtkosten(warenkorbModel.getGesamtkosten());
    aktuellerAuftrag.setAuftragsdatum(new Timestamp(System.currentTimeMillis()));
    
    aktuellerAuftrag = transaktionService.einlagerungBezahlen(aktuellerAuftrag);

    if(aktuellerAuftrag.isBezahlt() == false){
      return"fehler_beim_bezahlen";
    }
    
    Lagerauftrag bearbeiteterAuftrag = lagerservice.auftragBearbeiten(aktuellerAuftrag);

    List<Lagerware> waren = bearbeiteterAuftrag.getWarenliste();
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

    aktuellerAuftrag.setWarenliste(this.warenkorbModel.getAktuellerWarenkorb());

  }

}
