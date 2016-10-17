package transmetteur;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import information.Information;
import transmetteurs.Recepteur;

public class RecepteurTest {
	
	private float min, max;
	private int nbEch;
	private Information<Float> infoFloat;
	private Recepteur recepteur;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Before
	public void setUp() throws Exception {
		min = 0f;
		max = 1f;
		nbEch = 30;
		
		infoFloat = new Information<Float>();
		recepteur = new Recepteur("RZ", min, max, nbEch);
		
		//Premier symbole : 0
		for (int i=0; i<nbEch ; i++)
			infoFloat.add(min);
		
		//Premier symbole : 1
		for (int i=0; i<nbEch/3 ; i++)
			infoFloat.add(min);
		for (int i=0; i<nbEch/3 ; i++)
			infoFloat.add(max);
		for (int i=0; i<nbEch/3 ; i++)
			infoFloat.add(min);
		
		
		recepteur.recevoir(infoFloat);
		recepteur.emettre();
	}
	
	@Test
	public void testBonneReception() {
		collector.checkThat(recepteur.getInformationRecue(), is(infoFloat));
	}
	
	@Test
	public void testNombreElementsEmis() {
		collector.checkThat(recepteur.getInformationEmise().nbElements(), is(infoFloat.nbElements()/nbEch));	
	}

}
