package coders;

public abstract class Decoder implements IDecoder {
	
	protected int nbEchantillon;
	protected float min;
	protected float max;
	protected float seuilDeDetection;
	
	public Decoder(float min, float max, int nbEchantillon){
		this.nbEchantillon = nbEchantillon;
		this.min = min;
		this.max = max;
		seuilDeDetection = (max - min) / 2;
	}
	
}
