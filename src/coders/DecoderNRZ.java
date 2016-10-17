package coders;

import information.Information;

/**
 * DecoderNRZ permet de décoder un signal NRZ
 * 
 * @author Arouna
 *
 */
public class DecoderNRZ extends Decoder {
	/**
	 * onstructeur pour initialiser le décodeur NRZ
	 * @param min
	 *            amplitude minimale
	 * @param max
	 *            amplitude maximale
	 * @param nbEchantillon
	 *            nombre d'échantillons par bit
	 */
	public DecoderNRZ(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	/**
	 * Méthode pour décoder le signal NRZ
	 * Elle transforme un Information<Float> en Information<Boolean>
	 */
	@Override
	public Information<Boolean> decode(Information<Float> msg) {
		Information<Boolean> informationNRZ = new Information<Boolean>();

		for (int i = 0; i < (msg.nbElements() / nbEchantillon); i++) {
			if (msg.iemeElement(i * nbEchantillon) > seuilDeDetection) {
				informationNRZ.add(true);
			} else {
				informationNRZ.add(false);
			}
		}

		return informationNRZ;
	}

}
