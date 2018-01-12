package de.othr.sw.lagerhaus.service;

import de.othr.sw.lagerhaus.entity.Lagerauftrag;
import de.othr.sw.lagerhaus.entity.Lagerware;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
public class LagerService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public Lagerware wareAnlegen(Lagerware ware)
    {
        ware.setLagerwarennummer(new Random().nextInt(9999999));
        
        em.persist(ware);
        
        return ware;
    }
    
    @Transactional
    public List<Lagerware> warenAnlegen(List<Lagerware> waren)
    {
      
      for (Lagerware ware : waren) {
        ware.setLagerwarennummer(new Random().nextInt(9999999));
        em.persist(ware);
      }
        
        return waren;
    }
    
    @Transactional
    public Lagerauftrag wareEinlagern (Lagerauftrag auftrag)
    {
      auftrag.setLagerauftragsnummer(new Random().nextInt(9999999));
      em.persist(auftrag);
        
      return auftrag;
    }
    
    @Transactional
    public Lagerauftrag wareAuslagern (Lagerauftrag auftrag)
    {
      auftrag.setLagerauftragsnummer(new Random().nextInt(9999999));
      em.persist(auftrag);
        
      return auftrag;
    }
    
    
    
    
}
