package coders;

import information.Information;

/**
 * Classe DecoderNRZT qui herite de la classe abstraite Decoder.
 * A pour but de decoder un signal NRZT en reception.
 */

public class DecoderNRZT extends Decoder {
	
	/**
	 * Constructeur de la classe DecoderNRZT.
	 * Affecte les valeurs passees en argument aux attributs de la classe mere en appelant son constructeur.
	 * 
	 * @param min
	 * 		Amplitude minimale du signal de depart.
	 * @param max
	 *      Amplitude maximale du signal de depart.
	 * @param nbEchantillon
	 *     	Nombre d'echantillons par bit.
	 */
	public DecoderNRZT(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	/**
	 * Methode publique qui decode le signal NRZT de type Information<Float> en un message de type Information<Boolean>.
	 * 
	 * @return informationNRZT
	 * 		Le signal NRZT decode de type Information<Boolean>.
	 */
	@Override
	public Information<Boolean> decode(Information<Float> msg) {
		Information<Boolean> signalNRZT = new Information<Boolean>();
		float seuil = calculSeuil(msg);
		for (int i = 0; (i+nbEchantillon) < msg.nbElements(); i += nbEchantillon) {
			float moyenne = 0.0f;
			float somme = 0.0f;
			int t1 = nbEchantillon / 3;
			int t3 = t1;
			int t2 = nbEchantillon - t1 - t3;

			for (int j = 0; j < t2; j++) {
				somme += msg.iemeElement(i + t1 + j);
			}

			moyenne = somme / t2;
			signalNRZT.add(moyenne > seuil);
		}
		return signalNRZT;
	}

}
