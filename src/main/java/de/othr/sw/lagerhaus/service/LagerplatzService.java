package de.othr.sw.lagerhaus.service;

import de.othr.sw.lagerhaus.entity.Lagerplatz;
import java.util.Random;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
public class LagerplatzService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public Lagerplatz LagerplatzAnlegen(Lagerplatz k)
    {
        k.setLagerplatznummer(new Random().nextInt(9999999));
        
        em.persist(k);
        
        return k;
    }
    
    public Lagerplatz leseLagerplatz (int lagerplatzNummer)
    {
        Lagerplatz gefunden = em.find(Lagerplatz.class, lagerplatzNummer);
        return gefunden;
    }
    
}
