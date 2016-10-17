package coders;

import information.Information;

public class DecoderRZ extends Decoder{

	public DecoderRZ(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	@Override
	public Information<Boolean> decode(Information<Float> msg) {
		Information<Boolean> signalRZ = new Information<Boolean>();
		int i = 0;
		while (i < msg.nbElements()) {
			float moyenne = 0.0f;
			float somme = 0.0f;
			int debut = nbEchantillon / 3;
			int fin = debut * 2;

			for (int j = debut; j < fin; j++) {
				somme += msg.iemeElement(i + j);
			}

			i += nbEchantillon;
			moyenne = somme / (fin - debut);
			signalRZ.add(moyenne > seuilDeDetection);
		}
		return signalRZ;
	}

}
