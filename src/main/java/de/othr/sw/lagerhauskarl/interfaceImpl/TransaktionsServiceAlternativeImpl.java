package de.othr.sw.lagerhauskarl.interfaceImpl;

import de.othr.sw.lagerhauskarl.interfaces.TransaktionsServiceInterface;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;

@RequestScoped
@Alternative
public class TransaktionsServiceAlternativeImpl implements TransaktionsServiceInterface {

  @Override
  public boolean transaktionAbschliessen(double betrag, String verwendungszweck) {
    
    return true;

  }
  
}
