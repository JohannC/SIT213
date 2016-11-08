package Utils;

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
		for (int i = 0 ; i < signal1.nbElements() ; i++)
			signal.add(signal1.iemeElement(i) + signal2.iemeElement(i));
		return signal;
	}
}
