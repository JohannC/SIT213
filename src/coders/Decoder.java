package coders;

import information.Information;

/**
 * Classe abstraite Decoder qui implemente l'interface IDecoder.
 * A pour but de decoder l'information en reception.
 * Factorise les attributs suivants :
 * - nbEchantillon : nombre d'echantillons par bit
 * - min : amplitude minimale du signal analogique
 * - max : amplitude maximale du signal analogique
 * - seuilDeDetection : seuil permettant de decider entre un bit '0' et un bit '1'.
 */

public abstract class Decoder implements IDecoder {

	protected int nbEchantillon;
	protected float min;
	protected float max;
	protected float seuilDeDetection;

	/**
	 * Constructeur de la classe Decoder.
	 * Affecte les valeurs passees en argument aux attributs de la classe associes.
	 * Calcule le seuil de detection a partir des valeurs extremes du signal analogique.
	 * 
	 * @param min
	 * 		Amplitude minimale du signal de depart.
	 * @param max
	 *     	Amplitude maximale du signal de depart.
	 * @param nbEchantillon
	 *    	Nombre d'echantillons par bit.
	 */
	public Decoder(float min, float max, int nbEchantillon) {
		this.nbEchantillon = nbEchantillon;
		this.min = min;
		this.max = max;
		seuilDeDetection = (Math.abs(Math.abs(max) - Math.abs(min))) / 2 + min;
	}

	/**
	 * Methode protected qui retourne le seuil de detection selon le calcul suivant : 
	 * moyenne du signal / nbEchantillons.
	 * 
	 * @param msg
	 * 		Message en entree du decodeur.
	 * @return seuil
	 * 		Seuil de detection calcule.
	 */
	protected float calculSeuil(Information<Float> msg){
		float seuil, sommeMsg = 0;
		for (float e : msg)
			sommeMsg += e;
		seuil = sommeMsg/msg.nbElements() ;
		
		return seuil ;
	}
}
