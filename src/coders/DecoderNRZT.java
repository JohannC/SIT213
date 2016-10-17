package coders;

import information.Information;

public class DecoderNRZT extends Decoder{
	
	public DecoderNRZT(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	@Override
	public Information<Boolean> decode(Information<Float> msg) {
		Information<Boolean> signalNRZT = new Information<Boolean>();
		
		for(int i = 0; i < msg.nbElements(); i += nbEchantillon) {
			float moyenne = 0.0f;
			float somme = 0.0f;
			int t1 = nbEchantillon / 3;
			int t3 = t1;
			int t2 = nbEchantillon - t1 - t3;

			for (int j = 0; j < t2; j++) {
				somme += msg.iemeElement(i + t1 + j);
			}
			
			moyenne = somme / t2;
			signalNRZT.add(moyenne > seuilDeDetection);
		}
		return signalNRZT;
	}

}
