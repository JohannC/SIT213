package coders;

import information.Information;

/**
 * Classe EncoderNRZT qui herite de la classe abstraite Encoder.
 * A pour but d'encoder un signal NRZT en emission.
 */

public class EncoderNRZT extends Encoder {

	/**
	 * Constructeur de la classe EncoderNRZT.
	 * Affecte les valeurs passees en argument aux attributs de la classe mere en appelant son constructeur.
	 * 
	 * @param min
	 * 		Amplitude minimale du signal de depart.
	 * @param max
	 *      Amplitude maximale du signal de depart.
	 * @param nbEchantillon
	 *     	Nombre d'echantillons par bit.
	 */
	public EncoderNRZT(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	/**
	 * Methode publique qui encode un message de type Information<Boolean> en un message de type Information<Float>.
	 * L'encodage respecte les normes d'un signal NRZT.
	 * 
	 * @return informationNRZT
	 * 		Le signal NRZT encode de type Information<Float>.
	 */
	@Override
	public Information<Float> encode(Information<Boolean> msg) {
		Information<Float> signalNRZT = new Information<Float>();
		for (int i = 0; i < msg.nbElements(); i++) {
			Boolean precedentBit;
			Boolean nextBit;
			if (i == 0) {
				precedentBit = false;
			} else {
				precedentBit = msg.iemeElement(i - 1);
			}
			if (i == (msg.nbElements() - 1)) {
				nextBit = false;
			} else {
				nextBit = msg.iemeElement(i + 1);
			}

			encodageBit(signalNRZT, msg.iemeElement(i), precedentBit, nextBit);
		}
		return signalNRZT;
	}

	/**
	 * Methode privee qui s'occupe d'encoder un bit en un signal de type Information<Float>.
	 * L'encodage suit les normes d'un signal NRZT et chaque bit est constitue de nbEchantillons.
	 * 
	 * @param signalNRZT
	 * 		Le signal NRZT qui conserve tous les bits encodes en Information<Float>.
	 * @param bit
	 * 		Le bit que l'on souhaite encode.
	 */
	private void encodageBit(Information<Float> signalNRZT, Boolean bit, Boolean precedentBit, Boolean nextBit) {
		int t1 = nbEchantillon / 3;
		int t3 = t1;
		int t2 = nbEchantillon - t1 - t3;
		float deltaAmplitude = Math.abs(max - min);;
		if (bit == true) {// bit � 1
			if (precedentBit == false) {// Evite d'avoir
				for (int i = 0; i < t1; i++) {
					signalNRZT.add(this.min + (float) i / t1 * deltaAmplitude);
				}
			} else {
				for (int i = 0; i < t1; i++) {
					signalNRZT.add(this.max);
				}
			}
			for (int i = 0; i < t2; i++) {
				signalNRZT.add(this.max);
			}

			if (nextBit == true) {
				for (int i = 1; i <= t3; i++) {
					signalNRZT.add(this.max);
				}
			} else {
				for (int i = 1; i <= t3; i++) {
					signalNRZT.add(this.max - (float) i / t3 * deltaAmplitude);
				}
			}

		} else {
			for (int i = 1; i <= nbEchantillon; i++) {
				signalNRZT.add(this.min);
			}
		}

	}

}
