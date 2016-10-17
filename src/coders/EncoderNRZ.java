package coders;

import information.Information;

/**
 * EncoderNRZ permet de coder un signal NRZ
 * 
 */

public class EncoderNRZ extends Encoder {

	/**
	 * Constructeur pour initialiser le Codeur NRZ
	 * 
	 * @param min
	 *            amplitude minimale
	 * @param max
	 *            amplitude maximale
	 * @param nbEchantillon
	 *            nombre d'échantillons par bit
	 */
	public EncoderNRZ(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	/**
	 * Méthode pour Coder le signal NRZ Traransforme un Information<Boolean> en
	 * Information<Float>
	 * 
	 * @return informationNRZ : le signal NRZ encodé
	 */

	@Override
	public Information<Float> encode(Information<Boolean> msg) {
		Information<Float> informationNRZ = new Information<Float>();

		for (int i = 0; i < msg.nbElements(); i++) {
			if (msg.iemeElement(i)) {
				for (int j = 0; j < nbEchantillon; j++)
					informationNRZ.add(max);
			} else {
				for (int j = 0; j < nbEchantillon; j++)
					informationNRZ.add(min);
			}
		}

		return informationNRZ;
	}

}
