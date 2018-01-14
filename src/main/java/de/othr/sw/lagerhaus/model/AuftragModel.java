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

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class AuftragModel implements Serializable {

  @Inject
  private LagerService lagerservice;

  private Lagerauftrag aktuellerAuftrag = new Lagerauftrag();

  private Lagerplatz aktuellerLagerplatz = new Lagerplatz();

  private List<Lagerware> aktuelleWaren = new ArrayList<>();

  private String warenBezeichnung;

  public String getWarenBezeichnung() {
    return warenBezeichnung;
  }

  public void setWarenBezeichnung(String warenBezeichnung) {
    this.warenBezeichnung = warenBezeichnung;
  }

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

  public List<Lagerware> getAktuelleWaren() {
    return aktuelleWaren;
  }

  public void setAktuelleWaren(List<Lagerware> aktuelleWaren) {
    this.aktuelleWaren = aktuelleWaren;
  }

  public Auftragstyp[] getAuftragstyp() {
    return Auftragstyp.values();
  }

  public void lagerplatzAnlegen() {
    aktuellerLagerplatz.setLagerstatus(Lagerstatus.Frei);
    aktuellerLagerplatz.setLagerwaren(new ArrayList<Lagerware>());
    lagerservice.lagerplatzAnlegen(aktuellerLagerplatz);
  }

  public String auftragBearbeiten() {
    lagerwarenZumAuftragHinzufuegen();
    Lagerauftrag bearbeiteterAuftrag = lagerservice.auftragBearbeiten(aktuellerAuftrag);

    if (aktuellerAuftrag.getAuftragstyp() == Auftragstyp.Einlagerung) {
      List<Lagerware> waren = bearbeiteterAuftrag.getEinlagerungsWaren();
      for (Lagerware ware : waren) {
        if (ware.getLagerplatz() == null) {
          return "kein_freier_lagerplatz";
        }
      }
      return "auftrag_erfolgreich";
    } else {
      List<Lagerware> waren = bearbeiteterAuftrag.getAuslagerungsWaren();
      for (Lagerware ware : waren) {
        if (ware.getLagerplatz() != null) {
          return "ware_nicht_ausgelagert";
        }
      }
      return "auftrag_erfolgreich";
    }

  }

  public void lagerwarenZumAuftragHinzufuegen() {
    if (this.aktuellerAuftrag.getAuftragstyp() == Auftragstyp.Einlagerung) {
      aktuellerAuftrag.setEinlagerungsWaren(aktuelleWaren);
    } else {
      aktuellerAuftrag.setAuslagerungsWaren(aktuelleWaren);
    }
  }

  public String wareZurListeHinzufuegen() {
    Lagerware ware = new Lagerware();
    ware.setWarenbezeichnung(warenBezeichnung);
    aktuelleWaren.add(ware);
    return "ware_zur_Liste_hinzugefuegt";
  }

}
