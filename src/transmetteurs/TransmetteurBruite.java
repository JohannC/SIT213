package transmetteurs;

import information.Information;
import information.InformationNonConforme;

public class TransmetteurBruite extends Transmetteur<Float, Float> {

	@Override
	public void recevoir(Information<Float> information) throws InformationNonConforme {
		// TODO Auto-generated method stub
		informationRecue = information;
		emettre();
	}

	@Override
	public void emettre() throws InformationNonConforme {
		// TODO Auto-generated method stub
		
	}

	private float calculPuissanceBruit(Information<Float> informationRecue, float snr){
		float pBruit;
		float sommeCarreSignal = 0f;
		for (float info : informationRecue)
			sommeCarreSignal += Math.pow(info,2); 
		float pSignal= sommeCarreSignal / informationRecue.nbElements();
		pBruit = (float) (pSignal/Math.pow(10,(snr/10)));
		return pBruit;
	}
}
