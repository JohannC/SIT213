package utils;

import information.Information;

public class AdditionneurSignaux {
	/**
	 * Methode pour additionner le signal 1 et le signal 2
	 * Les deux doivent etre de type float
	 * @param signal 1
	 * @param signal 2
	 * @return addition des 2 signaux
	 */
	
	public static Information<Float> additionnerSignaux(Information<Float> signal1, Information<Float> signal2) {
		Information<Float> signal = new Information<Float> ();
		int tailleSignal1 = signal1.nbElements();
		int tailleSignal2 =signal2.nbElements();
		int length;
		if(tailleSignal1 < tailleSignal2){
			length = tailleSignal2;
		}
		else {
			length = tailleSignal1;
		}
		for (int i = 0 ; i < length ; i++){
			if(i < tailleSignal1 && i < tailleSignal2)
				signal.add(signal1.iemeElement(i) + signal2.iemeElement(i));
			else if(i < tailleSignal1 && i >= tailleSignal2)
				signal.add(signal1.iemeElement(i));
			else
				signal.add(signal2.iemeElement(i));
		}
		return signal;
	}
}
