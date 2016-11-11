package utils;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import information.Information;
import utils.GenerateurBruitBlancGaussien;


public class GenerateurBruitBlancGaussienTest {
	
	private static final int SEED = 2;

	private static final int PUISSANCE_BRUIT = 1;

	private static final int NB_ECH_BRUIT = 10;
	

	Information <Float> infoTestBruitBG;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void nombreElementsTest() {
		infoTestBruitBG = new Information<Float>();
		GenerateurBruitBlancGaussien bruitBG = new GenerateurBruitBlancGaussien(PUISSANCE_BRUIT, SEED);
		
		infoTestBruitBG = bruitBG.getBruitBlancGaussien(NB_ECH_BRUIT);
		
		collector.checkThat(infoTestBruitBG.nbElements(), is(NB_ECH_BRUIT));
		
		for(int i= 0; i < infoTestBruitBG.nbElements(); i++)
			System.out.println(infoTestBruitBG.iemeElement(i));
		
	}

}
