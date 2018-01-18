package de.othr.sw.lagerhauskarl.interfaceImpl;

import de.othr.brucknerbank.service.TransaktionServiceService;
import de.othr.brucknerbank.service.Transaktion;
import de.othr.brucknerbank.service.TransaktionService;
import de.othr.sw.lagerhauskarl.interfaces.TransaktionsServiceInterface;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;

@RequestScoped
@Alternative
public class TransaktionsServiceBrucknerImpl implements TransaktionsServiceInterface {

  @Override
  public boolean transaktionAbschliessen(double betrag, String verwendungszweck) {
    
    try {
      TransaktionServiceService service = new TransaktionServiceService();
      TransaktionService port = service.getTransaktionServicePort();
      Transaktion transaktion = new Transaktion();
      
      transaktion.setBetrag(betrag);
      transaktion.setVerwendungszweck(verwendungszweck);

      return port.transaktionLagerhausKarl(transaktion);
    } catch (Exception ex) {
      return false;
    }

  }
  
}
