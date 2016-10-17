package coders;

/**
 * La classe Encoder permet de Encoder l'information en réception
 *
 */
public abstract class Encoder implements IEncoder {

	protected int nbEchantillon;
	protected float min;
	protected float max;

	/**
	 * Construteur de la classe Encoder
	 * 
	 * @param min
	 *            amplitude minimale
	 * @param max
	 *            amplitude maximale
	 * @param nbEchantillon
	 *            nombre d'échantillons par bit
	 */
	public Encoder(float min, float max, int nbEchantillon) {
		this.nbEchantillon = nbEchantillon;
		this.min = min;
		this.max = max;
	}
}
