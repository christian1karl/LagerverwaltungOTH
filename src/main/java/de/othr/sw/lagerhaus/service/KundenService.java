package de.othr.sw.lagerhaus.service;

import de.othr.sw.lagerhaus.entity.Person;
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
    public Person PersonAnlegen(Person k)
    {
        k.setPersonenNummer(new Random().nextInt(9999999));
        
        em.persist(k);
        
        return k;
    }
    
    public Person lesePerson (int personenNummer)
    {
        Person gefunden = em.find(Person.class, personenNummer);
        return gefunden;
    }
    
}
