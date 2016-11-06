package coders;

import information.Information;

/**
 * Classe EncoderNRZ qui herite de la classe abstraite Encoder.
 * A pour but d'encoder un signal NRZ en emission.
 */

public class EncoderNRZ extends Encoder {

	/**
	 * Constructeur de la classe EncoderNRZ.
	 * Affecte les valeurs passees en argument aux attributs de la classe mere en appelant son constructeur.
	 * 
	 * @param min
	 * 		Amplitude minimale du signal de depart.
	 * @param max
	 *      Amplitude maximale du signal de depart.
	 * @param nbEchantillon
	 *     	Nombre d'echantillons par bit.
	 */
	public EncoderNRZ(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	/**
	 * Methode publique qui encode un message de type Information<Boolean> en un message de type Information<Float>.
	 * L'encodage respecte les normes d'un signal NRZ.
	 * 
	 * @return informationNRZ
	 * 		Le signal NRZ encode de type Information<Float>.
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
