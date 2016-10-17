package coders;

import information.Information;

public class DecoderNRZ extends Decoder {

	public DecoderNRZ(float min, float max, int nbEchantillon) {
		super(min, max, nbEchantillon);
	}

	@Override
	public Information<Boolean> decode(Information<Float> msg) {
		Information<Boolean> informationNRZ = new Information<Boolean>(); 
		
		for (int i = 0; i < (msg.nbElements()/nbEchantillon); i++) {
			if (msg.iemeElement(i*nbEchantillon) > seuilDeDetection) {
					informationNRZ.add(true);
			} else {
					informationNRZ.add(false);
			}
		}
		
		return informationNRZ;
	}

}
