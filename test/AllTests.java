import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import destinations.DestinationFinaleTest;
import sources.SourceAleatoireTest;
import sources.SourceFixeTest;
import transmetteur.TransmetteurParfaitTest;

@RunWith(Suite.class)
@SuiteClasses({ SourceAleatoireTest.class, TransmetteurParfaitTest.class, SimulateurTest.class, SourceFixeTest.class,
		DestinationFinaleTest.class,  })
public class AllTests {

}
