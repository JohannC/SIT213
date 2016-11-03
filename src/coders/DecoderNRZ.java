package coders;

import information.Information;

/**
 * DecoderNRZ permet de décoder un signal NRZ
 * 
 */
public class DecoderNRZ extends Decoder {
	/**
	 * Constructeur pour initialiser le décodeur NRZ
	 * 
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
	 * Méthode pour décoder le signal NRZ Traransforme un Information<Float> en
	 * Information<Boolean>
	 * 
	 * @return informationNRZ le signal NRZ décodé
	 */
	@Override
	public Information<Boolean> decode(Information<Float> msg) {
		Information<Boolean> informationNRZ = new Information<Boolean>();
		float seuil = calculSeuil(msg);
		for (int i = 0; i < msg.nbElements(); i += nbEchantillon) {
			float moyenne = 0.0f;
			float somme = 0.0f;
			int t1 = nbEchantillon / 3;
			int t3 = t1;
			int t2 = nbEchantillon - t1 - t3;

			for (int j = 0; j < t2; j++) {
				somme += msg.iemeElement(i + t1 + j);
			}

			moyenne = somme / t2;
			informationNRZ.add(moyenne > seuil);
		}

		return informationNRZ;
	}

}
