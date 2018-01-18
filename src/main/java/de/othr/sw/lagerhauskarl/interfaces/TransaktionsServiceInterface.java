package de.othr.sw.lagerhauskarl.interfaces;

import javax.enterprise.context.RequestScoped;


@RequestScoped
public interface TransaktionsServiceInterface {


    public boolean transaktionAbschliessen(double betrag, String verwendungszweck);
    

    
}
