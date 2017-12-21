package de.othr.sw.lagerhaus.service;

import de.othr.sw.lagerhaus.entity.Mitarbeiter;
import java.util.Random;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
public class MitarbeiterService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public Mitarbeiter MitarbeiterAnlegen(Mitarbeiter k)
    {
        k.setMitarbeiternummer(new Random().nextInt(9999999));
        
        em.persist(k);
        
        return k;
    }
    
    public Mitarbeiter lesePerson (int personenNummer)
    {
        Mitarbeiter gefunden = em.find(Mitarbeiter.class, personenNummer);
        return gefunden;
    }
    
}
