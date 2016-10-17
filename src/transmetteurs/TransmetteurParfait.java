package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * Classe simulant un canal de transmission parfait entre une Source et une
 * Destination. Il n'y a aucune perte entre les deux. Les données transitées
 */
public class TransmetteurParfait extends Transmetteur<Boolean, Boolean> {

	@Override
	public void emettre() throws InformationNonConforme {
		for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
			destinationConnectee.recevoir(informationRecue);
		}
		this.informationEmise = informationRecue;

	}

	@Override
	public void recevoir(Information<Boolean> information) throws InformationNonConforme {
		informationRecue = information;
		emettre();
	}

}
