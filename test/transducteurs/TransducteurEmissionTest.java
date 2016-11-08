package transducteurs;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import information.Information;

public class TransducteurEmissionTest {

	TransducteurEmission transducteurEmission;
	Information<Boolean> infoEntree;
	Information<Boolean> infoSortie;
	int n = 0;
	
	@Before
	public void setUp() throws Exception {
		
		//Creation d'un message en entree
		infoEntree = new Information<Boolean>();
		//Creation d'un message en sortie qui servira de comparaison
		infoSortie = new Information<Boolean>();
		
		//Ajout d'un bit au contenu du message 
		infoEntree.add(true);
		n++;
		//Ajout des bits correspondants pour message de sortie
		infoSortie.add(true);
		infoSortie.add(false);
		infoSortie.add(true);
		
		infoEntree.add(false);
		n++;
		infoSortie.add(false);
		infoSortie.add(true);
		infoSortie.add(false);
		
		transducteurEmission = new TransducteurEmission();
		transducteurEmission.recevoir(infoEntree);
	}
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void testNbElementsEntree() {
		collector.checkThat(transducteurEmission.getInformationRecue().nbElements(), is(n));
	}

	@Test
	public void testNbElementsSortie() {
		collector.checkThat(transducteurEmission.getInformationEmise().nbElements(), is(n*3));
	}
	
	@Test
	public void testValeursEntree() {
		for(int i = 0 ; i < n ; i++)
			collector.checkThat(transducteurEmission.getInformationRecue().iemeElement(i), is(infoEntree.iemeElement(i)));
	}
	
	@Test
	public void testValeursSortie() {
		for(int i = 0 ; i < n*3 ; i++)
			collector.checkThat(transducteurEmission.getInformationEmise().iemeElement(i), is(infoSortie.iemeElement(i)));
	}
}
