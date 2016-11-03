package transmetteurs;

/**
* Classe abstraite Transcepteur qui s'occuppe de conserver en commun les attributs :
* - nbEchantillon, le nombre d'echantillon par bit
* - min et max les valeurs limites du signal analogique
* @param <R> Type de l'information en reception
* @param <E> Type de l'information en emission
*/
public abstract class Transcepteur<R, E> extends Transmetteur<R, E> {
	protected int nbEchantillon;
	protected float min;
	protected float max;
	/**
	 * Constructeur de la classe Transcepteur.
	 * S'occupe d'attribuer les valeurs passees en arguments aux attributs de la classe
	 * @param min amplitude minimale du signal
	 * @param max amplitude maximale du signal
	 * @param nbEchantillon le nombre d'echantillons par bit
	 */
	public Transcepteur(float min, float max, int nbEchantillon) {
		this.nbEchantillon = nbEchantillon;
		this.min = min;
		this.max = max;
	}
}
