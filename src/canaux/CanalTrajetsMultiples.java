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
	 * Constructeur de la classe CanalTrajetsMultiples.
	 * Affecte a ses attributs les parametres associes suivant :
	 * 
	 * @param information 
	 * 		Le signal recu de type Information<Float>.
	 * @param retard
	 * 		Le retard qui sera attribue au nouveau signal. 
	 * @param attenuation
	 * 		L'attenuation du nouveau signal par rapport a l'original.
	 */
	public CanalTrajetsMultiples(Information<Float> information, int retard, float attenuation){
		informationRecue = information;
		this.retard = retard;
		this.attenuation = attenuation;
	}

	/**
	 * Methode publique qui s'occupe de creer le signal retarde et attenue.
	 * 
	 * @return informationRetardee
	 * 		Le nouveau signal retarde et attenue de type Information<Float>.
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
