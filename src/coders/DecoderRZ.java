package coders;

import information.Information;

/**
 * Classe DecoderRZ qui herite de la classe abstraite Decoder.
 * A pour but de decoder un signal RZ en reception.
 */
public class DecoderRZ extends Decoder {
	
	/**
	 * Constructeur de la classe DecoderRZ.
	 * Affecte les valeurs passees en argument aux attributs de la classe mere en appelant son constructeur.
	 * 
	 * @param min
	 * 		Amplitude minimale du signal de depart.
	 * @param max
	 *      Amplitude maximale du signal de depart.
	 * @param nbEchantillon
	 *     	Nombre d'echantillons par bit.
	 */
	public DecoderRZ(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	/**
	 * Methode publique qui decode le signal RZ de type Information<Float> en un message de type Information<Boolean>.
	 * 
	 * @return informationRZ
	 * 		Le signal RZ decode de type Information<Boolean>.
	 */
	@Override
	public Information<Boolean> decode(Information<Float> msg) {
		Information<Boolean> signalRZ = new Information<Boolean>();
		float seuil = calculSeuil(msg);
		int i = 0;
		while ((i+nbEchantillon) <= msg.nbElements()) {
			float moyenne = 0.0f;
			float somme = 0.0f;
			int debut = nbEchantillon / 3;
			int fin = debut * 2;

			for (int j = debut; j < fin; j++) {
				somme += msg.iemeElement(i + j);
			}

			i += nbEchantillon;
			moyenne = somme / (fin - debut);
			signalRZ.add(moyenne > seuil);
		}
		return signalRZ;
	}

}
