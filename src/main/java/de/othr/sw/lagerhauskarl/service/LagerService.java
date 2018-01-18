package de.othr.sw.lagerhauskarl.service;

import de.othr.sw.lagerhauskarl.entity.Lagerauftrag;
import de.othr.sw.lagerhauskarl.entity.Lagerplatz;
import de.othr.sw.lagerhauskarl.entity.Lagerware;
import de.othr.sw.lagerhauskarl.enums.Auftragstyp;
import de.othr.sw.lagerhauskarl.enums.Lagerstatus;
import java.sql.Timestamp;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


@WebService
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
  @WebMethod
  public Lagerauftrag auftragBearbeiten(Lagerauftrag lagerauftrag) {
    
    List<Lagerware> waren = lagerauftrag.getWarenliste();
    
    if (lagerauftrag.getAuftragstyp() == Auftragstyp.Einlagerung) {

      for (int i = 0; i < waren.size(); i++) {
        Lagerware eingelagerteWare = wareEinlagern(waren.get(i));
        if (eingelagerteWare == null) {
          return null;
        }
        eingelagerteWare.getLagerauftraege().add(lagerauftrag);
        em.merge(eingelagerteWare);
        
        em.persist(lagerauftrag);
      }
      em.merge(lagerauftrag);

    } else {
      
      for (Lagerware ware : waren) {
        Lagerware ausgelagerteWare = wareAuslagern(ware);
        if (ausgelagerteWare == null) {
          return null;
        }
        
        ausgelagerteWare.getLagerauftraege().add(lagerauftrag);
        em.persist(lagerauftrag);
        em.merge(ausgelagerteWare);
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
    Lagerplatz lagerplatz = ware.getLagerplatz();
    lagerplatz.getLagerwaren().remove(ware);
    ware.setLagerplatz(null);
    if(lagerplatz.getLagerwaren().size() < lagerplatz.getLagerwarenkapazitaet())
    {
      lagerplatz.setLagerstatus(Lagerstatus.Frei);
    }
    em.merge(lagerplatz);
    return ware;
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
    Query query = em.createQuery("SELECT c FROM Lagerware c JOIN c.lagerauftraege d WHERE d.auftraggeber.kundennummer like:kundennr AND c.lagerplatz IS NOT null")  
            .setParameter("kundennr", kundennr);
    
    
    return query.getResultList();

  }
  
  public List<Lagerware> sucheAlleEingelagertenWaren() {
    Query query = em.createQuery("SELECT c FROM Lagerware c WHERE c.lagerplatz IS NOT NULL");
    return query.getResultList();

  }

}
