package de.othr.sw.lagerhaus.service;

import de.othr.sw.lagerhaus.entity.Lagerauftrag;
import de.othr.sw.lagerhaus.entity.Lagerplatz;
import de.othr.sw.lagerhaus.entity.Lagerware;
import de.othr.sw.lagerhaus.enums.Auftragstyp;
import de.othr.sw.lagerhaus.enums.Lagerstatus;
import java.sql.Timestamp;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@RequestScoped
public class LagerService {

  @PersistenceContext
  private EntityManager em;

  @Transactional
  public Lagerplatz lagerplatzAnlegen(Lagerplatz lagerplatz) {
    em.persist(lagerplatz);
    return lagerplatz;
  }

  @Transactional
  public Lagerauftrag auftragBearbeiten(Lagerauftrag lagerauftrag) {

    if (lagerauftrag.getAuftragstyp() == Auftragstyp.Einlagerung) {

      List<Lagerware> waren = lagerauftrag.getEinlagerungsWaren();

      for (int i = 0; i < waren.size(); i++) {
        Lagerware eingelagerteWare = wareEinlagern(waren.get(i));
        if (eingelagerteWare == null) {
          return lagerauftrag;
        }
        eingelagerteWare.setEinlagerungsauftrag(lagerauftrag);
        em.merge(eingelagerteWare);
        lagerauftrag.setAuftragsdatum(new Timestamp(System.currentTimeMillis()));
        em.persist(lagerauftrag);
      }
      em.merge(lagerauftrag);

    } else {
      List<Lagerware> waren = lagerauftrag.getAuslagerungsWaren();
      for (Lagerware ware : waren) {
        Lagerware neueWare = wareAuslagern(ware);
        neueWare.setAuslagerungsauftrag(lagerauftrag);

        //Collections.replaceAll(waren, ware, neueWare);
        lagerauftrag.setAuftragsdatum(new Timestamp(System.currentTimeMillis()));
        em.persist(lagerauftrag);
        em.merge(neueWare);
      }

      em.merge(lagerauftrag);
    }
    return lagerauftrag;
  }

  @Transactional
  public Lagerware wareEinlagern(Lagerware ware) {
    Lagerplatz lagerplatz = findeFreienLagerplatz();
    if (lagerplatz == null) {
      return null;
    }

    lagerplatz.getLagerwaren().add(ware);

    if (lagerplatz.getLagerwarenkapazitaet() == lagerplatz.getLagerwaren().size()) {
      lagerplatz.setLagerstatus(Lagerstatus.Belegt);
    }

    ware.setLagerplatz(lagerplatz);

    em.persist(ware);
    em.merge(lagerplatz);

    return ware;
  }

  @Transactional
  public Lagerware wareAuslagern(Lagerware ware) {
    Lagerware wareInDB = findeLagerware(ware);
    Lagerplatz lagerplatz = findeLagerplatz(wareInDB.getLagerplatz());
    lagerplatz.getLagerwaren().remove(wareInDB);
    
    wareInDB.setLagerplatz(null);

    if (lagerplatz.getLagerwaren().size() < lagerplatz.getLagerwarenkapazitaet()) {
      lagerplatz.setLagerstatus(Lagerstatus.Frei);
    }
    
    
    em.merge(lagerplatz);    
    
    return wareInDB;
  }
  
  
  public Lagerware findeLagerware(Lagerware ware) {
    Query query = em.createQuery("SELECT c FROM Lagerware c WHERE c.warenbezeichnung like:ware")
            .setParameter("ware", ware.getWarenbezeichnung());
    List<Lagerware> Lagerwaren = query.getResultList();

    if (Lagerwaren.isEmpty()) {
      return null;
    } else {
      return Lagerwaren.get(0);
    }

  }

    public Lagerplatz findeLagerplatz(Lagerplatz lagerplatz) {
    Query query = em.createQuery("SELECT c FROM Lagerplatz c WHERE c.lagerplatznummer like:lagerplatz")
            .setParameter("lagerplatz", lagerplatz.getLagerplatznummer() );
    List<Lagerplatz> Lagerplaetze = query.getResultList();

    if (Lagerplaetze.isEmpty()) {
      return null;
    } else {
      return Lagerplaetze.get(0);
    }

  }
    
  public Lagerplatz findeFreienLagerplatz() {
    Query query = em.createQuery("SELECT c FROM Lagerplatz c WHERE c.lagerstatus like:lagerstatus")
            .setParameter("lagerstatus", Lagerstatus.Frei);
    List<Lagerplatz> lagerplaetze = query.getResultList();

    if (lagerplaetze.isEmpty()) {
      return null;
    } else {
      return lagerplaetze.get(0);
    }

  }

  public int anzahlFreierLagerwarenplaetze() {
    int anzahlFreierPlaetze = 0;

    Query query = em.createQuery("SELECT c FROM Lagerplatz c WHERE c.lagerstatus like:lagerstatus")
            .setParameter("lagerstatus", Lagerstatus.Frei);
    List<Lagerplatz> lagerplaetze = query.getResultList();

    for (Lagerplatz lagerplatz : lagerplaetze) {
      anzahlFreierPlaetze += lagerplatz.getAnzahlFreierStellplaetze();
    }

    return anzahlFreierPlaetze;
  }

  public double kostenFreierLagerwarenplaetze(int anzahlBenoetigterWarenplaetze) {
    int anzahlaktuellerWarenplaetze = 0;
    int anzahlVerbleibenderWarenplaetze = anzahlBenoetigterWarenplaetze - anzahlaktuellerWarenplaetze;
    int kosten = 0;

    Query query = em.createQuery("SELECT c FROM Lagerplatz c WHERE c.lagerstatus like:lagerstatus")
            .setParameter("lagerstatus", Lagerstatus.Frei);
    List<Lagerplatz> lagerplaetze = query.getResultList();

    for (Lagerplatz lagerplatz : lagerplaetze) {
      if (anzahlVerbleibenderWarenplaetze == 0) {
        return kosten;
      } else {
        int anzahlFreierstellPlaetze = lagerplatz.getAnzahlFreierStellplaetze();
        if (anzahlFreierstellPlaetze < anzahlVerbleibenderWarenplaetze) {
          kosten += lagerplatz.getLagerpreis() * anzahlFreierstellPlaetze;
          anzahlVerbleibenderWarenplaetze -= anzahlFreierstellPlaetze;
        } else {
          kosten += lagerplatz.getLagerpreis() * anzahlVerbleibenderWarenplaetze;
          anzahlVerbleibenderWarenplaetze -= anzahlVerbleibenderWarenplaetze;
        }

      }

    }
    return kosten;
  }

  public List<Lagerware> sucheAlleEingelagertenWarenEinesKunden(int kundennr) {
    Query query = em.createQuery("SELECT c FROM Lagerware c WHERE c.einlagerungsauftrag.auftraggeber.kundennummer like:kundennr AND c.auslagerungsauftrag is NULL ")
            .setParameter("kundennr", kundennr);
    return query.getResultList();

  }
  
  public List<Lagerware> sucheAlleEingelagertenWaren() {
    Query query = em.createQuery("SELECT c FROM Lagerware c WHERE c.auslagerungsauftrag IS NULL");
    return query.getResultList();

  }

}
