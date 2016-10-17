package transmetteur;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import information.Information;
import transmetteurs.Emetteur;

public class EmetteurTest {
	
	private int nbEch;
	private Information<Boolean> infoBool;
	private Emetteur emetteur;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Before
	public void setUp() throws Exception {
		float min = 0f;
		float max = 1f;
		nbEch = 30;
		
		infoBool = new Information<Boolean>();
		infoBool.add(true);
		infoBool.add(false);
		
		emetteur = new Emetteur("RZ", min, max, nbEch);
		emetteur.recevoir(infoBool);
	}
	
	@Test
	public void testBonneReception() {
		collector.checkThat(emetteur.getInformationRecue(), is(infoBool));
	}
	
	@Test
	public void testNombreElementsEmis() {
		collector.checkThat(emetteur.getInformationEmise().nbElements(), is(infoBool.nbElements()*nbEch));	
	}

}
