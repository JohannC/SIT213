package transducteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;
import transmetteurs.Transmetteur;

/**
 * Classe TransducteurEmission qui herite de la classe abstraite Transmetteur.
 * A pour but de coder la source du signal.
 */
public class TransducteurEmission extends Transmetteur<Boolean, Boolean> {
	
	/**
	 * Methode publique pour recevoir une information. 
	 * @param information 
	 * 		Le message a encoder de type Information<Boolean>.
	 */
	@Override
	public void recevoir(Information<Boolean> information) throws InformationNonConforme {
		informationRecue = information;
		codageDeSource();
		emettre();
	}
	
	/**
	 * Methode publique pour emettre une information. 
	 */
	@Override
	public void emettre() throws InformationNonConforme {
		for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(informationEmise);
		}
	}
	
	/**
	 * Methode privee pour encoder un message.
	 */
	private void codageDeSource () {
		
		Information<Boolean> info = new Information<Boolean>();
		
		for (Boolean bit : informationRecue) {
			if (bit) {
				info.add(true);
				info.add(false);
				info.add(true);
			}
			else {
				info.add(false);
				info.add(true);
				info.add(false);
			}
		}
		
		informationEmise = info;
	}

}
