package transducteurs;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import information.Information;

public class TransducteurReceptionTest {

	TransducteurReception transducteurReception;
	Information<Boolean> infoEntree;
	Information<Boolean> infoSortie;
	int n = 0;
	
	@Before
	public void setUp() throws Exception {
		
		//Creation d'un message en entree
		infoEntree = new Information<Boolean>();
		//Creation d'un message en sortie qui servira de comparaison
		infoSortie = new Information<Boolean>();
		
		//Ajout de trois bits au contenu du message 
		for (int i = 0 ; i < 3 ; i++)
			infoEntree.add(false);
		
		//Ajout du bit correspondant pour message de sortie
		infoSortie.add(false);
		
		n++;

		infoEntree.add(false);
		infoEntree.add(false);
		infoEntree.add(true);
		
		infoSortie.add(true);
		
		n++;
		
		transducteurReception = new TransducteurReception();
		transducteurReception.recevoir(infoEntree);
	}
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void testNbElementsEntree() {
		collector.checkThat(transducteurReception.getInformationRecue().nbElements(), is(n*3));
	}

	@Test
	public void testNbElementsSortie() {
		collector.checkThat(transducteurReception.getInformationEmise().nbElements(), is(n));
	}
	
	@Test
	public void testValeursEntree() {
		for(int i = 0 ; i < n*3 ; i++)
			collector.checkThat(transducteurReception.getInformationRecue().iemeElement(i), is(infoEntree.iemeElement(i)));
	}
	
	@Test
	public void testValeursSortie() {
		for(int i = 0 ; i < n ; i++)
			collector.checkThat(transducteurReception.getInformationEmise().iemeElement(i), is(infoSortie.iemeElement(i)));
	}
}
