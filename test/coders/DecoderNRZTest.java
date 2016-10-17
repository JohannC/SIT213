package coders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import information.Information;

import information.InformationNonConforme;
import excpetion.ArgumentsException;

import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;


public class DecoderNRZTest {

	/**
	 * 
	 * @author M.LEMAUVIEL
	 * @author LK.CHAO
	 * @author L.AMHOUCHE
	 * @author A.KANE
	 * @author J.CORCUFF
	 *
	 * La classe Test de la classe EmetteurNRZ
	 * JUnit Test Case of EmetteurNRZ
	 * 
	 */
	
	/**
	 * Nombre d'echantillon par défaut
	 */
	private static final int DEFAULT_NB_ECHANTILLON = 30;
	
	/**
	 * L'amplitude minimale du signal par défaut 
	 */
	private static float DEFAULT_MIN = 0.0f;
	
	/**
	 * L'amplitude maximale du signal par défaut 
	 */
	private static float DEFAULT_MAX = 1.0f;
	
	/**
	 * Nombre d'impulsation émises 
	 */
	private static final int NBECH_TEST = 6;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	/**
	 * Methode testant le nombre d'elements reçus 
	 * @throws InformationNonConforme : Exception levée à la reception des informations erronées  
	 * @throws ArgumentsException : Exception levée à la reception des arguments invalides 
	 */
	
	@Test
	public void testNombreBitsRecus() throws InformationNonConforme, ArgumentsException {
		
		Information<Float> informationTestRecue = new Information<Float>();
		
		Random r = new Random();
		for(int i = 0; i < NBECH_TEST; i++){
			Boolean value = r.nextBoolean();
			if (value)
				for(int j = 0; j < DEFAULT_NB_ECHANTILLON; j++)
					informationTestRecue.add(DEFAULT_MAX);
			else
				for(int j = 0; j < DEFAULT_NB_ECHANTILLON; j++)
					informationTestRecue.add(DEFAULT_MIN);
		}
		
		DecoderNRZT decoder = new DecoderNRZT(DEFAULT_MIN, DEFAULT_MAX, DEFAULT_NB_ECHANTILLON);
		
		collector.checkThat(decoder.decode(informationTestRecue).nbElements(), is(NBECH_TEST));
		
	}
	
	/**
	 * Methode testant le decodage de niveaux des echantillons et la conversion aux elements boolean   
	 * @throws InformationNonConforme : Exception levée à la reception des informations erronées  
	 * @throws ArgumentsException : Exception levée à la reception des arguments invalides 
	 */
	@Test
	public void testBonDecodageMinEtMax() throws InformationNonConforme, ArgumentsException {
		
		Information<Float> informationTestRecue = new Information<Float>();
		
		for(int j = 0; j < DEFAULT_NB_ECHANTILLON; j++)
			informationTestRecue.add(DEFAULT_MAX);
		for(int j = 0; j < 3*DEFAULT_NB_ECHANTILLON; j++)
			informationTestRecue.add(DEFAULT_MIN);
		
		DecoderNRZT decoder = new DecoderNRZT(DEFAULT_MIN, DEFAULT_MAX, DEFAULT_NB_ECHANTILLON);
		
		collector.checkThat(decoder.decode(informationTestRecue).iemeElement(0), is(true));
		for(int j = 1; j < 4; j++)
			collector.checkThat(decoder.decode(informationTestRecue).iemeElement(j), is(false));
		
	}

}
