package coders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import exception.ArgumentsException;
import information.Information;
import information.InformationNonConforme;

public class EncoderNRZTTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void testNombreEchantillonProduit() throws InformationNonConforme, ArgumentsException {
		Information<Boolean> msg = new Information<Boolean>();
		msg.add(false);
		msg.add(true);
		msg.add(false);
		
		int nbEchantillon = 30;
		float min = 0.0f;
		float max = 1.0f;
		EncoderNRZT encoder = new EncoderNRZT(min, max, nbEchantillon);
		
		Information<Float> signalEncoded = encoder.encode(msg);
		
		collector.checkThat(nbEchantillon*3, is(signalEncoded.nbElements()));
	}
	
	
	@Test
	public void testMsgContenantQueDes0() throws InformationNonConforme, ArgumentsException {
		Information<Boolean> msg = new Information<Boolean>();
		msg.add(false);
		msg.add(false);
		msg.add(false);
		
		int nbEchantillon = 30;
		float min = 0.0f;
		float max = 1.0f;
		EncoderNRZT encoder = new EncoderNRZT(min, max, nbEchantillon);
		
		Information<Float> signalEncoded = encoder.encode(msg);
		
		for(int i = 0; i < signalEncoded.nbElements(); i++){
			collector.checkThat(0.0f, is(signalEncoded.iemeElement(i)));
		}
	}
	
	@Test
	public void testMsgContenantQueDes1() throws InformationNonConforme, ArgumentsException {
		Information<Boolean> msg = new Information<Boolean>();
		msg.add(true);
		msg.add(true);
		msg.add(true);
		
		int nbEchantillon = 30;
		float min = 0.0f;
		float max = 1.0f;
		EncoderNRZT encoder = new EncoderNRZT(min, max, nbEchantillon);
		
		Information<Float> signalEncoded = encoder.encode(msg);
		
		for(int i = 0; i < nbEchantillon/3; i++){
			collector.checkThat(true, is(signalEncoded.iemeElement(i) < max));
		}
		
		for(int i = nbEchantillon/3; i < (nbEchantillon*2+nbEchantillon*2/3); i++){
			collector.checkThat(true, is(signalEncoded.iemeElement(i) == max));
		}
		
		for(int i = (nbEchantillon*2+nbEchantillon*2/3); i < nbEchantillon*3; i++){
			collector.checkThat(true, is(signalEncoded.iemeElement(i) < max));
		}
	}

}
