package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;
/**
 * Un transmetteur analogique parfait reçoit une information<Float> et le retransmet telle qu'elle
 *
 */
public class TransmetteurAnalogiqueParfait extends Transmetteur<Float, Float> {

	/**
	 * Méthode pour reçevoir une information (un signal)
	 */
	@Override
	public void recevoir(Information<Float> information) throws InformationNonConforme {
		informationRecue = information;	
		emettre();
	}

	/**
	 * Méthode pour emettre une information (un signal)
	 */
	@Override
	public void emettre() throws InformationNonConforme {
		for (DestinationInterface <Float> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(informationRecue);
		}
		this.informationEmise = informationRecue;
	}

}
