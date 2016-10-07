package sources;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.core.IsNot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import information.InformationNonConforme;

public class SourceAleatoireTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void testMemeSequencePourMemeSeed() throws InformationNonConforme {
		SourceAleatoire source1 = new SourceAleatoire(100, 2151);
		SourceAleatoire source2 = new SourceAleatoire(100, 2151);

		source1.emettre();
		source2.emettre();
		
		collector.checkThat(source1.getInformationEmise(), is(source2.getInformationEmise()));
	}

	@Test
	public void testMemeSequencePourDifferentesSeeds() throws InformationNonConforme {
		SourceAleatoire source1 = new SourceAleatoire(100, 2151);
		SourceAleatoire source2 = new SourceAleatoire(100, 2551);

		source1.emettre();
		source2.emettre();
		
		collector.checkThat(source1.getInformationEmise(), not(source2.getInformationEmise()));
	}

}
