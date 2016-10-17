package transmetteur;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import excpetion.ArgumentsException;
import information.Information;
import information.InformationNonConforme;
import transmetteurs.EmetteurNRZT;

public class EmetteurNRZTTest {
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void testMsgContenantQueDes0() throws InformationNonConforme, ArgumentsException {
		Information<Boolean> msg = new Information<Boolean>();
		msg.add(false);
		msg.add(false);
		msg.add(false);
		
		int nbEchantillon = 30;
		float min = 0.0f;
		float max = 1.0f;
		EmetteurNRZT emetteur = new EmetteurNRZT(min, max, nbEchantillon);
		emetteur.recevoir(msg);
		emetteur.emettre();
		
		for(int i = 0; i < emetteur.getInformationEmise().nbElements(); i++){
			collector.checkThat(0.0f, is(emetteur.getInformationEmise().iemeElement(i)));
		}
	}
	
	@Test
	public void testMsgContenantQueDes1() throws InformationNonConforme, ArgumentsException {
		Information<Boolean> msg = new Information<Boolean>();
		msg.add(true);
		msg.add(true);
		msg.add(true);
		
		int nbEchantillon = 30;
		float min = 0.0f;
		float max = 1.0f;
		EmetteurNRZT emetteur = new EmetteurNRZT(min, max, nbEchantillon);
		emetteur.recevoir(msg);
		emetteur.emettre();
		
		for(int i = 0; i < nbEchantillon/3; i++){
			collector.checkThat(true, is(emetteur.getInformationEmise().iemeElement(i) < max));
		}
		
		for(int i = nbEchantillon/3; i < (nbEchantillon*2+nbEchantillon*2/3); i++){
			collector.checkThat(true, is(emetteur.getInformationEmise().iemeElement(i) == max));
		}
		
		for(int i = (nbEchantillon*2+nbEchantillon*2/3); i < nbEchantillon*3; i++){
			collector.checkThat(true, is(emetteur.getInformationEmise().iemeElement(i) < max));
		}
	}
	
}