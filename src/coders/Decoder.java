package coders;

import information.Information;

/**
 * La classe Decoder permet de décoder l'information en réception
 * Cette classe implémente l'interface IDecoder.
 * Il s'agit d'une classe abstraite qui s'occupe de factoriser les attributs suivants :
 * - nbEchantillon : nombre d'échantillons par bit
 * - min : amplitude minimale du signal analogique
 * - max : amplitude maximale du signal analogique
 * - seuilDeDetection : seuil permettant de décider entre un bit '0' et un bit '1'
 */

public abstract class Decoder implements IDecoder {

	protected int nbEchantillon;
	protected float min;
	protected float max;
	protected float seuilDeDetection;

	/**
	 * Constructeur de la classe Decoder.
	 * S'occupe d'attribuer les valeurs passées en arguments aux attributs de la classe.
	 * Calcule le seuil de détection à partir des valeurs extrêmes du signal analogique.
	 * 
	 * @param min
	 *            amplitude minimale
	 * @param max
	 *            amplitude maximale
	 * @param nbEchantillon
	 *            nombre d'échantillons par bit
	 */
	public Decoder(float min, float max, int nbEchantillon) {
		this.nbEchantillon = nbEchantillon;
		this.min = min;
		this.max = max;
		seuilDeDetection = (Math.abs(Math.abs(max) - Math.abs(min))) / 2 + min;
	}

	//Creation d'un nouveau seuil : moyenne signal/nbEchantillons
	protected float calculSeuil(Information<Float> msg){
		float seuil, sommeMsg = 0;
		for (float e : msg)
			sommeMsg += e;
		seuil = sommeMsg/msg.nbElements() ;
		
		return seuil ;
	}
}
