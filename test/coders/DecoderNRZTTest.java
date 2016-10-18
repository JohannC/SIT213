package coders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import exception.ArgumentsException;
import information.Information;
import information.InformationNonConforme;

public class DecoderNRZTTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void testNombreBitsRecus() throws InformationNonConforme, ArgumentsException {
		Information<Boolean> msg = new Information<Boolean>();
		msg.add(false);
		msg.add(true);
		msg.add(false);
		
		int nbEchantillon = 30;
		float min = 0.0f;
		float max = 1.0f;
		EncoderNRZT encoder = new EncoderNRZT(min, max, nbEchantillon);
		
		Information<Float> signalEncoded = encoder.encode(msg);
		
		DecoderNRZT decoder = new DecoderNRZT(min, max, nbEchantillon);
		
		Information<Boolean> signalDecoded = decoder.decode(signalEncoded);
		
		collector.checkThat(3, is(signalDecoded.nbElements()));
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
		
		DecoderNRZT decoder = new DecoderNRZT(min, max, nbEchantillon);
		
		Information<Boolean> signalDecoded = decoder.decode(signalEncoded);
		
		for(int i = 0; i < signalDecoded.nbElements(); i++){
			collector.checkThat(new Boolean(false), is(signalDecoded.iemeElement(i)));
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
		
		DecoderNRZT decoder = new DecoderNRZT(min, max, nbEchantillon);
		
		Information<Boolean> signalDecoded = decoder.decode(signalEncoded);
		
		for(int i = 0; i < signalDecoded.nbElements(); i++){
			collector.checkThat(new Boolean(true), is(signalDecoded.iemeElement(i)));
		}
	}

}
