package transmetteur;

import static org.hamcrest.CoreMatchers.is;

import java.util.Random;

import information.Information;
import information.InformationNonConforme;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import transmetteurs.RecepteurNRZ;

/**
 * 
 * @author M.LEMAUVIEL
 * @author LK.CHAO
 * @author L.AMHOUCHE
 * @author A.KANE
 * @author J.CORCUFF
 *
 * La classe Test de la classe RecepteurNRZ
 * JUnit Test Case of RecepteurNRZ
 */

public class RecepteurNRZTest {


	private static final int DEFAULT_NB_ECHANTILLON = 30;
	private static float DEFAULT_MIN = 0.0f;
	private static float DEFAULT_MAX = 1.0f;
	private static final int NBECH_TEST = 6;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void test_ParametresDefault() throws InformationNonConforme {
		
		Information<Float> informationTestRecue = new Information<Float>();
		for(int j = 0; j < DEFAULT_NB_ECHANTILLON; j++)
			informationTestRecue.add(DEFAULT_MAX);
		for(int j = 0; j < DEFAULT_NB_ECHANTILLON; j++)
			informationTestRecue.add(DEFAULT_MIN);
		
		Random r = new Random();
		for(int i = 0; i < NBECH_TEST-2; i++){
			Boolean value = r.nextBoolean();
			if (value)
				for(int j = 0; j < DEFAULT_NB_ECHANTILLON; j++)
					informationTestRecue.add(DEFAULT_MAX);
			else
				for(int j = 0; j < DEFAULT_NB_ECHANTILLON; j++)
					informationTestRecue.add(DEFAULT_MIN);
		}
		
		RecepteurNRZ recepteurTestNRZ = new RecepteurNRZ();
		
		recepteurTestNRZ.recevoir(informationTestRecue);
		
		collector.checkThat(recepteurTestNRZ.getInformationRecue(), is(informationTestRecue));
		collector.checkThat(recepteurTestNRZ.getInformationEmise().nbElements(), is(NBECH_TEST));
		collector.checkThat(recepteurTestNRZ.getInformationEmise().iemeElement(0), is(true));	
		collector.checkThat(recepteurTestNRZ.getInformationEmise().iemeElement(1), is(false));
	}
}