package coders;

/**
 * Classe abstraite Encoder qui implemente l'interface IEncoder.
 * A pour but d'encoder l'information en emission.
 * Factorise les attributs suivants :
 * - nbEchantillon : nombre d'echantillons par bit
 * - min : amplitude minimale du signal analogique
 * - max : amplitude maximale du signal analogique.
 */
public abstract class Encoder implements IEncoder {

	protected int nbEchantillon;
	protected float min;
	protected float max;

	/**
	 * Constructeur de la classe Encoder.
	 * Affecte les valeurs passees en argument aux attributs de la classe associes.
	 * 
	 * @param min
	 * 		Amplitude minimale du signal de depart.
	 * @param max
	 *     	Amplitude maximale du signal de depart.
	 * @param nbEchantillon
	 *    	Nombre d'echantillons par bit.
	 */
	public Encoder(float min, float max, int nbEchantillon) {
		this.nbEchantillon = nbEchantillon;
		this.min = min;
		this.max = max;
	}
}
