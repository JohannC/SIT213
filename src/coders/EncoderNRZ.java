package coders;

import information.Information;

public class EncoderNRZ extends Encoder {
	
	public EncoderNRZ(float min, float max, int nbEchantillon){
		super(min, max, nbEchantillon);
	}
	
	@Override
	public Information<Float> encode(Information<Boolean> msg) {
		Information<Float> informationNRZ = new Information<Float>(); 
		
		for (int i = 0; i < msg.nbElements(); i++) {
			if (msg.iemeElement(i)) {
				for (int j = 0; j < nbEchantillon; j++)
					informationNRZ.add(max);
			} else {
				for (int j = 0; j < nbEchantillon; j++)
					informationNRZ.add(min);
			}
		}
		
		return informationNRZ;
	}

}
