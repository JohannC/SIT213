package coders;

public abstract class Encoder implements IEncoder{
	
	protected int nbEchantillon;
	protected float min;
	protected float max;
	
	public Encoder(float min, float max, int nbEchantillon){
		this.nbEchantillon = nbEchantillon;
		this.min = min;
		this.max = max;
	}
}
