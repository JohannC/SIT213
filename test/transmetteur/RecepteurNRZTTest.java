package transmetteur;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import excpetion.ArgumentsException;
import information.Information;
import information.InformationNonConforme;
import transmetteurs.EmetteurNRZT;
import transmetteurs.RecepteurNRZT;

public class RecepteurNRZTTest {

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
		RecepteurNRZT recepteur = new RecepteurNRZT(min, max, nbEchantillon);
		EmetteurNRZT emetteur = new EmetteurNRZT(min, max, nbEchantillon);
		emetteur.connecter(recepteur);
		emetteur.recevoir(msg);
		emetteur.emettre();
		
		for(int i = 0; i < recepteur.getInformationEmise().nbElements(); i++){
			collector.checkThat(new Boolean(false), is(recepteur.getInformationEmise().iemeElement(i)));
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
		RecepteurNRZT recepteur = new RecepteurNRZT(min, max, nbEchantillon);
		EmetteurNRZT emetteur = new EmetteurNRZT(min, max, nbEchantillon);
		emetteur.connecter(recepteur);
		emetteur.recevoir(msg);
		emetteur.emettre();
		
		for(int i = 0; i < recepteur.getInformationEmise().nbElements(); i++){
			collector.checkThat(new Boolean(true), is(recepteur.getInformationEmise().iemeElement(i)));
		}
	}
}
