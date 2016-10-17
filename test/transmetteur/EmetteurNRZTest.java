package transmetteur;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Random;

import information.Information;
import information.InformationNonConforme;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import excpetion.ArgumentsException;
import transmetteurs.EmetteurNRZ;

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

public class EmetteurNRZTest {

	private static final int DEFAULT_NB_ECHANTILLON = 30;
	private static float DEFAULT_MIN = 0.0f;
	private static float DEFAULT_MAX = 1.0f;
	private static final int NBECH_TEST = 6;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void test_ParametresDefault() throws InformationNonConforme {
		
		Information<Boolean> informationTestRecue = new Information<Boolean>();
		informationTestRecue.add(false);
		informationTestRecue.add(true);
		Random r = new Random();		
		for(int i = 0; i < NBECH_TEST-2; i++){
			Boolean value = r.nextBoolean();
			informationTestRecue.add(value);
		}
		
		EmetteurNRZ emetteurTestNRZ = new EmetteurNRZ();
		
		emetteurTestNRZ.recevoir(informationTestRecue);
		
		collector.checkThat(emetteurTestNRZ.getInformationRecue(), is(informationTestRecue));
		collector.checkThat(emetteurTestNRZ.getInformationEmise().nbElements(), is(NBECH_TEST * DEFAULT_NB_ECHANTILLON));
		collector.checkThat(emetteurTestNRZ.getInformationEmise().iemeElement(0), is(DEFAULT_MIN));	
		collector.checkThat(emetteurTestNRZ.getInformationEmise().iemeElement(DEFAULT_NB_ECHANTILLON), is(DEFAULT_MAX));
	}
	
	
	@Test
	public void test_NbEchDefault() throws InformationNonConforme, ArgumentsException {
		
		Information<Boolean> informationTestRecue = new Information<Boolean>();
		informationTestRecue.add(false);
		informationTestRecue.add(true);
		Random r = new Random();		
		for(int i = 0; i < NBECH_TEST-2; i++){
			Boolean value = r.nextBoolean();
			informationTestRecue.add(value);
		}
		
		EmetteurNRZ emetteurTestNRZ = new EmetteurNRZ(-1,1);
		
		emetteurTestNRZ.recevoir(informationTestRecue);
		
		collector.checkThat(emetteurTestNRZ.getInformationEmise().nbElements(), is(NBECH_TEST * DEFAULT_NB_ECHANTILLON));
		collector.checkThat(emetteurTestNRZ.getInformationEmise().iemeElement(0), is(-1.0F));	
		collector.checkThat(emetteurTestNRZ.getInformationEmise().iemeElement(DEFAULT_NB_ECHANTILLON), is(1.0F));
	}
	
	@Test	
	public void test_MaxMinDefault() throws InformationNonConforme, ArgumentsException {
		
		Information<Boolean> informationTestRecue = new Information<Boolean>();
		informationTestRecue.add(false);
		informationTestRecue.add(true);
		Random r = new Random();		
		for(int i = 0; i < NBECH_TEST-2; i++){
			Boolean value = r.nextBoolean();
			informationTestRecue.add(value);
		}
		
		EmetteurNRZ emetteurTestNRZ = new EmetteurNRZ(1000);
		
		emetteurTestNRZ.recevoir(informationTestRecue);
		
		collector.checkThat(emetteurTestNRZ.getInformationEmise().nbElements(), is(NBECH_TEST * 1000));
		collector.checkThat(emetteurTestNRZ.getInformationEmise().iemeElement(0), is(DEFAULT_MIN));	
		collector.checkThat(emetteurTestNRZ.getInformationEmise().iemeElement(1000), is(DEFAULT_MAX));
	}
	
	@Test	
	public void test_Parmetres() throws InformationNonConforme, ArgumentsException {
		
		Information<Boolean> informationTestRecue = new Information<Boolean>();
		informationTestRecue.add(false);
		informationTestRecue.add(true);
		Random r = new Random();		
		for(int i = 0; i < NBECH_TEST-2; i++){
			Boolean value = r.nextBoolean();
			informationTestRecue.add(value);
		}
		
		EmetteurNRZ emetteurTestNRZ = new EmetteurNRZ(-2,2,1000);
		
		emetteurTestNRZ.recevoir(informationTestRecue);
		
		collector.checkThat(emetteurTestNRZ.getInformationEmise().nbElements(), is(NBECH_TEST * 1000));
		collector.checkThat(emetteurTestNRZ.getInformationEmise().iemeElement(0), is(-2.0F));	
		collector.checkThat(emetteurTestNRZ.getInformationEmise().iemeElement(1000), is(2.0F));
	}


}
