package canaux;

import static org.hamcrest.CoreMatchers.is;
import information.Information;

import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class CanalTrajetsMultiplesTest {
	
	private static final int NBECH = 60;
	
	private static final float ATTENUATION = 0.5f;
	
	Information<Float> informationTestRetard;
	
	Information<Float> information;
	
	Random r = new Random();
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Before
	public void setUp() throws Exception {
		informationTestRetard = new Information<Float>();
		information = new Information<Float>();
		
		for(int j = 0; j < NBECH; j++){
			informationTestRetard.add(0f);
		}
		
		for (int i=0 ; i < 180 ; i++){
			Boolean valeur = r.nextBoolean();
			if(valeur){
				informationTestRetard.add(ATTENUATION);
				information.add(1f);
			}
			else { 
				informationTestRetard.add(0f);
				information.add(0f);
			}
		}
	}

	@Test
	public void inforRatardeeCorrectTest() {
		
		CanalTrajetsMultiples canal = new CanalTrajetsMultiples(information, NBECH, ATTENUATION);
		collector.checkThat(canal.monInfoRetardee(), is(informationTestRetard));
	}

}
