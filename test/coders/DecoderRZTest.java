package coders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import information.Information;

public class DecoderRZTest {
	
	int nbEch;
	float min, max;
	private DecoderRZ decodeur;
	private Information<Float> infoFloat;
	private Information<Boolean> infoBool;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Before
	public void setUp() throws Exception {
		
		min = 0f;
		max = 1f;
		nbEch = 30;
		
		infoFloat = new Information<Float>();
		
		//Premier symbole : 0
		for (int i=0; i<nbEch ; i++)
			infoFloat.add(0f);
		
		//Second symbole : 1
		for (int i=0; i<nbEch/3 ; i++)
			infoFloat.add(0f);
		for (int i=0; i<nbEch/3 ; i++)
			infoFloat.add(1f);
		for (int i=0; i<nbEch/3 ; i++)
			infoFloat.add(0f);
		
		decodeur = new DecoderRZ(min, max, nbEch);
		infoBool = decodeur.decode(infoFloat);
	}

	@Test
	public void testNombreEchantillonProduit() {
		collector.checkThat(infoBool.nbElements(), is(infoFloat.nbElements()/nbEch));
	}
	
	@Test
	public void testDecodagePremierBit0() {
		collector.checkThat(infoBool.iemeElement(0), is(false));
	}
	
	@Test
	public void testDecodageSecondBit1() {
		collector.checkThat(infoBool.iemeElement(1), is(true));
	}	

}
