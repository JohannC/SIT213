package coders;

import information.Information;

public class EncoderNRZT extends Encoder {
	
	public EncoderNRZT(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	/** Encode le signal NRZT
	 * 
	 * @return le signal NRZT
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
			if (i == (msg.nbElements()-1)) {
				nextBit = false;
			} else {
				nextBit = msg.iemeElement(i + 1);
			}
			
			encodageBit(signalNRZT, msg.iemeElement(i), precedentBit, nextBit);
		}
		return signalNRZT;
	}
	
	/**
	 
	private Information<Float> encodageNRZT() {
		
	}

	/**
	 * Encode chaque bit du signal NRZT, chaque bit est constitué de
	 * nbEchantillons
	 * 
	 * @param signalNRZT
	 *            le signal NRZT
	 * @param bit
	 *            Un bit du signal NRZT
	 */
	private void encodageBit(Information<Float> signalNRZT, Boolean bit, Boolean precedentBit, Boolean nextBit) {
		int t1 = nbEchantillon / 3;
		int t3 = t1;
		int t2 = nbEchantillon - t1 - t3;
		float deltaAmplitude = max - min;
		if (bit == true) {// bit � 1
			if (precedentBit == false) {//Evite d'avoir 
				for (int i = 0; i < t1; i++) {
					signalNRZT.add(this.min + (float)i / t1 * deltaAmplitude);
				}
			} else {
				for (int i = 0; i < t1; i++) {
					signalNRZT.add(this.max);
				}
			}
			for (int i = 0; i < t2; i++) {
				signalNRZT.add(this.max);
			}

			if(nextBit == true){
				for (int i = 1; i <= t3; i++) {
					signalNRZT.add(this.max);
				}
			} else {
				for (int i = 1; i <= t3; i++) {
					signalNRZT.add(this.max - (float)i / t3 * deltaAmplitude);
				}
			}
			

		} else {
			for (int i = 1; i <= nbEchantillon; i++) {
				signalNRZT.add(this.min);
			}
		}

	}
	
}
