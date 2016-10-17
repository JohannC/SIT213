package transmetteurs;

/**
 * Classe Transcepteur qui s'occuppe de conserver en commun les attributs :
 * - nbEchantillon, le nombre d'échantillon par bit
 * - min et max les valeurs limites du signal analogique
 * @param <R> Type de l'information en réception
 * @param <E> Type de l'information en émission
 */
public abstract class Transcepteur<R, E> extends Transmetteur<R, E> {
	protected int nbEchantillon;
	protected float min;
	protected float max;
	
	/**
	 * Constructeur de la classe Transcepteur.
	 * S'occupe d'attribuer les valeurs passées en arguments aux attributs de la classe
	 * @param min amplitude minimale du signal
	 * @param maxamplitude maximale du signal
	 * @param nbEchantillon le nombre d'échantillons par bit
	 */
	public Transcepteur(float min, float max, int nbEchantillon){
		this.nbEchantillon = nbEchantillon;
		this.min = min;
		this.max = max;
	}
}
