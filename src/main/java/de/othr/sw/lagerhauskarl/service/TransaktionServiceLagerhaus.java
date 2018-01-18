package de.othr.sw.lagerhauskarl.service;

import de.othr.sw.lagerhauskarl.entity.Lagerauftrag;
import de.othr.sw.lagerhauskarl.interfaces.TransaktionsServiceInterface;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@RequestScoped
public class TransaktionServiceLagerhaus {
  
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private TransaktionsServiceInterface transaktionsService;
  

    
    @Transactional
    public Lagerauftrag einlagerungBezahlen(Lagerauftrag auftrag){
      
      String verwendungszweck = auftrag.getAuftragstyp().toString() + " " + auftrag.getAuftraggeber().getNachname() +" am " +auftrag.getAuftragsdatum().toString();
      
      auftrag.setBezahlt(transaktionsService.transaktionAbschliessen(auftrag.getGesamtkosten(),verwendungszweck));
      
      return auftrag;
    }
    
    
}
