package coders;

import information.Information;

public class EncoderRZ extends Encoder{

	public EncoderRZ(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	@Override
	public Information<Float> encode(Information<Boolean> msg) {
		Information<Float> signalRZ = new Information<Float>();
		for (int i = 0; i < msg.nbElements(); i++) {
			encodageBitRZ(signalRZ, msg.iemeElement(i));
		}
		return signalRZ;
	}
	
	/**
	 * Encode chaque bit du signal RZ, chaque bit est constitué de nbEchantillons
	 * @param signalRZ le signal RZ
	 * @param bit Un bit du signal RZ
	 */
	private void encodageBitRZ(Information<Float> signalRZ, Boolean bit) {
		int t1 = nbEchantillon / 3;
		int t3 = t1;
		int t2 = nbEchantillon - t1 - t3;

		for (int i = 0; i < t1; i++) {
			signalRZ.add(this.min);
		}
		for (int i = 0; i < t2; i++) {
			signalRZ.add(bit ? this.max : this.min);
		}

		for (int i = 0; i < t3; i++) {
			signalRZ.add(this.min);
		}
	}

}
