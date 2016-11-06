package transmetteurs;

import canaux.CanalTrajetsMultiples;
import information.Information;

public class Utils {
	/**
	 * Methode pour additionner le signal 1 et le signal 2
	 * Les deux doivent etre de type float
	 * @param signal 1
	 * @param signal 2
	 * @return addition des 2 signaux
	 */
	private static CanalTrajetsMultiples canal;
	
	public static Information<Float> additionnerSignaux(Information<Float> signal1, Information<Float> signal2) {
		Information<Float> signal = new Information<Float> ();
		for (int i = 0 ; i < signal1.nbElements() ; i++)
			signal.add(signal1.iemeElement(i) + signal2.iemeElement(i));
		return signal;
	}
	
	/**
	 * Methode pour construire un signal multitrajet tel que : signal Multitrajet = signal direct + signal direct retardé
	 * @param informationRecue le signal direct
	 * @param retard le retard ajouté
	 * @param attenuation le facteur d'atténuation du signal
	 * @return
	 */
	
	public static Information<Float> enableTrajetMultiple(Information<Float> informationRecue, int retard, float attenuation) {
		canal = new CanalTrajetsMultiples(informationRecue, retard, attenuation);
		Information<Float> signalRetarde = canal.monInfoRetardee();
		return additionnerSignaux(informationRecue, signalRetarde);
		
	}
}
