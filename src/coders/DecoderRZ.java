package coders;

import information.Information;

/**
 * DecoderRZ permet de décoder un signal RZ
 * 
 */
public class DecoderRZ extends Decoder {
	/**
	 * Constructeur pour initialiser le décodeur RZ
	 * 
	 * @param min
	 *            amplitude minimale
	 * @param max
	 *            amplitude maximale
	 * @param nbEchantillon
	 *            nombre d'échantillons par bit
	 */

	public DecoderRZ(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	/**
	 * Méthode pour décoder le signal RZ Traransforme un Information<Float> en
	 * Information<Boolean>
	 * 
	 * @return informationRZ le signal RZ décodé
	 */

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
