package transmetteurs;

import excpetion.ArgumentsException;

public abstract class Transcepteur<R, E> extends Transmetteur<R, E> {
	protected int nbEchantillon;
	protected float min;
	protected float max;

	private static int DEFAULT_NB_ECHANTILLON = 30;
	private static float DEFAULT_MIN = 0.0f;
	private static float DEFAULT_MAX = 1.0f;

	public Transcepteur() {
		this.min = DEFAULT_MIN;
		this.max = DEFAULT_MAX;
		this.nbEchantillon = DEFAULT_NB_ECHANTILLON;
	}

	public Transcepteur(int nbEchantillon) throws ArgumentsException {
		this.min = DEFAULT_MIN;
		this.max = DEFAULT_MAX;
		if(nbEchantillon < 1){
			throw new ArgumentsException("Le nombre d'échantillons doit être une valeur entière positive");
		}
		this.nbEchantillon = nbEchantillon;
	}

	public Transcepteur(float min, float max) throws ArgumentsException {
		
		if(min >= max){
			throw new ArgumentsException("La valeur minimale doit être infiérieure à la valeur maximale");
		}
		this.min = min;
		this.max = max;
		this.nbEchantillon = DEFAULT_NB_ECHANTILLON;
	}

	public Transcepteur(float min, float max, int nbEchantillon) throws ArgumentsException {
		
		if(nbEchantillon < 1 || min >= max){
			throw new ArgumentsException("Le nombre d'échantillons doit être une valeur entière positive et la valeur minimale doit être infiérieure à la valeur maximale ");
		}
		this.nbEchantillon = nbEchantillon;
		this.min = min;
		this.max = max;
	}
}
