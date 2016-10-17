package coders;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import information.Information;

public class EncoderRZTest {
	
	int nbEch;
	float min, max;
	private EncoderRZ codeur;
	private Information<Boolean> infoBool;
	private int nbBits;
	private Information<Float> infoFloat;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Before
	public void setUp() throws Exception {
		
		min = 0f;
		max = 1f;
		nbEch = 30;
		
		infoBool = new Information <Boolean>();
		
		nbBits = 0;
		infoBool.add(false);
		nbBits ++;
		infoBool.add(true);
		nbBits ++;

		codeur = new EncoderRZ(min, max, nbEch);
		infoFloat = codeur.encode(infoBool);
	}
	@Test
	public void testNombreEchantillonProduit() {
		collector.checkThat(infoFloat.nbElements(), is(nbBits*nbEch));
	}
	
	@Test
	public void testCodagePremierBit0() {
		for(int i = 0 ; i < nbEch ; i++)
			collector.checkThat(infoFloat.iemeElement(i), is(min));
	}
	
	@Test
	public void testCodageSecondBit1() {
		for(int i = nbEch ; i < nbEch+(nbEch/3) ; i++)
			collector.checkThat(infoFloat.iemeElement(i), is(min));
		for(int i = nbEch+(nbEch/3) ; i < nbEch+(2*nbEch/3) ; i++)
			collector.checkThat(infoFloat.iemeElement(i), is(max));	
		for(int i = nbEch+(2*nbEch/3) ; i < nbBits*nbEch ; i++)
			collector.checkThat(infoFloat.iemeElement(i), is(min));
		
	}
	

}
