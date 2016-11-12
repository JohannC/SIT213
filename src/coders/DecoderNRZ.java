package coders;

import information.Information;

/**
 * Classe DecoderNRZ qui herite de la classe abstraite Decoder.
 * A pour but de decoder un signal NRZ en reception.
 */
public class DecoderNRZ extends Decoder {
	
	/**
	 * Constructeur de la classe DecoderNRZ.
	 * Affecte les valeurs passees en argument aux attributs de la classe mere en appelant son constructeur.
	 * 
	 * @param min
	 * 		Amplitude minimale du signal de depart.
	 * @param max
	 *      Amplitude maximale du signal de depart.
	 * @param nbEchantillon
	 *     	Nombre d'echantillons par bit.
	 */
	public DecoderNRZ(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	/**
	 * Methode publique qui decode le signal NRZ de type Information<Float> en un message de type Information<Boolean>.
	 * 
	 * @return informationNRZ
	 * 		Le signal NRZ decode de type Information<Boolean>.
	 */
	@Override
	public Information<Boolean> decode(Information<Float> msg) {
		Information<Boolean> informationNRZ = new Information<Boolean>();
		float seuil = calculSeuil(msg);
		for (int i = 0; (i+nbEchantillon) <= msg.nbElements(); i += nbEchantillon) {

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
