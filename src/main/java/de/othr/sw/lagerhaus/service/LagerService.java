package de.othr.sw.lagerhaus.service;

import de.othr.sw.lagerhaus.entity.Lagerauftrag;
import de.othr.sw.lagerhaus.entity.Lagerplatz;
import de.othr.sw.lagerhaus.entity.Lagerware;
import de.othr.sw.lagerhaus.enums.Auftragstyp;
import de.othr.sw.lagerhaus.enums.Lagerstatus;
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
        
        for (int i=0; i<waren.size(); i++) 
        {   
          Lagerware eingelagerteWare = wareEinlagern(waren.get(i));
          if(eingelagerteWare == null)
            return lagerauftrag;
          eingelagerteWare.setEinlagerungsauftrag(lagerauftrag);
          em.merge(eingelagerteWare);
        }
        em.persist(lagerauftrag);

      } else {
        List<Lagerware> waren = lagerauftrag.getAuslagerungsWaren();
        for (Lagerware ware : waren) {
          wareAuslagern(ware);
          ware.setAuslagerungsauftrag(lagerauftrag);
          em.merge(ware);
        }

        em.persist(lagerauftrag);
      }
      return lagerauftrag;
  }

  @Transactional
  public Lagerware wareEinlagern(Lagerware ware) {
    Lagerplatz lagerplatz = findeFreienLagerplatz();
    if(lagerplatz == null)
      return null;
    
    lagerplatz.getLagerwaren().add(ware);
    lagerplatz.setLagerstatus(Lagerstatus.Belegt);
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

    if (lagerplatz.getLagerwaren().isEmpty()) {
      lagerplatz.setLagerstatus(Lagerstatus.Frei);
    }

    em.merge(lagerplatz);
    em.merge(ware);
    return ware;
  }

  public Lagerplatz findeFreienLagerplatz() {
      Query query = em.createQuery("SELECT c FROM Lagerplatz c WHERE c.Lagerstatus like:lagerstatus")
              .setParameter("lagerstatus", Lagerstatus.Frei);
      List<Lagerplatz> lagerplaetze = query.getResultList();
      
      if(lagerplaetze.isEmpty())
        return null;
      else
        return lagerplaetze.get(0);
      
    

  }

}
