package Utils;

import information.Information;

/**
 * Classe CanalTrajetsMultiples
 * A partir du signal recu va generer un signal retarde et attenue
 */
public class RetardateurSignal {

	/**
	 * Methode publique qui s'occupe de creer le signal retarde et attenue.
	 * 
	 * @return informationRetardee
	 * 		Le nouveau signal retarde et attenue de type Information<Float>.
	 */
	public static  Information<Float> getSignalRetardee(Information<Float> informationRecue, int retard, float attenuation){
		Information<Float>informationRetardee = new Information<Float>();
		for (int i = 0 ; i < retard ; i++)
			informationRetardee.add(0f);
		
		for (float e : informationRecue)
			informationRetardee.add(e*attenuation);
		
		return informationRetardee;
	}



}
