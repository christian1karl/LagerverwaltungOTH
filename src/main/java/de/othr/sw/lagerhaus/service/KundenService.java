package de.othr.sw.lagerhaus.service;

import de.othr.sw.lagerhaus.entity.Kunde;
import java.util.Random;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
public class KundenService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public Kunde PersonAnlegen(Kunde k)
    {
        k.setPersonenNummer(new Random().nextInt(9999999));
        
        em.persist(k);
        
        return k;
    }
    
    public Kunde lesePerson (int personenNummer)
    {
        Kunde gefunden = em.find(Kunde.class, personenNummer);
        return gefunden;
    }
    
}
