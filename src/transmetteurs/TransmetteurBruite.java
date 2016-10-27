package transmetteurs;

import information.Information;
import information.InformationNonConforme;

public class TransmetteurBruite extends Transmetteur<Float, Float> {

	private float snrDB;
	private int seed = 0;
	
	public TransmetteurBruite(float snrDB){
		this.snrDB = snrDB; 
	}
	public TransmetteurBruite(float snrDB, int seed){
		this.snrDB = snrDB; 
		this.seed = seed;
	}
	
	@Override
	public void recevoir(Information<Float> information) throws InformationNonConforme {
		// TODO Auto-generated method stub
		informationRecue = information;
		float puissanceBruit = calculPuissanceBruit(informationRecue, snrDB);
		BruitBlancGaussien generateurBruit; 
		if (seed == 0)
			generateurBruit = new BruitBlancGaussien(puissanceBruit);
		else 
			generateurBruit = new BruitBlancGaussien(puissanceBruit, seed);
		
		Information<Float> bruit = generateurBruit.generateurBruitBG(informationRecue.nbElements());
		
		informationEmise = signalBruite(informationRecue, bruit);
		emettre();
	}

	@Override
	public void emettre() throws InformationNonConforme {
		// TODO Auto-generated method stub
		
	}

	private float calculPuissanceBruit(Information<Float> informationRecue, float snrDB){
		float pBruit;
		float sommeCarreSignal = 0f;
		for (float info : informationRecue)
			sommeCarreSignal += Math.pow(info,2); 
		float pSignal= sommeCarreSignal / informationRecue.nbElements();
		float snr = (float) Math.pow(10,(snrDB/10));
		pBruit = (float) (pSignal/snr);
		return pBruit;
	}
	
	private Information<Float> signalBruite(Information<Float> information, Information<Float> bruit) {
		Information<Float> signal = new Information<Float> ();
		for (int i = 0 ; i < information.nbElements() ; i++)
			signal.add(information.iemeElement(i) + bruit.iemeElement(i));
		return signal;
	}
}
