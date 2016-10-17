package coders;

/**
 * La classe Decoder permet de décoder l'information en réception
 *
 */

public abstract class Decoder implements IDecoder {

	protected int nbEchantillon;
	protected float min;
	protected float max;
	protected float seuilDeDetection;

	/**
	 * Construteur de la classe Decoder
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
		seuilDeDetection = (max + min) / 2;
	}

}
