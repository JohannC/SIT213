import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import coders.DecoderNRZTTest;
import coders.DecoderNRZTest;
import coders.DecoderRZTest;
import coders.EncoderNRZTTest;
import coders.EncoderNRZTest;
import coders.EncoderRZTest;
import destinations.DestinationFinaleTest;
import sources.SourceAleatoireTest;
import sources.SourceFixeTest;
import transmetteur.EmetteurTest;
import transmetteur.RecepteurTest;
import transmetteur.TransmetteurParfaitTest;

@RunWith(Suite.class)
@SuiteClasses({ SourceAleatoireTest.class, TransmetteurParfaitTest.class, SimulateurTest.class, SourceFixeTest.class,
		DestinationFinaleTest.class, DecoderNRZTest.class, DecoderNRZTTest.class, DecoderRZTest.class,
		EncoderNRZTest.class, EncoderNRZTTest.class, EncoderRZTest.class, EmetteurTest.class, RecepteurTest.class })
public class AllTests {

}
//fdfd