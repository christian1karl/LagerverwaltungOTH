package de.othr.sw.lagerhaus.entity;

import de.othr.sw.lagerhaus.enums.Auftragstyp;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lagerauftrag implements Serializable{
    
    @Id
    private int Lagerauftragsnummer;
    //private Collection<Lagerware> Waren;
    private Date Auftragsdatum;
    private Auftragstyp Auftragstyp;
    private Kunde Auftraggeber;
    
}
