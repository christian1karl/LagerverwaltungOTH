package de.othr.sw.lagerhaus.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lagerauftrag implements Serializable{
    
    @Id
    private int _lagerauftragsnummer;
    //private Collection<Lagerware> _waren;
    private Date _auftragsdatum;
    private Auftragstyp _auftragstyp;
    private Kunde _auftraggeber;
    
}
