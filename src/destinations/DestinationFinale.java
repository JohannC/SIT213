package destinations;

import information.Information;
import information.InformationNonConforme;

/**
 * La destination finale du signal
 */
public class DestinationFinale extends Destination<Boolean> {
	/**
	 * Méthode pour reçevoir une Information<Boolean>
	 */
	@Override
	public void recevoir(Information<Boolean> information) throws InformationNonConforme {
		informationRecue = information;
	}

}
