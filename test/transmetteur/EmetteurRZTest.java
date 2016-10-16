package transmetteur;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import information.Information;
import transmetteurs.EmetteurRZ;

public class EmetteurRZTest {

	private EmetteurRZ emetteur;
	private Information<Boolean> information;
	private int nbElements;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Before
	public void setUp() throws Exception {
		information = new Information <Boolean>();
		
		nbElements = 0;
		information.add(true);
		nbElements ++;
		information.add(true);
		nbElements ++;
		information.add(false);
		nbElements ++;
		
		emetteur = new EmetteurRZ();
		emetteur.recevoir(information);

	}

	@Test
	public void test() {
		//Verification de la bonne reception de l'information
		collector.checkThat(emetteur.getInformationRecue(), is(information));
		
		//Test du bon nombre d'elements en emission
		collector.checkThat(emetteur.getInformationEmise().nbElements(), is(nbElements*30));
		
		//Vérification de quelques elements du signal analogique en sortie
		collector.checkThat(emetteur.getInformationEmise().iemeElement(0), is(0.0f));
		collector.checkThat(emetteur.getInformationEmise().iemeElement(11), is(1.0f));
	}

}
