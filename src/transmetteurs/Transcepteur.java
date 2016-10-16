package transmetteurs;

import information.InformationNonConforme;

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

	public Transcepteur(int nbEchantillon) throws InformationNonConforme {
		this.min = DEFAULT_MIN;
		this.max = DEFAULT_MAX;
		if(nbEchantillon < 1){
			throw new InformationNonConforme();
		}
		this.nbEchantillon = nbEchantillon;
	}

	public Transcepteur(float min, float max) throws InformationNonConforme {
		
		if(min >= max){
			throw new InformationNonConforme();
		}
		this.min = min;
		this.max = max;
		this.nbEchantillon = DEFAULT_NB_ECHANTILLON;
	}

	public Transcepteur(float min, float max, int nbEchantillon) throws InformationNonConforme {
		
		if(nbEchantillon < 1 || min >= max){
			throw new InformationNonConforme();
		}
		this.nbEchantillon = nbEchantillon;
		this.min = min;
		this.max = max;
	}
}
