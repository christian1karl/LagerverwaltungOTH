package de.othr.sw.lagerhauskarl.service;

import de.othr.sw.lagerhauskarl.entity.Kunde;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@RequestScoped
public class KundenService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public Kunde registrieren(Kunde k)
    {
        k.setKundennummer(new Random().nextInt(9999999));
       
        em.persist(k);

        return k;
    }
    
    public Kunde einloggen(String benutzername, String passwort)
    {
      try{
       Query query = em.createQuery("SELECT c FROM Kunde c WHERE c.benutzername like:benutzername and c.passwort like:passwort)")
      .setParameter("benutzername", benutzername)
      .setParameter("passwort", passwort);
      Kunde kunde = (Kunde)query.getSingleResult();
      return kunde;
      }
      catch(NoResultException ex)
      {
        return null;
      }
      
    }
    
    public Kunde lesePerson (int personenNummer)
    {
        Kunde gefunden = em.find(Kunde.class, personenNummer);
        return gefunden;
    }

      public List<Kunde> leseAllePersonen ()
    {
        Query query = em.createQuery("SELECT t FROM Person ");
        return (List<Kunde>) query.getResultList();
    }
    
}
