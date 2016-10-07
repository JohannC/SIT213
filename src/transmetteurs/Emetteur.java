package transmetteurs;

public abstract class Emetteur<R, E> extends Transmetteur<R, E> {
	protected int nbEchantillon;
	protected float min;
	protected float max;

	private static int DEFAULT_NB_ECHANTILLON = 30;
	private static float DEFAULT_MIN = 0.0f;
	private static float DEFAULT_MAX = 1.0f;

	public Emetteur() {
		this.min = DEFAULT_MIN;
		this.max = DEFAULT_MAX;
		this.nbEchantillon = DEFAULT_NB_ECHANTILLON;
	}

	public Emetteur(int nbEchantillon) {
		this.min = DEFAULT_MIN;
		this.max = DEFAULT_MAX;
		this.nbEchantillon = nbEchantillon;
	}

	public Emetteur(float min, float max) {
		this.min = min;
		this.max = max;
		this.nbEchantillon = DEFAULT_NB_ECHANTILLON;
	}

	public Emetteur(float min, float max, int nbEchantillon) {
		this.min = min;
		this.max = max;
		this.nbEchantillon = nbEchantillon;
	}

}
