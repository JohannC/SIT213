package canaux;

import information.Information;

/**
 * Classe CanalTrajetsMultiples
 * A partir du signal recu va generer un signal retarde et attenue
 */
public class CanalTrajetsMultiples {
	
	private Information <Float> informationRecue;
	private Information <Float> informationRetardee = null;
	private int retard;
	private float attenuation;
	
	/**
	 * Construction de la classe CanalTrajetsMultiples
	 * S'occupe d'affecter à ses attributs les parametres associes
	 * @param information le signal recu de type Information<Float>
	 * @param retard 
	 * @param attenuation
	 */
	public void CanalTrajetsMultiple(Information<Float> information, int retard, float attenuation){
		informationRecue = information;
		this.retard = retard;
		this.attenuation = attenuation;
	}

	/**
	 * Methode qui s'occupe de creer le signal retarde et attenue
	 * 
	 */
	public Information<Float> monInfoRetardee(){
		informationRetardee = new Information<Float>();
		for (int i = 0 ; i < retard ; i++)
			informationRetardee.add(0f);
		
		for (float e : informationRecue)
			informationRetardee.add(e*attenuation);
		
		return informationRetardee;
	}



}
