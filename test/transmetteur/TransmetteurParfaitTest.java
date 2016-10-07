package transmetteur;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import information.InformationNonConforme;
import sources.SourceAleatoire;
import transmetteurs.TransmetteurParfait;

public class TransmetteurParfaitTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void testTransmetteurVraimentParfait() throws InformationNonConforme {
		SourceAleatoire source = new SourceAleatoire(100);
		TransmetteurParfait transmetteur = new TransmetteurParfait();

		source.connecter(transmetteur);
		source.emettre();
		
		collector.checkThat(source.getInformationEmise(), is(transmetteur.getInformationRecue()));
		collector.checkThat(source.getInformationEmise(), is(transmetteur.getInformationEmise()));		
	}

}
