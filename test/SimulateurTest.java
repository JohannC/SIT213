import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import excpetion.ArgumentsException ;


public class SimulateurTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test 
	public void testPerfectSimulatorWithFixedBinaryTransmission() throws Exception{
		String [] args = {"-mess","100111001010"};
		Simulateur sim = new Simulateur(args);
		sim.execute();
		collector.checkThat(sim.calculTauxErreurBinaire(), is(0.0f));
	}
	
	@Test 
	public void testPerfectSimulatorWithRandomTransmission() throws Exception{
		String [] args = {"-mess","200"};
		Simulateur sim = new Simulateur(args);
		sim.execute();
		collector.checkThat(sim.calculTauxErreurBinaire(), is(0.0f));
	}
	
	@Test 
	public void testPerfectSimulatorWithRandomTransmissionAndSeed() throws Exception{
		String [] args = {"-seed","10"};
		Simulateur sim = new Simulateur(args);
		sim.execute();
		collector.checkThat(sim.calculTauxErreurBinaire(), is(0.0f));
	}
	
	@Test 
	public void testAnalyseArgumentsFalseBinarySuit() throws ArgumentsException{
		thrown.expect(ArgumentsException.class);
		String [] args = {"-mess","2222255545455","-s"};
		Simulateur simulateur = new Simulateur(args);
	}
	

	@Test 
	public void testAnalyseArgumentsUnexistingOption() throws ArgumentsException{
		thrown.expect(ArgumentsException.class);
		String [] args = {"-ve"};
		Simulateur simulateur = new Simulateur(args);
	}
	
	@Test 
	public void testAnalyseArgumentsWrongSeed() throws ArgumentsException{
		thrown.expect(ArgumentsException.class);
		String [] args = {"-seed", "ffdsfds"};
		Simulateur simulateur = new Simulateur(args);
	}
	
}
