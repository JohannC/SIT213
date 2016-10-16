package sources;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import excpetion.ArgumentsException;
import information.InformationNonConforme;

public class SourceFixeTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void testMemeMessageBinaire() throws InformationNonConforme, ArgumentsException {
		SourceFixe source1 = new SourceFixe("11011");
		SourceFixe source2 = new SourceFixe("11011");

		source1.emettre();
		source2.emettre();
		
		collector.checkThat(source1.getInformationEmise(), is(source2.getInformationEmise()));
	}
	
	@Test
	public void testDifferentsMessagesBinaires() throws InformationNonConforme, ArgumentsException {
		SourceFixe source1 = new SourceFixe("11011");
		SourceFixe source2 = new SourceFixe("11010");

		source1.emettre();
		source2.emettre();
		
		collector.checkThat(source1.getInformationEmise(), not(source2.getInformationEmise()));
	}

}
