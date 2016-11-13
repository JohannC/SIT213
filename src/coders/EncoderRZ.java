package coders;

import information.Information;

/**
 * Classe EncoderRZ qui herite de la classe abstraite Encoder.
 * A pour but d'encoder un signal RZ en emission.
 */
public class EncoderRZ extends Encoder {

	/**
	 * Constructeur de la classe EncoderRZ.
	 * Affecte les valeurs passees en argument aux attributs de la classe mere en appelant son constructeur.
	 * 
	 * @param min
	 * 		Amplitude minimale du signal de depart.
	 * @param max
	 *      Amplitude maximale du signal de depart.
	 * @param nbEchantillon
	 *     	Nombre d'echantillons par bit.
	 */
	public EncoderRZ(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	/**
	 * Methode publique qui encode un message de type Information<Boolean> en un message de type Information<Float>.
	 * L'encodage respecte les normes d'un signal RZ.
	 * 
	 * @return informationRZ
	 * 		Le signal RZ encode de type Information<Float>.
	 */
	@Override
	public Information<Float> encode(Information<Boolean> msg) {
		Information<Float> signalRZ = new Information<Float>();
		for (int i = 0; i < msg.nbElements(); i++) {
			encodageBitRZ(signalRZ, msg.iemeElement(i));
		}
		return signalRZ;
	}

	/**
	 * Methode privee qui s'occupe d'encoder un bit en un signal de type Information<Float>.
	 * L'encodage suit les normes d'un signal RZ et chaque bit est constitue de nbEchantillons.
	 * 
	 * @param signalRZ
	 * 		Le signal RZ qui conserve tous les bits encodes en Information<Float>.
	 * @param bit
	 * 		Le bit que l'on souhaite encode.
	 */
	private void encodageBitRZ(Information<Float> signalRZ, Boolean bit) {
		int t1 = nbEchantillon / 3;
		int t3 = t1;
		int t2 = nbEchantillon - t1 - t3;
		float deltaMinMax = Math.abs((this.max) - (this.min));

		for (int i = 0; i < t1; i++) {
			signalRZ.add(this.min);
		}
		for (int i = 1; i <= t2; i++) {
			signalRZ.add(bit ? (float)Math.sin(Math.PI * (float)i/(float)t2)*deltaMinMax + this.min : this.min);
		}

		for (int i = 0; i < t3; i++) {
			signalRZ.add(this.min);
		}
	}

}
