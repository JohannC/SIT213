package transmetteur;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import sources.SourceFixe;
import transmetteurs.EmetteurRZ;
import transmetteurs.RecepteurRZ;
import transmetteurs.TransmetteurAnalogiqueParfait;

public class RecepteurRZTest {

	private SourceFixe source;
	private EmetteurRZ emeteur;
	private TransmetteurAnalogiqueParfait transmetteur;
	private RecepteurRZ recepteur;

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Before
	public void setUp() throws Exception {
		source = new SourceFixe("110110001");
		emeteur = new EmetteurRZ();
		transmetteur = new TransmetteurAnalogiqueParfait();
		recepteur = new RecepteurRZ();
		
		source.connecter(emeteur);
		source.emettre();
		transmetteur.connecter(recepteur);
		transmetteur.emettre();
	}

	@Test
	public void test() {
		// Vérification de la bonne réception de l'information
		collector.checkThat(transmetteur.getInformationEmise(), is(recepteur.getInformationRecue()));

	}
}
