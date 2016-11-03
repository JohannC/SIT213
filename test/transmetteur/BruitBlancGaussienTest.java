package transmetteur;

import static org.hamcrest.CoreMatchers.is;
import information.Information;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import transmetteurs.BruitBlancGaussien;

public class BruitBlancGaussienTest {
	
	private static final int SEED = 2;

	private static final int PUISSANCE_BRUIT = 1;

	private static final int NB_ECH_BRUIT = 10;
	

	Information <Float> infoTestBruitBG;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void nombreElementsTest() {
		infoTestBruitBG = new Information<Float>();
		BruitBlancGaussien bruitBG = new BruitBlancGaussien(PUISSANCE_BRUIT, SEED);
		
		infoTestBruitBG = bruitBG.generateurBruitBG(NB_ECH_BRUIT);
		
		collector.checkThat(infoTestBruitBG.nbElements(), is(NB_ECH_BRUIT));
		
		for(int i= 0; i < infoTestBruitBG.nbElements(); i++)
			System.out.println(infoTestBruitBG.iemeElement(i));
		
	}

}
