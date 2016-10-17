package coders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Random;

import information.Information;
import information.InformationNonConforme;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import excpetion.ArgumentsException;

public class EncoderNRZTest {

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
	 * Methode testant le nombre d'echantillons envoyé 
	 * @throws InformationNonConforme : Exception levée à la reception des informations erronées  
	 * @throws ArgumentsException : Exception levée à la reception des arguments invalides 
	 */
	@Test
	public void testNombreEchantillonProduit() throws InformationNonConforme, ArgumentsException {
		
		Information<Boolean> informationTestRecue = new Information<Boolean>();
		Random r = new Random();		
		for(int i = 0; i < NBECH_TEST; i++){
			Boolean value = r.nextBoolean();
			informationTestRecue.add(value);
		}
		
		EncoderNRZ encoder = new EncoderNRZ(DEFAULT_MIN, DEFAULT_MAX, DEFAULT_NB_ECHANTILLON);
		
		encoder.encode(informationTestRecue);
		
		collector.checkThat(encoder.encode(informationTestRecue).nbElements(), is(NBECH_TEST * DEFAULT_NB_ECHANTILLON));		
	}
	
	/**
	 * Methode testant le codage de niveau des echantillons envoyés 
	 * @throws InformationNonConforme : Exception levée à la reception des informations erronées  
	 * @throws ArgumentsException : Exception levée à la reception des arguments invalides 
	 */
	
	@Test
	public void testBonEncodageMinEtMax() throws InformationNonConforme, ArgumentsException {
		
		Information<Boolean> informationTestRecue = new Information<Boolean>();
		informationTestRecue.add(false);
		informationTestRecue.add(true);
		informationTestRecue.add(true);
		informationTestRecue.add(false);
		
		EncoderNRZ encoder = new EncoderNRZ(DEFAULT_MIN, DEFAULT_MAX, DEFAULT_NB_ECHANTILLON);
		
		encoder.encode(informationTestRecue);
		
		collector.checkThat(encoder.encode(informationTestRecue).iemeElement(0), is(DEFAULT_MIN));	
		collector.checkThat(encoder.encode(informationTestRecue).iemeElement(DEFAULT_NB_ECHANTILLON), is(DEFAULT_MAX));	
	}

}
