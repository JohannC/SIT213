package transmetteurs;

public abstract class Transcepteur<R, E> extends Transmetteur<R, E> {
	protected int nbEchantillon;
	protected float min;
	protected float max;

	public Transcepteur(float min, float max, int nbEchantillon) {
		this.nbEchantillon = nbEchantillon;
		this.min = min;
		this.max = max;
	}
}
