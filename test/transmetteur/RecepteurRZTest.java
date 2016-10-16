package transmetteur;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import information.Information;
import transmetteurs.RecepteurRZ;

public class RecepteurRZTest {

	private RecepteurRZ recepteur;
	private Information<Float> information;

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Before
	public void setUp() throws Exception {
		
		// Création de l'information test à envoyé au récepteur RZ
		information = new Information<Float>();
		
		//Premier symbole : 1
		for (int i=0; i<10; i++)
			information.add(0f);
		for (int i=0; i<10; i++)
			information.add(1f);
		for (int i=0; i<10; i++)
			information.add(0f);
		
		//Second symbole : 0
		for (int i=0; i<10; i++)
			information.add(0f);
		for (int i=0; i<10; i++)
			information.add(0f);
		for (int i=0; i<10; i++)
			information.add(0f);
		
		recepteur = new RecepteurRZ();
		recepteur.recevoir(information);
	}

	@Test
	public void test() {
		// Vérification de la bonne réception du premier symbole
		collector.checkThat(recepteur.getInformationRecue().iemeElement(5), is(0f));
		collector.checkThat(recepteur.getInformationRecue().iemeElement(15), is(1f));
		collector.checkThat(recepteur.getInformationRecue().iemeElement(25), is(0f));
		// Vérification de la bonne réception du second symbole
		collector.checkThat(recepteur.getInformationRecue().iemeElement(35), is(0f));
		collector.checkThat(recepteur.getInformationRecue().iemeElement(45), is(0f));
		collector.checkThat(recepteur.getInformationRecue().iemeElement(55), is(0f));
		
		// Vérification de la bonne émission de l'information
		collector.checkThat(recepteur.getInformationEmise().iemeElement(0), is(true));
		collector.checkThat(recepteur.getInformationEmise().iemeElement(1), is(false));
	}
}
