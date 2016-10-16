package transmetteur;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import sources.SourceFixe;
import transmetteurs.EmetteurRZ;

public class EmetteurRZTest {

	private SourceFixe source;
	private EmetteurRZ emetteur;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Before
	public void setUp() throws Exception {
		source = new SourceFixe("110110001");
		emetteur = new EmetteurRZ();
		
		source.connecter(emetteur);
		source.emettre();
	}

	@Test
	public void test() {
		//Vérification de la bonne réception de l'information
		collector.checkThat(source.getInformationEmise(), is(emetteur.getInformationRecue()));
		
		//Test du bon nombre d'élements en émission
		collector.checkThat(9*30, is(emetteur.getInformationEmise().nbElements()));
		
		//Vérification de quelques éléments du signal analogique en sortie
		collector.checkThat(0.0f, is(emetteur.getInformationEmise().iemeElement(0)));
		collector.checkThat(1.0f, is(emetteur.getInformationEmise().iemeElement(11)));
	}

}
